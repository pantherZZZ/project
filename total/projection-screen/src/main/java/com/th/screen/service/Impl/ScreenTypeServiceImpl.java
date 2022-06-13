package com.th.screen.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.hidisp.api.cloud.CommandManager;
import com.hidisp.api.cloud.ProgramManager;
import com.hidisp.api.cloud.models.CommandResult;
import com.hidisp.api.cloud.models.ExecuteMode;
import com.hidisp.api.cloud.models.SendResult;
import com.hidisp.api.cloud.models.SendTask;
import com.th.screen.entity.ProgramMediaLibrary;
import com.th.screen.entity.ScreenTwo;
import com.th.screen.service.*;
import com.th.screen.until.ReturnUtil;
import com.th.screen.until.Test;
import onbon.y2.Y2Env;
import onbon.y2.Y2Screen;
import onbon.y2.play.PicArea;
import onbon.y2.play.ProgramPlayFile;
import onbon.y2.play.VideoArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * @Author zhang bao
 * @Date 2022/3/16 11:52
 * @Description
 * @Version 1.0
 */
@Service
public class ScreenTypeServiceImpl implements ScreenTypeService {

    @Value("${screen.apiUrl}")
    private String apiUrl;

    @Value("${screen.appKey}")
    private String appKey;

    @Autowired
    private NewProgramGuidesService newProgramGuidesService;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public JSONObject projectionScreen(String screenId, String mediaId) {
        List<ScreenTwo> screenTwoList = findNumberById(screenId);
        List<ProgramMediaLibrary> mediaLibraryList = findUrl(mediaId);
        if (CollectionUtil.isEmpty(screenTwoList) || CollectionUtil.isEmpty(mediaLibraryList)) {
            return ReturnUtil.resultNullPointError();
        }
        List<String> fileUrlList = CollUtil.getFieldValues(mediaLibraryList, "fileUrl", String.class);
        boolean result = true;
        for (ScreenTwo screenTwo : screenTwoList) {
            //仰邦内网
            if (screenTwo.getScreenType() == 0 && screenTwo.getPattern() == 1) {
                //屏幕不在线，更新sta不在线
                if (screenTwo.getSte() == 0) {
                    newProgramGuidesService.updateOffLine(screenTwo.getId());
                    return ReturnUtil.resultScreenOffLineError();
                }
                try {
                    Y2Screen screen;
                    try {
                        screen = new Y2Screen("http://" + screenTwo.getNumber());
                        screen.login("guest", "guest");
                    } catch (Exception e) {
                        //登录没成功屏幕改为离线
                        newProgramGuidesService.updateOffLine(screenTwo.getId());
                        return ReturnUtil.resultLoginError();
                    }
                    screen.turnOn();
                    screen.syncTime();
                    Thread.sleep(1000);
                    PicArea pArea = new PicArea(0, 0, 400, 980);
                    VideoArea varea = new VideoArea(0, 0, 160, 280);
                    ProgramPlayFile pf = new ProgramPlayFile(0);
                    for (String fileUrl : fileUrlList) {
                        if (fileUrl.endsWith(".mp4")) {
                            varea.addVideo(fileUrl, 100);
                            pf.getAreas().add(varea);
                            String s = screen.writePlaylist(pf);
                            screen.play(s);
                        } else {
                            pArea.addPage(fileUrl);
                            pf.getAreas().add(pArea);
                            String s = screen.writePlaylist(pf);
                            screen.play(s);
                        }
                    }
                    screen.logout();
                } catch (Exception e) {
                    return ReturnUtil.resultYBError();
                }
            }

            //仰帮公网版
            if (screenTwo.getScreenType() == 0 && screenTwo.getPattern() == 0) {
                openScreen(screenTwo.getNumber());
                Map<String, File[]> data = new HashMap<>();
                String[] array = fileUrlList.toArray(new String[0]);
                File[] urlArray = new File[fileUrlList.size()];
                for (int i = 0; i < array.length; i++) {
                    urlArray[i] = new File(array[i]);
                }
                data.put("0", urlArray);
                SendTask task = new SendTask();
                try {
                    //调用投屏接口
                    SendResult sendResult = ProgramManager.getInstance().sendProgram(apiUrl, appKey, screenTwo.getNumber(), data, task);
                    if (sendResult.getResult() == 1) {
                        return ReturnUtil.resultYBError();
                    }
                } catch (Exception e) {
                    return ReturnUtil.resultYBError();
                }
            }

            //熙讯投屏
            if (screenTwo.getScreenType() == 1) {
                try {
                    Test.managementScreen(result);
                    Test.get();
                } catch (Exception e) {
                    ReturnUtil.resultXXError();
                }
            }
        }
        return ReturnUtil.resultOk();
    }

    @Override
    public JSONObject closeScreen(String screenId) {
        List<ScreenTwo> screenTwoList = findNumberById(screenId);
        if (CollectionUtil.isEmpty(screenTwoList)) {
            ReturnUtil.resultNullPointError();
        }
        boolean result = false;
        try {
            Y2Env.initial();
            for (ScreenTwo screenTwo : screenTwoList) {
                //仰帮公网关屏
                if (screenTwo.getPattern() == 0 && screenTwo.getScreenType() == 0) {
                    offScreen(screenTwo.getNumber());
//                    if (numberList.size() > 1) {
//                        StringBuffer sb = new StringBuffer();
//                        numberList.stream().forEach(str -> {
//                            sb.append(str).append(",");
//                        });
//                        sb.deleteCharAt(sb.length() - 1);
//                        String number = sb.toString();
//                        offScreen(number);
//                    } else {
//                        ScreenTwo screen = screenTwoMapper.selectById(screenId);
//                        offScreen(screen.getNumber());
//                    }
//                    break;
                }
                //仰帮内网关屏
                if (screenTwo.getPattern() == 1 && screenTwo.getScreenType() == 0) {
                    try {
                        Y2Screen screen = new Y2Screen("http://" + screenTwo.getNumber());
                        screen.login("guest", "guest");
                        screen.turnOff();
                    } catch (Exception e) {
                        ReturnUtil.resultLoginError();
                    }
                }

                //熙讯关屏
                if (screenTwo.getScreenType() == 1) {
                    Test.managementScreen(result);
                }
            }
        } catch (Exception e) {
            return ReturnUtil.CloseYBError();
        }
        return ReturnUtil.closeOk();
    }


    /**
     * 根据屏幕id查所有屏幕集合
     *
     * @param screenId
     * @return
     */
    public List<ScreenTwo> findNumberById(String screenId) {
        List<ScreenTwo> screenTwoList = new ArrayList<>();
        if (screenId.contains(",")) {
            String[] screenIdArray = screenId.split(",");
            for (String sId : screenIdArray) {
                ScreenTwo screenTwo = newProgramGuidesService.findScreenTypeById(Integer.parseInt(sId));
                screenTwoList.add(screenTwo);
            }
        } else {
            ScreenTwo screenTwo = newProgramGuidesService.findScreenTypeById(Integer.parseInt(screenId));
            screenTwoList.add(screenTwo);
        }
        return screenTwoList;
    }

    /**
     * 根据文件id查所有文件集合
     *
     * @param mediaId
     * @return
     */
    public List<ProgramMediaLibrary> findUrl(String mediaId) {
        List<ProgramMediaLibrary> mediaList = new ArrayList<>();
        if (mediaId.contains(",")) {
            String[] mediaIdArray = mediaId.split(",");
            for (String mId : mediaIdArray) {
                ProgramMediaLibrary programMediaLibrary = fileUploadService.findAllUrlById(Integer.parseInt(mId));
                mediaList.add(programMediaLibrary);
            }
        } else {
            ProgramMediaLibrary programMediaLibrary = fileUploadService.findAllUrlById(Integer.parseInt(mediaId));
            mediaList.add(programMediaLibrary);
        }
        return mediaList;
    }

    /**
     * 仰帮外网开屏
     *
     * @param screens
     */
    public void openScreen(String screens) {
        ExecuteMode mode = ExecuteMode.Sync;//默认为同步模式，可以为空;
        //========设置屏幕开关机
        String status = "on";// on开，off关
        CommandResult powerRes = CommandManager.getInstance().setPower(apiUrl, appKey, screens, status, mode);
    }

    /**
     * 仰帮外网关屏
     *
     * @param screens
     */
    public void offScreen(String screens) {
        ExecuteMode mode = ExecuteMode.Sync;//默认为同步模式，可以为空;
        //========设置屏幕开关机
        String status = "off";// on开，off关
        CommandResult powerRes = CommandManager.getInstance().setPower(apiUrl, appKey, screens, status, mode);
    }
}
