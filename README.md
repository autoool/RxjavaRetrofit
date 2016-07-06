# RxjavaDemo 
##app  module
- 测试rxjava
##data 
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
目前想到的存在两种情况：
 1. 网络接口数据的缓存，从网络获取数据，如果数据相同不会变化。本地有缓存从本地拿，不然就从网络重新获取并缓存。
 2. 另一种是从网络接口获取数据后，需要一直缓存到本地的固定数据。比如应用初始化的用户列表等数据，一次初始化，以后用到的时候，直接从本地拿。如果没有或者失败，只能重新登陆重新初始化，不会再去调用接口获取。

##domain
- 讲presentation层的很多，但是这一层的却很少。
##presentation 
-     clean 架构
      - 参考
      - https://github.com/android10/Android-CleanArchitecture
        - http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/
          *butterknife 8.0.1 引入后没办法使用 改为7.0.1版本*
## dblibrary 
- 使用greendao对数据进行存储
##corelibrary
- 项目使用的公共方法和通用View
## appclean 
- 整体参考
  - https://github.com/googlesamples/android-architecture[] todo-mvp-clean]
- 关于repository层参考
  - 共六篇文章 
  - http://blog.zhaiyifan.cn/2016/03/14/android-new-project-from-0-p1/

# 单元测试
- 参考
  - http://www.chriszou.com/2016/04/13/android-unit-testing-start-from-what.html

