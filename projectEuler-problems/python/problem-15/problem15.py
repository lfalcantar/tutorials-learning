import time

__author = 'luisitorey'

start_time = time.time()

n = 21

matrix = [[1]*n for x in range(n)]


for i in range(n-1):
    for j in range(1, n):
            print(matrix[i][j], ' + ', matrix[i+1][j-1], '=', (matrix[i][j] + matrix[i+1][j-1]))
            matrix[i+1][j] = matrix[i][j] + matrix[i+1][j-1]


print(matrix[20][20])


print('---  seconds ---',  time.time() - start_time)
# seconds --- 0.004289865493774414
exit(0)
