package com.citi.prepaid.canon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MSSQLDDAByMemberDAO extends JDBCDDAByMemberDAO
{

  public String get(String memberId)
  {

    String SQL = "SELECT dbo.app_func_get_dda_by_member(?) AS DDA";
    String dda = null;
    PreparedStatement pstmt = null;

    try
    {
      Connection con = getConnection();      

      pstmt = con.prepareStatement(SQL);

      pstmt.setString(1, memberId);
      ResultSet rs = pstmt.executeQuery();
      
      while ( rs.next() )
      {
        dda = rs.getString("DDA");
      }
    }
    catch ( SQLException se )
    {
      
      se.printStackTrace();
      
      if (pstmt != null)
      {
        try
        {
          pstmt.close();
        }
        catch (SQLException e )
        {
          throw new RuntimeException(e);
        }
      }
    }

    return dda;



  }

}
