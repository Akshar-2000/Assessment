package com.tavant.Spring_parallel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class DatabaseOperations {
	
	String qry;
	DataSource dataSoure;
	JdbcTemplate jdbcTemplate;
	
	public DataSource getDataSoure() {
		return dataSoure;
	}
	
	@Autowired
	public void setDataSoure(DataSource dataSoure) {
		jdbcTemplate =new JdbcTemplate(dataSoure);
	}

	//All METHODS HERE...
	
	int getUserount() {
		return(jdbcTemplate.queryForObject("select count(*) from accountdetails as userCount", Integer.class));
	}
	
	void addNewUser(Accounts acc) {
		qry="insert into accountdetails(accName, accPwd, dateOfCreation,amount) values(?, ?, ?, ?)";
		jdbcTemplate.update(qry,new Object[] {acc.getAccName(),acc.getAccPwd(),acc.getDateOfCreation(),acc.getAmount()});
	}
	
	int depositMoney(String accName, String accPwd,Integer money) {
		Integer prevAmount=0;
		prevAmount=jdbcTemplate.queryForObject("select amount from accountdetails where accName=? and accPwd=?",new Object[] {accName,accPwd}, Integer.class);
		Integer newAmount=prevAmount+money;
		jdbcTemplate.update("update accountdetails set amount=? where accName=? and accpwd=? ", newAmount,accName,accPwd);
		newAmount=jdbcTemplate.queryForObject("select amount from accountdetails where accName=? and accPwd=?",new Object[] {accName,accPwd}, Integer.class);
		return newAmount;
	}
	
	int withdrawMoney(String accName, String accPwd,Integer money) {
		Integer prevAmount=0,newAmount=0;
		prevAmount=jdbcTemplate.queryForObject("select amount from accountdetails where accName=? and accPwd=?",new Object[] {accName,accPwd}, Integer.class);
		if(prevAmount>money)
			newAmount=prevAmount-money;
		else
			System.exit(0);
		jdbcTemplate.update("update accountdetails set amount=? where accName=? and accpwd=? ", newAmount,accName,accPwd);
		newAmount=jdbcTemplate.queryForObject("select amount from accountdetails where accName=? and accPwd=?",new Object[] {accName,accPwd}, Integer.class);
		return newAmount;
	}
	
	int transfer(String accName,String accPwd,String transName,Integer money) {
		qry="Insert into transaction(paidByName,paidToName,Amount) values(?,?,?)";
		jdbcTemplate.update(qry,new Object[] {accName,transName,money});
		withdrawMoney(accName, accPwd, money);
		Integer prevAmount=jdbcTemplate.queryForObject("select amount from accountdetails where accName=?", new Object[] {transName},Integer.class);
		Integer newAmount=prevAmount+money;
		qry="update accountdetails set amount=? where accName=?";
		jdbcTemplate.update(qry,newAmount,transName);
		return newAmount;
	}
	
	Transaction viewTransaction(String accName,String accPwd) {
		return jdbcTemplate.queryForObject("select * from transaction where paidToName=? or paidByName=?", new Object[] {accName,accName},new TransMapper());
	}
	
	Accounts viewUser(String accName, String accPwd) {
		return jdbcTemplate.queryForObject("select * from accountdetails where accName=? and accPwd=?", new Object[] {accName,accPwd},new UserMapper());
	}
	
	
	class UserMapper implements RowMapper<Accounts>{
		@Override
		public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
			Accounts acc=new Accounts();
			acc.setId(rs.getInt("Id"));
			acc.setAccName(rs.getString("accName"));
			acc.setAccPwd(rs.getString("accPwd"));
			acc.setDateOfCreation(rs.getString("dateOfCreation"));
			acc.setAmount(rs.getInt("amount"));	
			return acc;

		}}
	
	class TransMapper implements RowMapper<Transaction>{
		@Override
		public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transaction tran=new Transaction();
			tran.setId(rs.getInt("Id"));
			tran.setPaidByName(rs.getString("paidByName"));
			tran.setPaidToName(rs.getString("paidToName"));
			tran.setAmount(rs.getInt("Amount"));
			return tran;
		}
		
	}
	
//	Connection dbCon;(Connection Already Established through dataSource variable)
//	void connectToDb() {
//		try {
//			dbCon=dataSoure.getConnection();
//			if(dbCon!=null)
//				System.out.println("Connected....");
//		} catch (SQLException e) {
//			System.out.println("Issues while Connecting"+ e.getMessage());
//		}
//	}
	
	
}
