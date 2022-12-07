import re

var1 = 367514
print("Вариант:", var1 % 6)

print('Введите строку')
s = input()

h = re.compile(r'\bВТ\b(?:.\w*){,6}\bИТМО\b')
result = h.findall(s)
if len(result) == 0:
    print('Выражение <"ВТ" ...не более 4 слов... "ИТМО"> не найдено в текущей строке.')
else:
    print(*result)