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
    output.write("%9s" % "Iteration", end=" ")
    for i in range(len(args)):
        output.write("%9s" % args[i].name, end = " ")
    output.write()

    n = len(args[0].array)
    for i in range(len(args)):
        output.write("%9d" % i, end=" ")
        for j in range(n):
            output.write("%9.3f" % args[j].array[i], end=" ")
        output.write()

def getXHalfDevisionMethod(func, a, b, e):
    output.write("Calculating with half devision method")
    n = int(np.log2(abs(a - b) / e)) + 1
    output.write(f"Count of iterations = {n}")
    
    beginning = a
    end = b
    x_i = (beginning + end) / 2
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
