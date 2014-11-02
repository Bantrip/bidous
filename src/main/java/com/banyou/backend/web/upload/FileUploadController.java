package com.banyou.backend.web.upload;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.MediaTypes;

import com.banyou.backend.service.resource.ImageService;
import com.banyou.backend.web.AjaxResponse;
import com.sun.prism.Image;

@Controller
public class FileUploadController {
	@Autowired
	ImageService service;

//    @RequestMapping(value="/upload", method=RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//        return "You can upload a file by posting to this same URL.";
//    }
	
	
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
//    @RequestMapping(value="/download", method=RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//        return "You can upload a file by posting to this same URL.";
//    }
    
    
    @RequestMapping(value="/upload", method=RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public @ResponseBody AjaxResponse<String> handleFileUpload(@RequestParam("Filename") String name,
            @RequestParam("Filedata") MultipartFile file){
        if (!file.isEmpty()) {
            try {
            	String url=service.saveResouce(name,file.getInputStream());
                AjaxResponse<String> uploadResponse=new AjaxResponse<String>();
                uploadResponse.setResult(url);
                return uploadResponse;
            } catch (Exception e) {
            	  AjaxResponse<String> uploadResponse=new AjaxResponse<String>();
            	  uploadResponse.setCode(AjaxResponse.ERROR);
            	  uploadResponse.setMessage(e.getMessage());
                return uploadResponse;
            }
        } else {
        	 AjaxResponse<String> uploadResponse=new AjaxResponse<String>();
       	  uploadResponse.setCode(AjaxResponse.ERROR);
       	  uploadResponse.setMessage("You failed to upload " + name + " because the file was empty.");
           return uploadResponse;
        }
    }

}