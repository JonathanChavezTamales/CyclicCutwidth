"""Este programa toma los resultados de las instancias, calcula estadisticas y grafica"""
import sys
import numpy as np
import matplotlib.pyplot as plt

if(len(sys.argv) <= 1):
    print("No se indico nombre de instancia")
    sys.exit()

instancename = sys.argv[1]


ccws = []
times = []
points = [[[], []], [[], []], [[], []], [[], []], [[], []]]

for i in range(1,6):
    fname = f"{instancename}.{i}.result"
    lines = []
    with open(fname, 'r') as f:
        lines = f.read().splitlines()
    
    ccws.append(int(lines[-1]))
    times.append(int(lines[-2]))

    for j in range(len(lines)-2):
        points[i-1][0].append(int(lines[j].split(",")[0]))
        points[i-1][1].append(int(lines[j].split(",")[1]))


# Print stats
print("Minimum CCW: ", np.min(ccws))
print("Maximum CCW: ", np.max(ccws))
print("Avg CCW: ", np.average(ccws))
print("Standard deviation: ", np.std(ccws))
print("Average time: " , np.average(times))


# Show plot
name = instancename.split("/")[-1]
for i in range(5):
    plt.plot(points[i][0], points[i][1])

    plt.title(f"minCCW - {name}")
    plt.legend(("iter1", "iter2", "iter3", "iter4", "iter5"))
    plt.xlabel("Iteración")
    plt.ylabel("minCCW")
plt.show()

