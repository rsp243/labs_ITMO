import re
#367401 % 6 = 3


#cсоздаём функцию проверки текста на фамилии
def check_for_fam(text1):
    file_to_check = open(text1)
    file_in_string = file_to_check.read()
    return re.findall("[А-Я][а-я]+\s+[А-Я]\.[А-Я]\.", file_in_string)

#Запускам пять тестов для проверки написанной функциий
for i in range (1,6):
    current_test = 'test' + str(i) + '.txt'
    a = check_for_fam(current_test).sort()
    for i in a:
        print(i[:-5])
