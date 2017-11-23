package hpu.edu.dlq;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public enum ResultEnum {
    //枚举的使用
    UNKNOW_ERROR(-1,"未知的错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"你可能还在上小学"),
    MIDDLE_SCHOOL(101,"你可能还在上小学"),
    ;
    private Integer code;
    private String msg;
    ResultEnum (Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
