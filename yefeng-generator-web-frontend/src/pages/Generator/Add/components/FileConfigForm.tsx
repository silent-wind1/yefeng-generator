import { CloseOutlined } from '@ant-design/icons';
import { Alert, Button, Card, Form, FormListFieldData, Input, Select, Space } from 'antd';

interface Props {
  formRef: any;
  oldData: any;
}

export default (props: Props) => {
  const { formRef, oldData } = props;

  /**
   * 单个文件表单视图
   * @param field
   * @param remove
   */
  const singleFieldFormView = (
    field: FormListFieldData,
    remove?: (index: number | number[]) => void,
  ) => {
    return (
      <Space key={field.key}>
        <Form.Item label="输入路径" name={[field.name, 'inputPath']}>
          <Input />
        </Form.Item>
        <Form.Item label="输出路径" name={[field.name, 'outputPath']}>
          <Input />
        </Form.Item>
        <Form.Item label="类型" name={[field.name, 'type']}>
          <Select
            style={{
              minWidth: 80,
            }}
            options={[
              { value: 'file', label: '文件' },
              { value: 'dir', label: '目录' },
            ]}
          />
        </Form.Item>
        <Form.Item label="生成类型" name={[field.name, 'generateType']}>
          <Select
            style={{
              minWidth: 80,
            }}
            options={[
              { value: 'static', label: '静态' },
              { value: 'dynamic', label: '动态' },
            ]}
          />
        </Form.Item>
        <Form.Item label="条件" name={[field.name, 'condition']}>
          <Input />
        </Form.Item>
        {remove && (
          <Button type="text" danger onClick={() => remove(field.name)}>
            删除
          </Button>
        )}
      </Space>
    );
  };

  return (
    <>
      <Alert message="如果不需要使用在线制作功能，可不填写" type="warning" closable />
      <div style={{ marginBottom: 16 }} />
      <Form.List name={['fileConfig', 'files']}>
        {(fields, { add, remove }) => {
          return (
            <div style={{ display: 'flex', rowGap: 16, flexDirection: 'column' }}>
              {fields.map((field) => {
                const fileConfig =
                  formRef?.current?.getFieldsValue()?.fileConfig ?? oldData?.fileConfig;
                const groupKey = fileConfig.files?.[field.name]?.groupKey;

                return (
                  <Card
                    size="small"
                    title={groupKey ? '分组' : '未分组文件'}
                    key={field.key}
                    extra={
                      <CloseOutlined
                        onClick={() => {
                          remove(field.name);
                        }}
                      />
                    }
                  >
                    {groupKey ? (
                      <Space>
                        <Form.Item label="分组key" name={[field.name, 'groupKey']}>
                          <Input />
                        </Form.Item>
                        <Form.Item label="组名" name={[field.name, 'groupName']}>
                          <Input />
                        </Form.Item>
                        <Form.Item label="条件" name={[field.name, 'condition']}>
                          <Input />
                        </Form.Item>
                      </Space>
                    ) : (
                      singleFieldFormView(field)
                    )}

                    {/* 组内文件 */}
                    {groupKey && (
                      <Form.Item label="组内文件">
                        <Form.List name={[field.name, 'files']}>
                          {(subFields, subOpt) => (
                            <div
                              style={{
                                display: 'flex',
                                flexDirection: 'column',
                                rowGap: 16,
                              }}
                            >
                              {subFields.map((subField) =>
                                singleFieldFormView(subField, subOpt.remove),
                              )}
                              <Button type="dashed" onClick={() => subOpt.add()} block>
                                添加组内文件
                              </Button>
                            </div>
                          )}
                        </Form.List>
                      </Form.Item>
                    )}
                  </Card>
                );
              })}
              <Button type="dashed" onClick={() => add()}>
                添加文件
              </Button>
              <Button
                type="dashed"
                onClick={() =>
                  add({
                    groupName: '分组',
                    groupKey: 'group',
                    type: 'group',
                  })
                }
              >
                添加分组
              </Button>
              <div style={{ marginBottom: 16 }} />
            </div>
          );
        }}
      </Form.List>
    </>
  );
};
