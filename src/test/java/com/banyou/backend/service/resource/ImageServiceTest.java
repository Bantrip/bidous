package com.banyou.backend.service.resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.stream.ImageInputStreamImpl;
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ImageServiceTest extends SpringTransactionalTestCase {
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	ImageService service;
	@Test
	public void testUpload(){
		URL uploadUrl;
		try {
			uploadUrl = ResourceUtils.getURL("classpath:草榴.gif");
			String url=service.saveResouce("caoliu.gif",uploadUrl.openStream());
			log.info("upload url is {}",url);
		
			service.getResourceStreamByUrl(url);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

