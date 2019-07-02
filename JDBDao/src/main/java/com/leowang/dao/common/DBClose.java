package com.leowang.dao.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidPooledConnection;

 /**   
 * @ClassName:  DBClose   
 * @Description:	TODO(这里用一句话描述这个类的作用)   
 * @author: 王龙(leowang)    
 * @date:   2019年6月9日 下午11:04:36   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class DBClose {

	//释放资源
	/**   
	 * @Title: close   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param connection
	 * @param: @param ps
	 * @param: @param stmt
	 * @param: @param resultSet      
	 * @return: void      
	 * @throws   
	 */
	public static void close(DruidPooledConnection connection,PreparedStatement ps, Statement stmt, ResultSet resultSet){
	    if(null !=connection){
	        try {
	        	connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        connection = null;
	    }
	    if(null !=ps){
	        try {
	        	ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        ps = null;
	    }
	    if(null != stmt){
	        try {
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        stmt = null;
	    }
	    if(null != resultSet){
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        resultSet = null;
	    }
	}
}
