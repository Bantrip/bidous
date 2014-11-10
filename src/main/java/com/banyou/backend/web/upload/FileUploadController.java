package com.banyou.backend.web.upload;

import com.banyou.backend.service.resource.ImageService;
import com.banyou.backend.web.JsonView.AjaxResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.MediaTypes;

@Controller
public class FileUploadController {
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	ImageService service;

//    @RequestMapping(value="/upload", method=RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//        return "You can upload a file by posting to this same URL.";
//    }
	
	@RequestMapping(value = "/dowload/{path}", method = RequestMethod.GET)
	public String download(@PathVariable("path") String path, Model model) {
		
		log.info(path);
		return "product/edit";
	}
	
	
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