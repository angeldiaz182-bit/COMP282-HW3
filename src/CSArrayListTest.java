import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class CSArrayListTest {
    public static void main(String[] args) {
        Collection<String> testCollection = new CSArrayList<>();
        testCollection.add("A");
        testCollection.add("B");

        Iterator<String> iterator = testCollection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(testCollection.size());
        System.out.println(testCollection.contains("B"));
        System.out.println(((CSArrayList<String>) testCollection).indexOf("B"));
/* Now testing the addAll method to make sure it results in true*/
        System.out.println("Testing addAll method");
        Collection<String> testCollection2 = new CSArrayList<>(); /* Create an array list with ABCD*/
        testCollection2.add("A");
        testCollection2.add("B");
        testCollection2.add("C");
        testCollection2.add("D");
       boolean result = testCollection.addAll(testCollection2);/* adds all of testCollection2 into testCollection*/
       System.out.println(result);
       System.out.println(testCollection);/* prints out the new testCollection*/
        }
    }

