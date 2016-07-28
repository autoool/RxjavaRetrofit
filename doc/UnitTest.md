## 使用测试
### junit
- 纯java单元测试
### Mock以及Mockito
- 模拟对象 没有ui，纯java 模拟对象
- 能够模拟出无关的依赖模块、行为，并能够验证调用顺序
### Robolectric 单元测试
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
- 

