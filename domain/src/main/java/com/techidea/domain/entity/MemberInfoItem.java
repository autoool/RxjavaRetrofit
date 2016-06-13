package com.techidea.domain.entity;

import java.io.Serializable;

/**
 * Created by zchao on 2016/1/29.
 * 会员积分
 */
public class MemberInfoItem implements Serializable {

    private String memberId;
    private String memberName;
    private String type;
    private String currentValue;
    private String amount;
    private String memberRemainPoint;
    private String memberPoint;
    private String deductionPoint;
    private String memberNum;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMemberRemainPoint() {
        return memberRemainPoint;
    }

    public void setMemberRemainPoint(String memberRemainPoint) {
        this.memberRemainPoint = memberRemainPoint;
    }

    public String getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(String memberPoint) {
        this.memberPoint = memberPoint;
    }

    public String getDeductionPoint() {
        return deductionPoint;
    }

    public void setDeductionPoint(String deductionPoint) {
        this.deductionPoint = deductionPoint;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
}
