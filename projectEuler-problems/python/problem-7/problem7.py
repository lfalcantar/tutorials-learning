import time
import math

__author = 'luisitorey'

start_time = time.time()

limit = 10001
''' multiply  the limit:10001 by 11 to get approximate the number we need'''
array_size = 104750
boolean_list = [True] * array_size

num = 2
while num <= math.sqrt(array_size):
    for x in range(2, len(boolean_list)):
        if (not x == num) and x % num == 0:
            boolean_list[x] = False
    num += 1


# counter = 0
# for n in range(2,len(boolean_list)):
#     if boolean_list[n]:
#         counter += 1
#         print('prime :', n)
# print('counte : ', counter)

counter = 0
n = 1
while not counter == limit:
    n += 1
    if boolean_list[n]:
        counter += 1

print('The prime number :', limit, ' is :', n)
print('---  seconds ---', time.time() - start_time)

# seconds --- 7.072404861450195

exit(0)


#
# def prime(n):
#     I = range(2, int(n**0.5) + 1)
#     return not (n<2 or any(not(n%i) for i in I))
#
# M = 10001
# for i,p in enumerate(filter(prime,count()), 1):
#     if i == M:
#        print(i,"ième nombre premier = ",p)
#        break
