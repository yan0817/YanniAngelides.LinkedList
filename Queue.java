/**
Iterface of the Queue class which is an offshoot of a LinkedList
@author Yanni Angelides
@version 11/30/15
*/
public interface Queue<E>
{
	public void offer(E item);
	// offer adds to the end of the Linked list so that the end pointer is always the same thing
	public E poll();
	public E peek();
	public boolean isEmpty();
}
