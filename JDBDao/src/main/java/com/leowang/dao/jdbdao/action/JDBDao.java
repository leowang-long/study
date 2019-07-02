package com.leowang.dao.jdbdao.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.leowang.dao.common.DBClose;
import com.leowang.dao.common.DataSourceType;
import com.leowang.dao.common.LongDataSource;
import com.leowang.dao.jdbdao.JDBFilter;
import com.leowang.dao.jdbdao.LongDataBase;
import com.leowang.dao.jdbdao.impl.AbstractJDBDao;
import com.leowang.dao.jdbdao.impl.JDBFilterOracleImpl;
import com.leowang.dao.jdbdao.impl.LongDataBaseOracleImpl;
import com.leowang.dao.jdbdao.impl.ManageSqlOracleImpl;
import com.leowang.tools.utils.JUtils;

/**   
* @ClassName:  JDBDao   
* @Description: 数据库操作工具类   
* @author: 王龙(leowang)    
* @date:   2019年6月3日 下午8:06:38   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public class JDBDao extends AbstractJDBDao {
	private static Logger logger = Logger.getLogger(JDBDao.class);

	// 首次使用对JDBDao进行初始化
	static {
		try {
			// 获取默认数据源信息
			InputStream inputStream = JDBDao.class.getClassLoader().getResourceAsStream("DataSource.properties");
			// 建立临时数据源
			LongDataSource dataSource = null;

			// 将数据流转换为Properties
			Properties props = JUtils.inputStreamToProperties(inputStream);
			// 数据源名称信息
			String dataSourceFileNames = "";
			if (null != props) {
				// 获取其他数据源名称
				dataSourceFileNames = props.getProperty("dataSourceFileNames");
				if (null == dataSourceFileNames || dataSourceFileNames.isEmpty()) {
					dataSourceFileNames = "";
				}
			}
			// 初始化数据源信息
			dataSource = initDataSource(props);

			if (null != dataSource) {
				// 保存数据源
				dataSources.put("DataSource", dataSource);
			}
			// 遍历并处理其他数据源
			for (String dataSourceFileName : dataSourceFileNames.split(",")) {

				inputStream = JDBDao.class.getClassLoader().getResourceAsStream(dataSourceFileName + ".properties");

				dataSource = initDataSource(props);

				if (null != dataSource) {
					dataSources.put("DataSource", dataSource);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws SQLException 
	 * @Title: JDBDao @Description: 无参构造函数，使用datasource数据源 @param: @throws
	 */
	public JDBDao() throws SQLException {
		dataSource = dataSources.get("DataSource");
		connection = dataSource.getDataSource().getConnection();
		InitResource("DataSource");
	}

	/**
	 * @throws SQLException 
	 * @Title: JDBDao @Description: 有参数构造函数，使用指定数据源 @param: @param
	 * dataSourceName @throws
	 */
	public JDBDao(String dataSourceName) throws SQLException {
		dataSource = dataSources.get(dataSourceName);
		connection = dataSource.getDataSource().getConnection();
		InitResource(dataSourceName);
	}

	private void InitResource(String dataSourceName) {
		System.gc();
		switch (dataSources.get(dataSourceName).getDataSourceType()) {
		case ORACLE:
			manageSql = new ManageSqlOracleImpl(dataSources.get(dataSourceName));
			break;

		default:
			break;
		}
	}

	/**   
	 * @Title: initDataSource   
	 * @Description: 根据参数流文件初始化数据源   
	 * @param: @param props
	 * @param: @return      
	 * @return: LongDataSource      
	 * @throws   
	 */
	public static LongDataSource initDataSource(Properties props) {
		LongDataSource dataSource = null;
		LongDataBase dataBase = null;
		try {
			// 获取驱动名称
			String driverClassName = "";
			if (null != props)
				driverClassName = props.getProperty("driverClassName");
			// 根据驱动名称获取数据库类型
			DataSourceType dataSourceType = DataSourceType.getDataSourceType(driverClassName);

			// 根据数据库类型初始化数据源
			switch (dataSourceType) {
			case ORACLE:
				dataBase = new LongDataBaseOracleImpl(props);
				break;

			default:
				break;
			}
			dataSource = dataBase.getDataSource();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		// TODO Auto-generated method stub
		return dataSource;
	}

	protected void finalize() {
		DBClose.close(connection, ps, null, resultSet);
	}
	
	public JDBFilter getFilter()
	{
		JDBFilter filter=null;
		switch (getDBType()) {
		case ORACLE:
			filter=new JDBFilterOracleImpl();
			break;

		default:
			break;
		}
		return filter;
	}
	
	/**   
	 * @Title: getDBType   
	 * @Description: 获取数据库类型 
	 * @param: @return      
	 * @return: DataSourceType      
	 * @throws   
	 */
	private DataSourceType getDBType() {
		DataSourceType dbType=DataSourceType.NULL;
		if(null!=dataSource)
		{
			dbType=dataSource.getDataSourceType();
		}
		return dbType;
	}
}
