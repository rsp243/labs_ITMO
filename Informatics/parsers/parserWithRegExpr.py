import re

def searching_key(XMLstr):
    pattern_key = "<[A-z]+>"
    key = re.compile(pattern_key).search(XMLstr).group(0)
    pattern_closed_key = "<" + "\/" + key[1:]
    if len(re.compile(pattern_closed_key).findall(XMLstr)) == 0:
        print("Исходный XML файл не корректен, измените его и перезапустите программу.")
        exit(0)
    return key[1:].lstrip()

def recurCreating(XMLstr, JSONstr):
    while XMLstr.find("<") != -1 and XMLstr.find(">") != -1:
        posOpenTrBr = XMLstr.find("<")
        posClosedTrBr = XMLstr.find(">")
        if XMLstr[posOpenTrBr + 1] not in {'/', "?"}:
            key = searching_key(XMLstr)
            if key.find(" ") != -1:
                key = key[:key.find(" ")]
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
    return JSONstr.replace("}, }", "}}").replace('", }', '"}').replace("], }", "]}").replace('", ]', '"]').replace("}, ]", "}]")

def XMLtoJSON(XMLstr):
    emptyJSON = ""
    return "{" + recurCreating(XMLstr, emptyJSON) + "}"

file1 = open("src/XML/XML-file-schedule-Wed.xml")
XMLstr = file1.read()
file1.close()
file2 = open("src/JSON/JSONout3(regexpr).json", "w+")
result = deletingCommas(XMLtoJSON(XMLstr))
file2.write(result)
print(result)
file2.close()