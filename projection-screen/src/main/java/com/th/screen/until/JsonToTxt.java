package com.th.screen.until;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

/**
 * @Author zhang bao
 * @Date 2022/4/12 16:46
 * @Description
 * @Version 1.0
 */
public class JsonToTxt {

    public static void createTXT(String src, JSONArray array) {
        File file = new File(src); //存储的目标文件
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            JSONObject first = array.getJSONObject(0);
            Iterator<String> iterator = first.keys();
            while (iterator.hasNext()) {
                // 得到key
                String key = (String) iterator.next();
                bw.write(key); //json字符串写入文件
                bw.write("\t");
            }
            bw.write("\r\n");
            for (int i = 0; i < array.size(); i++) {
                // 得到数组的每项
                JSONObject item = array.getJSONObject(i);
                // 得到key集合
                iterator = item.keys();
                while (iterator.hasNext()) {
                    // 得到key
                    String key = iterator.next();
                    // 得到key对应的value
                    String value = item.getString(key);
                    bw.write(value);
                    bw.write("\t");
                }
                bw.write("\r\n");
            }
            bw.flush();
            System.out.println("json数据写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileTxt(String[] args) {
        String str = "[\n" +
                "    {\n" +
                "      \"姓名\":\"张三\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"22\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"李四\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"23\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"王五\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"22\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"张三丰\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"100\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"楚雨荨\",\n" +
                "\t  \"性别\":\"女\",\n" +
                "\t  \"年龄\":\"22\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"张翰dfdfdf\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"23\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"张无忌sdfdsfdsfdfdfdff\",\n" +
                "\t  \"性别\":\"男\",\n" +
                "\t  \"年龄\":\"24\"\n" +
                "    },\n" +
                "\t {\n" +
                "      \"姓名\":\"赵敏\",\n" +
                "\t  \"性别\":\"女\",\n" +
                "\t  \"年龄\":\"23\"\n" +
                "    }\n" +
                "  ]";
        JSONArray jsonArray = JSONArray.fromObject(str);
        createTXT("D:/uselist.txt", jsonArray);
    }
}
