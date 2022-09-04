package com.example.mymusic.common.req;

/**
 * @Author 劳威锟
 * @Description //方法说明： 前端返回码
 * @Date 18:18 2022/1/7
 * @Param
 * @return
 **/
public class WebResponseCode {

    public static int SUCCESS = 1;                // 成功

    public static int FAIL = 0;                    // 失败

    public static int INVALID = 2;                // 无权访问，重新登录

    public static int HAVED = 3;                // 种子被领取，重新抢

    public static int SEEDFAIL = 4;                // 种子领取失败。

}
