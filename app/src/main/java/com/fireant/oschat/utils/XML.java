package com.fireant.oschat.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml 解析工具类
 * Created by zhangdeyi on 15/7/19.
 */
public class XML {

    private static XStream getXStream(Class beanClass) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        // 设置可忽略为在javabean类中定义的界面属性
        xStream.ignoreUnknownElements();
        xStream.registerConverter(new MyIntCoverter());
        xStream.registerConverter(new MyLongCoverter());
        xStream.registerConverter(new MyFloatCoverter());
        xStream.registerConverter(new MyDoubleCoverter());
        xStream.processAnnotations(beanClass);
        return xStream;
    }

    public static <T> T parseBean(Class<T> beanClass, String parseString) {
        XStream xStream = getXStream(beanClass);

        return (T) xStream.fromXML(parseString);
    }

    public static <T> T parseBean(Class<T> beanClass, InputStream parseIs) {

        try {
            return (T) getXStream(beanClass).fromXML(parseIs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (parseIs != null) {
                try {
                    parseIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static <T> T parseBean(Class<T> beanClass, byte[] parseByte) {
        return parseBean(beanClass, new ByteArrayInputStream(parseByte));
    }

    private static class MyIntCoverter extends IntConverter {

        @Override
        public Object fromString(String str) {
            int value;
            try {
                value = (Integer) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyLongCoverter extends LongConverter {
        @Override
        public Object fromString(String str) {
            long value;
            try {
                value = (Long) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyFloatCoverter extends FloatConverter {
        @Override
        public Object fromString(String str) {
            float value;
            try {
                value = (Float) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyDoubleCoverter extends DoubleConverter {
        @Override
        public Object fromString(String str) {
            double value;
            try {
                value = (Double) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }
}
