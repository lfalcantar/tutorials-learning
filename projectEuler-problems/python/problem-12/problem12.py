import time
import math
import re

__author = 'luisitorey'

start_time = time.time()

prime_numbers = [2, 3, 5, 7, 11, 13, 17, 19, 21, 23, 29]


def next_prime(num) -> int:
    gd = True
    while gd:
        if not re.match(r"^1?$|^(11+?)\1+$", "1" * num):
            gd = False
        else:
            num += 1
    return num


def find_multiples(n) -> int:
    dictionary = {}
    i = 0
    count = 1
    while n > 1:
        if i >= len(prime_numbers):
            prime_numbers.append(next_prime(prime_numbers[i - 1] + 1))

        elif n % prime_numbers[i] == 0:
            if str(prime_numbers[i]) not in dictionary:
                dictionary.update({str(prime_numbers[i]): 1})
            else:
                dictionary[str(prime_numbers[i])] += 1
            n /= prime_numbers[i]
        else:
            i += 1

    for value in dictionary:
        count *= dictionary[value] + 1
    return count


def main():
    guardian = False
    n = 0
    while not guardian:
        n += 1
        triangle_n = n * (n + 1) / 2
        if find_multiples(triangle_n) > 500:
            guardian = False
            print('eureka', triangle_n)
main()

print('---  seconds ---', time.time() - start_time)
exit(0)
