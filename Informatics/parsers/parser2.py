def f(dkeys, position, endPosition, value):
    current = 0
    for i in dkeys.keys():
        if position <= current <= endPosition + 1:
            dkeys[i] //= value
        current += 1
    return dkeys

def mainFunction(dkeys, JSONstr, XMLstr):
    counter = 0
    for i in dkeys.keys():
        counter += 1
        extraJSON = ""
        if dkeys[i] > 1:
            # Алгоритм поиска посследнего тега перед оболочкой
            key = i + ">"
            XMLclosed = XMLstr[XMLstr.find("<" + key) + len("<" + key):XMLstr.find("</" + key)]
            endWord = XMLclosed[XMLclosed.rfind("</") + 2:XMLclosed.rfind(">")]
            # Searching its position in dictionary dkeys
            endPosition = 0
            for i in dkeys.keys():
                if i == endWord:
                    break
                endPosition += 1
            dkeys = f(dkeys, counter, endPosition, dkeys[i])
            # Taking new dkeys with new counts of each key in it
            JSONstr += '"' + i + '": [ { '
            # extraJSON += mainFunction(dkeys[i:], )
        else:
            if dkeys[i] == 1 and XMLstr[XMLstr.find("<" + i + ">") + len("<" + i + ">"):XMLstr.find("</" + i + ">")].find("</") != -1: # Написать условие формаирования ключ - значение, ключ - вкладыщ 
                JSONstr += '"' + i + '": { '
            elif dkeys[i] == 1 and XMLstr[XMLstr.find("<" + i + ">") + len("<" + i + ">"):XMLstr.find("</" + i + ">")].find("</") == -1:
                JSONstr += '"' + i + '": ' + '"' + XMLstr[XMLstr.find("<" + i + ">") + len("<" + i + ">"):XMLstr.find("</" + i + ">")] + '", '

    return JSONstr
        # Написать алгоритм, который будет закрывать конструкции в JSON
        # Написать алгоритм, позволяющий делать разделение на несколько конструкций внутри {}

file1 = open("XML-file-schedule-Wed.xml")
XMLstr = file1.read()
XMLforKeys = XMLstr
JSONstr = "{"
dkeys = dict()
while XMLforKeys.find(">") != -1:
    if XMLforKeys[XMLforKeys.find("<") + 1] not in {'/', "?"}:
        key = XMLforKeys[XMLforKeys.find("<") + 1:XMLforKeys.find(">")].split()[0]
        if key not in dkeys.keys():
            dkeys[key] = 1
        else:
            dkeys[key] += 1
    XMLforKeys = XMLforKeys[XMLforKeys.find(">") + 1:]
JSONstr = mainFunction(dkeys, JSONstr, XMLstr)
