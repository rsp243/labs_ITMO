import re

var1 = 367514
print("Вариант:", var1 % 5)
print("Введите строку, разделяя слова пробелами. Выделите по 3 одинаковых буквы в каждом слове, написав их строчными.")
s = input()
pattern = "[А-ЯA-Z]"
h = re.compile(pattern)
b = set(h.findall(s))
if len(list(b)) != 3:
    print("Перезапустите программу и введите корректные данные.")
    exit(0)
b = sorted(b)
bLower = set()
for i in range(len(list(b))):
    bLower.add(list(b)[i].lower())
bLower = sorted(bLower)
flag = True
for i in re.compile("\w+").findall(s):
    checker = list()
    length = 0
    for j in range(len(i)):
        if i[j] not in b:
            length += 1
        if i[j] in b or i[j] in bLower:
            checker.append(length)
            length = 0
    if len(list(set(checker[1:]))) == 1:
        print(i)
        flag = False
if flag:
    print("Не найдено ни одного слова с одинаковым растоянием между выделенными буквами")