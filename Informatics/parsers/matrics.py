m, n = map(int, input().split())
a = [[0 for _ in range(m)] for j in range(n)]

x = 1
i = 0
j = 0
while x < m * n:
    while a[i][j + 1] == 0:
        a[i][j] = x
        if j + 1 != m:
            j += 1
        x += 1
        if j + 1 == m:
            break
    while a[i + 1][j] == 0:
        a[i][j] = x
        if i + 1 != n:
            i += 1
        x += 1
        if i + 1 == n:
            break
    while a[i][j - 1] == 0:
        a[i][j] = x
        if j - 1 != -1:
            j -= 1
        x += 1
        if j - 1 == -1:
            break
    while a[i - 1][j] == 0:
        a[i][j] = x
        if i - 1 != 0:
            i -= 1
        x += 1
        if i - 1 == 0:
            break

for i in range(n):
    print(*a[i])