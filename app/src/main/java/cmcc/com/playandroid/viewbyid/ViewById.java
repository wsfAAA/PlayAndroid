package cmcc.com.playandroid.viewbyid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1、@interface 代表注解
 * 2、使用注解必须要有两个标识 ：Target 、Retention
 *      Target：表示放在哪里,哪里可以使用
 *          可以设置：ElementType.FIELD 属性上  ElementType.TYPE 类上    ElementType.METHOD 方法上
 *      Retention: 表示什么时候起作用
 *          可以设置:RUNTIME 运行时（程序运行中）  CLASS 编译时（打包的时候） SOURCE 编程阶段（一般很少有，比如Activity中的@Override注解，不能注解父类没有覆写的方法上，不然直接报错，无需编译检测）
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewById {
   int value();  // 使用 ViewById 注解要传递的值
}
