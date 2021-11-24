package com.tavant.Spring_parallel;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	String userName,userPwd,dateOf;
    	Integer amount,result;
    	Scanner sc=new Scanner(System.in);
    	ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("config.xml");
    	DatabaseOperations theOperate=theContext.getBean("databaseOperations",DatabaseOperations.class);
    	
    	
    	System.out.println("Number of Account Holders: "+theOperate.getUserount());
    	
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
				   userName=sc.nextLine();
				   System.out.println("Password:");
				   userPwd=sc.nextLine();
				   System.out.println("Date in the format yy-mm-dd:");
				   dateOf=sc.nextLine();
				   System.out.println("Amount:");
				   amount=sc.nextInt();
				   theOperate.addNewUser(new Accounts(userName,userPwd,dateOf,amount));
				break;

			case 2:
					System.out.println("To Deposit the amount...Please enter your details");
					System.out.println("Enter your Name:");
					sc.nextLine();
					userName=sc.nextLine();
					System.out.println("Enter password");
					userPwd=sc.nextLine();
					System.out.println("Enter amount to be deposited:");
					amount=sc.nextInt();
					result=theOperate.depositMoney(userName, userPwd, amount);
					if(result>0) {
						System.out.println("Amount Deposited successfully...");
						System.out.println("Updated balance: "+result);
					}
					else
						System.out.println("Error While Depositing.....");
				break;

			case 3:
					System.out.println("To view your account details:");
					System.out.println("Enter your Name:");
					sc.nextLine();
					userName=sc.nextLine();
					System.out.println("Enter your Password");
					userPwd=sc.nextLine();
					theOperate.viewUser(userName, userPwd);
			    	System.out.println("*********************************");
					System.out.println("Account Holder Name:"+theOperate.viewUser(userName, userPwd).getAccName());
					System.out.println("Date of Creation:"+theOperate.viewUser(userName, userPwd).getDateOfCreation());
					System.out.println("Amount in the account:"+theOperate.viewUser(userName, userPwd).getAmount());
				break;

				
			case 4:
				System.out.println("To withdraw the amount...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				userName=sc.nextLine();
				System.out.println("Enter password");
				userPwd=sc.nextLine();
				System.out.println("Enter amount to withdraw:");
				amount=sc.nextInt();
				result=theOperate.withdrawMoney(userName, userPwd, amount);
				if(result>0) {
					System.out.println("Amount Withdrawn successfully...");
					System.out.println("Updated balance: "+result);
				}
				else
					System.out.println("Error While Withdrawing.....");
				break;
				
				
			case 5:
				System.out.println("To transfer the amount...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				userName=sc.nextLine();
				System.out.println("Enter password");
				userPwd=sc.nextLine();
				System.out.println("Enter reciever name:");
				String debtName=sc.nextLine();
				System.out.println("Enter amount to transfer:");
				amount=sc.nextInt();
				result=theOperate.transfer(userName, userPwd, debtName, amount);
				System.out.println("Amount "+amount+" transferred successfully to "+debtName+" by "+userName);
				break;
				
				
			case 6:
				System.out.println("To view the transactions...Please enter your details");
				System.out.println("Enter your Name:");
				sc.nextLine();
				userName=sc.nextLine();
				System.out.println("Enter password");
				userPwd=sc.nextLine();
				theOperate.viewTransaction(userName, userPwd);
				System.out.println("*********************************");
				System.out.println("List of transactions......");
				System.out.println("Paid to:"+theOperate.viewTransaction(userName, userPwd).getPaidToName());
				System.out.println("Paid by:"+theOperate.viewTransaction(userName, userPwd).getPaidByName());
				System.out.println("Amount:"+theOperate.viewTransaction(userName, userPwd).getAmount());
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
