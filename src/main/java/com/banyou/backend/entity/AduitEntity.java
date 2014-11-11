package com.banyou.backend.entity;

import java.util.Date;

public interface AduitEntity {
	void setCreateTime(Date time);

	void setCreater(Long id);

	Long getCreater();

	Date getCreateTime();

}
