def isLucky(n):
    strNum = str(n)
    digit = len(strNum)

    str1 = strNum[:digit>>1]
    str2 = strNum[digit>>1:]

    arr1 = [int(char) for char in str1]
    arr2 = [int(char) for char in str2]

    if sum(arr1) ==  sum(arr2) :
        return True
    else :
        return False


print(isLucky(1230))
