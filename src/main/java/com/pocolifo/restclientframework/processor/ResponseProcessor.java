package com.pocolifo.restclientframework.processor;

import com.google.gson.Gson;
import com.pocolifo.restclientframework.request.AbstractRequest;
import com.pocolifo.restclientframework.response.Response;
import com.pocolifo.restclientframework.response.ResponseHeader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;


public class ResponseProcessor {
    public static final Class<?> STRING_CLASS = String.class;
    public static final Class<?> LIST_CLASS = List.class;

    @SuppressWarnings("unchecked")
    public static <T> T getClassInstance(Type type, AbstractRequest<T> request, Response response) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T inst = null;

        if (EndpointProcessor.shouldFormatJson(request)) {
            inst = new Gson().fromJson(request.formatResponseString(response), type);
        }

        if (inst == null) {
            inst = ((Class<T>) type).getConstructor().newInstance();
        }

        for (Field field : inst.getClass().getFields()) {
            if (field.isAnnotationPresent(ResponseHeader.class)) {
                field.setAccessible(true);

                String key = field.getAnnotation(ResponseHeader.class).value().toLowerCase();
                List<String> header = response.getHeaders().get(key);
                String h = header.get(0);

                Class<?> fieldType = field.getType();

                if (fieldType.equals(STRING_CLASS)) {
                    field.set(inst, h);
                } else if (fieldType.equals(LIST_CLASS)) {
                    field.set(inst, header);
                } else {
                    if (int.class.equals(fieldType)) {
                        field.setInt(inst, Integer.parseInt(h));
                    } else if (double.class.equals(fieldType)) {
                        field.setDouble(inst, Double.parseDouble(h));
                    } else if (float.class.equals(fieldType)) {
                        field.setFloat(inst, Float.parseFloat(h));
                    } else if (boolean.class.equals(fieldType)) {
                        field.setBoolean(inst, Boolean.parseBoolean(h));
                    } else if (long.class.equals(fieldType)) {
                        field.setLong(inst, Long.parseLong(h));
                    }
                }
            }
        }

        return inst;
    }
}
