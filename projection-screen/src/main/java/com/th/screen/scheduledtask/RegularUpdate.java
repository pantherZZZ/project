package com.th.screen.scheduledtask;

import com.hidisp.api.cloud.ScreenManager;
import com.hidisp.api.cloud.models.ScreenInfo;
import com.th.screen.entity.ScreenTwo;
import com.th.screen.service.NewProgramGuidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/3/30 17:43
 * @Description 定时更新公网投屏屏幕离线在线状态
 * @Version 1.0
 */
@Component
public class RegularUpdate {
    @Value("${screen.apiUrl}")
    private String apiUrl;

    @Value("${screen.appKey}")
    private String appKey;

    @Autowired
    private NewProgramGuidesService newProgramGuidesService;

    @Scheduled(cron = "${task.cron.tradeData}")
    public void UpdateOnlineStatus() {
        List<ScreenInfo> screenInfoList = ScreenManager.getInstance().getScreens(apiUrl, appKey);
        if (screenInfoList != null) {
            List<ScreenTwo> screenTwoList = newProgramGuidesService.selectAll();
            for (ScreenInfo screenInfo : screenInfoList) {
                for (ScreenTwo screenTwo : screenTwoList) {
                    if (screenInfo.getCode().equals(screenTwo.getNumber())) {
                        if (screenInfo.getStatus() == 0 || screenInfo.getStatus() == 2) {
                            newProgramGuidesService.updateOffLine(screenTwo.getId());
                        } else if (screenInfo.getStatus() == 1) {
                            newProgramGuidesService.updateOnLine(screenTwo.getId());
                        }
                    }
                }
            }
        }
    }
}
