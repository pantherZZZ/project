package com.th.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.th.system.po.SysFac;
import com.th.system.po.SysMod;
import com.th.system.po.SysPro;
import com.th.system.service.SysDtuTotalService;
import com.th.system.tcp.*;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新增dtu中选择厂商，产品，型号
 */
@CrossOrigin
@RestController
@RequestMapping("/dtuTotal")
public class SysDtuTotalController {

    @Autowired
    private SysDtuTotalService sysDtuTotalService;

    @Autowired
    private RestTemplate restTemplate;

    EncapsulationUtil en = new EncapsulationUtil();

    /**
     * 查所有dtu厂商
     *
     * @return
     */
    @PostMapping(value = "/findFactory", produces = "application/json;charset=UTF-8")
    public String findAllDtu() {
        List<SysFac> list = sysDtuTotalService.findAllDtu();
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 根据dtu厂商查dtu产品
     *
     * @param plantId
     * @return
     */
    @PostMapping(value = "/findProduct", produces = "application/json;charset=UTF-8")
    public String findAllProduct(String plantId) {
        List<SysPro> list = sysDtuTotalService.findAllProduct(plantId);
        JSONObject json = en.encapsulationListJson(list);
        return JsonUtil.writeAsString(json);
    }

    /**
     * 根据dtu产品查dtu型号
     *
     * @param typeId
     * @return
     */
    @PostMapping(value = "/findModel", produces = "application/json;charset=UTF-8")
    public String findAllModel(String typeId) {
        List<SysMod> allModel = sysDtuTotalService.findAllModel(typeId);
        JSONObject json = en.encapsulationListJson(allModel);
        return JsonUtil.writeAsString(json);
    }

    @PostMapping(value = "/find", produces = "application/json;charset=UTF-8")
    public String findAll(String startTime, String endTime, String sortFiled, String sortType, Integer pageCurrent, Integer pageSize) {
        Reture data = new Reture();
        TwoData twoData = new TwoData();
        DataNeed dataNeed = new DataNeed();
        dataNeed.setId("1");
        dataNeed.setCalorie("500");
        dataNeed.setDistance("2000");
        dataNeed.setLocus("长沙");
        dataNeed.setSpeed("30");
        dataNeed.setTime("20");
        DataNeed dataNeed2 = new DataNeed();
        dataNeed2.setId("2");
        dataNeed2.setCalorie("250");
        dataNeed2.setDistance("1000");
        dataNeed2.setLocus("岳阳");
        dataNeed2.setSpeed("15");
        dataNeed2.setTime("10");
        List<DataNeed> list = new ArrayList<>();
        list.add(dataNeed);
        list.add(dataNeed2);
        twoData.setPages(1);
        twoData.setPageNum(2);
        twoData.setPageSize(4);
        twoData.setSize(2);
        twoData.setTotal(2);
        twoData.setList(list);
        data.setCode("200");
        data.setSuccess("成功");
        data.setErrMsg("没有错误信息!");
        data.setTwoData(twoData);
        Gson gson = new Gson();
        String s = gson.toJson(data);
        return s;
    }
//        List<DataNeed> list = new ArrayList<>();
//        DataNeed test = new DataNeed();
//        test.setId(1);
//        test.setName("张三");
//        test.setSex("男");
//        DataNeed test1 = new DataNeed();
//        test1.setId(2);
//        test1.setName("李四");
//        test1.setSex("男");
//        list.add(test);
//        list.add(test1);
//        Map<String, List<DataNeed>> map = new HashMap<>();
//        map.put("first", list);
//        return map;
//        String data = "apiurl的值为："+apiUrl+"appkey的值为："+appKey;
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("data",data);
//        return jsonObject;
//        JSONObject jsonOk = new JSONObject();
//        jsonOk.put("message", "成功");
//        jsonOk.put("code", 200);
//        JSONObject jsonError = new JSONObject();
//        jsonError.put("message", "不成功");
//        jsonError.put("code", 201);
//        try {
//            int i = 10 / 10;   // 抛出 Exception，后续处理被拒绝
//            System.out.println("i vaule is : " + i);
//            return jsonOk;    // Exception 已经抛出，没有获得被执行的机会
//        } catch (Exception e) {
//            return jsonError;    // Exception 抛出，获得了调用方法并返回方法值的机会
//        }


    @PostMapping("/aaa")
    public List<DataNeed> getData() throws Exception {
        String url = "https://light.taiott.com/v1/zhang/find";
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", "1");
        map.put("endTime", "2");
        map.put("sortFiled", "SPEED");
        map.put("sortType", "DESC");
        map.put("pageCurrent", 1);
        map.put("pageSize", 10);
        Map responseMap = restTemplate.postForObject(url, map, Map.class);
//        List<DataTwo> listDataTwo = new ArrayList<>();
//        List<DataOne> listDataOne = new ArrayList<>();
        List<DataNeed> resultList = new ArrayList<>();
        System.out.println(responseMap);
        responseMap.forEach((key, value) -> {
            if ("twoData".equals(key)) {
                System.out.println(key);
                Map<String, Object> date = (Map<String, Object>) value;
                System.out.println(date);
                date.forEach((k, v) -> {
                    if ("list".equals(k)) {
                        System.out.println(k);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) v;
                        System.out.println(list);
                        list.forEach((Map<String, Object> mapValue) -> {
                            DataNeed dataNeed = new DataNeed();
                            dataNeed.setId(mapValue.get("id").toString());
                            dataNeed.setLocus(mapValue.get("locus").toString());
                            dataNeed.setDistance(mapValue.get("distance").toString()+"M");
                            int time = Integer.parseInt(mapValue.get("time").toString());
                            dataNeed.setTime(sumSecondToTime(time*60));
                            dataNeed.setCalorie(mapValue.get("calorie").toString()+"CAl");
                            dataNeed.setSpeed(mapValue.get("speed").toString()+"M/h");
                            resultList.add(dataNeed);
                        });
                    }
                });
            }
        });
        System.out.println(resultList);
//        resultList.forEach(dataNeed -> {
//            System.out.println(dataNeed.getId());
//            System.out.println(dataNeed.getTime());
//            System.out.println(dataNeed.getSpeed());
//            System.out.println(dataNeed.getDistance());
//            System.out.println(dataNeed.getCalorie());
//            System.out.println(dataNeed.getLocus());
//        });
        String pathTxt = screen(resultList);

        return resultList;

    }

    @PostMapping("bbb")
    public String screen(List<DataNeed> list) {
        String readStr = "\t";//标题以tab 分割
        String lineFeed = "\n";
        String a = "速度 TOP 10" + lineFeed
                + "序号\t\t\t" + "路线\t\t\t" + "速度\t\t\t" + "完成时间\t\t\t" + "卡路里";
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + UUID.randomUUID().toString().replace("-", "") + ".txt";
        String pathTxt = "C:/bbb/" + newFileName;
        File file = new File(pathTxt);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer writer = null;
        for (int i = 0; i < list.size(); i++) {
            String b = "\n";
            b += list.get(i).getId() + "\t\t\t" + list.get(i).getLocus() + "\t\t\t" + list.get(i).getSpeed()
                    + "\t\t\t" + list.get(i).getTime() + "\t\t\t" + list.get(i).getCalorie();
            try {
                writer = new FileWriter(pathTxt, true);
                if (i == 0) {
                    writer.write(a + b);
                } else {
                    writer.write(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return pathTxt;
    }

    /**
     * 秒转时分秒
     *
     * @param sumSecond 总秒数
     * @return java.lang.String 返回 “ 01:01:01 ” 格式的时间
     */
    private String sumSecondToTime(int sumSecond) {
        if (sumSecond <= 0) {
            return "00:00:00";
        }
        int h = sumSecond / 3600;
        int m = (sumSecond - h * 3600) / 60;
        int s = sumSecond - h * 3600 - m * 60;
        String time = "%02d:%02d:%02d";
        time = String.format(time, h, m, s);
        return time;
    }


    /**
     * 测试数据
     *
     * @return
     */
    public static List<Map<String, String>> testDataTxtList() {
        // 标题
        List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", "id");
        map.put("2", "专辑名称");
        map.put("3", "歌手");
        map.put("4", "发表年份");
        list2.add(map);

        //表格内容
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("1", "1");
        map1.put("2", "七里香");
        map1.put("3", "周杰伦");
        map1.put("4", "2004");

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("1", "2");
        map2.put("2", "心中的日月");
        map2.put("3", "王力宏");
        map2.put("4", "2005");

        list2.add(map1);
        list2.add(map2);

        return list2;
    }

    /**
     * 创建表格方式的 txt文件
     *
     * @return
     */
    public static void createTxtFile(List<Map<String, String>> contentList, String filePath, String fileName) throws IOException {

        // 拼接文件完整路径
        String fullPath1 = filePath + File.separator + fileName + "_temp.txt";//生成一个临时文件
        String fullPath = filePath + File.separator + fileName + ".txt";//标准路径

        // 保证创建一个新文件
        File filenameShip = new File(fullPath1);
        if (!filenameShip.getParentFile().exists()) { // 如果父目录不存在，创建父目录
            filenameShip.getParentFile().mkdirs();
        }
        if (filenameShip.exists()) { // 如果已存在,删除旧文件
            filenameShip.delete();
        }

        // 先读取原有文件内容，然后进行写入操作
        String readStr = "\t";//标题以tab 分割
        FileWriter writer = null;
        for (int i = 0; i < contentList.size(); i++) {


            HashMap<String, String> map = (HashMap<String, String>) contentList.get(i);
            String filein1 = "\r\n";


            for (int j = 0; j < map.size() - 1; j++) {
                filein1 += map.get((1 + j) + "") + readStr;
                //对循环的某一个map 每一个值 空格
            }


            filein1 += map.get((map.size()) + "");   //序号展示

            filein1 += "\r\n";     //对每个map进行换行
            try {
                writer = new FileWriter(filenameShip, true);
                writer.write(filein1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        readLocalFile(fullPath1, fullPath, contentList.size());
    }

    /**
     * 用Java实现去除文本文件中的空行
     *
     * @param fullPath1
     * @param fullPath
     * @param size
     */
    public static void readLocalFile(String fullPath1, String fullPath, int size) {
        String filename1 = fullPath1;//该文件有大量的空行
        String filename2 = fullPath;//filename1文件被去除空行后，放入文件filename2中
        File file = new File(filename1);
        InputStream is = null;
        BufferedReader br = null;
        String tmp;
        FileWriter writer = null;
        int i = 0;

        try {
            is = new BufferedInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            writer = new FileWriter(filename2, true);
            while ((tmp = br.readLine()) != null) {
                if (tmp.equals("")) ;
                else {

                    if (i == (size - 1)) {//从0开始遍历
                        writer.write(tmp);//去除最后一行的空格
                    } else {
                        writer.write(tmp + "\n");
                        i++;
                    }
                }
            }
            writer.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File filenameShip = new File(fullPath1);//删除临时文件
        filenameShip.delete();
    }
}

