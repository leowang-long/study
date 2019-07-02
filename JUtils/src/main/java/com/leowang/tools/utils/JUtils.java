package com.leowang.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**   
 * @ClassName:  JUtils   
 * @Description:	TODO(这里用一句话描述这个类的作用)   
 * @author: 王龙(leowang)    
 * @date:   2019年6月6日 下午7:44:46   
 *      
  * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
 */
public class JUtils {

	public static final String dataTimeFormatType = "yyyy-MM-dd HH:mm:ss";
	public static final String timestampFormatType = "yyyy-MM-dd HH:mm:ss.SSS";

	/**   
	 * @Title: inputStreamToProperties   
	 * @Description: 将数据流转换为properties类型  
	 * @param: @param inputStream
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: Properties      
	 * @throws   
	 */
	public static Properties inputStreamToProperties(InputStream inputStream) throws IOException {
		Properties properties = null;

		if (null == inputStream)
			return properties;

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		properties = new Properties();
		properties.load(br);

		return properties;

	}

	/**   
	 * @Title: resultSetToMap   
	 * @Description: 将数据解析成ListMap   
	 * @param: @param resultSet
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<JMap<String,Object>>      
	 * @throws   
	 */
	public static List<JMap<String, Object>> resultSetToMap(ResultSet resultSet) throws SQLException {
		List<JMap<String, Object>> dataList = new ArrayList<JMap<String, Object>>();
		JMap<String, Object> dataMap = null;

		// 获得结果集结构信息,元数据
		ResultSetMetaData md = resultSet.getMetaData();
		// 获得列数
		int columnCount = md.getColumnCount();

		while (resultSet.next()) {
			dataMap = new JMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				dataMap.put(md.getColumnName(i), resultSet.getObject(i));
			}
			dataList.add(dataMap);
		}

		return dataList;
	}

	/**   
	 * @Title: objectToString   
	 * @Description: 将Objec对象转换为String类型   
	 * @param: @param object
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String objectToString(Object object) {
		String string = "";
		// if (object instanceof Integer) {
		// string = Integer.toString(object);
		// } else if (object instanceof String) {
		// string = object.toString();
		// } else if (object instanceof Double) {
		// string = object.toString();
		// } else if (object instanceof Float) {
		// string = String.valueOf(object);
		// } else if (object instanceof Long) {
		// string = Long.toString(object.);
		// } else if (object instanceof Boolean) {
		// string = String.valueOf(object);
		// } else
		// BigDecimal

		if (object instanceof String || object instanceof Boolean || object instanceof Short || object instanceof char[]
				|| object instanceof Double || object instanceof Float || object instanceof Integer
				|| object instanceof Long) {
			string = String.valueOf(object);
		}
		if (object instanceof char[]) {
			string = new String((char[]) object);
		}
		if (object instanceof byte[]) {
			string = new String((byte[]) object);
		}
		if (object instanceof BigDecimal) {
			if (null != object)
				string = ((BigDecimal) object).toString();
		}
		if (object instanceof java.sql.Date || object instanceof java.util.Date) {
			string = (new SimpleDateFormat(dataTimeFormatType)).format(object);
		}

		if (object instanceof Timestamp) {
			string = (new SimpleDateFormat(timestampFormatType)).format(object);
		}
		if (object instanceof Time) {
			string =((Time)object).toString();
		}		
		if (object instanceof Blob) {
			Blob b=(Blob)object;
			try {
				string=new String(b.getBytes(1, (int) b.length()),"UTF-8");
			} catch (UnsupportedEncodingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		if (object instanceof Clob) {
			Clob c=(Clob) object;
			try {
				string=c.getSubString(1, (int) c.length());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		if (object instanceof Struct) {

		}
		
		if (object instanceof java.sql.Array) {

		}
		if (object instanceof java.sql.ResultSet) {

		}
		
		if (object instanceof java.lang.reflect.Array || object instanceof Object[]) {
			string += "[";
			boolean first = true;
			for (Object tmpObject : Arrays.asList(object)) {
				if (first) {
					first = false;
				} else {
					string += ",";
				}
				string += objectToString(tmpObject);
			}
			string += "]";
		}
		if (object instanceof Map) {
			Map<Object, Object> mapData = (Map<Object, Object>) object;
			string += "{";
			boolean first = true;
			for (Object tmp : mapData.keySet()) {
				if (first) {
					first = false;
				} else {
					string += ",";
				}
				string += (objectToString(tmp) + ":" + objectToString(mapData.get(tmp)));
			}
			string += "}";
		}

		return string;
	}

}
