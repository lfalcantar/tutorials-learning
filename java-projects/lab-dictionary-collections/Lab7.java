import java.util.*;
import java.util.Collections;
import java.io.*;


public class Lab7
{
  /** 
   * printMenuOptions 
   * This method displays the options for the dictionary, which are: 
   * [S]: Search for word 
   * [A]: Add a new word 
   * [L]: Load dictionary 
   * [O]: Order dictionary (in case is not in order) 
   * [D]: Delete a word 
   * [W]: See Word v the day 
   * [X]: Exit the dictionary 
   *@return void 
   **/ 
  
  public static void printMenuOptions()throws IOException,WordException
  {
    Queue<Words> weeklyWords = new LinkedList<Words>();
    Queue<Words> historyQueue = new LinkedList<Words>();

    //main array list with all the words
    ArrayList<Words> mainData =  new ArrayList<Words>();
    //the String for the user imput
    String line;
    //scanner for the usser inpts
    Scanner scan = new Scanner(System.in);
    //keep track if the list is already sored
    int  ya =0;
    //keep track of the words of the day
    int day6 = 0; 

    //****************
    do{
      //print the list *******************************
      System.out.println("[S]: Search for word \n[A]: Add a new word  \n[L]: Load dictionary \n[O]: Order dictionary (in case is not in order) "+
                         "\n[D]: Delete a word \n[W]: See Word of the day \n[X]: Exit the dictionary ");
      //get the imput stored in a String
      line = scan.nextLine();
      // Lower case tje input easier to compare 
      line.toLowerCase();
      // verefy that the input is  part of the obtions
      if(line.charAt(0) == 'a' || line.charAt(0) == 's' ||line.charAt(0) == 'd' ||line.charAt(0) == 'w' ||line.charAt(0) == 'l' 
           ||line.charAt(0) == 'o')
      {
        //if the user selects s to search the word
        //************************************************
         /** 
          * getWordOfTheDay 
          * This method will retrieve the word of the day. This method dequeues 
          * the first element on the queue ‘‘weeklyWords’’. Also the word that 
          * is dequeued is stored in the ‘‘historyQueue’’. 
          *@param: n/a 
          *@return a Word object from the queue 
          *@throws WordException: indicating ‘‘No Word for Today’’ 
          **/
 
        if(line.charAt(0) == 'w')
        {
          if(mainData.isEmpty())
          {
            System.out.println("The diccionary  is empty, please loan a file");
            
          }
          else{
            Random ra = new Random();
             if( day6 < 7){
              for(int z = 0;z<=6;z++){
                weeklyWords.add(mainData.get(ra.nextInt(mainData.size()-1)));
              }
              System.out.println("*************************");
              System.out.println("The word of the day is!!!! ");
              System.out.println("*************************");
              
              System.out.println(weeklyWords.peek());
              day6++;
              historyQueue.add(weeklyWords.peek());
              weeklyWords.remove();

              
              
              
            }
             else 
             {
               day6 =0;
                 throw new WordException("No Word for Today");
             
             }
             
          }
    
          
          
        }
        //************************************************
        //for the l option loand the diccionary
        if(line.charAt(0) == 'l')
        {
          System.out.println("Give me the file of the diccionary");
          String text = scan.nextLine();
          mainData = loadDictionary(text);
        }
        //************************************************
        // if the input is x check if the user want to leave the prolem
        else if(line.charAt(0) == 'x')
        {
          System.out.println("Are you sure  you want to close the program (y/n)");
          String ans= scan.nextLine();
          ans.toLowerCase();
          if(ans.charAt(0) == 'n')
          {
            line= "q";
          }
        }
        //if the user elects a  too add a new word
        //************************************************
        if(line.charAt(0) == 'a')
        {
          System.out.println("What is the new word ");
          String newWord = scan.nextLine();
          System.out.println("What is the definition of  " +newWord );
          String newdef = scan.nextLine();
          Words ad = new Words(newWord,newdef);
          addWord(ad,mainData);
        }
        //if the user selects s to search the word
        //************************************************
        if(line.charAt(0) == 's')
        {
           if(mainData.isEmpty())
          {
            System.out.println("The diccionary  is empty, please loan a file");
            
          }
           else{
          System.out.println("What is the word you are looking fo");
          String lo = scan.nextLine();
          lo.toLowerCase();
          Words look =new Words(lo);
          System.out.println("*************************");
          System.out.println("The word was found !!!! ");
          System.out.println("*************************");
          System.out.println("The definition of the wor: "+ lo +"is: "+searchWord(mainData,look,ya));
          ya++;
           }
          
          
          
        }
        //if the user select o  to order the dccionary but  if the diccionry is aready sortedor if is empty check
        //************************************************
        if(line.charAt(0) == 'o')
        {
          if(ya >=1)
          {
            System.out.println("The list is alredy sorted");
          }
          else if(mainData.isEmpty())
          {
            System.out.println("The diccionary  is empty, please loan a file");
            
          }
          
          else
          {
            OrderDictionary(mainData);
            ya++;
          }
          
        }
        //************************************************
        //for the w option of word of the day the diccionary
        if(line.charAt(0) == 'w')
        {
          
        }
      }
      //end of the firt if statement is the input is not part of the obtions throw an exeption
      else 
      {
        throw new IllegalArgumentException( " '"+ line +  " not a valid input "); 
      }
    }while(line.charAt(0) != 'x');
  }
  
  /** 
   * loadDictionary 
   * This method will take a String, representing the file containing 
   * the dictionary. Your method must store into the Dictionary all 
   * the objects Word from the file. 
   *@param file: is a String that represents the file name 
   *@see Word.java 
   **/ 
  public static ArrayList loadDictionary(String input)throws IOException
  {
    
    ArrayList<Words> list1 =  new ArrayList<Words>();
    Scanner s = new Scanner(new File(input));
    int i=0 ;
    while(s.hasNext())
    {
      String line = s.nextLine();
      String[] y =line.split(":");
      Words wi = new Words (y[0],y[1]);
      list1.add(wi);
      i++;
    }
    System.out.println("*************************");
    System.out.println("The diccionary is ready ");
    System.out.println("*************************");
    return list1;
  }
  /** 
   * addWord 
   * This method will take a word ’w’ as a parameter 
   * and the dictionary, and adds the word w into 
   * the dictionary. 
   *@param w: a Word object desired to store in dictionary 
   *@param dictionary: the list that holds Word Object 
   *@see Word.java 
   **/ 
  
  public static void addWord(Words x , ArrayList<Words> y)
  {
    
    y.add(x);
    System.out.println("*************************");
    System.out.println("The word was succefully added ");
    System.out.println("*************************");
    
    
  }
  /** 
* deleteWord 
* Given the String word ’w’, this method will delete the Word 
* from the dictionary. 
*@param dictionary: the array of Word Objects 
*@param w: the String representing the word that will be deleted 
* from the dictionary 
*@return a boolean true in case the word was removed. False otherwise 
*@throws WordException: indicating ‘‘word not found in dictionary’’ 
**/
  public static boolean deleteWord(ArrayList x,Words y)
  {
    return x.remove(y);
  
  }
  /** 
   * OrderDictionary 
   * This method will sort the the Dictionary in case is not ‘‘sorted’’. 
   * This method implements the Merge sort. 
   * Precondition: The dictionary is not in order 
   * Postcondition: The dictionary is now in ascending order 
   *@param dictionary: the ArrayList that holds the Word objects. 
   * The ArrayList is NOT in order 
   *@see Word.java for the compareTo 
   **/
  public static void OrderDictionary(ArrayList<Words> x)
  {
    Collections.sort(x, new WordComparator());
    System.out.println("*************************");
    System.out.println("The word was succefully sorted ");
    System.out.println("*************************");
    
  }
  /** 
   * searchWord 
   * This method will search for the definition of a given word ’w’ in 
   * the dictionary. 
   *@param dictionary: the array of Word Objects 
   *@param w: the word that will look search for in dictionary 
   *@return a String representing the definition of w. 
   *@throws WordException: indicating ‘‘word not found in dictionary’’ 
   **/
  public static String searchWord(ArrayList<Words> x,Words w,int ya)throws WordException
  {
    int index;
    
    if(ya>=1)
    {
      index  =Collections.binarySearch(x,w,new WordComparator());
      if(index < 0)
      {
        throw new WordException("word not found in dictionary");
      }
      else{
        return x.get(index).getDefinition();
        
        //System.out.println("The definition of the word : " + w.getword() + " is " + x.get(index).getDefinition() );
      }
    }
    else
    {
      Collections.sort(x, new WordComparator());
      
      index  =Collections.binarySearch(x,w,new WordComparator());
      if(index == -1)
      {
        throw new WordException("word not found in dictionary");
      }
      else
      {
        return x.get(index).getDefinition();
        
        //System.out.println("The definition of the word : " + w.getword() + " is " + x.get(index).getDefinition() );
      }
    }
    
    
    }
  /** 
   * getWordOfTheDay 
   * This method will retrieve the word of the day. This method dequeues 
   * the first element on the queue ‘‘weeklyWords’’. Also the word that 
   * is dequeued is stored in the ‘‘historyQueue’’. 
   *@param: n/a 
   *@return a Word object from the queue 
   *@throws WordException: indicating ‘‘No Word for Today’’ 
   **/
 
  
  
  

  
  /*
   * Print
   */ 
  public static void printh(ArrayList<Words> x)
  {
    for(Words s: x)
      System.out.println(s);
  }

  public static void main(String[]args)
  {
    try
    {
      printMenuOptions();
    }
    catch(IOException e)
    {
      System.out.println(e);
    }
    catch(WordException k)
    {
      System.out.println(k);
    }
    
    
    
    
    
    
    
  }
}