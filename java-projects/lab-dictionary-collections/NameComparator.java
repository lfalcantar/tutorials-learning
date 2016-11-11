import java.util.Comparator;
public class WordComparator implements Comparator<Words>
{
    public int compare(Words o1, Words o2)
    {
       return o1.getword().compareTo(o2.getword());
   }
}