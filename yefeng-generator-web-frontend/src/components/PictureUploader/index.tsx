import {uploadFileUsingPost} from '@/services/backend/fileController';
import {PlusOutlined} from '@ant-design/icons';
import {message, Upload, UploadProps} from 'antd';
import React, {useState} from 'react';
import {COS_HOST} from "@/constants";

interface Props {
  biz: string;
  onChange?: (url: string) => void;
  value?: string;
}

/**
 * 图片上传组件
 * @constructor
 */
const PictureUploader: React.FC<Props> = (props) => {
  const { biz, value, onChange } = props;
  const [loading, setLoading] = useState(false);

  const uploadProps: UploadProps = {
    name: 'file',
    multiple: false,
    maxCount: 1,
    listType: 'picture-card',
    showUploadList: false,
    disabled: loading,
    customRequest: async (fileObj: any) => {
      setLoading(true);
      try {
        const res = await uploadFileUsingPost(
          {
            biz,
          },
          {},
          fileObj.file,
        );
        const fullPath =  res.data;
        onChange?.(fullPath ?? '');
        fileObj.onSuccess(res.data);
      } catch (e: any) {
        message.error('上传失败，' + e.message);
        fileObj.onError(e);
      }
      setLoading(false);
    },
  };

  const uploadButton = (
    <button style={{ border: 0, background: 'none' }} type="button">
      <PlusOutlined />
      <div style={{ marginTop: 8 }}>上传图片</div>
    </button>
  );

  return (
    <Upload {...uploadProps}>
      {value ? <img src={value} alt="picture" style={{ width: '100%' }} /> : uploadButton}
    </Upload>
  );
};

export default PictureUploader;
