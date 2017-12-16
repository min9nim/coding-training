def avoidObstacles(inputArray):
    maxnum = max(inputArray)
    for i in range(maxnum)[2:] :
        if all([j%i != 0 for j in inputArray]) :
            return i
    return maxnum + 1




print avoidObstacles([1,4,7,8,9])
