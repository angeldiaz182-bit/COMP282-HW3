
import java.util.Arrays;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;

/**
 *  This class implements some of the methods of the Java
 *  ArrayList class.
 * @param <E> The element type
 *  @author .
 */
public class CSArrayList<E>
        extends AbstractList<E>
{
    // Data Fields

    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array
     *  private E[] theData declares a private array that can hold objects of a type specified by the generic parameter E. This is a common pattern in implementing generic data structures like lists, stacks, queues, where the underlying storage is an array, and the type of elements stored is flexible.*/
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    /**
     * Construct an empty CSArrayList with the default
     * initial capacity
     */
    /*Java provides an annotation that enables you to compile the constructor without an error message. If you place the statement @SuppressWarnings("unchecked") before the constructor, the compiler warning will not appear*/
    @SuppressWarnings({"unchecked"})
    public CSArrayList() {
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }


    /**
     * Construct an ArrayList<E> from any Collection whose elements are E or a subtype of E.
     * @param c The Collection
     */
    public CSArrayList(Collection<? extends E> c) {
        this.addAll(c);
    }

    /**
     * An empty CSArrayList with a specified initial capacity
     * @param capacity The initial capacity
     */
    @SuppressWarnings("unchecked")
    public CSArrayList(int capacity) {
        this.capacity = capacity;
        theData = (E[]) new Object[capacity];
    }


    /**
     * Add an entry to the data inserting it at the end of the list.
     * @param anEntry The value to be added to the list.
     * @return true since the add always succeeds
     */
    @Override
    public boolean add(E anEntry) {
        // if the size is equal to capacity we must first allocate a new array to hold the data and then copy the data to this new array with method reallocate
        if (size == capacity) {
            reallocate();
        }
        theData[size] = anEntry;
        size++;
        modCount++;
        return true;
    }

    /**
     * Add an entry to the data inserting it at index of the list.
     * @param index - The index of the item desired
     * @param anEntry The value to be added to the list.
     */
    public void add (int index, E anEntry) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == capacity) {
            reallocate();
        }
        // Shift data in elements from index to size - 1
        for (int i = size; i > index; i--) {
            theData[i] = theData[i - 1];
        }
        // Insert the new item.
        theData[index] = anEntry;
        size++;
        modCount++;
    }
    /**
     * Get a value in the array based on its index.
     * @param index - The index of the item desired
     * @return The contents of the array at that index
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return theData[index];
    }

    /**
     * Set the value in the array based on its index.
     * @param index - The index of the item desired
     * @param newValue - The new value to store at this position
     * @return The old value at this position
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }

    /**
     * Remove an entry based on its index
     * @param index - The index of the entry to be removed
     * @return The value removed
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++) {
            theData[i - 1] = theData[i];
        }
        size--;
        modCount++;
        return returnValue;
    }

    /**
     * Allocate a new array that is twice the size of the current array. Copies the contents of the current array to the new one using .copyOf
     */
    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**
     * Get the current size of the array
     * @return The current size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element
     * @param item The object to search for
     * @return The index of the first occurrence of the specified item
     *         or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (theData[i] == null && item == null) {
                return i;
            }
            if (theData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

/* converts theData to an array type String*/
@Override public String toString() {
    if (size == 0)
        return "[]";
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < size; i++) {
        sb.append(theData[i].toString());
        if (i != size - 1) {
            sb.append(",");
        }
    }
    sb.append("]");
    return sb.toString();
}
/* implements the clear method which clears all the data in the array list*/
public void clear(){
size = 0;
modCount++;
}
/* checks if the array list is Empty if it is return true*/
public boolean isEmpty(){
    if(size == 0){
    }
    return true;
}
/* Removes the object o from the array list*/
public boolean removes(Object o){
    int index = indexOf(o);
    if(index == -1){
        return true;
    }
    modCount++;
return false;
}
/* Makes sure there is enough capacity in the arrayList so everything can fit into the list*/
public void ensureCapacity(int minCapacity){
if(minCapacity > capacity){ /* Saying that if the min is bigger then capacity Create a new variable named new Capacity and double it*/
    int newCapacity = capacity * 2;
    if(newCapacity < minCapacity){ /*If min is still larger then the new Capacity just make the min capaciy equal to new capacity so min is now new capacity*/
        newCapacity = minCapacity;
    }
    theData = Arrays.copyOf(theData, newCapacity);/* copies the theData into the newCapacity*/
}


}
/* Trims the array list to remove any excess capacity that is not used in the array list*/
public void trimToSize() {
    if (capacity > size) {
        theData = Arrays.copyOf(theData, size);
    }
}

    /* Part C Optional Challenge implement addAll which add all elements from the collection to the specified index*/

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object[] oldData = c.toArray(); /* create object oldData and make it into an array*/
        int temp = oldData.length;
        if (temp == 0)
            return false;
        if (size == capacity) { /* If size is at capacity we make sure there is enough capacity so we call ensureCapacity method*/
            ensureCapacity(capacity * 2);
        }
        /* loop to go shift the elements to the right*/
        for (int i = size; i > index; i--) {
            theData[i + temp] = theData[i];
        }
        /* loop to insert the new elements*/
        for (int j = 0; j < temp; j++) {
            E element = (E) oldData[j];
            theData[index + j] = element;
        }
        size += temp;
        size++;
        modCount++;
        return true;
    }
}




