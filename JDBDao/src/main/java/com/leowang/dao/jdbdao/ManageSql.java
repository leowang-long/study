package com.leowang.dao.jdbdao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.leowang.dao.jdbdao.column.type.ColumnType;
import com.leowang.tools.utils.JMap;

/**   
 * @ClassName:  ManageSql   
 * @Description: SQL生成接口   
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:05:30   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public interface ManageSql {

	/**   
	 * @Title: createSelectSql   
	 * @Description: 根据表明，数据内容、刷选条件、排序字组装查询SQL   
	 * @param: @param paramList
	 * @param: @param tableNameString
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @param orderColumnList
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String createSelectSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap,
			List<String> columnList, List<String> orderColumnList, Boolean nDesc);

	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: createInsertSql   
	 * @Description: 根据表明，数据内容、刷选条件、排序字组装查询SQL   
	 * @param: @param paramList
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String createInsertSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap)
			throws SQLException, UnsupportedEncodingException;

	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: createUpdateSql   
	 * @Description: 根据表明，数据内容、刷选条件、排序字组装查询SQL   
	 * @param: @param paramList
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String createUpdateSql(List<Object> paramList, String tableName, JMap<String, Object> dataMap,
			List<String> columnList) throws SQLException, UnsupportedEncodingException;

	/**
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	 * @throws SerialException    
	 * @Title: stringToDBType   
	 * @Description: 将数据转换为数据库类型格式   
	 * @param: @param data
	 * @param: @param dBType
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public Object objecttToDBType(Object data, ColumnType dBType)
			throws SerialException, SQLException, UnsupportedEncodingException;

	/**   
	 * @Title: createSelectSql   
	 * @Description: 创建查询SQL语句
	 * @param: @param <T>
	 * @param: @param paramList
	 * @param: @param t
	 * @param: @param filter
	 * @param: @param orderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public <T> String createSelectSql(List<Object> paramList, T t, JDBFilter filter, List<String> orderColumns,
			Boolean nDesc) throws SQLException ;

	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: createInsertSql   
	 * @Description: 创建插入SQL语句
	 * @param: @param <T>
	 * @param: @param paramList
	 * @param: @param t
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public <T> String createInsertSql(List<Object> paramList, T t) throws SQLException, UnsupportedEncodingException ;

	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: createUpdateSql   
	 * @Description: 创建更新SQL语句
	 * @param: @param <T>
	 * @param: @param paramList
	 * @param: @param t
	 * @param: @param filter
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public <T> String createUpdateSql(List<Object> paramList, T t, JDBFilter filter) throws SQLException, UnsupportedEncodingException ;
}
