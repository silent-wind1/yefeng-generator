import {GithubOutlined} from '@ant-design/icons';
import {DefaultFooter} from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = 'yefeng';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> 叶枫
            </>
          ),
          href: 'https://github.com/silent-wind1/',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
