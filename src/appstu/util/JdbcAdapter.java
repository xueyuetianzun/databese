package appstu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import appstu.model.Obj_classinfo;
import appstu.model.Obj_examkinds;
import appstu.model.Obj_gradeinfo;
import appstu.model.Obj_gradeinfo_sub;
import appstu.model.Obj_student;
import appstu.model.Obj_subject;
import appstu.model.Obj_teacher;
import appstu.model.Obj_user;
import appstu.view.JF_view_error;

public class JdbcAdapter {
    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String infoStr = null;
    
    public boolean BuildeDeleteTempView(String sqlState) {
        boolean flag = false;
        System.out.println("ִ�е����Ϊ:" + sqlState);
        try {
            con = CommonaJdbc.conection;
            pstmt = con.prepareStatement(sqlState);
            pstmt.execute();
            flag = true;
        } catch (java.sql.SQLException sql) {
            flag = false;
            sql.printStackTrace();
        }
        return flag;
    }
    
    // ������ִ�ж����ݿ�ĸ��ֲ���
    private boolean AdapterObject(String sqlState) {
        boolean flag = false;
        try {
            con = CommonaJdbc.conection; // ��ȡ���ݿ�����
            pstmt = con.prepareStatement(sqlState); // ��ȡPreparedStatementʵ��
            pstmt.execute(); // ִ�и�SQL���
            flag = true; // ����ʶ���޸�Ϊtrue
            JOptionPane.showMessageDialog(null, infoStr + "���ݳɹ�!!!", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE); // ������Ӧ��ʾ�Ի���
        } catch (java.sql.SQLException sql) {
            flag = false;
            sql.printStackTrace();
        }
        return flag; // ����ʶ������
    }
    
    // ִ��ɾ�����ݱ��е�����
    public boolean DeleteObject(String deleteSql) {
        infoStr = "ɾ��";
        return AdapterObject(deleteSql);
    }
    
    // ��֤���ݱ����Ƿ��������
    private boolean validateID(String id, String tname, String idvalue) {
        String sqlStr = null;
        sqlStr = "select count(*) from " + tname + " where " + id + " = '" + idvalue + "'"; // ����SQL���
        try {
            con = CommonaJdbc.conection; // ��ȡ���ݿ�����
            pstmt = con.prepareStatement(sqlStr); // ��ȡPreparedStatementʵ��
            java.sql.ResultSet rs = null; // ��ȡResultSetʵ��
            rs = pstmt.executeQuery(); // ִ��SQL���
            if (rs.next()) {
                if (rs.getInt(1) > 0) // ������ݱ�����ֵ
                    return true; // ����trueֵ
            }
        } catch (java.sql.SQLException sql) { // ��������쳣
            sql.printStackTrace(); // ����쳣
            return false; // ����falseֵ
        }
        return false; // ����falseֵ
    }
    
    public boolean InsertOrUpdateObject(Obj_gradeinfo objgradeinfo) {
        String sqlStatement = null;
        if (validateID("gradeID", "tb_gradeinfo", objgradeinfo.getGradeID().trim())) {
            sqlStatement = "Update tb_gradeinfo set gradeID = '" + objgradeinfo.getGradeID() + "',gradeName ='" + objgradeinfo.getGradeName()
                    + "' where gradeID = '" + objgradeinfo.getGradeID().trim() + "'";
            infoStr = "�����꼶";
        } else {
            sqlStatement = "Insert tb_gradeinfo(gradeID,gradeName) values ('" + objgradeinfo.getGradeID() + "','" + objgradeinfo.getGradeName() + "')";
            infoStr = "����꼶";
        }
        
        System.out.println(sqlStatement);
        return AdapterObject(sqlStatement);
    }
    
    public boolean InsertOrUpdateObject(Obj_classinfo objclassinfo) {
        String sqlStatement = null;
        if (validateID("classID", "tb_classinfo", objclassinfo.getClassID())) {
            sqlStatement = "Update tb_classinfo set className = '" + objclassinfo.getClassName() + "' where classID = '" + objclassinfo.getClassID().trim()
                    + "'";
            
            infoStr = "���°༶";
            
        } else {
            sqlStatement = "Insert tb_classinfo(classID,gradeID,className) values ('" + objclassinfo.getClassID() + "','" + objclassinfo.getGradeID() + "','"
                    + objclassinfo.getClassName() + "')";
            infoStr = "��Ӱ༶";
        }
        System.out.println(sqlStatement);
        return AdapterObject(sqlStatement);
        
    }
    
    public boolean InsertOrUpdateObject(Obj_subject objsubject) {
        String sqlStatement = null;
        if (validateID("code", "tb_subject", objsubject.getCode())) {
            sqlStatement = "Update tb_subject set code = '" + objsubject.getCode() + "',subject = '" + objsubject.getSubject() + "' where code = '"
                    + objsubject.getCode().trim() + "'";
            infoStr = "���¿��Կ�Ŀ";
            
        } else {
            sqlStatement = "Insert tb_subject(code,subject) values ('" + objsubject.getCode() + "','" + objsubject.getSubject() + "')";
            infoStr = "��ӿ��Կ�Ŀ";
        }
        
        return AdapterObject(sqlStatement);
        
    }
    
    public boolean InsertOrUpdateObject(Obj_examkinds objexamkinds) {
        String sqlStatement = null;
        if (validateID("KindID", "tb_examkinds", objexamkinds.getKindID())) {
            sqlStatement = "Update tb_examkinds set KindID = '" + objexamkinds.getKindID() + "',KindName = '" + objexamkinds.getKindName()
                    + "' where KindID = '" + objexamkinds.getKindID().trim() + "'";
            
            infoStr = "���¿������";
            
        } else {
            sqlStatement = "Insert tb_examkinds(KindID,KindName) values ('" + objexamkinds.getKindID() + "','" + objexamkinds.getKindName() + "')";
            infoStr = "��ӿ������";
        }
        return AdapterObject(sqlStatement);
    }
    
    public boolean InsertOrUpdateObject(Obj_user objuser) {
        String sqlStatement = null;
        if (validateID("userid", "tb_user", objuser.getUserid())) {
            sqlStatement = "Update tb_user set userid = '" + objuser.getUserid() + "',username = '" + objuser.getUsername() + "',pass = '" + objuser.getPass()
                    + "' where userid = '" + objuser.getUserid().trim() + "'";
            infoStr = "�����û�";
            
        } else {
            sqlStatement = "Insert tb_user(userid,username,pass) values ('" + objuser.getUserid() + "','" + objuser.getUsername() + "','" + objuser.getPass()
                    + "')";
            infoStr = "����û�";
        }
        
        return AdapterObject(sqlStatement);
        
    }
    
    // /////////////////////////////////////////////
    // �������´���ѧ����Ϣ���
    public boolean InsertOrUpdateObject(Obj_student objstudent) {
        String sqlStatement = null;
        if (validateID("stuid", "tb_studentinfo", objstudent.getStuid())) {
            sqlStatement = "Update tb_studentinfo set stuid = '" + objstudent.getStuid() + "',classID = '" + objstudent.getClassID() + "',stuname = '"
                    + objstudent.getStuname() + "',sex = '" + objstudent.getSex() + "',age = '" + objstudent.getAge() + "',addr = '" + objstudent.getAddress()
                    + "',phone = '" + objstudent.getPhone() + "' where stuid = '" + objstudent.getStuid().trim() + "'";
            infoStr = "����ѧ����Ϣ";
        } else {
            sqlStatement = "Insert tb_studentinfo(stuid,classid,stuname,sex,age,addr,phone) values ('" + objstudent.getStuid() + "','"
                    + objstudent.getClassID() + "','" + objstudent.getStuname() + "','" + objstudent.getSex() + "','" + objstudent.getAge() + "','"
                    + objstudent.getAddress() + "','" + objstudent.getPhone() + "')";
            infoStr = "���ѧ����Ϣ";
        }
        return AdapterObject(sqlStatement);
    }
    
    // �������´��̽�ʦ��Ϣ���
    public boolean InsertOrUpdateObject(Obj_teacher objteacher) {
        String sqlStatement = null;
        if (validateID("teaid", "tb_teacher", objteacher.getTeaid())) {
            sqlStatement = "Update tb_teacher set teaid = '" + objteacher.getTeaid() + "',classID = '" + objteacher.getClassID() + "',teaname = '"
                    + objteacher.getTeaname() + "',sex = '" + objteacher.getSex() + "',knowledge = '" + objteacher.getKnowledge() + "',knowlevel = '"
                    + objteacher.getKnowlevel() + "' where teaid = '" + objteacher.getTeaid().trim() + "'";
            
            infoStr = "���½�ʦ��Ϣ";
            
        } else {
            sqlStatement = "Insert tb_teacher(teaid,classID,teaname,sex,knowledge,knowlevel) values ('" + objteacher.getTeaid() + "','"
                    + objteacher.getClassID() + "','" + objteacher.getTeaname() + "','" + objteacher.getSex() + "','" + objteacher.getKnowledge() + "','"
                    + objteacher.getKnowlevel() + "')";
            
            infoStr = "��ӽ�ʦ��Ϣ";
        }
        return AdapterObject(sqlStatement);
    }
    
    // ��֤���ݱ����Ƿ��������
    private boolean validateobjgradeinfo(String stuid, String kindid, String code) {
        String sqlStr = null;
        sqlStr = "select count(*) from tb_gradeinfo_sub where stuid = '" + stuid + "' and kindID = '" + kindid + "' and code = '" + code + "'";
        System.out.println(sqlStr);
        try {
            con = CommonaJdbc.conection;
            pstmt = con.prepareStatement(sqlStr);
            java.sql.ResultSet rs = null;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0)
                    return true;
            }
        } catch (java.sql.SQLException sql) {
            sql.printStackTrace();
            new appstu.view.JF_view_error("ִ�е�SQL���Ϊ:\n" + sqlStr + "\n������ϢΪ��" + sql.getMessage());
            
            return false;
        }
        
        return false;
    }
    
    // ��������ѧ����Ϣ��Ϣ���
    public boolean InsertOrUpdate_Obj_gradeinfo_sub(Obj_gradeinfo_sub[] object) {
        try {
            con = CommonaJdbc.conection;
            stmt = con.createStatement();
            for (int i = 0; i < object.length; i++) {
                String sqlStr = null;
                if (validateobjgradeinfo(object[i].getStuid(), object[i].getKindID(), object[i].getCode())) {
                    sqlStr = "update tb_gradeinfo_sub set stuid = '" + object[i].getStuid() + "',stuname = '" + object[i].getSutname() + "',kindID = '"
                            + object[i].getKindID() + "',code = '" + object[i].getCode() + "',grade = " + object[i].getGrade() + " ,examdate = '"
                            + object[i].getExamdate() + "' where stuid = '" + object[i].getStuid() + "' and kindID = '" + object[i].getKindID()
                            + "' and code = '" + object[i].getCode() + "'";
                    
                } else {
                    sqlStr = "insert  tb_gradeinfo_sub(stuid,stuname,kindID,code,grade,examdate)  values ('" + object[i].getStuid() + "','"
                            + object[i].getSutname() + "','" + object[i].getKindID() + "','" + object[i].getCode() + "'," + object[i].getGrade() + " ,'"
                            + object[i].getExamdate() + "')";
                }
                System.out.println("sqlStr = " + sqlStr);
                stmt.addBatch(sqlStr);
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "ѧ���ɼ����ݴ��̳ɹ�!!!", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
        } catch (java.sql.SQLException sqlerror) {
            new appstu.view.JF_view_error("������ϢΪ��" + sqlerror.getMessage());
            return false;
        }
        return true;
    }
    
    // ��������ѧ����Ϣ��Ϣ���
    public boolean Delete_Obj_gradeinfo_sub(Obj_gradeinfo_sub[] object) {
        try {
            con = CommonaJdbc.conection;
            stmt = con.createStatement();
            for (int i = 0; i < object.length; i++) {
                String sqlStr = null;
                sqlStr = "Delete From tb_gradeinfo_sub  where stuid = '" + object[i].getStuid() + "' and kindID = '" + object[i].getKindID() + "' and code = '"
                        + object[i].getCode() + "'";
                System.out.println("sqlStr = " + sqlStr);
                stmt.addBatch(sqlStr);
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "ѧ���ɼ���������ɾ���ɹ�!!!", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
        } catch (java.sql.SQLException sqlerror) {
            new appstu.view.JF_view_error("������ϢΪ��" + sqlerror.getMessage());
            return false;
        }
        return true;
    }
    
}
