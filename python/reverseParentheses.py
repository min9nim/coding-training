def reverseParentheses(s):
    end = s.find(")")
    if end == -1 :
        return s
    else:
        start = s.rfind("(",0,end)
        res = s[:start] + s[start+1:end][::-1] + s[end+1:]
        if res.find("(") == -1 :
            return res
        else :
            return reverseParentheses(res)


print reverseParentheses("a(bcdefghijkl(mno)p)q")
