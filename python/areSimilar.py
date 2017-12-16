def areSimilar(a, b):
    if a == b :
        return True
    c = []
    for ix in range(len(a)):
        if a[ix] == b[ix] :
            c.append("s")
        else :
            c.append(ix)
    ix_arr = [item for item in c if item != "s"]
    if len(ix_arr) == 2:
        if (a[ix_arr[0]] == b[ix_arr[1]]) and (a[ix_arr[1]] == b[ix_arr[0]]) :
            return True

    return False



a = [1, 1, 4]
b = [1, 2, 3]



print areSimilar(a, b)
