package auto.framework.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import auto.framework.ReportLog;

public class DataBaseHandler {
	public Connection conn = null;
	public ResultSet resultSet = null;

	public DataBaseHandler(String DBConfig, String userName, String password) throws Exception {
		System.out.println("OracleDBCFunctions(): Triggered constructor");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(DBConfig, userName, password);
		System.out.println("OracleDBCFunctions(): Successful Connection to Database");
	}

	private void dbExecuteStatement(String p_Query) throws Exception {
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = stmt.executeQuery(p_Query);
		System.out.println("dbExecuteStatement(): Executed the ff. query" + p_Query);
		ReportLog.logEvent(true,"dbExecuteStatement(): Executed the ff. query" + p_Query);
	}

	public String dbFetchDataFrom1stRow(String p_Query, String p_dataType, String p_ColName) throws Exception {
		String strValue = "";
		dbExecuteStatement(p_Query);
		dbGetRowCount(p_Query);
		resultSet.first();
		System.out.println("dbFetchDataFrom1stRow(): Starting...");
		if (p_dataType.equalsIgnoreCase("string")) {
			strValue = resultSet.getString(p_ColName);
		} else if (p_dataType.equalsIgnoreCase("int")) {
			int temp = 0;
			temp = resultSet.getInt(p_ColName);
			strValue = String.valueOf(temp);
		} else if (p_dataType.equalsIgnoreCase("float")) {
			float tempf = 0.0f;
			tempf = resultSet.getFloat(p_ColName);
			strValue = String.valueOf(tempf);
		}
		System.out.println("dbFetchDataFrom1stRow(): strValue-" + strValue);
		ReportLog.logEvent(true,"dbFetchDataFrom1stRow(): strValue-" + strValue);
		return strValue;
	}

	public String dbFetchFrom1stRowMultiCols(String p_Query, String p_dataType, String p_ColNames) throws Exception {
		String sColName[] = p_ColNames.split("##");
		String strValue = "";
		String strColsValue = "";
		dbExecuteStatement(p_Query);
		int intRowCount = dbGetRowCount(p_Query);
		if (intRowCount > 0) {
			resultSet.first();
			System.out.println("dbFetchDataFrom1stRow: Starting...");
			for (int x = 0; x <= sColName.length - 1; x++) {
				if (p_dataType.equalsIgnoreCase("string")) {
					strValue = resultSet.getString(sColName[x]);
					strColsValue = strColsValue + "##" + resultSet.getString(sColName[x]);
				} else if (p_dataType.equalsIgnoreCase("int")) {
					int temp = 0;
					strValue = String.valueOf(temp);
					strColsValue = strColsValue + "##" + String.valueOf(temp);
				} else if (p_dataType.equalsIgnoreCase("float")) {
					float tempf = 0.0f;
					tempf = resultSet.getFloat(sColName[x]);
					strValue = String.valueOf(tempf);
					strColsValue = strColsValue + "##" + String.valueOf(tempf);
				}
				System.out.println("dbFetchDataFrom1stRow(): strValue=" + strValue);
				ReportLog.logEvent(true,"dbFetchDataFrom1stRow(): strValue=" + strValue);
			}
		} else {
			strColsValue = "";
		}
		return strColsValue;
	}

	public String dbFetchDataFromXRow(String p_Query, String p_dataType, String p_ColName, int p_rowNum)
			throws Exception {
		String strValue = "";
		dbExecuteStatement(p_Query);
		resultSet.absolute(p_rowNum);
		System.out.println("dbFetchDataFromXRow();: Starting...");
		if (p_dataType == "String") {
			strValue = resultSet.getString(p_ColName);
		} else if (p_dataType == "int") {
			int temp = 0;
			temp = resultSet.getInt(p_ColName);
			strValue = String.valueOf(temp);
		} else if (p_dataType == "float") {
			float tempf = 0.0f;
			tempf = resultSet.getFloat(p_ColName);
			strValue = String.valueOf(tempf);

		}

		System.out.println("dbFetchDataFromXRow(): strValue=" + strValue);
		ReportLog.logEvent(true,"dbFetchDataFromXRow(): strValue=" + strValue);
		return strValue;
	}

	public int dbGetRowCount(String p_Query) throws Exception {
		System.out.println("dbGetRowCount(): Starting...");
		int intRowCount = 0;
		if (resultSet.first()) {
			do {
				intRowCount++;
			} while (resultSet.next());
		} else {
			intRowCount = 0;
		}
		System.out.println("dbGetRowCount():" + intRowCount);
		ReportLog.logEvent(true,"dbGetRowCount():" + intRowCount);
		return intRowCount;
	}

	public void dbCloseConn() throws Exception {
		System.out.println("dbCloseConn();: Starting...");
		conn.close();
		System.out.println("dbCloseConn();: Closed");
		ReportLog.logEvent(true,"dbCloseConn();: Closed");
		
	}

}
