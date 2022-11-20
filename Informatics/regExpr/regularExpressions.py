import re

def spec(x):
    set1 = set(["\\", "]", "[", "(", ")"])
    f = False
    j = 0
    s1 = x
    for i in range(len(x)):
        if x[i] in set1:
            j = i
            f = True
    if f:
        s1 = x[:j] + "\\" + x[j:]
    return s1
            

print("Введите целое шестизначное число (табельный номер студента, логин ИСУ). С помощью регулярных выражений, программа сделает из него смайлик!")
var1 = input()
try: 
    if len(var1) != 6:
        print("Перезапустите програмаму и введите корректные данные.")
        exit(0)
    var1 = int(var1)
except :
    print("Перезапустите програмаму и введите корректные данные.")
    exit(0)
print(var1 % 6, var1 % 4, var1 % 7)
string1 = "0:1;2X384=5[6"
string2 = "0-1<2-{3<{4"
string3 = "0(1)2O3|4\\5/6P7"
first = re.search(str(var1 % 6) + '.?\d?', string1).group(0)[1:-1]
second = re.search(str(var1 % 4) + '.?.?\d?', string2).group(0)[1:-1]
third = re.search(str(var1 % 7) + '.?.?\d?', string3).group(0)[1:-1]
pattern1 = first + second + third
print("Ваш смайлик:", pattern1)

print("Введите текст и мы выведем количество найденных смайликов в тексте")
text = input()

print(len(re.findall(spec(pattern1), text)))

# Тесты: 367514 ( Ответ программы - X-{(, мой ответ - X-{( )
#        728899 ( Ответ программы - ;<{|, мой ответ - ;<{| )
#        176546 ( Ответ программы - X-{P, мой ответ - X-{P )
#        636363 ( Ответ программы - 8<{(, мой ответ - 8<{( )
#        100045 ( Ответ программы - ;<), мой ответ - ;<) )
#        999990 ( Ответ программы - :-{/, мой ответ - :-{/ )