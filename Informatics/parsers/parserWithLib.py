import json
import xmltodict
import time

XMLfile = open("src/XML/XML-file-schedule-Wed.xml")
start_time = 0
dataDict = xmltodict.parse(XMLfile.read())
XMLfile.close()
JSONdata = json.dumps(dataDict, ensure_ascii=False)
JSONfile = open("src/JSON/JSONout2(lib).json", "w+")
print(f'time: {time.process_time_ns()-start_time} ns')
print(JSONdata)
JSONfile.write(JSONdata)
JSONfile.close()