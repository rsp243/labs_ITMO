import numpy as np

def isFloat(n):
    try:
        float(n)
    except ValueError:
        return False
    return True

def checkN(n):
    if not n.isdigit():
        print("Entered n is not an integer")
        exit(-1)

def checkSplittedStrOnCount(splittedStr, n):
    if len(splittedStr) != n:
        print(f"You entered incorrect count of values in matrix row")
        exit(-1)

def checkDet(det):
    if det == 0:
        print(f"Impossible to solve entered system of equations")
        exit(-1)

def getFloatMatrix(m):
    n = len(m)
    for i in range(n):
        for j in range(n):
            if not isFloat(m[i][j]):
                print(f"Entered matrix value '{m[i][j]}' on position ({i}; {j}) is not an integer")
                exit(-1)
            m[i][j] = float(m[i][j])
    return m

def getFloatResultArray(r):
    n = len(r)
    for i in range(n):
        if not isFloat(r[i]):
            print(f"Entered result matrix value '{r[i]}' on position (1; {i}) is not a float")
            exit(-1)
        r[i] = float(r[i])
    return r


def cliInput():
    print("Enter matrix size")
    n = input().strip()

    checkN(n)
    n = int(n)

    m = [[0 for _ in range(n)] for _ in range(n)]

    print("Enter matrix")
    for i in range(n):
        splittedStr = input().strip().split(" ")
        checkSplittedStrOnCount(splittedStr, n)
        m[i] = [j for j in splittedStr]
    m = getFloatMatrix(m)

    r = [0 for _ in range(n)]

    print(f"Enter result matrix with size 1X{n}")
    for i in range(n):
        splittedStr = input().strip().split(" ")
        checkSplittedStrOnCount(splittedStr, 1)
        r[i] = splittedStr[0]
    r = getFloatResultArray(r)
    
    return (m, r)

def fileInput():
    print("Enter filename")
    filename = input()
    try:
        f = open(f"{filename}", "r")
    except:
        print("There is no file with that name, or there is no priviliges to read it")
        exit(-1)
    n = f.readline().strip()

    checkN(n)
    n = int(n)

    m = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        splittedStr = f.readline().strip().split(" ")
        checkSplittedStrOnCount(splittedStr, n)
        m[i] = [j for j in splittedStr]
    m = getFloatMatrix(m)

    r = [0 for _ in range(n)]
    for i in range(n):
        splittedStr = f.readline().strip().split(" ")
        checkSplittedStrOnCount(splittedStr, 1)
        r[i] = splittedStr[0]
    r = getFloatResultArray(r)
    
    return (m, r)

def getMaxPosOfMaxEl(matrix, i):
    n = len(matrix)
    l = i
    m = i + 1
    while m < n:
        if abs(matrix[m][i]) > abs(matrix[l][i]):
            l = m
        m += 1

    return l

def getTriangleSystem(matrix, resultList):
    n = len(matrix)
    count = 0
    print("Executing:")
    for i in range(n - 1):
        maxCol = getMaxPosOfMaxEl(matrix, i)
        if i != maxCol:
            count += 1
            matrix[i], matrix[maxCol] = matrix[maxCol], matrix[i] 
            resultList[maxCol], resultList[i] = resultList[i], resultList[maxCol]
        k = i + 1
        while k < n:
            c = -1 * matrix[k][i] / matrix[i][i]
            print(f"c = {c}, matrix[k][i] = {matrix[k][i]}, matrix[i][i] = {matrix[i][i]}, k = {k}, i = {i}")
            for j in range(n):
                matrix[k][j] += c * matrix[i][j]
            resultList[k] += c * resultList[i]
            for x in range(n):
                for q in range(n):
                    print("%7.4f" % matrix[x][q], end=" ")
                print("%7.4f" % resultList[x])
            k += 1
    print()

    return matrix, resultList, count

def getDeterminant(matrix, k):
    n = len(matrix)
    p = 1
    for i in range(n):
        p *= m[i][i]
    return (-1) ** (k - 1) * p

def getSolution(matrix, resultList):
    n = len(matrix)
    xList = [0 for _ in range(n)]
    
    for i in range(n - 1, 0, -1):
        b = resultList[i]
        for j in range(n - 1, i, -1):
            b -= matrix[i][j] * xList[j]
        xList[i] = b / matrix[i][i]
    return xList

def getNievyazka(matrix, resultList, xList):
    n = len(matrix)
    r = [0 for i in range(n)]
    for i in range(n):
        sum = 0
        for j in range(n):
            sum += matrix[i][j] * xList[j] 
        r[i] = sum - resultList[i]
    return r


print("Var: 11.")
print("Gaussian method with chosing main element.")

print("Are you going to input data from file or using CLI?")
print("Enter '1' to use input from file, or '0' to use CLI")
fileOrCli = input().strip()
while fileOrCli != '0' and fileOrCli != '1':
    print("You entered not '1' and not '0' - try again")
    print("Enter '1' to use input from file, or '0' to use CLI")
    fileOrCli = input().strip()

if fileOrCli == "0":
    m, r = cliInput()
else:
    m, r = fileInput()
n = len(m)
print("Your system of equations:")
for x in range(n):
    for q in range(n):
        print("%7.4f" % m[x][q], end=" ")
    print("%7.4f" % r[x])

mNp = m
rNp = r

print()

m, r, k = getTriangleSystem(m, r)
print("Transformed system of equations:")
for x in range(n):
    for q in range(n):
        print("%7.4f" % m[x][q], end=" ")
    print("%7.4f" % r[x])

det = getDeterminant(m, k)
print(f"Determinant of matrix = {det}")
checkDet(det)

print()
xList = getSolution(m, r)
print("x list:")
for i in xList:
    print("(%7.4f)" % i)

nevyazkaList = getNievyazka(m, r, xList)
print("nevyazka list:")
for i in nevyazkaList:
    print("(%9.6f)" % i)

print()
print("-----------------------")

# using lib!
a = np.array(mNp)
b = np.array(rNp)
xListNp = np.linalg.inv(a).dot(b)
print("Matrix determinant evaluated by numpy = ", end="")
print("%7.4f" % np.linalg.det(a))
print("x list evaluated by numpy:")
for i in xListNp:
    print("(%7.4f)" % i)
