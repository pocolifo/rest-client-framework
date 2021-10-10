package com.pocolifo.restclientframework.processor;

import com.pocolifo.restclientframework.request.AbstractRequest;
import com.pocolifo.restclientframework.request.RequestHeader;
import com.pocolifo.restclientframework.request.RequestParameter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotationProcessor {

    public static final Class<?> STRING_CLASS = String.class;

    public static Map<String, String> getRequestParameters(AbstractRequest<?> req) {

        Map<String, String> map = new HashMap<>();

        for (Field field : req.getClass().getFields()) {
            if (field.isAnnotationPresent(RequestParameter.class)) {
                field.setAccessible(true);

                try {
                    Object o = field.get(req);

                    if (o == null) {
                        throw new NullPointerException("The request parameter " + field.getName() + " is unset!");
                    } else {
                        map.put(field.getName(), o.toString());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    // TODO: Add additional headers to request from request template
    public static Map<String, String> getAdditionalHeaders(AbstractRequest<?> a) {
        Class<?> c = a.getClass();

        Map<String, String> map = new HashMap<>();

        for (Field f : c.getFields()) {
            if (f.isAnnotationPresent(RequestHeader.class)) {
                f.setAccessible(true);

                if (f.getType().equals(STRING_CLASS)) {
                    try {
                        String header = f.getAnnotation(RequestHeader.class).value();
                        String value = (String) f.get(a);

                        if (header.isEmpty()) throw new IllegalStateException("The request header " + f.getName() + " is unset!");
                        if (value.isEmpty()) throw new IllegalStateException("The request header value " + f.getName() + " is unset!");

                        map.put(header, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new IllegalStateException("Field '" + f.getName() + "' must be of type String!");
                }
            }
        }

        return map;
    }

}
