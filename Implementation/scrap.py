import os

d = '../benchmarks/'
f = open("RunningBenchmarksOnWindows.bat", "a")
index = 0
for o in os.listdir(d):
    bench = os.path.join(d,o)
    if os.path.isdir(bench):
        for oo in os.listdir(bench):
            method = os.path.join(bench,oo)
            if os.path.isdir(method): 
                for ooo in os.listdir(method):
                    Eq = os.path.join(method,ooo)
                    if os.path.isdir(Eq): 
                        versions = []
                        for version in os.listdir(Eq):
                            if(version.startswith("new")):
                               versions.append(os.path.join(Eq,version))
                            if(version.startswith("old")):
                               versions.append(os.path.join(Eq,version))
                        index = index + 1
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("echo \"###################################    "+ Eq +"     ###################################\" \n")
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("java -Djava.library.path=jpf-git/jpf-symbc/lib -Xmx20G -Xms16G -jar target/artifacts/Implementation_jar/Implementation.jar --path1 " + versions[1] + " --path2 " + versions[0] + " --tool D --s coral --b 3 & timeout /T 300 \n")
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("java -Djava.library.path=jpf-git/jpf-symbc/lib -Xmx20G -Xms16G -jar target/artifacts/Implementation_jar/Implementation.jar --path1 " + versions[1] + " --path2 " + versions[0] + " --tool I --s coral --b 3 & timeout /T 300 \n")
                        f.write("echo \"###################################################################################################\" \n")
                        f.write("java -Djava.library.path=jpf-git/jpf-symbc/lib -Xmx20G -Xms16G -jar target/artifacts/Implementation_jar/Implementation.jar --path1 " + versions[1] + " --path2 " + versions[0] + " --tool A --s coral --b 3 & timeout /T 300 \n")
                        print(Eq)
f.close()
print(index)  
