import Footer from '@/components/Footer';
import {userRegisterUsingPost} from '@/services/backend/userController';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {LoginForm, ProFormText} from '@ant-design/pro-components';
import {useEmotionCss} from '@ant-design/use-emotion-css';
import {Helmet, history, Link} from '@umijs/max';
import {message, Tabs} from 'antd';
import React, {useState} from 'react';
import Settings from '../../../../config/defaultSettings';

const UserRegisterPage: React.FC = () => {
  const [type, setType] = useState<string>('account');
  const containerClassName = useEmotionCss(() => {
    return {
      display: 'flex',
      flexDirection: 'column',
      height: '100vh',
      overflow: 'auto',
      backgroundImage:
        "url('https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/V-_oS6r-i7wAAAAAAAAAAAAAFl94AQBr')",
      backgroundSize: '100% 100%',
    };
  });

  const handleSubmit = async (values: API.UserLoginRequest) => {
    try {
      // 注册
      await userRegisterUsingPost({
        ...values,
      });

      const defaultLoginSuccessMessage = '注册成功！';
      message.success(defaultLoginSuccessMessage);
      history.push('/user/login');
      return;
    } catch (error: any) {
      const defaultLoginFailureMessage = `注册失败，${error.message}`;
      message.error(defaultLoginFailureMessage);
    }
  };

  return (
    <div className={containerClassName}>
      <Helmet>
        <title>
          {'注册'}- {Settings.title}
        </title>
      </Helmet>
      <div
        style={{
          flex: '1',
          padding: '32px 0',
        }}
      >
        <LoginForm
          contentStyle={{
            minWidth: 280,
            maxWidth: '75vw',
          }}
          logo={<img alt="logo" style={{ height: '100%' }} src="/logo.png" />}
          title="CodeGenx"
          subTitle={'快速帮助你生成后端代码'}
          initialValues={{
            autoLogin: true,
          }}
          submitter={{
            searchConfig: {
              submitText: '注册',
            },
          }}
          onFinish={async (values) => {
            await handleSubmit(values as API.UserLoginRequest);
          }}
        >
          <Tabs
            activeKey={type}
            onChange={setType}
            centered
            items={[
              {
                key: 'account',
                label: '新用户注册',
              },
            ]}
          />
          {type === 'account' && (
            <>
              <ProFormText
                name="userAccount"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined />,
                }}
                placeholder={'请输入账号'}
                rules={[
                  {
                    required: true,
                    message: '账号是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined />,
                }}
                placeholder={'请输入密码'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="checkPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined />,
                }}
                placeholder={'请再次确认密码'}
                rules={[
                  {
                    required: true,
                    message: '确认密码是必填项！',
                  },
                ]}
              />
            </>
          )}

          <div
            style={{
              marginBottom: 24,
              textAlign: 'right',
            }}
          >
            <Link to="/user/login">已有账号，请登录</Link>
          </div>
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default UserRegisterPage;
