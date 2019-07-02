package com.leowang.dao.jdbdao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.leowang.dao.common.DBClose;
import com.leowang.dao.jdbdao.column.type.ColumnType;
import com.leowang.dao.jdbdao.column.type.ColumnType.OracleColumnType;
import com.leowang.dao.jdbdao.model.Column;
import com.leowang.dao.jdbdao.model.Table;

/**   
* @ClassName:  LongDataBaseOracleImpl   
* @Description:TODO(这里用一句话描述这个类的作用)   
* @author: 王龙(leowang)    
* @date:   2019年5月30日 下午8:14:15   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public class LongDataBaseOracleImpl extends AbstractLongDataBase {

	private static Logger logger = Logger.getLogger(LongDataBaseOracleImpl.class);
	/**   
	 * @Fields TABLESQL :  获取当前用户可使用的表   
	 */
	private static String TABLESQL = "SELECT C.TABLE_NAME,C.COMMENTS FROM USER_TAB_COMMENTS C,USER_TABLES T WHERE T.TABLE_NAME=C.TABLE_NAME";

	/**   
	 * @Fields TABLEPKSQL : 获取表中的主键字段名   
	 */
	private static String TABLEPKSQL = "SELECT COL.COLUMN_NAME FROM USER_CONSTRAINTS CON,  USER_CONS_COLUMNS COL WHERE CON.CONSTRAINT_NAME = COL.CONSTRAINT_NAME AND CON.CONSTRAINT_TYPE='P' AND COL.TABLE_NAME = ?";

	/**   
	 * @Fields TABLECOLUMNSQL : 获取表字段信息   
	 */
	private static String TABLECOLUMNSQL = "SELECT COL.COLUMN_NAME,COL.DATA_TYPE,COL.DATA_PRECISION,COL.CHAR_COL_DECL_LENGTH,COL.NULLABLE,COL.DATA_DEFAULT,COM.COMMENTS FROM USER_TAB_COLUMNS COL,USER_COL_COMMENTS COM WHERE COL.COLUMN_NAME=COM.COLUMN_NAME AND COL.TABLE_NAME=COM.TABLE_NAME AND COL.TABLE_NAME =?";


	/**   
	 * @Title:  LongDataBaseOracleImpl   
	 * @Description:    获取数据库连接池链接   
	 * @param:  @param properties
	 * @param:  @throws SQLException  
	 * @throws   
	 */
	public LongDataBaseOracleImpl(Properties properties) throws SQLException {
		InitDataBase(properties);
		connection = dataSource.getDataSource().getConnection();// 获取连接
	}

	/**   
	 * <p>Title: getDataBaseTables</p>   
	 * <p>Description: 获取数据库表结构</p>   
	 * @return
	 * @throws SQLException   
	 * @see com.leowang.dao.jdbdao.impl.AbstractLongDataBase#getDataBaseTables()   
	 */
	@Override
	public HashMap<String, Table> getDataBaseTables() throws SQLException {

		HashMap<String, Table> tables = new HashMap<String, Table>();
		Table table = null;
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		ResultSet resultSet = null;
		try {
			ps = connection.prepareStatement(TABLESQL);
			resultSet = ps.executeQuery();
			List<String> pkList = null;
			List<Column> notNullColumns = null;
			List<Column> nullColumns = null;
			while (resultSet.next()) {
				table = new Table();
				// 获取表名称
				table.setName(resultSet.getString("TABLE_NAME"));

				// 获取PK
				pkList = getTablePkList(table.getName().toUpperCase());

				// 设置表PK
				table.setPK(pkList);
				
				notNullColumns=new ArrayList<Column>();
				nullColumns=new ArrayList<Column>();
				// 获取字段信息
				getTableColumns(notNullColumns, nullColumns, table.getName());
				// 设置不可为空字段信息
				table.setNotNullColumns(notNullColumns);
				// 设置可为空字段信息
				table.setNullColumns(nullColumns);

				tables.put(table.getName(), table);

			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			throw e;
		} finally {
			DBClose.close(connection, ps, null, resultSet);
			connection.close();
		}

		return tables;
	}

	/**   
	 * @Title: getTablePkList   
	 * @Description: 获取数据表主键   
	 * @param: @param tableNameString
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<String>      
	 * @throws   
	 */
	private List<String> getTablePkList(String tableNameString) throws SQLException {
		List<String> pkList = new ArrayList<String>();
		PreparedStatement ps =  null;
		ResultSet resultSet=null;
		try {
			ps =  connection.prepareStatement(TABLEPKSQL);
			ps.setString(1, tableNameString);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {

				pkList.add(resultSet.getString("COLUMN_NAME"));

			}
		} finally {
			// TODO: handle finally clause
			DBClose.close(null, ps, null, resultSet);
		}

		return pkList;
	}

	/**   
	 * @Title: getTableColumns   
	 * @Description: 获取数据表字段信息   
	 * @param: @param notNullColumns
	 * @param: @param nullColumns
	 * @param: @param tableNameString
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Boolean      
	 * @throws   
	 */
	private Boolean getTableColumns(List<Column> notNullColumns, List<Column> nullColumns, String tableNameString)
			throws SQLException {

		Column column = null;
		ResultSet resultSet = null;
		PreparedStatement ps = null;
		try {
		ps = connection.prepareStatement(TABLECOLUMNSQL);
		ps.setString(1, tableNameString);
		resultSet = ps.executeQuery();

		while (resultSet.next()) {

			column = new Column();
			column.setName(resultSet.getString("COLUMN_NAME").toUpperCase());
			ColumnType columnType=OracleColumnType.getColumnType(resultSet.getString("DATA_TYPE"));
			column.setType(columnType);
			column.setLength(resultSet.getInt("CHAR_COL_DECL_LENGTH"));
			column.setPrecision(resultSet.getInt("DATA_PRECISION"));
			column.setIsNull(resultSet.getString("NULLABLE").toUpperCase().equals("Y") ? true : false);
			column.setDefaults(resultSet.getString("DATA_DEFAULT"));
			column.setComments(resultSet.getString("COMMENTS"));
			if (column.getIsNull()) {
				nullColumns.add(column);
			} else {
				notNullColumns.add(column);
			}

		}
		
	} finally {
		// TODO: handle finally clause
		DBClose.close(null, ps, null, resultSet);
	}
		return true;
	}
}
