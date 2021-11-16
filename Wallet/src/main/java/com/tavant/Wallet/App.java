package com.tavant.Wallet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	Data data=new Data();

		System.out.println("\n1.Create an Account\n" + "2.Deposit(Add) money to the account\n"
				+ "3.View details of account.\n" + "4.Withdraw money from account.\n"+"5.Transfer Money.\n"
				+"6.View transactions.\n"
			    + "Press any other button to terminate the program.\n"
				+ "Enter the option:");
		
		int option;
		do {

			option = sc.nextInt();
			System.out.println("Enter your option:");
			System.out.println("*************************************");

			switch (option) {
			case 1:System.out.println("To create an account,Please provide the following details:");
				   System.out.println("Your Name:");
				   sc.nextLine();
				   String accName=sc.nextLine();
				   System.out.println("Password:");
				   String accPwd=sc.nextLine();
				   System.out.println("Date in the format yy-mm-dd:");
				   String dateOfCreation=sc.nextLine();
				   System.out.println("Amount:");
				   int amount=sc.nextInt();
				   data.addNewUser(accName, accPwd, dateOfCreation, amount);
				break;

			case 2:
					System.out.println("To Deposit the amount...Please enter your details");
					System.out.println("Enter your Name:");
					sc.nextLine();
					String addName=sc.nextLine();
					System.out.println("Enter password");
					String addPwd=sc.nextLine();
					System.out.println("Enter amount to be deposited:");
					int addMoney=sc.nextInt();
					data.depositMoney(addName, addPwd, addMoney);
				break;

			case 3:
					System.out.println("To view your account details:");
					System.out.println("Enter your Name:");
					sc.nextLine();
					String viewName=sc.nextLine();
					System.out.println("Enter your Password");
					String viewPwd=sc.nextLine();
					data.viewUser(viewName, viewPwd);	
				break;

				
			case 4:
				System.out.println("To withdraw the amount...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				String remName=sc.nextLine();
				System.out.println("Enter password");
				String remPwd=sc.nextLine();
				System.out.println("Enter amount to withdraw:");
				int remMoney=sc.nextInt();
				data.withdrawMoney(remName, remPwd,remMoney);
				break;
			case 5:
				System.out.println("To transfer the amount...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				String credName=sc.nextLine();
				System.out.println("Enter password");
				String credPwd=sc.nextLine();
				System.out.println("Enter reciever name:");
				String debtName=sc.nextLine();
				System.out.println("Enter amount to transfer:");
				int credMoney=sc.nextInt();
				data.transfer(credName, credPwd, debtName, credMoney);
				break;
			case 6:
				System.out.println("To view the transactions...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				String tracName=sc.nextLine();
				System.out.println("Enter password");
				String tracPwd=sc.nextLine();
				data.viewTransaction(tracName, tracPwd);
				break;

			default:
				System.out.println("No approriate option was selected");
				System.out.println("Program terminated");
				System.exit(0);
			}
			System.out.println("\n1.Create an Account\n" + "2.Deposit(Add) money to the account\n"
					+ "3.View details of account.\n" + "4.Withdraw money from account.\n"+"5.Transfer Money.\n"
				    + "6.View transactions.\n"+"Press any other button to terminate the program.\n"
					+ "Enter the option:");
		} while (option <= 6);

	}
}

class Data{
	public static final String URLTOCONNECT = "jdbc:mysql://localhost:3306/Accounts";
	
	public static final String USERNAME = "root";
	
	public static final String USERPASSWORD = "password";
	
	String qry,query;
	Connection dbCon;
	Statement theStatement;
	ResultSet theResultSet;
	PreparedStatement thePreparedStatement;
	
	Data(){
		try {
//          Load the DB Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			dbCon = DriverManager.getConnection(URLTOCONNECT, USERNAME, USERPASSWORD);
			
			theStatement = dbCon.createStatement();
			
//			System.out.println("Connected to the database now...");	
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to the database : " + e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		 
		final String URLTOCONNECT = "jdbc:mysql://localhost:3306/Accounts";
		
		final String USERNAME = "root";
		
		final String USERPASSWORD = "password";
 
        Connection connection = null;
        try {
        	  Class.forName("com.mysql.cj.jdbc.Driver");
 
              connection = DriverManager.getConnection(URLTOCONNECT, USERNAME,
              USERPASSWORD);
 
        } catch (ClassNotFoundException e) {
            System.err.println("Where is your Oracle JDBC Driver?\n");
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Connection Failed.\n");
            e.printStackTrace();
            System.exit(1);
        }
 
        return connection;
    }
	
	void addNewUser(String accName, String accPwd, String dateOfCreation,Integer amount) {
    	qry = "insert into accountdetails(accName, accPwd, dateOfCreation,amount) values(?, ?, ?, ?)";
    	
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, accPwd);
			thePreparedStatement.setString(3, dateOfCreation);
			thePreparedStatement.setInt(4, amount);
			
//			Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Account created successfully...");
		} catch (SQLException e) {
			System.out.println("Issues with PreparedStatement insert query : " + e.getMessage());
		}
    }
	
	void depositMoney(String accName, String accPwd,Integer money) {
    	qry = "select * from accountdetails where accName=? and accPwd=?";
    	Integer prevAmount=0;
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, accPwd);
//			Execute the query
			ResultSet rs=thePreparedStatement.executeQuery();	
				while(rs.next())
				{
					prevAmount=rs.getInt(5);
				}
		} catch (SQLException e) {
			System.out.println("Error while depositing : " + e.getMessage());
		}
    	
    	Integer newAmount=prevAmount+money;
    	
    	qry="update accountdetails set amount=? where accName=? and accpwd=? ";
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setInt(1, newAmount);
			thePreparedStatement.setString(2, accName);
			thePreparedStatement.setString(3, accPwd);
//			Execute the query
			if(thePreparedStatement.executeUpdate() > 0) {
				System.out.println("Amount Deposited successfully...");
				System.out.println("Updated balance: "+newAmount);
			}
		} catch (SQLException e) {
			System.out.println("Issues while depositing : " + e.getMessage());
		}
    }
	
	void withdrawMoney(String accName, String accPwd,Integer money) {
    	qry = "select * from accountdetails where accName=? and accPwd=?";
    	Integer prevAmount=0;
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, accPwd);
//			Execute the query
			ResultSet rs=thePreparedStatement.executeQuery();	
				while(rs.next())
				{
					prevAmount=rs.getInt(5);
				}
		} catch (SQLException e) {
			System.out.println("Error while depositing : " + e.getMessage());
		}
    	if(prevAmount>money) {
    	
    	Integer newAmount=prevAmount-money;
    	
    	qry="update accountdetails set amount=? where accName=? and accpwd=? ";
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setInt(1, newAmount);
			thePreparedStatement.setString(2, accName);
			thePreparedStatement.setString(3, accPwd);
//			Execute the query
			if(thePreparedStatement.executeUpdate() > 0) {
				System.out.println("Amount Successfully withdrawn...");
				System.out.println("Updated balance: "+newAmount);
			}
		} catch (SQLException e) {
			System.out.println("Issues while depositing : " + e.getMessage());
		}
    }
    	else {
    		System.out.println("Insufficient funds..........");
    		System.exit(0);
    	}
	}

	
	
	
	void viewUser(String accName, String accPwd) {
    	qry = "select * from accountdetails where accName=? and accPwd=?";
    	
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, accPwd);
//			Execute the query
			ResultSet rs=thePreparedStatement.executeQuery();	
				while(rs.next())
				{
					System.out.println("*********************************");
					System.out.println("Account Holder Name:"+rs.getString(2));
					System.out.println("Date of Creation:"+rs.getString(4));
					System.out.println("Amount in the account:"+rs.getInt(5));
				}
		} catch (SQLException e) {
			System.out.println("Issues while Viewing--Could not find the specified record : " + e.getMessage());
		}
    }
	
	void transfer(String accName, String accPwd,String transName,Integer Amount) {
    	qry = "Insert into transaction(paidByName,paidToName,Amount) values(?,?,?)";
    	
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, transName);
			thePreparedStatement.setInt(3, Amount);
//			Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Amount transferred successfully to "+transName);
		} catch (SQLException e) {
			System.out.println("Issues while Viewing--Could not find the specified record : " + e.getMessage());
		}
    	withdrawMoney(accName, accPwd, Amount);
    	qry = "select * from accountdetails where accName=?";
    	Integer prevAmount=0;
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, transName);
//			Execute the query
			ResultSet rs=thePreparedStatement.executeQuery();	
				while(rs.next())
				{
					prevAmount=rs.getInt(5);
				}
		} catch (SQLException e) {
			System.out.println("Error while depositing : " + e.getMessage());
		}
    	
    	Integer newAmount=prevAmount+Amount;
    	
    	qry="update accountdetails set amount=? where accName=?";
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setInt(1, newAmount);
			thePreparedStatement.setString(2, transName);
//			Execute the query
			if(thePreparedStatement.executeUpdate() > 0) {
				System.out.println("*************************************");
				System.out.println("Account balance of :"+transName);
				System.out.println("Amount Credited by :"+accName);
				System.out.println("Updated balance: "+newAmount);
			}
		} catch (SQLException e) {
			System.out.println("Issues while transferring : " + e.getMessage());
		}   	
	}
	
	
	void viewTransaction(String accName, String accPwd) {
    	qry = "select * from transaction where paidToName=? or paidByName=?";
    	
    	try {
//        	Get the PreparedStatement object
			thePreparedStatement = dbCon.prepareStatement(qry);

//			Put the values for ?
			thePreparedStatement.setString(1, accName);
			thePreparedStatement.setString(2, accName);
//			Execute the query
			ResultSet rs=thePreparedStatement.executeQuery();	
				while(rs.next())
				{
					System.out.println("*********************************");
					System.out.println("List of transactions......");
					System.out.println("Paid to:"+rs.getString(3));
					System.out.println("Paid by:"+rs.getString(2));
					System.out.println("Amount:"+rs.getInt(4));
				}
		} catch (SQLException e) {
			System.out.println("Issues while Viewing--Could not find the specified record : " + e.getMessage());
		}
    }
}
