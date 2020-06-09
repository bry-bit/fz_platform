package com.system.util;

public class JsonResult {

    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    /**
     * 状态
     */
    private int state;
    /**
     * 对应状态的消息
     */
    private String msg;
    /**
     * 具体业务数据
     */
    private Object data;

    /**
     * 此构造方法应用于data为null的场景
     */
    public JsonResult() {
        this.state = SUCCESS;
        this.msg = "请求成功";
    }

    /**
     * 有业务返回时调用此方法
     */
    public JsonResult(Object data) {
        this();
        this.data = data;
    }

    /**
     * 出现异常以后ControllerExceptionHandler调用此方法封装异常信息
     */
    public JsonResult(ExceptionUtil e) {

        this.state = ERROR;
        this.msg = e.getMessage();
    }

    public int getState() {
        return state;
    }


    public String getMessage() {
        return msg;
    }


    public Object getData() {
        return data;
    }


	/*public static String toJSONString(String callback,JsonUtils jsonUtils) {
        String retStr = callback + "(" + JSON.toJSONString(jsonUtils) + ")";
		return retStr;
	}*/
	/*public static String toJSONString(String callback,Map<String,Object> result) {
        JSONObject json=new JSONObject(result); 
        json.toJSONString();
        String retStr = callback + "(" + JSON.toJSONString(result) + ")";
		return retStr;
	}*/
}
