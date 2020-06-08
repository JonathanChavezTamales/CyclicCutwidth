import os
import sys

if(len(sys.argv) <= 2):
    print("No se indico nombre de instancia y/o su ruta")
    sys.exit()

instance_name = sys.argv[1]
instance_path = sys.argv[2]

numer_of_tests = 1

for x in range(1,numer_of_tests+1):
    os.system(f"java -cp ../src/ Main < {instance_path} > ../tests/{instance_name}/{instance_name}.{x}.result")