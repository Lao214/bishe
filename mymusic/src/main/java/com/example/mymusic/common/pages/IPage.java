package com.example.mymusic.common.pages;

import java.io.Serializable;
import java.util.Collection;

public abstract interface IPage<T> extends Serializable
{
  public abstract int getStart();

  public abstract int getLimit();

  public abstract long getTotal();

  public abstract Collection<T> getRows();

  public abstract Object getOtherData();
}
