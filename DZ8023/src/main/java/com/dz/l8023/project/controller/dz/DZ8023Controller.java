package com.dz.l8023.project.controller.dz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/dz" }, produces = { "text/plain;charset=utf-8" })
public class DZ8023Controller {
	private static final String userCollectionName = "user";

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping({ "/{userID}/get.do" })
	@ResponseBody
	public String pathVariable(@PathVariable("userID") String userID) {
		Map<String, Object> result = Maps.newHashMap();

		result.put("requestParamter", userID);

		return JSONObject.fromObject(result).toString();
	}

	@RequestMapping("/mogo.do")
  @ResponseBody
  public String mogo(){
		
//		JSONObject json = new JSONObject();
//		json.put("name", "3026890");
//		json.put("age", "3026890");
//		mongoTemplate.insert(json, "person");
//	  List<JSONObject> list = Lists.newArrayList();
//	  JSONObject json ;
//	  for(int i=2;i<10;i++){
//		  json = new JSONObject();
//			json.put("uid", i);
//			json.put("age", 12);
//			json.put("status", 0);
//		  list.add(json);
//	  }
//	  mongoTemplate.insert(list, "user");
	  
	  Query query = new Query();
	  query.addCriteria(Criteria.where("uid").is(1));
	  List<JSONObject> list = mongoTemplate.find(query,JSONObject.class,"user");
	  if(list != null && !list.isEmpty()){
		  String str = list.get(0).toString();
		  System.out.println(str);
		  LogFactory.getLog(this.getClass()).debug(str);
	  }
	  
	  //分页
	  Query qy = new Query();
	  qy.limit(5);
	  qy.skip(3);
	  List<JSONObject> jsons = mongoTemplate.find(qy, JSONObject.class, userCollectionName);
	  if(jsons != null && !jsons.isEmpty()){
		  for(JSONObject js : jsons){
			  System.out.println(js.toString());
			  LogFactory.getLog(this.getClass()).debug(js.toString());
		  }
	  }
	  
	  long userCount = mongoTemplate.count(query, userCollectionName);
	  System.out.println(userCount);
	  
	  Update up = new Update();
	  up.set("status", 2);
	  JSONObject jup = mongoTemplate.findAndModify(
			  query, up, 
			  new FindAndModifyOptions().returnNew(true),
			  JSONObject.class,
			  userCollectionName);
	  
	  System.out.println(jup.toString());
	  
	  mongoTemplate.remove(query,JSONObject.class,userCollectionName);
	  
//	  mongoTemplate.m
	  
	  Query q = new Query();
	  Criteria criteria = new Criteria();
	  
	  return "";
  }
	
		
	@RequestMapping("/add.do")
	@ResponseBody
	public String addUser(HttpServletRequest request){
		
		final String city []= {"anhui","chongqin","hainan","jianpuzai","laowo","yingguo","shijiazhuang","haikou","hebei","beijing","tianjing","zhangjiajie",
				"jiuzaigou","shaoxing","ningbo","taiwan","qinghai","qingdao","nanjing","hefei","bengbu","yunnan","mohe","heilongjiang","liaoning",
				"daxing","shunyi","jiuxianlou","hangzhou"};
		final int citylen = city.length;
		
		final String tags[] = {"mysql","redis","mongodb","memchae","spark","ajax","js","jquery","c","c++","linux c","linux c++"
				,"android","c#","object-c","swift","python","shell","xml","json","bson","transtion","isolaction","acid",
				"mvc","iop","doc","http","https","ws","webservice"};
		final int tagslen = tags.length;
		
		final String name[] = {"apple","banana","orangle","lion","fly","yellow","pig","cat","dog","spring","summer","july","june","jack","jane","cms","shit","wick",
				"phone","mobile","cup","hand","room","head","cmd","ziru","lianjie","ali","baidu","computer","world","hello","black","pink","white","red","green","grew","sharp","clever","pen","pencile","channel",
				"car","finger","flower","check","sofa","chair","cheeses","chine","japan","idea","one","two","three","four"};
		final int namelen = name.length;
		
		final Random ran = new Random();
		
		final int begin = 950000;
		new Thread(){
			public void run(){
				List<JSONObject> list = Lists.newArrayList();
			for(int i = begin;i<1050000;i++){
				if(i%1000 ==0){
					mongoTemplate.insert(list, userCollectionName);
					list = null;
					list = Lists.newArrayList();
				}
				JSONObject json = new JSONObject();
				json.put("uid", i);
				json.put("name", name[ran.nextInt(namelen)]);

				JSONObject js = new JSONObject();
				js.put("city", city[ran.nextInt(citylen)]);
				js.put("state", city[ran.nextInt(citylen)]);
				js.put("pincode", ran.nextInt(100000));
				json.put("address", js);
				
				JSONArray ja = new JSONArray();
				ja.add(tags[ran.nextInt(tagslen)]);
				ja.add(tags[ran.nextInt(tagslen)]);
				json.put("tags", ja);
				
				list.add(json);
			}
			mongoTemplate.insert(list, userCollectionName);
		}}.start();

		
		return "hello world";
	}
	
	
	

}