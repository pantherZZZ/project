package com.th.system.tcp;

import com.th.system.po.SysEquipment;
import com.th.system.service.SysEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时任务 更改设备是否在线状态
 *
 * @Author zhang bao
 * @Date 2022/1/12 15:49
 * @Version 1.0
 */
@Component
public class TimingScript {
    @Autowired
    private SysEquipmentService sysEquipmentService;


    @Scheduled(cron = "${task.cron.updateData}")
    public void updateStatus() {
        List<SysEquipment> list = sysEquipmentService.findAllEquipment();
        list.forEach(sysEquipment -> { onOrOffLine(sysEquipment); });
    }

//    //TODO：定时任务调度-拉取出 有效 + 未支付 的订单列表，前往缓存查询订单是否已失效
// @Scheduled(cron = "0 0/5 * * * ?")
// @Async("threadPoolTaskExecutor")
//    public void schedulerCheckOrder() {
//        try {
//            List list = userOrderMapper.selectUnPayOrders();
//            if (list != null && !list.isEmpty()) {
//                list.forEach(entity -> {
//                    final String orderNo = entity.getOrderNo();
//                    String key = RedisUserOrderPrefix + orderNo;
//                    if (!stringRedisTemplate.hasKey(key)) {                        //TODO:表示缓存中该Key已经失效了，即“该订单已经是超过30min未支付了，得需要前往数据库将其失效掉”                        userOrderMapper.unActiveOrder(entity.getId());
//                        log.info("缓存中该订单编号已经是超过指定的时间未支付了，得需要前往数据库将其失效掉！orderNo={}", orderNo);
//                    }
//                });
//            }
//        } catch (Exception e) {
//            log.error("定时任务调度-拉取出 有效 + 未支付 的订单列表，前往缓存查询订单是否已失效-发生异常：", e.fillInStackTrace());
//        }
//    }

    public void onOrOffLine(SysEquipment sysEquipment) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //系统时间n
            long nTime = new Date().getTime() / (60 * 1000);
            //数据库时间
            Date nowTime = df.parse(sysEquipment.getLastTime());
            long dbTime = nowTime.getTime() / (60 * 1000);
            long l = nTime - dbTime;
            if (l > 3) {
                sysEquipmentService.updateOff(sysEquipment.getEquipmentId());
            } else {
                sysEquipmentService.updateOn(sysEquipment.getEquipmentId());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

