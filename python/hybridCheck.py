import requests

#url = "https://localhost:8443/5.0_20170407/websquare/config.xml"
#url = "http://localhost:8080/5.0_20170407/websquare/config.xml"

#url = "http://localhost:8080/hybrid/inAppUpdate.wq"
#url = "https://localhost:8443/hybrid/inAppUpdate.wq"
#payload = '{"appid":"TEMPLATE","method":"getSecurityRuleMetrix"}'

url = 'https://kh.shinhanglobal.com/inAppUpdate.wq'
payload = '{"appid":"Sbank2.0_KH","method":"getSecurityRuleMetrix"}'


res = requests.post(url, data=payload, verify=False)
#res = requests.get(url, verify=False)
print "*****************************"
if res.content == "" :
    print "x"
else :
    print res.content
print "*****************************"
