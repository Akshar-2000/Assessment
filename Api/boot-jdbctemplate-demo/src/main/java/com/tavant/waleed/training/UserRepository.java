package com.tavant.waleed.training;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class UserRepository {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	int getCountOfUsers() {
		return jdbcTemplate.queryForObject(
				"select count(*) from accountdetails", 
				Integer.class);
	}

	public void addNewUser(Accounts acc) {
		String qry="insert into accountdetails(accName, accPwd, dateOfCreation,amount) values(?, ?, ?, ?)";
		jdbcTemplate.update(qry,new Object[] {acc.getAccName(),acc.getAccPwd(),acc.getDateOfCreation(),acc.getAmount()});		
	}
	
	public List<Accounts> users(){
		return jdbcTemplate.query("select * from accountdetails", new UserMapper());
	}
	
	@SuppressWarnings("deprecation")
	public Accounts viewUser(Accounts acc) {
		return jdbcTemplate.queryForObject("select * from accountdetails where accName=? and accPwd=?",new UserMapper(),acc.getAccName(),acc.getAccPwd());
	}
	
	
	public void updateDetails(int id,Accounts acc) {
		jdbcTemplate.update("update accountdetails set accName=?,accPwd=? where Id=?",new Object[] {acc.getAccName(),acc.getAccPwd(),id});
	}
	
	public void deleteAccountDetails(Accounts acc) {
		jdbcTemplate.update("delete from accountdetails where accName=? and accPwd=?",new Object[] {acc.getAccName(),acc.getAccPwd()},new UserMapper());
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

		}
		}
}
