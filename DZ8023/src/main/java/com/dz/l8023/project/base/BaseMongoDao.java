package com.dz.l8023.project.base;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface BaseMongoDao {
	
	public MongoTemplate getMongoTemplate();

}
