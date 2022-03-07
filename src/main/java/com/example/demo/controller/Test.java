package com.example.demo.controller;

import com.example.demo.utils.DomeTimerTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/test")
public class Test {

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

    public  void zip(String[] args) throws IOException {
        File file = new File("E:\\Desktop\\ha.zip");
        InputStream inputStream = new FileInputStream(file);
//        String result = new BufferedReader(new InputStreamReader(inputStream))
//                .lines().collect(Collectors.joining(System.lineSeparator()));
//                System.out.println(result);

        InputStream tInputStringStream = new ByteArrayInputStream(inputStream.toString().getBytes());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch=0;
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


    public static void main(String[] args) {
        String s=" 222";
        System.out.println(Long.parseLong(s));
    }
}
