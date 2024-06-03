from sympy import Symbol, sympify
import math
import json
import matplotlib.pyplot as plt

def isFloat(n):
    try:
        float(n)
    except ValueError:
        return False
    return True

def checkInt(n):
    if not n.isdigit():
        print("Entered value is not an integer")
        exit(-1)

class Output:
    fileOrCliOutput = 0
    file = 0

    def initFileOrCLIOutput(self, fileOrCliOutput):    
        self.fileOrCliOutput = fileOrCliOutput == "1"
        if fileOrCliOutput == "1":
            print("Enter filename for output")
            filename = input()
            try:
                self.file = open(f"{filename}", "w")
            except:
                print("There is no file with that name, or there is no permissions to write into it")
                exit(1)

    def write(self, string = "", end="\n"):
        if not self.fileOrCliOutput:
            print(string, end=end)
        else:
            self.file.write(f"{string}{end}")

    def closeFile(self):
        if self.fileOrCliOutput:
            self.file.close()

def checkSplittedStrOnCount(splittedStr, n):
    if len(splittedStr) != n:
        print(f"You entered incorrect count of values in matrix row")
        exit(-1)

def getFunc():
    filename = "initfunc.json"
    f = open(f"{filename}", "r")
    bodyJson = f.read()
    bodyDict = json.loads(bodyJson)
    availibleFunc = bodyDict['functions']

    print("Choose one function you would like to take values in range")
    for i in range(len(availibleFunc)):
        print(f"{i + 1}. {availibleFunc[i]}")
    num = input().strip()
    while not num.isdigit() and num not in [x + 1 for x in range(len(availibleFunc))]:
        print("You entered not integer or there is not function labeled with that integer - try again")
        print("Choose one function you would like to take values in range")
        for i in range(len(availibleFunc)):
            print(f"{i + 1}. {availibleFunc[i]}")
        num = input().strip()

    return sympify(availibleFunc[int(num) - 1])

def getBoundaries():
    print("Enter boundaries for x in format 'a b'")
    cin = input().strip().split(" ")
    checkSplittedStrOnCount(cin, 2)
    for i in range(len(cin)):
        isFloat(cin[i])
        cin[i] = float(cin[i])
    a, b = cin
    if abs(a) > abs(b):
        a, b = b, a
    if a == b:
        print(f"You entered two equal boundaries")
        exit(-1)
        
    print("Enter count of interval partion")
    cin = input().strip()
    checkInt(cin)
    n = int(cin)
    if n <= 0:
        print(f"count of interval partion cannot be 0 or less than 0")
        exit(-1)

    return (a, b, n)

def getxArray(a, b, n):
    h = abs(a - b) / (n - 1)
    xArray = [0 for _ in range(n)]
    i = 0
    while (i < n):
        xArray[i] = round(float(a + h * i), 5)
        i += 1
    
    return xArray

def getyArray(func, xArray):
    yArray = [0 for _ in range(len(xArray))]
    for i in range(len(xArray)):
        yArray[i] = func.subs({Symbol("x"): xArray[i], Symbol("e"): math.e})

    return yArray

def getxPoint():
    print("Enter argument of function you would like to find the value in it")
    cin = input().strip()
    isFloat(cin)
    x = float(cin)
    
    return x
    
def cliInput():
    print("Are you going to input data in table or get function from config file?")
    print("Enter '1' to use data from table, or '0' to use functions from config file")
    inputMethod = input().strip()
    while inputMethod != '0' and inputMethod != '1':
        print("You entered not '1' and not '0' - try again")
        print("Enter '1' to use data from table, or '0' to use functions from config file")
        inputMethod = input().strip()
    
    if inputMethod == '1':
        print("Enter table")
        print("Firstly enter x values in row (delimit values with space symbol)")
        splittedStr = input().strip().split(" ")
        x = [float(i) for i in splittedStr]
        print("Firstly enter y values in row (delimit values with space symbol)")
        splittedStr = input().strip().split(" ")
        checkSplittedStrOnCount(splittedStr, len(x))
        y = [float(i) for i in splittedStr]
        
        return (x, y)
    
    func = getFunc()
    a, b, n = getBoundaries()
    x = getxArray(a, b, n)
    y = getyArray(func, x)
    
    return (x, y)

def fileInput():
    print("Enter filename for input")
    filename = input()
    try:
        f = open(f"{filename}", "r")
    except:
        print("There is no file with that name, or there is no permissions to read it")
        exit(1)

    splittedStr = f.readline().strip().split(" ")
    x = [float(i) for i in splittedStr]
    splittedStr = f.readline().strip().split(" ")
    checkSplittedStrOnCount(splittedStr, len(x))
    y = [float(i) for i in splittedStr]

    return (x, y)

def printValuesTable(xArray, yArray, yArrayP = [], epsilonArray = [], s = -1):
    output.write("Table of X, Y, epsilon (if aproximate function has passed) values, \
calculated based on entered (and aproximate) function, interval and step")
    output.write("X", end=" ")
    for x in xArray:
        output.write('%9.4f' % x, end=" ")
    output.write()
    output.write("Y", end=" ")
    for y in yArray:
        output.write('%9.4f' % y, end=" ")
    
    if yArrayP != []:
        output.write()
        output.write("P", end=" ")
        for y in yArrayP:
            output.write('%9.4f' % y, end=" ")
    if epsilonArray != []:
        output.write()
        output.write("e", end=" ")
        for eps in epsilonArray:
            output.write('%9.4f' % eps, end=" ")
    if s != -1:
        output.write()
        output.write(f"S = sum(epsilon_i^2) = {s}")
    output.write("\n")

def getLangrValue(xList, yList, xPoint):
    output.write("Calculation with Lagranzh's method")
    l = list()
    n = len(xList)
    for i in range(n):
        p_x = 1
        p_x_i = 1
        p_xStr = ""
        p_x_iStr = ""
        for j in range(n):
            if j != i:
                p_x *= xPoint - xList[j]
                p_xStr += f"({xPoint} - {xList[j]})"
                p_x_i *= xList[i] - xList[j]
                p_x_iStr += f"({xList[i]} - {xList[j]})"
        l.append(p_x / p_x_i * yList[i])
        output.write(f"l_{i} = {p_xStr} / {p_x_iStr} * {yList[i]}")
        
    return sum(l)

def getMaxLenVal(xList):
    max = 0
    for i in xList:
        if max < len(str(i)):
            max = len(str(i))
            
    return max

def isXListHasFiniteDiffs(xList):
    xFirst = xList[0]
    xLast = xList[-1]
    xListCheck = getxArray(xFirst, xLast, len(xList))
    if xListCheck == xList:
        return True

    return False

def getSmallRange(xList, xPoint):
    for i in range(len(xList) - 1):
        if xList[i] <= xPoint < xList[i + 1]:
            return i
    return -1

def getTMul(t, index):
    p = 1
    for i in range(index):
        p *= t - i
    return round(p, 4)

def getDeltaYMatrix(yList):
    matrix = [[0 for _ in range(len(yList) - i)] for i in range(len(yList))]
    matrix[0] = yList
    n = len(yList)
    for i in range(1, n):
        for k in range(0, n - i):
            matrix[i][k] = round(matrix[i - 1][k + 1] - matrix[i - 1][k], 4)
    
    return matrix

def getNewtonEquation(yList, index, t, deltaYMatrix):    
    sum = 0
    for i in range(len(yList) - index):
        output.write(f"{deltaYMatrix[i][index]} * {getTMul(t, i)} / {math.factorial(i)} + ", end="")
        sum += deltaYMatrix[i][index] * getTMul(t, i) / math.factorial(i)

    output.write(f"0 = {sum}")

    return round(sum, 5)
  
def getNewtonMethodWithFiniteDiffs(xList, yList, xPoint):
    output.write("Calculation with Newton's method with finite differencies")
    if not isXListHasFiniteDiffs(xList):
        print("Elements in entered x list doesn't have finite differencies")
        return

    smallRangeIndex = getSmallRange(xList, xPoint)
    if smallRangeIndex == -1:
        print(f"xPoint = {xPoint} is not in range of x list")
        return
    h = xList[1] - xList[0]
    t = (xPoint - xList[smallRangeIndex]) / h
    
    deltaMatrix = getDeltaYMatrix(yList)
    for i in range(len(deltaMatrix)):
        output.write("%11s" % f"delta^{i} y_i", end=" ")
    output.write()
    for i in range(len(deltaMatrix)):
        for j in range(len(deltaMatrix[i])):
            output.write("%11d" % deltaMatrix[j][i], end=" ")
        output.write()
    
    solution = getNewtonEquation(yList, smallRangeIndex, t, deltaMatrix)
    output.write(f"Calculated aproximate value using Newton's method with finite differences = {solution}")
    
    newtonValList = []
    for xPoint in xList:
        smallRangeIndex = getSmallRange(xList, xPoint)
        if smallRangeIndex != -1:
            newtonValList.append(getNewtonEquation(yList, smallRangeIndex, t, deltaMatrix))
    plt.plot(xList[:-1:], newtonValList, label="Newton interpolation function")

    return solution
    

print("Var: 11.")
print("Interpolation of function.")

print("Are you going to input data from file or using CLI?")
print("Enter '1' to use input from file, or '0' to use CLI")
fileOrCliInput = input().strip()
while fileOrCliInput != '0' and fileOrCliInput != '1':
    print("You entered not '1' and not '0' - try again")
    print("Enter '1' to use input from file, or '0' to use CLI")
    fileOrCliInput = input().strip()

print("Would you like to output results to file or to CLI?")
print("Enter '1' to output results to file, or '0' to output to CLI")
fileOrCliOutput = input().strip()
while fileOrCliOutput != '0' and fileOrCliOutput != '1':
    print("You entered not '1' and not '0' - try again")
    print("Enter '1' to output results to file, or '0' to output to CLI")
    fileOrCliOutput = input().strip()

if fileOrCliInput == "0":
    xList, yList = cliInput()
else:
    xList, yList = fileInput()

xPoint = getxPoint()

plt.plot(xList[:-1:], yList[:-1:], 'o', label="Interpolation nodes")

output = Output()
output.initFileOrCLIOutput(fileOrCliOutput)

# Многочлен Лагранжа
# Многочлен Ньютона с конечными разностями

langVal = getLangrValue(xList, yList, xPoint)
output.write(f"Calculated aproximate value using Lagranzh's method = {langVal}")

newtonVal = getNewtonMethodWithFiniteDiffs(xList, yList, xPoint)
output.write(f"")

plt.legend(loc="upper left")
plt.show()