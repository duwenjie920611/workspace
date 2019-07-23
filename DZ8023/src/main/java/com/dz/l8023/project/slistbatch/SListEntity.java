package com.dz.l8023.project.slistbatch;

import java.util.List;

public class SListEntity
{
  public List<? extends SBaseEntity> data;

  public List<? extends SBaseEntity> getData()
  {
    return this.data;
  }

  public void setData(List<? extends SBaseEntity> data) {
    this.data = data;
  }
}