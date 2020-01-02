### java8新特性

#### Project 01
- 给出了行为参数化传递代码的方式

1. 定义一个标准的谓词模板

```java
public interface ApplePredicate{
    boolean test (Apple apple);
}
```

2. 用ApplePredicate的多个实现代表不同的选择标准
```java
public class AppleHeavyWeightPredicate implements ApplePredicate{ 
    public boolean test(Apple apple){
        return apple.getWeight() > 150; 
    }
}
public class AppleGreenColorPredicate implements ApplePredicate{
    public boolean test(Apple apple){
        return "green".equals(apple.getColor());
    }
}
```
3. 设置抽象条件筛选
```java
public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate p){
    List<Apple> result = new ArrayList<>();
    for(Apple apple: inventory){
        if(p.test(apple)){ 
            result.add(apple);
        } 
    }
    return result;
}
```
4. 代码传递行为
```java
public class AppleRedAndHeavyPredicate implements ApplePredicate{ 
    public boolean test(Apple apple){
        return "red".equals(apple.getColor()) && apple.getWeight() > 150;
    } 
}
List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
```
5. 使用匿名类
```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate() { 
    public boolean test(Apple apple){
        return "red".equals(apple.getColor());
    });
}
```
6. 使用Lambda表达式
```java
List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
```

7. 将List抽象化
```java
public interface Predicate<T>{
    boolean test(T t);
}
public static <T> List<T> filter(List<T> list, Predicate<T> p){
    List<T> result = new ArrayList<>();
    for(T e: list){
        if(p.test(e)){
            result.add(e);
        }
    } 
    return result;
}
```
#### Project 02

- 匿名类与Lambda表达式的使用

- 使用函数式接口

1. java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型 T对象，并返回一个boolean
```java
@FunctionalInterface
public interface Predicate<T>{
    boolean test(T t);
}
public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for(T s: list){
        if(p.test(s)){
            results.add(s);
        } 
    }
    return results;
}
Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty(); List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
```

2. java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T的对象，没有返回(void)。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个接口。
```java
@FunctionalInterface
public interface Consumer<T>{
    void accept(T t);
}
public static <T> void forEach(List<T> list, Consumer<T> c){
    for(T i: list){ 
        c.accept(i);
    }
}
forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
```

3. java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。如果你需要定义一个Lambda，将输入对象的信息映射到输出，就可以使用这个接口。
```java
@FunctionalInterface
public interface Function<T, R>{
    R apply(T t);
}
public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for(T s: list){
       result.add(f.apply(s)); 
    }
    return result;
}
List<Integer> l = map(Arrays.asList("lambdas","in","action"), (String s) -> s.length());
```
#### Project 03

- 引入流Stream
```java
List<Apple> heavyApples = inventory.stream().filter((Apple a) -> a.getWeight() > 150).collect(toList());
```
- 如何使用Stream
- 如何用流收集数据
- 并行数据处理
```java
List<Apple> heavyApples = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150) .collect(toList());
```
#### Project 04

- 使用Java8新特性重构代码
- Optional
- CompletableFuture
