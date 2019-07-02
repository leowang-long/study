package com.leowang.tools.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class testMain 
{
	public static final String timestampFormatType = "yyyy-MM-dd HH:mm:ss.SSS";
    public static void main( String[] args )
    {
    	Timestamp timestamp=new Timestamp(966666279);
    	timestamp=timestamp.valueOf("2023-01-12 12:31:06.279");
    	SimpleDateFormat sdf = new SimpleDateFormat(timestampFormatType); 
    	Time time=new Time(timestamp.getTime());

        System.out.println( time.toString());
        System.out.println( sdf.format(timestamp) );
    }
}
