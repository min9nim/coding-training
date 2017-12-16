def cumulativeRank(tupleList):
    rsum = 0.0
    csum = 0.0
    for tu in tupleList:
        r,c = tu
        rsum += r
        csum += c
    return rsum/csum

def getMinRank(tupleList):
    global n,k
    if n == k :
        minRank = cumulativeRank(tupleList)
    else:
        rankList = []
        for i in range(n):
            tmp = tupleList.pop(i)
            rankList.append((i,cumulativeRank(tupleList),tmp))
            tupleList.insert(i, tmp)
        rankList.sort(key=lambda tup: tup[1])
        print rankList
        for i in range(n-k):
            tupleList.remove(rankList[n-1-i][2])
        minRank = cumulativeRank(tupleList)
    return minRank


import sys
rl = lambda: sys.stdin.readline().strip()
n = int(rl())

for i in range(n):
    tupleList = []
    input1 = rl().split(" ")
    n = int(input1[0])
    k = int(input1[1])
    #print n, k
    input2 = rl().split(" ")
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
