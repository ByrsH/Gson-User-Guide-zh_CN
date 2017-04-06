# [Gson 用户指南](https://github.com/google/gson/blob/master/UserGuide.md#gson-user-guide)

## 概述

Gson 是一个能够用于转换 Java 对象到JSON表达式的 Java库。它当然也可以用于转换一个 JSON 字符串到等价的 Java 对象。

Gson 能够处理任意的 Java对象，包括你没有源代码的现有对象。


## Gson的目标

-  提供简单的使用方式，例如 toString() 和 构造器（工厂方法）用于Java 和 JSON之间的转换。


- 允许已有的不可改变的对象转换为 JSON，或从JSON转换为对象。 


- 允许自定义表示对象



- 支持任意的复合对象


- 生成紧凑的和易读的 JSON 输出



## Gson 性能和可扩展性

这里有一些度量数据，它们是在一个台式机（双处理器，8GB内存、64位的Ubuntu）上一起运行许多其他事物测试得到的。你可以重新运行这些例子，通过 [PerformanceTest](https://github.com/google/gson/blob/master/gson/src/test/java/com/google/gson/metrics/PerformanceTest.java)类。




-  字符串：反序列化超过25MB的字符串没有任何问题（查看类 PerformanceTest 中 disabled_testStringDeserializationPerformance 方法）


- 大集合：

    序列化一个140万个对象的集合（查看类 PerformanceTest 中 disabled_testLargeCollectionSerialization 方法）

    反序列化一个87000个对象的集合（查看类 PerformanceTest 中 disabled_testLargeCollection Deserialization 方法 ）


- Gson 1.4版本提高了反序列化数组和集合字节的上线限制，从80KB提升到了11MB


注意：运行这些测试例子时去掉 disabled_ 前缀。我们使用这个前缀是为了防止每次运行 JUnit 测试时运行这些例子。
           

## Gson 用户

Gson 最初被创建是用于Google内部的一些项目，现在被用于很多开源项目和公司。


## 使用Gson

主要的类是[Gson](https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/Gson.java)，你可以仅仅调用 new Gson() 来创建一个实例。当然也有一个类的 [GsonBuilder](https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/GsonBuilder.java)可用的，它可以设置各种各样的属性来构建一个Gson实例，例如版本控制等等。

当调用json操作时，Gson实例是不维持任何状态的。因此，你可以自由的重复使用同一个对象来操作多个json序列化和反序列化。


### 通过Maven使用Gson

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


### 基本数据类型例子

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


### 对象例子

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

#### 对象操作的细节



- 最好（推荐）使用私有字段


- 不需要使用任何注释去表明一个字段包含序列化和反序列化。当前类（和继承父类）的所有的字段都默认包含


-  如果一个字段被标记为 transient，（默认）它是被忽略的，JSON序列化和反序列化时不包含其中


- 这个实现能正确的处理 null


- 当序列化时，输出将会跳过值为null的字段


- 当反序列化时，JSON结果中没有的字段，对象对应的字段将会设置为null


-  如果一个字段是 synthetic 的，那么它将被忽略，JSON序列化和反序列化不包括它


- 在内部类、匿名类和本地类中对应于外部类的字段是忽略的，并且不包括在序列化和反序列化中



### 嵌套类（包括内部类）

Gson 可以很容易的序列化静态嵌套类。

Gson 同样也可以反序列化静态嵌套类。但是，Gson不能自动的反序列化纯的内部类，由于它们的无参构造器需要一个引用包含反序列化时不可用的对象。你可以通过使内部类静态化或者提供一个实例构造器来解决这个问题。下面有一个例子：

```
public class A { 
  public String a; 

  class B { 

    public String b; 

    public B() {
      // No args constructor for B
    }
  } 
}
```

注意：上面的B类（默认）不能使用Gson序列化。

Gson 不能反序列化 {"b":"abc"} 为一个B的实例，由于类B是一个内部类。如果内部类B是静态类那么Gson将能够反序列化这个字符串。另一个解决方案是写一个类B的实例构造器。

```
public class InstanceCreatorForB implements InstanceCreator<A.B> {
  private final A a;
  public InstanceCreatorForB(A a)  {
    this.a = a;
  }
  public A.B createInstance(Type type) {
    return a.new B();
  }
}
```
上面的代码是可行的，但是不推荐。


### 数组例子

```
Gson gson = new Gson();
int[] ints = {1, 2, 3, 4, 5};
String[] strings = {"abc", "def", "ghi"};

// Serialization
gson.toJson(ints);     // ==> [1,2,3,4,5]
gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

// Deserializationint[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class); 
// ==> ints2 will be same as ints
```

我们将支持多维数组和各种各样的混合元素类型。


### 集合例子

```
Gson gson = new Gson();
Collection<Integer> ints = Lists.immutableList(1,2,3,4,5);

// SerializationString json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]

// DeserializationType collectionType = new TypeToken<Collection<Integer>>(){}.getType();
Collection<Integer> ints2 = gson.fromJson(json, collectionType);
// ==> ints2 is same as ints
```

相当可怕的：注意我们怎样定义集合的类型。不幸的是，我们没有办法不使用这个java方法。


#### 集合限制

Gson 能够序列化任意的对象集合，但是不能反序列化它们。因为使用者没有方法指出结果对象的类型，然而，当反序列化时，集合必须有一个明确的、泛型类型。这个很容易明白，当遵循好的java编码实践时很少出现这个问题。


### 序列化和反序列化泛型类型

当你调用 toJson(obj) 时，Gson会调用 obj.getClass() 方法得到类的字段信息从而序列化。类似的，你也可以通过 Myclass.class 方式得到对象信息，在方法 fromJson(json, MyClass.class) 中使用。这种方法对于非泛型类型的对象很适用。但是，如果这个对象是一个泛型类型，那么泛型类型的信息会丢失，因为 Java 类型擦除。这里有一个例子说明了这一点：

```
class Foo<T> {
  T value;
}
Gson gson = new Gson();
Foo<Bar> foo = new Foo<Bar>();
gson.toJson(foo); // May not serialize foo.value correctly

gson.fromJson(json, foo.getClass()); // Fails to deserialize foo.value as Bar
```

以上代码不能解释值为 Bar 类型，因为Gson调用 list.getClass() 方法得到它的类信息，但是这个方法返回一个原始类：Foo.class 。这意味着Gson没有方法知道这是一个类型为 Foo<Bar> 的对象，而不仅仅是普通的Foo。

你可以通过指定当前参数类型为你的泛型类型来解决这个问题， 可以通过使用 TypeToken 类来实现。

```
Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
gson.toJson(foo, fooType);

gson.fromJson(json, fooType);
```

用于得到 fooType 的方式实际上定义了一个匿名的本地内部类，这个类包含 getType() 方法，该方法返回完全参数化类型。
