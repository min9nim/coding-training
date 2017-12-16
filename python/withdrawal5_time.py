def getMinRank(tupleList):
    global n,k,rsum,csum
    minrsum = rsum
    mincsum = csum
    if n == k :
        minrank = rsum / float(csum)
    else:
        minrank = minrsum / float(mincsum)
        rankList = []
        for i in range(len(tupleList)):
            newrsum = minrsum - tupleList[i][0]
            newcsum = mincsum - tupleList[i][1]
            rankList.append((tupleList[i],newrsum / float(newcsum)))
        rankList.sort(key=lambda tup: tup[1])
        for i in range(k):
            minrsum -= rankList[i][0][0]
            mincsum -= rankList[i][0][1]
        minrank = minrsum / float(mincsum)
    return minrank

import time
import sys
rl = lambda: sys.stdin.readline()
n = int(rl())

for i in range(n):
    tupleList = []
    input1 = rl().strip().split(" ")
    n = int(input1[0])
    k = int(input1[1])
    print n,k
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
    #print "init: ", rsum, csum
    print "%.10f" % (getMinRank(tupleList))
    end = time.time()

    print "[Finished in %.3fs]" % (end-start)
