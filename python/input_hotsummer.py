import random
T = random.randrange(1,2)
print str(T)
for i in range(T):
    n = random.randrange(990,1000)
    k = random.randrange(1,n+1)
    print n, k
    tmpstr = ""
    for i in range(n):
        c = random.randrange(1,1000)
        r = random.randrange(1,c+1)
        tmpstr += str(r) + " " + str(c) + " "
    print tmpstr
