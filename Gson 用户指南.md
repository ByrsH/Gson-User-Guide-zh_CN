# [Gson 用户指南](https://github.com/google/gson/blob/master/UserGuide.md#gson-user-guide)

##概述

Gson 是一个能够用于转换 Java 对象到JSON表达式的 Java库。它当然也可以用于转换一个 JSON 字符串到等价的 Java 对象。

Gson 能够处理任意的 Java对象，包括你没有源代码的现有对象。


##Gson的目标


	* 提供简单的使用方式，例如 toString() 和 构造器（工厂方法）用于Java 和 JSON之间的转换。
	* 允许已有的不可改变的对象转换为 JSON，或从JSON转换为对象。
	* 
允许自定义表示对象

	* 支持任意的复合对象
	* 生成紧凑的和易读的 JSON 输出



##Gson 性能和可扩展性

这里有一些度量数据，它们是在一个台式机（双处理器，8GB内存、64位的Ubuntu）上一起运行许多其他事物测试得到的。你可以重新运行这些例子，通过 [PerformanceTest] （https://github.com/google/gson/blob/master/gson/src/test/java/com/google/gson/metrics/PerformanceTest.java）类。


	* 字符串：反序列化超过25MB的字符串没有任何问题（查看类 PerformanceTest 中 disabled_testStringDeserializationPerformance 方法）
	* 大集合：

             序列化一个140万个对象的集合（查看类 PerformanceTest 中 disabled_testLargeCollectionSerialization 方法）
             反序列化一个87000个对象的集合（查看类 PerformanceTest 中 disabled_testLargeCollection Deserialization 方法 ）

	* Gson 1.4版本提高了反序列化数组和集合字节的上线限制，从80KB提升到了11MB


注意：运行这些测试例子时去掉 disabled_ 前缀。我们使用这个前缀是为了防止每次运行 JUnit 测试时运行这些例子。
           

##Gson 用户

Gson 最初被创建是用于Google内部的一些项目，现在被用于很多开源项目和公司。


##使用Gson

主要的类是[Gson]（https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/Gson.java），你可以仅仅调用 new Gson() 来创建一个实例。当然也有一个类的 [GsonBuilder]（https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/GsonBuilder.java）可用的，它可以设置各种各样的属性来构建一个Gson实例，例如版本控制等等。

当调用json操作时，Gson实例是不维持任何状态的。因此，你可以自由的重复使用同一个对象来操作多个json序列化和反序列化。


###通过Maven使用Gson

对应在Maven2/3下使用Gson，你可以使用Gson可用的版本，通过在Maven文件中添加以下依赖：

```
<dependencies>
    <!--  Gson: Java to Json conversion -->
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.8.0</version>
      <scope>compile</scope>
    </dependency>
</dependencies>
```
就是这样，现在你的maven项目可以使用Gson了。


###基本数据类型例子

```
// SerializationGson gson = new Gson();
gson.toJson(1);            // ==> 1
gson.toJson("abcd");       // ==> "abcd"
gson.toJson(new Long(10)); // ==> 10int[] values = { 1 };
gson.toJson(values);       // ==> [1]

// Deserializationint one = gson.fromJson("1", int.class);
Integer one = gson.fromJson("1", Integer.class);
Long one = gson.fromJson("1", Long.class);
Boolean false = gson.fromJson("false", Boolean.class);
String str = gson.fromJson("\"abc\"", String.class);
String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
```


###对象例子

```
class BagOfPrimitives {
  private int value1 = 1;
  private String value2 = "abc";
  private transient int value3 = 3;
  BagOfPrimitives() {
    // no-args constructor
  }
}


// SerializationBagOfPrimitives obj = new BagOfPrimitives();
Gson gson = new Gson();
String json = gson.toJson(obj); 

// ==> json is {"value1":1,"value2":"abc"}
```

注意不能序列化循环引用的对象，这样会造成无限递归。

```
// DeserializationBagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
// ==> obj2 is just like obj
```
