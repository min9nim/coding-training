def getMinRank(tupleList):
    global n,k,rsum,csum
    minrsum = rsum
    mincsum = csum
    if n == k :
        minrank = rsum / float(csum)
    else:
        for i in range(n-k):
            minrank = minrsum / float(mincsum)
            for i in range(len(tupleList)):
                newrsum = minrsum - tupleList[i][0]
                newcsum = mincsum - tupleList[i][1]
                newrank = newrsum / float(newcsum)
                if(minrank > newrank):
                    minrank = newrank
                    idx = i
            minrsum -= tupleList[idx][0]
            mincsum -= tupleList[idx][1]
            tupleList.pop(idx)
    return minrank

import sys
rl = lambda: sys.stdin.readline()
n = int(rl())

for i in range(n):
    tupleList = []
    input1 = rl().strip().split(" ")
    n = int(input1[0])
    k = int(input1[1])
    input2 = rl().strip().split(" ")
    rsum = 0
    csum = 0
    for i in range(0,len(input2),2):
        r = int(input2[i])
        c = int(input2[i+1])
        rsum += r
        csum += c
        tupleList.append((r,c))
    print "%.10f" % (getMinRank(tupleList))
