package com.ann.project;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        Filter f1 = new Filter();
        f1.setId(10);//查询ID为10的用户

        Filter f2 = new Filter();
        f2.setUserName("Lucy");//查询用户名为Lucy的用户
        f2.setAge(18);

        Filter f3 = new Filter();
        f3.setEmail("zhangsan@163.com,lisi@qq.com,wangwu@gmail.com");//查询邮箱为其中任意一个的用户

        String sql1 = query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

        Filter2 filter2 = new Filter2();
        filter2.setName("技术部");
        filter2.setAmount(20);
        System.out.println(query(filter2));
    }

    private static String query(Object f) {
        StringBuilder sb = new StringBuilder();
        //1. 获取class
        Class c = f.getClass();
        //2. 获取table的名字
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1");
        //3. 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            //4. 处理每个字段对应的sql
            //4.1. 拿到字段名
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            //4.2. 拿到字段值
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = getMethod.invoke(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //4.3. 拼装sql
            if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                continue;
            }
            sb.append(" AND ").append(fieldName);
            //以下代码只处理了String和Integer两种类型
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] values = ((String) fieldValue).split(",");
                    sb.append(" in(");
                    for (String v : values) {
                        sb.append("'").append(v).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("=").append("'").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                sb.append("=").append(fieldValue);
            }
        }
        return sb.toString();
    }
}
