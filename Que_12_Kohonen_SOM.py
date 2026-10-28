# python -m pip install numpy matplotlib
# python Que_12_Kohonen_SOM.py
import numpy as np
import matplotlib.pyplot as plt

# 6 input points: 3 near (1,1) and 3 near (8,8)
x = np.array([
    [1, 1], [3, 6], [2, 1],
    [8, 8], [8, 9], [9, 8]
])

# 2 neurons = 2 clusters
w = np.array([
    [0.0, 0.0],
    [10.0, 10.0]
])

lr = 0.5


# Find the winning neuron closest to the input point
def winner(p, w):
    distances = np.sum((w - p) ** 2, axis=1)
    return np.argmin(distances)


# Train the SOM
for epoch in range(20):
    for p in x:
        win = winner(p, w)

        # Update only the winning neuron
        w[win] += lr * (p - w[win])

    lr = lr * 0.9  # Decrease learning rate


# Assign points to clusters
clusters = [[], []]

for p in x:
    c = winner(p, w)
    clusters[c].append(p)


# Display cluster results
for c in range(2):
    print(f"Cluster {c}: ", end="")

    for p in clusters[c]:
        print(f"({int(p[0])},{int(p[1])}) ", end="")

    print(f"  Weight = [{w[c][0]:.2f}, {w[c][1]:.2f}]")


# Visualize the clusters
plt.figure(figsize=(8, 6))

colors = ["blue", "green"]

for c in range(2):
    points = np.array(clusters[c])

    if len(points) > 0:
        plt.scatter(
            points[:, 0],
            points[:, 1],
            color=colors[c],
            label=f"Cluster {c}",
            s=100
        )

    # Plot the final neuron weight
    plt.scatter(
        w[c][0],
        w[c][1],
        color=colors[c],
        marker="X",
        s=250,
        edgecolors="black",
        label=f"Neuron {c}"
    )

plt.title("Kohonen Self-Organizing Map (SOM)")
plt.xlabel("Input Feature 1")
plt.ylabel("Input Feature 2")
plt.grid(True)
plt.legend()
plt.show()
