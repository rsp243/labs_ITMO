import numpy as np
from sympy import Symbol, sympify, evalf
import matplotlib.pyplot as plt
import math

def isFloat(n):
    try:
        float(n)
    except ValueError:
        return False
    return True

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
        if fileOrCliOutput:
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

def cliInput():
    print("Enter function you would like to aproximate")

    func = input().strip()
    func = sympify(func)

    print("Enter x interval to aproximate function in format 'a b'. a and b included into interval")
    boundariesSplittedStr = input().strip().split(" ")
    checkSplittedStrOnCount(boundariesSplittedStr, 2)
    a = float(boundariesSplittedStr[0])
    b = float(boundariesSplittedStr[1])
    if a > b:
        a, b = b, a
    if a == b:
        print(f"You entered two equal boundaries")
        exit(-1)

    print("Enter h - step on the inverval. We will calculate f(x), where x = a + h * n (a + h * n <= b)")
    h = input().strip()

    isFloat(h)
    h = float(h)
    if h == 0:
        print(f"You entered step with length 0")
        exit(-1)

    return (func, a, b, h)

def fileInput():
    print("Enter filename for input")
    filename = input()
    try:
        f = open(f"{filename}", "r")
    except:
        print("There is no file with that name, or there is no permissions to read it")
        exit(1)

    func = f.readline().strip()
    func = sympify(func)

    boundariesSplittedStr = f.readline().strip().split(" ")
    checkSplittedStrOnCount(boundariesSplittedStr, 2)
    a = float(boundariesSplittedStr[0])
    b = float(boundariesSplittedStr[1])
    if a > b:
        a, b = b, a
    if a == b:
        print(f"You entered two equal boundaries")
        exit(-1)

    h = f.readline().strip()

    isFloat(h)
    h = float(h)
    if h == 0:
        print(f"You entered step with length 0")
        exit(-1)

    return (func, a, b, h)

def getxArray(a, b, h):
    n = int((b - a) / h) + 1
    xArray = [0 for _ in range(n)]
    i = 0
    while (i < n):
        xArray[i] = a + h * i
        i += 1
    
    return xArray

def getyArray(func, xArray):
    yArray = [0 for _ in range(len(xArray))]
    for i in range(len(xArray)):
        yArray[i] = func.subs({Symbol("x"): xArray[i], Symbol("e"): math.e})

    return yArray

def getArrayWithElemInDegree(xArray, degree):
    result = [0 for _ in range(len(xArray))]
    for i in range(len(xArray)):
        result[i] = xArray[i] ** degree
    
    return result

def getSXY(xArray, yArray):
    sum = 0
    for i in range(len(xArray)):
        sum += xArray[i] * yArray[i]

    return sum

def getEpsilonArray(yArray, yArrayP):
    n = len(yArray)
    epsilonArray = [0 for _ in range(n)]
    for i in range(n):
        epsilonArray[i] = yArray[i] - yArrayP[i]

    return epsilonArray

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
    
def isSomeElemIsNegative(xArray):
    for x in xArray:
        if x < 0:
            return 1
    return 0

def getLinearABCoeficients(xArray, yArray):
    output.write("Calculating linear coeficients")
    s1x = sum(xArray)
    s2x = sum(getArrayWithElemInDegree(xArray, 2))
    s1y = sum(yArray)
    s1x1y = getSXY(xArray, yArray)
    output.write(f"SX = sum(x_i) = {'%.4f' % s1x}")
    output.write(f"SXX = sum(x_i^2) = {'%.4f' % s2x}")
    output.write(f"SY = sum(y_i) = {'%.4f' % s1y}")
    output.write(f"SXY = sum(x_i * y_i) = {'%.4f' % s1x1y}")

    matrix = [[s2x, s1x], [s1x, n]]
    result = [s1x1y, s1y]

    matrixNP = np.array(matrix)
    resultNP = np.array(result)
    linSol = np.linalg.inv(matrixNP).dot(resultNP)

    return linSol

def getPirconsCoeficient(xArray, yArray):
    n = len(xArray)
    xAvg = sum(xArray) / len(xArray)
    yAvg = sum(yArray) / len(yArray)
    sum1 = 0
    sum2 = 0
    sum3 = 0
    for i in range(n):
        x_iMinusAvg = xArray[i] - xAvg
        y_iMinusAvg = yArray[i] - yAvg
        sum1 += x_iMinusAvg * y_iMinusAvg
        sum2 += x_iMinusAvg ** 2
        sum3 += y_iMinusAvg ** 2
    
    return sum1 / (sum2 * sum3) ** 0.5

def getLinearFunction(xArray, yArray):
    output.write("Calculating linear function")

    linSol = getLinearABCoeficients(xArray, yArray)
    funcP1 = sympify(f"{'%.4f' % linSol[0]} * x + {'%.4f' % linSol[1]}")
    output.write(f"P_1(x) = {funcP1}")
    yArrayP1 = getyArray(funcP1, xArray)
    epsilonArrayP1 = getEpsilonArray(yArray, yArrayP1)
    sP1 = sum(getArrayWithElemInDegree(epsilonArrayP1, 2))
    r = getPirconsCoeficient(xArray, yArray)
    output.write(f"Pirson's coefficient = {r}")
    output.write()
    printValuesTable(xArray, yArray, yArrayP1, epsilonArrayP1, sP1)

    return funcP1

def getSecondDegreePolinomialFunction(xArray, yArray):
    output.write("Calculating second degree polinomial function")
    s1x = sum(xArray)
    s2x = sum(getArrayWithElemInDegree(xArray, 2))
    s1y = sum(yArray)
    s1x1y = getSXY(xArray, yArray)
    s3x = sum(getArrayWithElemInDegree(xArray, 3))
    s4x = sum(getArrayWithElemInDegree(xArray, 4))
    s2x1y = getSXY(getArrayWithElemInDegree(xArray, 2), yArray)
    output.write(f"SX = sum(x_i) = {'%.4f' % s1x}")
    output.write(f"SXX = sum(x_i^2) = {'%.4f' % s2x}")
    output.write(f"SXXX = sum(x_i^3) = {'%.4f' % s3x}")
    output.write(f"SXXXX = sum(x_i^4) = {'%.4f' % s4x}")
    output.write(f"SY = sum(y_i) = {'%.4f' % s1y}")
    output.write(f"SXY = sum(x_i * y_i) = {'%.4f' % s1x1y}")
    output.write(f"SXXY = sum(x_i^2 * y_i) = {'%.4f' % s2x1y}")

    matrix = [[n, s1x, s2x], [s1x, s2x, s3x], [s2x, s3x, s4x]]
    result = [s1y, s1x1y, s2x1y]

    matrixNP = np.array(matrix)
    resultNP = np.array(result)
    polSquareSol = np.linalg.inv(matrixNP).dot(resultNP)
    funcP2 = sympify(f"{'%.4f' % polSquareSol[2]} * x^2 + {'%.4f' % polSquareSol[1]} * x + {'%.4f' % polSquareSol[0]}")
    output.write(f"P_2(x) = {funcP2}")
    yArrayP2 = getyArray(funcP2, xArray)
    epsilonArrayP2 = getEpsilonArray(yArray, yArrayP2)
    sP2 = sum(getArrayWithElemInDegree(epsilonArrayP2, 2))
    output.write()
    printValuesTable(xArray, yArray, yArrayP2, epsilonArrayP2, sP2)

    return funcP2

def getThirdDegreePolinomialFunction(xArray, yArray):
    output.write("Calculating third degree polinomial function")
    s1x = sum(xArray)
    s2x = sum(getArrayWithElemInDegree(xArray, 2))
    s1y = sum(yArray)
    s1x1y = getSXY(xArray, yArray)
    s3x = sum(getArrayWithElemInDegree(xArray, 3))
    s4x = sum(getArrayWithElemInDegree(xArray, 4))
    s2x1y = getSXY(getArrayWithElemInDegree(xArray, 2), yArray)
    s5x = sum(getArrayWithElemInDegree(xArray, 5))
    s6x = sum(getArrayWithElemInDegree(xArray, 6))
    s3x1y = getSXY(getArrayWithElemInDegree(xArray, 3), yArray)
    output.write(f"SX = sum(x_i) = {'%.4f' % s1x}")
    output.write(f"SXX = sum(x_i^2) = {'%.4f' % s2x}")
    output.write(f"SXXX = sum(x_i^3) = {'%.4f' % s3x}")
    output.write(f"SXXXX = sum(x_i^4) = {'%.4f' % s4x}")
    output.write(f"SXXXXX = sum(x_i^5) = {'%.4f' % s5x}")
    output.write(f"SXXXXXX = sum(x_i^6) = {'%.4f' % s6x}")
    output.write(f"SY = sum(y_i) = {'%.4f' % s1y}")
    output.write(f"SXY = sum(x_i * y_i) = {'%.4f' % s1x1y}")
    output.write(f"SXXY = sum(x_i^2 * y_i) = {'%.4f' % s2x1y}")
    output.write(f"SXXXY = sum(x_i^3 * y_i) = {'%.4f' % s3x1y}")

    matrix = [[n, s1x, s2x, s3x], [s1x, s2x, s3x, s4x], [s2x, s3x, s4x, s5x], [s3x, s4x, s5x, s6x]]
    result = [s1y, s1x1y, s2x1y, s3x1y]

    matrixNP = np.array(matrix)
    resultNP = np.array(result)
    thirdDegreePolSol = np.linalg.inv(matrixNP).dot(resultNP)
    funcP3 = sympify(f"{'%.4f' % thirdDegreePolSol[3]} * x^3 + {'%.4f' % thirdDegreePolSol[2]} * x^2 + {'%.4f' % thirdDegreePolSol[1]} * x + {'%.4f' % thirdDegreePolSol[0]}")
    output.write(f"P_3(x) = {funcP3}")
    yArrayP3 = getyArray(funcP3, xArray)
    epsilonArrayP3 = getEpsilonArray(yArray, yArrayP3)
    sP3 = sum(getArrayWithElemInDegree(epsilonArrayP3, 2))
    output.write()
    printValuesTable(xArray, yArray, yArrayP3, epsilonArrayP3, sP3)

    return funcP3

def getPowerFunction(xArray, yArray):
    output.write("Calculating power function")
    funcP4 = 0
    linSol = getLinearABCoeficients(xArray, yArray)
    if (not isSomeElemIsNegative(xArray) or not abs(linSol[1]) < 1):
        aPowerCoef = math.e ** linSol[0]
        funcP4 = sympify(f"{'%.4f' % aPowerCoef} * x ^ {'%.4f' % linSol[1]}")
        output.write(f"P_4(x) = {funcP4}")
        yArrayP4 = getyArray(funcP4, xArray)
        epsilonArrayP4 = getEpsilonArray(yArray, yArrayP4)
        sP4 = sum(getArrayWithElemInDegree(epsilonArrayP4, 2))
        output.write()
        printValuesTable(xArray, yArray, yArrayP4, epsilonArrayP4, sP4)
    else:
        output.write("Unable to aproximate function as power function, \
because it is restricted to power negative number (x) in power strictly less than 1 (if calculations are held in real number space)")
        output.write()
    
    return funcP4

def getExponentialFunction(xArray, yArray):
    output.write("Calculating exponential function")
    linSol = getLinearABCoeficients(xArray, yArray)
    aExpCoef = math.e ** linSol[0]
    funcP5 = sympify(f"{'%.4f' % aExpCoef} * e ^ ({'%.4f' % linSol[1]} * x)")
    output.write(f"P_5(x) = {funcP5}")
    yArrayP5 = getyArray(funcP5, xArray)
    epsilonArrayP5 = getEpsilonArray(yArray, yArrayP5)
    sP5 = sum(getArrayWithElemInDegree(epsilonArrayP5, 2))
    output.write()
    printValuesTable(xArray, yArray, yArrayP5, epsilonArrayP5, sP5)

    return funcP5

def getLogarithmFunction(xArray, yArray): 
    output.write("Calculating logarithm function")
    funcP6 = 0
    linSol = getLinearABCoeficients(xArray, yArray)

    if (not(isSomeElemIsNegative(xArray) or xArray.count(0) != 0)):
        aLogCoef = linSol[0]
        funcP6 = sympify(f"{'%.4f' % aLogCoef} * ln(x) + {'%.4f' % linSol[1]}")
        output.write(f"P_6(x) = {funcP6}")
        yArrayP6 = getyArray(funcP6, xArray)
        epsilonArrayP6 = getEpsilonArray(yArray, yArrayP6)
        sP6 = sum(getArrayWithElemInDegree(epsilonArrayP6, 2))
        output.write()
        printValuesTable(xArray, yArray, yArrayP6, epsilonArrayP6, sP6)
    else:
        output.write("Unable to aproximate function as logarithm function, \
because it is restricted to take logarithm of negative number or 0 (x) (if calculations are held in real number space)")
        output.write()

    return funcP6

print("Var: 11.")
print("Aproximation of function.")

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
    func, a, b, h = cliInput()
else:
    func, a, b, h = fileInput()

output = Output()
output.initFileOrCLIOutput(output)

n = int((b - a) / h) + 1
xArray = getxArray(a, b, h)
yArray = getyArray(func, xArray)

printValuesTable(xArray, yArray)

plt.plot(xArray, yArray, label=str(func))

# linear function
funcP1 = getLinearFunction(xArray, yArray)
yArrayP1 = getyArray(funcP1, xArray)
epsilonP1 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP1), 2))
plt.plot(xArray, yArrayP1, label=str(funcP1))

# second degree polinomial function
funcP2 = getSecondDegreePolinomialFunction(xArray, yArray)
yArrayP2 = getyArray(funcP2, xArray)
epsilonP2 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP2), 2))
plt.plot(xArray, yArrayP2, label=str(funcP2))

# third degree polinomial function
funcP3 = getThirdDegreePolinomialFunction(xArray, yArray)
yArrayP3 = getyArray(funcP3, xArray)
epsilonP3 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP3), 2))
plt.plot(xArray, yArrayP3, label=str(funcP3))

# power function
funcP4 = getPowerFunction(xArray, yArray)
yArrayP4 = []
epsilonP4 = 999999
if funcP4:
    yArrayP4 = getyArray(funcP4, xArray)
    epsilonP4 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP4), 2))
    plt.plot(xArray, yArrayP4, label=str(funcP4))

# exp function
funcP5 = getExponentialFunction(xArray, yArray)
yArrayP5 = getyArray(funcP5, xArray)
epsilonP5 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP5), 2))
plt.plot(xArray, yArrayP5, label=str(funcP5))

# log function
funcP6 = getLogarithmFunction(xArray, yArray)
yArrayP6 = []
epsilonP6 = 999999
if funcP6:
    yArrayP6 = getyArray(funcP6, xArray)
    epsilonP6 = sum(getArrayWithElemInDegree(getEpsilonArray(yArray, yArrayP6), 2))
    plt.plot(xArray, yArrayP6, label=
             str(funcP6))
epsilonList = [epsilonP1, epsilonP2, epsilonP3, epsilonP4, epsilonP5, epsilonP6]
funcList = [funcP1, funcP2, funcP3, funcP4, funcP5, funcP6]
output.write(f"Minimum epsilon among all aproximate functions epsilons = {min(epsilonList)}")
output.write(f"The most similar aproximate function = {funcList[epsilonList.index(min(epsilonList))]}")

output.closeFile()
plt.legend(loc="upper left")
plt.show()
