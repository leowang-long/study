package com.leowang.dao.jdbdao;

import java.util.Properties;

import com.leowang.dao.common.LongDataSource;

 /**   
 * @ClassName:  LongDataBase   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:04:42   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public interface LongDataBase {
	

	/**   
	 * @Title: InitDataBase   
	 * @Description: 初始化数据源
	 * @param: @param properties
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean InitDataBase(Properties properties);
	
 
	/**   
	 * @Title: getDataSource   
	 * @Description: 获取数据源  
	 * @param: @return      
	 * @return: LongDataSource      
	 * @throws   
	 */
	public LongDataSource getDataSource();

}
