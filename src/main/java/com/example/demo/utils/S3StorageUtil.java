package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class S3StorageUtil {

    @Value("${s3_access_key}")
    private static String awd_access_key_id = "";
    @Value("${s3_secret_key}")
    private static String awd_secret_access_key = "";
    @Value("${s3_awd_bucket_us}")
    private static String awd_bucket_us = "";
    @Value("${s3_awd_bucket_eu}")
    private static String awd_bucket_eu = "";
    @Value("${s3_region_us}")
    private static String region_us = "";
    @Value("${s3_region_eu}")
    private static String region_eu = "";
    @Value("${file.upload.tempFilePath}")
    private static String tempFilePath = "D:/data/project_files";

    /**
     * 创建连接，连接S3服务器
     */
    public static S3Client creatS3Client(String position) {
        try {
            Region region;
            if ("US".equals(position)) {
                region = Region.US_WEST_1;
            } else {
                region = Region.EU_CENTRAL_1;
            }
            AwsCredentials awsCredentials = AwsBasicCredentials.create(awd_access_key_id, awd_secret_access_key);
            AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);
            S3Client s3Client = S3Client.builder().credentialsProvider(credentialsProvider).region(region).build();
            return s3Client;
        } catch(Exception e) {
            log.error("FileStorageUtil#creatS3Client:" + e);
            return null;
        }
    }

    /**
     * 获取该连接下所有的容器信息
     * @return
     */
    public static String getFileUrl(String filePath, String position) {
        String bucket;
        String region;
        if ("US".equals(position)) {
            bucket = awd_bucket_us;
            region = region_us;
        } else {
            bucket = awd_bucket_eu;
            region = region_eu;
        }
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + filePath;
    }

    /**
     * 获取该连接下所有的容器信息
     * @return
     */
    public static List<Bucket> getBuckets(String position) {
        try {
            List<Bucket> buckets = creatS3Client(position).listBuckets().buckets();
            return buckets;
        } catch (Exception e) {
            log.error("FileStorageUtil#getBuckets:" + e);
            return null;
        }
    }

    /**
     * 获取某个容器对象
     * @param bucketName
     * @return
     */
    public static Bucket getBuketsByName(String bucketName, String position) {
        try {
            Bucket resultBucket = null;
            if (bucketName.isEmpty()) {
                return null;
            }
            List<Bucket> buckets = creatS3Client(position).listBuckets().buckets();
            if(buckets == null){
                return resultBucket;
            }
            for (Bucket bucket : buckets) {
                if (bucketName.equals(bucket.name())) {
                    resultBucket = bucket;
                    break;
                }
            }
            return resultBucket;
        } catch (Exception e) {
            log.error("FileStorageUtil#getBuketsByName:" + e);
            return null;
        }
    }

    /**
     * 新建容器
     * @param bucketName
     * @return
     */
    public static void creatBucket(String bucketName, String position) {
        try {
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            creatS3Client(position).createBucket(bucketRequest);
        } catch (Exception e) {
            log.error("FileStorageUtil#creatBucket:" + e);
        }
    }

    /**
     * 删除容器
     * @param bucketName
     * @return
     */
    public static void deleteBucket(String bucketName, String position) {
        try {
            DeleteBucketRequest bucketRequest = DeleteBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            creatS3Client(position).deleteBucket(bucketRequest);
        } catch (Exception e) {
            log.error("FileStorageUtil#deleteBucket:" + e);
        }
    }

    /**
     * 删除容器中的某个数据
     * @param bucketName
     * @param objectName
     */
    public static void deleteBucketObjects(String bucketName, String objectName, String position) {
        try {
            ArrayList<ObjectIdentifier> toDelete = new ArrayList<>();
            toDelete.add(ObjectIdentifier.builder().key(objectName).build());
            DeleteObjectsRequest dor = DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(Delete.builder().objects(toDelete).build())
                    .build();
            creatS3Client(position).deleteObjects(dor);
        } catch (Exception e) {
            log.error("FileStorageUtil#deleteBucketObjects:" + e);
        }
    }

    /**
     * 上传本地文件到s3
     * @param filePath
     * @param fileName
     * @return
     */
    public static void uploadFile(String filePath, String fileName, String position) {
        try {
            String bucket;
            if ("US".equals(position)) {
                bucket = awd_bucket_us;
            } else {
                bucket = awd_bucket_eu;
            }
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .acl("public-read")
                    .bucket(bucket)
                    .key(fileName)
                    .build();
            creatS3Client(position).putObject(objectRequest, RequestBody.fromByteBuffer(readFileToByteBuffer(filePath)));
        } catch (Exception e) {
            log.error("FileStorageUtil#uploadFile:" + e);
        }
    }

    /**
     * 上传远程文件到s3
     * @param file
     * @param fileName
     * @return
     */
    public static void uploadMultipartFile(MultipartFile file, String fileName, String position) {
        try {
            String bucket;
            if ("US".equals(position)) {
                bucket = awd_bucket_us;
            } else {
                bucket = awd_bucket_eu;
            }
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .acl("public-read")
                    .bucket(bucket)
                    .key(fileName)
                    .build();
            creatS3Client(position).putObject(objectRequest, RequestBody.fromByteBuffer(readMultipartFileToByteBuffer(file)));
        } catch (Exception e) {
            log.error("FileStorageUtil#uploadMultipartFile:" + e);
        }
    }

//    /**
//     * 分片上传远程文件到s3
//     * @param file
//     * @param fileName
//     * @return
//     */
//    public static void uploadMultipartPartFile(MultipartFile file, String fileName) {
//        try {
//            // First create a multipart upload and get the upload id
//            CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
//                    .bucket(awd_bucket)
//                    .key(fileName)
//                    .build();
//            S3Client s3 = creatS3Client();
//            CreateMultipartUploadResponse response = s3.createMultipartUpload(createMultipartUploadRequest);
//            String uploadId = response.uploadId();
//            System.out.println(uploadId);
//            // Upload all the different parts of the object
//            UploadPartRequest uploadPartRequest1 = UploadPartRequest.builder()
//                    .bucket(awd_bucket)
//                    .key(fileName)
//                    .uploadId(uploadId)
//                    .partNumber(1).build();
//            String etag1 = s3.uploadPart(uploadPartRequest1, RequestBody.fromByteBuffer(getRandomByteBuffer(5 * mB))).eTag();
//            CompletedPart part1 = CompletedPart.builder().partNumber(1).eTag(etag1).build();
//            UploadPartRequest uploadPartRequest2 = UploadPartRequest.builder()
//                    .bucket(awd_bucket)
//                    .key(fileName)
//                    .uploadId(uploadId)
//                    .partNumber(2).build();
//            String etag2 = s3.uploadPart(uploadPartRequest2, RequestBody.fromByteBuffer(getRandomByteBuffer(3 * mB))).eTag();
//            CompletedPart part2 = CompletedPart.builder().partNumber(2).eTag(etag2).build();
//            // Finally call completeMultipartUpload operation to tell S3 to merge all uploaded
//            // parts and finish the multipart operation.
//            CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
//                    .parts(part1, part2)
//                    .build();
//            CompleteMultipartUploadRequest completeMultipartUploadRequest =
//                    CompleteMultipartUploadRequest.builder()
//                            .bucket(awd_bucket)
//                            .key(fileName)
//                            .uploadId(uploadId)
//                            .multipartUpload(completedMultipartUpload)
//                            .build();
//            s3.completeMultipartUpload(completeMultipartUploadRequest);
//        } catch (Exception e) {
//            log.error("FileStorageUtil#uploadMultipartPartFile:" + e);
//        }
//    }

    /**
     * 下载对象
     * @param position
     * @param key
     */
    public static void downloadBucketObjects(String key, String position, String folder) {
        try {
            String bucket;
            String region;
            if ("US".equals(position)) {
                bucket = awd_bucket_us;
                region = region_us;
            } else {
                bucket = awd_bucket_eu;
                region = region_eu;
            }
            String searchChar = "https://" + bucket + ".s3." + region + ".amazonaws.com/";
            key = key.replace(searchChar, "");
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            ResponseInputStream<GetObjectResponse> object = creatS3Client(position).getObject(getObjectRequest);
            String fileName = key.substring(key.lastIndexOf("/")+1,key.length());
            String filePath = folder + "/" + fileName;
            FileUtils.createFile(filePath);
            FileOutputStream out = new FileOutputStream(filePath);
            int count = 0;
            byte[] b = new byte[8 * 1024];
            while( (count=object.read(b)) != -1 )
                out.write(b,0,count);
            object.close();
            out.close();
        } catch (Exception e) {
            log.error("FileStorageUtil#downloadBucketObjects:" + e);
        }
    }

    private static ByteBuffer readFileToByteBuffer(String filepath) {
        try {
            File file = new File(filepath);
            InputStream is= new FileInputStream(file);
            ByteArrayOutputStream out= new ByteArrayOutputStream();
            int count = 0;
            byte[] b = new byte[8 * 1024];
            while( (count=is.read(b)) != -1 )
                out.write(b,0,count);
            is.close();
            return ByteBuffer.wrap(out.toByteArray());
        } catch(Exception e) {
            log.error("FileStorageUtil#readFileToByteBuffer:" + e);
            return null;
        }
    }

    private static ByteBuffer readMultipartFileToByteBuffer(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            ByteArrayOutputStream out= new ByteArrayOutputStream();
            out.write(bytes);
            return ByteBuffer.wrap(out.toByteArray());
        } catch(Exception e) {
            log.error("FileStorageUtil#readMultipartFileToByteBuffer:" + e);
            return null;
        }
    }

    public static void main(String[] args) {
//        List<Bucket> buckets = FileStorageUtil.getBuckets();
//        System.out.println(JSONObject.toJSON(buckets));
//        getBuketsByname("contract47");
//        creatBucket("infinityxupload");
//        deleteBucket("audio472");
//        uploadFile("E:\\work\\data\\Recording File.wav", "20211009/123.wav", "12");
//        creatBucketFolder("data\\audio", "abc");
//        downloadBucketObjects("upload/20210923/33f0ba96-614e-4419-b3da-4836bff9965d.jpg"
//                , "US", tempFilePath + "/" + UUID.randomUUID() + "");
        String a = "https://infinityxupload.s3.us-west-1.amazonaws.com/upload/20211011/US_0016_140.wav";
        System.out.println(a);
        downloadBucketObjects(a, "US", "D:/data/soundrecording_files/0008");
        System.out.println("syo");
    }

}
