package com.core.util;

import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;  
  
public class JdbcUtils {  

    private static Connection connection;  
    private PreparedStatement pstmt;  

    public boolean initTable(String dbname) {
    	    boolean flag = false;   
     	try {
			Class.forName("com.mysql.jdbc.Driver");
		  	connection = DriverManager.getConnection("jdbc:mysql://192.168.10.32:3306/"+dbname,"root","PMC_BJ_test_0118");
		  	//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname,"root","Wdt1cWbFII0I1spS");
	        ScriptRunner runner = new ScriptRunner(connection);
	        try {
				runner.runScript(Resources.getResourceAsReader("NH_table.sql"));
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	     	try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
    		return flag;
    }
    public boolean initDatabse(String sql){  
        boolean flag = false;  
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	connection = DriverManager.getConnection("jdbc:mysql://192.168.10.32:3306/","root","PMC_BJ_test_0118");

        	//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","Wdt1cWbFII0I1spS");
            pstmt = connection.prepareStatement(sql);  
            int result = pstmt.executeUpdate();  
            flag = result > 0 ? true : false;  
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				connection.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
        return flag;  
    }  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws SQLException {  
        // TODO Auto-generated method stub  
        JdbcUtils jdbcUtils = new JdbcUtils();
        String databaseName ="NH11";
        String createdatabase ="create DATABASE `"+databaseName+"` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
        boolean ok=jdbcUtils.initDatabse(createdatabase);
        jdbcUtils.initTable(databaseName);
  
    }  
  
}  