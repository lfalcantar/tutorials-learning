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


start_time = time.time()
# find max value
max_value = 0
range_size = x * y
for n in range(range_size):
    if num_list[n] > 0:
        print('The value :', n, '\n')
    # check Down
        if n + (x * 3) < range_size:
            sum_product = num_list[n] * num_list[n + (x * 1)] * num_list[n + (x * 2)] * num_list[n + (x * 3)]
            print(sum_product)
            print('Down:', num_list[n], '*', num_list[n + (x * 1)], '*', num_list[n + (x * 2)], '*', num_list[n + (x * 3)], '\n')
            if sum_product > max_value:
                max_value = sum_product

    # check front
        if n + 3 < range_size and n + 3 < ((n / x) + 1) * x:
            sum_product = num_list[n] * num_list[n + 1] * num_list[n + 2] * num_list[n + 3]
            print(sum_product)
            print('Front:', num_list[n], '*', num_list[n + 1], '*', num_list[n + 2], '*', num_list[n + 3], '\n')
            if sum_product > max_value:
                max_value = sum_product

    # check diagonal right
        if (n+(x+1)) * 3 <= range_size:
            sum_product = num_list[n] * num_list[n + (x+1)] * num_list[n + (x+1) * 2] * num_list[n + (x+1) * 3]
            print(sum_product)
            print('Dig-R :', num_list[n], '*', num_list[n + (x+1)], '*', num_list[n + (x+1) * 2], '*', num_list[n + (x+1) * 3], '\n')
            if sum_product > max_value:
                max_value = sum_product

    # check diagonal left
        if (n + (x - 1)) * 3 < range_size:
            sum_product = num_list[n] * num_list[n + (x - 1)] * num_list[n + (x - 1) * 2] * num_list[n + (x - 1) * 3]
            print(sum_product)
            print('Dig-L', num_list[n], '*', num_list[n + (x - 1)], '*', num_list[n + (x - 1) * 2], '*', num_list[n + (x - 1) * 3], '\n')
            if sum_product > max_value:
                max_value = sum_product

print(max_value)
print('---  seconds ---',  time.time() - start_time)
# seconds --- 007855892181396484

exit(0)

'''
Modes	Description
r	Opens a file for reading only. The file pointer is placed at the beginning of the file. This is the default mode.
rb	Opens a file for reading only in binary format. The file pointer is placed at the beginning of the file. This is the default mode.
r+	Opens a file for both reading and writing. The file pointer will be at the beginning of the file.
rb+	Opens a file for both reading and writing in binary format. The file pointer will be at the beginning of the file.
w	Opens a file for writing only. Overwrites the file if the file exists. If the file does not exist, creates a new file for writing.
wb	Opens a file for writing only in binary format. Overwrites the file if the file exists. If the file does not exist, creates a new file for writing.
w+	Opens a file for both writing and reading. Overwrites the existing file if the file exists. If the file does not exist, creates a new file for reading and writing.
wb+	Opens a file for both writing and reading in binary format. Overwrites the existing file if the file exists. If the file does not exist, creates a new file for reading and writing.
a	Opens a file for appending. The file pointer is at the end of the file if the file exists. That is, the file is in the append mode. If the file does not exist, it creates a new file for writing.
ab	Opens a file for appending in binary format. The file pointer is at the end of the file if the file exists. That is, the file is in the append mode. If the file does not exist, it creates a new file for writing.
a+	Opens a file for both appending and reading. The file pointer is at the end of the file if the file exists. The file opens in the append mode. If the file does not exist, it creates a new file for reading and writing.
ab+	Opens a file for both appending and reading in binary format. The file pointer is at the end of the file if the file exists. The file opens in the append mode. If the file does not exist, it creates a new file for reading and writing.
'''
