package com.dls.web.exception;

public class BusinessExceptionConst {
    /**
     * 返回结果:成功
     */
    public static final int RESULT_FLAG_SUCCESS = 1;
    /**
     * 返回结果:系统异常
     */
    public static final String RESULT_FLAG_SYSTEM_ERROR = "2";
    /**
     * 返回结果:业务异常
     */
    public static final int RESULT_FLAG_BUSINESS_ERROR = 3;
    /**
     * 返回结果:会话超时
     */
    public static final int RESULT_FLAG_TIME_OUT = 4;
    public static final String E001 = "";
    public static final String E002 = "KEY：{0}，类名：{1}，方法名：{2}，系统异常，错误信息：{3}";
    public static final String E003 = "用户名或密码错误";
    public static final String E004 = "用户未登录，请先登录";
    public static final String E005 = "权限不足，不允许访问";

    public static final String INVALID_REQUEST_EXCEPTION ="4";
    public static final String INVALID_PAYLOAD_ENGINE_EXCEPTION= "13";

    /**
     * 返回结果:业务异常
     */
    public static final String COLLECTION_NOT_FOUND_EXCEPTION = "200";
    public static final String FIELD_NOT_FOUND_EXCEPTION = "202";
    public static final String ITEM_NOT_FOUND_EXCEPTION = "203";
    public static final String READING_ITEMS_DENIED_EXCEPTION = "300";
    public static final String CREATING_ITEMS_DENIED_EXCEPTION = "301";
    public static final String UPDATING_ITEMS_DENIED_EXCEPTION ="302" ;
    public static final String DELETING_ITEMS_DENIED_EXCEPTION = "303";
    public static final String COLLECTION_ALREADY_EXISTS_EXCEPTION = "307";
    public static final String ALTERING_COLLECTION_DENIED_EXCEPTION = "306";
    public static final String FIELD_ALREADY_EXISTS_EXCEPTION = "308";
    public static final String READING_FIELD_DENIED_EXCEPTION = "304";

    public static String CUSTOM_HTTPS_STATUS = "2";
}
