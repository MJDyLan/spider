package com.zimu.javacore.utils;

import org.apache.commons.lang3.ArrayUtils;

public final class MyArrayUtils extends ArrayUtils{

	/**
	 * 字符串数组是否包含字符串，不区分大小写.
	 * 
	 * @param array
	 * @param obj
	 * @return
	 */
	public static boolean containsIgnoreCase(String[] array, String obj) {

		if (MyArrayUtils.isEmpty(array) || obj == null) {
			return false;
		}
		for (String a : array) {
			if (a.equalsIgnoreCase(obj.trim())) {
				return true;
			}
		}
		return false;
	}
}
