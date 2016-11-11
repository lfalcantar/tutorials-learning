#----------------------------------------------
# Conway's Game of Life
# More programs at UsingPython.com/programs
#
#----------------------------------------------
from __future__ import print_function
from future.builtins import input
import PIL
from PIL import Image, ImageTk
from Tkinter import *

from colorama import Fore, Back, Style ,init, deinit

import random
import time
import sys
import os

#---------------------------------------------------------------------------
def printGen(cols, rows, array, genNo):
    clearConsole()

    print("\tGame of Life -- Generation " + str(genNo + 1))

    for i in range(rows):
        for j in range(cols):
            if array[i][j] == -1:
                print("#", end=" ")
            elif array[i][j] == 1:
                print(".", end=" ")
            else:
                print(" ", end=" ")
        print("\n")

def printGenColor(cols, rows, array, genNo):
    clearConsole()
    init()
    print("\tGame of Life -- Generation " + str(genNo + 1))

    for i in range(rows):
        for j in range(cols):
            if array[i][j] == -1:
                print(Fore.YELLOW + "#", end=" ")
            elif array[i][j] == 1:
                print(Fore.RED + ".", end=" ")
            else:
                print(Fore.BLUE + " ", end=" ")
        print("\n")
    deinit()

"""---------------------------------------------------------------------------
                        DEFAULT SIM(s)
            This code is the original code, with theexception of 2
            lines of code that where used to add the color functionality
---------------------------------------------------------------------------"""
def initGrid(cols, rows, array):
    for i in range(rows):
        arrayRow = []
        for j in range(cols):
            if (i == 0 or j == 0 or (i == rows - 1) or (j == cols - 1)):
                arrayRow += [-1]
            else:
                ran = random.randint(0,3)
                if ran == 0:
                    arrayRow += [1]
                else:
                    arrayRow += [0]
        array += [arrayRow]


def processNeighbours(x, y, array):
    nCount = 0
    for j in range(y-1,y+2):
        for i in range(x-1,x+2):
            if not(i == x and j == y):
                if array[i][j] != -1:
                    nCount += array[i][j]
    if array[x][y] == 1 and nCount < 2:
        return 0
    if array[x][y] == 1 and nCount > 3:
        return 0
    if array[x][y] == 0 and nCount == 3:
        return 1
    else:
        return array[x][y]

def processNextGen(cols, rows, cur, nxt):
    for i in range(1,rows-1):
        for j in range(1,cols-1):
            nxt[i][j] = processNeighbours(i, j, cur)
def flowcontrol(color):
    clearConsole()
    if color == "color":
        defaultSim(True)
    else:
        defaultSim(True)

def defaultSim(color):
    ROWS = 11
    COLS = 20
    GENERATIONS = 100
    DELAY = .50


    thisGen = []
    nextGen = []

    initGrid(COLS, ROWS, thisGen)
    initGrid(COLS, ROWS, nextGen)
    for gens in range(GENERATIONS):
        if color:
            printGenColor(COLS, ROWS, thisGen, gens)
        else:
            printGen(COLS, ROWS, thisGen, gens)
        processNextGen(COLS, ROWS, thisGen, nextGen)
        time.sleep(DELAY)
        thisGen, nextGen = nextGen, thisGen


def injectCells():
    print("")

"""---------------------------------------------------------------------------
                        PHOTO SIM(s) PENDING*
        The goal of this method is to implement the rules of
    the Game of Life to the pixes of the photo, and create different
    figures.
---------------------------------------------------------------------------"""



def processImg(oldImg, newImg):
    '''
      //TODO-PENDIND
    :param oldImg:
    :param newImg:
    :return:
    '''
    for x in range(0, 100):
        try:
            px = newImg.load()
            for x in range(0,100):
                px[random.randint(0,newImg.size[0]-1), random.randint(0,newImg.size[0]-1)] = (251,252,252)
            return newImg
        except Exception as inst:
            print(inst, end='');

def resizePhoto(img,newsize):
    """
        This method will resize a image to a specific size.
    :param img: image
    :param newsize: the integer number representing the requesting size of the picture """

    percent = (newsize / float(img.size[0]))
    size = int((float(img.size[1]) * float(percent)))
    img = img.resize((newsize, size), PIL.Image.ANTIALIAS)
    img.save("picture1.png")
    return img


def pictureSim():
    #picture1.png
    try:
        #Open image and check size, if is lager than 500 resize
        img = PIL.Image.open("picture1.png")
        if img.size[0] != 500:
            img = resizePhoto(img,500)

        #GUI
        root = Tk()
        canvas = Canvas(root, width=500, height=500)
        canvas.pack()
        tk_img = ImageTk.PhotoImage(img)
        myimg = canvas.create_image(250, 250, image=tk_img)
        def callback(e):
            newImg = img
            newImg = processImg(img,newImg)
            canvas.itemconfigure(myimg, image=newImg)

        root.bind("<Return>", callback)
        root.mainloop()


    except Exception as inst:
        print(inst, end='');



"""---------------------------------------------------------------------------
                UTILS: GetUserInput, ClearConsole
---------------------------------------------------------------------------"""

def getUserInput(message,type):
    """
        This method will obptain the user input, and it wil handle any type of bad input
    :param message: Display message to user
    :param type:  supported INT, STR
    :return: It return a boolean, if true input was valid,otherwise false
             It returns the input obtain from the user
    """
    validInput = False
    while not validInput:
        try:
            userInput = input(message)

            if type == "int":
                return True, int(userInput)
            else:
                return True, userInput
        except Exception as ex:
            print("Please provide a " + ("number" if  type == "int" else "Alpha-value") , end='')

def clearConsole():
    """ This method will clear the console, according to the corresponding Operating system
        supported OS: Windows, and OS X """

    if sys.platform.startswith('win32'):
        os.system("cls")
    elif sys.platform.startswith('darwin'):
        os.system("clear")
    elif sys.platform.startswith('linux2'):
        os.system("clear")
    elif sys.platform.startswith('cygwin'):
        os.system("clear")
    else:
        print("LOG:Not Supported OS " + sys.platform)

"""---------------------------------------------------------------------------
                                Menu
---------------------------------------------------------------------------"""
userInput = "newsim"
invalidInput = False;

while userInput != "exit" :
    clearConsole()

    if invalidInput:
        print("Invalid option, please select a valid option\n", end='')

    invalidInput, userInput = getUserInput("Welcome to the Game Of Life, please select an option"
                          "\n\t Please Select an option:"
                          "\n\t a)Normal sim"
                          "\n\t b)color sim"
                          "\n\t c)Picture Sim Pending*"
                          "\n\t x)Exit\n","STR");

    if userInput == "a":
        flowcontrol("nocolor")
        invalidInput = False
    elif userInput == "b":
        flowcontrol("color")
        invalidInput = False
    elif userInput == "c":
        pictureSim()
        invalidInput = False
    elif userInput == "x":
        userInput = "exit"
        invalidInput = False
    else:
        invalidInput = True

print("Thank you for using the game of life, 42",end='')