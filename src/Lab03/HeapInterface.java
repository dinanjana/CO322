/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab03;

/**
 *
 * @author Dinanajana
 * @param <T>
 */
public interface HeapInterface <T extends Comparable<T>>  {
    
    
    int leftChild(int index);
    int parent(int index);
    int rightChild(int index);
    T parentValue(int index);
    
    
    boolean hasLeftChild(int i);
    boolean hasRightChild(int i);
    
    void swap(int a,int b);
    boolean isEmpty();
    void addElement(T ele);
    T removeElement();
    
    
}
