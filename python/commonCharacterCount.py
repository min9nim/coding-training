def commonCharacterCount(s1, s2) :
    res = 0
    arr1, arr2 = [], []
    for char in s1 :
        arr1.append(char)
    for char in s2 :
        arr2.append(char)
    arr1.sort()
    arr2.sort()

    arr1len = len(arr1)
    ix = 0
    while ix < arr1len :
        if arr2.count(arr1[ix]) > 0 :
            if arr2.count(arr1[ix]) > arr1.count(arr1[ix]) :
                comCnt = arr1.count(arr1[ix])
            else :
                comCnt = arr2.count(arr1[ix])
            ix = ix + arr1.count(arr1[ix])
            res = res + comCnt
        else :
            ix = ix + 1
    return res


def commonCharacterCount2(s1, s2):
    print [s1 for i in set(s1)]



print(commonCharacterCount2( "aabcc", "adcaa"))
