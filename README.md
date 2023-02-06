# function-ex
Function throws Exception with Java 8
## 简介 ( Chinese )
这是一套对 java.util.function.* 函数式接口的扩展库，它们都默认抛出了 Exception，用来更简洁地构建你的代码。例如：
```
    void persist(Object o) throws SQLException {
        // into db
    }
```

