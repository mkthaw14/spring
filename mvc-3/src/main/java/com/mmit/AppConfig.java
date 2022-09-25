package com.mmit;

import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer
{
	//This method is for showing images in view page
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		String dirName = "upload-photos";
		Path dirPath = Path.of(dirName);
		
		String uploadPath = dirPath.toFile().getAbsolutePath(); // E:\Z2prolevel-2\Spring\WorkSpace..upload-photos\
		
		// /upload-photos/** (**) mean find all under given folder
		registry.addResourceHandler("/" + dirName + "/**")
				.addResourceLocations("file:///" + uploadPath + "/");
		
	}
}
