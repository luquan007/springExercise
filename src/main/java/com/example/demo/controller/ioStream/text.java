package com.example.demo.controller.ioStream;

import io.netty.util.CharsetUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("text")
public class text {

    @PostMapping("read")
    public void readText(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), CharsetUtil.UTF_8));
        String lineTxt;
        List list= new ArrayList();
        while ((lineTxt=bufferedReader.readLine())!=null){
            list.add(lineTxt);
            if(lineTxt.contains("text =")){
                int size = list.size();
                System.out.println(lineTxt.replace("text = ","").replace("\"","").trim());
                System.out.println(list.get(size-2).toString().replace("xmax = ","").trim());
                System.out.println(list.get(size-3).toString().replace("xmin = ","").trim());
                System.out.println("-------");
            }
           // System.out.println(lineTxt);
        }


    }
}
