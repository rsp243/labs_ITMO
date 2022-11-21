import json
import xmltodict

XMLfile = open("src/XML/XML-file-schedule-Wed.xml")
dataDict = xmltodict.parse(XMLfile.read())
XMLfile.close()
print(dataDict)
JSONdata = json.dumps(dataDict, ensure_ascii=False)
JSONfile = open("src/JSON/JSONout2(lib).json", "w+")
print(JSONdata)
JSONfile.write(JSONdata)
JSONfile.close()