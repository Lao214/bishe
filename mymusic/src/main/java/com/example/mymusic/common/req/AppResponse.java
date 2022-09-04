package com.example.mymusic.common.req;

/**
 * 
 * 类说明：app返回类
 * @author 聂枫
 * 2015年12月21日上午10:34:34
 * @param <T>
 */
public class AppResponse<T> {

	/**
	 * 状态码
	 */
	private int status;
	
	/**
	 * 消息
	 */
	private String msg;
	
	/**
	 * 结果
	 */
	private T data;

	public AppResponse(int string, String msg, T data) {
		this(string, msg);
		this.setData(data);
	}

	public static <T> AppResponse<T> success(T data) {
		return new AppResponse<>(1, null, data);
	}
	
	public static <T> AppResponse<T> fail(String msg) {
		return new AppResponse<>(0, msg, null);
	}
	
	public AppResponse() {
		super();
	}
	
	public AppResponse(int status) {
		this.status = status;
	}
	
	public AppResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppResponse [status=");
		builder.append(status);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
}
