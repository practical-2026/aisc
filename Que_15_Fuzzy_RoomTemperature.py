# python -m pip install numpy matplotlib
# python Que_15_Fuzzy_RoomTemperature.py
import numpy as np
import matplotlib.pyplot as plt

names = ["Cold", "Comfortable", "Hot"]

tri = [[-10, 0, 20], [10, 22, 34], [24, 40, 56]]
trap = [[-10, 0, 10, 20], [15, 20, 26, 31], [26, 32, 45, 50]]
gauss = [[5, 6], [22, 4], [38, 6]]


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


value = 18  # Temperature (°C)

for function_type, function_name in enumerate(
    ["Triangular", "Trapezoidal", "Gaussian"]
):
    print(f"\n{function_name} memberships at {value}")

    for i in range(3):
        print(f"{names[i]}: {membership(function_type, i, value):.2f}")

x_values = np.linspace(-15, 60, 500)

fig, axes = plt.subplots(1, 3, figsize=(18, 5))

function_names = ["Triangular", "Trapezoidal", "Gaussian"]

for function_type, ax in enumerate(axes):

    for i in range(3):
        y_values = [
            membership(function_type, i, x)
            for x in x_values
        ]

        ax.plot(x_values, y_values, label=names[i])

        y = membership(function_type, i, value)
        ax.plot(value, y, "o")
        ax.hlines(y, -15, value, linestyles="dotted", alpha=0.5)

    ax.axvline(x=value, linestyle="--", label="Temperature = 18°C")

    ax.set_title(function_names[function_type])
    ax.set_xlabel("Room Temperature (°C)")
    ax.set_ylabel("Membership Value")
    ax.set_ylim(0, 1.05)
    ax.grid(True)
    ax.legend(fontsize=8)

plt.suptitle("Fuzzy Room Temperature Membership Functions")
plt.tight_layout()
plt.show()
