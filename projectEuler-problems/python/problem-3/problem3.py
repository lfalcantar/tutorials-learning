import time

__author__ = 'luisitorey'

start_time = time.time()


def is_prime(num):
    if num == 2:
        return True
    elif num % 2 == 0 or num < 2:
        return False
    else:
        i = num - 1
        while i > 2:
            if num % i == 0:
                return False
            i -= 1
        return True

num = 600851475143
gd = False
i = 2
list_ = []
while not gd:
    if num == 1:
        gd = True
    else:
        if num % i == 0:
            num /= i
            list_.append(i)
    i += 1

for x in list_:
    if is_prime(x):
        print(x)

print('--- %s seconds ---',  time.time() - start_time)

#seconds --- 0.0066

exit(0)