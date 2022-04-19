package com.th.screen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhang bao
 * @Date 2022/3/15 13:27
 * @Description
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileIdVo {

    private String fileId;

    private String deviceCode;

    private Date startTime;

    private Date endTime;
}
