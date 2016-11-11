import time
import math

__author = 'luisitorey'

start_time = time.time()

array_size = 2000000
boolean_list = [True] * array_size

num = 2
while num <= math.sqrt(array_size):
    print(num)
    for x in range(2, len(boolean_list)):
        if (not x == num) and x % num == 0:
            boolean_list[x] = False
    num += 1

sum = 0
for n in range(2, 2000000):
    print(n)
    if boolean_list[n]:
        sum += n

print('The sum is  :', sum)
print('---  seconds ---', time.time() - start_time)

# seconds --- 7.072404861450195

exit(0)
