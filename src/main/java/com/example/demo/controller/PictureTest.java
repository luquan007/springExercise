package com.example.demo.controller;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.heif.HeifMetadataReader;
import com.drew.imaging.heif.HeifReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.mov.QuickTimeDescriptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class PictureTest {

    public static void main(String[] args) throws FileNotFoundException {
        readPic();
    }

    //读取图片的信息
    public static void readPic() throws FileNotFoundException {
        System.out.println("开始读取图片信息...");
        File jpegFile = new File("E:/Desktop/实况照片/IMG_3420.HEIC");
        FileInputStream fileInputStream = new FileInputStream(jpegFile);
        Metadata metadata;
        try {
            //metadata = JpegMetadataReader.readMetadata(jpegFile);
            metadata = HeifMetadataReader.readMetadata(fileInputStream);
            //metadata = Mp4MetadataReader.readMetadata(jpegFile);

            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = (Tag) tags.next();
                    System.out.println(tag);
                }
            }
            System.out.println("图片信息读取完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}