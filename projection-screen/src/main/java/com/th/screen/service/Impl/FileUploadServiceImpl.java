package com.th.screen.service.Impl;

import com.th.screen.entity.ProgramMediaLibrary;
import com.th.screen.mapper.FileUpLoadMapper;
import com.th.screen.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author zhang bao
 * @Date 2022/3/15 13:46
 * @Description
 * @Version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUpLoadMapper fileUploadMapper;


    @Override
    public ProgramMediaLibrary findAllUrlById(int id) {
        return  fileUploadMapper.selectById(id);
    }
}
