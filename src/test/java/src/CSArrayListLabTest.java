import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

class CSArrayListLabTest {
    private CSArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CSArrayList<>();
    }

    @Test
        /*Testing to see if we can get the value indicated at index 0*/
    void testIndexAtZero() {
        list.add(0, 50);//Adds 100 to index 0
        Assertions.assertEquals(50, list.get(0));// checks to see if the expected value which is 100 matches the actual which is 100 but you get it from calling list.get(0)
        Assertions.assertEquals(1, list.size());// Checks to see if the list only has one item in there
    }

    @Test
    void indexCaseSizeAtEnd() {
     list.add(3);//adds 3 into the array list
     list.add(list.size(),5); // adds 5 to the end of the array list
     Assertions.assertEquals(5, list.get(list.size()-1)); // checks to see if the last element is equal to 5
    }
    @Test
    void indexCaseAtSize(){
        int size = list.size();
        list.add(10);
        list.add(size,20);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(20, list.get(size));
    }
    @Test
    void multipleResizes(){
    int max = 10000;// max the array list resizes to is 10000
    for(int i=0; i<max; i++){ // when i less then max increase the size by one
        list.add(i);
    }
    Assertions.assertEquals(max, list.size());
    }
    @Test
    void searchDuplicates(){// checking to see if thier is duplicates
        list.add(1);// Adds 1,2,3,1 to the list
        list.add(2);
        list.add(3);
        list.add(1);
        Assertions.assertEquals(4, list.size());// checks to see if the list has 4 elements
        Assertions.assertEquals(0, list.indexOf(1));// checks whats the first index in where 1 appears
        Assertions.assertEquals(3, list.lastIndexOf(1));// checks to see the last index in where q appears
    }
    @Test
    void searchNull(){
        list.add(1);// creates a list with 1,null,3
        list.add(null);
        list.add(3);
        Assertions.assertEquals(3, list.size());// checks to see if the size is 3
        Assertions.assertEquals(1,list.indexOf(null));// searchs for what index null appears in
    }
    @Test
    void testRemove(){
        list.add(1);// adds 1,2,3 to the list
        list.add(2);
        list.add(3);
        Assertions.assertTrue(list.removes(2));// returns true if removes 2 from the list
        Assertions.assertEquals(2, list.size());// new list size should be 2 after removing
        Assertions.assertFalse(list.removes(6));// returns false since 6 was never in the list
    }
    @Test
    void failFastIteratorChecksAdd(){ // Tests the fail fast for the add method
    list.add(1);
    list.add(2);
    list.add(3);
    Iterator<Integer> iterator = list.iterator();
    list.add(4);
    Assertions.assertThrows(ConcurrentModificationException.class, iterator::next); // should pass the test
    }
    @Test
    void failFastIteratorRemove(){ // tests the fail fast for the remove method
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        list.remove(1);
        Assertions.assertThrows(ConcurrentModificationException.class, iterator::next);
    }
    @Test
    void failFastIteratorClear() { //checks the fail fast for the clear method
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        list.clear();//clears the list so size should be zero
        Assertions.assertEquals(0, list.size());
        Assertions.assertThrows(ConcurrentModificationException.class, iterator::next);
    }
    @Test
    void failfastIteratorAddAll(){ // checks the fail fast for the AddAll method
        list.add(1);
        list.add(2);
        list.add(3);
        CSArrayList<Integer> list2 = new CSArrayList<>();// creates a new array list named list 2
        list2.add(1);
        list2.add(2);
        list2.add(3);
        Iterator<Integer> iterator = list2.iterator();//iterates through list 2
        list2.addAll(list);// adds all of list to list2
        Assertions.assertThrows(ConcurrentModificationException.class, iterator::next);
    }
    }

