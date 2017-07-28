package com.fangjt.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.fangjt.common.annotation.Column;


/**驼峰写法和下划线写法互转*/
public class ReflactUtils {
	  public static String operateColumn(Field field){
			for (Annotation annotation : field.getAnnotations()){
				if(annotation.getClass().equals(Column.class)){
					Column column = (Column)annotation;
					return column.name()!=""?column.name():CamelUtils.camelToUnderline(field.getName()) ; 
				}
			}
			return CamelUtils.camelToUnderline(field.getName());
	    } 
}
