import matplotlib.pyplot as plt
import numpy as np
import math

X = [ 0.053,
0.026,
0.037,
0.056,
0.041,
0.035,
0.031,
0.046,
0.021,
0.054,
0.035,
0.039,
0.043,
0.031,
0.038,
0.023,
0.045,
0.026,
0.037,
0.042,
0.030,
0.041,
0.021,
0.047,
0.026,
0.046,
0.033,
0.038,
0.053,
0.035,
0.049,
0.054,
0.039,
0.034,
0.051,
0.029,
0.046,
0.023,
0.038,
0.043,
0.026,
0.039,
0.033,
0.020,
0.042,
0.050,
0.025,
0.037,
0.041,
0.029,
0.029,
0.038,
0.027,
0.043,
0.035,
0.030,
0.049,
0.055,
0.039,
0.034,
0.022,
0.045,
0.034,
0.055,
0.037,
0.025,
0.033,
0.051,
0.027,
0.045,
0.041,
0.051,
0.027,
0.046,
0.029,
0.038,
0.042,
0.020,
0.039,
0.031,
0.025,
0.047,
0.030,
0.050,
0.023,
0.039,
0.035,
0.049,
0.030,
0.047,
0.034,
0.022,
0.042,
0.031,
0.049,
0.033,
0.056,
0.037,
0.050, 
0.025, ]

all_length = len(X)
print("Количество чисел в X -", all_length)

print("Пункт А")
# вариационный ряд
var_row = sorted(X)
print("Вариационный ряд: ")
print(var_row)
print()

min1 = min(X)
max1 = max(X)
omega = max1 - min1
l = 9
print("Экстримальные значения - " + str(min1) + ", " + str(max1) + ". Размах - " + str(omega) + ".")
print()

h = omega / l
print("Длина частичного интервала -", h)

length = len(X)

n_i_list = list()
x_i_avg_list = list()
W_i_list = list()
W_i_h_list = list()

xmin = min1
xmax = xmin + h

F_list = list()
s_previous = 0
F_list.append(s_previous)
x_list = list()
x_list.append(xmin)
print("Пункт Б")

for i in range(1, l + 1):
    n_i = len(list(x for x in var_row if xmin <= x <= xmax))
    n_i_list.append(n_i)
    W_i = n_i / all_length
    s_previous += W_i 
    W_i_list.append(W_i)
    W_i_h = W_i / h
    W_i_h_list.append(W_i_h)
    x_i_avg = round((xmin + xmax) / 2, 4)
    x_i_avg_list.append(x_i_avg)
    F_list.append(s_previous)
    x_list.append(xmax)
    print(i, xmin, "-", xmax, x_i_avg, n_i, W_i, W_i_h)
    xmin = xmax
    xmax = round(xmin + h, 4)

print("Пункт В")

# add coordinate axes
# figure = plt.figure()
# coordinate_axes = figure.add_subplot(1, 1, 1)

# # drawing coordinate xes
# coordinate_axes.spines['left'].set_position('center')
# coordinate_axes.spines['bottom'].set_position('zero')
# coordinate_axes.spines['right'].set_color('none')
# coordinate_axes.spines['top'].set_color('none')
# coordinate_axes.xaxis.set_ticks_position('bottom')
# coordinate_axes.yaxis.set_ticks_position('left')

# plt.plot(x_i_avg_list, n_i_list, 'b') 
# plt.show()

# # need to fix!
# plt.bar(x_i_avg_list, W_i_h_list)
# plt.show()

# plt.plot(x_list, F_list)
# plt.show()

print()
print("Пункт Г")
xmin = min1
xmax = xmin + h
sum_x_i_n_i = 0
sum_x_ikv_n_i = 0
for i in range(1, l + 1):
    n_i = len(list(x for x in var_row if xmin <= x <= xmax))
    x_i_avg = round((xmin + xmax) / 2, 4)
    sum_x_i_n_i += round(n_i * x_i_avg, 6)
    sum_x_ikv_n_i += round(x_i_avg ** 2 * n_i, 8)
    print(i, xmin, "-", xmax, x_i_avg, n_i, round(n_i * x_i_avg, 6), round(x_i_avg ** 2, 7), round(x_i_avg ** 2 * n_i, 8))
    xmin = xmax
    xmax = round(xmin + h, 4)

sum_x_ikv_n_i = round(sum_x_ikv_n_i, 9)
print("Сумма x_i * n_i =", sum_x_i_n_i)
print("Сумма x_i**2 * n_i =", sum_x_ikv_n_i)
print()
x_vi_avg = sum_x_i_n_i / length
print("Выборочное среднее -", x_vi_avg)
# Dispertion
D = round(sum_x_ikv_n_i / length - (sum_x_i_n_i / length) ** 2, 10)
print("Выборочная дисперсия -", D)
sigma = round(D ** 0.5, 10)
print("Сигма_в =", round(D ** 0.5, 10))
print("D_в с чертой =", round(length / (length - 1) * D, 10))
print("Сигма_в с чертой =", round((length / (length - 1) * D) ** 0.5, 10))

print()

print("Пункт Д")
xmin = min1
xmax = xmin + h
print("i Интервал\t x_i_xSH\t  x_i_1_xSH\t Границы интервала (z_i; z_i+1)")
for i in range(1, l + 1):
    x_i_xSH = round(xmin - x_vi_avg, 10)
    x_i_1_xSH = round(xmax - x_vi_avg, 10)
    # print(i, "\t",  xmin,"\t", "-","\t", xmax, "\t", x_i_xSH, x_i_1_xSH)
    if i == 1:
        print(f"{i} {xmin}\t- {xmax}\t ------ \t {round(x_i_1_xSH, 7)}\t ---------- - {round(x_i_1_xSH / sigma, 7)}")
    elif i == l:
        print(f"{i} {xmin}\t- {xmax}\t {x_i_xSH}\t ------ \t {round(x_i_xSH / sigma, 7)} - ----------")
    else:
        print(f"{i} {xmin}\t- {xmax}\t {x_i_xSH}\t {x_i_1_xSH}\t {round(x_i_xSH / sigma, 7)} - {round(x_i_1_xSH / sigma, 7)}")
    xmin = xmax
    xmax = round(xmin + h, 4)

print()
F_x = [-0.5000, -0.4207, -0.3389, -0.2157, -0.0596, 0.1026, 0.2517, 0.3643, 0.4357, 0.5000]
xmin = min1
xmax = xmin + h
print("i\t Интервал z\t \t Ф(z_i)\t \t \tФ(z_i+1) \t P_i = Ф(z_i+1) - Ф(z_i) \tn_i_sh = 100 * P_i")
sumP = 0
sumn_i_sh = 0
for i in range(1, l + 1):
    x_i_xSH = round(xmin - x_vi_avg, 10)
    x_i_1_xSH = round(xmax - x_vi_avg, 10)
    P = F_x[i] - F_x[i - 1]
    n_i_sh = P * 100
    # print(i, "\t",  xmin,"\t", "-","\t", xmax, "\t", x_i_xSH, x_i_1_xSH)
    if i == 1:
        print(f"{i} ---------- - {round(x_i_1_xSH / sigma, 7)}\t {F_x[i - 1]:.4f} \t \t {F_x[i]:.4f}\t {P:.4f}\t \t \t \t{n_i_sh:.2f}")
    elif i == l:
        print(f"{i} {round(x_i_xSH / sigma, 7)} - ---------- \t {F_x[i - 1]:.4f} \t \t {F_x[i]:.4f} \t {P:.4f}\t \t \t \t{n_i_sh:.2f}")
    else:
        print(f"{i} {round(x_i_xSH / sigma, 7)} - {round(x_i_1_xSH / sigma, 7)} \t {F_x[i - 1]:.4f} \t \t {F_x[i]:.4f} \t {P:.4f}\t \t \t \t{n_i_sh:.2f}")
    sumP += F_x[i] - F_x[i - 1]
    sumn_i_sh += n_i_sh
    xmin = xmax
    xmax = round(xmin + h, 4)

print("\t\t\t\t\t\t\t\t\t", sumP, "\t\t\t\t", round(sumn_i_sh, 3))

print()

sumn_i = 0
sumhi_kv = 0
sumn_i_kv_del_n_i_sh = 0
xmin = min1
xmax = xmin + h
print("i n_i \t \t n_i_sh \t n_i - n_i_sh \t (n_i - n_i_sh)^2 \t (n_i - n_i_sh)^2 / n_i \t n_i^2 \t n_i^2 / n_i_sh")
for i in range(1, l + 1):
    n_i = len(list(x for x in var_row if xmin <= x <= xmax))
    P = F_x[i] - F_x[i - 1]
    n_i_sh = P * 100
    print(f"{i} {n_i:.2f}   \t {n_i_sh:.2f}   \t {(n_i - n_i_sh):.2f}   \t {((n_i - n_i_sh) ** 2):.2f} \t \t \t {(((n_i - n_i_sh) ** 2) / n_i_sh):.4f} \t \t \t {n_i ** 2} \t {(n_i ** 2 / n_i_sh):.4f}")
    sumn_i += n_i
    sumhi_kv += ((n_i - n_i_sh) ** 2) / n_i_sh
    sumn_i_kv_del_n_i_sh += n_i ** 2 / n_i_sh
    xmin = xmax
    xmax = round(xmin + h, 4)
print(" ", sumn_i, "\t \t", round(sumn_i_sh, 3), "\t\t\t\t", round(sumhi_kv, 4), "\t\t\t\t\t\t\t", round(sumn_i_kv_del_n_i_sh, 4))
print()
print("Проверка:", round(sumn_i_kv_del_n_i_sh, 4), "-", sumn_i, "=", round(round(sumn_i_kv_del_n_i_sh, 4) - sumn_i, 4))

print("alpha = 0.025, k = l - 3 = 6, l - число интервалов")
print("hi_cr^2 = 14,4")
print(f"Так как hi_набл^2 < hi_cr^2, где hi_набл^2 = {round(sumhi_kv, 4)} и hi_cr^2 = 14,4, то гипотеза о нормальном распределении генеральной совокупности принимается.")

print()
print("Пункт E")
t_gamma = 1.86
print(f"Дельта (точность оценки) - {round((length / (length - 1) * D) ** 0.5, 10) / (length ** 0.5) * t_gamma}")
print(f"Доверительный интервал для а (с вероятностью 90% этот интервал накроет занчение генеральной средней) - ({x_vi_avg - round((length / (length - 1) * D) ** 0.5, 10) / (length ** 0.5) * t_gamma}; {x_vi_avg + round((length / (length - 1) * D) ** 0.5, 10) / (length ** 0.5) * t_gamma})")

print()

q = 0.11
print(f"Доверительный интервал, покрывающий среднее квадратичное отлонение сигма с заданной надежностью гамма = (сигма_в_sh(1 - q); сигма_в_sh(1 + q)). q = 0.143 (из таблицы 9)")
print(f"Доверительный интервал, покрывающий среднее квадратичное отлонение сигма с заданной надежностью гамма = ({round((length / (length - 1) * D) ** 0.5, 10) * (1 - q)}; {round((length / (length - 1) * D) ** 0.5, 10) * (1 + q)})")
print(f"Доверительным интервалом для сигма будет ({round((length / (length - 1) * D) ** 0.5, 10) * (1 - q)}; {round((length / (length - 1) * D) ** 0.5, 10) * (1 + q)})")
