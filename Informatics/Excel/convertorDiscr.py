print("Введите число А")
A = int(input())
# print("Введите число C")
# C = int(input())
hexA = hex(A)[2:].upper()
# hexC = hex(C)[2:].upper()
binA = ""
binB = ""
for i in hexA:
    hexVal = "ABCDEF"
    if i not in hexVal:
        print(0 | int(bin(int(i))[2:]))
    else:
        array = [_ for _ in range(10, 16, 1)]
        print(int(bin(int(array[hexVal.index(i)]))[2:]))