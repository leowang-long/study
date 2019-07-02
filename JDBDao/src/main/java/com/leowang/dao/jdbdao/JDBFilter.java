package com.leowang.dao.jdbdao;

import java.util.List;

/**   
 * @ClassName:  JDBFilter   
 * @Description:	查询或更新where数据过滤器
 * @author: 王龙(leowang)    
 * @date:   2019年6月23日 上午11:14:36   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public interface JDBFilter {
	/**   
	 * @Title: columnEqualTo   
	 * @Description: 等于 =
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnEqualTo(String columnName,Object columnData);	
	

	/**   
	 * @Title: columnNotEqualTo   
	 * @Description: 不等于 !=
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnNotEqualTo(String columnName,Object columnData);


	/**   
	 * @Title: columnIn   
	 * @Description: in
	 * @param: @param columnName
	 * @param: @param datas      
	 * @return: void      
	 * @throws   
	 */
	public void columnIn(String columnName,List<Object> datas);
	
	/**   
	 * @Title: columnLike   
	 * @Description: 模糊查询 like '%**%'
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnLike(String columnName,String columnData);
	
	
	/**   
	 * @Title: columnGreaterThan   
	 * @Description: 大于 >
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnGreaterThan(String columnName,Object columnData);
	
	/**   
	 * @Title: columnGreaterThanOrEqualTo   
	 * @Description: 大于或等于  >=
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnGreaterThanOrEqualTo(String columnName,Object columnData);

	/**   
	 * @Title: columnLessThan   
	 * @Description: 小于 <
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnLessThan(String columnName,Object columnData);
	
	/**   
	 * @Title: columnLessThanOrEqualTo   
	 * @Description: 小于或等于 <=
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnLessThanOrEqualTo(String columnName,Object columnData);
	
	/**   
	 * @Title: columnLikeStart   
	 * @Description: 模糊查询从**开始 like '**%' 
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnLikeStart(String columnName,String columnData);
	

	/**   
	 * @Title: columnLikeRight   
	 * @Description: 模糊查询 到**结束 like '%**'
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnLikeEnd(String columnName,String columnData);
	
	/**   
	 * @Title: columnNoLike   
	 * @Description: 模糊查询 不包含 not like '%**%' 
	 * @param: @param columnName
	 * @param: @param columnData      
	 * @return: void      
	 * @throws   
	 */
	public void columnNoLike(String columnName,String columnData);
	
	/**   
	 * @Title: getFilterSql   
	 * @Description: 获取筛选语句
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String getFilterSql();
	
	/**   
	 * @Title: getParamList   
	 * @Description: 获取参数列表
	 * @param: @return      
	 * @return: List<Object>      
	 * @throws   
	 */
	public List<Object> getParamList();
	
}
