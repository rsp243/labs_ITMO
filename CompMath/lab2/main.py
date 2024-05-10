import numpy as np
from sympy import Symbol, sympify, evalf, diff, solve, Max
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


def checkSplittedStrOnCount(splittedStr, n):
    if len(splittedStr) != n:
        print(f"You entered incorrect count of values in matrix row")
        exit(-1)

def cliInput():
    print("Enter function")

    func = input().strip()
    func = sympify(func)

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
    
    return (func, a, b, e)

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

    e = f.readline().strip()
    isFloat(e)
    e = float(e)
    
    return (func, a, b, e)

def printArrInColumns(*args):
    output.write("%15s" % "Iteration", end = " ")
    for i in range(len(args)):
        output.write("%15s" % args[i].name, end = " ")
    output.write()

    n = len(args[0].array)
    for j in range(n):
        output.write("%15s" % j, end = " ")
        for i in range(len(args)):
            output.write("%15.3f" % args[i].array[j], end=" ")
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

def getPhiFunc(func, a, b):
    func_hatch = diff(func) # differential of func
    lam = - 1 / (Max(abs(func_hatch.subs({Symbol("x"): a})), abs(func_hatch.subs({Symbol("x"): a}))))
    phi = sympify(f"x + {lam} * ({func})")
        
    return phi

def getXSimpleIterationMethod(func, a, b, e):
    output.write("Calculating with method of simple iterations")
    phi = getPhiFunc(func, a, b)
    x_0 = getStartingPoints(func, a, b)[0]
    q = min(abs(diff(phi).subs({Symbol("x"): a})), abs(diff(phi).subs({Symbol("x"): b})))
    if q >= 1 or q < 0:
        output.write("Iterational sequence of method of simple iterations {x_n} does not converge to the root of the equation.")
        return
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
    func, a, b, e = cliInput()
else:
    func, a, b, e = fileInput()

output = Output()
output.initFileOrCLIOutput(fileOrCliOutput)

# methods - метод половинного деления, метод секущих, метод простой итерации, системы - метод Ньютона
output.write(getXHalfDevisionMethod(func, a, b, e))

output.write(getXSecantMethod(func, a, b, e))

output.write(getXSimpleIterationMethod(func, a, b, e))
