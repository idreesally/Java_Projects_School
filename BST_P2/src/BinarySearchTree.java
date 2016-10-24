import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
  private static class Node<AnyType>
  {
    private AnyType data;
    private Node<AnyType> parent;
    private Node<AnyType> left;
    private Node<AnyType> right;

    public Node(AnyType d, Node<AnyType> p, Node<AnyType> l, Node<AnyType> r)
    {
      setData(d);
      setParent(p);
      setLeft(l);
      setRight(r);
    }

    public AnyType getData() { return data; }
    public Node<AnyType> getParent() { return parent; }
    public Node<AnyType> getLeft() { return left; }
    public Node<AnyType> getRight() { return right; }

    public void setData(AnyType d) { data = d; }
    public void setParent(Node<AnyType> p) { parent = p; }
    public void setLeft(Node<AnyType> l) { left = l; }
    public void setRight(Node<AnyType> r) { right = r; }
  }

  private Node<AnyType> root;

  public BinarySearchTree() { makeEmpty(); }

  public void makeEmpty() { root = null; } 

  public boolean isEmpty() { return (root == null); }
  
  //Returns true if given node has no children
  private boolean hasNoChildren(Node<AnyType> n){
	if(n.getLeft() == null && n.getRight() == null) return true;
	else return false;
  }
  
  //Returns true if given node has only one child
  private boolean hasOnlyOneChild(Node<AnyType> n){
	  if(hasLeftChild(n) && !hasRightChild(n)) return true;
	  if(hasRightChild(n) && !hasLeftChild(n)) return true;
	  else return false;
  }
  
  //Returns true if given node has a left child
  private boolean hasLeftChild(Node<AnyType> p){
	  if(p.getLeft() != null) return true;
	  else return false;
  }
  
  //Returns true if given node has a right child
  private boolean hasRightChild(Node<AnyType> p){
	  if(p.getRight() != null) return true;
	  else return false;
  }

  public boolean contains(AnyType v) { return contains(v, root); }

  private boolean contains(AnyType v, Node<AnyType> t)
  {
    if (t == null) return false;

    int compareResult = v.compareTo(t.getData());

    if (compareResult < 0)
      return contains(v, t.getLeft());
    else
      if (compareResult > 0)
        return contains(v, t.getRight());
      else
        return true;
  }

  public AnyType findMin() throws IllegalStateException
  {
    if (isEmpty()) throw new IllegalStateException("Search Tree is empty.");

    Node<AnyType> minNode = findMin(root);
    return minNode.getData();
  }

  private Node<AnyType> findMin(Node<AnyType> t)
  {
    if (t == null)
      return null;
    else
      if (t.getLeft() == null)
        return t;

    return findMin(t.getLeft());
  }

  public AnyType findMax() throws IllegalStateException
  {
    if (isEmpty()) throw new IllegalStateException("Search Tree is empty.");

    Node<AnyType> maxNode = findMax(root);
    return maxNode.getData();
  }

  private Node<AnyType> findMax(Node<AnyType> t)
  {
    if (t == null)
      return null;
    else
      if (t.getRight() == null)
        return t;

    return findMax(t.getRight());
  }

  public void insert(AnyType v)
  {
	  Node<AnyType> it = root; //to reset root at the end
	  Node<AnyType> newParent = null;
	  Node<AnyType> n = new Node<AnyType>(v, null, null, null);
	 
	  //if Tree is empty, no need to iterate
	  if(isEmpty()){
		  root = n;
		  return;
	  }
	  
	  //Prevent duplicate values from entering the list
	  if(contains(v)) return;
	  
	 // root will become location for input 
	 while(root != null)
	 {
		newParent = root;
		traverseLoop(v);
	 }
	 
	 //insert new value into the tree and assign it to its proper parent
	 if(v.compareTo(newParent.getData()) < 0) newParent.setLeft(n);
	 else newParent.setRight(n);	  
	 n.setParent(newParent);
	 
	 //reset root
	 root = it;
  }
  
  //For use in while loop. Choose which direction to go down the tree.
  private void traverseLoop(AnyType a){
	  if(a.compareTo(root.getData()) < 0) root = root.getLeft();
      else root = root.getRight();	
  }
 
  public void remove(AnyType v)
  {
	  
	  Node<AnyType> it = root;
	  Node<AnyType> newParent = null;
	 
	  //if empty or doesn't contain node to remove then return
	  if(isEmpty()) return;
	  if(!contains(v)) return;
	  
	  //if value to remove is at the root, operations slightly differ so separate method
	  if(root.getData() == v){
		  removeRoot();
		  return;
	  }
	  
	  // root becomes location of node to be removed
	  while(root.getData() != v)
	  {
		 newParent = root;
		 traverseLoop(v);	  
	  }
	  
	  // Case 1: if Node to be removed has no children
	  if(hasNoChildren(root)){
		  if(v.compareTo(newParent.getData()) < 0) newParent.setLeft(null);
		  else newParent.setRight(null);	  
	  } 
	  
	  // Case 2: if Node to be removed has 1 child
	  else if(hasOnlyOneChild(root)){
		  if(v.compareTo(newParent.getData()) < 0){
			  if(hasLeftChild(root)){
				  newParent.setLeft(root.getLeft());
				  root.getLeft().setParent(newParent);
			  }
			  else{
				  newParent.setLeft(root.getRight());
				  root.getRight().setParent(newParent);
			  }
		  }
		  else {
			  if(hasRightChild(root)){
				  newParent.setRight(root.getRight());
				  root.getRight().setParent(newParent);
			  }
			  else{
				  newParent.setRight(root.getLeft());
				  root.getLeft().setParent(newParent);
			  }
		  }
	  }
	  
	  // Case 3: if Node to be removed has 2 children
	  else{
		  Node<AnyType> temp;

		  temp = findMin(root.getRight());
		  if(v.compareTo(newParent.getData()) < 0){
			  newParent.setLeft(temp);
		  }
		  else newParent.setRight(temp);
		  
		  //if right child of Node is equal to new parent node, right child must be gone
		  if(temp.getData() == root.getRight().getData()) temp.setRight(root.getRight().getRight());
		  else temp.setRight(root.getRight());
		  
		  temp.setLeft(root.getLeft());
		  temp.setParent(newParent);
		  root.getLeft().setParent(temp);
		  root.getRight().setParent(temp);
		
		  Node<AnyType> toDelete = null;
		  while(root.getData() != temp.getData())
		  {  
			 toDelete = root; 
			 traverseLoop(temp.getData()); 
		  }
		  toDelete.setLeft(null);
	  
	  }
	  root = it;
  }
  
  private void removeRoot(){
	  
	  // Case 1: Root node has no children
	  if(hasNoChildren(root)) root = null;
	  
	  // Case 2: Root node has one child
	  else if(hasOnlyOneChild(root)){
		  if(hasLeftChild(root)) root = root.getLeft();
		  else root = root.getRight();
		  root.setParent(null);
	  }
	  
	  // Case 3: Root node has 2 children
	  else{
		  Node<AnyType> temp;
		  temp = findMin(root.getRight());
		  temp.setLeft(root.getLeft());

		  if(temp.getData() == root.getRight().getData()) temp.setRight(root.getRight().getRight());
		  else temp.setRight(root.getRight());
		  
		  root.getLeft().setParent(temp);
		  root.getRight().setParent(temp);

		  Node<AnyType> toDelete = null;
		  while(root.getData() != temp.getData())
		  {  
			 toDelete = root; 
			 traverseLoop(temp.getData());	 
		  }
		  toDelete.setLeft(null);
		  root.setParent(null);  
	  }
  }

  public void printTree(){ printBST(root, "");}

  private void printBST(Node<AnyType> r, String spacing) 
  {
	    if (r == null) {
	      System.out.println(spacing);
	      return;
	    }

	    // spacing is the indentation for the parent node.
	    // spaceChildren is the indentation for the children
	    String spaceChildren;
	    if (spacing.equals("")) spaceChildren = "     ";
	    else spaceChildren = "     " + spacing;
	    
	    //print by navigating in order traversal
	    printBST(r.getRight(), spaceChildren);
	    System.out.println(spacing + r.getData());
	    printBST(r.getLeft(), spaceChildren);
  }
  
  public Iterator<AnyType> iterator()
  {
    LinkedList<AnyType> snapshot = new LinkedList<>();

    inOrderTraversal(root, snapshot);
    return snapshot.iterator();
  }

  private void inOrderTraversal(Node<AnyType> t, LinkedList<AnyType> l)
  {
    if (t != null)
    {
      inOrderTraversal(t.getLeft(), l);
      l.add(t.getData());
      inOrderTraversal(t.getRight(), l);
    }
  }
}
