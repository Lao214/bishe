package com.example.mymusic.common;

public class FdServiceException extends FdException
{
  private static final long serialVersionUID = 7498517390326327837L;

  public FdServiceException(String exceptionCode, String exceptionInfo)
  {
    super(exceptionCode, exceptionInfo);
  }
  public FdServiceException(String exceptionCode, String exceptionInfo, Throwable throwable) {
    super(exceptionCode, exceptionInfo, throwable);
  }
}
