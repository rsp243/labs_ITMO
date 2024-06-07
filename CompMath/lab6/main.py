import json
import numpy as np
from sympy import Symbol, Function, Derivative, Eq, sympify, solve, diff, dsolve
import matplotlib.pyplot as plt
import math

class ArrayWithName:
    name = ""
    array = []

    def __init__(self, name, array) -> None:
        self.name = name
        self.array = array

def isFloat(n):
    try:
        float(n)
    except ValueError:
        return False
    return True

def checkFloat(n):
    if not isFloat(n):
        print("Entered value is not a float")
        exit(-1)

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

    print("Choose one differential equation you would like to solve")
    for i in range(len(availibleFunc)):
        print(f"{i + 1}. y' = {availibleFunc[i]}")
    num = input().strip()
    while not num.isdigit() and num not in [x + 1 for x in range(len(availibleFunc))]:
        print("You entered not integer or there is not function labeled with that integer - try again")
        print("Choose one differential equation you would like to solve")
        for i in range(len(availibleFunc)):
            print(f"{i + 1}. {availibleFunc[i]}")
        num = input().strip()

    return sympify(availibleFunc[int(num) - 1])

def getBoundariesN():
    print("Enter boundaries of interval in format 'x_0 x_n'")
    cin = input().strip().split(" ")
    checkSplittedStrOnCount(cin, 2)
    for i in range(len(cin)):
        checkFloat(cin[i])
        cin[i] = float(cin[i])
    a, b = cin
    if abs(a) > abs(b):
        a, b = b, a
    if a == b:
        print(f"You entered two equal boundaries")
        exit(-1)

    print("Enter starting Y value = y(x_0):")
    
    cin = input().strip()
    checkFloat(cin)
    startY = float(cin)
    
    print("Enter accuracy of calculations")
    cin = input().strip()
    checkFloat(cin)
    acc = float(cin)
    if acc <= 0:
        print(f"Accurancy cannot be 0 or less than <")
        exit(-1)

    print("Enter step of interval partion")
    cin = input().strip()
    checkFloat(cin)
    h = float(cin)
    if h <= 0:
        print(f"Step of interval partion cannot be 0 or less than 0")
        exit(-1)

    return (a, b, startY, h, acc)

def getSymbolsArr(funcArr):
    return sorted(list(set(str(j) for i in range(len(funcArr)) for j in funcArr[i].free_symbols)))

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

def getYListEqlerModMethod(func, xList, startY, h):
    yList = [startY]
    for i in range(1, len(xList)):
        fVal = func.subs({Symbol("x"): xList[i - 1], Symbol("y"): yList[i - 1]})
        fValNext = func.subs({Symbol("x"): xList[i], Symbol("y"): yList[i - 1] + h * fVal})
        yNext = yList[i - 1] + h / 2 * (fVal + fValNext)
        yList.append(yNext)
    
    return yList

def getAccYListEqlerModMethod(func, xList, startY, h, epsilon):
    output.write("Calculations with Modificated Eqler method")
    p = 2
    yList = getYListEqlerModMethod(func, xList, startY, h)
    xListHnew = getxArray(xList[0], xList[-1], h / 2)
    yListHnew = getYListEqlerModMethod(func, xListHnew, startY, h / 2)
    r = abs(yList[-1] - yListHnew[-1]) / (2 ** p - 1)
    while r > epsilon:
        h = h / 2
        xList = getxArray(xList[0], xList[-1], h)
        yList = getYListEqlerModMethod(func, xListHnew, startY, h)
        xListHnew = getxArray(xList[0], xList[-1], h / 2)
        yListHnew = getYListEqlerModMethod(func, xList, startY, h / 2)
        r = abs(yList[-1] - yListHnew[-1]) / 2 ** p - 1
    
    output.write("%10s %10s %10s" % ("i", "x_i", "y_i"))
    for i in range(len(xList)):
        output.write("%10d %10f %10f" % (i, round(xList[i], 5), round(yList[i], 5)))
    output.write(f"Result was taken with error R = {r}, and end step was h = {h}")

    return yList

def getYListRungeKettaMethod(func, xList, startY, h):
    yList = [startY]
    for i in range(1, len(xList)):
        fValk1 = func.subs({Symbol("x"): xList[i - 1], Symbol("y"): yList[i - 1]})
        k1 = h * fValk1
        fValk2 = func.subs({Symbol("x"): xList[i - 1] + h / 2, Symbol("y"): yList[i - 1] + k1 / 2})
        k2 = h * fValk2
        fValk3 = func.subs({Symbol("x"): xList[i - 1] + h / 2, Symbol("y"): yList[i - 1] + k2 / 2})
        k3 = h * fValk3
        fValk4 = func.subs({Symbol("x"): xList[i - 1] + h, Symbol("y"): yList[i - 1] + k3})
        k4 = h * fValk4
        yList.append(yList[i - 1] + 1 / 6 * (k1 + 2 * k2 + 2 * k3 + k4))
    
    return yList

def getAccYListRungeKettaMethod(func, xList, startY, h, epsilon):
    output.write("Calculations with Runge-Ketta method")
    p = 4
    yList = getYListRungeKettaMethod(func, xList, startY, h)
    xListHnew = getxArray(xList[0], xList[-1], h / 2)
    yListHnew = getYListRungeKettaMethod(func, xListHnew, startY, h / 2)
    r = abs(yList[-1] - yListHnew[-1]) / (2 ** p - 1)
    while r > epsilon:
        h = h / 2
        xList = getxArray(xList[0], xList[-1], h)
        yList = getYListRungeKettaMethod(func, xListHnew, startY, h)
        xListHnew = getxArray(xList[0], xList[-1], h / 2)
        yListHnew = getYListRungeKettaMethod(func, xList, startY, h / 2)
        r = abs(yList[-1] - yListHnew[-1]) / 2 ** p - 1

    output.write("%10s %10s %10s" % ("i", "x_i", "y_i"))
    for i in range(len(xList)):
        output.write("%10d %10f %10f" % (i, round(xList[i], 5), round(yList[i], 5)))
    output.write(f"Result was taken with error R = {r}, and end step was h = {h}")
    
    return yList

def getFuncValue(func, xList, yList):
    result = list()
    for i in range(len(xList)):
        result.append(func.subs({Symbol("x"): xList[i], Symbol("y"): yList[i]}))
    
    return result

def getYListAdamsMethod(func, xList, startY, h):
    output.write("Calculations with method Adams")
    yListAdams = getYListRungeKettaMethod(func, xList[:4], startY, h)
    fValArray = getFuncValue(func, xList[:4], yListAdams)
    for i in range(4, len(xList)):
        delta_0f_i = fValArray[i - 1]
        delta_1f_i = fValArray[i - 1] - fValArray[i - 2]
        delta_2f_i = fValArray[i - 1] - 2 * fValArray[i - 2] + fValArray[i - 3]
        delta_3f_i = fValArray[i - 1] - 3 * fValArray[i - 2] + 3 * fValArray[i - 3] - fValArray[i - 4]
        yListAdams.append(yListAdams[i - 1] +
                          h * delta_0f_i +
                          h ** 2 / 2 * delta_1f_i +
                          5 * h ** 3 / 12 * delta_2f_i +
                          3 * h ** 4 / 8 * delta_3f_i)
        fValArray.append(func.subs({Symbol("x"): xList[i], Symbol("y"): yListAdams[i]}))

    output.write("%10s %10s %10s" % ("i", "x_i", "y_i"))
    for i in range(len(xList)):
        output.write("%10d %10f %10f" % (i, round(xList[i], 5), round(yListAdams[i], 5)))
    
    return yListAdams

def getCorrectYList(func, xList, startY):
    output.write("Accurate calculation of solution of differential equation")
    x = Symbol('x')
    y = Function('y')
    equation = Eq(y(x).diff(x), sympify(str(func).replace("y", "y(x)")))
    solvedODE = dsolve(equation).rhs
    output.write(f"General solution - {solvedODE}")

    func = getFuncValue(solvedODE, xList[0:1], [startY])[0]
    symbolsArr = getSymbolsArr([func])
    cValSolution = solve(Eq(func, startY))
    symbolsDict = dict()
    for i in range(len(symbolsArr)):
        symbolsDict[symbolsArr[i]] = cValSolution[i]
    resultFunc = solvedODE.subs(symbolsDict)
    output.write(f"Private solution - {resultFunc}")

    yList = list()
    for i in range(len(xList)):
        yList.append(resultFunc.subs({x: xList[i]}))
    
    output.write("%10s %10s %10s" % ("i", "x_i", "y_i"))
    for i in range(len(xList)):
        output.write("%10d %10f %10f" % (i, round(xList[i], 5), round(yList[i], 5)))
    
    return yList

def getMultiStepEpsilon(yList, yCorrectList):
    max = 0
    for i in range(len(yList)):
        diff = abs(yCorrectList[i] - yList[i])
        if max < diff:
            max = diff
    
    return max


print("Var: 11.")
print("Koshi's task solving for differential equation.")

func = getFunc()
a, b, startY, h, epsilon = getBoundariesN()
    
output = Output()
output.initFileOrCLIOutput("CLI")

xList = getxArray(a, b, h)
yListCorrect = getCorrectYList(func, xList, startY)
plt.plot(xList, yListCorrect, label="Correct solution")
print(yListCorrect)

# Одношаговые - усовершенствованный метод Эйлера, метод Рунге-Кутта 4 порядка
# Многошаговые - метод Адамса

# one-step methods
yListEqlerMod = getAccYListEqlerModMethod(func, xList, startY, h, epsilon)
plt.plot(xList, yListEqlerMod, label="Eqler modificatied method")
# output.write(yListEqlerMod)

yListRungeKutta = getAccYListRungeKettaMethod(func, xList, startY, h, epsilon)
plt.plot(xList, yListRungeKutta, label="Runge-Ketta method")
# output.write(yListRungeKutta)

# multi-step method
yListAdams = getYListAdamsMethod(func, xList, startY, h)
plt.plot(xList, yListAdams, label="Adams method")
# output.write(yListAdams)
eps = getMultiStepEpsilon(yListAdams, yListCorrect)
output.write(f"{('Accurate result got using Adams method. Error = ' + str(eps)) if eps <= epsilon else ('Inaccurate result got using Adams method. Error = ' + str(eps))}")

plt.legend(loc="upper left")
plt.show()
