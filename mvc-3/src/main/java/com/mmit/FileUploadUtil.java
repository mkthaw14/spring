package com.mmit;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil 
{

	public static void savePhoto(String uploadDir, String fileName, MultipartFile file) 
	{
		try
		{
			Path path = Path.of(uploadDir); // upload-photos/1
			
			if(! Files.exists(path)) // check if the directory does not exist
				Files.createDirectories(path);
			
			InputStream input = file.getInputStream();
			
			Path uploadFile = path.resolve(fileName); // upload-photos/1/image.png
			
			Files.copy(input, uploadFile, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
