package com.banyou.backend.service.ad;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyou.backend.entity.AdPosition;
import com.banyou.backend.repository.AdPositionDao;

@Component
public class AdService {
private AdPositionDao adPositionDao;

public AdPosition getAdPositionByCode(String code){
	if(StringUtils.isBlank(code)){
		return AdPosition.EMPTY_POSITION;
	}
	AdPosition position=adPositionDao.findByCode(code);
	if(position==null){
		position=AdPosition.EMPTY_POSITION;
	}
	position.getContent();
	return position;
	
}

@Autowired
public void setAdPositionDao(AdPositionDao adPositionDao) {
	this.adPositionDao = adPositionDao;
}

}
