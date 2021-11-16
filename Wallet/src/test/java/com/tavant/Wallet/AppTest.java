package com.tavant.Wallet;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class AppTest {
	@Test
	public void testConnection() throws Exception{
		Connection result=Data.getConnection();
		assertEquals(result!=null, true);
	}
	
	@Test
	public void testColumn() throws Exception{
		Statement st;
		Connection con=Data.getConnection();
		st=con.createStatement();
		String testqry="select * from accountdetails";
		ResultSet rs=st.executeQuery(testqry);
		java.sql.ResultSetMetaData rsmd=rs.getMetaData();
		String col1=rsmd.getColumnName(2);
		assertEquals("accName", col1);
	}
}

