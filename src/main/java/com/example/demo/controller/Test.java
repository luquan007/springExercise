package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.enumeration.RoleEnum;
import com.example.demo.utils.DomeTimerTask;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("test")
    public List test(String str) {
        List list=new ArrayList();
        List<String> jsonObjects = JSONArray.parseArray(str, String.class);
        List<String> collect = jsonObjects.stream().map(
                firstFloor -> {
                    String replace = firstFloor.replace("\"name\":", "\"label\":").replace("\"attribute\":", "\"children\":");
                    return replace;
                }
        ).collect(Collectors.toList());
        return collect;
    }

    @GetMapping("contains")
    public boolean getPermissions(@RequestParam("list")List<Long> list,@RequestParam("code") Long code) {
        boolean b = list.stream().anyMatch(temp -> temp <= code);
        return b;
        /*for (Long aLong : codeList) {
            if (aLong < code) {
                return true;
            }
        }
        return false;*/
    }


    /**
     * 判断一个list里是否包含大于一个数的
     *
     * @param
     * @return
     */

    public Boolean containsNum(@RequestParam("arr") List<Long> arr, @RequestParam("num") Long num) {

        if (arr.stream().filter(ss -> ss < num).collect(Collectors.toList()).size() > 0) {
            return true;
        }
        return false;
    }

    @GetMapping("timerTask")
    public Map timerTaskTest(@RequestBody Map map1) {
        /*Timer timer = new Timer();
        timer.schedule(new DomeTimerTask("PeriodDemo"),1000L,1000L*10);*/
        Map map = new HashMap<Long, String>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        return map1;
    }

    public void zip(String[] args) throws IOException {
        File file = new File("E:\\Desktop\\ha.zip");
        InputStream inputStream = new FileInputStream(file);
//        String result = new BufferedReader(new InputStreamReader(inputStream))
//                .lines().collect(Collectors.joining(System.lineSeparator()));
//                System.out.println(result);

        InputStream tInputStringStream = new ByteArrayInputStream(inputStream.toString().getBytes());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch = 0;
        while ((ch = tInputStringStream.read()) != -1) {
            byteArrayOutputStream.write(ch);
        }

        /*File newFile = new File("E:\\Desktop\\hah.zip");
        OutputStream os = System.out;
        os = new FileOutputStream(newFile);
        int a = 0;
        while ((a = inputStream.read()) != -1) {
            os.write(a);
        }*/
        inputStream.close();
        tInputStringStream.close();
        byteArrayOutputStream.close();
    }

    @GetMapping("enumTest")
    public void enumTest() {
        RoleEnum[] values = RoleEnum.values();
        for (RoleEnum value : values) {
            if ("PM" == value.getDesc()) {
                System.out.println(value.getCode());
            }
        }
    }

}
