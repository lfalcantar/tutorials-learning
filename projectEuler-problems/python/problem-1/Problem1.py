import time

__author__ = 'luisitorey'

start_time = time.time()

limit = 1000
sum = 0

while limit > 1:
    limit = limit - 1
    if (limit % 3 == 0) or (limit % 5 == 0):
        sum += limit
print(sum)

print('--- %s seconds ---',  time.time() - start_time)

#seconds --- 0.0066

exit(0)