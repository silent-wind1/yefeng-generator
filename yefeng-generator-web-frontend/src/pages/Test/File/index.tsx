import {COS_HOST} from '@/constants';
import {
  testDownloadFileUsingGet,
  testUploadFileUsingPost,
  uploadFileUsingPost
} from '@/services/backend/fileController';
import {InboxOutlined} from '@ant-design/icons';
import {PageContainer} from '@ant-design/pro-components';
import {Button, Card, Divider, Flex, message, Upload, UploadProps} from 'antd';
import {saveAs} from 'file-saver';
import React, {useState} from 'react';

const {Dragger} = Upload;

const TestFilePage: React.FC = () => {
  let [value, setValue] = useState<string>();
  if (value === undefined) {
    value = ""
  }

  const props: UploadProps = {
    name: 'file',
    multiple: false,
    maxCount: 1,
    customRequest: async (fileObj: any) => {
      try {
        const res = await testUploadFileUsingPost({}, fileObj.file);
        fileObj.onSuccess(res.data);
        setValue(res.data);
      } catch (e: any) {
        message.error('上传失败' + e.message);
        fileObj.onError(e);
      }
    },
    onRemove: () => {
      setValue(undefined);
    },
  };
  return (
    <PageContainer>
      <Flex gap={16} justify="center">
        <Card title="文件上传">
          <Dragger {...props}>
            <p className="ant-upload-drag-icon">
              <InboxOutlined/>
            </p>
            <p className="ant-upload-text">选择你要上传的文件</p>
            <p className="ant-upload-hint">
              上传的文件不要太大哦，控制在2MB以内，太大会把人家弄坏的！弄坏人家，人家就没办法给你服务了
            </p>
          </Dragger>
        </Card>
        <Card title="文件下载">
          文件地址: {COS_HOST + value}
          <Divider/>
          <Flex justify="center">
            <img src={COS_HOST + value} alt="" height={200}/>
          </Flex>
          <Divider/>
          <Button
            onClick={async () => {
              const blob = await testDownloadFileUsingGet(
                {
                  filepath: value,
                },
                {
                  responseType: 'blob',
                },
              );
              const fullPath = COS_HOST + value;
              saveAs(blob, fullPath.substring(fullPath.lastIndexOf('/') + 1));
            }}
          >
            点击下载文件
          </Button>
        </Card>
      </Flex>
    </PageContainer>
  );
};
export default TestFilePage;
