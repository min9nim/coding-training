def arrayChange(inputArray):
    res = 0
    for ix in range(len(inputArray)-1) :
        if inputArray[ix] >= inputArray[ix+1] :
            res = res + inputArray[ix] - inputArray[ix+1] + 1
            inputArray[ix+1] = inputArray[ix] + 1
    return res

print arrayChange([1, 1, 1])
