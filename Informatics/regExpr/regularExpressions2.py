import re

var1 = 367514
print("Вариант:", var1 % 5)
print("Введите строку, разделяя слова пробелами. Выделите по 3 одинаковых буквы в каждом слове, написав их строчными.")
s = input()
s = s.split(" ")
b = set()
for i in s:
    for j in re.findall('[А-ЯA-Z]', i):
        b.add(j)
    if len(re.findall('[А-Я]', i)) != 3 and len(list(b)) != 3:
        print("Перезапустите программу и введите корректные данные.")
        exit(0)
bLower = set()
for i in range(len(list(b))):
    bLower.add(list(b)[i].lower())
for i in s:
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