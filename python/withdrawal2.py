def cumulativeRank(tupleList):
    rsum = 0.0
    csum = 0.0
    for tu in tupleList:
        r,c = tu
        rsum += r
        csum += c
    return rsum/csum

def getMinRank(tupleList):
    if n == k :
        minrank = cumulativeRank(tupleList)
    else:
        for i in range(n-k):
            minrank = cumulativeRank(tupleList)
            for i in range(len(tupleList)):
                tmp = tupleList.pop(i)
                newrank = cumulativeRank(tupleList)
                if(minrank > newrank):
                    minrank = newrank
                    maxrankidx = i
                tupleList.insert(i, tmp)
            print minrank, tupleList.pop(maxrankidx)
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
    for i in range(0,len(input2),2):
        tupleList.append((int(input2[i]), int(input2[i+1])))
    print "%.10f" % (getMinRank(tupleList))
