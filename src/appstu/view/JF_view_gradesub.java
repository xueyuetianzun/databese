package appstu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import appstu.model.Obj_gradeinfo_sub;
import appstu.util.RetrieveObject;

public class JF_view_gradesub extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -537038919964506695L;
    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JPanel jPanel2 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JTable jTable1 = new JTable();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JButton jBexit = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable2 = new JTable();
    // ////////////////
    String classid[] = null;
    String examkindid[] = null;
    String examkindname[] = null;
    String subjectcode[] = null;
    String subjectname[] = null;
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jBdel = new JButton();
    
    public JF_view_gradesub() {
        try {
            jbInit();
            initialize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setDividerSize(10);
        jPanel2.setLayout(flowLayout1);
        jLabel1.setText("ѡ��༶:");
        jLabel2.setText("��������:");
        jBadd.setText("���");
        jBadd.addActionListener(new JF_view_gradesub_jBadd_actionAdapter(this));
        jBsave.setText("����");
        jBsave.addActionListener(new JF_view_gradesub_jBsave_actionAdapter(this));
        jBexit.setText("�˳�");
        jBexit.addActionListener(new JF_view_gradesub_jBexit_actionAdapter(this));
        this.setClosable(true);
        jComboBox2.addItemListener(new JF_view_gradesub_jComboBox2_itemAdapter(this));
        jLabel3.setText("��������:");
        jTextField1.setPreferredSize(new Dimension(96, 26));
        jTextField1.setText("");
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jTable1.addMouseListener(new JF_view_gradesub_jTable1_mouseAdapter(this));
        jBdel.setText("ɾ��");
        jBdel.addActionListener(new JF_view_gradesub_jBdel_actionAdapter(this));
        
        jSplitPane1.add(jScrollPane2, JSplitPane.TOP);
        jSplitPane1.add(jScrollPane1, JSplitPane.BOTTOM);
        jScrollPane1.getViewport().add(jTable2);
        jPanel2.add(jLabel3);
        jPanel2.add(jTextField1);
        jPanel2.add(jLabel2);
        jPanel2.add(jComboBox1);
        jPanel2.add(jLabel1);
        jPanel2.add(jComboBox2);
        jPanel2.add(jBadd);
        jPanel2.add(jBdel);
        jPanel2.add(jBsave);
        jPanel2.add(jBexit);
        jScrollPane2.getViewport().add(jTable1);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        setSize(700, 500);
        setVisible(true);
        jSplitPane1.setDividerLocation(159);
    }
    
    public void initialize() {
        RetrieveObject retrieve = new RetrieveObject();
        java.util.Vector vdata = new java.util.Vector();
        String sqlStr = null;
        java.util.Collection collection = null;
        java.util.Iterator iterator = null;
        sqlStr = "SELECT * FROM tb_examkinds";
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        examkindid = new String[collection.size()];
        examkindname = new String[collection.size()];
        int i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            examkindid[i] = String.valueOf(vdata.get(0));
            examkindname[i] = String.valueOf(vdata.get(1));
            jComboBox1.addItem(vdata.get(1));
            i++;
        }
        sqlStr = "select * from tb_classinfo";
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        classid = new String[collection.size()];
        i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            classid[i] = String.valueOf(vdata.get(0));
            jComboBox2.addItem(vdata.get(2));
            i++;
        }
        sqlStr = "select * from tb_subject";
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        subjectcode = new String[collection.size()];
        subjectname = new String[collection.size()];
        i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            subjectcode[i] = String.valueOf(vdata.get(0));
            subjectname[i] = String.valueOf(vdata.get(1));
            
            i++;
        }
        long nCurrentTime = System.currentTimeMillis();
        java.util.Calendar calendar = java.util.Calendar.getInstance(new Locale("CN"));
        calendar.setTimeInMillis(nCurrentTime);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String mm, dd;
        if (month < 10) {
            mm = "0" + String.valueOf(month);
        } else {
            mm = String.valueOf(month);
        }
        if (day < 10) {
            dd = "0" + String.valueOf(day);
        } else {
            dd = String.valueOf(day);
        }
        java.sql.Date date = java.sql.Date.valueOf(year + "-" + mm + "-" + dd);
        jTextField1.setText(String.valueOf(date));
    }
    
    public void jComboBox2_itemStateChanged(ItemEvent e) {
        String cid = classid[jComboBox2.getSelectedIndex()];
        DefaultTableModel tablemodel = null;
        String[] name = { "ѧ�����", "�༶���", "ѧ������", "�Ա�", "����", "��ͥסַ", "��ϵ�绰" };
        String sqlStr = "select * from tb_studentinfo where classID = '" + cid + "'";
        appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
        tablemodel = bdt.getTableModel(name, sqlStr);
        jTable1.setModel(tablemodel);
        jTable1.setRowHeight(24);
    }
    
    public void jBadd_actionPerformed(ActionEvent e) {
        int currow;
        currow = jTable1.getSelectedRow();
        if (currow >= 0) {
            DefaultTableModel tablemodel = null;
            String[] name = { "ѧ�����", "ѧ������", "�������", "���Կ�Ŀ", "���Գɼ�", "����ʱ��" };
            tablemodel = new DefaultTableModel(name, 0);
            String sqlStr = null;
            Collection collection = null;
            Object[] object = null;
            Iterator iterator = null;
            sqlStr = "SELECT subject FROM tb_subject"; // �����ѯ����
            RetrieveObject retrieve = new RetrieveObject(); // ���幫�������
            Vector vdata = null;
            vdata = retrieve.getObjectRow(sqlStr);
            for (int i = 0; i < vdata.size(); i++) {
                Vector vrow = new Vector();
                if (i == 0) {
                    vrow.addElement(jTable1.getValueAt(currow, 0));
                    vrow.addElement(jTable1.getValueAt(currow, 2));
                    vrow.addElement(jComboBox1.getSelectedItem());
                    vrow.addElement(vdata.get(i));
                    vrow.addElement("");
                    vrow.addElement(jTextField1.getText().trim());
                } else {
                    vrow.addElement("");
                    vrow.addElement("");
                    vrow.addElement("");
                    vrow.addElement(vdata.get(i));
                    vrow.addElement("");
                    vrow.addElement(jTextField1.getText().trim());
                }
                tablemodel.addRow(vrow);
                this.jTable2.setModel(tablemodel);
                this.jTable2.setRowHeight(23);
            }
        }
    }
    
    public void jBsave_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ����ѧ�����Գɼ�����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.NO_OPTION)
            return;
        int rcount;
        rcount = jTable2.getRowCount();
        if (rcount > 0) {
            appstu.util.JdbcAdapter jdbcAdapter = new appstu.util.JdbcAdapter();
            Obj_gradeinfo_sub[] object = new Obj_gradeinfo_sub[rcount];
            for (int i = 0; i < rcount; i++) {
                object[i] = new Obj_gradeinfo_sub();
                object[i].setStuid(String.valueOf(jTable2.getValueAt(0, 0)));
                object[i].setKindID(examkindid[jComboBox1.getSelectedIndex()]);
                object[i].setCode(subjectcode[i]);
                object[i].setSutname(String.valueOf(jTable2.getValueAt(0, 1)));
                float grade;
                grade = Float.parseFloat(String.valueOf(jTable2.getValueAt(i, 4)));
                object[i].setGrade(grade);
                java.sql.Date rq = null;
                try {
                    String strrq = String.valueOf(jTable2.getValueAt(i, 5));
                    rq = java.sql.Date.valueOf(strrq);
                } catch (Exception dt) {
                    JOptionPane.showMessageDialog(null, "�ڡ�" + i + "������������ݸ�ʽ����,������¼��!!\n" + dt.getMessage(), "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                object[i].setExamdate(rq);
            }
            jdbcAdapter.InsertOrUpdate_Obj_gradeinfo_sub(object); // ִ�й������е����ݴ��̲���
        }
    }
    
    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳�ѧ�����Գɼ�����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            manger.closeFrame(this);
        }
    }
    
    public void jTable1_mouseClicked(MouseEvent e) {
        int currow = jTable1.getSelectedRow();
        if (currow >= 0) {
            DefaultTableModel tablemodel = null;
            String[] name = { "ѧ�����", "ѧ������", "�������", "���Կ�Ŀ", "���Գɼ�", "����ʱ��" };
            tablemodel = new DefaultTableModel(name, 0);
            String sqlStr = null;
            Collection collection = null;
            Object[] object = null;
            sqlStr = "SELECT * FROM tb_gradeinfo_sub where stuid = '" + jTable1.getValueAt(currow, 0) + "' and kindID = '"
                    + examkindid[jComboBox1.getSelectedIndex()] + "'";
            RetrieveObject retrieve = new RetrieveObject();
            collection = retrieve.getTableCollection(sqlStr);
            object = collection.toArray();
            int findindex = 0;
            for (int i = 0; i < object.length; i++) {
                Vector vrow = new Vector();
                Vector vdata = (Vector) object[i];
                String sujcode = String.valueOf(vdata.get(3));
                for (int aa = 0; aa < this.subjectcode.length; aa++) {
                    if (sujcode.equals(subjectcode[aa])) {
                        findindex = aa;
                        System.out.println("findindex = " + findindex);
                    }
                }
                if (i == 0) {
                    vrow.addElement(vdata.get(0));
                    vrow.addElement(vdata.get(1));
                    vrow.addElement(examkindname[Integer.parseInt(String.valueOf(vdata.get(2))) - 1]);
                    vrow.addElement(subjectname[findindex]);
                    vrow.addElement(vdata.get(4));
                    String ksrq = String.valueOf(vdata.get(5));
                    ksrq = ksrq.substring(0, 10);
                    System.out.println(ksrq);
                    vrow.addElement(ksrq);
                } else {
                    vrow.addElement("");
                    vrow.addElement("");
                    vrow.addElement("");
                    vrow.addElement(subjectname[findindex]);
                    vrow.addElement(vdata.get(4));
                    String ksrq = String.valueOf(vdata.get(5));
                    ksrq = ksrq.substring(0, 10);
                    System.out.println(ksrq);
                    vrow.addElement(ksrq);
                }
                tablemodel.addRow(vrow);
            }
            this.jTable2.setModel(tablemodel);
            this.jTable2.setRowHeight(22);
        }
    }
    
    public void jBdel_actionPerformed(ActionEvent e) {
        int rcount = jTable2.getRowCount();
        if (rcount > 0) {
            int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ��ѧ����" + jTable2.getValueAt(0, 1) + "���Ŀ��Գɼ�����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "��", "��" }, "��");
            if (result == JOptionPane.NO_OPTION)
                return;
            
            appstu.util.JdbcAdapter jdbcAdapter = new appstu.util.JdbcAdapter();
            Obj_gradeinfo_sub[] object = new Obj_gradeinfo_sub[rcount];
            for (int i = 0; i < rcount; i++) {
                object[i] = new Obj_gradeinfo_sub();
                object[i].setStuid(String.valueOf(jTable2.getValueAt(0, 0)));
                object[i].setKindID(examkindid[jComboBox1.getSelectedIndex()]);
                object[i].setCode(subjectcode[i]);
                object[i].setSutname(String.valueOf(jTable2.getValueAt(i, 1)));
                float grade;
                grade = Float.parseFloat(String.valueOf(jTable2.getValueAt(i, 4)));
                object[i].setGrade(grade);
                java.sql.Date rq = null;
                try {
                    System.out.println(jTable2.getValueAt(i, 5));
                    String strrq = String.valueOf(jTable2.getValueAt(i, 5));
                    System.out.println(i + ";strrq = " + strrq + "strrq.length = " + strrq.length());
                    rq = java.sql.Date.valueOf(strrq);
                    
                } catch (Exception dt) {
                    JOptionPane.showMessageDialog(null, "�ڡ�" + i + "������������ݸ�ʽ����,������¼��!!\n" + dt.getMessage(), "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                object[i].setExamdate(rq);
            }
        }
        
    }
}

class JF_view_gradesub_jBdel_actionAdapter implements ActionListener {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jBdel_actionAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}

class JF_view_gradesub_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jTable1_mouseAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}

class JF_view_gradesub_jBexit_actionAdapter implements ActionListener {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jBexit_actionAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_gradesub_jBsave_actionAdapter implements ActionListener {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jBsave_actionAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBsave_actionPerformed(e);
    }
}

class JF_view_gradesub_jBadd_actionAdapter implements ActionListener {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jBadd_actionAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}

class JF_view_gradesub_jComboBox2_itemAdapter implements ItemListener {
    private JF_view_gradesub adaptee;
    
    JF_view_gradesub_jComboBox2_itemAdapter(JF_view_gradesub adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox2_itemStateChanged(e);
    }
}
