from symbol import break_stmt
import time

__author = 'luisitorey'

start_time = time.time()

for a in range(200, 999):
    for b in range(a+1, 999):
        for c in range(b+1, 999):
            print(a, ' ', b, ' ', c)
            if a**2 + b**2 == c**2 and c+a+b == 1000 and (a<b) and (b<c):
                print(a, '*', b, '*', c, '=', (a*b*c))
                input("lo encontramos")

print('---  seconds ---',  time.time() - start_time)
# seconds --- 007855892181396484

exit(0)
