package com.leowang.tools.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) {

		
		JMap<String, Object> jMap=new JMap<>();
		Object object;
		Integer mInteger=2222222;
		jMap.put("Integer", mInteger);
		object=mInteger;
		System.out.println("Integer:"+JUtils.objectToString(object));
		String string="string";
		jMap.put("String", string);
		object=string;
		System.out.println("String:"+JUtils.objectToString(object));
		Double double1=1231.11231;
		jMap.put("Double", double1);
		object=double1;
		System.out.println("Double:"+JUtils.objectToString(object));
		Float float1=(float) 12.23;
		object=float1;
		System.out.println("Float:"+JUtils.objectToString(object));
		Long long1=(long) 33333333;
		jMap.put("Long", long1);
		object=long1;
		System.out.println("Long:"+JUtils.objectToString(object));
		Boolean boolean1=true;
		jMap.put("Boolean", boolean1);
		object=boolean1;
		System.out.println("Boolean:"+JUtils.objectToString(object));
		Byte byte1=12;
		jMap.put("Byte", byte1);
		object=byte1;
		System.out.println("Byte:"+JUtils.objectToString(object));
		Date date=new Date();
		jMap.put("Date", date);
		object=date;
		System.out.println("Date:"+JUtils.objectToString(object));
		boolean b=false;
		jMap.put("boolean", b);
		object=b;
		System.out.println("boolean:"+JUtils.objectToString(object));
		char c='q';
		jMap.put("char", c);
		object=c;
		System.out.println("char:"+JUtils.objectToString(object));
		char[] cr={'p','p','p','p','p','p'};
		jMap.put("char[]", cr);
		object=cr;
		System.out.println("char[]:"+JUtils.objectToString(object));
		double d=43534.2333;
		jMap.put("double", d);
		object=d;
		System.out.println("double:"+JUtils.objectToString(object));
		float f=(float) 23432.123;
		jMap.put("float", f);
		object=f;
		System.out.println("float:"+JUtils.objectToString(object));
		long l=9999999;
		jMap.put("long", l);
		object=l;
		System.out.println("long:"+JUtils.objectToString(object));
		int i=111;
		jMap.put("int", i);
		object=i;
		System.out.println("int:"+JUtils.objectToString(object));
		byte b1=34;
		jMap.put("byte", b1);
		object=b1;
		List<String> strings=Arrays.asList("1111111","22222222","33333333");
		jMap.put("List<String> ", strings);
		System.out.println("byte:"+JUtils.objectToString(object));
		System.out.println(jMap.toJsion().toString());
		System.out.println(JUtils.objectToString(jMap));
		System.out.println(jMap.toString());
		
	}

}
