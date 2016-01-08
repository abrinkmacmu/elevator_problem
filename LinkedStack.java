interface StackInterface<T>{
	public void push(T newEntry);
	public T peek();
	public T pop();
	public boolean isEmpty();
	public void clear();
}

public class LinkedStack<T> implements StackInterface<T>{
	private Node topNode;

	public LinkedStack()
	{
		topNode = null;
	}

	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}

	public T pop()
	{
		T result = peek();
		topNode.setData(null);
		if(!isEmpty())
			topNode = topNode.getNext();
		return result;
	}

	public T peek()
	{
		T result = null;
		if(!isEmpty())
			result = topNode.getData();
		return result;
	}

	public boolean isEmpty()
	{
		return topNode == null;
	}

	public void clear()
	{
		while(!isEmpty())
		{
			topNode.setData(null);
			topNode = topNode.getNext();
		}
	}
	
	private class Node
	{
		private T data;
		private Node next;
	
		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}

		public void setData(T newData){this.data = newData;}

		public T getData(){return this.data;}

		public void setNext(Node newNext){this.next = newNext;}

		public Node getNext(){return this.next;}
	}
}
