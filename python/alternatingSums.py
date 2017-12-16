def alternatingSums(a):
    sum = [0,0]
    ix = 0
    while ix < len(a) :
        if ix%2 == 1 :
            sum[1] = sum[1] + a[ix]
        else :
            sum[0] = sum[0] + a[ix]
        ix = ix + 1
    return sum


print alternatingSums([50, 60, 60, 45, 70])
