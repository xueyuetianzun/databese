package demo;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import Login_view.Login;
import Login_view.TeacherMenu;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

//import java.util.Scanner;

public class Appsu {
	 private static Connection con = null;
	public static Connection sendConnection() {try {
		 String dbUserName = "sa";//用户名
		 String dbPassword = "root";//密码
		 String url = "jdbc:sqlserver://localhost:1433;DataBaseName=studentManage;integratedSecurity=false; ";//DataBaseName=Test数据库的名称
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(url, dbUserName, dbPassword);
		System.out.println("成功连接到数据库。");
	} catch (Exception e) {

		e.printStackTrace();

	} finally {
	}
	return con;}
public static void main(String[] args) {
 new TeacherMenu();
new Login();
//	Connection con1 =sendConnection();
//	String sql_1 = "select * from course";
//	try {
//		java.sql.Statement stat =  con1.createStatement();
//		stat.execute(sql_1);
//		ResultSet re = stat.executeQuery(sql_1);
//		while(re.next()) {
//			System.out.println(re.getString("c_id"));
//		}
//	} catch (SQLException e) {
//		// TODO 自动生成的 catch 块
//		e.printStackTrace();
//	}



	}

}

