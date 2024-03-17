package org.rahi.aseet.services;

import org.imgscalr.Scalr;
import org.rahi.aseet.Entities.ProductEntity;
import org.rahi.aseet.Entities.ProductImagesEntity;
import org.rahi.aseet.Exception.FileStorageException;
import org.rahi.aseet.payload.request.UploadImageRequest;
import org.rahi.aseet.repositories.ProductImageRepository;
import org.rahi.aseet.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public List<ProductImagesEntity> addImage(UploadImageRequest uploadImageRequest, UUID productId){
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(productEntity.isPresent()){
            List<ProductImagesEntity> productImagesEntityList = new ArrayList<>(Collections.emptyList());

           for (MultipartFile file: uploadImageRequest.getImages()){

               String filename = uploadFile(file);


               ProductImagesEntity productImagesEntity = new ProductImagesEntity();
               productImagesEntity.setProduct(productEntity.get());
               productImagesEntity.setImageName(filename);
               productImagesEntity.setExtension(file.getContentType());
               productImagesEntity.setSize(file.getSize());
               productImagesEntityList.add(productImagesEntity);
           }

            return productImageRepository.saveAll(productImagesEntityList);
        }

        return null;
    }


    private String uploadFile(MultipartFile file)
    {
        try {

            String filetype = Objects.requireNonNull(file.getContentType()).split("/")[1];
            String filename = UUID.randomUUID()+"_%s."+filetype;
            Path copyLocation = Paths.get(uploadDir + File.separator + String.format(filename, "original"));
            Files.copy(file.getInputStream(),copyLocation, StandardCopyOption.REPLACE_EXISTING);

            createThumbnail(file, 150, filename);
            createThumbnail(file, 100, filename);

            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("File Not Found");
        }
    }

    private void createThumbnail(MultipartFile orginalFile,
                                               Integer width, String filename) throws IOException{
        BufferedImage img = ImageIO.read(orginalFile.getInputStream());
        BufferedImage thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);

        String thumbLocation = uploadDir + File.separator+  "thumbs"+ File.separator+ String.format(filename, "thumb_"+width);

        try(OutputStream outputStream = new FileOutputStream(new File(thumbLocation))) {
            ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1] , outputStream);
        }
    }

}
