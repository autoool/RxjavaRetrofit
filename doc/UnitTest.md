## 单元测试实践

http://www.infoq.com/cn/articles/mogujie-android-unit-testing

> 这里举一个例子来说明一下，顺便澄清一个十分常见的误解。比如说有一个Activity，管他叫DataActivity，它有一个public void loadData()方法, 会去调用底层的DataModel类，异步的执行一些网络请求。当网络请求返回以后，更新用户界面。
> 这里的loadData()方法是void的，它该怎么测试呢？一个最直接的反应可能是，调用loadData()方法(当然，实际可能是通过其他事件触发)，然后一段时间后，验证界面得到了更新。然而这种方法是错的，这种测试叫集成测试，而不是单元测试。因为它涉及到很多个方面，它涉及到DataModel、网络服务器，以及网络返回正确时，DataActivity内部的处理，等等。集成测试固然有它的必要性，但不是我们应该最关注的地方，也不是最有价值的地方
> 正确的单元测试方法，应该是去验证loadData()方法调用了DataModel的某个请求数据的方法，同时传递的参数是正确的。“调用了DataModel的方法，同时参数是。。。” 这个才是loadData()这个方法的“返回结果”。

http://www.jianshu.com/p/00ab03f3d394

### mvp+CleanArchitecture如何写单元测试
究竟测试什么：
http://chriszou.com/2016/04/25/android-unit-testing-wechat-group-share.html
所有的Model、Presenter/ViewModel、Api、Utils等类的public方法
Data类除了getter、setter、toString、hashCode等一般自动生成的方法之外的逻辑部分
自定义View的功能：比如set data以后，text有没有显示出来等等，简单的交互，比如click事件，负责的交互一般不测，比如touch、滑动事件等等。
Activity的主要功能：比如view是不是存在、显示数据、错误信息、简单的点击事件等。
*比较复杂的用户交互比如onTouch，以及view的样式、位置等等可以不测。因为不好测*

#### data 层
Context 使用RuntimeEnvironment.application 替换
- 网络层api获取数据
- 本地缓存数据
- 数据库保存数据
#### domain层 隔离data和view层
- ​
#### view层
- presenter
- activity(fragment)

如果LoginActivity没有用Fragment，也没有用dagger2，像下面的代码，怎么把mock的presenter替换到activity里呢？
```
class LoginActivity{
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
presenter = new LoginPresenter();
}
}
login 成功 失败 Observable
```
方法1：
加一个setter方法
方法2：（采用）
或者把presenter的scope变成package，test 里面直接.presenter = Mock的presenter
这种情况下，LoginActivityTest的package name要跟LoginActivity一致


### junit

- 纯java单元测试
### Mock以及Mockito
- 模拟对象 没有ui，纯java 模拟对象
- 能够模拟出无关的依赖模块、行为，并能够验证调用顺序
### Robolectric 单元测试
http://robolectric.org/contributing/
Robolectric is a unit test framework
它的目标是你能够用单元测试来测试一些Android相关的代码
模拟执行控件的点击事件，测试同步的activity跳转
### Espresso UI测试
https://segmentfault.com/a/1190000004355178  七篇文章
https://developer.android.com/training/testing/ui-testing/espresso-testing.html
- Using Espresso with ActivityTestRule
- Using Espresso with ActivityInstrumentationTestCase2  不建议
- Google官方提供的Android UI自动化测试的框架. **适用于app内的功能性UI测试**。
- 和Robolectric 一样，为了测试ui
- Espresso 测试框架提供了一系列的API用于构建UI**测试来测试app内用户流操作**。这些API让你可以编写简洁可靠的自动化UI测试。Espresso非常适合用来编写白盒测试，其中测试代码的编写是利用了被测试app中程序代码实现细节。
- service
- intent
- webview
- data adapter
### UI Automator：
- UI测试框架，适用于系统和安装app间跨app的功能性UI测试
### ActivityInstrumentationTestCase2 
- 可以和AndroidJUnitRunner 一起使用
- 弃用

```
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.ActivityInstrumentationTestCase2;

@RunWith(AndroidJUnit4.class)
public class CalculatorInstrumentationTest
        extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    @Before
    public void setUp() throws Exception {
        super.setUp();

        // 当你使用`AndroidJUnitRunner`运行测试时，
        // 注入Instrumentation实例是必要的。
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void typeOperandsAndPerformAddOperation() {
        // 调用CalculatorActivity add()方法并传入一些操作数据，
        // 然后检查返回值是否是期望值
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
```
### AndroidJUnitRunner （AndroidJUnit4）
- AndroidJUnitRunner 类是一个 JUnit 测试runner，它可以让你在Android设备上运行JUnit 3或者JUnit 4-style的测试类，包括使用了 Espresso 和 UI Automator 测试框架的。test runner处理的事情有 加载你的测试package和需要在设备上测试的app，运行你的测试，还有报告测试结果。这个类会替换掉只支持JUnit 3测试的InstrumentationTestRunner类
- 这个test runner的关键特性包括：
  - JUnit的支持
  - 访问instrumentation信息
  - 测试过滤
  - 测试拆分

###  参考网址
[http://www.cnblogs.com/tiantianbyconan/p/5048524.html]

## 目录结构
- test  单元测试   测试流程
- androidtest  测试android api



## 针对android的单元测试
### 使用espresso  AndroidJUnit4  ui

测试ui是否按照预期的显示，比如说点击了按钮，view发生了改变，可以测试view是否按照预期变化。

-  dataadapter  
-  service
-  webview
### 使用junit  MockitoJUnitRunner AndroidJUnit4
- sharepreferences
- ​

## JaCoco 分析单元代码测试覆盖率
### 参考文章（看到生成的覆盖率报告，笑了。）
- Android 使用 JaCoco 分析单元代码测试覆盖率 [http://www.jianshu.com/p/959716683c53?utm_source=tuicool&utm_medium=referral]
- Code coverage on Android with JaCoCo  [https://blog.gouline.net/code-coverage-on-android-with-jacoco-92ec90c9355e#.lvmqh8rgm]
- Code coverage on Android with JaCoCo github [https://github.com/mgouline/android-samples]
  其中第二个网址：
   To generate a report, run gradle testBlueDebugUnitTestCoverage and you will see them in “build/reports/jacoco/testBlueDebugUnitTestCoverage/”.
   gradle test + productFlavors (首字母大写) + DebugUnitTestCoverage




