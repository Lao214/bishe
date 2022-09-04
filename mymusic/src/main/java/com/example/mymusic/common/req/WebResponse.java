package com.example.mymusic.common.req;

/**
 * @Author 劳威锟
 * @Description //方法说明： 前端返回类
 * @Date 18:18 2022/1/7
 * @Param 
 * @return 
 **/
public class WebResponse<T> {

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

	public WebResponse(int string, String msg, T data) {
		this(string, msg);
		this.setData(data);
	}


	public static <T> WebResponse<T> success(T data) {
		return new WebResponse<>(1, null, data);
	}
	
	public static <T> WebResponse<T> fail(String msg) {
		return new WebResponse<>(0, msg, null);
	}
	
	public WebResponse() {
		super();
	}
	
	public WebResponse(int status) {
		this.status = status;
	}
	
	public WebResponse(int status, String msg) {
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
