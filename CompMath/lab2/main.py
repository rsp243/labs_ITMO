import numpy as np
from sympy import Symbol, sympify, evalf, diff, solve, Max, lambdify
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
        if self.fileOrCliOutput:
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

class ArrayWithName:
    name = ""
    array = []

    def __init__(self, name, array) -> None:
        self.name = name
        self.array = array

def verifySolutionInInterval(func, a, b, symbol):
    f_a = func.subs({symbol: a})
    f_b = func.subs({symbol: b})
    if f_a * f_b > 0:
        print(f"There are no solutions in interval [{a}, {b}] for {func}")
        exit(1)
    
    diff_func = diff(func, symbol)
    diff_func_a = diff_func.subs({symbol: a})
    diff_func_b = diff_func.subs({symbol: b})
    if diff_func_a * diff_func_b < 0:
        print(f"There are more than one solution in interval [{a}, {b}] for {func}")
        exit(1)

def getxArray(a, b, h):
    n = int((b - a) / h) + 1
    xArray = [0 for _ in range(n)]
    i = 0
    while (i < n):
        xArray[i] = a + h * i
        i += 1
    
    return xArray

def getyArray(func, xArray, symbol):
    yArray = [0 for _ in range(len(xArray))]
    for i in range(len(xArray)):
        yArray[i] = func.subs({symbol: xArray[i], Symbol("e"): math.e})

    return yArray

def checkSplittedStrOnCount(splittedStr, n):
    if len(splittedStr) != n:
        print(f"You entered incorrect count of values in matrix row")
        exit(-1)

def makeArrFloat(boundariesSplittedStr):
    result = []
    for i in boundariesSplittedStr:
        if isFloat(i):
            result.append(float(i))
        else:
            print("Start values are not float")
            exit(1)
    
    return result

def cliInput():
    print("Enter function, equation or equations (for system of equations)")

    func = input().strip()
    parts = input().strip().split("=")
    if len(parts) > 2:
        print("You've entered wrong equations. There is equation with 2 signs '='")
        exit(1)
    if len(parts) > 1:
        funcStr = str(parts[0]) + " - (" + str(parts[1]) + ")"
    else:
        funcStr = func.strip()
    func = sympify(funcStr)
    funcArr = [func]
    
    systemOrOneEquation = ''
    while (systemOrOneEquation != '0'):
        print("Continue entering functions for system of equations?")    
        print("Enter '1' to enter more functions, or '0' to continue entered function(-s)")
        systemOrOneEquation = input().strip()
        while systemOrOneEquation != '0' and systemOrOneEquation != '1':
            print("You entered not '1' and not '0' - try again")
            print("Enter '1' to enter more functions, or '0' to continue entered function(-s)")
            systemOrOneEquation = input().strip()
        print("Enter function or equation")
        func = input().strip()
        parts = input().strip().split("=")
        if len(parts) > 2:
            print("You've entered wrong equations. There is equation with 2 signs '='")
            exit(1)
        if len(parts) > 1:
            funcStr = str(parts[0]) + " - (" + str(parts[1]) + ")"
        else:
            funcStr = func.strip()
        func = sympify(funcStr)
        funcArr.append(func)

    print("Enter x interval of function in format 'a b'. a and b included into interval")
    boundariesSplittedStr = input().strip().split(" ")
    checkSplittedStrOnCount(boundariesSplittedStr, 2)
    a = float(boundariesSplittedStr[0])
    b = float(boundariesSplittedStr[1])
    if a > b:
        a, b = b, a
    if a == b:
        print(f"You entered two equal boundaries")
        exit(-1)

    print("Enter e - epsilon, accuracy of function value in solution point")
    e = input().strip()

    isFloat(e)
    e = float(e)
    
    return (funcArr, a, b, e)

def fileInput():
    print("Enter filename for input")
    filename = input()
    try:
        f = open(f"{filename}", "r")
    except:
        print("There is no file with that name, or there is no permissions to read it")
        exit(1)

    lines = f.readlines()
    funcArr = []
    i = 0
    while i < len(lines) - 2:
        parts = lines[i].strip().split("=")
        if len(parts) > 2:
            print("You've entered wrong equations. There is equation with 2 signs '='")
            exit(1)
        if len(parts) > 1:
            funcStr = str(parts[0]) + " - (" + str(parts[1]) + ")"
        else:
            funcStr = lines[i].strip()
        func = sympify(funcStr)
        funcArr.append(func)
        
        i += 1
            
    boundariesSplittedStr = lines[i].strip().split(" ")
    if len(funcArr) < 2:
        checkSplittedStrOnCount(boundariesSplittedStr, 2)
        a = float(boundariesSplittedStr[0])
        b = float(boundariesSplittedStr[1])
        if a > b:
            a, b = b, a
        if a == b:
            print(f"You entered two equal boundaries")
            exit(-1)
    else:
        checkSplittedStrOnCount(boundariesSplittedStr, len(funcArr))
        startValuesList = makeArrFloat(boundariesSplittedStr)

    e = lines[i + 1].strip()
    isFloat(e)
    e = float(e)
    if len(funcArr) < 2:
        return (funcArr, [a, b], e)
    return (funcArr, startValuesList, e)

def printArrInColumns(*args):
    output.write("%16s" % "Iteration", end = " ")
    for i in range(len(args)):
        output.write("%16s" % args[i].name, end = " ")
    output.write()

    n = len(args[0].array)
    for j in range(n):
        output.write("%16s" % j, end = " ")
        for i in range(len(args)):
            output.write("%16.4f" % args[i].array[j], end=" ")
        output.write()
    output.write()

def getXHalfDevisionMethod(func, a, b, e):
    output.write("Calculating with half devision method")
    n = int(np.log2(abs(a - b) / e)) + 1
    output.write(f"Count of iterations = {n}")
    
    beginning = a
    end = b
    x_i = (beginning + end) / 2
    
    # output arrays initialization
    aArr = ArrayWithName("a", [])
    bArr = ArrayWithName("b", [])
    xArr = ArrayWithName("x", [])
    fBegArr = ArrayWithName("f(a)", [])
    fEndArr = ArrayWithName("f(b)", [])
    fXArr = ArrayWithName("f(x)", [])
    diffArr = ArrayWithName("|a - b|", [])

    while (abs(func.subs({Symbol("x"): x_i})) > e and abs(beginning - end) > e):
        x_i = (beginning + end) / 2
        fBegVal = func.subs({Symbol("x"): beginning})
        fEndVal = func.subs({Symbol("x"): end})
        fXVal = func.subs({Symbol("x"): x_i})
        
        # output arrays filling
        aArr.array.append(beginning)
        bArr.array.append(end)
        xArr.array.append(x_i)
        fBegArr.array.append(fBegVal)
        fEndArr.array.append(fEndVal)
        fXArr.array.append(fXVal)
        diffArr.array.append(abs(beginning - end))
        
        if np.sign(fBegVal) != np.sign(fXVal):
            end = x_i
        else:
            beginning = x_i

    printArrInColumns(aArr, bArr, xArr, fBegArr, fEndArr, fXArr, diffArr)
    return (beginning + end) / 2

def getStartingPoints(func, a, b):
    if func.subs({Symbol("x"): a}) * diff(diff(func)).subs({Symbol("x"): a}) > 0:
        x_0 = a
        x_1 = x_0 + abs(a - b) / 3
    else:
        x_0 = b
        x_1 = x_0 - abs(b - a) / 3
        
    return [x_0, x_1]    

def getXSecantMethod(func, a, b, e):
    output.write("Calculating with secant method")
    xArr = getStartingPoints(func, a, b)

    # output arrays initialization
    xiPrevArr = ArrayWithName("x_(i-1)", [])
    xiArr = ArrayWithName("x_i", [])
    xiNextArr = ArrayWithName("x_(i+1)", [])
    fNextArr = ArrayWithName("f(x_(i+1))", [])
    diffArr = ArrayWithName("|x_(i+1) - x_i|", [])
    
    i = 1
    while abs(xArr[i] - xArr[i - 1]) > e and abs(func.subs({Symbol("x"): xArr[i]})) > e:
        f_x_i = func.subs({Symbol("x"): xArr[i]})
        xArr.append(xArr[i] - (xArr[i] - xArr[i - 1]) * f_x_i / (f_x_i - func.subs({Symbol("x"): xArr[i - 1]})))

        # output arrays filling
        xiPrevArr.array.append(xArr[i - 1])
        xiArr.array.append(xArr[i])
        xiNextArr.array.append(xArr[i + 1])
        fNextArr.array.append(func.subs({Symbol("x"): xArr[i + 1]}))
        diffArr.array.append(abs(xArr[i + 1] - xArr[i]))

        i += 1
    printArrInColumns(xiPrevArr, xiArr, xiNextArr, fNextArr, diffArr)
    
    return xArr[i]

def getHordMethod(func, a, b, e):
    output.write("Calculating with hord method")
    # n = int(np.log2(abs(a - b) / e)) + 1
    # output.write(f"Count of iterations = {n}")

    # output arrays initialization
    aArr = ArrayWithName("a", [])
    bArr = ArrayWithName("b", [])
    xArr = ArrayWithName("x", [])
    fBegArr = ArrayWithName("f(a)", [])
    fEndArr = ArrayWithName("f(b)", [])
    fXArr = ArrayWithName("f(x)", [])
    diffArr = ArrayWithName("|x_n - x_(n-1)|", [])
    
    beginning = a
    end = b
    fBegVal = func.subs({Symbol("x"): beginning})
    fEndVal = func.subs({Symbol("x"): end})
    x_i = beginning - (end - beginning) / (fEndVal - fBegVal) * fBegVal
    fXVal = func.subs({Symbol("x"): x_i})
    
    aArr.array.append(beginning)
    bArr.array.append(end)
    xArr.array.append(x_i)
    fBegArr.array.append(fBegVal)
    fEndArr.array.append(fEndVal)
    fXArr.array.append(fXVal)
    diffArr.array.append(x_i - a)
            
    if np.sign(fBegVal) != np.sign(fXVal):
        end = x_i
    else:
        beginning = x_i

    while (abs(func.subs({Symbol("x"): x_i})) > e and abs(diffArr.array[len(diffArr.array) - 1]) > e and abs(fBegVal - fEndVal) > e):
        fBegVal = func.subs({Symbol("x"): beginning})
        fEndVal = func.subs({Symbol("x"): end})
        x_i = (beginning * fEndVal - end * fBegVal) / (fEndVal - fBegVal)
        fXVal = func.subs({Symbol("x"): x_i})
        
        # output arrays filling
        aArr.array.append(beginning)
        bArr.array.append(end)
        xArr.array.append(x_i)
        fBegArr.array.append(fBegVal)
        fEndArr.array.append(fEndVal)
        fXArr.array.append(fXVal)
        diffArr.array.append(x_i - xArr.array[len(xArr.array) - 2])
        
        if np.sign(fBegVal) != np.sign(fXVal):
            end = x_i
        else:
            beginning = x_i

    printArrInColumns(aArr, bArr, xArr, fBegArr, fEndArr, fXArr, diffArr)
    return xArr.array[len(xArr.array) - 1]

def getPhiFunc(func, a, b):
    func_hatch = diff(func) # differential of func
    lam = - 1 / (Max(abs(func_hatch.subs({Symbol("x"): a})), abs(func_hatch.subs({Symbol("x"): a}))))
    phi = sympify(f"x + {lam} * ({func})")
        
    return phi

def getXSimpleIterationMethod(func, a, b, e):
    output.write("Calculating with method of simple iterations")
    phi = getPhiFunc(func, a, b)
    output.write(f"Found φ function: φ = {phi}")
    x_0 = getStartingPoints(func, a, b)[0]
    q = min(abs(diff(phi).subs({Symbol("x"): a})), abs(diff(phi).subs({Symbol("x"): b})))
    if q >= 1 or q < 0:
        output.write("Iterational sequence of method of simple iterations {x_n} does not converge to the root of the equation.")
        return "None"
    xArr = [x_0]
    xArr.append(phi.subs({Symbol("x"): x_0}))
    i = 0

    # output arrays initialization
    xiArr = ArrayWithName("x_i", [xArr[i]])
    xiNextArr = ArrayWithName("x_(i+1)", [xArr[i + 1]])
    phiNextArr = ArrayWithName("φ(x_(i+1))", [phi.subs({Symbol("x"): xArr[i + 1]})])
    fNextArr = ArrayWithName("f(x_(i+1))", [func.subs({Symbol("x"): xArr[i + 1]})])
    diffArr = ArrayWithName("|x_(i+1) - x_i|", [abs(xArr[i] - xArr[i + 1])])

    i += 1
    while (abs(xArr[i] - xArr[i - 1]) > e):
        xArr.append(phi.subs({Symbol("x"): xArr[i]}))
        
        # output arrays filling
        xiArr.array.append(xArr[i])
        xiNextArr.array.append(xArr[i + 1])
        phiNextArr.array.append(phi.subs({Symbol("x"): xArr[i + 1]}))
        fNextArr.array.append(func.subs({Symbol("x"): xArr[i + 1]}))
        diffArr.array.append(abs(xArr[i] - xArr[i + 1]))
        
        i += 1
    printArrInColumns(xiArr, xiNextArr, phiNextArr, fNextArr, diffArr)
    
    return xArr[i]

def getSymbolsArr(funcArr):
    return sorted(list(set(str(j) for i in range(len(funcArr)) for j in funcArr[i].free_symbols)))

def outputSystemOfEquations(matrix, result, symbolsArr):
    output.write("System of equations to solve:")
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            output.write(matrix[i][j], end=f" * delta_{symbolsArr[j]} ")
        output.write(f"= {result[i]}")

def outputFoundApproximations(approximation, symbolsArr):
    output.write("Solution of system of equations:")
    for i in range(len(symbolsArr)):
        output.write(f"delta_{symbolsArr[i]} = {approximation[i]}")

def outputArgsValWithName(symbolsArr, argsVal):
    output.write("New values of variables:")
    result = dict()
    for i in range(len(symbolsArr)):
        result[symbolsArr[i]] = argsVal[i]
        output.write(f"{symbolsArr[i]} = {argsVal[i]}")
    
    return result
        
def getSystemOfEquation(funcArr, symbolsArr, args):
    '''
    function create matrix of left part of system of equation of Newton's method
    calculate differentials of each function in each symbol in entered equations
    result matrix have to be multiplied by matrix of (delta_{SYMBOL_1}, delta_{SYMBOL_2}, ...)^T to be equal matrix of passed functions
    '''
    
    matrix = [[0 for _ in range(len(symbolsArr))] for _ in range(len(funcArr))]
    for symIndex in range(len(symbolsArr)):
        for funcIndex in range(len(funcArr)):
            # print(diff(funcArr[funcIndex], symbolsArr[symIndex]))
            matrix[funcIndex][symIndex] = sympify(diff(funcArr[funcIndex], symbolsArr[symIndex]))
            for i in range(len(symbolsArr)):
                matrix[funcIndex][symIndex] = matrix[funcIndex][symIndex].subs({symbolsArr[i]: args[i]})

    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            matrix[i][j] = float(matrix[i][j])

    return matrix

def getXNewtonMethodSystem(funcArr, e, symbolsArr, args):
    argsNext = args
    matrix = getSystemOfEquation(funcArr, symbolsArr, args)
    isSolutionFound = False

    iterCounter = 0
    while not isSolutionFound:
        output.write(f"Iteration {iterCounter}")
        result = []
        args = argsNext
        for funcIndex in range(len(funcArr)):
            result.append(-1 * (funcArr[funcIndex]))
            for i in range(len(symbolsArr)):
                result[funcIndex] = result[funcIndex].subs({Symbol(symbolsArr[i]): args[i]})
                # print(result[funcIndex])

        for i in range(len(result)):
            result[i] = float(result[i])

        outputSystemOfEquations(matrix, result, symbolsArr)
        approximation = np.linalg.solve(matrix, result)
        outputFoundApproximations(approximation, symbolsArr)
        for i in range(len(argsNext)):
            argsNext += approximation
            if abs(argsNext[i] - args[i]) <= e:
                isSolutionFound = True
        
        outputArgsValWithName(symbolsArr, argsNext)
        output.write()
        iterCounter += 1
    output.write(f"Solution found with {iterCounter} iterations")
    
    return outputArgsValWithName(symbolsArr, argsNext) 

def drawAFuncInInterval(func, symbolsArr, xStart, yStart):    
    Z_func = lambdify((symbolsArr[0], symbolsArr[1]), func, modules='numpy')

    xLinSpace = np.linspace(xStart - 3, xStart + 3, 100)
    yLinSpace = np.linspace(yStart - 3, yStart + 3, 100)
    x, y = np.meshgrid(xLinSpace, yLinSpace)    

    plt.contour(x, y, Z_func(x, y), levels=[0])
    plt.gca().set_aspect('equal', adjustable='box')
    
def getSolutionOfOneEquation(func, a, b, e):
    symbolsArr = getSymbolsArr([func])
    verifySolutionInInterval(func, a, b, symbolsArr[0])
    
    output.write(f"Function crosses y=0 in x (found using half devision method) = {'%.4f' % getXHalfDevisionMethod(func, a, b, e)}")

    output.write(f"Function crosses y=0 in x (found using secant method) = {'%.4f' % getXSecantMethod(func, a, b, e)}")

    simpleIterX = getXSimpleIterationMethod(func, a, b, e)
    if simpleIterX != "None":
        output.write(f"Function crosses y=0 in x (found using method of simple iterations) = {'%.4f' % simpleIterX}")

    # output.write(getHordMethod(func, a, b, e))
    
    output.closeFile()
    xArray = getxArray(a, b, 0.1)
    yArray = getyArray(func, xArray, symbolsArr[0])
    plt.plot(xArray, yArray, label=str(func))
    plt.axhline(y=0, color='black', linestyle='--')
    plt.legend(loc="upper left")
    plt.show()
    
def getSolutionOfSystem(funcArr, e, *args):
    symbolsArr = getSymbolsArr(funcArr)
    result = getXNewtonMethodSystem(funcArr, e, symbolsArr, *args)
    output.write(f"Solution: {result}")
    if len(getSymbolsArr(funcArr)) < 3:
        for func in funcArr:
            drawAFuncInInterval(func, symbolsArr, result[symbolsArr[0]], result[symbolsArr[1]])
    output.closeFile()
    plt.show()


print("Var: 11.")
print("Numerical solution of nonlinear equations and of system of nonlinear equations.")

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
    funcArr, startVal, e = cliInput()
else:
    funcArr, startVal, e = fileInput()

print(startVal)

output = Output()
output.initFileOrCLIOutput(fileOrCliOutput)

if len(funcArr) != 1:
    getSolutionOfSystem(funcArr, e, startVal)
else:
    getSolutionOfOneEquation(funcArr[0], startVal[0], startVal[1], e)

# methods - метод половинного деления, метод секущих, метод простой итерации, системы - метод Ньютона
