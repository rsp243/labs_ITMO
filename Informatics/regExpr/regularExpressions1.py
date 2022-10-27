import re

var1 = 367514
print("Вариант:", var1 % 6)

print('Введите строку')
s = input()
count = 0
a = re.findall('\w+', s)
flag = True
for i in range(len(a)):
    count = 0
    pos = 0
    if a[i] == "ВТ":
        pos = i
        for j in range(i + 1, len(a)):
            if pos != 0:
                count += 1
            if a[j] == "ИТМО" and 0 < count <= 4: 
                print(*a[pos:i+count + 1])
                flag = False
if flag:
    print('Выражение <"ВТ" ...не более 4 слов... "ИТМО"> не найдено в текущей строке.')