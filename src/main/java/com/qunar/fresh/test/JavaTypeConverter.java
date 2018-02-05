package com.qunar.fresh.test;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * create by lijiajia on 2018/2/1
 */
public class JavaTypeConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JavaType getJavaType(Type type, Class<?> contextClass) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        if (contextClass != null) {
            ResolvableType resolvedType = ResolvableType.forType(type);
            if (type instanceof TypeVariable) {
                ResolvableType resolvedTypeVariable = resolveVariable(
                        (TypeVariable<?>) type, ResolvableType.forClass(contextClass));
                if (resolvedTypeVariable != ResolvableType.NONE) {
                    return typeFactory.constructType(resolvedTypeVariable.resolve());
                }
            }
            else if (type instanceof ParameterizedType && resolvedType.hasUnresolvableGenerics()) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?>[] generics = new Class<?>[parameterizedType.getActualTypeArguments().length];
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < typeArguments.length; i++) {
                    Type typeArgument = typeArguments[i];
                    if (typeArgument instanceof TypeVariable) {
                        ResolvableType resolvedTypeArgument = resolveVariable(
                                (TypeVariable<?>) typeArgument, ResolvableType.forClass(contextClass));
                        if (resolvedTypeArgument != ResolvableType.NONE) {
                            generics[i] = resolvedTypeArgument.resolve();
                        }
                        else {
                            generics[i] = ResolvableType.forType(typeArgument).resolve();
                        }
                    }
                    else {
                        generics[i] = ResolvableType.forType(typeArgument).resolve();
                    }
                }
                return typeFactory.constructType(ResolvableType.
                        forClassWithGenerics(resolvedType.getRawClass(), generics).getType());
            }
        }
        return typeFactory.constructType(type);
    }

    private static ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType contextType) {
        ResolvableType resolvedType;
        if (contextType.hasGenerics()) {
            resolvedType = ResolvableType.forType(typeVariable, contextType);
            if (resolvedType.resolve() != null) {
                return resolvedType;
            }
        }

        ResolvableType superType = contextType.getSuperType();
        if (superType != ResolvableType.NONE) {
            resolvedType = resolveVariable(typeVariable, superType);
            if (resolvedType.resolve() != null) {
                return resolvedType;
            }
        }
        for (ResolvableType ifc : contextType.getInterfaces()) {
            resolvedType = resolveVariable(typeVariable, ifc);
            if (resolvedType.resolve() != null) {
                return resolvedType;
            }
        }
        return ResolvableType.NONE;
    }
}
