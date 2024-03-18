package appstu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import appstu.util.JdbcAdapter;
import appstu.util.ProduceMaxBh;
import appstu.util.RetrieveObject;
import appstu.util.SendFocuseAdapter;

public class JF_view_sysset extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -2650251464761500276L;

    public JF_view_sysset() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        buildTable();
        this.getContentPane().setLayout(borderLayout1);
        setSize(380, 300);
        this.setClosable(true);
        setVisible(true);
        gridLayout1.setColumns(2);
        gridLayout1.setRows(2);
        jLabel1.setText("�꼶���");
        jTextField1.setText("");
        jLabel2.setText("�꼶����");
        jTextField2.setText("");
        jBdel.setText("ɾ��");
        jBdel.addActionListener(new JF_view_sysset_jBdel_actionAdapter(this));
        jPanel2.setLayout(flowLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jBadd.setText("���");
        jBadd.addActionListener(new JF_view_sysset_jBadd_actionAdapter(this));
        jBsave.setText("����");
        jBsave.addActionListener(new JF_view_sysset_jButton3_actionAdapter(this));
        jBexit.setText("�˳�");
        jBexit.addActionListener(new JF_view_sysset_jBexit_actionAdapter(this));
        jTable1.addMouseListener(new JF_view_sysset_jTable1_mouseAdapter(this));
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jPanel1.setLayout(gridLayout1);
        jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
        jScrollPane1.getViewport().add(jTable1);
        jSplitPane1.add(jPanel1, JSplitPane.BOTTOM);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField2);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(jBdel);
        jPanel2.add(jBadd);
        jPanel2.add(jBsave);
        jPanel2.add(jBexit);
        jSplitPane1.setDividerLocation(164);
        
        jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
        buildTable();
    }
    
    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    JPanel jPanel1 = new JPanel();
    JTable jTable1 = new JTable();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JPanel jPanel2 = new JPanel();
    JButton jBdel = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JButton jBexit = new JButton();
    
    public void buildTable() {
        DefaultTableModel tablemodel = null;
        String[] name = { "�༶ID", "�༶����" };
        String sqlStr = "select * from tb_gradeinfo";
        appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
        tablemodel = bdt.getTableModel(name, sqlStr);
        jTable1.setModel(tablemodel);
        jTable1.setRowHeight(24);
    }
    
    public void jBsave_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ�����꼶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.NO_OPTION)
            return;
        
        appstu.model.Obj_gradeinfo objgradeinfo = new appstu.model.Obj_gradeinfo();
        objgradeinfo.setGradeID(jTextField1.getText().trim());
        objgradeinfo.setGradeName(jTextField2.getText().trim());
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        if (jdbcAdapter.InsertOrUpdateObject(objgradeinfo))
            buildTable();
        
    }
    
    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳��꼶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            manger.closeFrame(this);
        }
        
    }
    
    public void jBadd_actionPerformed(ActionEvent e) {
        ProduceMaxBh pm = new appstu.util.ProduceMaxBh();
        String sqlStr = null, grdeid = null;
        sqlStr = "SELECT MAX(gradeID) FROM tb_gradeinfo";
        grdeid = pm.getMaxBh(sqlStr, "");
        jTextField1.setText(grdeid);
        jTextField2.setText("");
        jTextField2.requestFocus();
    }
    
    public void jTable1_mouseClicked(MouseEvent e) {
        String id = null;
        String sqlStr = null;
        int selectrow = 0;
        selectrow = jTable1.getSelectedRow();
        if (selectrow <= 0)
            return;
        
        id = jTable1.getValueAt(selectrow, 0).toString();
        sqlStr = "select * from tb_gradeinfo where gradeID = '" + id + "'";
        java.util.Vector vdata = null;
        RetrieveObject retrive = new RetrieveObject();
        vdata = retrive.getObjectRow(sqlStr);
        
        jTextField1.setText(vdata.get(0).toString());
        jTextField2.setText(vdata.get(1).toString());
    }
    
    public void jBdel_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ���꼶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.NO_OPTION)
            return;
        String sqlDel = "delete tb_gradeinfo where gradeID = '" + jTextField1.getText().trim() + "'";
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        if (jdbcAdapter.DeleteObject(sqlDel)) {
            jTextField1.setText("");
            jTextField2.setText("");
            buildTable();
        }
    }
}

class JF_view_sysset_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_sysset adaptee;
    
    JF_view_sysset_jTable1_mouseAdapter(JF_view_sysset adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}

class JF_view_sysset_jBdel_actionAdapter implements ActionListener {
    private JF_view_sysset adaptee;
    
    JF_view_sysset_jBdel_actionAdapter(JF_view_sysset adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}

class JF_view_sysset_jBadd_actionAdapter implements ActionListener {
    private JF_view_sysset adaptee;
    
    JF_view_sysset_jBadd_actionAdapter(JF_view_sysset adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}

class JF_view_sysset_jBexit_actionAdapter implements ActionListener {
    private JF_view_sysset adaptee;
    
    JF_view_sysset_jBexit_actionAdapter(JF_view_sysset adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_sysset_jButton3_actionAdapter implements ActionListener {
    private JF_view_sysset adaptee;
    
    JF_view_sysset_jButton3_actionAdapter(JF_view_sysset adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        adaptee.jBsave_actionPerformed(e);
    }
}
