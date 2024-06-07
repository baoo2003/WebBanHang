package shop.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	@Autowired
	ServletContext context;
	
	public String upload(MultipartFile image) throws Exception {
		try {
			image.transferTo(new File(context.getRealPath("/img/" + image.getOriginalFilename())));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Upload image failed");
		}
		String imagePath = "img/" + image.getOriginalFilename();
		return imagePath;
	}
}
