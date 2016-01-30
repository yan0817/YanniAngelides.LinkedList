/**
Iterface of the Queue class which is an offshoot of a LinkedList
@author Yanni Angelides
@version 11/30/15
*/
public interface Stack<E>
{
	public void push(E item);
	// adds to the beginning of the list so that the end pointer is always the same thing
	public E pop();
	public E peek();
	public boolean isEmpty();
}