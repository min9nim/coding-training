def addBorder(picture):
    cnt = len(picture[0]) + 2
    return ["*"*cnt] + map(lambda x : "*"+x+"*", picture) + ["*"*cnt]


print addBorder(["ab4c",
           "de2d"]);
