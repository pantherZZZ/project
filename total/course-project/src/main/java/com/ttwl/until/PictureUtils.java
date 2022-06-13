package com.ttwl.until;

import com.ttwl.pojo.Picture;
import com.ttwl.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author zhang bao
 * @Date 2022/5/26 11:24
 * @Description：
 * @Version 1.0
 */
@Slf4j
@Component
public class PictureUtils {

    @Autowired
    private PictureService pictureService;

    private static PictureUtils pictureUtils;

    //初始化
    @PostConstruct
    public void init(){
        pictureUtils = this;
        pictureUtils.pictureService = this.pictureService;
    }

    public Integer pictureUpload(MultipartFile file) {
        if (file == null) log.error("此文件为空:{}", file);
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
            log.error("图片：{}存储失败",dest);
            e.printStackTrace();
        }
        Picture picture = new Picture();
        picture.setPictureName(fileName);
        picture.setPicturePath(databasePath);
        pictureService.insertPicture(picture);
        return pictureService.getPictureByPath(databasePath);

//        Integer count;
//        String pictureId;
//        if (file != null) {
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            if (!suffixName.equals(".jpg") || !suffixName.equals(".png")) {
//                String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "").substring(6) + suffixName;
//                // 文件上传后的路径
//                String filePath = "/www/wwwroot/jgw.taiott.com/dist/static/jgwImg/";
//                //		    String filePath = "D:/path/";
//                String size = "http://jgw.taiott.com/static/jgwImg/" + newFileName;
//                File dest = new File(filePath + newFileName);
//                // 检测是否存在目录
//                if (!dest.getParentFile().exists()) {
//                    dest.getParentFile().mkdirs();
//                }
//                file.transferTo(dest);
//                SysPathlist pathlist = new SysPathlist();
//                pathlist.setPictureName(fileName);
//                pathlist.setPicturePath(size);
//                count = sysPathlistService.insertPath(pathlist);
//                pictureId = sysPathlistService.showPictureId(size);

    }


}
