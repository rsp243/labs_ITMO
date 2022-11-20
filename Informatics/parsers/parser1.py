# def doJSON(JSONstr, XMLstr, keys, iterator, missed):
#     if iterator >= len(keys):
#         return JSONstr
#     # if XMLstr[XMLstr.find("<" + keys[iterator] + ">") + len("<" + keys[iterator] + ">") + 1:XMLstr.find("</" + keys[iterator] + ">")].find("<") == -1:
#     #     JSONstr += '"' + keys[iterator] + '": ' + XMLstr[XMLstr.find("<" + keys[iterator] + ">") + len("<" + keys[iterator] + ">"):XMLstr.find("</" + keys[iterator] + ">")] + ", "
#     #     XMLstr = XMLstr[:XMLstr.find("<" + keys[iterator] + ">") - 1] + XMLstr[XMLstr.find("</" + keys[iterator] + ">") + len("</" + keys[iterator] + ">"):]
#     # elif JSONstr != "" and len(missed) > 0: # Добавить проверку на то чтобы вставлялось после нахождения нового тегай
#     #     JSONstr = JSONstr[::-1]
#     #     JSONstr += ('"' + missed[-1] + '": { ')[::-1]
#     #     JSONstr = JSONstr[::-1]
#     #     JSONstr += "}"
#     #     missed.pop(-1)
#     # else:
#     #     missed.append(keys[iterator])
#     print(JSONstr)
#     doJSON(JSONstr, XMLstr, keys, iterator + 1, missed)

def doJSON(JSONstr, XMLstr, keys, iterator):
    if iterator >= len(keys):
        return JSONstr
    if XMLstr[XMLstr.find("<" + keys[iterator] + ">") + len("<" + keys[iterator] + ">") + 1:XMLstr.find("</" + keys[iterator] + ">")].find("<") == -1:
        JSONstr += '"' + keys[iterator] + '": ' + XMLstr[XMLstr.find("<" + keys[iterator] + ">") + len("<" + keys[iterator] + ">"):XMLstr.find("</" + keys[iterator] + ">")] + ", \n"
        XMLstr = XMLstr[:XMLstr.find("<" + keys[iterator] + ">") - 1] + XMLstr[XMLstr.find("</" + keys[iterator] + ">") + len("</" + keys[iterator] + ">"):]
        print("JSON = " + JSONstr)
        print("XML = " + XMLstr)
    # elif :
    #     XMLstr = 
    doJSON(JSONstr, XMLstr, keys, iterator + 1)

# Variant = 13, XML -> JSON, Wednesday
file1 = open("XML-file-schedule-Wed.xml")
XMLstr = file1.read()
# Search of keys
XMLforKeys = XMLstr
keys = []
while XMLforKeys.find(">") != -1:
    if XMLforKeys[XMLforKeys.find("<") + 1] not in {'/', "?"}:
        keys.append(XMLforKeys[XMLforKeys.find("<") + 1:XMLforKeys.find(">")])
    XMLforKeys = XMLforKeys[XMLforKeys.find(">") + 1:]
JSONstr = ''
iterator = 0
missed = [""]
print(doJSON(JSONstr, XMLstr, keys, iterator))