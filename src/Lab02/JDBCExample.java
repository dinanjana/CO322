/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab02;

/**
 *
 * @author Dinanajana
 */
    import java.sql.*;

public class JDBCExample
{

	static final String DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.40.18.170/e11133"; //replace e11500 with your registration number

	static final String USERNAME = "e11133"; // replace e11500 with your registration number
	static final String PASSWORD = "";
   
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Connected database successfully...");

			System.out.println("Creating table in given database..."); //lets create a table in our database
			stmt = conn.createStatement();
			String sql = "CREATE TABLE testDatabase (number integer, name varchar(20));";  

			stmt.executeUpdate(sql);

			System.out.println("Done");

			System.out.println("Inserting records into the table..."); //now lets insert some data into our new table
      
			sql = "INSERT INTO testDatabase VALUES (100, 'Sasha')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO testDatabase VALUES (101, 'Natasha')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO testDatabase VALUES (102, 'Marsha')";
			stmt.executeUpdate(sql);

			System.out.println("Done");

			System.out.println("Reading the data back..."); // finally, lets read back the data that we wrote 
			
			sql = "SELECT number,name FROM testDatabase";
			rs = stmt.executeQuery(sql);

  			while(rs.next())
			{
				int number  = rs.getInt("number");
				String name = rs.getString("name");
				System.out.print("number: " + number);
				System.out.println(", name: " + name);
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
    

