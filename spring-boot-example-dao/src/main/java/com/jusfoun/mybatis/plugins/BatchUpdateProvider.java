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
public class BatchUpdateProvider {
    public static String batchUpdateSelective(Map<String, Object> params) throws IllegalAccessException {
        Collection collection = (Collection) params.get("collection");
        if (collection.isEmpty()) {
            throw new GlobalException("Batch update collection is empty!");
        }
        Iterator iterator = collection.iterator();
        Class clazz = iterator.next().getClass();
        iterator = null;

        String tableName = CamelUnderlineUtils.camelToUnderline(clazz.getSimpleName());
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();

        for (Object object : collection) {
            buffer.append("update `").append(tableName).append("` set ");
            Object id = null;
            String idColumn = null;
            for (Field field : fields) {
                if (field.getName().equals("serialVersionUID")) continue;
                field.setAccessible(true);
                Object o = field.get(object);
                idColumn = CamelUnderlineUtils.firstCharLowerCase(clazz.getSimpleName()) + "Id";

                if (field.getName().equals(idColumn)) {
                    id = o;
                    continue;
                }

                if (o != null) {
                    buffer.append("`").append(field.getName()).append("` = ");
                    if (o instanceof Boolean) {
                        buffer.append("b'");
                        buffer.append((Boolean) o ? 1 : 0);
                        buffer.append("',");
                    } else {
                        buffer.append("'").append(o).append("',");
                    }
                }
            }

            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append(" where `").append(idColumn).append("` = '").append(id).append("';");
        }

        return buffer.toString();
    }
}
