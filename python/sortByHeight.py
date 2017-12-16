def sortByHeight(a):
    treecnt = a.count(-1)
    treeidx = []
    start = 0
    for ix in range(treecnt) :
        tmp = a.index(-1,start)
        treeidx.append(tmp)
        start = tmp + 1
    for ix in range(treecnt) :
        a.remove(-1)
    a.sort()
    for ix in range(treecnt) :
        a.insert(treeidx[ix], -1)
    return a


def sortByHeight2(a):
    arr = [i for i in a if i > 0]
    print(arr)
    l = sorted(arr)
    for n,i in enumerate(a):

        if i == -1:
            l.insert(n,i)
    return l


a = [-1, 150, 190, 170, -1, -1, 160, 180]
print(sortByHeight2(a))
