__author = 'luisitorey'

import time

start_time = time.time()


def odd(n: int) -> int:
    return 3*n + 1


def even(n: int) -> int:
    return n/2


def collatz(n: int) -> int:
    list_num = [1]
    while not n == 1:
        list_num.append(n)
        if n % 2 == 0:
            n = even(n)
        else:
            n = odd(n)
    return len(list_num)


def main():
    max_number = 0
    result = 1
    for x in range(1, 1000000):
        print(x)
        len_list = collatz(x)
        if len_list > max_number:
            max_number = len_list
            result = x
    print(result)
main()






print('---  seconds ---',  time.time() - start_time)
exit(0)

