__author__ = 'luisitorey'

import math

num = math.factorial(100)
num_s = str(num)

sum_f = 0
for i in range(len(num_s)):
    sum_f += int(num_s[i])

print(sum_f)