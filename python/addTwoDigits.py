import math

def addTwoDigits(n):
    n1 = math.floor(n/10)
    n2 = n%10
    return n1+n2

print(addTwoDigits(76))
