def recurCreating(XMLstr, JSONstr):
    while XMLstr.find("<") != -1 and XMLstr.find(">") != -1:
        posOpenTrBr = XMLstr.find("<")
        posClosedTrBr = XMLstr.find(">")
        if XMLstr[posOpenTrBr + 1] not in {'/', "?"}:
            key = XMLstr[posOpenTrBr + 1:posClosedTrBr]
            if key.find(" ") != -1:
                key = key[:key.find(" ")]
            key += ">"
            posOpenKey = XMLstr.find("<" + key)
            lenOpenKey = len("<" + key)
            posClosedKey = XMLstr.find("</" + key)
            if XMLstr.count("<" + key) > 1:
                strListOfMoreThan1 = "" 
                while XMLstr.count("<" + key) != 0:                
                    strListOfMoreThan1 += "{" + recurCreating(XMLstr[posOpenKey + lenOpenKey:posClosedKey], "") + "}, "
                    XMLstr = XMLstr[posClosedKey + len("</" + key):]
                JSONstr = '"' + key[:-1] + '" : [' + strListOfMoreThan1 + "], "
            elif XMLstr[posOpenKey + lenOpenKey:posClosedKey].find("</") == -1:
                JSONstr += '"' + key[:-1] + '": ' + '"' + XMLstr[posOpenKey + lenOpenKey:posClosedKey] + '", '
            else:
                JSONstr += '"' + key[:-1] + '": {' + recurCreating(XMLstr[posOpenKey + lenOpenKey:posClosedKey], JSONstr="") + "}, "
            XMLstr = XMLstr[posClosedKey + len("</" + key):]
        else:
            XMLstr = XMLstr[posClosedTrBr + 1:]
    return JSONstr 


def deletingCommas(JSONstr):
    return JSONstr.replace("}, }", "} }").replace('", }', '" }').replace("], }", "] }").replace('", ]', '" ]').replace("}, ]", "} ]")

def XMLtoJSON(XMLstr):
    emptyJSON = ""
    return "{" + recurCreating(XMLstr, emptyJSON) + "}"

file1 = open("XML-file-schedule-Wed.xml")
XMLstr = file1.read()
file1.close()
file2 = open("JSONout.json", "w+")
result = deletingCommas(XMLtoJSON(XMLstr))
file2.write(result)
print(result)
file2.close()