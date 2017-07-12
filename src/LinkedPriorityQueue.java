/**
 * LinkedQueue represents a linked implementation of a queue.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

public class LinkedPriorityQueue<T> implements PriorityQueueADT<T>
{
   private int count;
   private PriorityNode<T> front, rear;

   /**
    * Creates an empty queue.
    */
   public LinkedPriorityQueue()
   {
      count = 0;
      front = rear = null;
   }

   /**
    * Adds the specified element to the end of the queue.
    *
    * @param element  the element to be added to the rear of this queue
    */
   public void enqueue (T element)
   {
      PriorityNode<T> node = new PriorityNode<T>(element, Double.MAX_VALUE);

      if (isEmpty())
         front = node;
      else
         rear.setNext(node);

      rear = node;
      count++;
   }
   
   /**
    * Adds the specified element to the priority queue in the proper location based on priority.
    *
    * @param element  the element to be added to the rear of this queue
    */
   public void enqueue (T element, double p)
   {
      PriorityNode<T> node = new PriorityNode<T>(element, p);

      if (isEmpty())
      {
         front = node;							//if it's empty just add to the front
         rear = node;
      }
      else if (p >= rear.getPriority())
      {
    	 rear.setNext(node);					//if the priority of the new node is worse than the rear, then make it the new rear
      	 rear = node;
      }
      else
      {
    	  PriorityNode<T> current = front;
    	  PriorityNode<T> before = null;
    	  while (current != null)
    	  {
    		  if(p < current.getPriority())		//if the priority is less than the currents, then it has to come right before it
    		  {
    			  if(before == null)			//if before is null, then we are ultimately inserting at front of queue.
    			  {
    				  node.setNext(front);
    				  front = node;
    			  }
    			  else							//otherwise we are inserting in the middle
    			  {
    				  node.setNext(current);
    				  before.setNext(node);
    			  }
    			  break;
    		  }
    		  
    		  before = current;					//shuffle on down!
    		  current = current.getNext();
    	  }
      }
      count++;
   }

   /**
    * Removes the element at the front of this queue and returns a
    * reference to it. Throws an EmptyCollectionException if the
    * queue is empty.
    *
    * @return                           the element at the front of this queue
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T dequeue() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      T result = front.getElement();
      front = front.getNext();
      count--;
      
      if (isEmpty())
         rear = null;

      return result;
   }
   
   /**
    * Returns a reference to the element at the front of this queue.
    * The element is not removed from the queue.  Throws an
    * EmptyCollectionException if the queue is empty.  
    *
    * @return                            a reference to the first element in
    *                                    this queue
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T first() throws EmptyCollectionException
   {
	   if (isEmpty())
	         throw new EmptyCollectionException ("queue");

	      T result = front.getElement();
	      return result;
   }

   /**
    * Returns true if this queue is empty and false otherwise. 
    *
    * @return  true if this queue is empty and false if otherwise
    */
   public boolean isEmpty()
   {
	   return count == 0 && front == null;
   }
 
   /**
    * Returns the number of elements currently in this queue.
    *
    * @return  the integer representation of the size of this queue
    */
   public int size()
   {
       return count;
   }

   // This additional method merges another priority queue with this one
   public void mergeAll(LinkedPriorityQueue<T> other){
       while(!other.isEmpty()){
           double priority = other.front.getPriority();
           this.enqueue(other.dequeue(), priority);
       }
   }

   /**
    * Returns a string representation of this queue. 
    *
    * @return  the string representation of this queue
    */
   public String toString()
   {
       String s = "PriorityQueue:\n";
	   PriorityNode<T> current = front;
       while (current != null)
       {
    	   s = s + "\t" + current.toString() + "\n";
           current = current.getNext();
       }
       return s;
   }
}
