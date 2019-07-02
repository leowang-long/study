package com.leowang.dao.jdbdao.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.leowang.dao.common.LongDataSource;
import com.leowang.dao.jdbdao.column.type.ColumnType;
import com.leowang.dao.jdbdao.column.type.ColumnType.OracleColumnType;
import com.leowang.tools.utils.JUtils;

/**   
 * @ClassName:  ManageSqlOracleImpl   
 * @Description: oracle语句生成工具类  
 * @author: 王龙(leowang)    
 * @date:   2019年6月3日 下午10:52:19   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class ManageSqlOracleImpl extends AbstractManageSql {

	public ManageSqlOracleImpl(LongDataSource longDataSource)
	{
		super.dataSource=longDataSource;
	}
	@Override
	public Object objecttToDBType(Object dataObject, ColumnType dBType) throws SerialException, SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Object returnObject=dataObject;
		OracleColumnType columnType=(OracleColumnType) dBType;
		switch(columnType)
		{
		case CHAR:	
		case NCHAR:///			
		case VARCHAR:
		case  VARCHAR2:
		case  NVARCHAR2:///
		case  LONG:
			if(!(dataObject instanceof String)){
				returnObject=JUtils.objectToString(dataObject);
			}
			break;
		case  FLOAT:////
		case  BINARY_FLOAT:////
		case  BINARY_DOUBLE:////
			if(!(dataObject instanceof String)){
				returnObject=JUtils.objectToString(dataObject);
			}
			break;

		case NUMBER:
			if(!(dataObject instanceof BigDecimal)){
				String tmpString=JUtils.objectToString(dataObject);
				
				returnObject=new BigDecimal(tmpString);
			}
			break;

		case  RAW:
		case  LONGRAW:
			if(!(dataObject instanceof byte[])){
				String tmpString=JUtils.objectToString(dataObject);
				
				returnObject=tmpString.getBytes();
			}
			break;

		case  DATE:
			if(!(dataObject instanceof java.sql.Date || dataObject instanceof java.util.Date)){
				String tmpString=JUtils.objectToString(dataObject);
				java.sql.Timestamp date;
				date=java.sql.Timestamp.valueOf(tmpString);
				returnObject=date;
			}
			break;

		case TIMESTAMP:
			if(!(dataObject instanceof Timestamp)) {
				String tmpString=JUtils.objectToString(dataObject);
				Timestamp timestamp;
				timestamp=Timestamp.valueOf(tmpString);
				returnObject=timestamp;
			}
			break;

		case  BFILE:///
		case  BLOB:
			if(!(dataObject instanceof Blob)){
				String tmpString=JUtils.objectToString(dataObject);
				returnObject=new SerialBlob(tmpString.getBytes("UTF-8"));
			}
			break;

		case CLOB:
		case NCLOB:///
			if(!(dataObject instanceof Clob)){
				String tmpString=JUtils.objectToString(dataObject);
				returnObject=new SerialClob(tmpString.toCharArray());
			}
			break;

		case  UNKNOWN://ROWID, UROWID
			break;
		default:
			break;
		}
		return returnObject;
	}

	
}
