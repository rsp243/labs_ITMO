import numpy as np
import matplotlib.pyplot as plt

# Create a grid of x and y values
x = np.linspace(-2, 2, 100)
y = np.linspace(-2, 2, 100)
X, Y = np.meshgrid(x, y)

# Define the equation x^2 + y^2 = 4
Z = X**2 + Y**2 - 4

# Plot the contour of the circle
plt.contour(X, Y, Z, levels=[0], colors='b')
plt.gca().set_aspect('equal', adjustable='box')
plt.xlabel('x')
plt.ylabel('y')
plt.title('Circle with radius 2')

plt.grid(True)
plt.show()