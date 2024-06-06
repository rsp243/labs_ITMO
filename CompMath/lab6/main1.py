import sympy

print("#Дифференциальные уравнения первого порядка.")
print("#Дифференциальные уравнения с разделяющимися переменными")
print("#Пример 2")

x = sympy.symbols('x')
y = sympy.Function('y')

equation = sympy.Eq(y(x).diff(x), -2*y(x))
print("Условие:")
print(equation)
solution = sympy.dsolve(equation)
print("Общее решение:")
print(solution)
C1=sympy.solve(solution.subs(x,0).subs(y(0),2),'C1')[0]#Подставляем условия
z=solution.subs('C1',C1)
print("Частное решение:")
print(z)
print("Ответ сошелся")
