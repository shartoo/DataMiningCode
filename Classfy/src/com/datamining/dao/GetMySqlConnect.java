package com.datamining.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 
 * @author 夏涛
 * email：shartoo518@gmail.com
 */
public class GetMySqlConnect
 {
   public String  driverName = "";
   private String userName;
   private String userPwd;
   private String url;
   String table = "";
 
   public String getDriverName() {
     return this.driverName;
   }
 
   public String getUserName() {
     return this.userName;
  }
   public String getUserPwd() {
     return this.userPwd;
   }
 
   public String getUrl() {
     return this.url;
   }
   private void GetConfig()
   {
     try
    {
       Properties prop = new Properties();
 
       InputStream inputStream = getClass().getClassLoader().getResourceAsStream("DBConnection.properties");
 
       prop.load(inputStream);
 
       this.driverName = prop.getProperty("driver");
 
       this.url = prop.getProperty("url");
 
       this.userName = prop.getProperty("user");
 
       this.userPwd = prop.getProperty("password");
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 
   public Connection getConnection()
   {
     GetConfig();
 
     Connection conn = null;
     try
     {
      Class.forName(this.driverName);
 
       conn = DriverManager.getConnection(this.url, this.userName, this.userPwd);
 
       if (!conn.isClosed())
       {
         System.out.println("Succeeded connecting to the Database!");
       }
     }
     catch (ClassNotFoundException e)
    {
      System.out.println("Sorry,can`t find the Driver!");
       e.printStackTrace();
     }
     catch (SQLException e)
     {
      e.printStackTrace();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
 
     if (conn == null)
       System.out.println("连接失败！--来自GetMySqlConnect类");
     return conn;
   }
 
   public void test() throws SQLException
   {
     Connection conn = getConnection();
     System.out.println("driverName=" + this.driverName);
     System.out.println("dbURL=" + this.url);
     System.out.println("userName=" + this.userName);
     System.out.println("userPwd=" + this.userPwd);
 
     Statement st = conn.createStatement();
 
     String sql = "select *  from test where id<'5'";
     ResultSet rs = st.executeQuery(sql);
     while (rs.next())
     {
       String s = rs.getString("name");
       int id = rs.getInt("id");
       System.out.println("name=" + s);
       System.out.println("用户id=" + id);
     }
   }
 
   public static void main(String[] args)
     throws SQLException
   {
     GetMySqlConnect c = new GetMySqlConnect();
     c.test();
   }
 }
