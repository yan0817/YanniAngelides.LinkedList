# YanniAngelides.LinkedList
public class LinkedList
{
	private ListNode head;
	private ListNode tail;
	private int size; 
	//singley link
	//head pointer
	//tail pointer
	
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public void add(int indx, ListNode node)
	{
		if (head == null)
		{
			head.setPointer(node);
		}
		else
		{
			ListNode trav = head;
			for (int i = 0; i < indx; i++)
			{
				trav = trav.getNext();
			}
			ListNode set = trav.getNext();
			trav.setPointer(node.getNext());
			(trav.getNext()).setPointer(set);
		}
	}
	
	public void add(ListNode node)
	{
		if (head == null)
		{
			head.setPointer(node);
		}
		else
		{
			tail.setPointer(node);
		}	
	}
	
	public String toString()
	{
		String str = " ";
		for (ListNode i = head; i != tail; i = i.getNext())
		{
			str += i.toString();
		}
		return str;
	}
	
	public static void main(String [] args)
	{
		LinkedList arr = new LinkedList();
		ListNode<Integer> n1 = new ListNode<Integer>(5);
		ListNode<Integer> n2 = new ListNode<Integer>(9);
		ListNode<Integer> n3 = new ListNode<Integer>(234);
		arr.add(n1);
		arr.add(n2);
		arr.add(1, n3);
	}
	//add
	//remove
}

public class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	public ListNode(E e)
	{
		item = e;
	}
	
	public ListNode(E e, ListNode<E> ln)
	{
		item = e;
		next = ln;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setItem(E e)
	{
		item = e;
	}
	
	public void setPointer(ListNode<E> ln)
	{
		next = ln;
	}
	
	public String toString()
	{
		String node = " ";
		node += "Item: " + item.toString();
		return node;
	}
}
