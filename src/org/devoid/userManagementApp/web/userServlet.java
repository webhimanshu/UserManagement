package org.devoid.userManagementApp.web;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import org.devoid.userManagementApp.dao.*;
import org.devoid.userManagementApp.model.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devoid.userManagementApp.*;
@WebServlet("/")
public class userServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String action=req.getServletPath();
	
	
	switch(action) 
	{
	case"/new":
		showNewForm(req,resp);
		break;
	case"/insert":
		try {
			insertUser(req,resp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		break;
	case"/delete":
		try {
			deleteUser(req,resp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		break;
	case"edit":
		try {
			showEditForm(req, resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		break;
	case"update":
		try {
			updateForm(req,resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		break;
	default:
		try {
			listUser(req,resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  break;
		
	}
	}
private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  
{
RequestDispatcher rd=req.getRequestDispatcher("user-form.jsp");
rd.forward(req, resp);


}

private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException ,ClassNotFoundException,IOException
{
String name=req.getParameter("name");
String email=req.getParameter("email");
String country=req.getParameter("country");
user newuser=new user(name,email,country);
userDAO u=new userDAO();
u.insertUser(newuser);
resp.sendRedirect("list");
}
private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException ,ClassNotFoundException,IOException
{
	int id=Integer.parseInt(req.getParameter("id"));
	userDAO u=new userDAO();
	u.deleteUser(id);
	resp.sendRedirect("list");
}

private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException ,ClassNotFoundException,IOException,ServletException
{
	int id=Integer.parseInt(req.getParameter("id"));
	userDAO u=new userDAO();
	user exitUser=u.selectUser(id);
	RequestDispatcher rd=req.getRequestDispatcher("user-form");
	req.setAttribute("user", exitUser);
	rd.forward(req, resp);
}
private void updateForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException ,ClassNotFoundException,IOException,ServletException
{
	int id=Integer.parseInt(req.getParameter("id"));
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String country=req.getParameter("country");
	user newuser=new user(id,name,email,country);
	userDAO u=new userDAO();
	u.updateUser(newuser);
	resp.sendRedirect("list");
}
private void listUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException ,ClassNotFoundException,IOException,ServletException
{
	userDAO u=new userDAO();
	List<user> listUser=u.selectAllUser();
	req.setAttribute("listuser", listUser);
	RequestDispatcher rd=req.getRequestDispatcher("user-list.jsp");
	rd.forward(req, resp);
}
}
