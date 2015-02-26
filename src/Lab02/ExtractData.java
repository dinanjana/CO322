/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashMap;

/**
 *
 * @author Dinanajana
 */
public class ExtractData {
    static final String DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/co322data"; //replace e11500 with your registration number 10.40.18.170

	static final String USERNAME = "root";//"e11133"; // replace e11500 with your registration number
	static final String PASSWORD = "";
        final ArrayList<StudentData> relation = new ArrayList<> ();
        
   
	public ExtractData()
	{
                
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
                        //conn = DriverManager.
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Connected database successfully...");

			System.out.println("Creating table in given database..."); //lets create a table in our database
			stmt = conn.createStatement();
			
                        String sql = "SELECT* FROM studentdata";
			
			System.out.println("Reading the data back..."); // finally, lets read back the data that we wrote 
		
			rs = stmt.executeQuery(sql);

  			while(rs.next())
			{
                                double weight = rs.getDouble("weight");
				double height  = rs.getDouble("height");
                                double age = rs.getDouble("age");
				String regno = rs.getString("regno");
                                String gender = rs.getString("gender");
                                
				//System.out.println("number: " + gender);
				/*System.out.println("name: " + regno);
                                System.out.println("age: " + age);
                                System.out.println("height: " + height);
                                System.out.println("weight: " + weight);*/
                                if("Female".equals(gender)){
                                StudentData student = new StudentData(regno,1.25*(weight+height+age));
                                //System.out.println("number: " + gender + 1.25*(weight+height+age));
                                relation.add(student);
                                }else{
                                StudentData student = new StudentData(regno,(weight+height+age));
                                //System.out.println("number: " + gender + (weight+height+age));
                                relation.add(student);
                                }
                                
                                
                                
                                
			}
                        
                        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!= null)
					rs.close();
				if(stmt!=null)
					conn.close();
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

