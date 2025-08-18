package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<String, Object> params, String key, Class<T> tclass) {
		Object obj = params.getOrDefault(key, null);
		if(obj != null) {
			if(tclass.getTypeName().equals("java.lang.Long")) {
				obj = obj != "" ? Long.valueOf(obj.toString()) : null;
			}
			else if(tclass.getTypeName().equals("java.lang.Integer")) {
				obj = obj != "" ? Integer.valueOf(obj.toString()) : null;
			}
			else if(tclass.getTypeName().equals("java.lang.String")){
				obj = obj.toString();
			}
			return tclass.cast(obj);
		}
		return null;
	}
}
