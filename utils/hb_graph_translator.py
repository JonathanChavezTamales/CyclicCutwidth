""" Convierte un archivo .rnd a uno .rnd2 (nuestro formato)"""
import sys

filename = ""

if len(sys.argv) >= 2:
    filename = sys.argv[1]
else:
    print("No filename provided")
    sys.exit()

newfile = open(filename+'2', 'w')

with open(filename, 'r') as reader:
        line = reader.readline()
        line = reader.readline()
        vertices = line.split()[0]
        newfile.write(vertices + '\n')

        line = reader.readline()

        while line != '':
            newfile.write(str(int(line.split()[0])-1) + ' ' + str(int(line.split()[1])-1) + '\n')
            line = reader.readline()

newfile.write("-1 -1\n")
newfile.close()
        
