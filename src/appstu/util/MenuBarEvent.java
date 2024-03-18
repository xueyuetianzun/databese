package appstu.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultDesktopManager;
import javax.swing.JOptionPane;

import appstu.view.JF_view_gradesub;
import appstu.view.JF_view_query_grade_hz;
import appstu.view.JF_view_query_grade_mx;
import appstu.view.JF_view_query_jbqk;
import appstu.view.JF_view_student;
import appstu.view.JF_view_sysset_class;
import appstu.view.JF_view_sysset_examkinds;
import appstu.view.JF_view_sysset_grade;
import appstu.view.JF_view_sysset_subject;
import appstu.view.JF_view_teacher;
import appstu.view.JF_view_user_modify;

public class MenuBarEvent implements ActionListener {
    private javax.swing.JDesktopPane JDeskTop = null;
    private String EventName = "";
    private DefaultDesktopManager desktopManager = new DefaultDesktopManager();
    
    public void setDeskTop(javax.swing.JDesktopPane deskTop) {
        this.JDeskTop = deskTop;
    }
    
    public void setEventName(String eventName) {
        this.EventName = eventName;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e.getActionCommand() = " + e.getActionCommand() + ";EventName = " + EventName);
        if (e.getActionCommand().equals("JB_EXIT") || EventName.equals("JB_EXIT")) {
            javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
            int result = JOptionPane.showOptionDialog(null, "�Ƿ�����˳�ѧ������ϵͳ?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[] { "��", "��" }, "��");
            if (result == JOptionPane.YES_OPTION) {
                try {
                    CommonaJdbc.conection.close();
                } catch (java.sql.SQLException sql) {
                    sql.printStackTrace();
                }
                System.exit(0);
            }
            return;
        }
        
        if (e.getActionCommand().equals("sys_grade") || EventName.equals("sys_grade")) {
            JF_view_sysset_grade jfInternalFrame = new JF_view_sysset_grade();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("�꼶��Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("sys_class") || EventName.equals("sys_class")) {
            JF_view_sysset_class jfInternalFrame = new JF_view_sysset_class();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("�༶��Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("sys_examkinds") || EventName.equals("sys_examkinds")) {
            JF_view_sysset_examkinds jfInternalFrame = new JF_view_sysset_examkinds();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("���������Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("sys_subject") || EventName.equals("sys_subject")) {
            JF_view_sysset_subject jfInternalFrame = new JF_view_sysset_subject();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("���Կ�Ŀ��Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("JF_view_student") || EventName.equals("JF_view_student")) {
            JF_view_student jfInternalFrame = new JF_view_student();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("ѧ��������Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("JF_view_teacher") || EventName.equals("JF_view_teacher")) {
            JF_view_teacher jfInternalFrame = new JF_view_teacher();
            jfInternalFrame.setLocation(50, 50);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("��ʦ������Ϣ����");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("JF_view_gradesub") || EventName.equals("JF_view_gradesub")) {
            JF_view_gradesub jfInternalFrame = new JF_view_gradesub();
            jfInternalFrame.setLocation(50, 30);
            JDeskTop.add(jfInternalFrame);
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            jfInternalFrame.setTitle("ѧ�����Գɼ���Ϣ����");
            jfInternalFrame.requestFocus(true);
            return;
        }
        if (e.getActionCommand().equals("JF_view_query_jbqk") || EventName.equals("JF_view_query_jbqk")) {
            JF_view_query_jbqk jfInternalFrame = new JF_view_query_jbqk();
            jfInternalFrame.setLocation(50, 30);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("������Ϣ���ݲ�ѯ");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("JF_view_query_grade_mx") || EventName.equals("JF_view_query_grade_mx")) {
            JF_view_query_grade_mx jfInternalFrame = new JF_view_query_grade_mx();
            jfInternalFrame.setLocation(50, 30);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("���Գɼ��༶��ϸ���ݲ�ѯ");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("JF_view_query_grade_hz") || EventName.equals("JF_view_query_grade_hz")) {
            JF_view_query_grade_hz jfInternalFrame = new JF_view_query_grade_hz();
            jfInternalFrame.setLocation(50, 30);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("���Գɼ��꼶�������ݲ�ѯ");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        if (e.getActionCommand().equals("sys_user_modify") || EventName.equals("sys_user_modify")) {
            JF_view_user_modify jfInternalFrame = new JF_view_user_modify();
            jfInternalFrame.setLocation(50, 30);
            JDeskTop.add(jfInternalFrame);
            jfInternalFrame.show();
            jfInternalFrame.setTitle("�û�������Ϣά��");
            JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
            return;
        }
        
    }
}
