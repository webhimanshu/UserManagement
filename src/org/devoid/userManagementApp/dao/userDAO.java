 package org.devoid.userManagementApp.dao;
import org.devoid.userManagementApp.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

// dao basically stands for data access object it is design pattern to separate all the  database
//all the database stuff
public class userDAO {
private String  jdbcUrl="jdbc:mysql://localhost:3306/demo";
private String user="root";
private String password="admin";

private static final String INSERT_USERS_SQL="INSERT INTO  user  (name, email, country) VALUSE (?,?,?) ";
private static final String SELECT_USER_BY_ID="SELECT name,email,country WHERE id=?";
private static final String SELECT_ALL_USERS="SELECT * FROM  user";
private static final String DELETE_USERS_SQL="DELETE FROM USER WHERE id=?";
private static final String UPDATE_USERS_SQL="UPDATE USER SET name=?, email=?,country=? WHERE id=?";

//connection method
protected Connection getConnection() {
	Connection con=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection(jdbcUrl);
	}
	catch(ClassNotFoundException | SQLException e) 
	{
		e.printStackTrace();
	
	}
	finally {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return con;
	
	
}


//create or insert user

public int  insertUser(user u) throws ClassNotFoundException ,SQLException{
	PreparedStatement pstmt=null;
	int status=0;
	try {
		Connection con=getConnection();
	 pstmt=con.prepareStatement(INSERT_USERS_SQL);
		pstmt.setString(1, u.getName());
		pstmt.setString(2, u.getEmail());
		pstmt.setString(3, u.getCountry());
		status=pstmt.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally
	{
		if(pstmt!=null)
		{
			pstmt.close();
		}
	}
	return status;
	
}

//update user

public int   updateUser(user u) {
	PreparedStatement pstmt=null;
	int status=0;
	try 
	{
		Connection con=getConnection();
		pstmt=con.prepareStatement(UPDATE_USERS_SQL);
		pstmt.setString(1, u.getName());
		pstmt.setString(2, u.getEmail());
		pstmt.setString(3, u.getCountry());
		pstmt.setInt(4, u.getId());
		status=pstmt.executeUpdate();
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
 	if(pstmt!=null) 
	{
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	}
	return status;
}
//select user by id
public user selectUser(int id) 
{
	user u=null;
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	try 
	{
		Connection con=getConnection();
		pstmt=con.prepareStatement(SELECT_USER_BY_ID);
		pstmt.setInt(1,id);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			String name=rs.getString("name");
			String email=rs.getString("email");
			String country=rs.getString("country");
			 u=new user(name, email,country);
		}
		
	}
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	return u;

}

//select ALL  user
public List<user>selectAllUser() 
{
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	List<user> al=new ArrayList<user>();
try 
{
	
	Connection con=getConnection();
	pstmt=con.prepareStatement(SELECT_ALL_USERS);
	rs=pstmt.executeQuery();
	while(rs.next()) 
	{
		int id=rs.getInt("id");
		String name=rs.getString("name");
		String email=rs.getString("email");
		String country=rs.getString("country");
		al.add(new user(id, name, email, country));
	}
	
}
catch(Exception e) 
{
	e.printStackTrace();
}
return al;
	
}


//delete user
public int deleteUser(int id) 
{
PreparedStatement pstmt=null;
int status=0;
try 
{
Connection con=getConnection();
pstmt=con.prepareStatement(DELETE_USERS_SQL);
pstmt.setInt(1, id);
status=pstmt.executeUpdate();

}
catch(Exception e) 
{
	e.printStackTrace();
}
 return status;
}

}
