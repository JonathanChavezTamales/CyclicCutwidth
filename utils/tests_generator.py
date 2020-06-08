import os
import sys

if(len(sys.argv) <= 2):
    print("No se indico nombre de instancia y/o su ruta")
    sys.exit()

for i in range(1, len(sys.argv), 2):
    instance_name = sys.argv[i]
    instance_path = sys.argv[i+1]

    numer_of_tests = 10

    for x in range(1,numer_of_tests+1):
        os.system(f"java -cp ../src/ Main < {instance_path} > ../tests/{instance_name}/{instance_name}.{x}.result 100 180000 10 6000000")