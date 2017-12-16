import sys
rl = sys.stdin.readline
n = int(rl())

def getMinRank(n,k,tupleList):
    if(n == k):
        minRank = cumulativeRank(tupleList)
    else:
        minRank = 1
        for i in range(n):
            tmp = tupleList.pop(i)
            rank = getMinRank(n-1, k, tupleList)
            tupleList.insert(i,tmp)
            if(minRank > rank):
                minRank = rank
    return minRank

def cumulativeRank(tupleList):
    rsum = 0.0
    csum = 0.0
    for tu in tupleList:
        r,c = tu
        rsum += r
        csum += c
    return rsum/csum

for i in range(n):
    tupleList = []
    input1 = rl().strip().split(" ")
    n = int(input1[0])
    k = int(input1[1])
    input2 = rl().strip().split(" ")
    for i in range(0,len(input2),2):
        tupleList.append((int(input2[i]), int(input2[i+1])))
    print("%.10f" % (getMinRank(n,k,tupleList)))

    
