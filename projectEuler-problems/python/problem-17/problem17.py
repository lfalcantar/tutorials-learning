__author__ = 'LuisistoRey'

#run one time and  iterate with others
decimals = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
tens = ['ten','eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
cents = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']

#1 - 99
#first 1-9
sum_letters = 0
for x in decimals:
    print(x)
    sum_letters += len(x)
print('1 - 9 :', sum_letters)

#10 - 19
for x in tens:
    print(x)
    sum_letters += len(x)
print('1-19 :', sum_letters)

#20 - 99
for x in cents:
    print(x)
    sum_letters += len(x)
    for z in decimals:
        print(x,' ',z)
        sum_letters += len(x) #twenty
        sum_letters += len(z) #one

print('1-99 :', sum_letters)\

#100 - 999 without n10-n19
for x in decimals:
    print(x,' hundred')
    sum_letters += len(x)
    sum_letters += len('hundred')
    for z in cents:
        print(x,' hundred',' and',z)
        sum_letters += len(x)
        sum_letters += len('hundred')
        sum_letters += len('and')
        sum_letters += len(z)
        for y in decimals:
            print(x,' hundred',' and', z,' ',y)
            sum_letters += len(x)
            sum_letters += len('hundred')
            sum_letters += len('and')
            sum_letters += len(z)
            sum_letters += len(y)

#n10 - n20
for x in decimals:
    for z in tens:
        print(x,'hundred and',z)
        sum_letters += len(x)
        sum_letters += len('hundred')
        sum_letters += len('and')
        sum_letters += len(z)

#n01-n09
for x in decimals:
    for z in decimals:
        print(x,'hundred and',z)
        sum_letters += len(x)
        sum_letters += len('hundred')
        sum_letters += len('and')
        sum_letters += len(z)


#misiing tousand and 1-19
sum_letters += len('one')
sum_letters += len('thousand')


#thousand
print('Total :', sum_letters)






