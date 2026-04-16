package edu.iastate.cs2280.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * whats left TODO
 * - fix find method to fix the error with out of bounds
 * - implement standard remove per guidelines
 * - complete StoutListIterator class (add, remove)
 * - figure out sorting and flow/algorithms
 * - test and polish
 * - junit?
 */

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    return size;
  }
  
  @Override
  public boolean add(E item) throws NullPointerException
  {
    if (item == null) throw new NullPointerException();

    if (size == 0)
    {
        Node newNode = new Node();
        newNode.addItem(item);
        head.next = newNode;
        newNode.previous = head;
        tail.previous = newNode;
        newNode.next = tail;
    }

    else
    {
        if (tail.previous.count < nodeSize)
        {
            tail.previous.addItem(item);
        }

        else
        {
            Node newNode = new Node();
            newNode.addItem(item);
            Node temp = tail.previous;
            temp.next = newNode;
            newNode.previous = temp;
            tail.previous = newNode;
            newNode.next = tail;
        }

    }

    size++;
    return true;
  }

  @Override
  public void add(int pos, E item)
  {
    if (item == null) throw new NullPointerException();

    if (pos < 0 || pos > size) throw new IndexOutOfBoundsException();

    NodeInfo nI = find(pos);
    Node n = nI.node;
    int off = nI.offset;

    if (head.next == tail) { add(item); return;}

    if (off == 0)
    {
        if (n.previous != head && n.previous.count < nodeSize) { n.previous.addItem(item); return; }
        else if (n == tail && n.previous.count == nodeSize)
        {
            Node newNode = new Node();
            newNode.addItem(item);
            Node temp = n.previous;
            temp.next = newNode;
            newNode.previous = temp;
            n.previous = newNode;
            newNode.next = n;
            size++;
            return;
        }
    }

    if (n.count < nodeSize) { n.addItem(off, item); size++; return; }

    else
    {
        Node newNode = new Node();
        Node temp = n.next;
        temp.previous = newNode;
        newNode.next = temp;
        n.next = newNode;
        newNode.previous = n;
        for (int i = nodeSize / 2; i < nodeSize; i++)
        {
            newNode.addItem(n.data[i]);
            n.data[i] = null;
        }

        n.count = nodeSize / 2;
        newNode.count = nodeSize - (nodeSize / 2);

        if (off <= nodeSize / 2 ) { n.addItem(off, item); }
        else if (off > nodeSize / 2) { newNode.addItem(off - (nodeSize / 2), item); }

        size++;
        return;
    }
  }

  @Override
  public E remove(int pos)
  {

    return null;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  // TODO 
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO
  }
  
  @Override
  public Iterator<E> iterator()
  {
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }

  private class NodeInfo {
      public Node node;
      public int offset;

      public NodeInfo(Node n, int o) {
          this.node = n;
          this.offset = o;
      }
  }

  private NodeInfo find(int pos)
  {
      Node n = head.next;
      int count = 0;

      while (n != tail)
      {

      }

      return null;
  }

  private class StoutListIterator implements ListIterator<E>
  {

	private int cursor; // keep track of position
    private int lastPos; // keep track of last position, will be used to compare for remove/set
    private E[] data; // list of data in each node so it is easier to iterate through


    /**
     * Default constructor
     */
    public StoutListIterator()
    {
        this(0);
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
        this.cursor = pos;
        this.lastPos = cursor - 1;
        dataInit();
    }

    private void dataInit()
    {
        data = (E[]) new Comparable[size];

        Node focus = head.next;
        int j = 0;
        while (focus != tail)
        {
            for (int i = 0; i < focus.count; i++) {
                data[j++] = focus.data[i];
            }
            focus = focus.next;
        }
    }

    @Override
    public boolean hasNext() { return cursor < size; }

    @Override
    public E next()
    {
        if (!hasNext()) throw new NoSuchElementException();
        else
        {
            lastPos = cursor;
            return data[cursor++];
        }
    }

    @Override
    public int nextIndex()
    {
        return cursor;
    }

    @Override
    public boolean hasPrevious() { return cursor > 0; }

    @Override
    public E previous()
    {
        if (!hasPrevious()) throw new NoSuchElementException();
        else
        {
            lastPos = cursor;
            return data[--cursor];
        }
    }

    @Override
    public int previousIndex()
    {
        return cursor - 1;
    }

    @Override
    public void add(E item)
    {
        // TODO
    }

    @Override
    public void remove()
    {
    	// TODO 
    }

    @Override
    public void set(E item)
    {
        if (cursor - lastPos < 0)
        {
            NodeInfo nI = find(cursor);
            nI.node.data[nI.offset] = item;
            data[cursor] = item;
        }

        else if (cursor - lastPos >= 0)
        {
            NodeInfo nI = find(lastPos);
            nI.node.data[nI.offset] = item;
            data[lastPos] = item;
        }

        else
        {
            throw new IllegalArgumentException();
        }
    }
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    // 
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  // TODO
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  // TODO
  }
 

}