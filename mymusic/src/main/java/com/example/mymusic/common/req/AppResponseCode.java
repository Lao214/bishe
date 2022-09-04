package com.example.mymusic.common.req;

/**
 * 类说明：app返回码
 *
 * @author 聂枫
 * 2015年12月21日上午10:34:12
 */
public class AppResponseCode {

    public static int SUCCESS = 1;                // 成功

    public static int FAIL = 0;                    // 失败

    public static int INVALID = 2;                // 无权访问，重新登录

    public static int SEEDINVALID = 3;                // 种子被领取，重新抢

    public static int SEEDFAIL = 4;                // 种子领取失败。

}
