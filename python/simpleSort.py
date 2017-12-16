def simpleSort(arr):

    n = len(arr)

    for i in range(n):
        j = 0
        stop = n - i
        while j < stop - 1:
            if arr[j] > arr[j + 1]:
               arr[j], arr[j + 1] = arr[j+1], arr[j]
            j += 1
    return arr


print simpleSort([5,6,13,2,4,7,3,12,2,16,8,1])
