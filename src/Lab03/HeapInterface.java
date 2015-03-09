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
    
    boolean isRoot(int index);
    int leftChild(int index);
    int parent(int index);
    int rightChild(int index);
    T myParent(int index);
    
    
    boolean hasLeftChild(int i);
    boolean hasRightChild(int i);
    
    void swap(int a,int b);
    boolean isEmpty();
    //void add(T ele);
    T remove();
    void bubbleUp();
    void bubbleDown();
}
