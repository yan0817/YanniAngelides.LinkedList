import java.util.NoSuchElementException; 
import java.util.Iterator;
import java.lang.Iterable;

public class LinkedListIterator<E> implements Iterator<E>
{
	private ListNode<E> curr;
	
	public LinkedListIterator(ListNode<E> head)
	{
		curr = head;
	}
	
	/**
	This is a method from the Iterator Interface that returns the next object in the Vector	
	@return E the next object of type E in the Vector
	*/
	public E next()
	{
		if(hasNext() == false)
		{
			throw new NoSuchElementException(); //Because if hasNext() == false then there us no such thing as the next element
		}
		else
		{
			E item = curr.getValue();
			curr = curr.getNext();
			return item; 
		}
	}
	
	/**
	@return boolean determining whether or not there is another object in the Vector
	*/
	public boolean hasNext()
	{
		return curr != null;
	}
}