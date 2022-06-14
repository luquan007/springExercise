package com.example.demo.controller.ioStream;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("zipTest")
public class ZipTest {


    @GetMapping("zipFolder")
    public void zipFolder(String filePath) throws IOException {

        ZipFile zipFile = new ZipFile(filePath);
        String mFileName = "";
        String mFileChange = "";
        FileInputStream fileInputStream = new FileInputStream(filePath);

        for (Enumeration<? extends ZipEntry> e = zipFile.getEntries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()) {
                InputStream inputStream = zipFile.getInputStream(entry);
                System.out.println("保单文件名:" + entry.getName() + ", 内容如下:");
                String name = entry.getName();
                String pathName = "E:/Desktop".concat("/" ) + name;
                File markFile = new File(pathName.substring(0,pathName.lastIndexOf("/")));
                File imgFile = new File(pathName);
                if (!markFile.exists()) {
                    markFile.mkdirs();
                }
                if (!imgFile.exists()) {
                    imgFile.createNewFile();
                }
            FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int n;
            byte[] bytes = new byte[1024];

            while ((n = inputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, n);
            }
            //关闭流
            bufferedOutputStream.close();
            fileOutputStream.close();
            inputStream.close();
            }
        }
       /* ZipInputStream zipInputStream = null;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //不指定格式的话获取不到压缩文件里的东西
        // zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"));
        zipInputStream = new ZipInputStream(fileInputStream, Charset.forName("GBK"));
        //读取zip包里的文件
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        if(nextEntry.isDirectory()){
            File file = new File(nextEntry.getName());

            System.out.println(file.getAbsolutePath());
        }
        while (nextEntry!=null && !nextEntry.isDirectory()){
            System.out.println(nextEntry);
        }*/

    }


    @PostMapping("test")
    public void test1(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            //前端接收
            response.reset();
            response.setContentType("application/zip;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test" + ".zip", "UTF-8"));

            inputStream = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);

            File outFile = new File("E:\\Desktop\\test.zip");
            outFile.createNewFile();
            fileOutputStream = new FileOutputStream(outFile);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = new byte[1024 * 10];
            int read = 0;
            while ((read = bufferedInputStream.read(bytes, 0, 1024 * 10)) != -1) {
                /*response.getOutputStream().write(bytes,0,read);
                response.getWriter();*/
                bufferedOutputStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("testOne")
    public void test(HttpServletResponse response) {
        String sourceFilePath = "E:\\Desktop\\img";
        String zipFilePath = "E:\\Desktop\\test.zip";


        //源文件
        File sourceFile = new File(sourceFilePath);

        //文件输入流
        FileInputStream fis = null;

        //缓冲流
        BufferedInputStream bis = null;

        //文件输出流
        FileOutputStream fos = null;

        //zip输出流
        ZipOutputStream zos = null;

        try {
            //前端接收
            response.reset();
            response.setContentType("application/zip;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test" + ".zip", "UTF-8"));

            if (!sourceFile.exists()) {
                System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在. <<<<<<");
            } else {
                //创建压缩文件
                File zipFile = new File(zipFilePath);
                if (zipFile.exists()) {
                    System.out.println("已经有该文件了");
                } else {
                    //创建一个文件
                    //zipFile.createNewFile();

                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
                        System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath
                                + " 里面不存在文件,无需压缩. <<<<<<");
                    } else {
                        // fos = new FileOutputStream(zipFile);
                        //zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        zos = new ZipOutputStream(response.getOutputStream());
                        byte[] bytes = new byte[1024 * 10];
                        for (int i = 0; i < sourceFiles.length; i++) {

                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            sourceFiles[i].setExecutable(true);
                            sourceFiles[i].setReadable(true);
                            sourceFiles[i].setWritable(true);

                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024 * 10);
                            int read = 0;

                            while ((read = bis.read(bytes, 0, 1024 * 10)) != -1) {
                                zos.write(bytes, 0, read);
                            }

                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != zos) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("testTwo")
    public void testTwo(String[] strings, HttpServletResponse response) {
        //zip输出流
        ZipOutputStream zos = null;
        try {
            //前端接收
            response.reset();
            response.setContentType("application/zip;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test" + ".zip", "UTF-8"));
            zos = new ZipOutputStream(response.getOutputStream());
            for (String string : strings) {
                //new File("");
                ZipEntry zipEntry = new ZipEntry(string);
                zipEntry.setComment("thisIsComment");
                zos.putNextEntry(zipEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("testThree")
    public String getPython(HttpServletResponse response) throws IOException {
        String url = "http://192.168.0.106:9089/py/luoboExportHandler";
        JSONObject result = new JSONObject();
        JSONArray objectlist = new JSONArray();
        JSONObject requestJson = new JSONObject();
        requestJson.put("name", "person");
        JSONObject bndbox = new JSONObject();
        bndbox.put("xmin", 48);
        bndbox.put("ymin", 240);
        bndbox.put("xmax", 195);
        bndbox.put("ymax", 371);
        requestJson.put("bndbox", bndbox);
        objectlist.add(requestJson);

        result.put("url", "https://issglobalgroup.com/semanticsegmentation/image/20220316test1.jpg");
        result.put("objectlist", objectlist);

        String requestParam = result.toJSONString();
        HttpResponse responseObj = HttpRequest.post(url).body(requestParam).execute();
        byte[] bytes = responseObj.bodyBytes();

        ServletOutputStream outputStream = response.getOutputStream();
        //FileOutputStream fileOutputStream = new FileOutputStream(response.getOutputStream());
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*//zip输出流
        ZipOutputStream zos = null;
        //缓冲流
        BufferedInputStream bis = null;
        //文件输入流
        FileInputStream fis = null;
        int read = 0;

        //zos = new ZipOutputStream(new FileOutputStream("E:\\Desktop\\test.zip"));
        try {

            if (!((read = bis.read(bytes, 0, bytes.length)) != -1)) {
                zos.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


        System.out.println(responseObj);
        return null;
    }

    @GetMapping("testFour")
    public String testFour(String url) {

        File file = new File(url);
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<MediaType, Parser>());
        Metadata metadata = new Metadata();
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());
        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }

    @GetMapping("testFive")
    public String testFive(String url) throws IOException {
        FileWriter fileWriter = new FileWriter(url);
        fileWriter.write("开始时间\t");
        fileWriter.write("结束时间\t");
        fileWriter.write("对应文本/标答\n");
        for (int i = 0; i < 10; i++) {
            fileWriter.write(i + i + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" + "\t");
            fileWriter.write(i + i + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" + "\t");
            fileWriter.write(i + i + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" + "\n");
        }
        fileWriter.close();
        return "success";
    }

    /*@PostMapping("testSix")
    public String testSix(@RequestParam("file") MultipartFile file) {
        String tempFilePath = "D:/data/transcribe_files";
        ZipInputStream zipInputStream = null;
        String originalFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        File markFile = new File(tempFilePath.concat("/").concat(originalFilename));
        try {
            if (!markFile.exists()) {
                markFile.mkdirs();
            }
            //不指定格式的话获取不到压缩文件里的东西
            zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"));
            ZipEntry nextEntry = zipInputStream.getNextEntry();

            while (nextEntry != null) {
                String name = nextEntry.getName();
                File imgFile = new File(tempFilePath.concat("/").concat(originalFilename) + "/" + name);
                //文件则写入具体的路径中
                FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int n;
                byte[] bytes = new byte[1024];
                while ((n = zipInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, n);
                }
                //关闭流
                bufferedOutputStream.close();
                fileOutputStream.close();
                //关闭当前布姆
                zipInputStream.closeEntry();
                //读取下一个目录，作为循环条件
                nextEntry = zipInputStream.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }*/

    @PostMapping("testSeven")
    public static String testSeven(@RequestParam("file") MultipartFile file) {
        long size = file.getSize();
        return "result" + size;
    }

    /**
     * 发送post请求返回json
     *
     * @param url
     * @param formEntity
     */
    public static String sendPostGetJson(String url, HttpEntity<?> formEntity) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, formEntity, JSONObject.class);
        return response.toString();
    }

}
