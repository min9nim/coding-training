def areEquallyStrong(yourLeft, yourRight, friendsLeft, friendsRight):
    if yourLeft + yourRight == friendsLeft + friendsRight :
        return (yourLeft-yourRight)**2 == (friendsLeft-friendsRight)**2
    return False



print areEquallyStrong(10,15,5,20)
