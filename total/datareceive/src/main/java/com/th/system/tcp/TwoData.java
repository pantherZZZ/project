package com.th.system.tcp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/4/24 11:22
 * @Description： 二级
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class TwoData {
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private Integer startRow;
    private Integer endRow;
    private Integer total;
    private Integer pages;
    private List<DataNeed> list;
}
