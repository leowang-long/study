package com.leowang.dao.jdbdao.model;

import java.util.List;


 /**   
 * @ClassName:  Table   
 * @Description: 数据表结构  
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:01:08   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class Table {
	//表表名
	private String name;
	//主键
	private List<String> PK;
	//不可为空字段
	private List<Column> notNullColumns;
	//可为空字段
	private List<Column> nullColumns;
	//表描述
	private String comments;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPK() {
		return PK;
	}
	public void setPK(List<String> pK) {
		PK = pK;
	}
	public List<Column> getNotNullColumns() {
		return notNullColumns;
	}
	public void setNotNullColumns(List<Column> notNullColumns) {
		this.notNullColumns = notNullColumns;
	}
	public List<Column> getNullColumns() {
		return nullColumns;
	}
	public void setNullColumns(List<Column> nullColumns) {
		this.nullColumns = nullColumns;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}	
	
}
