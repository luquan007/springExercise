package com.example.demo.utils;

import ch.qos.logback.core.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUtils {

    /**
     * 创建文件夹及文件
     *
     * @param src
     */
    public static boolean createFile(String src) {
        // path表示你所创建文件的路径
        String path = src.substring(0, src.lastIndexOf("/"));
        // fileName表示你创建的文件名
        String fileName = src.substring(src.lastIndexOf("/") + 1, src.length());
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        File file = new File(f, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("FileUtils#createFile:" + e);
                return false;
            }
        }
        return true;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName：要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.info("FileUtils#delete: 文件不存在");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName：要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                log.info("FileUtils#deleteFile: 删除文件失败");
                return false;
            }
        } else {
            log.info("FileUtils#deleteFile: 文件不存在");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir：要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            log.info("FileUtils#deleteFile: 文件夹不存在");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            log.info("FileUtils#deleteFile: 删除子文件夹失败");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            log.info("FileUtils#deleteFile: 删除父文件夹失败");
            return false;
        }
    }

    public static void downloadFile(String filePath, HttpServletResponse response, String contentType) {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            File file = new File(filePath);
            String filename = file.getName();
            fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + String.valueOf(URLEncoder.encode(filename, "UTF-8")));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/msword");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            log.error("导出文件异常:", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("导出文件异常:", e);
                }
            }
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    log.error("导出文件异常:", e);
                }
            }
        }
    }


    public static File multipartFileToFile(MultipartFile file, String filePath) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(filePath);
            //判断是否存在文件夹,不存在则新建
            if (!toFile.getParentFile().exists()) {
                toFile.getParentFile().mkdirs();
            }

            //文件不存在就创建文件
            if (!toFile.exists()) toFile.createNewFile();

            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        deleteDirectory("D:\\data\\audio\\123");
        List<File> allFile = getAllFile("D:\\data\\transcribe_files\\20220210121852", false);
        System.out.printf("qwe");
    }


    public static void copyInputStreamToFile(InputStream inputStream, File file) {
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<File> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<File> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            list.add(baseFile);
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file);
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file);
            }
        }
        return list;
    }

}
