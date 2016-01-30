/**
Code for the creation and manipulation of a Data Structure called a LinkedList. A LinkedList
is a data structure that contains a bunch of nodes that are connected together by their pointers. 
It is essentially a line of nodes that are connected together. The first node points to the next
node, the next node points to the one after that and so on and so forth. I implemented a 
singly linked list so that means that you can only iterate through the nodes in one direction. 
@author Yanni Angelides
@version 11/30/15
*/

import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>, Stack<E>, Queue<E>
{
	private ListNode head;
	//first node in the Linked List
	
	private ListNode tail;
	//last node in the LinkedList
	
	private int size;
	//number of nodes in the LinkedList 
	
	/**
	Creates a completely empty LinkedList (Default Constructor)
	*/
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	Creates a LinkedList that contains one ListNode
	Precondition that h.getNext() == null
	@param ListNode<E> h that is used to create the LinkedList
	*/
	public LinkedList(ListNode<E> h)
	{
		head = h;
		tail = h;
		size = 1;
	}
	
	/**
	Creates a LinkedList that is a copy of another LinkedList (Copy Constructor)
	@param LinkedList<E> list that is copied to create a new LinkedList 
	*/
	public LinkedList(LinkedList<E> list)
	{
		if (list.size() == 0)
		{
			throw new IllegalArgumentException("The LinkedList you are trying to copy is equal to null"); 
			//cannot copy a LinkedList that is null
		}
		else
		{
			head = new ListNode(list.get(0).getValue());
			ListNode<E> curr = head;
			for (ListNode<E> i = list.get(0).getNext(); i != null; i = i.getNext()) 
			//starts at the head of the param list and then keeps getting the next ListNode until it reaches the end
			{
				ListNode<E> node = new ListNode<E>(i.getValue());
				curr.setNext(node);
				curr = node;
			}
			tail = curr;
			size = list.size();
		}
	}
	
	/**
	A helper method that can get the ListNode at a specific index in the LinkedList
	@param int indx, the index of the ListNode that needs to be found
	@return ListNode<E>, ListNode that is needed
	*/
	@SuppressWarnings("unchecked")
	public ListNode<E> get(int indx)
	{
		if (indx >= size || indx < 0)
		{
			throw new IndexOutOfBoundsException("That index does not exist in the LinkedList");
		}
		else
		{
			ListNode trav = head;
			for (int i = 0; i < indx; i++) 
			//The for loop will start at the head of the LinkedList and then keep getting the next ListNode until it reaches the right index
			{
				trav = trav.getNext();
			}
			return trav;
		}	
	}
	
	/**
	Finds the index of a specified ListNode within the LinkedList
	@param E item, that needs to be found
	@return int, index of the ListNode<E> passed in as a parameter
	*/
	public int indexOf(E item)
	{
		int indx = 0;
		for (ListNode<E> i = head; i != null; i = i.getNext())
		{
			if(item.equals(i.getValue())) 
			//same for loop as the one in the above method except that each time it goes to the next ListNode it checks if it is equal to the ListNode passed in as a parameter
			{
				return indx;
			}
			indx++;
		}
		return indx;	
	}
	
	/**
	Gets the size of the LinkedList
	Important note (The index of the back of the LinkedList is not the size but the size - 1
	@return int, size of the LinkedList 
	*/
	public int size()
	{
		return size;
	}
	
	/**
	Completely empties the LinkedList so that there are no ListNodes in it
	*/
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	Sets a specific index of the LinkedList to an E value passed in as a paramater 
	@param int indx, index that needs to be changed, E item, that you want to set the specified index to
	*/
	@SuppressWarnings("unchecked")
	public void set(int indx, E item)
	{
		if (indx >= size || indx < 0)
		{
			throw new IndexOutOfBoundsException("That index does not exist in the LinkedList");
		}
		ListNode<E> n = new ListNode<E>(item); 
		//Need to make the item into a ListNode so that it can be put into the LinkedList
		if(indx == 0)
		{
			if (head != null)
			{
				n.setNext(head.getNext()); 
				//if indx == 0 then the item parameter must become the head
			}
			head = n;
			
		}
		else if(indx == size)
		{ 
			get(size - 1).setNext(n); 
			//if indx == size the item parameter must become the new tail
			tail = n;
		}
		else
		{
			n.setNext(get(indx+1));
			(get(indx - 1)).setNext(n);
			//sets pointer of the ListNode before the index item needs to be placed to item and the pointer of item to the ListNode after the index that the item needs to be placed  
		}
	}
	
	/**
	Checks if the item passed in as a parameter is contained in the LinkedList
	@param E item, item that the LinkedList needs to be checked for
	@return boolean indicating if the object is contained within the LinkedList
	*/
	public boolean contains(E item)
	{
		for(ListNode<E> i = head; i != null; i = i.getNext())
		{
			if((i.getValue()).equals(item))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	Takes out a specific item from the LinkedList
	@param E item, item that needs to be removed
	@return boolean indicating if the item was removed or not
	*/
	public boolean remove(E item)
	{
		return remove(this.indexOf(item));
	}
	
	/**
	Removes the ListNode at a certain index in the LinkedList
	@param int indx, index that needs to be removed
	@return boolean indicating whether or not the index was removed
	*/
	@SuppressWarnings("unchecked")
	public boolean remove(int indx)
	{
		if (indx >= size || indx < 0)
		{
			throw new IndexOutOfBoundsException("That index does not exist in the LinkedList");
		}
		else if (indx == 0)
		{
			this.removeFirst(); 
			return true;
		}
		else if (indx == size)
		{
			this.removeLast();
			return true;
		}
		else
		{
			(get(indx - 1)).setNext((get(indx)).getNext());
			//Setting the pointer of the ListNode before indx to the ListNode after index so the ListNode at index is not connected to any of the ListNodes any more, essentially being skipped over when the LinkedList is iterated through
			size--;
			return true;
		}
	}
	
	/**
	Adds a specific item to the LinkedList at a specified index
	@param int indx, index where the item needs to be added, E item that needs to be added
	*/
	public void add(int indx, E item)
	{
		if (indx > size || indx < 0)
		{
			throw new IndexOutOfBoundsException("That index does not exist in the LinkedList");
		}
		else if(indx == 0)
		{
			this.addFirst(item);
		}
		else if(indx == size)
		{
			this.addLast(item);
		}
		else
		{
			ListNode<E> n = new ListNode<E>(item);
			ListNode<E> node = head;
			for(int i = 0; i < indx - 1; i++)
			//Because the LinkedList is singly linked you have to stop at index minus one so that you can set the pointer of the ListNode of index - 1 to the ListNode you are trying to add
			{
				node = node.getNext();
			}
			n.setNext(node.getNext());
			node.setNext(n);
			size++;
		}
	}
	
	/**
	Adds the specified item to the end of the LinkedList
	@param E item that needs to be added
	*/
	public void add(E item)
	{
		add(size, item);	
	}
	
	/**
	Creates a String representation of the LinkedList
	@return String that represents the LinkedList
	*/
	public String toString()
	{
		String str = " ";
		for(ListNode<E> i = head; i != null; i = i.getNext())
		//when i equals null it will have reached the ListNode after the tail which is set to null because the pointer of the tail is null
		{
			str += i.toString() + ", "; 
			//this to String method is for ListNode because i is a ListNode, method not recursive
		}
		return str;
	}
	
	/**
	Removes the first ListNode in the LinkedList
	@return E removed from the LinkedList
	*/
	public E removeFirst()
	{
		if (size == 0)
		{
			throw new NoSuchElementException("The LinkedList is empty. Have to add a node to the list before you can remove one");
		}
		ListNode<E> save = head; //have to save head here because once it is removed from the LinkedList there is no way to retrieve it so that it can be returned
		head = head.getNext();
		size--;
		return (E)(save.getValue());
	}
	
	/**
	Removes the last ListNode from the LinkedList
	@return E that was removed
	*/
	public E removeLast()
	{
		if (size == 0)
		{
			throw new NoSuchElementException("The LinkedList is empty. Have to add a node to the list before you can remove one");
		}
		ListNode<E> save = tail;
		tail = this.get(size - 1);
		//have to traverse to the back of the LinkedList because you can't just get the tail. You also need the item before it  the LinkedList is singley linked so you can't go backwards
		size--;
		return (E)(save.getValue());
	}
	
	/**
	Adds an item to the beginning of the LinkedList
	@param E item, item that needs to be added
	*/
	public void addFirst(E item)
	{
		ListNode n = new ListNode(item); 
		//item must be turned into a ListNode before being added to the LinkedList
		if(this.size() == 0)
		{
			head = n;
			tail = n;
		}
		else
		{
			n.setNext(head);
			head = n;
		}
		size++;
	}
	
	/**
	Adds a specified item to the beginning of the LinkedList
	@param E item, item that needs to be added
	*/
	public void addLast(E item)
	{
		ListNode n = new ListNode(item);
		if(this.size() == 0)
		{
			head = n;
			tail = n;
		}
		else
		{
			tail.setNext(n);	
			tail = n;
		}
		size++;
	}
	
	/**
	Checks if the LinkedList is empty (does not have any ListNodes in it)
	@return boolean indicating whether or not the LinkedList is empty
	*/
	public boolean isEmpty()
	{
		if(size == 0) 
		//size will always be zero if the LinkedList is empty
		{
			return true;
		}	
		else
		{
			return false;
		}
	}
	
	/**
	Method that allows for the creation of an Iterator of LinkedList so for loops can be used with it
	@return Iterator<E> created in the LinkedListIterator class
	*/
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator()
	{
		return new LinkedListIterator(head); 
		//parameter is head because that is where the LinkedListIterator class is going to start traversing through the LinkedList
	}
	
	//STACK
	
	/**
	Adds an item to the top of the stack
	@param E item, item to be added to the stack
	*/
	public void push(E item)
	{
		this.addFirst(item);
	}
	
	/**
	Gets whatever ListNode is at the top of the stack
	@return E, value of the ListNode at the top of the stack
	*/
	public E peek()
	{
		return (E)(head.getValue());
	}
	
	/**
	Removes the item at the top of the Stack
	@return E item, value of ListNode<E> removed from the Stack
	*/
	public E pop()
	{
		return this.removeFirst();
	}
	
	//QUEUE
	
	/**
	Adds item to the end of the Queue
	@param E item, item to be added to the Queue
	*/
	public void offer(E item)
	{
		this.addLast(item);
	}
	
	/**
	Removes the last ListNode in the Queue
	@return E value of the last ListNode in the Queue
	*/
	public E poll()
	{
		return removeLast();
	}
	
	/**
	Main method that acts as a runner to test all of the other methods
	*/
	public static void main(String [] args)
	{
		LinkedList<Integer> arr = new LinkedList<Integer>();
		ListNode<Integer> n1 = new ListNode<Integer>(5);
		ListNode<Integer> n2 = new ListNode<Integer>(9);
		ListNode<Integer> n3 = new ListNode<Integer>(234);
		ListNode<Integer> n4 = new ListNode<Integer>(8);
		arr.add(9);
		arr.add(5);
		arr.add(arr.size(), 234);
		arr.add(0, 8);
		System.out.println(arr.size());
		System.out.println(arr.toString());
		LinkedList arr2 = new LinkedList(n4);
		LinkedList arr3 = new LinkedList(arr);
		System.out.println(arr2.toString());
		System.out.println(arr3.toString());
		System.out.println(arr.get(0).toString());
		System.out.println(arr.get(1).toString());
		System.out.println(arr.get(arr.size()-1).toString());
		arr.set(0, 9);
		System.out.println(arr.toString());
		System.out.println(arr.contains(8));
		System.out.println(arr.indexOf(9));
		System.out.println(arr.indexOf(5));
		System.out.println(arr.indexOf(234));
		arr3.clear();
		System.out.println(arr3.toString());
		System.out.println(arr3.isEmpty());
		System.out.println(arr.isEmpty());
		arr.remove(0);
		System.out.println(arr.toString());
		System.out.println(arr.contains(234));
		System.out.println(arr.indexOf(5));
		for (int i: arr)
		{
			System.out.println("*");		
		}
		LinkedList<String> arr5 = new LinkedList<String>();
		arr5.add("a");
		arr5.add("b");
		arr5.add("c");
		arr5.add("d");
		System.out.println(arr5.toString());
		arr5.remove("d");
		System.out.println(arr5.toString());
		
		Stack<String> sta = new LinkedList<String>();
		sta.push("hello");
		System.out.println(sta.peek());
		System.out.println(sta.pop());
		Queue<String> que = new LinkedList<String>();
		que.offer("1");
		que.offer("2");
		System.out.println(que.peek());
		System.out.println(que.poll());
		System.out.println(que.peek());
	}
}







