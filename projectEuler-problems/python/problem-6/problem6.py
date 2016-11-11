import time

__author__ = 'luisitorey'

start_time = time.time()

n = 100

nsum = (n * (n + 1)) / 2
nsum **= 2
print('The sum 1 .. ', n,' is ', nsum)

n_square_sum = (n/6)*((n+1)*(2*n + 1))
print('The sum 1 .. ', n, '^2 is ', n_square_sum)

n_total = nsum - n_square_sum
print('The total is :r',n_total)

print('---  seconds ---',  time.time() - start_time)
# seconds --- 007855892181396484

exit(0)
