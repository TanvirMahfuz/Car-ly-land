package com.example.springwithgradle.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

    public static Map<String, Object> toHashMap(Object obj) {
        Map<String, Object> map = new HashMap<>();

        if (obj == null) return map;

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                // Skip nulls
                if (value == null) continue;

                Class<?> type = field.getType();

                // Skip empty strings
                if (type == String.class && ((String) value).isBlank()) continue;

                // Skip zero-valued numbers
                if (Number.class.isAssignableFrom(type) || type.isPrimitive()) {
                    if (isZero(value)) continue;
                }

                // Skip false booleans
                if (type == boolean.class || type == Boolean.class) {
                    if (!((Boolean) value)) continue;
                }

                // Skip empty collections or arrays
                if (value instanceof Collection<?> collection && collection.isEmpty()) continue;
                if (type.isArray() && Array.getLength(value) == 0) continue;

                // Add valid field
                map.put(field.getName(), value);

            } catch (IllegalAccessException ignored) {}
        }

        return map;
    }

    private static boolean isZero(Object value) {
        if (value instanceof Number num) {
            return num.doubleValue() == 0.0;
        }
        // Handle primitive numeric types (int, long, float, etc.)
        if (value instanceof Byte) return ((Byte) value) == 0;
        if (value instanceof Short) return ((Short) value) == 0;
        if (value instanceof Integer) return ((Integer) value) == 0;
        if (value instanceof Long) return ((Long) value) == 0L;
        if (value instanceof Float) return ((Float) value) == 0.0f;
        if (value instanceof Double) return ((Double) value) == 0.0;
        return false;
    }
}
