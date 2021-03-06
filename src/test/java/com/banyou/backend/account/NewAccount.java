package com.banyou.backend.account;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.banyou.backend.service.account.AccountService;
import com.google.common.collect.Maps;

public class NewAccount {

	@InjectMocks
	private AccountService service=new AccountService();
	
	private String tpl="insert into ss_user ( login_name, name, password, salt, roles, create_time) values"
		+ "('{loginname}','{name}','{password}','{salt}','{roles}','{date}');";
	private class Template{
		public String doTemplate(Map param){
			boolean in=true;
			StringTokenizer tk=new StringTokenizer(tpl);
			String start=tk.nextToken("{");
			StringBuilder sb=new StringBuilder();
			sb.append(start);
			while(tk.hasMoreElements()){
				String token=in?"}":"{";
				String str=tk.nextToken(token);
				str=str.substring(token.length());
				if(in){
					sb.append(param.get(str));
				}else{
				sb.append(str);}
				
				in=!in;
			}
			return sb.toString();
		}
	}
	



	@Test
	public void buildTest(){
		String loginName="iverzhang@163.com";
		String name="睿";
		String password="123456";
		String role="admin,user";
		Map<String,String>map=Maps.newHashMap();
		map.put("loginname", loginName);
		map.put("name", name);
		String[] passwordInfos=service.entryptPassword(password).split("/");
		map.put("password", passwordInfos[1]);
		map.put("salt", passwordInfos[0]);
		map.put("roles", role );
		map.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(new Template().doTemplate(map));
	
				//2012-06-04 01:00:00
		
//	Proxy.newProxyInstance(loader, interfaces, h);
//		service.setUserDao(
//		});
		//User user=new User();
		//user.setLoginName("zhuyongliang");
	//	user.setPassword("asdf");
		String[] infos=service.entryptPassword("asdf").split("/");
		System.out.println(Arrays.toString(infos));
	} 
	
}
