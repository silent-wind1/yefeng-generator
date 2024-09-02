package com.yefeng.manager;

import cn.hutool.core.collection.CollUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.yefeng.config.CosClientConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cos 对象存储操作
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;

    private TransferManager transferManager;

    // bean加载完后执行
    @PostConstruct
    public void init() {
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        transferManager = new TransferManager(cosClient, threadPool);
    }

    /**
     * 上传对象
     *
     * @param key           唯一键
     * @param localFilePath 本地文件路径
     * @return
     */
    public PutObjectResult putObject(String key, String localFilePath) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, new File(localFilePath));
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 上传对象
     *
     * @param key  唯一键
     * @param file 文件
     * @return
     */
    public PutObjectResult putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 分块上传
     * @param key 对象键(Key)是对象在存储桶中的唯一标识。
     * @param uploadId uploadId 是一次分块上传任务的唯一标识，通过初始化分块上传或者查询分块上传任务获得
     * @return
     */
    public UploadPartResult uploadPart(String key, String uploadId) {
        // 每个分块上传之后都会得到一个返回值 etag，保存起来用于最后合并分块时使用
        List<PartETag> partETags = new LinkedList<>();
        // 上传数据, 这里上传 10 个 1M 的分块数据
        for (int i = 1; i <= 10; i++) {
            // 这里创建一个 ByteArrayInputStream 来作为示例，实际中这里应该是您要上传的 InputStream 类型的流
            int inputStreamLength = 1024 * 1024;
            byte[] data = new byte[inputStreamLength];
            InputStream inputStream = new ByteArrayInputStream(data);

            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(cosClientConfig.getBucket());
            uploadPartRequest.setKey(key);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(inputStream);
            // 设置分块的长度
            uploadPartRequest.setPartSize(data.length);
            // 设置要上传的分块编号，从 1 开始
            uploadPartRequest.setPartNumber(i);

            UploadPartResult uploadPartResult = cosClient.uploadPart(uploadPartRequest);
            PartETag partETag = uploadPartResult.getPartETag();
            partETags.add(partETag);
        }

        System.out.println(partETags);
        return null;
    }
    /**
     * 下载对象
     *
     * @param key 唯一键
     * @return 下载结果
     */
    public COSObject getObject(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        return cosClient.getObject(getObjectRequest);
    }

    /**
     * 下载对象到本地文件
     *
     * @param key           唯一键
     * @param localFilePath 本地文件路径
     * @return 下载文件
     * @throws InterruptedException
     */
    public Download download(String key, String localFilePath) throws InterruptedException {
        File downloadFile = new File(localFilePath);
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        Download download = transferManager.download(getObjectRequest, downloadFile);
        download.waitForCompletion();
        return download;
    }

    /**
     * 删除对象
     *
     * @param key
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteObject(String key) throws CosClientException, CosServiceException {
        cosClient.deleteObject(cosClientConfig.getBucket(), key);
    }

    /**
     * 批量删除对象
     *
     * @param keyList
     * @return
     * @throws MultiObjectDeleteException
     * @throws CosClientException
     * @throws CosServiceException
     */
    public DeleteObjectsResult deleteObjects(List<String> keyList) throws MultiObjectDeleteException, CosClientException, CosServiceException {
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(cosClientConfig.getBucket());
        // 设置要删除的key列表, 最多一次删除1000个
        ArrayList<DeleteObjectsRequest.KeyVersion> keyVersions = new ArrayList<>();
        // 传入要删除的文件名
        // 注意文件名不允许以正斜线/或者反斜线\开头，例如：
        // 存储桶目录下有a/b/c.txt文件，如果要删除，只能是 keyList.add(new KeyVersion("a/b/c.txt")), 若使用 keyList.add(new KeyVersion("/a/b/c.txt"))会导致删除不成功
        for (String key : keyList) {
            keyVersions.add(new DeleteObjectsRequest.KeyVersion(key));
        }
        deleteObjectsRequest.setKeys(keyVersions);
        return cosClient.deleteObjects(deleteObjectsRequest);
    }

    /**
     * 删除目录
     *
     * @param delPrefix
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteDir(String delPrefix) throws CosClientException, CosServiceException {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置 bucket 名称
        listObjectsRequest.setBucketName(cosClientConfig.getBucket());
        // prefix 表示列出的对象名以 prefix 为前缀
        // 这里填要列出的目录的相对 bucket 的路径
        listObjectsRequest.setPrefix(delPrefix);
        // 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        // 保存每次列出的结果
        ObjectListing objectListing = null;
        do {
            objectListing = cosClient.listObjects(listObjectsRequest);
            // 这里保存列出的对象列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            if (CollUtil.isEmpty(cosObjectSummaries)) {
                break;
            }

            ArrayList<DeleteObjectsRequest.KeyVersion> delObjects = new ArrayList<>();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                delObjects.add(new DeleteObjectsRequest.KeyVersion(cosObjectSummary.getKey()));
            }

            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(cosClientConfig.getBucket());
            deleteObjectsRequest.setKeys(delObjects);
            cosClient.deleteObjects(deleteObjectsRequest);

            // 标记下一次开始的位置
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
    }
}
