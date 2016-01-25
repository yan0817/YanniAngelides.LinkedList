/**
Iterface of the Queue class which is an offshoot of a LinkedList
@author Yanni Angelides
@version 11/30/15
*/
public interface Stack<E>
{
	void push(E item);
	// adds to the beginning of the list so that the end pointer is always the same thing
	E pop();
	E peek();
	boolean isEmpty();
}