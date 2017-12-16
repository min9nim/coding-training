def isIPv4Address(s):
    p = s.split('.')

    return len(p) == 4 and all([n.isdigit() and 0 <= int(n) < 256 for n in p])



print isIPv4Address("5.16.254.1")
