package com.qunar.fresh.test;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * create by lijiajia on 2018/1/7
 */
public class ListTest {
    public static void main(String[] args) {
        String pakage = "com.qunar.fresh.leetCode";
        // List<String> classNames = getClassName(packageName);
        List<Class<?>> classNames = getClassName(pakage, false);
        if (classNames != null) {
            for (Class className : classNames) {
                System.out.println(className.getSimpleName());
            }
        }

        List<TestModel> models = Lists.newArrayList();
        TestModel t1 = new TestModel();
        t1.setAge(12);
        t1.setCode(Long.valueOf(123));
        t1.setName("lijia");
        models.add(t1);
        System.out.println(JSON.toJSON(models));
    }

    /**
     * 获取某包下所有类
     * @param packageName 包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<Class<?>> getClassName(String packageName, boolean childPackage) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url == null) {
            return null;
        }
        return getClassNameByFile(packageName, url.getPath(), null, childPackage);
    }

    /**
     * 从项目文件获取某包下所有类
     * @param filePath 文件路径
     * @param className 类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<Class<?>> getClassNameByFile(String basePackage, String filePath, List<Class<?>> className, boolean childPackage) {
        List<Class<?>> myClassName = Lists.newArrayList();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();

        for (File childFile : childFiles) {
            if (childFile.isDirectory() && childPackage) {
                myClassName.addAll(getClassNameByFile(basePackage, childFile.getPath(), myClassName, childPackage));
            } else {
                String childFilePath = childFile.getPath();
                if (!childFilePath.endsWith(".class")) {
                    continue;
                }
                String simpleName = childFilePath.substring(childFilePath.lastIndexOf("/") + 1);
                simpleName = simpleName.substring(0, simpleName.indexOf("."));
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(basePackage + "." + simpleName);
                } catch (Exception e) {

                }
                myClassName.add(aClass);
            }
        }
        return myClassName;
    }
}
