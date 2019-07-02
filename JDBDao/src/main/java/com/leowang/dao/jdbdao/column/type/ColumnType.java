package com.leowang.dao.jdbdao.column.type;

public interface ColumnType {

	enum OracleColumnType implements ColumnType {
		CHAR, NCHAR, VARCHAR, VARCHAR2, NVARCHAR2, FLOAT, BINARY_FLOAT, BINARY_DOUBLE, LONG, NUMBER, RAW, ROWID, UROWID, LONGRAW, DATE, TIMESTAMP, BLOB, CLOB, NCLOB, BFILE, UNKNOWN;
		public static OracleColumnType getColumnType(String typeString) {
			if (typeString.indexOf("TIMESTAMP") >= 0)
				typeString = "TIMESTAMP";

			OracleColumnType columnType;
			switch (typeString) {
			case "CHAR":
				columnType = OracleColumnType.CHAR;
				break;
				
			case "NCHAR":
				columnType = OracleColumnType.NCHAR;
				break;

			case "VARCHAR":
				columnType = OracleColumnType.VARCHAR;
				break;

			case "VARCHAR2":
				columnType = OracleColumnType.VARCHAR2;
				break;

			case "NVARCHAR2":
				columnType = OracleColumnType.NVARCHAR2;
				break;

			case "FLOAT":
				columnType = OracleColumnType.FLOAT;
				break;
				
			case "BINARY_FLOAT":
				columnType = OracleColumnType.BINARY_FLOAT;
				break;
				
			case "BINARY_DOUBLE":
				columnType = OracleColumnType.BINARY_DOUBLE;
				break;

			case "LONG":
				columnType = OracleColumnType.LONG;
				break;

			case "NUMBER":
				columnType = OracleColumnType.NUMBER;
				break;

			case "RAW":
				columnType = OracleColumnType.RAW;
				break;
				
			case "ROWID":
				columnType = OracleColumnType.ROWID;
				break;

			case "UROWID":
				columnType = OracleColumnType.UROWID;
				break;

			case "LONG RAW":
				columnType = OracleColumnType.LONGRAW;
				break;

			case "DATE":
				columnType = OracleColumnType.DATE;
				break;

			case "TIMESTAMP":
				columnType = OracleColumnType.TIMESTAMP;

			case "BLOB":
				columnType = OracleColumnType.BLOB;

			case "CLOB":
				columnType = OracleColumnType.CLOB;
				break;
				
			case "NCLOB":
				columnType = OracleColumnType.NCLOB;
				break;
				
			case "BFILE":
				columnType = OracleColumnType.BFILE;
				break;
				
			default:
				columnType = OracleColumnType.UNKNOWN;
				break;
			}
			return columnType;
		}
	}

	enum Db2COLUMNTYPE implements ColumnType {
		CHAR, VARCHAR, VARCHAR2, LONG, NUMBER, RAW, LONGRAW, DATE, TIMESTAMP, BLOB, CLOB, UNKNOWN;
		public static Db2COLUMNTYPE getColumnType(String typeString) {
			Db2COLUMNTYPE columnType = null;
			// TODO
			return columnType;
		}
	}
}
