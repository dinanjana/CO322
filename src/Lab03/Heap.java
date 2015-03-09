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
    private final int default_size = 10; 
    
    private final T[] array;
    private int size; 
    

    public Heap() { 
	array = (T[]) new Comparable[default_size];
	size  = 0; 
    }

    @Override
    public boolean isRoot(int index) {	return (index == 0);   }
    @Override
    public int leftChild(int index)  { return 2 * index + 1;  }
    @Override
    public int parent(int index)     { return (int)((index - 1) / 2); }
    @Override
    public int rightChild(int index) { return 2 * index + 2;   }
    
    @Override
    public T myParent(int index) { return array[parent(index)]; }
    //@Override
    
    
    @Override
    public boolean hasLeftChild(int i) { return leftChild(i) < size; } 
    @Override
    public boolean hasRightChild(int i){ return rightChild(i) < size; } 

    @Override
    public void swap(int a, int b) { 
	T tmp = array[a];
        
	array[a] = array[b]; 
	array[b] = tmp;
    }
    
    

    public boolean isEmpty() { return (size == 0); } 


    /* adding heap */
    public void add(T value) { 
	if(size == default_size) throw new IllegalStateException("Full array");
	array[size++] = value; 
	bubbleUp(); 
    }

    public void bubbleUp() { 
	if(size == 0) throw new IllegalStateException("Shape error");
	int index = size - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(array[index]) <= 0) break; 
	    /* else part */
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }  

    /* removing */

    @Override
    public T remove() {
	if(isEmpty()) return null; 
	T res = array[0]; /* root */
	array[0] = array[size-1]; 
	size --; 
	bubbleDown(); 
	return res;
    }

    @Override
    public void bubbleDown() { 
	/* implmement me */
        
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
        
    }
            
            


    public void show() {
	for(int i=0; i<size; i++) 
	    System.out.print(array[i] + " "); 
	System.out.println("=======");
    }


    public static void main(String [] args) {
	Heap heap = new Heap<Integer>(); 

	for(int i=0; i<10; i++) {
	    heap.add((int) (Math.random() * 10)); 
	    heap.show();
	}


	System.out.println("You should see sorted numbers");
	while(!heap.isEmpty()) 
	    System.out.print(heap.remove());
	
    }

    
    
       

}
	