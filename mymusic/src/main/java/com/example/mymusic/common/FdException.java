package com.example.mymusic.common;

public abstract class FdException extends RuntimeException {
	private static final long serialVersionUID = -3664225596755566456L;
	private String exceptionCode;
	private String exceptionInfo;

	public FdException(String exceptionCode) {
		super(exceptionCode);
		setExceptionCode(exceptionCode);
	}

	public FdException(String exceptionCode, String exceptionInfo) {
		super(exceptionInfo);
		setExceptionCode(exceptionCode);
		setExceptionInfo(exceptionInfo);
	}

	public FdException(String exceptionCode, Throwable throwable) {
		super(exceptionCode, throwable);
		setExceptionCode(exceptionCode);
	}

	public FdException(String exceptionCode, String exceptionInfo,
                       Throwable throwable) {
		super(exceptionInfo, throwable);
		setExceptionCode(exceptionCode);
		setExceptionInfo(exceptionInfo);
	}

	public String getExceptionCode() {
		return this.exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionInfo() {
		return this.exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
}
