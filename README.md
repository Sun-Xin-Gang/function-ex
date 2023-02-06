# function-ex ( Chinese )
Function throws Exception with Java 8
## 简介
这是一套对 java.util.function.* 函数式接口的扩展库，它们都默认抛出了 Exception，用来更简洁地构建你的代码。例如：
``` java
    void persist(Object o) throws SQLException {
        // into db
    }
```
当你对实体集合遍历时，
``` java
    list.forEach(this::persist); // compile error, checked exception
```
你必须，
``` java
    // with Java 8
    list.forEach(o -> {
        try {
            persist(o);
        } catch (SQLException e) {
            // do nothing
        }
    });
```
你可以利用本项目简化代码，
``` java
    // with function-ex
    list.forEach( ConsumerEx.without(this::persist) );
    // default throw a CheckedRuntimeException when SQLException occurs
```
当你希望和上面 Java 8 示例代码等价，捕捉 SQLException 并 do nothing 时，也可以，
``` java
@ExceptionCatcher(value = SQLException.class, consumer = IgnoreAndReturn.class)
public class Test { }

    // with function-ex
    list.forEach( ConsumerEx.without(this::persist, Test.class) );
```
当你希望对不同的 Exception，赋予不同的处理时，还可以，
``` java
@ExceptionCatcher(value = SQLException.class, consumer = IgnoreAndReturn.class)
@ExceptionCatcher(value = IOException.class, consumer = ToRuntimeException.class, to = YourRuntimeException.class)
@ExceptionCatcher(value = CheckedException.class, consumer = ToRuntimeException.class) // default
public class Test { }

    // with function-ex
    list.forEach( ConsumerEx.without(this::persist, Test.class) );
```
定制你自己的检验异常处理过程。

## 结构
| Java 8     |  function-ex |
|------------|-------------:|
| Runnable   |   RunnableEx |
| Function   |   FunctionEx |
| Consumer   |   ConsumerEx |
| Predicate  |  PredicateEx |
| Supplier   |   SupplierEx |
| BiXxx      |      BiXxxEx |
| BooleanXxx | BooleanXxxEx |
| IntXxx     |     IntXxxEx |
| LongXxx    |    LongXxxEx |
| DoubleXxx  |  DoubleXxxEx |
| ObjXxx     |     ObjXxxEx |
| UnaryXxx   |   UnaryXxxEx |
| BinaryXxx  |  BinaryXxxEx |
| ToXxx      |      ToXxxEx |

java.util.function.* (43) + java.lang.Runnable (1)， 
对应为带检验异常版本 pers.sunrise.util.function.* 一共 44 个 Ex 函数式接口，
用于简化你的代码。