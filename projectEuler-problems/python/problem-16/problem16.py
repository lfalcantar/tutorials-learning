__author__ = 'luisitorey'

import time

start_time = time.time()

n = 2**1000
n_string = str(n) + ''

sum_dig = 0

for x in n_string:
    sum_dig += int(x)

print(sum_dig)

print(sum([int(i) for i in str(2**1000)]))


print('---  seconds ---',  time.time() - start_time)
# seconds --- 007855892181396484
exit(0)
