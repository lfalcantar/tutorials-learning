import time

__author = 'luisitorey'

start_time = time.time()


def find_sum_d(num):
    return sum([y for y in range(1, num) if num % y == 0])


sumAll = 0
for x in range(10001):
    temp = find_sum_d(x)
    if (not temp == x) and x == find_sum_d(temp):
        print(x)
        sumAll += x
print(sumAll)




print('---  seconds ---',  time.time() - start_time)
exit(0)
