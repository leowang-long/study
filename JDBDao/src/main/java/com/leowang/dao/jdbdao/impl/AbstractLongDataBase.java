package com.leowang.dao.jdbdao.impl;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.leowang.dao.common.DBClose;
import com.leowang.dao.common.DataSourceType;
import com.leowang.dao.common.LongDataSource;
import com.leowang.dao.jdbdao.LongDataBase;
import com.leowang.dao.jdbdao.model.Table;

/**  

* <p>Title: AbstractLongDataBase</p>  

* <p>Description: </p>  

* @author leowang  

* @date 2019年5月29日  

*/  
public abstract class AbstractLongDataBase implements LongDataBase{

	private static Logger logger = Logger.getLogger(AbstractLongDataBase.class);
	
	protected DruidPooledConnection connection =null;
	
	protected LongDataSource dataSource=new LongDataSource();
	
	/* (non-Javadoc)  
	
	 * <p>Title: InitDataBase</p>  
	
	 * <p>Description: </p>  
	
	 * @param properties
	 * @return  
	
	 * @see com.leowang.dao.jdbdao.LongDataBase#InitDataBase(java.util.Properties)  
	
	 */
	public Boolean InitDataBase(Properties properties)
	{
		try {
			//创建Druid数据源
			DruidDataSource druidDataSource=null;
			//初始化数据源
			druidDataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
			//设置设置LongDataSource数据源
			dataSource.setDataSource(druidDataSource);
			//数据源类型
			DataSourceType dataSourceType = null;
			//获取数据源类型
			dataSourceType=DataSourceType.getDataSourceType(properties.getProperty("driverClassName"));
			//设置LongDataSource数据源类型
			dataSource.setDataSourceType(dataSourceType);
			//获取连接
			connection=druidDataSource.getConnection();
			//设置LongDataSource数据表结构
			dataSource.setTables(getDataBaseTables());
			//关闭连接
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			return false;
		}finally {
			// TODO: handle finally clause
			DBClose.close(connection, null, null, null);
		}
		return true;
	}
	
	/**  
	
	 * <p>Title: getDataBaseTables</p>  
	
	 * <p>Description: </p>  
	
	 * @return  
	
	 */  
	public abstract HashMap<String, Table> getDataBaseTables() throws Exception;

	/* (non-Javadoc)  
	
	 * <p>Title: getDataSource</p>  
	
	 * <p>Description: </p>  
	
	 * @return  
	
	 * @see com.leowang.dao.jdbdao.LongDataBase#getDataSource()  
	
	 */
	public LongDataSource getDataSource() {
		return dataSource;
	}
	
}
