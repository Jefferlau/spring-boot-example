package com.jusfoun.mybatis.plugins;


import com.jusfoun.exception.GlobalException;
import com.jusfoun.utils.CamelUnderlineUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-08-11
 */
public class BatchInsertProvider {
    public static String batchInsertSelective(Map<String, Object> params) throws IllegalAccessException {
        Collection collection = (Collection)params.get("collection");
        if (collection.isEmpty()) {
            throw new GlobalException("Batch insert collection is empty!");
        }
        Iterator iterator = collection.iterator();
        Class clazz = iterator.next().getClass();
        iterator = null;

        String tableName = CamelUnderlineUtils.camelToUnderline(clazz.getSimpleName());
        if (tableName.endsWith("_key")) {
            tableName = tableName.substring(0, tableName.lastIndexOf("_key"));
        }
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();

        for (Object object : collection) {
            buffer.append("insert into `").append(tableName).append("` (");

            for (Field field : fields) {
                if (field.getName().equals("serialVersionUID")) continue;
                field.setAccessible(true);
                if (field.get(object) != null) {
                    buffer.append("`").append(field.getName()).append("`,");
                }
            }
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append(") values (");

            for (Field field : fields) {
                if (field.getName().equals("serialVersionUID")) continue;
                field.setAccessible(true);
                Object o = field.get(object);
                if (o != null) {
                    if (o instanceof Boolean) {
                        buffer.append("b'");
                        buffer.append((Boolean) o ? 1 : 0);
                        buffer.append("',");
                    } else {
                        buffer.append("'").append(escapeSql(o)).append("',");
                    }
                }
            }
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append(");");
        }

        return buffer.toString();
    }

    public static String escapeSql(Object str) {
        if (str == null) {
            return "";
        }
        String keyword = String.valueOf(str);
        if(keyword.contains("%") || keyword.contains("_") || keyword.contains("'") ){
            keyword = keyword.replaceAll("%", "\\%")
                    .replaceAll("_", "\\_")
                    .replaceAll("'", "\\\\'");
        }
        return keyword;
    }

    public static void main(String[] args) {
        String str = "%";
        System.out.println(escapeSql(str));
    }
}
