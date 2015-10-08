import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tektak on 7/10/15.
 */
public class GenericTest {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
//        System.out.println(l1.getClass()==l2.getClass());
//        System.out.println("----> " + l1.getClass() + "  -----> " + l2.getClass());


        List<String> sl = new ArrayList<String>();
        sl.add("one");
        sl.add("two");
        new GenericTest().copyAllFromOne(sl, new ArrayList<String>());
    }

    public <T> void copyAllFromOne(Collection<T> test, Collection<T> tmp){
        System.out.println(test.size());
        for(T o : test){
            tmp.add(o);
        }
        System.out.println(tmp);
    }
}
