All of the source code to this product is available under agpl-v3 Copyright© 2023 谈翰文

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
License as published by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

# Trinity

#### 介绍
一个使用SpringBoot制作的微服务化的博客、视频网站、购物网站三合一系统。
同时使用rabbitMq构建了一个简单的聊天室功能DEMO。

注意！本项目代码使用AGPL V3协议开源，如若修改本项目请继续保持开源。

如果你觉得这个DEMO还不错，欢迎加我QQ（2183720260）。

当然，如果愿意，可以请我喝杯咖啡，非常感谢。
![咖啡](11dce27819c59ed7c0114e26ae53510.jpg)

#### 开发使用技术
##### 前端技术
Bootstrap	
Semantic UI	
editormd	
Artplayer	
tocbot	
Typo.css	
Prism	
Hui	
FontAwesome文字图标	
animate.css	
##### 后端技术	
Mybatis 架构	
MariaDB 数据库	
IBM Semeru Runtimes	
Redis 缓存	
RabbitMQ 消息队列	
Apache Shiro 安全框架	
支付宝沙箱	
Druid 数据源	
PageHelper 分页系统	
Lombok	
Alibaba Fastjson	
Commonmark 解析器	
Apache Maven 架构	
Thymeleaf 模板	
SpringBoot框架	

本项目参考自[简而美的个人博客系统](https://gitee.com/dreamchasers/myblog)
##### 注册界面
![注册界面](%E5%9B%BE%E7%89%87.png)

使用Shiro安全框架对密码进行加密处理。首先，用随机字符串混淆用户名，然后用SHA-256算法对混淆后的用户名进行七次加密，以保证密码的高度安全。

##### 登录界面
![登录界面](%E5%9B%BE%E7%89%872.png)
##### 博客界面
![博客界面](%E5%9B%BE%E7%89%873.png)
##### 文章界面
![文章界面](%E5%9B%BE%E7%89%8711.png)

支持插入图片，视频，同时可使用MARKDOWN。

##### 视频播放
![视频播放](%E5%9B%BE%E7%89%874.png)

该模块基于Artplay前端插件实现，支持MP4、OGG、WEBM格式视频的播放，同时提供视频的播放速度调整、视频截图、视频画面镜像反转的可选功能。同时在视频播放页中提供了视频的下载功能。

##### 商品购买
![商品购买](%E5%9B%BE%E7%89%875.png)

该模块基于支付宝沙箱构成，在商品详情页面，用户可以选择商品的数量并将其加入购物车，之后将跳转至购物车页面，在此页面中用户可以选择要结账的商品进行结账，点击结账按钮后，前端会将用户选择的商品计入商品订单总表，统计其总计金额并将其传入支付宝沙箱提供的支付api，之后将跳转至支付宝沙箱的支付页面。

##### 评论区
![评论区](%E5%9B%BE%E7%89%8712.png)
##### 聊天室
![聊天室](%E5%9B%BE%E7%89%876.png)
##### 购物车
![购物车](%E5%9B%BE%E7%89%877.png)
##### 订单管理
![订单管理](%E5%9B%BE%E7%89%8713.png)
##### 视频管理
![视频管理](%E5%9B%BE%E7%89%8710.png)
##### 文章管理
![文章管理](%E5%9B%BE%E7%89%879.png)

其余说明将会在稍后补充
