package com.citi.prepaid.canon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;

public class MSSQLDDAByMemberDAOTest extends TestCase
{

  public void testGet()
  {
    String URL ="jdbc:sqlserver://ppamwdcpdsql1b1.nam.nsroot.net;instanceName=ppamwdcpdsql1b1;databaseName=EcountCore";
    String username = "csa";
    String password = "E2_Hn3C!e8";
    String results = null;
    
    JDBCDDAByMemberDAO dao = new MSSQLDDAByMemberDAO();
    
    try
    {    
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection(URL,username, password);
      
      dao.setConnection(conn);
      
      results = dao.get("030e40f7-7984-48c3-a584-93c351dc5242");
      
      conn.close();
      
    }
    catch (Exception e)
    {
      fail("Cannot connect to DB " + e.getMessage());
    }
    
    assertNotNull(results, results);
    
    
  }

}
