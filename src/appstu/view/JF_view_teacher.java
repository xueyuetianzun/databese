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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import appstu.util.JdbcAdapter;
import appstu.util.ProduceMaxBh;
import appstu.util.RetrieveObject;

public class JF_view_teacher extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3152358757919633732L;
    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JPanel jPanel1 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JPanel jPanel3 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JTextField jTextField3 = new JTextField();
    JLabel jLabel6 = new JLabel();
    JComboBox jComboBox3 = new JComboBox();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JButton jBrefresh = new JButton();
    JButton jBexit = new JButton();
    JButton jBdel = new JButton();
    JComboBox jComboBox2 = new JComboBox();
    JComboBox jComboBox4 = new JComboBox();
    JComboBox jComboBox1 = new JComboBox();
    
    String classid[] = null;
    
    public JF_view_teacher() {
        try {
            jbInit();
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        getContentPane().setLayout(borderLayout1);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setOpaque(false);
        jSplitPane1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(flowLayout1);
        jBadd.setText("���");
        jBadd.addActionListener(new JF_view_teacher_jBadd_actionAdapter(this));
        jBsave.setMnemonic('0');
        jBsave.setText("����");
        jBsave.addActionListener(new JF_view_teacher_jBsave_actionAdapter(this));
        jPanel3.setLayout(gridLayout1);
        gridLayout1.setColumns(4);
        gridLayout1.setRows(3);
        jLabel3.setText("��ʦ���");
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        jLabel4.setText("�༶����");
        jLabel5.setText("��ʦ����");
        jTextField3.setText("");
        jLabel6.setText("�Ա�");
        jLabel8.setText("��ʦ�ȼ�");
        jLabel9.setText("��ʦְ��");
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jBrefresh.setText("ˢ��");
        jBrefresh.addActionListener(new JF_view_teacher_jBrefresh_actionAdapter(this));
        jBexit.setText("�˳�");
        jBexit.addActionListener(new JF_view_teacher_jBexit_actionAdapter(this));
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        this.setClosable(true);
        jBdel.setText("ɾ��");
        jBdel.addActionListener(new JF_view_teacher_jBdel_actionAdapter(this));
        jTable1.addMouseListener(new JF_view_teacher_jTable1_mouseAdapter(this));
        jComboBox2.setEditable(true);
        jComboBox4.setEditable(true);
        jPanel1.add(jBrefresh);
        jPanel1.add(jBadd);
        jPanel1.add(jBdel);
        jPanel1.add(jBsave);
        jPanel1.add(jBexit);
        jSplitPane1.add(jPanel3, JSplitPane.BOTTOM);
        jPanel3.add(jLabel3);
        jPanel3.add(jTextField1);
        jPanel3.add(jLabel4);
        jPanel3.add(jComboBox1);
        jPanel3.add(jLabel5);
        jPanel3.add(jTextField3);
        jPanel3.add(jLabel6);
        jPanel3.add(jComboBox3);
        jPanel3.add(jLabel9);
        jPanel3.add(jComboBox2);
        jPanel3.add(jLabel8);
        jPanel3.add(jComboBox4);
        jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        setSize(640, 440);
        setVisible(true);
        jSplitPane1.setDividerLocation(256);
    }
    
    // ���һ������
    public void jBadd_actionPerformed(ActionEvent e) {
        
        jComboBox1.removeAllItems();
        RetrieveObject retrieve = new RetrieveObject();
        java.util.Vector vdata = new java.util.Vector();
        java.util.Collection collection = null;
        java.util.Iterator iterator = null;
        
        String sqlStr = null;
        sqlStr = "select * from tb_classinfo";
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        classid = new String[collection.size()];
        int i = 0;
        while (iterator.hasNext()) {
            vdata = (java.util.Vector) iterator.next();
            classid[i] = String.valueOf(vdata.get(0));
            jComboBox1.addItem(vdata.get(2));
            i++;
        }
        
        String sqlMax = "select max(teaid) from tb_teacher ";
        ProduceMaxBh pm = new appstu.util.ProduceMaxBh();
        
        String stuid = null;
        stuid = pm.getMaxBh(sqlMax, "");
        
        jTextField1.setText(stuid);
        jTextField3.setText("");
        jComboBox3.removeAllItems();
        jComboBox3.addItem("��");
        jComboBox3.addItem("Ů");
        
        jComboBox2.removeAllItems();
        jComboBox2.addItem("�߼���ʦ");
        jComboBox2.addItem("�м���ʦ");
        jComboBox2.addItem("������ʦ");
        
        jComboBox4.removeAllItems();
        jComboBox4.addItem("�߼�");
        jComboBox4.addItem("�м�");
        jComboBox4.addItem("����");
        
        jTextField3.requestFocus();
    }
    
    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳���ʦ������Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            manger.closeFrame(this);
        }
        
    }
    
    public void jBsave_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ���̽�ʦ����������Ϣ?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.NO_OPTION)
            return;
        
        appstu.model.Obj_teacher object = new appstu.model.Obj_teacher();
        
        object.setTeaid(jTextField1.getText().trim());
        
        object.setClassID(classid[jComboBox1.getSelectedIndex()]);
        object.setTeaname(jTextField3.getText().trim());
        object.setSex(String.valueOf(jComboBox3.getSelectedItem()));
        object.setKnowlevel(String.valueOf(jComboBox4.getSelectedItem()));
        object.setKnowledge(String.valueOf(jComboBox2.getSelectedItem()));
        
        appstu.util.JdbcAdapter adapter = new appstu.util.JdbcAdapter();
        if (adapter.InsertOrUpdateObject(object)) {
            ActionEvent event = new ActionEvent(jBrefresh, 0, null);
            jBrefresh_actionPerformed(event);
        }
    }
    
    public void jBrefresh_actionPerformed(ActionEvent e) {
        DefaultTableModel tablemodel = null;
        String[] name = { "��ʦ���", "�༶���", "��ʦ����", "�Ա�", "��ʦְ��", "֪ʶˮƽ" };
        String sqlStr = "select * from tb_teacher";
        appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
        tablemodel = bdt.getTableModel(name, sqlStr);
        jTable1.setModel(tablemodel);
        jTable1.setRowHeight(24);
        
    }
    
    public void jBdel_actionPerformed(ActionEvent e) {
        if (jTextField1.getText().trim().length() <= 0)
            return;
        int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ����ʦ�Ļ�����Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.NO_OPTION)
            return;
        String sqlDel = "delete tb_teacher where teaid = '" + jTextField1.getText().trim() + "'";
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        if (jdbcAdapter.DeleteObject(sqlDel)) {
            jTextField1.setText("");
            jTextField3.setText("");
            jComboBox1.removeAllItems();
            jComboBox2.removeAllItems();
            jComboBox3.removeAllItems();
            jComboBox4.removeAllItems();
            ActionEvent event = new ActionEvent(jBrefresh, 0, null);
            jBrefresh_actionPerformed(event);
            
        }
        
    }
    
    public void jTable1_mouseClicked(MouseEvent e) {
        String id = null;
        String sqlStr = null;
        int selectrow = 0;
        selectrow = jTable1.getSelectedRow();
        if (selectrow < 0)
            return;
        
        id = jTable1.getValueAt(selectrow, 0).toString();
        sqlStr = "select * from tb_teacher where teaid = '" + id + "'";
        java.util.Vector vdata = null;
        RetrieveObject retrive = new RetrieveObject();
        vdata = retrive.getObjectRow(sqlStr);
        String gradeid = null, classid = null;
        String gradename = null, classname = null;
        java.util.Vector vname = null;
        classid = vdata.get(1).toString();
        gradeid = classid.substring(0, 2);
        vname = retrive.getObjectRow("select className from tb_classinfo where classID = '" + classid + "'");
        classname = String.valueOf(vname.get(0));
        
        jTextField1.setText(vdata.get(0).toString());
        jComboBox1.removeAllItems();
        jComboBox1.addItem(classname);
        jTextField3.setText(vdata.get(2).toString());
        jComboBox3.removeAllItems();
        jComboBox3.addItem(vdata.get(3).toString());
        jComboBox2.removeAllItems();
        jComboBox2.addItem(vdata.get(4).toString());
        jComboBox4.removeAllItems();
        jComboBox4.addItem(vdata.get(5).toString());
    }
    
}

class JF_view_teacher_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jTable1_mouseAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}

class JF_view_teacher_jBdel_actionAdapter implements ActionListener {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jBdel_actionAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}

class JF_view_teacher_jBrefresh_actionAdapter implements ActionListener {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jBrefresh_actionAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBrefresh_actionPerformed(e);
    }
}

class JF_view_teacher_jBexit_actionAdapter implements ActionListener {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jBexit_actionAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_teacher_jBadd_actionAdapter implements ActionListener {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jBadd_actionAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}

class JF_view_teacher_jBsave_actionAdapter implements ActionListener {
    private JF_view_teacher adaptee;
    
    JF_view_teacher_jBsave_actionAdapter(JF_view_teacher adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBsave_actionPerformed(e);
    }
}
