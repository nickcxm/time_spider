from bs4 import BeautifulSoup
from urllib import request
from urllib import parse
import sys

url = "http://srh.bankofchina.com/search/whpj/search.jsp"
Form_Data = {}
Form_Data['erectDate'] = ''
Form_Data['nothing'] = ''
# Form_Data['pjname'] = '1316'
data=[1,2];
    
i=sys.argv[1];    
def func(i):
    Form_Data['pjname']=i;
    data = parse.urlencode(Form_Data).encode('utf-8')
    html = request.urlopen(url,data).read()
    soup = BeautifulSoup(html,'html.parser')

    div = soup.find('div', attrs = {'class':'BOC_main publish'})
    table = div.find('table')
    tr = table.find_all('tr')
    td = tr[1].find_all('td')
    try :
        print(td[0].get_text(),td[7].get_text(),td[3].get_text())
        # return td[3].get_text();
    except IndexError:
    #    return -1;
        print (-1)

func(i);

