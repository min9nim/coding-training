def baseConversion(n, x):
    return str(hex(int(n,x)))[2:]

print baseConversion("337", 8)
