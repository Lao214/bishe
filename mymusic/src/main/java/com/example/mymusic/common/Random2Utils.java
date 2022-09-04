
package com.example.mymusic.common;


import org.apache.commons.lang3.RandomUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 
 * 类说明：随机生成字符串
 * @author 聂枫
 * 2016年1月21日下午5:32:04
 */
public class Random2Utils {
	
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";
	public static final int RANDOM_START_INCLUSIVE = 1000;
	public static final int RANDOM__END_EXCLUSIVE = 10000;
	public static final int ID_LENGTH = 8;
	public static final int idnum = 6;
	/**
	 * 会员日期   注册数量
	 */
	public static String memberDate = "";
	public static int memberNum = 0;
	/**
	 * 账户日期   注册数量
	 */
	public static String accountDate = "";
	public static int accountNum = 0;
	/**
	 * 订单日期  生成数量
	 */
	public static String orderDate = "";
	public static int orderNum = 0;
	/**
	 * 订单商品日期  生成数量
	 */
	public static String orderProductsDate = "";
	public static int orderProductsNum = 0;
	/**
	 * 优惠券日期  生成数量
	 */
	public static String voucherDate = "";
	public static int voucherNum = 0;
	/**
	 *  二维码日期  生成数量
	 */
	public static String barcodeDate = "";
	public static int barcodeNum = 0;
	/**
	 *  二维码批次日期  生成数量
	 */
	public static String barBatchDate = "";
	public static int barBatchNum = 0;

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     * 
     * @param length
     *            字符串长度
     * @return 纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     * 
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     * 
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }
    
    /**
     * 
     * 方法说明：账户生成编号
     * @author 聂枫
     * 2016年1月21日下午5:42:56
     * @param prefix
     * @return
     */
    public synchronized static String accountBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(accountDate)){
    		accountNum++;
    	}else{
    		accountDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		accountNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(accountNum,ID_LENGTH);
	}
    
    
    /**
     * 
     * 方法说明：会员生成编号
     * @author 聂枫
     * 2016年1月21日下午5:42:56
     * @param prefix
     * @return
     */
    public synchronized static String memberBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(memberDate)){
    		memberNum++;
    	}else{
    		memberDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		memberNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(memberNum,ID_LENGTH);
	}
    /**
     * 
     * 方法说明：订单生成编号
     * @author 聂枫
     * 2016年1月21日下午5:43:10
     * @param prefix
     * @return
     */
    public synchronized static String orderBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(orderDate)){
    		orderNum++;
    	}else{
    		orderDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		orderNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(orderNum,ID_LENGTH);
	}
    /**
     * 
     * 方法说明：订单商品生成编号
     * @author 聂枫
     * 2016年1月21日下午5:43:10
     * @param prefix
     * @return
     */
    public synchronized static String orderProductsBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(orderProductsDate)){
    		orderProductsNum++;
    	}else{
    		orderProductsDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		orderProductsNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(orderProductsNum,ID_LENGTH);
	}
    /**
     * 
     * 方法说明：优惠券生成编号
     * @author 聂枫
     * 2016年1月21日下午5:43:10
     * @param prefix
     * @return
     */
    public synchronized static String voucherBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(voucherDate)){
    		voucherNum++;
    	}else{
    		voucherDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		voucherNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(voucherNum,ID_LENGTH);
	}
    /**
     * 
     * 方法说明：统一生成编号
     * @author 聂枫
     * 2016年1月5日下午8:21:20
     * @param prefix
     * @return
     */
	public synchronized static String buildSn(String prefix) {
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + RandomUtils.nextInt(RANDOM_START_INCLUSIVE,RANDOM__END_EXCLUSIVE);
	}
    
    public static void main(String[] args) {
        System.out.println(generateString(15));
        System.out.println(generateMixString(15));
        System.out.println(generateLowerString(15));
        System.out.println(generateUpperString(15));
        System.out.println(generateZeroString(15));
        System.out.println(toFixdLengthString(123, 15));
        System.out.println(toFixdLengthString(123L, 15));
        long a = System.currentTimeMillis();
        System.out.println(a);
        //低效代码
        System.out.println(DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyyMMddHHmmssSSS));
        long b = System.currentTimeMillis();
        System.out.println(b-a);
        //优化的代码
        System.out.println(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()));
        long c = System.currentTimeMillis();
        System.out.println(c-b);
    }
    
    /**
     * 返回一个定长的随机数字串(纯数字)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateNumString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }
    public synchronized static String barcodeBuildSn(String prefix) {
    	if(new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date()).equals(barcodeDate)){
    		barcodeNum++;
    	}else{
    		barcodeDate = new SimpleDateFormat(DateUtils.PATTERN_yyyyMMdd).format(new Date());
    		barcodeNum=1;
    	}
		return prefix + new SimpleDateFormat(DateUtils.PATTERN_yyyyMMddHHmmssSSS).format(new Date()) + toFixdLengthString(barcodeNum,idnum);
	}
}
