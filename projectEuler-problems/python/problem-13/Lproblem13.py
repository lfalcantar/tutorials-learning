import time

__author = 'luisitorey'


# input + process input + list +  create a file read to file and write to file

dimentions = input('Plese probide dimentions in fomat Z x Y \n')
dimen_split = dimentions.split("x")
x = int(dimen_split[0])
y = int(dimen_split[1])

num_list = []

user_input = 'none'
while not user_input.lower() == 'done':
    user_input = input("When you finish type done :\n")
    if not user_input.lower() == 'done':
        numbers = user_input.split(" ")
        for n in numbers:
            num = int(n)
            if (num >= 0) and (num < 100):
                num_list.append(num)

# print(type(num_list[0]))
print('The length of the list is :', len(num_list))

# create file and write to the file
file = open('text/grid.txt', 'w+')
for row in range(x*y):
    if (not row == 0) and row % x == 0:
        file.write('\n')
    file.write(str(num_list[row]) + ' ')