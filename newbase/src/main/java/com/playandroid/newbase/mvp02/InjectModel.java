package com.playandroid.newbase.mvp02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)// 属性
@Retention(RetentionPolicy.RUNTIME)// 运行时
public @interface InjectModel {
}
