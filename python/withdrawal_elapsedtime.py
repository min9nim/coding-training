def getMinRank(tupleList):
    global n, k, rsum, csum
    minrsum = rsum
    mincsum = csum
    if n == k :
        minRank = rsum / float(csum)
    else:
        rankList = []
        for i in range(n):
            tmp = tupleList[i]
            rank = (rsum - tmp[0]) / float(csum - tmp[1])
            rankList.append((rank,tmp))
        rankList.sort(key=lambda tup: tup[0])
        for i in rankList:
            print i
        for i in range(n-k):
            minrsum -= rankList[i][1][0]
            mincsum -= rankList[i][1][1]
        minRank = minrsum / float(mincsum)
    return minRank

import time
import sys
rl = lambda: sys.stdin.readline()
n = int(rl())

for i in range(n):
    tupleList = []
    input1 = rl().strip().split(" ")
    n = int(input1[0])
    k = int(input1[1])
    #print n, k
    input2 = rl().strip().split(" ")
    start = time.time()

    rsum = 0
    csum = 0

    for i in range(0,len(input2),2):
        r = int(input2[i])
        c = int(input2[i+1])
        rsum += r
        csum += c
        tupleList.append((r,c))
    #print tupleList
    print "%.10f" % (getMinRank(tupleList))
    end = time.time()

    print "[Finished in %.3fs]" % (end-start)
