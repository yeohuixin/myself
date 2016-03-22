package controller;

public class BaseResult {
	private int code;		//请求结果编码
	private String msg;		//请求结果文字说明
	
	public BaseResult() {
	}
	
	public BaseResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
