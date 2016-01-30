/**
This is the class that allows for the creation of an object that will act as the base of
the LinkedList Data Structure. This objects has a value as well as a pointer. When the
pointers of ListNodes are connected that connection creates a LinkedList
@author Yanni Angelides
@version 11/30/15
*/

public class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	/**
	Constructs a ListNode<E> with a specified item
	*/
	public ListNode(E e)
	{
		item = e;
		next = null;
	}
	
	/**
	Constructs a ListNode with a specified item and pointer that points to the next object in the LinkedList
	*/
	public ListNode(E e, ListNode<E> ln)
	{
		item = e;
		next = ln;
	}
	
	/**
	Gets the value of the specified ListNode
	@return E value of the item in the ListNode
	*/
	public E getValue()
	{
		return item;
	}
	
	/**
	Gets the pointer of the specified ListNode
	@return the next ListNode in the LinkedList 
	*/
	public ListNode<E> getNext()
	{
		return next;
	}
	
	/**
	Sets the value of the specified ListNode 
	@param E the value that the item class field in the ListNode needs to be set to
	*/
	public void setValue(E e)
	{
		item = e;
	}
	
	/**
	Sets the pointer of the specified ListNode
	@param the ListNode that the current ListNode is set to point to
	*/
	public void setNext(ListNode<E> ln)
	{
		next = ln;
	}
	
	/**
	Makes a String representation of the specified ListNode
	@return String representation of the ListNode
	*/
	public String toString()
	{
		String node = " ";
		node += item.toString();
		return node;
	}
}