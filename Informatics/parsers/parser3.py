def f(XMLstr, JSONstr):
    counter = 0
    while XMLstr.find(">") != -1 and XMLstr.find("<") != -1:
        if XMLstr[XMLstr.find("<") + 1] not in {'/', "?"}:
            key = XMLstr[XMLstr.find("<") + 1:XMLstr.find(">")]
            if key.find(" ") != -1:
                key = key[:key.find(" ")]
            key += ">"
            if XMLstr.count("<" + key) > 1:
                strListOfMoreThan1 = ""
                while XMLstr.count("<" + key) != 0:                
                    strListOfMoreThan1 += "{" + f(XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)], "") + "}, "
                    XMLstr = XMLstr[XMLstr.find("</" + key) + len("</" + key):]
                JSONstr = '"' + key[:-1] + '" : [' + strListOfMoreThan1 + "], "

            elif XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)].find("</") == -1:
                JSONstr += '"' + key[:-1] + '": ' + '"' + XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)] + '", '
            elif XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)].find("</") != -1:
                JSONstr += '"' + key[:-1] + '": {' + f(XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)], "") + "}, "
            XMLstr = XMLstr[XMLstr.find("</" + key) + len("</" + key):]
            counter += 1
        else:
            XMLstr = XMLstr[XMLstr.find(">") + 1:]
    return JSONstr 
        

file1 = open("XML-file-schedule-Wed.xml")
XMLstr = file1.read()
JSONstr = ""
dkeys = dict()
print("{" + f(XMLstr, JSONstr) + "}")