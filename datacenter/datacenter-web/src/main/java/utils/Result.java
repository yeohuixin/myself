package utils;

import java.util.Date;

public class Result<T> {
	private int code;		//请求结果编码
	private String msg;		//请求结果文字说明
	private Date timestamp;	//服务器时间戳
	T data;					//请求结果数据
	
	public Result() {
		
	}
	
	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg, Date timestamp) {
		this.code = code;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.timestamp = new Date();
	}

	public Result(int code, String msg, T data, Date timestamp) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.timestamp = timestamp;
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
