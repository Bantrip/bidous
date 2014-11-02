package com.banyou.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.banyou.backend.service.resource.ImageService;

public class DownloadServlet extends HttpServlet{
	@Autowired
	private ImageService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//req.
		String url=req.getRequestURL().toString();
		req.getRequestURL();
	}

}
