package com.example.mymusic.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 类说明：实体类赋值
 * @author 丰林林
 * 2016年5月25日上午9:55:16
 */
public class BeanUtils {
	
    /**
     * 方法说明：获取空属性
     * @author 丰林林
     * 2016年5月25日上午9:56:43
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
    	 final BeanWrapper src = new BeanWrapperImpl(source);
    	 java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    	 Set<String> emptyNames = new HashSet<String>();
    	 for(java.beans.PropertyDescriptor pd : pds) {
	    	 Object srcValue = src.getPropertyValue(pd.getName());
	    	 if (srcValue == null) emptyNames.add(pd.getName());
    	 }
    	 String[] result = new String[emptyNames.size()];
    	 return emptyNames.toArray(result);
    }
    /**
     * 方法说明：忽略空值
     * @author 丰林林
     * 2016年5月25日上午9:57:18
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target,boolean flag){
    	if(flag){
    		org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    	}else{
    		org.springframework.beans.BeanUtils.copyProperties(src, target);
    	}
    }
}
