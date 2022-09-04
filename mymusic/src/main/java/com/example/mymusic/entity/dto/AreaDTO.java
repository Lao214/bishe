package com.example.mymusic.entity.dto;


import com.example.mymusic.entity.Area;

/**
 *
 * 类说明：地区表
 * @author 黄丽星
 * @date 2018年09月10日
 */
public class AreaDTO extends Area {
    private static final long serialVersionUID = 1L;
    /**
     * 代理商姓名
     */
    private String memberName;
    /**
     * 手机号
     */
    private String mobileNo;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        StringBuilder bulider = new StringBuilder("AreaDTO [");
        bulider.append("memberName=")
                .append(memberName);
        bulider.append(",mobileNo=")
                .append(mobileNo);
        bulider.append(']');
        return bulider.toString();
    }
}
