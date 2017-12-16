def sum(arr):
    res = 0
    for i in arr:
        res += i
    return res

import sys
rl = lambda: sys.stdin.readline()
n = int(rl())

for i in range(n):
    w = int(rl().strip())
    arr = [int(i) for i in rl().strip().split(" ")]
    if w >= sum(arr) :
        print "YES"
    else:
        print "NO"
