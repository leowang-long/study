package com.leowang.dao.common;

import java.util.List;

/**   
 * @ClassName:  JDBFilter   
 * @Description:	TODO(这里用一句话描述这个类的作用)   
 * @author: 王龙(leowang)    
 * @date:   2019年6月23日 上午8:38:49   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class JDBFilter {
	private String filterSql="";
	private List<Object> paramList;
	private boolean first=true;
	private void addFilterSql(String columnSql) {
		if(first)
		{
			filterSql=" where "+columnSql;
		}
		else {
			filterSql+="and"+columnSql;
		}
		
	}
	public void columnEqualTo(String columnName,Object object) {
		String columnSql="";
		
	}
	
	public String getFilterSql() {
		return filterSql;
	}

	public void setFilterSql(String filterSql) {
		this.filterSql = filterSql;
	}
	
}
