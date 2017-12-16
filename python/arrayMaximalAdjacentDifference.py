def arrayMaximalAdjacentDifference(inputArray):
    return max([abs(inputArray[i]-inputArray[i+1]) for i in range(len(inputArray)-1)])


print arrayMaximalAdjacentDifference([-1, 4, 10, 3, -2])
