import time

__author__ = 'luisitorey'

start_time = time.time()

fib1 = 1
fib2 = 1
sum  = 1

while (fib1 + fib2) < 4000000:
    temp = fib1
    fib1 = fib2
    fib2 = temp + fib2
    if fib2 % 2 == 0:
        sum += fib2
        print(fib2)

print(sum)

print('--- %s seconds ---',  time.time() - start_time)

#seconds --- 0.0066

exit(0)



