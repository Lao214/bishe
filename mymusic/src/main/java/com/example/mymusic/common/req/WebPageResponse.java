package com.example.mymusic.common.req;

/**
 * @Author 劳威锟
 * @Description //方法说明： 前端返回类
 * @Date 18:18 2022/1/7
 * @Param 
 * @return 
 **/
public class WebPageResponse<T> {

	/**
	 * 状态码
	 */
	private int status;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 其实条数
	 */
	private Integer offset=0;

	/**
	 * 总数
	 */
	private Long total;

	/**
	 * 限制条数
	 */
	private Integer limit=10;

	/**
	 * 结果
	 */
	private T data;

	public WebPageResponse(int string, String msg, T data) {
		this(string, msg);
		this.setData(data);
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public static <T> WebPageResponse<T> success(T data) {
		return new WebPageResponse<>(1, null, data);
	}

	public static <T> WebPageResponse<T> fail(String msg) {
		return new WebPageResponse<>(0, msg, null);
	}

	public WebPageResponse() {
		super();
	}

	public WebPageResponse(int status) {
		this.status = status;
	}

	public WebPageResponse(int status, String msg) {
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
