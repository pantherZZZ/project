package com.th.system.tcp;

import com.th.system.po.SysEquipment;
import com.th.system.po.Threshold;
import com.th.system.service.SysDtuSetvice;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysInsertData;
import com.th.system.service.SysThresholdService;
import com.th.system.utils.BinaryUtil;
import com.th.system.utils.InterceptDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tcp通讯
 *
 * @Author zhang bao
 * @Date 2021/12/27 16:47
 * @Version 1.0
 */

public class TcpServer extends Thread {

    Socket clientSocket;

    private SysInsertData sysInsertData;

    private SysDtuSetvice sysDtuSetvice;

    private SysEquipmentService sysEquipmentService;

    public TcpServer(Socket clientSocket, SysInsertData sysInsertData,
                     SysDtuSetvice sysDtuSetvice, SysEquipmentService sysEquipmentService) {
        super();
        this.clientSocket = clientSocket;
        this.sysInsertData = sysInsertData;
        this.sysDtuSetvice = sysDtuSetvice;
        this.sysEquipmentService = sysEquipmentService;
    }

    @Override
    public void run() {
        try {
            this.clientSocket.setSoTimeout(5 * 60 * 1000);
            char[] a = new char[100];
            String str = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            in.read(a);
            str = new String(a);
            // 清除数组中多余的内容
            str = str.replace("\0", "");
            System.out.println("received message:" + str);
//            866193059798658   0A 03 10 01 40 00 CE 01 A9 00 2B 00 42 00 00 00 00 01 02 0D AA
            //气象箱
            if ("866193059798658".equals(str)) {
                //设备在线存入当前时间搓和状态
                SysEquipment sysDtu = sysDtuSetvice.findByNumber(str);
                if (sysDtu != null) {
                    sysEquipmentService.updateOnLine(sysDtu.getEquipmentId());
                }

                while (true) {
                    // 发送指令
                    byte[] s = BinaryUtil.hexStrToBinaryStr("0A0301F400080579");
                    System.out.println("发送的指令");
                    this.clientSocket.getOutputStream().write(s);
                    // 接收信息
                    byte[] rsByte = new byte[1024];
                    this.clientSocket.getInputStream().read(rsByte);
                    String recMsg = BinaryUtil.BinaryToHexString(rsByte);

                      //传感器数据传了更新时间 状态在线
                      //没传 对比当前时间>更新时间三分钟       存入时间 离线
                    if (sysDtu != null) {
                        sysEquipmentService.updateOnLine(sysDtu.getEquipmentId());
                    }


                    //清除空格
                    recMsg = recMsg.replace(" ", "");
                    System.out.println("传感器数据为：" + recMsg);
                    HashMap<String, String> map = InterceptDataUtil.InterceptData(recMsg);
                    sysInsertData.insertData(str, map);
                    sysInsertData.insertTimeData(str, map);
                    System.out.println(map);
                    Thread.sleep(1000 * 60);
                }
                //55 7A 10 00350028F4B802DB04C3F519051011214500E9FBDE04E93FAD3FADA6CCA6C501326D202020202020202020202020202059545A58303131358
                //应变计   866193059811592
                // 55 7A 10 00 35 00 28 F4 B8 02 DB 04 C3 F5 19 05 10 11 21 45    00 F0  FB E2  04 ED  3F C9       3F C9 A6 CC A6
                // C5 01 32 6D 20 20 20 20 20 20 20 20 20 20 20 20 20 20 59 54 5A 58 30 31 31 35 A1
            } else if ("866193059811592".equals(str)) {
                //设备在线存入当前时间搓和状态
                SysEquipment sysDtu = sysDtuSetvice.findByNumber(str);
                if (sysDtu != null) {
                    sysEquipmentService.updateOnLine(sysDtu.getEquipmentId());
                }

                while (true) {
                    // 发送指令   AA7510000EF500000000000000001905101121454D
                    byte[] s = BinaryUtil.hexStrToBinaryStr("AA7510000EF500000000000000001905101121454D");
                    System.out.println("发送的指令");
                    this.clientSocket.getOutputStream().write(s);
                    // 接收信息
                    byte[] rsByte = new byte[1024];
                    this.clientSocket.getInputStream().read(rsByte);
                    String recMsg = BinaryUtil.BinaryToHexString(rsByte);

                    // 传感器数据传了更新时间 状态在线
                    // 没传 对比当前时间>更新时间三分钟       存入时间 离线
                    if (sysDtu != null) {
                        sysEquipmentService.updateOnLine(sysDtu.getEquipmentId());
                    }

                    //清除空格
                    recMsg = recMsg.replace(" ", "");
                    System.out.println("传感器数据为：" + recMsg);
                    HashMap<String, String> map = InterceptDataUtil.InterceptDataTwo(recMsg);
                    sysInsertData.insertDataTwo(str, map);
                    sysInsertData.insertTimeDataTwo(str, map);
                    System.out.println(map);
                    Thread.sleep(1000 * 60);
                }
            }

        } catch (Exception e) {
            System.out.println("发生错误:" + e.getMessage());
            try {
                this.clientSocket.close();
            } catch (IOException e1) {
                System.out.println("Socket关闭发生错误:" + e1.getMessage());
            }
        }
    }
}

