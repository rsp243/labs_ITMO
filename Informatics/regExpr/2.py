def f(x):
    p = 1
    for i in range(1, x):
        p *= i
    return p

a = 286 * f(10)
print(a)

for i in range(10, 50):
    if int(f(i) / f(i - 10)) == a:
        print(i)


for x in range(1000):
    if x ** 8 == (2 + x) ** 10:
        print(x)