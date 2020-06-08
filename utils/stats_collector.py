"""Este programa toma los resultados de las instancias, calcula estadisticas y grafica"""
import sys
import numpy as np
import matplotlib.pyplot as plt

if(len(sys.argv) <= 1):
    print("No se indico nombre de instancia")
    sys.exit()

for x in range(1, len(sys.argv)):


    instancename = sys.argv[x]

    ccws = []
    times = []
    points = [[[], []] for x in range(10)]

    for i in range(1,11):
        fname = f"../tests/{instancename}/{instancename}.{i}.result"
        lines = []
        with open(fname, 'r') as f:
            lines = f.read().splitlines()
            f.close()
        
        ccws.append(int(lines[-1]))
        times.append(int(lines[-2]))

        for j in range(len(lines)-2):
            points[i-1][0].append(int(lines[j].split(",")[0]))
            points[i-1][1].append(int(lines[j].split(",")[1]))

    statistics_file = f"../tests/{instancename}/{instancename}.statistics"

    with open(statistics_file, 'w') as s:
        # Write stats
        s.write("Minimum CCW: "+ str(np.min(ccws)) + '\n')
        s.write("Maximum CCW: "+ str(np.max(ccws)) + '\n')
        s.write("Avg CCW: "+ str(np.average(ccws)) + '\n')
        s.write("Standard deviation: "+ str(np.std(ccws)) + '\n')
        s.write("Average time: "+ str(np.average(times)) + '\n')


    # Show plot
    name = instancename.split("/")[-1]
    for i in range(10):
        plt.plot(points[i][0], points[i][1])
        plt.title(f"minCCW - {name}")
        plt.legend(("iter1", "iter2", "iter3", "iter4", "iter5","iter6","iter7","iter8","iter9","iter10"))
        plt.xlabel("Iteración")
        plt.ylabel("minCCW")
    plt.show()

