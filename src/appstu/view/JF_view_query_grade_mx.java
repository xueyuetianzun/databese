package appstu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appstu.util.RetrieveObject;

public class JF_view_query_grade_mx extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3556774937393119189L;
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JPanel jPanel1 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JButton jByes = new JButton();
    JButton jBexit = new JButton();
    
    // ////////////////
    String classid[] = null;
    String classname[] = null;
    String examkindid[] = null;
    String examkindname[] = null;
    JLabel jLabel1 = new JLabel();
    
    public JF_view_query_grade_mx() {
        try {
            jbInit();
            this.initialize();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        this.setClosable(true);
        jByes.addActionListener(new JF_view_query_grade_mx_jByes_actionAdapter(this));
        jBexit.addActionListener(new JF_view_query_grade_mx_jBexit_actionAdapter(this));
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jLabel1.setForeground(Color.red);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTable1);
        jPanel1.setLayout(flowLayout1);
        jLabel2.setToolTipText("");
        jLabel2.setText("�������");
        jLabel3.setText("�����༶");
        jByes.setText("ȷ��");
        jBexit.setText("�˳�");
        jPanel1.add(jLabel2);
        jPanel1.add(jComboBox1);
        jPanel1.add(jLabel3);
        jPanel1.add(jComboBox2);
        jPanel1.add(jByes);
        jPanel1.add(jBexit);
        this.getContentPane().add(jLabel1, java.awt.BorderLayout.SOUTH);
        
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        this.setSize(680, 460);
        this.setVisible(true);
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
        classname = new String[collection.size()];
        i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            classid[i] = String.valueOf(vdata.get(0));
            classname[i] = String.valueOf(vdata.get(2));
            jComboBox2.addItem(vdata.get(2));
            i++;
        }
        
    }
    
    public void jByes_actionPerformed(ActionEvent e) {
        String sqlSubject = null;
        java.util.Collection collection = null;
        Object[] object = null;
        java.util.Iterator iterator = null;
        sqlSubject = "SELECT * FROM tb_subject";
        RetrieveObject retrieve = new RetrieveObject();
        collection = retrieve.getTableCollection(sqlSubject);
        object = collection.toArray();
        String strCode[] = new String[object.length];                   // ���������ſ��Կ�Ŀ����
        String strSubject[] = new String[object.length];                    // ���������ſ��Կ�Ŀ����
        String[] tbname = new String[object.length + 2];                // ���������ű��ؼ�������
        tbname[0] = "ѧ�����";
        tbname[1] = "ѧ������";
        String sqlStr = "SELECT stuid, stuname, ";
        for (int i = 0; i < object.length; i++) {
            String code = null, subject = null;
            java.util.Vector vdata = null;
            vdata = (java.util.Vector) object[i];
            code = String.valueOf(vdata.get(0));
            subject = String.valueOf(vdata.get(1));
            tbname[i + 2] = subject;
            if ((i + 1) == object.length) {
                sqlStr = sqlStr + " SUM(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '"
                                                                                     + subject + "'";
            } else {
                sqlStr = sqlStr + " SUM(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '"
                                                                                     + subject + "',";
            }
        }
        String whereStr = " where kind";
        // Ϊ����whereStr���и�ֵ�������ɲ�ѯ��SQL���
        whereStr = " where kindID = '" + this.examkindid[jComboBox1.getSelectedIndex()] + "' and subString(stuid,1,4) = '"
                + this.classid[jComboBox2.getSelectedIndex()] + "' ";
        // Ϊ����sqlStr���и�ֵ�������ɲ�ѯ��SQL���
        sqlStr = sqlStr + " FROM tb_gradeinfo_sub " + whereStr + " GROUP BY stuid,stuname ";
        DefaultTableModel tablemodel = null;
        appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
        tablemodel = bdt.getTableModel(tbname, sqlStr); // ͨ������bdt��getTableModel����Ϊ���ֵ
        jTable1.setModel(tablemodel);
        if (jTable1.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "û���ҵ���������������!!!", "ϵͳ��ʾ", 
                                                            JOptionPane.INFORMATION_MESSAGE);
        }
        jTable1.setRowHeight(24);
        jLabel1.setText("�������ݡ�" + String.valueOf(jTable1.getRowCount()) + "����");
    }

    
    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳�ѧ���ɼ���ϸ��Ϣ��ѯ?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            manger.closeFrame(this);
        }
        
    }
    
}

class JF_view_query_grade_mx_jBexit_actionAdapter implements ActionListener {
    private JF_view_query_grade_mx adaptee;
    
    JF_view_query_grade_mx_jBexit_actionAdapter(JF_view_query_grade_mx adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_query_grade_mx_jByes_actionAdapter implements ActionListener {
    private JF_view_query_grade_mx adaptee;
    
    JF_view_query_grade_mx_jByes_actionAdapter(JF_view_query_grade_mx adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jByes_actionPerformed(e);
    }
}
