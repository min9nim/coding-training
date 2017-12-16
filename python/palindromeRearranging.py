def palindromeRearranging(inputString):
    odd_cnt = 0
    for item in list(set(inputString)) :
        if inputString.count(item) % 2 == 1 :
            odd_cnt = odd_cnt + 1
            if odd_cnt > 1 :
                return False
    return True
