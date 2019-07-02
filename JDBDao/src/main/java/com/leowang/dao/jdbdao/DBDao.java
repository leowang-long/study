package com.leowang.dao.jdbdao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.leowang.tools.utils.JMap;

/**   
* @ClassName:  DBDao   
* @Description:DBDao对外开放接口定义   
* @author: 王龙(leowang)    
* @date:   2019年5月30日 下午9:28:50   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public interface DBDao {

	/**
	 * @throws SQLException    
	 * @Title: selectBySql   
	 * @Description: 根据SQL语句和传递的单个参数查询数据   
	 * @param: @param sql
	 * @param: @param param
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> selectBySql(String sql, Object param) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: selectBySql   
	 * @Description: 根据SQL语句和传递的参数查询数据   
	 * @param: @param sql
	 * @param: @param params
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> selectBySql(String sql, List<Object> params) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: queselect
	 * @Description: 根据SQL语句查询数据   
	 * @param: @param sql
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> selectBySql(String sql) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明查询全部数据   
	 * @param: @param tableName
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询 @param: @param   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList)
			throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询    
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnString
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString)
			throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columns
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns)
			throws SQLException;

	/**   
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询 
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter)
			throws SQLException;
	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @param coderColumnString
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			String coderColumnString, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @param coderColumnList
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			List<String> coderColumnList, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @param coderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, List<String> columnList,
			String[] coderColumns, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnString
	 * @param: @param coderColumnString
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			String coderColumnString, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnString
	 * @param: @param coderColumnList
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			List<String> coderColumnList, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnString
	 * @param: @param coderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String columnString,
			String[] coderColumns, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columns
	 * @param: @param coderColumnString
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			String coderColumnString, Boolean nDesc) throws SQLException;
	
	/**   
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序 
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @param coderColumnString
	 * @param: @param nDesc
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			String coderColumnString, Boolean nDesc) throws SQLException;
	
	/**   
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @param coderColumnList
	 * @param: @param nDesc
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			List<String> coderColumnList, Boolean nDesc) throws SQLException;
	
	/**   
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @param coderColumns
	 * @param: @param nDesc
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, JDBFilter filter,
			String[] coderColumns, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columns
	 * @param: @param coderColumnList
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			List<String> coderColumnList, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据表明，数据内容和刷选条件的字段进行查询,并进行排序
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columns
	 * @param: @param coderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public List<JMap<String, Object>> select(String tableName, JMap<String, Object> dataMap, String[] columns,
			String[] coderColumns, Boolean nDesc) throws SQLException;

	/**
	 * @throws SQLException 
	 * 
	 * @Title: selectByPk   
	 * @Description: 根据主键查询数据   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @return      
	 * @return: JMap<String,Object>     
	 * @throws
	 */
	public JMap<String, Object> selectByPk(String tableName, JMap<String, Object> dataMap) throws SQLException;

	/**   
	 * @Title: execute   
	 * @Description: 执行语句   
	 * @param: @param sql
	 * @param: @param paramList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int execute(String sql, List<Object> paramList);

	/**   
	 * @Title: insert   
	 * @Description: 根据表名和Map插入数据   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insert(String tableName, JMap<String, Object> dataMap);

	/**   
	 * @Title: insert   
	 * @Description: 根据表名和List插入数据   
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insert(String tableName, List<JMap<String, Object>> dataList);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新),无主键只能进行插入不能进行更新
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，无主键只能进行插入不能进行更新
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据，多个字段使用“,”分割  
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param column
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, String column);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据   
	 * @param: @param tableName
	 * @param: @param data
	 * @param: @param columns
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, String[] columns);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据 
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, List<String> columnList);
	
	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入JDBFilter进行更新数据 
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, JMap<String, Object> dataMap, JDBFilter filter);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据，多个字段使用“,”分割   
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param column
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, String column);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据   
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param columns
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, String[] columns);

	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入字段进行更新数据   
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param columnList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, List<String> columnList);
	
	/**   
	 * @Title: insertOrUpdate   
	 * @Description: 插入或更新(更新是根据主键进行更新)，根据输入JDBFilter进行更新数据 
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int insertOrUpdate(String tableName, List<JMap<String, Object>> dataList, JDBFilter filter);

	/**   
	 * @Title: update   
	 * @Description: 根据主键更新数据   
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, JMap<String, Object> dataMap);

	/**   
	 * @Title: update   
	 * @Description: 根据主键更新数据   
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, List<JMap<String, Object>> dataList);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据，多个字段使用“,”分割
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param column 
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, JMap<String, Object> dataMap, String column);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据 
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param Column
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, JMap<String, Object> dataMap, String[] columns);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据  
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param columnList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, JMap<String, Object> dataMap, List<String> columnList);
	
	/**   
	 * @Title: update   
	 * @Description: 根据JDBFilter进行更新数据  
	 * @param: @param tableName
	 * @param: @param dataMap
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, JMap<String, Object> dataMap, JDBFilter filter);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据，多个字段使用“,”分割
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param column 
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, List<JMap<String, Object>> dataList, String column);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据 
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param columns
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, List<JMap<String, Object>> dataList, String[] columns);

	/**   
	 * @Title: update   
	 * @Description: 根据输入字段进行更新数据  
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param columnList
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, List<JMap<String, Object>> dataList, List<String> columnList);
	
	/**   
	 * @Title: update   
	 * @Description: 根据JDBFilter进行更新数据    
	 * @param: @param tableName
	 * @param: @param dataList
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int update(String tableName, List<JMap<String, Object>> dataList, JDBFilter filter);

	/**
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException    
	 * @Title: select   
	 * @Description: 根据过滤条件查询数据
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @param filter
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	public <T> List<T> select(T object, JDBFilter filter)
			throws InstantiationException, IllegalAccessException, SQLException;

	/**
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException    
	 * @Title: select   
	 * @Description: 根据过滤条件查询数据
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @param filter
	 * @param: @param orderColumnList
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	public <T> List<T> select(T object, JDBFilter filter, List<String> orderColumnList, Boolean nDesc)
			throws SQLException, InstantiationException, IllegalAccessException;

	/**
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException    
	 * @Title: select   
	 * @Description: 根据过滤条件查询数据
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @param filter
	 * @param: @param orderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	public <T> List<T> select(T object, JDBFilter filter, String[] orderColumns, Boolean nDesc)
			throws InstantiationException, IllegalAccessException, SQLException;

	/**
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException    
	 * @Title: select   
	 * @Description: 根据过滤条件查询数据
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @param filter
	 * @param: @param orderColumns
	 * @param: @param nDesc
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	public <T> List<T> select(T object, JDBFilter filter, String orderColumns, Boolean nDesc)
			throws InstantiationException, IllegalAccessException, SQLException;

	/**
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException    
	 * @Title: insert   
	 * @Description: 插入数据  
	 * @param: @param <T>
	 * @param: @param object
	 * @param: @return      
	 * @return: List<T>      
	 * @throws   
	 */
	public <T> int insert(T object) throws UnsupportedEncodingException, SQLException;

	/**
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException    
	 * @Title: insert   
	 * @Description: 插入数据
	 * @param: @param <T>
	 * @param: @param objects
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public <T> int insert(List<T> objects) throws UnsupportedEncodingException, SQLException;

	/**
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException    
	 * @Title: update   
	 * @Description: 更新数据库
	 * @param: @param <T>
	 * @param: @param objects
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public <T> int update(T objects, JDBFilter filter) throws UnsupportedEncodingException, SQLException;

	/**
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException    
	 * @Title: update   
	 * @Description: 更新数据库
	 * @param: @param <T>
	 * @param: @param objects
	 * @param: @param filter
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public <T> int update(List<T> objects, JDBFilter filter) throws UnsupportedEncodingException, SQLException;
}
