/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.service.resource;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.banyou.backend.service.ServiceException;


/**
 * 资源管理
 * 
 * @author calvin
 */
@Component
public class ImageService {
	//url 规则 urlBase+文件URI
	//path 规则 pathBase+文件URI
	//映射关系 url==>path   url去掉urlBase替换为pathBase
	private String separator= File.separator;
	private String urlBase="http://127.0.0.1:8081/bidoushi/download";
	private String pathBase=System.getProperty("user.home")+separator+"temp"+separator+"upload";
	
	
	@PostConstruct
	public void init(){
		
	}
	
	public InputStream getResourceStreamByUrl(String url){
		int index=url.indexOf(urlBase);
		Assert.isTrue(index==0,"not my resource");
		String path=pathBase+url.substring(urlBase.length());
		
		try {
			return new FileInputStream(path);
		} catch (FileNotFoundException e) {
			 return null;
		}
	}
	/**
	 * 
	 * @param file
	 * @return url
	 */
	public String saveResouce(String fileName,InputStream is){
		
		//OutputStream out=null;
		try{
			String path=createRealPath(fileName);
			File outFile=new File(pathBase+path);
			while(outFile.exists()){
				path=createRealPath(fileName);
				outFile=new File(pathBase+path);
			}
			//if(!outFile.getParentFile().exists()){
				//outFile.mkdirs();
			//}
			//outFile.createNewFile();
			
			//out= new FileOutputStream(outFile);
			FileUtils.copyInputStreamToFile(is, outFile);
			//写文件
			//IOUtils.copyLarge(is, out);

        return urlBase+path;
		} catch (IOException e) {
			throw new ServiceException(e);
			
		}finally{
			IOUtils.closeQuietly(is);
			//IOUtils.closeQuietly(out);
		}
	}
	
	private String createRealPath(String fileName){
		Calendar now=Calendar.getInstance();
		String year=String.valueOf(now.get(Calendar.YEAR));
		String month=String.valueOf(now.get(Calendar.MONTH));
		String day=String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		
		String pre=UUID.randomUUID().toString();
		return separator+year+separator+month+separator+day+separator+pre+"."+fileName;
		
	}
	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}
	public void setPathBase(String pathBase) {
		this.pathBase = pathBase;
	}
	public String getUrlBase() {
		return urlBase;
	}
	public String getPathBase() {
		return pathBase;
	}


}
