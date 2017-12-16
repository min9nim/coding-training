def adjacentElementsProduct(inputArray):
    cnt = len(inputArray)
    if cnt == 2 :
        return inputArray[0]*inputArray[1]
    max = -50000
    for ix in range(cnt) :
        prod = inputArray[ix] * inputArray[ix+1]
        if prod > max :
            max = prod
        if ix+1 == cnt-1 :
            break
    return max

input = [5, 1, 2, 3, 1, 4]
print(adjacentElementsProduct(input))
