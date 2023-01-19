import re

print("I" + "T" if "2018".isdigit() else "M" + "O")
num = float(2)
s = "88877777777763150781111"
pattern = '(\d)\\1'
h = re.compile(pattern)
print(sorted(list(set(h.findall(s)))))

# print("Введите строку, программа найдет в ней номер телефона")
# s = input()
# try:
#     print(re.compile(r'(?:(?:\+|00)(\d){1,3}\((\d){3,3}\)(?: |)(\d){3,3}-(\d){2,2}-(\d){2,2}|(?:\+|00)(\d){1,3}\((\d){4,4}\)(?: |)(\d){3,3}-(\d){2,2}-(\d){1,1}|(?:\+|00)(\d){1,3}\((\d){5,5}\)(?: |)(\d){3,3}-(\d){2,2})').search(s).group(0))
# except:
#     print("Не найдено!")

x = True
y = False
z = False
if not x or y:
    print(1)
elif not x or not y and z:
    print(2)
elif not x or y or not y and x:
    print(3)
else:
    print(4)

from math import *
L1_up = list(map(int,input("L1_up: ").strip().split()))
L1_down = list(map(int,input("L1_down: ").strip().split()))
L2_up = list(map(int,input("L2_up: ").strip().split()))
L2_down = list(map(int,input("L2_down: ").strip().split()))
a_b = [L1_down[1]*L2_down[2]-L1_down[2]*L2_down[1],
L1_down[2]*L2_down[0]-L1_down[0]*L2_down[2],
L1_down[0]*L2_down[1]-L1_down[1]*L2_down[0]]
L = 1/sqrt(a_b[0]**2+a_b[1]**2+a_b[2]**2)
N = [a_b[0]*L, a_b[1]*L, a_b[2]*L,(a_b[0]*(-L2_up[0])+a_b[1]*(-L2_up[1])+a_b[2]*(-L2_up[2]))*L]
print(abs(N[0]*L1_up[0]+N[1]*L1_up[1]+N[2]*L1_up[2]+N[3]))