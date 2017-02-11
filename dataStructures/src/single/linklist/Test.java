/**
 * Created by Luis Alcantar on 2/8/17.
 */
package single.linklist;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[]args){
        LinkList<Integer> list = new LinkList<Integer>();


        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(8);

        System.out.println("Size before :"+list.size());
        System.out.println("Added add node at index 2: " + list.add(2, 3));
        System.out.println("Added add node at index 7: " + list.add(6, 7));
        System.out.println("add at the end: " + list.add(list.size(), 7));


        System.out.println(list);
        System.out.println(list.clone()+ "->Clone");


        LinkList t = new LinkList();

        Class tClass = t.getClass();
        Method[] methods = tClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }

    }
}
