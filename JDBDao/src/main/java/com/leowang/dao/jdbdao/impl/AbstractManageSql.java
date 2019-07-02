package com.leowang.dao.jdbdao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;

import com.leowang.dao.common.LongDataSource;
import com.leowang.dao.jdbdao.JDBFilter;
import com.leowang.dao.jdbdao.ManageSql;
import com.leowang.dao.jdbdao.column.type.ColumnType;
import com.leowang.dao.jdbdao.model.Column;
import com.leowang.dao.jdbdao.model.Table;
import com.leowang.tools.utils.ClassInfo;
import com.leowang.tools.utils.JMap;

/**   
* @ClassName:  AbstractManageSql   
* @Description:TODO(这里用一句话描述这个类的作用)   
* @author: 王龙(leowang)    
* @date:   2019年6月3日 下午9:46:14   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public abstract class AbstractManageSql implements ManageSql {

	public static final String tableNameField = "TableName";

	private static Logger logger = Logger.getLogger(AbstractManageSql.class);

	protected LongDataSource dataSource;

	@Override
	public String createSelectSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap,
			List<String> columnList, List<String> orderColumnList, Boolean nDesc) {
		// TODO Auto-generated method stub
		String selectSql = "select * from " + tableName + " where 1=1 ";

		if (null != columnList) {
			if(null==paramList)
				paramList = new ArrayList<Object>();
			for (String column : columnList) {
				selectSql += ("and " + column + "=? ");
				paramList.add(dataMap.get(column));
			}
		}
		if (null != orderColumnList && orderColumnList.size() > 0) {
			selectSql += "order by ";
			boolean frist = true;
			for (String orderCoumn : orderColumnList) {
				if (!frist) {
					selectSql += ",";
				}
				selectSql += orderCoumn;
			}
			if (!nDesc) {
				selectSql += " desc";
			}
		}

		return selectSql;
	}

	@Override
	public String createInsertSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap)
			throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		boolean first = true;
		String insertSql = String.format("INSERT INTO %s (", tableName);
		String valuesSql = ") VALUES (";
		Table tableInfo = dataSource.getTables().get(tableName.toUpperCase());
		for (String pk : tableInfo.getPK()) {
			if (!dataMap.containsKey(pk)) {
				String errMes = "插入表[" + tableName + "]内容时，主键字段[" + pk + "]的值不存在,dataMap:" + dataMap.toString();
				logger.error(errMes);
				throw new SQLException(errMes);
			}
		}

		for (Column coulumn : tableInfo.getNotNullColumns()) {
			if (!dataMap.containsKey(coulumn.getName())) {
				continue;
			}
			if (!first) {
				first = false;
				insertSql += " , ";
				valuesSql += ",";
			}
			insertSql += coulumn.getName();
			valuesSql += "?";
			Object tmpObject = objecttToDBType(dataMap.get(coulumn.getName()), coulumn.getType());
			paramList.add(tmpObject);
		}
		insertSql += (valuesSql + ")");
		return insertSql;
	}

	@Override
	public String createUpdateSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap,
			List<String> columnList) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		boolean first = true;
		String updateSql = String.format("update %s set ", tableName);
		Table tableInfo = dataSource.getTables().get(tableName.toUpperCase());
		if(null==tableInfo)
		{
			String errMes="表不存在:数据表[" + tableName + "]不可用";
			logger.error(errMes);
			throw new SQLException(errMes);
		}
		String tmpSql = mapToUpdateSql(tableInfo.getNotNullColumns(), dataMap, paramList);
		if (tmpSql.length() > 0) {
			first = false;
			updateSql += tmpSql;
		}
		tmpSql = mapToUpdateSql(tableInfo.getNullColumns(), dataMap, paramList);
		if (tmpSql.length() > 0 && first) {
			first = false;
			updateSql += ("," + tmpSql);
		}

		String whereSql = "";
		first = true;
		for (String column : columnList) {
			if (first) {
				first = false;
				whereSql += (" WHERE " + column + "=? ");
			} else {
				whereSql += (" AND " + column + "=? ");
			}
			if (dataMap.containsKey(column)) {
				paramList.add(dataMap.get(column));
			} else {
				String errMes = "更新表[" + tableName + "]内容时传递内容不完整存在，字段[" + column + "]的值不存在,dataMap:"
						+ dataMap.toString();
				logger.error(errMes);
				throw new SQLException(errMes);
			}
		}
		updateSql += whereSql;
		return updateSql;
	}

	private String mapToUpdateSql(List<Column> coulumns, JMap<String, Object> dataMap, List<Object> paramList)
			throws SerialException, UnsupportedEncodingException, SQLException {
		String sqlStr = "";
		boolean first = true;
		for (Column coulumn : coulumns) {
			if (!dataMap.containsKey(coulumn.getName())) {
				continue;
			}
			if (!first) {
				first = false;
				sqlStr += " , ";
			}
			sqlStr += (coulumn.getName() + "=? ");
			Object tmpObject = objecttToDBType(dataMap.get(coulumn.getName()), coulumn.getType());
			paramList.add(tmpObject);
		}
		return sqlStr;
	}

	@Override
	abstract public Object objecttToDBType(Object data, ColumnType dBType)
			throws SerialException, SQLException, UnsupportedEncodingException;

	@Override
	public <T> String createSelectSql(List<Object> paramList, T t, JDBFilter filter, List<String> orderColumns,
			Boolean nDesc) throws SQLException {
		String sqlStr = "";

		// TODO Auto-generated method stub
		ClassInfo objectClassInfo = new ClassInfo(t);

		Object tableName = objectClassInfo.getFieldValue(tableNameField, t);
		if (null == tableName) {
			String errMes="表名不存在:对象中字段[" + tableNameField + "]不可用";
			logger.error(errMes);
			throw new SQLException(errMes);
		}
		sqlStr = "select * from " + tableName.toString() + filter.getFilterSql();
		paramList = filter.getParamList();
		boolean first = true;
		for (String string : orderColumns) {
			if (first) {
				sqlStr += (" order by " + string);
				first = false;
			} else {
				sqlStr += ("," + string);
			}
		}
		if (nDesc) {
			sqlStr += " desc";
		}

		return sqlStr;
	}

	@Override
	public <T> String createInsertSql(List<Object> paramList, T t) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		JMap<String, Object> dataJMap=new JMap<String, Object>();
		String tableName="";
		tableName=objectToMap(t,dataJMap);
		return createInsertSql(paramList, tableName, dataJMap);
	}

	@Override
	public <T> String createUpdateSql(List<Object> paramList, T t, JDBFilter filter) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		JMap<String, Object> dataJMap=new JMap<String, Object>();
		String tableName="";
		tableName=objectToMap(t,dataJMap);

		Table tableInfo = dataSource.getTables().get(tableName.toUpperCase());
		if(null==tableInfo)
		{
			String errMes="表不存在:数据表[" + tableName + "]不可用";
			logger.error(errMes);
			throw new SQLException(errMes);
		}
		String tmpSql = mapToUpdateSql(tableInfo.getNullColumns(), dataJMap, paramList);
		tmpSql+=filter.getFilterSql();
		paramList.addAll(filter.getParamList());
		return tmpSql;
	}
	
	/**   
	 * @Title: ObjectToMap   
	 * @Description: 将对象内容转换为Map并返回表明  
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @param dataJMap
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: String      
	 * @throws   
	 */
	protected <T> String objectToMap(T object,JMap<String, Object> dataJMap) throws SQLException {
		ClassInfo objectClassInfo = new ClassInfo(object);

		Object tableNameObj=objectClassInfo.getFieldValue(tableNameField, object);
		if(null==tableNameObj)
		{
			String errMes="表名不存在:对象中字段[" + tableNameField + "]不可用";
			logger.error(errMes);
			throw new SQLException(errMes);
		}
		String tableName=tableNameObj.toString();
		for(String methodName:objectClassInfo.getGetMethodName())
		{
			dataJMap.put(methodName.substring(3),objectClassInfo.runMethod(methodName, object,null));
		}
		return tableName;
	}
}
