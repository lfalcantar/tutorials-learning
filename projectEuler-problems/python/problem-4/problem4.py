import time

__author__ = 'luisitorey'

start_time = time.time()

'''
#option one****************************
x = 999
palindrome = 0
z = 0

while x > 99:
    y = 999
    while y > 99:
        palindrome = x * y
        if str(palindrome) == str(palindrome)[::-1]:
            z = max(palindrome, z)
        y -= 1
    x -= 1

print(z)


print('---  seconds ---',  time.time() - start_time)

# seconds --- 0.9049408435821533
'''


# option two******************************
max([x*y for x in range(900,1000) for y in range(900,1000) if str(x*y) == str(x*y)[::-1]])
# seconds --- 007855892181396484
print('---  seconds ---',  time.time() - start_time)



exit(0)




