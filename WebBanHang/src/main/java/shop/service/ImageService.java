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
			String imageName = generateImageName(image);
			image.transferTo(new File(context.getRealPath("/img/" + imageName)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Upload image failed");
		}
		String imagePath = "img/" + image.getOriginalFilename();
		return imagePath;
	}
	
	public String replace(String oldImagePath, MultipartFile newImage) throws Exception {
		try {
            String oldImageFullPath = context.getRealPath("/" + oldImagePath);

            File oldImageFile = new File(oldImageFullPath);
            if (oldImageFile.exists()) {
                oldImageFile.delete();
            } else {
                throw new Exception("Old image not found!");
            }

			String newImageName = generateImageName(newImage);
            File newImageFile = new File(context.getRealPath("/img/" + newImageName));
            newImage.transferTo(newImageFile);

            return "img/" + newImageName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Replace image failed");
        }
	}
	
	private String generateImageName(MultipartFile image) {
		long currentTimestamp = System.currentTimeMillis();
		String originalFileName = image.getOriginalFilename();
		String imageName = currentTimestamp + "_" + originalFileName;
		return imageName;
	}
}
