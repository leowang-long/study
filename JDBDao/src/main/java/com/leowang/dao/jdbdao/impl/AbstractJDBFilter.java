package com.leowang.dao.jdbdao.impl;

import java.util.ArrayList;
import java.util.List;

import com.leowang.dao.jdbdao.JDBFilter;

public abstract class AbstractJDBFilter implements JDBFilter {

	/**   
	 * @Fields filterSql : 过滤条件 
	 */  
	protected String filterSql="";
	
	/**   
	 * @Fields paramList : 过滤传递内容 
	 */  
	protected List<Object> paramList;
	
	/**   
	 * @Fields first : 是否第一次
	 */  
	private boolean first=true;
	
	/**   
	 * @Title: getSupperInfo   
	 * @Description: 获取语句拼装的内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	private String getSupperInfo()
	{
		String str="";
		if(first)
		{
			str=" where ";
			paramList=new ArrayList<Object>();
			first=false;
		}
		else {
			str=" and ";			
		}
		return str;
	}
	
	@Override
	public void columnEqualTo(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+"=?");
		paramList.add(columnData);		
	}

	@Override
	public void columnNotEqualTo(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+"!=?");
		paramList.add(columnData);		
		
	}

	@Override
	public void columnIn(String columnName, List<Object> datas) {
		// TODO Auto-generated method stub
		String inSql="";
		boolean tmpFirst=true;
		for(Object obj : datas)
		{
			if(tmpFirst) {
				inSql=columnName+"in (";
				tmpFirst=false;
			}
			else {
				inSql+=",";
			}
			inSql+="?";
			paramList.add(obj);
		}
		inSql+=")";

		filterSql+=(this.getSupperInfo()+inSql);		
	}

	@Override
	public void columnLike(String columnName, String columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+" like '%"+columnData+"%'");
	}

	@Override
	public void columnGreaterThan(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+">?");
		paramList.add(columnData);	
	}
			

	@Override
	public void columnGreaterThanOrEqualTo(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+">=?");
		paramList.add(columnData);	
		
	}

	@Override
	public void columnLessThan(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+"<?");
		paramList.add(columnData);	
		
	}

	@Override
	public void columnLessThanOrEqualTo(String columnName, Object columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+"<=?");
		paramList.add(columnData);	
		
	}

	@Override
	public void columnLikeStart(String columnName, String columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+" like '"+columnData+"%'");

	}

	@Override
	public void columnNoLike(String columnName, String columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+" not like '%"+columnData+"%'");
		
	}

	@Override
	public void columnLikeEnd(String columnName, String columnData) {
		// TODO Auto-generated method stub
		filterSql+=(this.getSupperInfo()+columnName+" like '%"+columnData+"'");

	}

	@Override
	public String getFilterSql() {
		// TODO Auto-generated method stub
		return filterSql;
	}

	@Override
	public List<Object> getParamList() {
		// TODO Auto-generated method stub
		return paramList;
	}

	
}
