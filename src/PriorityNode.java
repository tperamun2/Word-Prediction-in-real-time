/** * LinearNode represents a node in a linked list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */public class PriorityNode<E>{    private PriorityNode<E> next;    private E element;    private double priority;        /**     * Creates an empty node.
     */    public PriorityNode()    {        next = null;        element = null;        priority = Double.MAX_VALUE;    }        /**     * Creates a node storing the specified element.     *
     * @param elem  the element to be stored within the new node     * @param inP	the priority of this particular element  
     */    public PriorityNode (E elem, double inP)    {        next = null;        element = elem;        priority = inP;    }        /**     * Returns the node that follows this one.     *
     * @return  the node that follows the current one
     */    public PriorityNode<E> getNext()    {        return next;    }        /**     * Sets the node that follows this one.     *
     * @param node  the node to be set to follow the current one
     */    public void setNext (PriorityNode<E> node)    {        next = node;    }        /**     * Returns the element stored in this node.     *
     * @return  the element stored in this node
     */    public E getElement()    {        return element;    }        /**     * Sets the element stored in this node.     *
     * @param elem  the element to be stored in this node
     */    public void setElement (E elem)    {        element = elem;    }        /**     * Returns the priority of this node.     *     * @return  the priority of the node     */    public double getPriority()    {    	return priority;    }        /**     * Sets the priority of the node.     *      * This function should probably not be called.     *     * @param 	p the priority to set the node to     */    public void setElement (double p)    {        priority = p;    }        /**     * Returns a string representation of this node.      *     * @return  the string representation of this node     */    public String toString()    {    	return element.toString() + " " + priority;    }}
