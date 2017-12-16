import sys
rl = sys.stdin.readline
n = int(rl())
for i in range(1,n+1):
    arr = rl().strip().split(" ")
    val = arr[0]
    unit = arr[1]
    if(unit == "kg") :
        print("%d %.4f lb" % (i,float(val) * 2.2046))
    if(unit == "l") :
        print("%d %.4f g" % (i,float(val) * 0.2642))
    if(unit == "lb") :
        print("%d %.4f kg" % (i,float(val) * 0.4536))
    if(unit == "g") :
        print("%d %.4f l" % (i,float(val) * 3.7854))
