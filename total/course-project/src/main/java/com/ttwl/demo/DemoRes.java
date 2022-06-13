package com.ttwl.demo;

import com.ttwl.until.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhang bao
 * @Date 2022/5/30 13:48
 * @Description：
 * @Version 1.0
 */
public class DemoRes {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

//        System.out.println(map.toString());
        R put = R.ok("返回信息").put("data", list);
        System.out.println(put.toString());
//        System.out.println(ok.toString());
    }
}
