package com.ttwl.controller;

import com.ttwl.pojo.Picture;
import com.ttwl.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author zhang bao
 * @Date 2022/5/26 15:39
 * @Description：
 * @Version 1.0
 */
@RestController
@RequestMapping("")
@Slf4j
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @PostMapping("")
    public Integer pictureUpload(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            log.error("此文件为空:{}", file);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (!suffixName.equals(".jpg") || !suffixName.equals(".png") || !suffixName.equals(".webp")) {
            log.error("此图片格式为：{}", suffixName);
            //TODO 返回信息
        }
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + UUID.randomUUID().toString().replace("-", "")
                .substring(6) + suffixName;
        // 文件上传后的路径
        String serverPath = "/www/wwwroot/jgw.taiott.com/dist/static/jgwImg/";
        String databasePath = "http://jgw.taiott.com/static/jgwImg/" + newFileName;
        File dest = new File(serverPath + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("图片：{}上传服务器失败 错误信息为:{}", dest,e.getMessage());
            e.printStackTrace();
        }
        Picture picture = new Picture();
        picture.setPictureName(fileName);
        picture.setPicturePath(databasePath);
        pictureService.insertPicture(picture);
        return pictureService.getPictureByPath(databasePath);
    }
}
