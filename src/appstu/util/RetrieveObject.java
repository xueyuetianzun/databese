package appstu.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Collection;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class RetrieveObject {
    private Connection connection = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    public Collection getTableCollection(String sqlStr) {
        System.out.println("ִ执行的集合查询为:" + sqlStr);
        Collection collection = new Vector();
        connection = CommonaJdbc.conection;
        try {
            rs = connection.prepareStatement(sqlStr).executeQuery();//对sql预编译后的结果返回
            rsmd = rs.getMetaData();//返回结果集rs的信息
            while (rs.next()) {
                Vector vdata = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {//返回对象的列数
                    vdata.addElement(rs.getObject(i));
                }
                collection.add(vdata);
            }
        } catch (java.sql.SQLException sql) {
            new appstu.view.JF_view_error("执行的sql语句为:\n" + sqlStr + "\n错误信息为:" + sql.getMessage());
            sql.printStackTrace();
            return null;
        }
        return collection;
    }
    
    public DefaultTableModel getTableModel(String[] name, String sqlStr) {
        Vector vname = new Vector();
        for (int i = 0; i < name.length; i++) {
            vname.addElement(name[i]);
        }
        DefaultTableModel tableModel = new DefaultTableModel(vname, 0);
        connection = CommonaJdbc.conection;
        try {
            rs = connection.prepareStatement(sqlStr).executeQuery();
            rsmd = rs.getMetaData();
            while (rs.next()) {
                Vector vdata = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    vdata.addElement(rs.getObject(i));
                }
                tableModel.addRow(vdata);//封装
            }
        } catch (java.sql.SQLException sql) {
            sql.printStackTrace();
            return null;
        }
        return tableModel;//作为表格数据模型返回
    }
    
    // 获得满足条件的数据
    public Vector getObjectRow(String sqlStr) {
        Vector vdata = new Vector(); // 定义一个集合
        connection = CommonaJdbc.conection; // 获取一个数据库连接
        try {
            rs = connection.prepareStatement(sqlStr).executeQuery(); // 获取一个resultste实例
            rsmd = rs.getMetaData(); // 获取一个resultstematedata实例
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    vdata.addElement(rs.getObject(i)); // 将数据库的结果返回到一个集合中
                }
            }
        } catch (java.sql.SQLException sql) {
            sql.printStackTrace();
            return null;
        }
        return vdata; 
    }
    
}
