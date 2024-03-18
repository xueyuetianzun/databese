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

public class JF_view_query_grade_hz extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -4104847790861381958L;
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
    String gradeid[] = null;
    String gradename[] = null;
    String examkindid[] = null;
    String examkindname[] = null;
    JLabel jLabel1 = new JLabel();
    
    public JF_view_query_grade_hz() {
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
        jByes.addActionListener(new JF_view_query_grade_hz_jByes_actionAdapter(this));
        jBexit.addActionListener(new JF_view_query_grade_hz_jBexit_actionAdapter(this));
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jLabel1.setForeground(Color.red);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTable1);
        jPanel1.setLayout(flowLayout1);
        jLabel2.setToolTipText("");
        jLabel2.setText("�������");
        jLabel3.setText("�����꼶");
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
        sqlStr = "select * from tb_gradeinfo";
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        gradeid = new String[collection.size()];
        gradename = new String[collection.size()];
        i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            gradeid[i] = String.valueOf(vdata.get(0));
            gradename[i] = String.valueOf(vdata.get(1));
            jComboBox2.addItem(vdata.get(1));
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
        String strCode[] = new String[object.length];
        String strSubject[] = new String[object.length];
        
        String sqlStr = "CREATE VIEW TempView As SELECT stuid, tb_classinfo.className, ";
        for (int i = 0; i < object.length; i++) {
            
            String code = null, subject = null;
            java.util.Vector vdata = null;
            vdata = (java.util.Vector) object[i];
            code = String.valueOf(vdata.get(0));
            subject = String.valueOf(vdata.get(1));
            strSubject[i] = subject;
            
            if ((i + 1) == object.length) {
                sqlStr = sqlStr + " SUM(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '" + subject + "'";
            } else {
                sqlStr = sqlStr + " SUM(CASE code WHEN '" + code + "' THEN grade ELSE 0 END) AS '" + subject + "',";
            }
            
        }
        String whereStr = null;
        whereStr = " where kindID = '" + this.examkindid[jComboBox1.getSelectedIndex()] + "' and subString(stuid,1,2) = '"
                + this.gradeid[jComboBox2.getSelectedIndex()] + "' and tb_classinfo.classID = substring(tb_gradeinfo_sub.stuid, 1, 4)";
        
        sqlStr = sqlStr + " FROM tb_gradeinfo_sub, tb_classinfo " + whereStr + " GROUP BY stuid,tb_classinfo.className ";
        System.out.println(sqlStr);
        
        appstu.util.JdbcAdapter jdbcAdapter = new appstu.util.JdbcAdapter();
        if (jdbcAdapter.BuildeDeleteTempView(sqlStr)) {
            String[] tbname = new String[object.length + 2];
            tbname[0] = "�༶����";
            tbname[1] = "ѧ������";
            
            String sqlView = null;
            sqlView = "SELECT className AS �༶����,COUNT(stuid) AS ѧ������,";
            for (int i = 0; i < strSubject.length; i++) {
                tbname[i + 2] = strSubject[i];
                if ((i + 1) == strSubject.length) {
                    sqlView = sqlView + "sum(" + strSubject[i] + ") as '" + strSubject[i] + "'";
                } else {
                    sqlView = sqlView + "sum(" + strSubject[i] + ") as '" + strSubject[i] + "',";
                }
            }
            sqlView = sqlView + " from TempView GROUP BY SUBSTRING(stuid, 1, 4), className";
            System.out.println(sqlView);
            DefaultTableModel tablemodel = null;
            
            appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
            tablemodel = bdt.getTableModel(tbname, sqlView);
            
            jTable1.setModel(tablemodel);
            String dropSql = "DROP VIEW TempView ";
            jdbcAdapter.BuildeDeleteTempView(dropSql);
        }
        
        if (jTable1.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "û���ҵ���������������!!!", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
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

class JF_view_query_grade_hz_jBexit_actionAdapter implements ActionListener {
    private JF_view_query_grade_hz adaptee;
    
    JF_view_query_grade_hz_jBexit_actionAdapter(JF_view_query_grade_hz adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_query_grade_hz_jByes_actionAdapter implements ActionListener {
    private JF_view_query_grade_hz adaptee;
    
    JF_view_query_grade_hz_jByes_actionAdapter(JF_view_query_grade_hz adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jByes_actionPerformed(e);
    }
}
