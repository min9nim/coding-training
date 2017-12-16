def isIPv4Address(inputString):
    arr = inputString.split(".")
    if len(arr) != 4 :
        return False
    for i in range(len(arr)) :
        if arr[i].isdigit() == False :
            return False
        if int(arr[i]) < 0 or int(arr[i]) > 255 :
            return False
    return True



print isIPv4Address("5.16.254.1")
