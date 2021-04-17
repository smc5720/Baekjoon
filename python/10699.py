from datetime import datetime

y = datetime.today().year
m = datetime.today().month
d = datetime.today().day

print(str(y) + "-0" + str(m) + "-" + str(d))
