def matrixElementsSum(matrix):
    totsum = 0
    for ix in range(len(matrix[0])) :
        totsum = totsum + sumOfCol(ix, matrix)
    return totsum

def sumOfCol(colidx, matrix) :
    res = 0;
    for rowidx in range(len(matrix)) :
        if matrix[rowidx][colidx] == 0 :
            break
        res = res + matrix[rowidx][colidx]
    return res

matrix = [[0, 1, 1, 2],
          [0, 5, 0, 0],
          [2, 0, 3, 3]]

print(matrixElementsSum(matrix))          
