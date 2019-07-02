package com.leowang.dao.common;


 /**   
 * @ClassName:  DataSourceType   
 * @Description: 数据源类型  
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:02:07   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public enum DataSourceType {
	ORACLE, DB2, SQLSERVER, SQLITE, HSQLDB, MYSQL, POSTGRESQL, ODBC, NULL;

	public static DataSourceType getDataSourceType(String dataSourceTypeString) {

		if (dataSourceTypeString == null || dataSourceTypeString.isEmpty())
			return DataSourceType.NULL;

		if (dataSourceTypeString.toUpperCase().indexOf("ORACLE") >= 0)
			return DataSourceType.ORACLE;

		if (dataSourceTypeString.toUpperCase().indexOf("DB2") >= 0)
			return DataSourceType.DB2;

		if (dataSourceTypeString.toUpperCase().indexOf("SQLSERVER") >= 0
				|| dataSourceTypeString.toUpperCase().indexOf("JTDS") >= 0)
			return DataSourceType.SQLSERVER;

		if (dataSourceTypeString.toUpperCase().indexOf("SQLITE") >= 0)
			return DataSourceType.SQLITE;

		if (dataSourceTypeString.toUpperCase().indexOf("HSQLDB") >= 0)
			return DataSourceType.HSQLDB;

		if (dataSourceTypeString.toUpperCase().indexOf("MYSQL") >= 0)
			return DataSourceType.MYSQL;

		if (dataSourceTypeString.toUpperCase().indexOf("POSTGRESQL") >= 0)
			return DataSourceType.POSTGRESQL;

		if (dataSourceTypeString.toUpperCase().indexOf("ODBC") >= 0)
			return DataSourceType.ODBC;

		return DataSourceType.NULL;
	}

	public static String getString(DataSourceType dataSourceType) {
		String dataSourceTypeString = "";
		switch (dataSourceType) {
		case ORACLE:
			dataSourceTypeString = "ORACLE";
			break;
		case DB2:
			dataSourceTypeString = "DB2";
			break;
		case SQLSERVER:
			dataSourceTypeString = "SQLSERVER";
			break;
		case SQLITE:
			dataSourceTypeString = "SQLITE";
			break;
		case HSQLDB:
			dataSourceTypeString = "HSQLDB";
			break;
		case MYSQL:
			dataSourceTypeString = "MYSQL";
			break;
		case POSTGRESQL:
			dataSourceTypeString = "POSTGRESQL";
			break;
		case ODBC:
			dataSourceTypeString = "ODBC";
			break;

		default:
			dataSourceTypeString = "NULL";
			break;
		}
		return dataSourceTypeString;
	}

}
