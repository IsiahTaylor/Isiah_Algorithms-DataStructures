/* Author: Ralph A. Foy [AA0000]
 * Class : ICS340-01 Summer 2020
 * 
 *       Copyright (c) 2020 
 *       Authorization is given to students enrolled in the course to reproduce 
 *       this material exclusively for their own personal use.
 */
package edu.metrostate.ics340.p2.it8343;
/**
 * Defines the requirements for an object that can be used as a tree node in a binary tree.
 * Implementations of TreeNode that override equals will typically need to override hashCode as well.
 *  
 * @author rfoy
 *
 * @param <T> Type of value contained in a node
 */
public interface TreeNode<T> {
	/**
	 * Returns the left child of this node
	 * @return the left child of this node
	 */
	TreeNode<T> getLeftChild();
	
	/**
	 * Returns the right child of this node
	 * @return the right child of this node
	 */
	TreeNode<T> getRightChild();
	
	/**
	 * Returns the value contained in this node
	 * @return the value contained in this node
	 */
	T getValue();
}
