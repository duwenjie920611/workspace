package com.dz.l8023.project.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BaseMongoDaoImp implements BaseMongoDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return this.mongoTemplate;
	}

}
