package com.banyou.backend.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.banyou.backend.service.resource.ImageService;

public class DownloadServlet extends HttpServlet {
	@Autowired
	private ImageService service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		this.service = wac.getBean(ImageService.class);
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// req.
		String url = req.getRequestURL().toString();
		req.getRequestURL();
		InputStream stream = null;
		try {
			stream = service.getResourceStreamByUrl(url);
			IOUtils.copy(stream, resp.getOutputStream());
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

}
