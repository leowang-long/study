package com.leowang.dao.jdbdao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.leowang.dao.common.LongDataSource;
import com.leowang.dao.jdbdao.DBDao;
import com.leowang.dao.jdbdao.JDBFilter;
import com.leowang.dao.jdbdao.ManageSql;
import com.leowang.tools.utils.ClassInfo;
import com.leowang.tools.utils.JMap;
import com.leowang.tools.utils.JUtils;

/**   
* @ClassName:  AbstractJDBDao   
* @Description:TODO(这里用一句话描述这个类的作用)   
* @author: 王龙(leowang)    
* @date:   2019年5月30日 下午8:32:53   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public abstract class AbstractJDBDao implements DBDao {

	private static Logger logger = Logger.getLogger(AbstractJDBDao.class);

	/**
	 * @Fields dataSources : 用于存放数据源，支持多数据源
	 */
	protected static JMap<String, LongDataSource> dataSources = new JMap<String, LongDataSource>();

	protected LongDataSource dataSource = null;
	/**   
	 * @Fields connection : 当前用户使用的数据库链接   
	 */
	protected DruidPooledConnection connection = null;

	protected PreparedStatement ps = null;

	protected ResultSet resultSet = null;

	protected ManageSql manageSql = null;

	@Override
	public List<JMap<String, Object>> selectBySql(String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		List<Object> dataList = null;
		if (null != param) {
			dataList = new ArrayList<Object>();
			dataList.add(param);
		}
		return selectBySql(sql, dataList);
	}

	@Override
	public List<JMap<String, Object>> selectBySql(String sql, List<Object> params) throws SQLException {
		// TODO Auto-generated method stub
		List<JMap<String, Object>> dataList = null;

		logger.debug(sql);

		ps = connection.prepareStatement(sql);

		if (null != params) {
			int num = 1;
			for (Object object : params) {
				ps.setObject(num++, object);
			}
		}
		logger.debug(ps);
		resultSet = ps.executeQuery();
		dataList = JUtils.resultSetToMap(resultSet);

		if (null == dataList) {
			dataList = new ArrayList<JMap<String, Object>>();
		}
		return dataList;
	}

	@Override
	public List<JMap<String, Object>> selectBySql(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return selectBySql(sql, null);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName) throws SQLException {
		// TODO Auto-generated method stub

		String sqlString = "select * from " + tableName;

		return selectBySql(sqlString);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList)
			throws SQLException {
		// TODO Auto-generated method stub

		return select(tableName, dataMap, columnList, new ArrayList<String>(), false);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String column)
			throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, column.split(","));
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns)
			throws SQLException {
		// TODO Auto-generated method stub
		List<String> columnList = new ArrayList<String>(Arrays.asList(columns));
		return select(tableName, dataMap, columnList);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			String orderColumnString, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub

		return select(tableName, dataMap, columnList, orderColumnString.split(","), nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			List<String> orderColumnList, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		List<Object> paramList = new ArrayList<Object>();
		String sqlString = manageSql.createSelectSql(paramList, tableName, dataMap, columnList, orderColumnList, nDesc);
		return selectBySql(sqlString, paramList);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			String[] orderColumns, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, columnList, new ArrayList<String>(Arrays.asList(orderColumns)), nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			String orderColumnString, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, columnString.split(","), orderColumnString, nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			List<String> orderColumnList, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, columnString.split(","), orderColumnList, nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			String[] orderColumns, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, columnString.split(","), orderColumns, nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			String orderColumnString, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, new ArrayList<String>(Arrays.asList(columns)), orderColumnString, nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			List<String> orderColumnList, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, new ArrayList<String>(Arrays.asList(columns)), orderColumnList, nDesc);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			String[] orderColumns, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return select(tableName, dataMap, new ArrayList<String>(Arrays.asList(columns)), orderColumns, nDesc);
	}

	@Override
	public JMap<String, Object> selectByPk(String tableName, JMap<String, Object> dataMap) throws SQLException {
		// TODO Auto-generated method stub
		JMap<String, Object> data = null;
		List<String> pkList = dataSource.getTables().get(tableName).getPK();
		List<JMap<String, Object>> dataList = select(tableName, dataMap, pkList);
		if (null != dataList && dataList.size() > 0) {
			data = dataList.get(0);
		} else {
			data = new JMap<String, Object>();
		}
		return data;
	}

	@Override
	public int execute(String sql, List<Object> paramList) {
		// TODO Auto-generated method stub
		int count = 0;
		try {

			logger.debug(sql);

			ps = connection.prepareStatement(sql);
			if (null != paramList) {
				int num = 1;
				for (Object object : paramList) {
					ps.setObject(num++, object);
				}
			}

			logger.debug(ps);
			count = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			logger.error(e);
		}
		return count;
	}

	@Override
	public int insert(String tableName, JMap<String, Object> dataMap) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sqlString = manageSql.createInsertSql(paramList, tableName, dataMap);
			ps = connection.prepareStatement(sqlString);
			int num = 1;
			for (Object param : paramList) {
				ps.setObject(num++, param);
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return count;
	}

	@Override
	public int insert(String tableName, List<JMap<String, Object>> dataList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {
			count += insert(tableName, data);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap) {
		// TODO Auto-generated method stub
		int count = 0;
		count = update(tableName, dataMap);
		if (count == 0) {
			count = insert(tableName, dataMap);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {
			count += insertOrUpdate(tableName, data);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, String column) {
		// TODO Auto-generated method stub
		int count = 0;
		count = update(tableName, dataMap, column);
		if (count == 0) {
			count = insert(tableName, dataMap);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, String[] columns) {
		// TODO Auto-generated method stub
		int count = 0;
		count = update(tableName, dataMap, columns);
		if (count == 0) {
			count = insert(tableName, dataMap);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, List<String> columnList) {
		// TODO Auto-generated method stub
		int count = 0;
		count = update(tableName, dataMap, columnList);
		if (count == 0) {
			count = insert(tableName, dataMap);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, String column) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {
			count += insertOrUpdate(tableName, data, column);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, String[] columns) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {
			count += insertOrUpdate(tableName, data, columns);
		}
		return count;
	}

	@Override
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, List<String> columnList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {
			count += insertOrUpdate(tableName, data, columnList);
		}
		return count;
	}

	@Override
	public int update(String tableName, JMap<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return update(tableName, dataMap, new ArrayList<String>());
	}

	@Override
	public int update(String tableName, List<JMap<String, Object>> dataList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {

			count += update(tableName, data);
		}
		return count;
	}

	@Override
	public int update(String tableName, JMap<String, Object> dataMap, String column) {
		// TODO Auto-generated method stub
		return update(tableName, dataMap, column.split(","));
	}

	@Override
	public int update(String tableName, JMap<String, Object> dataMap, String[] columns) {
		// TODO Auto-generated method stub
		return update(tableName, dataMap, new ArrayList<String>(Arrays.asList(columns)));
	}

	@Override
	public int update(String tableName, JMap<String, Object> dataMap, List<String> columnList) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sqlString = manageSql.createUpdateSql(paramList, tableName, dataMap, columnList);
			ps = connection.prepareStatement(sqlString);
			int num = 1;
			for (Object param : paramList) {
				ps.setObject(num++, param);
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return count;
	}

	@Override
	public int update(String tableName, List<JMap<String, Object>> dataList, String column) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {

			count += update(tableName, data, column);
		}
		return count;
	}

	@Override
	public int update(String tableName, List<JMap<String, Object>> dataList, String[] columns) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {

			count += update(tableName, data, columns);
		}
		return count;
	}

	@Override
	public int update(String tableName, List<JMap<String, Object>> dataList, List<String> columnList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (JMap<String, Object> data : dataList) {

			count += update(tableName, data, columnList);
		}
		return count;
	}

	@Override
	public <T> List<T> select(T object, JDBFilter filter)
			throws InstantiationException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		return select(object, filter, new ArrayList<String>(), false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> select(T object, JDBFilter filter, List<String> orderColumnList, Boolean nDesc)
			throws SQLException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub

		List<T> dataList = new ArrayList<T>();

		List<Object> paramList = new ArrayList<Object>();
		String sqlString = manageSql.createSelectSql(paramList, object, filter, orderColumnList, nDesc);

		List<JMap<String, Object>> dataMaps = this.selectBySql(sqlString, filter.getParamList());
		ClassInfo objectClassInfo = new ClassInfo(object);
		for (JMap<String, Object> jMap : dataMaps) {
			T t = (T) object.getClass().newInstance();
			for (Map.Entry<String, Object> entry : jMap.entrySet()) {
				objectClassInfo.runMethod("set" + entry.getKey(), t, entry.getValue());
			}
			dataList.add(t);
		}

		return dataList;
	}

	@Override
	public <T> List<T> select(T object, JDBFilter filter, String[] orderColumns, Boolean nDesc)
			throws InstantiationException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		return select(object, filter, Arrays.asList(orderColumns), nDesc);
	}

	@Override
	public <T> List<T> select(T object, JDBFilter filter, String orderColumns, Boolean nDesc)
			throws InstantiationException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		return select(object, filter, orderColumns.split(","), nDesc);
	}

	@Override
	public <T> int insert(T object) throws UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		List<Object> paramList = new ArrayList<Object>();
		String insertSql = manageSql.createInsertSql(paramList, object);
		return this.execute(insertSql, paramList);
	}

	@Override
	public <T> int insert(List<T> objects) throws UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		int count = 0;
		for (T t : objects) {
			count += this.insert(t);
		}
		return count;
	}

	@Override
	public <T> int update(T objects, JDBFilter filter) throws UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		List<Object> paramList = new ArrayList<Object>();
		String updateSql = manageSql.createUpdateSql(paramList, objects, filter);
		return this.execute(updateSql, paramList);
	}

	@Override
	public <T> int update(List<T> objects, JDBFilter filter) throws UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		int count = 0;
		for (T t : objects) {
			count += this.update(t, filter);
		}
		return count;
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Object> paramList = new ArrayList<Object>();
		String sqlString = manageSql.createSelectSql(paramList, tableName, dataMap, null, null, false);
		sqlString += filter.getFilterSql();
		return selectBySql(sqlString, paramList);
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			String coderColumnString, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			List<String> coderColumnList, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
//		List<Object> paramList = new ArrayList<Object>();
//		String sqlString = manageSql.createSelectSql(paramList, tableName, dataMap, null, null, false);
//		sqlString += filter.getFilterSql();
//		String oraderByString="";
//		Boole
//		if(null!=coderColumnList)
//		{
//			for (String column : coderColumnList) {
//				oraderByString +=
//			}
//		}
//		return selectBySql(sqlString, paramList);
		return null;
	}

	@Override
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			String[] coderColumns, Boolean nDesc) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, JDBFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, JDBFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String tableName, JMap<String, Object> dataMap, JDBFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String tableName, List<JMap<String, Object>> dataList, JDBFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
