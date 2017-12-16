n = 8
visit = []
adj = []


def DFS(v):
    global n, adj, visit

    visit.append(v)
    for i in range(1,n+1):
        if (adj.count((v,i)) > 0 and visit.count(i) == 0):
            print("%d => %d" % (v , i));
            DFS(i)



def main():
    global adj
    start = 1
    input = [1, 2, 1, 3, 2, 4, 2, 5, 4, 8, 5, 8, 3, 6, 3, 7, 6, 8, 7, 8, -1, -1]
    ix = 0
    while True :
        v1 = input[ix]
        ix += 1
        v2 = input[ix]
        ix += 1
        if v1 == -1 and v2 == -1 :
            break
        adj.append((v1,v2))
        adj.append((v2,v1))

    DFS(start)

main()
