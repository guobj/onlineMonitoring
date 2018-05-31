import urllib.request
import re
import urllib.error
import json
from bs4 import BeautifulSoup
import sched, time
import requests
from requests.exceptions import RequestException
# s = sched.scheduler(time.time, time.sleep)

def get_one_page():
    headers = {
        "Host": "sd.weather.com.cn",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0",
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Accept-Language": "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
        "Accept-Encoding": "gzip, deflate",
        "Referer": "http://www.sdqx.gov.cn/"
    }
    url = "http://sd.weather.com.cn/skjc/index.shtml"
    try:
        response = requests.get(url, headers=headers)
        response.encoding = 'utf-8'
        if response.status_code == 200:
            return response.text
        return None
    except RequestException as e:
        return None

def parse_one_page(html):
    pat1 = '<div class="navbox".+?</div>'
    res1 = re.compile(pat1, re.S).findall(html)

    pat2 = '<span>.+?</span>'
    res2 = re.compile(pat2, re.S).findall(res1[0])
    # print(res2)

    pat3 = '<a href="(.+?\.shtml)">(.+?)</a>'
    res3 = re.compile(pat3, re.S).findall(res2[0])
    print(res3)
    # print(len(res3))

    return  res3
    # print(res4)

def parse_area_page(result):
    resultLists = []
    for item in result:
        html = urllib.request.urlopen(item[0]).read().decode('utf-8')

        pattern1 = '<div class="forecastBox" id="forecastID">.+?</div>'
        res1 = re.compile(pattern1, re.S).findall(html)

        patttern2 = '<a title=".+?" href="(.+?\.shtml)" target=".+?">(.+?)</a>'
        res2 = re.compile(patttern2, re.S).findall(res1[0])
        resultLists.append(res2)
    print(resultLists)
    return resultLists

def parse_result_page(resultLists):

    result = {}
    for city in resultLists:
        for item in city:
            oneDayHtml = item[0].replace('/weather/','/weather1d/')
            html = urllib.request.urlopen(oneDayHtml).read().decode('utf-8')
            soup = BeautifulSoup(html,"html.parser")
            # div = soup.find_all(name='div',attrs={'class':'wpic'})
            # div = soup.select('.wpic div')
            # print(div)

            pat1 = '<div class="today clearfix" id="today">.+?<div id="weatherChart">'
            res1 = re.compile(pat1, re.S).findall(html)
            # print(res8)

            pat2 = '<script>(.+?)</script>'
            res2 = re.compile(pat2, re.S).findall(res1[0])

            # # finalResult = dict( = )
            result[item[1]] = res2[1].replace('\nvar observe24h_data = ','').replace(';\n','')
            # print(result)
    return result
if __name__ == '__main__':
    html = get_one_page()
    result = parse_one_page(html)
    resultLists = parse_area_page(result)
    result = parse_result_page(resultLists)
    print(json.dumps(result, ensure_ascii=False))
    # s.enter(10, 1, main, )
    # return result