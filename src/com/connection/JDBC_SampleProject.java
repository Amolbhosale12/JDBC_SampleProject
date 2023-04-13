/*
 * Create table name as employee with field id,name,email,contact ,sal and perform following operation on it.
case 1: Insert record of employee
case 2: select all records of employee
case 3: delete employee record by using its id.
case 4: update employee record by using its id
case 5: fetch employee record by using its name and id
case 6: fetch employee record who is taking second highest salary 
case 7: fetch employee record whose name ends with sh and start with r 

 */
package com.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
// here we need to handle SQLException 
public class JDBC_SampleProject  {
	public static void main(String []args) throws SQLException {
		Scanner sc= new Scanner(System.in);
	
		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();// here we create object of Driver class.
		DriverManager.registerDriver(d);
		//System.out.println("Load class successfully......");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/empInformation","root","amb123");// here we need to write username and password of database
		if(c!=null) {
			//System.out.println("connected....");
			Statement s=c.createStatement();
			do {
				System.out.println("enter 1 for Insert record of employee.");
				System.out.println("enter 2 for select all records of employee(show all record of empinfo table)");
				System.out.println("enter 3 for delete employee record by using its id.");
				System.out.println("enter 4 for update employee record by using its id.");
				System.out.println("enter 5 for fetch employee record by using its name and id.");
				System.out.println("enter 6 for fetch employee record who is taking second highest salary.");
				System.out.println("enter 7 for fetch employee record whose name ends with sh and start with r.");
				System.out.println("Enter your choice here:");
				int choice=sc.nextInt();
				switch(choice) {
				case 1: 
					System.out.println("enter data here:");
					int id=sc.nextInt();
					String name=sc.next();
					String gmail=sc.next();
					int contact=sc.nextInt();
					int sal=sc.nextInt();
					int result=s.executeUpdate("insert into empInfo values('"+id+"','"+name+"','"+gmail+"','"+contact+"','"+sal+"')");
					if(result!=0) {
     					System.out.println("data added.");
					}
					else {
						System.out.println("data not added.");
					}
					break;
				case 2:
					// if you want to fetch data from database we have to use executeQuery("query") method;
					ResultSet rr=s.executeQuery("select *from empinfo");
					while(rr.next()) {// next() method return true or false.if data is not present in table then return false otherwise return true; 
						int idd=rr.getInt("id");
						String n=rr.getString("name");
						String g=rr.getString("gmail");
						int con=rr.getInt("contact");
						int salary=rr.getInt("sal");
						System.out.println(idd+" "+n+" "+g+" "+con+" "+salary);
					}
					break;
				case 3:
					System.out.println("enter id here:");
					int ids=sc.nextInt();
					int value=s.executeUpdate("delete from empinfo where id='"+ids+"'");
					if(value!=0) {
						System.out.println("delete successfully...");
					}
					else {
						System.out.println("not deleted.");
					}
					break;
				case 4:
					System.out.println("enter id here:");
					int uid=sc.nextInt();
					System.out.println("enter updated record here:");
					String uname=sc.next();
					String ugmail=sc.next();
					int ucontact=sc.nextInt();
					int usal=sc.nextInt();
					int res=s.executeUpdate("update empinfo set name='"+uname+"', gmail='"+ugmail+"', contact='"+ucontact+"', sal='"+usal+"' where id='"+uid+"'");
					if(res!=0) {
						System.out.println("updated successfully.");
					}
					else {
						System.out.println("not updated.");
					}
					break;
				case 5:// fetch all information of employee using name and id
					System.out.println("enter 1 for search by name.");
					System.out.println("enter 2 for search by id.");
					System.out.println("enter your choice here:");
					int ch=sc.nextInt();
					switch(ch) {
					case 1:
						System.out.println("enter name here:");
						String nname=sc.next();
						// here logic for  search by name 
						ResultSet re=s.executeQuery("select *from empinfo where name='"+nname+"'");// all data of this query store in ResultSet interface(int reference.)
						while(re.next()) {
							int rid=re.getInt(1);
							String rname=re.getString(2);
							String rgmail=re.getString(3);
							int rcontact=re.getInt(4);
							int rsal=re.getInt(5);
							System.out.println(rid+" "+rname+" "+rgmail+" "+rcontact+" "+rsal);
						}
						break;
					case 2:
						// if user enter search using id follow below logic 
						System.out.println("enter id here:");
						int iid=sc.nextInt();
						ResultSet rs=s.executeQuery("select *from empinfo where id='"+iid+"'");
						while(rs.next()) {// if we want to fetch data from table according your requirement then we need to 
							// use next or loop still will not get false statement.
							int rrid=rs.getInt("id");
							String rrname=rs.getString("name");
							String rrgmail=rs.getString("gmail");
							int rrcontact=rs.getInt("contact");
							int rrsal=rs.getInt("sal");
							System.out.println(rrid+" "+rrname+" "+rrgmail+" "+rrcontact+" "+rrsal);
						}
						
						break;
					default:System.out.println("Invalid choice.");
					}
					break;
				case 6:
					// we want second highest salary employee details.
					System.out.println("second highest salary employee details:");
					ResultSet rt=s.executeQuery("select *From empinfo order by sal desc limit 1,1");
					while(rt.next()) {
						System.out.println(rt.getInt(1)+" "+rt.getString(2)+" "+rt.getString(3)+rt.getInt(4)+" "+rt.getInt(5));
					}
					break;
				case 7:
					// in this case we want whose name start from r and end with sh.
					System.out.println("data of employee:");
					ResultSet lt=s.executeQuery("select *from empinfo where name LIKE 'r%sh'");
					while(lt.next()) {
						System.out.println(lt.getInt(1)+" "+lt.getString(2)+" "+lt.getString(3)+lt.getInt(4)+" "+lt.getInt(5));
					}
					break;
				default:System.out.println("Invalid choice.");
				
				}
			}
			while(true);
			
		}
		else {
			System.out.println("Not connected.");
		}
	}

}
