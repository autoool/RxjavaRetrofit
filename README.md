# RxjavaDemo 

---
## 项目目录结构
### appclean  module
- 整体参考
      - [https://github.com/googlesamples/android-architecture](todo-clean)
      - https://github.com/android10/Android-CleanArchitecture
- 关于repository层参考
      - 共六篇文章 
      - http://blog.zhaiyifan.cn/2016/03/14/android-new-project-from-0-p1/
- clean 架构
      - 参考
      - https://github.com/android10/Android-CleanArchitecture
      - http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/
          *butterknife 8.0.1 引入后没办法使用 改为7.0.1版本*
        - 根据 https://github.com/googlesamples/android-architecture 里的todo-mvp-clean/ 和 https://github.com/android10/Android-CleanArchitecture.git
          - 写的 mvp rxjava retrofit 结合的demo,里面还包含了部分单元测试
### data 
#### RxJava 与 Retrofit / okhttp 结合 网络请求 
- 参考网址
  - http://gank.io/post/56e80c2c677659311bed9841?hmsr=toutiao.io 
  - http://square.github.io/retrofit/ [Retrofit]
  - http://rxjava.yuxingxin.com/ [RxJava Essentials 中文翻译版]
  - http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/ [Error handling in RxJava]

- 参考例子：
  - https://github.com/tough1985/RxjavaRetrofitDemo

#### okhttp https 双向认证 *没有测试* 啊 ==

    - https://github.com/square/okhttp/wiki/HTTPS
    - http://blog.csdn.net/lmj623565791/article/details/48129405
    - https://github.com/hongyangAndroid/okhttp-utils

#### 用flatmap()解决nested callback/ callback hell

见SplashActivity flatmapCallback 方法
- 参考网址
  - http://www.jianshu.com/p/0f926fda682b

#### 添加数据缓存 todo

需要缓存的数据：
1 网络数据的缓存三级缓存
2 本地全局数据的缓存 ：初始化的数据  用户编辑数据（用于数据恢复）
商品，商品分类， 登陆用户 ，订单，从本地获取  
创建订单失败的数据恢复

#### 使用Mockito 网络请求单元测试

- 参考网址
  - http://www.jianshu.com/p/cdfeb6c3d099

### domain
- 讲presentation层的很多，但是这一层的却很少。

### corelibrary

- 项目使用的公共方法和通用View

---
## 目前存在的问题
- 如何保持rxjava的链式结构
  关于Android-CleanArchitecture的domain层 ，UserCase抽象类很困惑。
  关于UserCase 的困惑：根据这边文章 http://www.jianshu.com/p/f3f0eccbcd6f “不能打断其链式结构”，确实rxjava的链式结构真的是一大优势，但是UserCase的写法我觉得已经违背了链式结构，因为没有办法几个请求使用flapmap链式执行了。
  - 修改RxBaseCase 满足链式结构 16/07/28
- 数据缓存
  - 类似app全局数据，应用启动初始化从服务端获取，以后再使用的话先从内存读，内存如果没有就读取本地文件，如果仍然没有，就从网络端获取。但是就像用户正在编辑的临时数据，本地如果没有的话，网络端更加没有。
- 缓存请求服务
  - 类似离线收银，如果当前没有网络，就把当前需要访问的接口和提交的参数数据保存在本地，在有网络的时候唤醒相关服务提交数据。

---
## 单元测试
- data 层 和 domain 层 单元测试
- view 单元测试
- 参考
  - http://www.chriszou.com/2016/04/13/android-unit-testing-start-from-what.html





