import sys
import urllib

rl = sys.stdin.readline
n = int(rl())

for i in range(n):
    uri = rl().strip()
    print(urllib.unquote(uri).decode())
