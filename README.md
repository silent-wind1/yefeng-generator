# 项目介绍

基于React + SpringBoot + Vert.x响应式编程的**定制化代码生成项目**。



## 项目名称

code craft 代码工艺

制作属于自己的代码生成器

## 项目背景

### 解决的问题

1）代码生成器本身的作用就是生成常见、重复性的代码片段，**解决重复编码、效率低下的问题。**

2）虽然网上有很多代码生成器，但都是别人制作封装好的，很多时候还是**无法满足实际开发的定制化需求**，比如要在每个类上增加特定的注解和注释。（这也是为什么明明有代码生成器，很多开发者还是会抱怨自己的工作总是复制粘贴、编写重复的代码、天天CRUD的原因。如果能够有一个工具帮助开发者快速定制属于自己的代码生成器，那么将进一步提高开发效率。

3）在团队开发中，要生成的代码可能是需要频繁变化和持续更新维护的。如果有一个线上平台来维护多个不同的代码生成器，支持在线编辑和共享生成器，在提高开发效率的同时，将有利于协作共建，打造更高质量的代码生成器。



### 实际应用

举例一些代码生成的实际应用场景，我们将通过本项目进行解决：

1）经常做算法题的Coder，可能需要一套ACM代码输入模板，能够支持多种不同的输入模式，比如单词读取和循环

2）经常开发新项目的Coder，可能需要一套初始化项目模板代码，比如一键生成Controller代码（替换其中的对象）、整合Redis、MySQL依赖等

3）可以制作项目“换皮”工具，支持一键给网络热门项目换皮（比如替换项目的名称、Logo等）





# 需求分析

## 调研

网上有一些代码生成器项目，比如前端[Ant Design Pro](https://pro.ant.design/zh-CN/)中后台项目脚手架，能够让用户交互式地创建指定的项目；后端[MyBatisX](https://baomidou.com/pages/ba5b24/)插件，能够让用户通过界面来创建CRUD重复代码。但这些项目都是开发者提前制作好的代码生成器，然后让Coder**根据他们设置好的规则**生成代码（或者拉取特定位置的代码），生成后的代码通常还要再自己二次修改，不够灵活。

还有很多所谓的代码生成项目，其实本质上是一个现成的项目模板，让你通过编写对应配置文件来使用项目，或者还是基于预设的程序来生成特定代码。比如网上很多知名的开源管理系统。

这和本项目做的事不同，我们的目标是升级一个层次！打造一个帮助Coder快速制作代码生成器的工具，也就是「造轮子的轮子」。

然后再升级一个层次，允许用户像发布应用一样，在平台上发布和管理代码生成器，便于共享和创作！



## 流程

需求分析 => 设计(概要设计、详细设计)=> 技术选型 => 项目初始化 / 引入需要的技术 => Demo编写 => 实际开发(实现业务逻辑) => 测试(单元测试、系统测试)=> 代码提交 / 代码评审 => 部署 => 发布上线



# 项目设计

## 代码生成器的核心原理

**`参数 + 模板文件 => 生成完整代码`**

【🌰栗子】

参数：`author = youyi`

模板文件代码：

```html
--------------
I am ${author}
--------------
```

将参数注入到模板文件中，得到生成的完整代码：

```html
--------------
I am yefeng
--------------
```

如果想要使用这套模板生成其他的代码，只需要修改参数的值即可，而不需要改变模板文件。



## 三个阶段

1）第一阶段：制作属于自己的`本地代码生成器`，是一个**基于命令行的脚手架**，能够根据用户的交互式输入快速生成特定代码。

2）第二阶段：开发`制作代码生成器的工具`。比如我们有一段常用的项目代码，使用该工具，可以快速把项目代码制作为代码生成器，将是提高工作效率的大杀器。

3）第三阶段：开发`在线代码生成器平台`。我们可以在平台上制作发布自己的代码生成器，还可以在线使用别人的代码生成器，甚至可以共享协作。



### 第一阶段 —— 本地代码生成器

目标：开发一个本地（离线）的代码生成器，实现一个简易的Java ACM模板项目的定制化生成器。

#### 业务流程

1）准备用于制作代码生成器的原始代码（比如Java ACM模板项目），用于后续生成

2）开发者基于原始代码，设置参数，编写动态模板

3）制作可交互的命令行工具，支持用户输入参数，得到代码生成器jar包

4）使用者得到代码生成器Jar包，执行程序并输入参数，从而生成完整代码



#### 流程图

![第一阶段.png](/img/第一阶段.png)

#### 实现思路

1）先根据本地项目，扫描文件树，实现同样的静态代码生成

2）根据本地的项目，预设部分动态参数、编写模板文件，能够传入配置对象进行生成

3）制作可交互的命令行工具，接受用户输入的参数，并动态生成代码

4）制作封装代码生成器的Jar包文件，并简化使用命令



#### 关键问题

1）如何根据一套项目文件，完整地生成同样一套项目？

2）如何编写动态模板文件？怎么根据模板和参数生成代码？

3）如何制作命令行工具？如何交互式接受用户的输入？

4）怎么将命令行工具制作为Jar包？怎么简化命令？



### 第二阶段 —— 代码生成器制作工具

目标：开发本地的代码生成器制作工具，能够快速将一个项目制作为可以动态定制部分内容的代码生成器。

#### 业务流程

1）准备用于制作代码生成器的原始代码，用于后续生成

2）开发者基于原始代码，**使用代码生成器制作工具**，来快速设置参数、生成动态模板

3）**使用代码生成器制作工具**，动态生成代码生成器Jar包

4）使用者得到代码生成器Jar包，执行程序并输入参数，从而生成完整代码

相较于第一阶段的业务流程，本阶段完成后可以直接使用代码生成器制作工具来快速将固定的项目代码改造为可以定制的动态模板，并自动生成命令行工具Jar包。



#### 流程图

![第二阶段流程图](/img/第二阶段.png)



#### 实现思路

1）使用独立空间来存储管理要生成的原始文件、动态模板文件等

2）使用配置文件来记录要生成的参数和模板文件信息、自定义配置等**元信息**

3）代码生成器制作工具需要有多种可单独或者组合使用的功能，比如从原始文件中抽取参数、动态生成命令行工具、生成Jar包等



#### 关键问题

1）如何使用配置文件来记录参数和模板文件信息？何种结构？

2）怎么能够提高代码生成器的制作效率？工具应该提供哪种能力？

3）如何从原始文件中抽取参数？有哪些类型的参数？有哪些抽取规则？比如布尔类型参数（是否生成）、字符串类型参数（生成指定的值）等

4）如何动态生成配置类？如何动态生成命令行工具？如何动态生成Jar包？



### 第三阶段 —— 在线代码生成器平台

目标：开发一个在线代码生成器平台，可以理解为代码生成器的**应用市场**。所有人都能发布、使用、甚至是在线制作自己的代码生成器。

#### 业务流程

1）获取用于制作代码生成器的原始代码（本地复制 / 远程拉取）

2）开发者基于原始代码，使用**在线代码生成器制作工具**，来快速制作代码生成器

3）开发者发布代码生成器至平台

4）使用者在平台上搜索代码生成器，支持在线使用或者下载离线Jar包，甚至还可以支持接口调用



#### 流程图

![第三阶段流程图](/img/第三阶段.png)



#### 实现思路

1）使用Web开发框架实现代码生成器信息的CRUD

2）将本地的配置和文件**上云**，存储到数据库、对象存储等云服务

3）通过可视化界面来操作第二阶段的代码生成器制作工具，复用阶段二的成果



#### 关键问题

1）怎么在云上存储管理代码生成器？

2）如何通过前端开发，提高代码生成器的制作效率？

3）如果通过后端优化，提高代码生成器的制作性能？

4）如何保证代码生成器的存储空间不超限、如何优化存储？



## 技术选型

### 前端

- React 开发框架 + 组件库 + 代码编辑器
- 前端工程化：ESLint + Prettier + TypeScript


### 后端

- SpringBoot系
- MySQL + MyBatis
- Java命令行应用开发
- FreeMarker模板引擎
- Vert.x响应式编程
- Caffeine + Redis多级缓存
- 分布式任务调度系统
- 多种设计模式
- 多种系统设计的巧思
- 对象存储



### 项目亮点

- Java命令行应用开发
- FreeMarker模板引擎
- Vert.x响应式编程
- 设计模式（命令模式）
- 系统设计的巧思
- 分布式任务调度系统

> Vert.x框架，并发连接处理能力吊打Spring
>
> [Web Framework Benchmarks](https://www.techempower.com/benchmarks/#section=data-r21&test=composite&hw=ph)