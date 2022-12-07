def interpretation(num1, ss2):
    global listss1, num2
    if num1 > 0:
        num2 += listss1[num1 % ss2]
        interpretation(num1 // ss2, ss2)

def octinterpretation(oct, ss2, n):
    global oct2, listss1
    if len(str(oct)) > 3:
        if str(oct * ss2).find('.') != -1:
            oct2 += listss1[int(str(oct * ss2)[:str(oct * ss2).find('.')])]
        else:
            oct2 += listss1[int(str(oct * ss2))]
        if n <= 5:
            octinterpretation(float("0." + str(oct * ss2)[str(oct * ss2).find('.') + 1:]), ss2, n+1)

def err(num):
    global listss1
    try:  
        for i in range(len(num)):
            if listss1.find(num[i]) == -1:
                return True
            if listss1.find(num[i]) >= int(ss1):
                return True
    except:
        return True
    if num.count(',') >= 2:
        return True
    return False

print("Введите число:")
num = input()
print("Введите систему счисления исходного числа (<=16 или Берг(B)):")
ss1 = input()
print("Введите новую систему счисления(<=16)")
ss2 = input()
listss1 = "0123456789ABCDEF"
num10 = 0
oct = ""
try:
    if ss1 != 'B':    
        ss1 = int(ss1)
        ss2 = int(ss2)
except:
    print("Перезапустите программу и введите корректные данные!")
    exit(0)
if ss1 != 'B':
    if int(ss1) > 16 or int(ss2) > 16 or err(num):
        print("Перезапустите программу и введите корректные данные!")
        exit(0)
if num.find(',') != 0:
    oct = num[num.find(',') + 1:] 
    num = num[:num.find(',')]
num = num[::-1]
if ss1 == "B":
    z = (1 + 5 ** 0.5) / 2
    for i in range(len(num)):
        num10 += z ** i * listss1.find(num[i])
    if oct != "":
        oct10 = 0
        for i in range(len(oct)):
            oct10 += z ** (- (i + 1)) * listss1.find(oct[i])
else:
    for i in range(len(num)):
        num10 += int(ss1) ** i * listss1.find(num[i])
    if oct != "":
        oct10 = 0
        for i in range(len(oct)):
            oct10 += int(ss1) ** (- (i + 1)) * listss1.find(oct[i])
if ss2 == "10":
    print(num10 + oct10)
else:
    num2 = ""
    interpretation(num10, int(ss2))
    if oct != "":
        oct2 = "0,"
        octinterpretation(oct10, int(ss2), 0)
    if num2 != "":
        print(num2[::-1] + oct2[oct2.find(','):])
    else:
        print(oct2)