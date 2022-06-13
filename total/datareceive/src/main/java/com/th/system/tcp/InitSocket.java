package com.th.system.tcp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.th.system.service.SysDtuSetvice;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysInsertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 项目启动时加载此类
 * 连接多个客户端
 *
 * @Author zhang bao
 * @Date 2021/12/28 15:37
 * @Version 1.0
 */
@Component
public class InitSocket implements ApplicationRunner {
    @Autowired
    private SysInsertData sysInsertData;

    @Autowired
    private SysDtuSetvice sysDtuSetvice;

    @Autowired
    private SysEquipmentService sysEquipmentService;

    private static final int PORT = 19501;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init() throws IOException {
        System.out.println("服务已启动， 等待连接！");


//        File[] urlArray = fileUrlList.toArray(new File[0]);
//        for (int i = 0; i < urlArray.length; ++i) {
//            File contents = urlArray[i];
//            System.out.print(contents);
//        }
//        Map<String, File[]> data = new HashMap<>();
//        List<String> fileUrlList = new ArrayList<>();
//        fileUrlList.add("https://light.taiott.com/public/file/9179b7f6-ca49-4011-89b4-6f06aac8e7c6微信图片_20211018135838.jpg");
//        fileUrlList.add("https://light.taiott.com/public/file/6578e048-5cdd-424a-9a92-cc4392cbce30_20211017103522.jpg");
//        String[] array = fileUrlList.toArray(new String[0]);
//        File[] files = new File[fileUrlList.size()];
//        for (int i = 0; i < array.length; i++) {
//            files[i] = new File(array[i]);
//        }
//        data.put("0", files);
//        for (Map.Entry<String, File[]> entry : data.entrySet()) {
//            System.out.println("key为：" + entry.getKey() + "value为：" + entry.getValue());
//        }

//        //                String[] array = fileUrlList.toArray(new String[0]);
//        List<String> list = new ArrayList<>();
//        list.add("C://Users//THWL//Downloads//17ad5ecf-75ee-4df0-97cc-1b32fd5c5352486076264726644874.jpg");
////                list.add("C://Users//THWL//Downloads//f5688880-0488-49b0-9be6-f7a0a0ea73f3filelvyou.mp4");

        //建立TCP连接服务,绑定端口
        ServerSocket tcpServer = new ServerSocket(PORT);

        //接受连接,每个TCP连接都是一个java线程
        while (true) {
            Socket clientSocket = tcpServer.accept();
            new TcpServer(clientSocket, sysInsertData, sysDtuSetvice, sysEquipmentService).start();
        }
    }
}

//                List<String> list = new ArrayList<>();
//                list.add("C://Users//THWL//Downloads//17ad5ecf-75ee-4df0-97cc-1b32fd5c5352486076264726644874.jpg");
//                list.add("C://Users//THWL//Downloads//1918f7d8-a1ba-4a08-81a7-deb6db36d811_20211018135838.jpg");
//                list.add("C://Users//THWL//Downloads//549f6756-4f32-4a0e-8585-30643e49f11891fb77655b86b607568c01b482bca657.mp4");
//                list.add("C://Users//THWL//Downloads//f5688880-0488-49b0-9be6-f7a0a0ea73f3filelvyou.mp4");

