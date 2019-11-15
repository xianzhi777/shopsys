package com.woniu.jdbc;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class DbOpr {
    private static String driver = "";
    private static String url = "";
    private static String account = "";
    private static String password = "";

    static {
        try {
            Properties p = new Properties();
            p.load(DbOpr.class.getClassLoader().getResourceAsStream("config/jdbc.properties"));
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            account = p.getProperty("account");
            password = p.getProperty("password");
            //System.out.println(url);
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //多行查询
    public static <T> List<T> query(String sql, Class<T> clazz, Object... params) {
        List<T> list = new ArrayList<T>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, account, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                T t = clazz.newInstance();
                int colcount = meta.getColumnCount();
                for (int i = 1; i <= colcount; i++) {
                    String colName = meta.getColumnName(i);
                    int colType = meta.getColumnType(i);
                    Field f = clazz.getDeclaredField(colName);
                    Class classf = f.getClass();
                    f.setAccessible(true);
                    doMap(t, f, rs, colType, classf, colName);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //字段投影
    private static <T> void doMap(T t, Field f, ResultSet rs, int colType, Class classf, String colName) {
        try {
            if (colType == Types.INTEGER) {
                f.set(t, rs.getInt(colName));
            } else if (colType == Types.VARCHAR || colType == Types.CHAR) {
                f.set(t, rs.getString(colName));
            } else if (colType == Types.TIMESTAMP || colType == Types.DATE) {
                f.set(t, rs.getTimestamp(colName));
            } else if (colType == Types.DECIMAL) {
                f.set(t, rs.getDouble(colName));
            } else if (colType == Types.LONGVARCHAR) {
                f.set(t, rs.getString(colName));
            }else if(colType==Types.DOUBLE){
                f.set(t,rs.getDouble(colName));
            } else {
                throw new Exception("----------no such type------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //单行查询
    public static <T> T queryOne(String sql, Class<T> clazz, Object... params) {
        List<T> list = query(sql, clazz,params);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    //非查询
    public static <T> void excute(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, account, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            DriverManager.getConnection(url, account, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //获取指定属性
    public static Object queryVal(String sql,Object...params){
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url,account,password);
            PreparedStatement stmt=conn.prepareStatement(sql);
            for(int i=0;i<params.length;i++){
                stmt.setObject(i+1,params[i]);
            }
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                return rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null&&!conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

