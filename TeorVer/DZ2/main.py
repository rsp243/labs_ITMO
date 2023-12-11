import matplotlib.pyplot as plt
import numpy as np
import math

X = [ -0.26, 1.49, -1.54, -1.33, -1.68, -1.55, 0.34, -0.84, -1.72, 0.34, -0.58, -0.84, 1.13, -0.78, -0.94, 1.54, 0.58, -1.58, -0.49, -0.14 ]

# вариационный ряд
var_row = sorted(X)
print("Вариационный ряд: ")
print(var_row)
print()

min1 = min(X)
max1 = max(X)
print("Экстримальные значения - " + str(min1) + ", " + str(max1) + ". Размах - " + str(max1 - min1) + ".")
print()

length = len(X)

M = 0
for i in range(length):
    M += X[i] * X.count(X[i]) 
M = M / length
print("Оценка мат. ожидания - " + str(M))

D = 0
for i in range(length):
    D += pow((X[i] - M), 2) * X.count(X[i])
D = D / length
sigma = pow(D, 0.5)
print("Среднеквадратическое отклонение - " + str(sigma))
print()

set_var_row = sorted(list(set(var_row)))
set_var_row_length = len(set_var_row)
sum = 0

stats_row = {i: 0 for i in var_row}
for i in var_row:
    stats_row[i] += 1
print("Статистический ряд:") # Statistic row - array with repeat count of each element in input array
for i in stats_row:
    print(i, end=" ")
print()
for i in stats_row:
    if i < 0:
        print(stats_row[i], end="     ")
    else:
        print(stats_row[i], end="    ")
print()

print()

p = {i: 0 for i in var_row} # p - array to save p probabilities
for i in set_var_row:
    p[i] = stats_row[i] / len(var_row)

print("Массив частостей для каждого из x в X:")
print(p)
print()

print("Интервальный статистический ряд:")
h = round((max1 - min1) / (1 + math.log2(set_var_row_length)), 1) # count of intervals in interval statictic row
xfirst = min1 - h / 2 # first num in interval
xlast = min1 + h / 2 # last num in interval
count_of_x_in_interval = 0
interval_centers = list()
interval_frequency = list()

for i in set_var_row:
    if i < xlast:
        count_of_x_in_interval += stats_row[i]
    else:
        interval_centers.append((xfirst + xlast) / 2)
        interval_frequency.append(count_of_x_in_interval / len(var_row)) # add частотность to array
        print("[" + str(xfirst) + ", " + str(xlast) + ") - частота:", count_of_x_in_interval, "частотность:", count_of_x_in_interval / len(var_row))
        count_of_x_in_interval = 0
        xfirst = xlast # first num in interval
        xlast = round(xfirst + h, 2) # last num in interval
        count_of_x_in_interval += stats_row[i]
interval_centers.append((xfirst + xlast) / 2)
interval_frequency.append(count_of_x_in_interval / len(var_row))
print("[" + str(xfirst) + ", " + str(xlast) + ") - частота:", count_of_x_in_interval, "частотность:", count_of_x_in_interval / len(var_row))
print()

print("Эмпирическая функция распределения - ")
print(f"Для x <= '{set_var_row[0]}' - 0")
previous = set_var_row[0]
sum = p[set_var_row[0]]
for i in set_var_row[1:]:
    print(f"Для '{previous}' < x < '{i}' - {sum}")
    sum = round(sum + p[i], 3)
    previous = i
print(f"Для '{previous}' < x - {sum}")
print()

def F(x):
    ans = 0
    for i in set_var_row:
        if (i < x):
            ans += p[i]
    return ans

x = np.linspace(var_row[0] - 1, var_row[-1] + 1, 10000) # drawing line
y = [F(i) for i in x] # compuute function

# add coordinate axes
figure = plt.figure()
coordinate_axes = figure.add_subplot(1, 1, 1)

# drawing coordinate xes
coordinate_axes.spines['left'].set_position('center')
coordinate_axes.spines['bottom'].set_position('zero')
coordinate_axes.spines['right'].set_color('none')
coordinate_axes.spines['top'].set_color('none')
coordinate_axes.xaxis.set_ticks_position('bottom')
coordinate_axes.yaxis.set_ticks_position('left')

print(interval_centers)

plt.plot(x, y, 'b') 
plt.show()

plt.bar(interval_centers, interval_frequency)
plt.show()

plt.plot(interval_centers, interval_frequency)
plt.show()

