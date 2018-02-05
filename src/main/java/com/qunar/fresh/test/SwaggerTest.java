package com.qunar.fresh.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.web.bind.annotation.RequestParam;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * create by lijiajia on 2018/1/5
 */
public class SwaggerTest {

    public static final String TEST_MODELS = "[{\"code\":123,\"name\":\"lijia\",\"age\":12}]";

    public static void main(String[] args) {
//        String a = "PmsGatewayService";
//        System.out.println(splitCamelCase(a, "-").replace("/", "").toLowerCase());


        String pakage = "com.qunar.fresh.test.Test";
        Class<?> aClass = null;
        try {
            aClass = Class.forName(pakage);
        } catch (Exception e) {

        }
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Parameter[] parameters = method.getParameters();
            int length = parameters.length;
            Object[] argvs = new Object[length];
            for (int i = 0; i < length; i++) {
                Class<?> parameterType = parameterTypes[i];
                Parameter parameter = parameters[i];
                ParameterizedTypeImpl parameterizedType = null;
                Type[] actualTypeArguments = null;
                Class<?> rawType = null;
                Type ownerType = null;
                try {
                    parameterizedType = (ParameterizedTypeImpl) parameter.getParameterizedType();
                    actualTypeArguments = parameterizedType.getActualTypeArguments();
                    ownerType = parameterizedType.getOwnerType();
                    rawType = parameterizedType.getRawType();
                } catch (Exception e) {

                }

                if (rawType == null) {
//                    argvs[i] = getIntegerValue(parameter.getType());
                    argvs[i] = getHaValue(parameter.getType(), null);
                } else {
//                    argvs[i] = getListValue(rawType, actualTypeArguments[0]);
                    argvs[i] = getHaValue(parameterizedType, aClass);
                }
            }
            try {
                method.invoke(aClass.newInstance(), argvs);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static Object getHaValue(Type parameterizedType, Class<?> aClass) {
        JavaType javaType = JavaTypeConverter.getJavaType(parameterizedType, aClass);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            Object o = mapper.readValue(TEST_MODELS, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    private static Object getListValue(Class<?> rawType, Type actualTypeArgument) {
        if (rawType == null) {
            return null;
        }
        if (rawType.equals(Integer.class) || rawType.equals(Integer.TYPE)) {

        }
        if (rawType.equals(List.class) ) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            try {
                Class<?> aClass = Class.forName(actualTypeArgument.getTypeName());
                if (actualTypeArgument.equals(Long.TYPE) || actualTypeArgument.equals(Long.class)) {
                    Object o = mapper.readValue("[1, 2]", new TypeReference<List<Long>>(){});
//                    for (Object i : list) {
//                        System.out.println(i);
//                    }
                    return o;
                } else {
                    System.out.println(actualTypeArgument.getTypeName());
                    String typeName = actualTypeArgument.getTypeName();
                    Class clazz = Class.forName(typeName);
                    Class declaringClass = clazz.getDeclaringClass();
                    Object o = mapper.readValue(TEST_MODELS, new TypeReference<List>(){});
                    return o;
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    private static Object getIntegerValue(Class<?> type) {
        if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
            return new Integer(10);
        }
        return new Long(1);
    }


    public static String splitCamelCase(String s, String separator) {
        if (isNullOrEmpty(s)) {
            return "";
        }
        String format = String.format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
        );
        return s.replaceAll(format, separator);
    }


}
