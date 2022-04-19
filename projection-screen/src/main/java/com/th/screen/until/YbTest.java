package com.th.screen.until;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hidisp.api.cloud.NoticeManager;
import com.hidisp.api.cloud.ProgramManager;
import com.hidisp.api.cloud.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhang bao
 * @Date 2022/4/12 10:19
 * @Description
 * @Version 1.0
 */
@Component
public class YbTest {
    @Value("${screen.apiUrl}")
    private String apiUrl;

    @Value("${screen.appKey}")
    private String appKey;

    //LiveFile 直接使用 网络直接视频的URL地址文件类。（目前仅Y系列和S6播放器支持Url直播）


    public  void webCast() {

        // 逗号分割的屏幕条码列表
        String screens = "C0XXXXXXXXXXXXXX";
        //选择需要上传的素材
        // Map data 要发送的数据
        // key:接口分区编码，如果keys是“0” 代表是默认接口分区， 其他值 可以从iLEDCloud多媒体信息发布平台-> 高级功能 —> 二次开发——> 接口分区管理下获取
        // value：要放到接口分区的文件列表 ,
        Map data = new HashMap<>();
        //data.put("0",null);// 如果value=null     代表使用接口分区的默认素材，
        //data.put("0", new File[0]);// value=new File[0] (空数组),代表清理接口分区素材


        //要发送的播放列表编码，可选，如果为空，则表示接口分区必须在同一个接口节目中，如果不为空，则接口分区，必须在播放列表包含的接口节目中
        //获取途径：您可以进入 节目管理-播放列表-节目清单按钮后弹出的弹窗标题处的后缀看到
        String playlist = null;

        //SendTask task 发送回调控制对象，调用程序可以在另外一个线程，读取发送过程和控制取消发送等
        SendTask task = new SendTask();

        try {

            ISendMode mode = null;
            ExecuteMode exm = ExecuteMode.Sync;
            OnlineMode lineMode = OnlineMode.Offline;
            mode = new DefaultSendMode(exm, null, lineMode, 2);

            LiveFile liveFile = new LiveFile("16.url", "https://haokan.baidu.com/v?vid=13133660391077167035&tab=dongman");
            data.put("0", new File[]{liveFile});

            SendResult sendResult = ProgramManager.getInstance().sendProgram(apiUrl, appKey, screens, data, task, playlist, mode);

            //任务进行状态 0：发送成功，1：发送失败
            int result = sendResult.getResult();

            if (result == 0) {
                //发送成功
                //发送结果消息
                String message = sendResult.getMessage();
                //各个屏幕发送状态列表，与sendProgram传入的screens参数数量相同
                Map rData = sendResult.getData();
                System.out.println("发送结果消息（里面注意查看是否有无效的屏幕条码）:" + message);
                //发送成功各个屏幕发送状态列表
                if (rData != null) {
                    for (Map.Entry entry : sendResult.getData().entrySet()) {
                        //屏幕编码 = 结果,消息
//                        System.out.println(entry.getKey() + "=" + entry.getValue().isSuccess() + "," + entry.getValue().getMessage());
                    }
                }
            } else if (result == 1) {
                //1：发送失败

                //发送结果消息
                String message = sendResult.getMessage();
                //各个屏幕发送状态列表，与sendProgram传入的screens参数数量相同
                Map rData = sendResult.getData();
                System.out.println("发送结果消息的错误原因:" + message);
                //发送失败后各个屏幕发送状态列表
                if (rData != null) {
                    for (Map.Entry entry : sendResult.getData().entrySet()) {
                        //屏幕编码 = 结果,消息
//                        System.out.println(entry.getKey() + "=" + entry.getValue().isSuccess() + "," + entry.getValue().getMessage());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //紧急插播
    public  void emergencyBroadcast() {

        // 逗号分割的屏幕条码列表
        String screens = "C0XXXXXXXXXXXXXX";

        //选择需要上传的素材
        // Map data 要发送的数据
        // key:接口分区编码，如果keys是“0” 代表是默认接口分区， 其他值 可以从iLEDCloud多媒体信息发布平台-> 高级功能 —> 二次开发——> 接口分区管理下获取
        // value：要放到接口分区的文件列表 ,
        Map data = new HashMap<>();
        //data.put("0",null);// 如果value=null     代表使用接口分区的默认素材，
        //data.put("0", new File[0]);// value=new File[0] (空数组),代表清理接口分区素材


        //要发送的播放列表编码，可选，如果为空，则表示接口分区必须在同一个接口节目中，如果不为空，则接口分区，必须在播放列表包含的接口节目中
        //获取途径：您可以进入 节目管理-播放列表-节目清单按钮后弹出的弹窗标题处的后缀看到
        String playlist = null;

        //SendTask task 发送回调控制对象，调用程序可以在另外一个线程，读取发送过程和控制取消发送等
        SendTask task = new SendTask();

        try {
            //同步Sync 异步Async
            ExecuteMode exm = ExecuteMode.Async;//ExecuteMode.Async;
            if (ExecuteMode.Async == exm) {
                Boolean addFg = NoticeManager.getInstance().addServer(apiUrl, appKey);
                if (addFg) {
//                    NoticeManager.getInstance().addListener(new FileCommandListener());
                }
            }


            //发送模式：sendMode=Normal|Emergent Normal，普通发送模式（覆盖节目）；Emergent，紧急插播模式，按指定的方式紧急插播，播放完后继续播放原来的节目
            String sendMode = "Normal";
            // 紧急插播方式：emergentPlayMode=Times|Duration 紧急插播方式：Times，定次数播放，Duration定时长播放
            String emergentPlayMode = "Times";
            //紧急插播次数或者时长（秒数） emergentTime=
            String emergentTime = "10";

            ISendMode mode = null;

            //todo 超时时间(只针对同步有效且是发送节目后)
            Integer timeOut = null;
            //在线Online和离线Offline配置 也可以设置为null
            OnlineMode lineMode = OnlineMode.Online;//OnlineMode.Online
            if ("Emergent".equalsIgnoreCase(sendMode)) {
                //异步方式
                Integer playMode = 0;
                if ("Duration".equalsIgnoreCase(emergentPlayMode)) {
                    playMode = 1;
                }
                Integer emergentTimeV = 0;
                if (StringUtils.isNotEmpty(emergentTime)) {
                    emergentTimeV = Integer.valueOf(emergentTime);
                }
                //todo 任务处理模式
                //情况1：   为空不设置，默认为1
                mode = new EmergentSendMode(exm, playMode, emergentTimeV, timeOut, lineMode);
                //情况2： 设置1 ：如果屏幕当前有任务，则跳过此次发布
//                mode = new EmergentSendMode(exm,playMode,emergentTimeV,timeOut,lineMode,1);
                //情况3： 设置 2：如果有任务，则取消之前的任务后发送本次任务。
//                mode = new EmergentSendMode(exm,playMode,emergentTimeV,timeOut,lineMode,2);

            } else {
                //todo 任务处理模式
                //情况1：   为空不设置，默认为1
//                mode = new DefaultSendMode(exm,timeOut,lineMode);
                //情况2： 设置1 ：如果屏幕当前有任务，则跳过此次发布
//                mode = new DefaultSendMode(exm,timeOut,lineMode,1);
                //情况3： 设置 2：如果有任务，则取消之前的任务后发送本次任务。
                mode = new DefaultSendMode(exm, timeOut, lineMode, 2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
