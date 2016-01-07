/**
 * 
 */
package com.zimu.javacore.utils;

import java.util.Comparator;

/**
 * @author jasonChiu
 * 
 * @title 字符串比较器，用来比较ASCII码比较
 * 
 * @time 2016年1月7日下午8:08:32
 * @version 1.0
 */
public class MapKeyComparator implements Comparator<String> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
