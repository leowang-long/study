package com.leowang.dao.jdbdao.model;

import com.leowang.dao.jdbdao.column.type.ColumnType;

/**   
 * @ClassName:  Column   
 * @Description: 数据表列信息   
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午8:00:38   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class Column {
	//字段名称
	private String name;
	//字段类型
	private ColumnType type;
	//字段长度
	private int length;
	//字段精度
	private int precision;
	//可为空
	private Boolean isNull;
	//默认值
	private String defaults;
	//是主键
	private Boolean isPK;
	//字段描述
	private String comments;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ColumnType getType() {
		return type;
	}
	public void setType(ColumnType type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Boolean getIsNull() {
		return isNull;
	}
	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}
	public Boolean getIsPK() {
		return isPK;
	}
	public void setIsPK(Boolean isPK) {
		this.isPK = isPK;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	
	
}
