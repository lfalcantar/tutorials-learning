import java.util.Comparator;
public class KIPComparator implements Comparator<KIP>
{
    public int compare(KIP o1, KIP o2)
    {
       return o1.getData().compareTo(o2.getData());
   }
}