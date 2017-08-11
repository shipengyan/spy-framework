package com.github.shipengyan.framework.domain;


import com.github.shipengyan.framework.util.EqualsUtil;
import com.github.shipengyan.framework.util.HashCodeUtil;
import com.github.shipengyan.framework.util.StringUtil;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 模型基类
 *
 * @author shi.pengyan
 * @date 2016年12月26日 下午2:59:29
 */
public class BaseDomain implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 提供默认的HashCode算法 当前对象上自己定义的Field才会参与Hash值计算，不包含父类的Field
     *
     * @return int 返回哈希值<br>
     */
    @Override
    public int hashCode() {
        int result = 17;

        Field[] fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object thisValue = field.get(this);
                    result = 37 * result + HashCodeUtil.hashCode(thisValue);
                } catch (IllegalArgumentException e) {
                    return super.hashCode();
                } catch (IllegalAccessException e) {
                    return super.hashCode();
                }
            }
        }

        return result;

    }

    /**
     * 判断两个对象是否相等 当前对象上自己定义的Field才会参与比较，不包含父类的Field
     *
     * @param obj 要比较的对象
     * @return boolean 两个对象相等才会返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        Field[] fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object thisValue = field.get(this);
                    Object thatValue = field.get(obj);
                    if (!EqualsUtil.equals(thisValue, thatValue)) {
                        return false;
                    }
                } catch (IllegalArgumentException e) {
                    super.equals(obj);
                } catch (IllegalAccessException e) {
                    super.equals(obj);
                }
            }
        }

        return true;
    }

    /**
     * 重载toString()函数 当前对象上自己定义的Field才会被输出，不包含父类的Field
     *
     * @return String 返回字符串
     */
    @Override
    public String toString() {
        StringBuffer returnStr = new StringBuffer();
        Field[]      fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            returnStr.append("Object Value List :").append(System.getProperty("line.separator"));
            returnStr.append(generate(fieldList));
        }
        return returnStr.toString();
    }

    /**
     * 输出字符串值
     *
     * @param level 父类层级
     * @return
     */
    public String toString(int level) {
        Field[] fieldList = this.getClass().getDeclaredFields();

        StringBuffer sb = new StringBuffer();

        if (fieldList != null) {
            sb.append(generate(fieldList));
        }

        for (int i = 0; i < level; i++) {
            Class<?> parent = this.getClass().getSuperclass();
            if (parent == null) {
                break;
            }
            sb.append(generate(parent.getDeclaredFields()));
        }
        return sb.toString();
    }

    /**
     * 组装字段
     *
     * @param fieldList 可用列表
     * @return
     */
    private String generate(Field[] fieldList) {
        StringBuffer sb = new StringBuffer();

        if (fieldList != null) {
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                sb.append(field.getName()).append(":[");
                String value;
                try {
                    value = StringUtil.toString(field.get(this));
                } catch (IllegalArgumentException e) {
                    value = "";
                } catch (IllegalAccessException e) {
                    value = "";
                }
                sb.append(value).append("]").append(System.getProperty("line.separator"));
            }
        }

        return sb.toString();
    }
}
