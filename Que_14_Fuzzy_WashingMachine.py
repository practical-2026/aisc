# python -m pip install numpy matplotlib
# python Que_14_Fuzzy_WashingMachine.py
import numpy as np
import matplotlib.pyplot as plt

names = ["Lightly Soiled", "Moderately Soiled", "Heavily Soiled"]

tri = [[-50, 0, 50], [0, 50, 100], [50, 100, 150]]
trap = [[-10, 0, 20, 40], [20, 40, 60, 80], [60, 80, 100, 110]]
gauss = [[0, 20], [50, 15], [100, 20]]


def triangular(x, a, b, c):
    if x <= a or x >= c:
        return 0
    if x <= b:
        return (x - a) / (b - a)
    return (c - x) / (c - b)


def trapezoidal(x, a, b, c, d):
    if x <= a or x >= d:
        return 0
    if x < b:
        return (x - a) / (b - a)
    if x <= c:
        return 1
    return (d - x) / (d - c)


def gaussian(x, m, s):
    return np.exp(-((x - m) ** 2) / (2 * s ** 2))


def membership(function_type, i, x):
    if function_type == 0:
        return triangular(x, *tri[i])
    elif function_type == 1:
        return trapezoidal(x, *trap[i])
    else:
        return gaussian(x, *gauss[i])


value = 20

for function_type, function_name in enumerate(
    ["Triangular", "Trapezoidal", "Gaussian"]
):
    print(f"\n{function_name} memberships at {value}")

    for i in range(3):
        print(f"{names[i]}: {membership(function_type, i, value):.2f}")


x_values = np.linspace(-20, 160, 500)
value = 35

fig, axes = plt.subplots(1, 3, figsize=(18, 5))

function_names = ["Triangular", "Trapezoidal", "Gaussian"]

for function_type, ax in enumerate(axes):

    for i in range(3):
        y_values = [
            membership(function_type, i, x)
            for x in x_values
        ]

        ax.plot(x_values, y_values, label=names[i])

        # Mark membership value at dirt level 35
        y = membership(function_type, i, value)
        ax.plot(value, y, "o")
        ax.hlines(y, -20, value, linestyles="dotted", alpha=0.5)

    # Vertical line at input value 35
    ax.axvline(
        x=value,
        linestyle="--",
        label="Dirt Level = 35"
    )

    ax.set_title(function_names[function_type])
    ax.set_xlabel("Dirt Level (%)")
    ax.set_ylabel("Membership Value")
    ax.set_ylim(0, 1.05)
    ax.grid(True)
    ax.legend(fontsize=8)

plt.suptitle("Fuzzy Washing Machine Membership Functions")
plt.tight_layout()
plt.show()
