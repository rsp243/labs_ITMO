print("Введите набор из семи цифр '0' или '1', записанных подряд:")
a = input()
if len(a) != 7 or a.count("0") + a.count("1") != 7:
    print("Вы ввели некорректные данные. Перезапустите программу для повторного ввода!")
    exit(0)
b = []
for i in a:
    b.append(bool(int(i)))
s1 = int(b[0])
s2 = int(b[1])
s3 = int(b[3])
a1 = [b[0], b[2], b[4], b[6]]
a2 = [b[1], b[2], b[5], b[6]]
a3 = [b[3], b[4], b[5], b[6]]
s1 = a1[0]
s2 = a2[0]
s3 = a3[0]
for i in range(1, 4):
    s1 ^= a1[i]
    s2 ^= a2[i]
    s3 ^= a3[i]
result = str(s1) + str(s2) + str(s3)
result = result.replace("True", "1")
result = result.replace("False", "0")
if result == '000':
    print("Ошибок не обнаружено! Сообщение: ", a)
elif result == '100':
    correct = ''
    for i in range(len(b)):
        if i == 0:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 1. Верное сообщение:", correct)
elif result == '010':
    correct = ''
    for i in range(len(b)):
        if i == 1:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 2. Верное сообщение:", correct)
elif result == '110':    
    correct = ''
    for i in range(len(b)):
        if i == 2:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 3. Верное сообщение:", correct)
elif result == '001':
    correct = ''
    for i in range(len(b)):
        if i == 3:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 4. Верное сообщение:", correct)
elif result == '101':
    correct = ''
    for i in range(len(b)):
        if i == 4:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 5. Верное сообщение:", correct)
elif result == '011':
    correct = ''
    for i in range(len(b)):
        if i == 5:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 6. Верное сообщение:", correct)
elif result == '111':
    correct = ''
    for i in range(len(b)):
        if i == 6:
            correct += str(not b[i])
        correct += str(b[i])
    correct = correct.replace("True", "1").replace("False", "0")
    print("Ошибка в 6ите 7. Верное сообщение:", correct)