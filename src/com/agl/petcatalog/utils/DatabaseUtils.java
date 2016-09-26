package com.agl.petcatalog.utils;
/*
 * Author      : Sreekanth Vadassery
 * Date        : 10-Sep-2016
 * Description : This class will contain all the Database operations such as establishing a connection, 
 * 				 closing the connection, querying the result sets, inserting a records , updating an record etc.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {

	@SuppressWarnings("finally")
	public static Connection Database_Connection_Establish(){
		Connection dbConnection = null;
		String [] Qa_DB_Details=new String[5];{
			Qa_DB_Details[0]=ConfigFileUtils.readConfigFile("QA_DB_Server", "./test.properties");
			Qa_DB_Details[1]=ConfigFileUtils.readConfigFile("QA_DB_Port","./test.properties");
			Qa_DB_Details[2]=ConfigFileUtils.readConfigFile("QA_DB_Service","./test.properties");
			Qa_DB_Details[3]=ConfigFileUtils.readConfigFile("QA_DB_Username","./test.properties");
			Qa_DB_Details[4]=ConfigFileUtils.readConfigFile("QA_DB_Password","./test.properties");}
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://"+Qa_DB_Details[0]+":"+Qa_DB_Details[1]+";DatabaseName="+Qa_DB_Details[2];
			//url=jdbc:sqlserver://server:port;DatabaseName=dbname
			dbConnection = DriverManager.getConnection(url, Qa_DB_Details[3], Qa_DB_Details[4]);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{return dbConnection;}
	}

	public static void Database_Connection_Close(Connection dbConnection){
		try{
			dbConnection.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@SuppressWarnings({ "finally"})
	public static ResultSet Database_Query_Select(Connection dbConnection,String dbSql){
		ResultSet dbResultset=null;
		Statement dbStatement;
		try{
			//dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			dbResultset = dbStatement.executeQuery(dbSql);
		}
		catch(Exception e){
			System.out.println(dbSql+" -- "+e.toString());
		}
		finally{return dbResultset;}
	}

	@SuppressWarnings("finally")
	public static int Database_Query_UpadteORInsert(Connection dbConnection,String dbSql){
		int dbResultset=0;
		Statement dbStatement;
		try{
			//dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			dbResultset = dbStatement.executeUpdate(dbSql);
		}
		catch(Exception e){
			System.out.println(dbSql+" -- "+e.toString());
		}
		finally{
			return dbResultset;}
	}

	public static void Database_Query_Delete(Connection dbConnection,String dbSql){
		Statement dbStatement;
		try{
			dbStatement = dbConnection.createStatement();
			dbStatement.execute(dbSql);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public static void updateOrInsertExample(){
		/*
		 * Method example for executing update/insert query. Follow the same pattern
		 */
		Connection dbConnection=null;
		try{
			String updateSql="update Reference..Song set ReleasedDate='2016-06-21 00:00:00.970' where SongKey=2723016";
			dbConnection=DatabaseUtils.Database_Connection_Establish();
			DatabaseUtils.Database_Query_UpadteORInsert(dbConnection, updateSql);
			DatabaseUtils.Database_Connection_Close(dbConnection);
		}
		catch(Exception e){
			DatabaseUtils.Database_Connection_Close(dbConnection);
		}
	}
	
	public static Integer selectExample(){
		/*
		 * Method example for select query. Follow the same pattern
		 */
		Integer songKey = 0;
		String selectTotalSongCount="select top 1 * from Reference..Song";
		Connection dbConnection=null;
		try{
			dbConnection=DatabaseUtils.Database_Connection_Establish();
			ResultSet resultSetItem=DatabaseUtils.Database_Query_Select(dbConnection, selectTotalSongCount);
			while(resultSetItem.next()){
				songKey=resultSetItem.getInt("SongKey");
			}
			DatabaseUtils.Database_Connection_Close(dbConnection);
		}
		catch(Exception e){
			DatabaseUtils.Database_Connection_Close(dbConnection);
		}
		return songKey;
	}
	
}
