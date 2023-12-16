package com.nhom7.entity.attendancelog.type;

import java.util.HashMap;
import java.util.Map;

public class TypeFactory {
    private static final TypeFactory instance = new TypeFactory();
    private final Map<String, Type> registeredTypes = new HashMap<>();

    private TypeFactory() {
    }

    public static TypeFactory instance() {
        return instance;
    }

    public void registerType(Type type) {
        registeredTypes.put(type.getType(), type);
    }

    public Type createType(String type) {
        return registeredTypes.get(type);
    }
}
