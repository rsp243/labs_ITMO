import json
import numpy as np
from sympy import Symbol, sympify
from scipy import integrate 

def checkInt(n):
    if not n.isdigit():
        print("Entered value is not an integer")
        exit(-1)

def isFloat(n):
    try:
        float(n)
    except ValueError:
        return False
    return True

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

    print("Choose one function you would like to integrate")
    for i in range(len(availibleFunc)):
        print(f"{i + 1}. {availibleFunc[i]}")
    num = input().strip()
    while not num.isdigit() and num not in [x + 1 for x in range(len(availibleFunc))]:
        print("You entered not integer or there is not function labeled with that integer - try again")
        print("Choose one function you would like to integrate")
        for i in range(len(availibleFunc)):
            print(f"{i + 1}. {availibleFunc[i]}")
        num = input().strip()

    return sympify(availibleFunc[int(num) - 1])

def getBoundariesN():
    print("Enter boundaries in format 'a b'")
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

    print("Enter accuracy of calculations")
    cin = input().strip()
    isFloat(cin)
    acc = float(cin)
    if acc <= 0:
        print(f"Accurancy cannot be 0 or less than <")
        exit(-1)

    print("Enter count of interval partion")
    cin = input().strip()
    checkInt(cin)
    n = int(cin)
    if n <= 0:
        print(f"count of interval partion cannot be 0 or less than 0")
        exit(-1)

    return (a, b, n, acc)

def integrateSquare(func, a, b, n, squaresType = False):
    if not squaresType:    
        print("What type of square's method you would like to integrate of chosen function?")
        print("Enter 'l' to use left-square's method, 'r' to use right-square's method, \
    or 'm' to use middle-square's method")
        squaresType = input().strip()
        while squaresType != 'l' and squaresType != 'r' and squaresType != 'm':
            print("You entered not 'p', not 't', and not 's' - try again")
            print("Enter 'l' to use left-square's method, 'r' to use right-square's method, \
    or 'm' to use middle-square's method")
            squaresType = input().strip()
    print()
    print("Execution:")
    h = (b - a) / n
    print(f"h = {h}")
    x = np.arange(a, b + h, h)
    print("Summing up below values ->")
    sum = 0
    if squaresType == "l":
        print("0 <= i <= n - 1")
        for i in range(n):
            fValI = func.subs({'x': x[i]})
            sum += fValI
            print(f"f(x_{i}) = f({'%7.4f' % x[i]}) = {'%7.4f' % fValI}")
    elif squaresType == "r":
        print("1 <= i <= n")
        for i in range(1, n + 1):
            fValI = func.subs({'x': x[i - 1]}) 
            sum += fValI
            print(f"f(x_{i}) = f({'%7.4f' % x[i - 1]}) = {'%7.4f' % fValI}")
    else:
        print("1 <= i <= n")
        for i in range(1, n + 1):
            fValI = func.subs({'x': (x[i - 1] + x[i]) / 2})
            sum += fValI
            print(f"f(x_({i}-1/2)) = f({'%7.4f' % ((x[i - 1] + x[i]) / 2)}) = {'%7.4f' % fValI}")
    return (sum * h, squaresType)

def integrateTrapetion(func, a, b, n):
    print()
    print("Execution:")
    h = (b - a) / n
    print(f"h = {h}")
    x = np.arange(a, b + h, h)
    sum = 0
    print("Summing up below values ->")
    print("1 <= i <= n - 1")
    for i in range(1, n):
        fValI = func.subs({'x': x[i]})
        sum += fValI
        print(f"f(x_{i}) = f({'%7.4f' % x[i]}) = {'%7.4f' % fValI}")
    sum += (func.subs({'x': x[0]}) + func.subs({'x': x[n]})) / 2
    print(f"(f(x_0) + f(x_n)) / 2 = {'%7.4f' % ((func.subs({'x': x[0]}) + func.subs({'x': x[n]})) / 2)}")

    return sum * h

def integrateSimpson(func, a, b, n):
    print()
    print("Execution:")
    h = (b - a) / n
    print(f"h = {h}")
    x = np.arange(a, b + h, h)
    sum = func.subs({'x': x[0]})
    print("Summing up below values ->")
    print(f"f(x_0) = f({'%7.4f' % x[0]}) = {'%7.4f' % sum}")
    print("1 <= i <= n - 1")
    for i in range(1, n):
        fValI = func.subs({'x': x[i]})
        if i % 2:
            sum += 4 * fValI
            print(f"4 * f(x_{i}) = 4 * f({'%7.4f' % x[i]}) = {'%7.4f' % (4 * fValI)}")
        else:
            sum += 2 * fValI
            print(f"2 * f(x_{i}) = 2 * f({'%7.4f' % x[i]}) = {'%7.4f' % (2 * fValI)}")
    sum += func.subs({'x': x[n]})
    print(f"f(x_0) = f({'%7.4f' % x[n]}) = {'%7.4f' % func.subs({'x': x[n]})}")

    return sum * h / 3

def getAccurateResult(func, a, b):
    result, _ = integrate.quad(lambda x: eval(str(func), {'x': x}), a, b)

    return result

def getSecondDerivative(func):
    x = Symbol('x')
    fFirst = func.diff(x)
    return fFirst.diff(x)


print("Var: 11.")
print("Integration of functions.")

func = getFunc()
a, b, n, acc = getBoundariesN()

print("What method you would like to integrate of chosen function?")
print("Enter 'p' to use square's method, 't' to use trapetion's method, or 's' to use Simpson's method")
integrationMethod = input().strip()
while integrationMethod != 'p' and integrationMethod != 't' and integrationMethod != 's':
    print("You entered not 'p', not 't', and not 's' - try again")
    print("Enter 'p' to use square's method, 't' to use trapetion's method, or 's' to use Simpson's method")
    integrationMethod = input().strip()

if integrationMethod == 'p':
    result, squaresType = integrateSquare(func, a, b, n)
elif integrationMethod == 't':
    result = integrateTrapetion(func, a, b, n)
else:
    result = integrateSimpson(func, a, b, n)
print()
print(f"Result of integration of chosen function using chosen integration method = {'%7.4f' % result}")

accurateResult = getAccurateResult(func, a, b)
print(f"Accurate result = {accurateResult}")

relativeError = abs(result - accurateResult) / accurateResult * 100 
print(f"Relative error = {relativeError}")

k = 2
if integrationMethod == 'p':
    resultRun, _ = integrateSquare(func, a, b, n * 2, squaresType)
elif integrationMethod == 't':
    resultRun = integrateTrapetion(func, a, b, n * 2)
else:
    k = 4
    resultRun = integrateSimpson(func, a, b, n * 4)
print(f"Result of integration of chosen function using chosen integration method and \
Runge rule for practical assessment of error = {'%7.4f' % resultRun}")
print(f"Measurement error = {'%7.4f' % ((resultRun - result) / (2 ** k - 1))}")

print()
print(f"Executing integration using entered accuration of calculations")
if integrationMethod == 'p':
    fSec = getSecondDerivative(func)
    fSecValList = [fSec.subs({'x': value}) for value in np.arange(a, b + 1, 1)]
    maxValue = max(fSecValList)
    nNew = int(np.ceil(np.sqrt(float(maxValue * (b - a) ** 3 / (24 * acc)))))
    print(f"Derivative of function = {fSec}")
    print(f"Max value of function in range ({a}, {b}) = {maxValue}")
    print(f"New n = {nNew}")
    result, _ = integrateSquare(func, a, b, nNew, squaresType)
elif integrationMethod == 't':
    fSec = getSecondDerivative(func)
    fSecValList = [fSec.subs({'x': value}) for value in np.arange(a, b + 1, 1)]
    maxValue = max(fSecValList)
    nNew = int(np.ceil(np.sqrt(float(maxValue * (b - a) ** 3 / (12 * acc)))))
    print(f"Derivative of function = {fSec}")
    print(f"Max value of function in range ({a}, {b}) = {maxValue}")
    print(f"New n = {nNew}")
    result = integrateTrapetion(func, a, b, nNew)
else:
    fSec = getSecondDerivative(func)
    fForth = getSecondDerivative(fSec)
    fSecValList = [fSec.subs({'x': value}) for value in np.arange(a, b + 1, 1)]
    maxValue = max(fSecValList)
    nNew = int(np.ceil(np.sqrt(np.sqrt(float(maxValue * (b - a) ** 5 / (180 * acc))))))
    print(f"Derivative of function = {fSec}")
    print(f"Max value of function in range ({a}, {b}) = {maxValue}")
    print(f"New n = {nNew}")
    result = integrateSimpson(func, a, b, nNew)
print(f"Result = {result}")
errorApproximate = abs(result - accurateResult)
print(f"|R| = |I - Iac| = {errorApproximate}")
print(f"Is accuracy was reached? {'YES' if errorApproximate < acc else 'NO'}")
