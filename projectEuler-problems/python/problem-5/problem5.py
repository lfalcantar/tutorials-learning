import time

__author__ = 'luisitorey'

start_time = time.time()


gd = False
num = 2520
while not gd:
    if num % 1 == 0 and num % 2 == 0 and num % 3 == 0 and num % 4 == 0 and num % 5 == 0 and num % 6 == 0 and num % 7 == 0 and num % 8 == 0 and num % 9 == 0 and num % 10 == 0 and num % 11 == 0 and num % 12 == 0 and num % 13 == 0 and num % 14 == 0 and num % 15 == 0 and num % 16 == 0 and num % 17 == 0 and num % 18 == 0 and num % 19 == 0 and num % 20 == 0:
        print(num)
        gd = False
    num += 1


print('---  seconds ---',  time.time() - start_time)
# seconds --- 007855892181396484

exit(0)
