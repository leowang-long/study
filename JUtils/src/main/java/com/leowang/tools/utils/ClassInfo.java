package com.leowang.tools.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
* @ClassName:  ClassInfo   
* @Description:对象信息
* @author: 王龙(leowang)    
* @date:   2019年6月23日 下午2:57:13   
*      
 * 注意：本内容仅限于个人学习使用，禁止外泄以及用于其他的商业目地
*/
public class ClassInfo {
	/**   
	 * @Fields objMethodMap :对象中的方法   
	 */
	Map<String, Method> objMethodMap = new HashMap<String, Method>();

	/**   
	 * @Fields objFieldMap : 对象中的变量
	 */
	Map<String, Field> objFieldMap = new HashMap<String, Field>();

	/**   
	 * @Fields getMethodMap : 带get方法的成员函数
	 */
	List<String> getMethodName = new ArrayList<String>();

	public ClassInfo(Object object) {
		for (Method method : object.getClass().getDeclaredMethods()) {
//				object.getClass().getMethod("", String.class).
//			String methodNameString=method.getName();
//			if("set".equals(methodNameString.substring(0, 3)))
//			{
//				objMethodMap.put("set"+","+methodNameString.substring(3).toLowerCase(), method);
//			}
			objMethodMap.put(method.getName().toLowerCase(), method);
			if ("get".equals(method.getName().substring(0, 3))) {
				getMethodName.add(method.getName());
			}
		}
		for (Field field : object.getClass().getDeclaredFields()) {
			objFieldMap.put(field.getName().toLowerCase(), field);
		}
	}

	/**   
	 * @Title: runMethod   
	 * @Description: 执行成员函数
	 * @param: @param methodName 成员函数名
	 * @param: @param classObject 对象
	 * @param: @param params
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	public Object runMethod(String methodName, final Object classObject, Object... params) {
		Object object = null;
		if (!objMethodMap.containsKey(methodName.toLowerCase()))
			return object;
		try {
			if (null == params)
				object = objMethodMap.get(methodName.toLowerCase()).invoke(classObject);
			else
				object = objMethodMap.get(methodName.toLowerCase()).invoke(classObject, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	/**   
	 * @Title: getFieldValue   
	 * @Description: 获取对象成员变量值
	 * @param: @param fieldName 变量名
	 * @param: @param classObject 对象
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	public Object getFieldValue(String fieldName, final Object classObject) {
		Object object = null;
		if (!objFieldMap.containsKey(fieldName.toLowerCase()))
			return object;
		try {
			object = objFieldMap.get(fieldName.toLowerCase()).get(classObject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	/**   
	 * @Title: getGetMethodName   
	 * @Description: 获取带Get的成员函数
	 * @param: @return      
	 * @return: List<String>     
	 * @throws   
	 */
	public List<String> getGetMethodName() {
		return getMethodName;
	}

}
