from takprotobuf import xmlToProto

XMLfile = open("src/XML/XML-file-schedule-Wed.xml")
print(xmlToProto(XMLfile.read()))