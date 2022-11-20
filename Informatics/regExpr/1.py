import re

s = "88877777777763150781111"
pattern = '(\d)\\1'
h = re.compile(pattern)
print(sorted(list(set(h.findall(s)))))

s = "КоРмА КоРкА КоРчмА"
pattern = "[А-ЯA-Z]"
h = re.compile(pattern)
print(list(set(h.findall(s))))
