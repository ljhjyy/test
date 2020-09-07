package com.easygo.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @BelongsProject: MyFastDFS
 * @BelongsPackage: com.bruceliu.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-03-31 14:50
 * @Description: FastDfs的封装工具类 Utils
 * StringUtils 字符串处理工具类
 * DateUtils  日期处理工具类
 */
public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名，不包含（.）
     * @param metas    文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    /**
     * 02-文件下载
     * @param filePath 文件地址
     * @param savePath 本地保存地址
     */
    public void download(String filePath, String savePath,String groupName) {
        try {
            byte[] bytes = storageClient.download_file(groupName,filePath);
            FileOutputStream fos = new FileOutputStream(savePath);
            fos.write(bytes,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 03-文件删除
     *
     * @param filePath 文件的地址
     * @return
     */
    public Boolean deleteFile(String filePath,String groupName) {
        try {
            int i = storageClient.delete_file(groupName,filePath);
            return i == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 04-获取文件信息
     * @param filePath 文件的地址
     * @return
     */
    public String getFileInfo(String filePath,String groupName) {
        try {
            FileInfo fileInfo = storageClient.get_file_info(groupName,filePath);
            String sourceIpAddr = fileInfo.getSourceIpAddr();//文件IP地址
            long fileSize = fileInfo.getFileSize();//文件大小
            Date createTimestamp = fileInfo.getCreateTimestamp();//文件创建时间
            long crc32 = fileInfo.getCrc32();//错误码
            System.out.println(sourceIpAddr);
            System.out.println(createTimestamp);
            System.out.println(crc32);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
