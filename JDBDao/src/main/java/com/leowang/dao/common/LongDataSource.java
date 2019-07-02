package com.leowang.dao.common;

import java.util.HashMap;

import com.alibaba.druid.pool.DruidDataSource;
import com.leowang.dao.jdbdao.model.Table;


 /**   
 * @ClassName:  LongDataSource   
 * @Description: 自定义数据源
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:02:35   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class LongDataSource {
	private DruidDataSource dataSource;
	private DataSourceType dataSourceType;
	private HashMap<String, Table> tables;

	
	public DruidDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public DataSourceType getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(DataSourceType dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public HashMap<String, Table> getTables() {
		return tables;
	}
	public void setTables(HashMap<String, Table> tables) {
		this.tables = tables;
	}
	
}
