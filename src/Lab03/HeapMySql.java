/* READ ME!!!!!
 * This will create testHeap table on Heap database
 * 
 * 
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
public class HeapMySql<T extends Comparable<Integer>> implements HeapInterface {

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
            sql = "CREATE TABLE testHeap (indexnum integer, value integer);"; 
            stmt.executeUpdate(sql);
            System.out.println("Done");
        }catch(Exception e){
          e.printStackTrace();
        }        
    
    }
     
    
    @Override
    public int leftChild(int index)  {
        return 2 * index + 1;  
    }
    @Override
    public int parent(int index)     { 
        return (int)((index - 1) / 2); 
    }
    @Override
    public int rightChild(int index) { 
        return 2 * index + 2;   
    }
    
    

    @Override
    public Integer parentValue(int index) {
        
        int parent_index = parent(index);
        
        sql = "SELECT value FROM testHeap WHERE indexnum =" + parent_index ;
        
        try {
        
            ResultSet a = stmt.executeQuery(sql);
        
        if(a.next()){
            
            Object b = a.getObject("value");
            Integer c = (Integer)b;
        
            return c;
        }
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
            if(temp.next()){
                int t = (int)temp.getLong(1);
                
                sql = "SELECT value FROM testHeap WHERE indexnum = " +b;
                temp = stmt.executeQuery(sql);
                
                if(temp.next()){
                
                    int t2 = (int)temp.getLong(1);
                    sql = "UPDATE testHeap "
                        + "SET value = "+ t2
                        + " WHERE indexnum = "+ a;
                    //System.out.println(sql);
                    stmt.executeUpdate(sql);
            
                    sql = "UPDATE testHeap "
                        +"SET value ="+ t 
                        +" WHERE indexnum ="+ b;
            
                    stmt.executeUpdate(sql);
                }
            }
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
            if (size ==0) return;
            int index = size - 1;  
            while(size != 0) {
            
                sql = "SELECT value FROM testHeap WHERE indexnum ="+index;
                ResultSet val;
                try {
                    stmt.close();
                    stmt = conn.createStatement();
                    val =  stmt.executeQuery(sql);
                    if(val.next()){
                        
                        Object d = val.getObject("value");
                        Integer e = (Integer)d;
                        Integer parent = parentValue(index);
                        
                            if(parent.compareTo(e)<= 0) break; 
                        
                        swap(parent(index), index); 
                        index = parent(index);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        
	
	    
	
    }
    public void SQLupdate (int indexnum,int value) throws SQLException{
        
        sql = "UPDATE testHeap SET value = " +value 
                +" WHERE indexnum = " + indexnum;
        
        stmt.executeUpdate(sql);
        
        
    }
    public void SQLdelete(int indexnum) throws SQLException{
        sql = "DELETE FROM testHeap "
                + "WHERE indexnum = "+indexnum;
    
        stmt.executeUpdate(sql);
    }
    
    public int SQLQuery (int indexnum) throws SQLException{
        
        sql = "SELECT value "
                + "FROM testHeap "
                + "WHERE indexnum = " + indexnum;
        
        rs = stmt.executeQuery(sql);
        
        if(rs.next()) return rs.getInt(1);
        
        return 0;
    }

    @Override
    public Comparable removeElement() {
        if(isEmpty()) return null; 
	
        int res;
        try {
            res = SQLQuery(0);
            int last = SQLQuery(size);
            SQLdelete(size);
            SQLupdate(0,last);
            size--;
            int index = 0;
        
            while(index < size ){
                
                
                if(hasLeftChild(index)&&
                        hasRightChild(index)){
                    
                        //check for equal cases
                        if(SQLQuery(leftChild(index)) >= SQLQuery(rightChild(index))){
                    
                            if(SQLQuery(index)>=
                            SQLQuery(rightChild(index))){
                    
                                swap(index,rightChild(index));
                        
                                index = rightChild(index);
                            }else{
                            
                            break;
                            }
                            
                        }
                        //check left
                        else if(SQLQuery(leftChild(index))<
                                SQLQuery(rightChild(index))){
                            
                            if(SQLQuery(index)>
                              SQLQuery(leftChild(index))){
                        
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
                    
                        if(SQLQuery(index)>
                              SQLQuery(leftChild(index))){
                        
                            swap(index,leftChild(index));
                        
                            index = leftChild(index);
                        
                        }else{
                        
                            break;
                        }
                
                
                
                }
                // Right child only
                if(hasRightChild(index) && !hasLeftChild(index)){
                
                    if(SQLQuery(index)>
                            SQLQuery(rightChild(index))){
                    
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
        } catch (SQLException ex) {
            Logger.getLogger(HeapMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
	
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
                test1.addElement((int) (Math.random() * 1000));
                
                test1.show();
            }
            System.out.println("Sorted List");
            
            test1.removeElement();
            for(int i=0; i<100; i++) {
                System.out.print(test1.removeElement()+ " ");
                
                
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
