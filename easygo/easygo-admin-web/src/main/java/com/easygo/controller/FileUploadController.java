package com.easygo.controller;

import com.easygo.utils.FastDFSClient;
import com.easygo.utils.MessageResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @CreateTime: 2020-03-31 16:06
 * @Description: 文件上传的控制器
 */
@Controller
@Scope("prototype")
public class FileUploadController {

    //图片服务器的地址在配置文件中
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/file_upload")
    @ResponseBody
    public MessageResults uploadImg(MultipartFile file) {
        //文件上传的逻辑代码写在这个位置：
        //1.获取上传的文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("上传的原始文件名是:" + originalFilename);
        //2.获取上传的文件的后缀名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println("文件的扩展名是:" + extName);
        try {
            //获取工具类,配置文件
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //上传文件,返回上传成功的地址
            // /group1/M00/00/00/rBEAD16CveSAC_wmAAA7XBQ3J9g389.png
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            //还需要拼接一下全地址
            //http://122.51.50.249:8080/group1/M00/00/00/rBEAD16CveSAC_wmAAA7XBQ3J9g389.png
            String fullPath = FILE_SERVER_URL + url;
            System.out.println("文件上传成功之后的全地址是:" + fullPath);
            return new MessageResults(200, fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MessageResults(500, null);
    }
}
