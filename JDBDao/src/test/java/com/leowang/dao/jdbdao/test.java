package com.leowang.dao.jdbdao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.leowang.tools.utils.ClassInfo;

public class test {

	public static void main(String[] args) {
		testClass object=new testClass();
//			JDBDao jdbDao=new JDBDao();
//		Map<String, Method> objMethodMap=new HashMap<String, Method>();
//
//		Map<String, Field> objFieldMap=new HashMap<String, Field>();
//
//		for(Method method:object.getClass().getDeclaredMethods())
//		{
////				object.getClass().getMethod("", String.class).
//			String methodNameString=method.getName();
//			if("set".equals(methodNameString.substring(0, 3)))
//			{
//				objMethodMap.put("set"+","+methodNameString.substring(3).toLowerCase(), method);
//			}
//		}
//		for(Field field:object.getClass().getDeclaredFields())
//		{
//			objFieldMap.put(field.getName(), field);
//		}
		testClass test=new testClass();
		ClassInfo testInfo=new ClassInfo(new testClass());
		try {
//			objMethodMap.get("set,name").invoke(test, "qweqweqw12312312");
//			objMethodMap.get("set,code").invoke(test, 33231);
//			objMethodMap.get("set,age").invoke(test, new Date());
//			
//			Object object2=objFieldMap.get("TableName").get(test);
			
			testInfo.runMethod("setname", test, "asdasdasda");
			testInfo.runMethod("setcode", test, 65646);
			testInfo.runMethod("setage", test, new Date());
			Object obj=testInfo.getFieldValue("TableName", test);
			System.out.println(obj);
			System.out.println(test);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
