/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab03;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinanajana
 * @param <T>
 */
public class HeapMySql<T extends Comparable<T>> implements HeapInterface {

    static final String DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/Heap"; 

    static final String USERNAME = "root";
    static final String PASSWORD = "";
    
    private int size = 0 ;
    String sql;
    static Statement stmt = null;
    static Connection conn = null;
    static ResultSet rs = null;
    
    public HeapMySql(){
  
        try
        {		
            sql = "CREATE TABLE testHeap (indexnum integer, value integer);";  //"CREATE TABLE testHeap (index integer, value integer);";  
            stmt.executeUpdate(sql);
            System.out.println("Done");
        }catch(Exception e){
          e.printStackTrace();
        }        
    
    }
     
    
    @Override
    public int leftChild(int index)  { return 2 * index + 1;  }
    @Override
    public int parent(int index)     { return (int)((index - 1) / 2); }
    @Override
    public int rightChild(int index) { return 2 * index + 2;   }
    
    

    @Override
    public T parentValue(int index) {
        
        int parent_index = parent(index);
        
        sql = "SELECT value FROM testHeap WHERE indexnum =" + parent_index ;
        
        try {
            return (T)stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }

    @Override
    public boolean hasLeftChild(int i) {
        return leftChild(i) < size;
    
    }

    @Override
    public boolean hasRightChild(int i) {
 
        return rightChild(i) < size;
    }

    @Override
    public void swap(int a, int b) {
        
        sql = "SELECT value FROM testHeap WHERE indexnum ="+a;
        try {
 
            ResultSet temp = stmt.executeQuery(sql);
            int t = (int)temp.getLong(1);
                       
            sql = "UPDATE testHeap "
                    + "SET value = "
                    + "(SELECT value FROM "
                    + "testHeap WHERE indexnum =" +b+ ")"
                    + "WHERE index ="+ a;
            stmt.executeUpdate(sql);
            
            sql = "UPDATE testHeap "
                    +"SET value ="+ t 
                    +"WHERE indexnum ="+ b;
            
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public boolean isEmpty() {
        
        return (size == 0);
    }

    @Override
    public void addElement(Comparable value) {
        
        sql = "INSERT INTO testHeap VALUES("+size+","+(int)value+ ") ";
        try {
            stmt.executeUpdate(sql);
            size++;
            if (size ==1) return;
            int index = size - 1;  
            while(size != 0) {
            
                sql = "SELECT value FROM testHeap WHERE indexnum ="+index;
                T val;
                try {
                    val =  (T)stmt.executeQuery(sql).getObject(1);
                        if((parentValue(index)).compareTo(val) <= 0) break; 
                        
                        swap(parent(index), index); 
                        index = parent(index);
                } catch (SQLException ex) {
                    Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        
	
	    
	
    }

    @Override
    public Comparable removeElement() {
        size--;
        return null;
    }
    
    public void show(){
        sql = "SELECT value from testHeap";
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next()){
            
                System.out.println(rs.getInt("value")+ " ");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static void main(String [] arg){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating table in given database..."); 
            stmt = conn.createStatement();
        
            HeapMySql test1 = new HeapMySql<>();
            for(int i=0; i<100; i++) {
                test1.addElement((int) (Math.random() * 100)); 
                test1.show();
            }
        
        }catch(ClassNotFoundException | SQLException ex){
        
        }finally{
            
                try
		{
                    if(rs!= null)
                    rs.close();
                    if(stmt!=null)
                    stmt.close();
                    if(conn!=null)
                    conn.close();
		}
		catch(SQLException se)
		{
                    se.printStackTrace();
		}
            
        }
        
    }
    
}
