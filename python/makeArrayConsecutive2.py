def makeArrayConsecutive2(statues):
    statues.sort()
    res = 0
    cnt = len(statues)
    if cnt == 1 :
        return 0
    for ix in range(cnt-1) :
        diff = statues[ix+1] - statues[ix]
        if(diff > 1) :
            res = res + diff - 1
    return res


print(makeArrayConsecutive2([6, 2, 3, 8]))
