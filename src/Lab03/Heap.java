/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab03;





/**
 *
 * @author Dinanajana
 * 
 */
public class Heap<T extends Comparable<T>> implements HeapInterface 
{ 
    private final int MAX = 1000; 
    
    private final T[] array;
    private int size; 
    

    public Heap() { 
	array = (T[]) new Comparable[MAX];
	size  = 0; 
    }

    
    @Override
    public int leftChild(int index)  { 
        return 2 * index + 1;  
    }
    @Override
    public int parent(int index){ 
        return (int)((index - 1) / 2); 
    }
    @Override
    public int rightChild(int index){
        return 2 * index + 2;   
    }
    
    @Override
    public T parentValue(int index) {
        return array[parent(index)]; 
    }
    
    @Override
    public boolean hasLeftChild(int index) {
        return leftChild(index) < size; 
    } 
    @Override
    public boolean hasRightChild(int index){
        return rightChild(index) < size; 
    } 

    @Override
    public void swap(int a, int b) { 
	T tmp = array[a];
        
	array[a] = array[b]; 
	array[b] = tmp;
    }
    
    

    @Override
    public boolean isEmpty() { return (size == 0); } 


    
    @Override
    public void addElement(Comparable value) { 
	if(size == MAX) throw new IllegalStateException("Full array");
	array[size++] = (T) value; 
	
        if(size == 0) throw new IllegalStateException("Shape error");
	int index = size - 1;  
	while(size != 0) { 
	    if(parentValue(index).compareTo(array[index]) <= 0) break; 
	    
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }

    @Override
    public T removeElement() {
	if(isEmpty()) return null; 
	T res = array[0]; 
	array[0] = array[size-1]; 
	size --; 
	int index = 0;
        
            while(index < size ){
                
                
                if(hasLeftChild(index)&&
                        hasRightChild(index)){
                    
                        //check for equal cases
                        if(array[leftChild(index)].compareTo
                           (array[rightChild(index)]) >= 0){
                    
                            if(array[index].compareTo
                            (array[rightChild(index)]) >= 0){
                    
                            swap(index,rightChild(index));
                        
                            index = rightChild(index);
                            }else{
                            
                            break;
                            }
                            
                        }
                        //check left
                        else if(array[leftChild(index)].compareTo
                                (array[rightChild(index)]) < 0){
                            
                            if(array[index].compareTo
                              (array[leftChild(index)]) > 0){
                        
                            swap(index,leftChild(index));
                        
                            index = leftChild(index);
                        
                            }else{
                            
                            break;
                            }
                        }else{ 
                            //exit loop
                            break;                        
                        }
                    }
                    // Left child only
                    if(hasLeftChild(index) && !hasRightChild(index)){
                    
                        if(array[index].compareTo
                          (array[leftChild(index)]) > 0){
                        
                            swap(index,leftChild(index));
                        
                            index = leftChild(index);
                        
                        }else{
                        
                            break;
                        }
                
                
                
                }
                // Right child only
                if(hasRightChild(index) && !hasLeftChild(index)){
                
                    if(array[index].compareTo
                      (array[rightChild(index)]) > 0){
                    
                        swap(index,rightChild(index));
                        
                        index = rightChild(index);
                    }else{
                        
                        break;
                    
                    }
                
                }
                // no children
                if(!hasRightChild(index) && !hasLeftChild(index)){
                
                    break;
                }
                
            } 
	return res;
    }

    public void showHeap() {
	for(int i=0; i<size; i++) 
	    System.out.print(array[i] + " "); 
	
    }


    public static void main(String [] args) {
	Heap heap = new Heap<Integer>(); 

	for(int i=0; i<100; i++) {
	    heap.addElement((int) (Math.random() * 100)); 
	    heap.showHeap();
	}


	System.out.println("\n Sorted List:");
	while(!heap.isEmpty()) 
	    System.out.print(heap.removeElement()+" ");
	
    }

    
    
       

}
	