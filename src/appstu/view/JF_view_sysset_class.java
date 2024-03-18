package appstu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import appstu.util.JdbcAdapter;
import appstu.util.ProduceMaxBh;
import appstu.util.RetrieveObject;

public class JF_view_sysset_class extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -2113619556260692677L;

    public JF_view_sysset_class() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        // buildTable();
        this.getContentPane().setLayout(borderLayout1);
        setSize(420, 340);
        this.setClosable(true);
        setVisible(true);
        gridLayout1.setColumns(2);
        gridLayout1.setRows(3);
        jLabel1.setText("�꼶����");
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        jLabel2.setText("�༶���");
        jTextField2.setEnabled(false);
        jTextField2.setText("");
        jBdel.setText("ɾ��");
        jBdel.addActionListener(new JF_view_sysset_class_jBdel_actionAdapter(this));
        jPanel2.setLayout(flowLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jBadd.setText("���");
        jBadd.addActionListener(new JF_view_sysset_class_jBadd_actionAdapter(this));
        jBsave.setText("����");
        jBsave.addActionListener(new JF_view_sysset_class_jButton3_actionAdapter(this));
        jBexit.setText("�˳�");
        jBexit.addActionListener(new JF_view_sysset_class_jBexit_actionAdapter(this));
        jTable1.addMouseListener(new JF_view_sysset_class_jTable1_mouseAdapter(this));
        jLabel3.setText("�༶����");
        jComboBox1.addItemListener(new JF_view_sysset_class_jComboBox1_itemAdapter(this));
        jLabel4.setText("ѡ���꼶");
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jPanel1.setLayout(gridLayout1);
        jSplitPane1.add(jScrollPane1, JSplitPane.TOP);
        jScrollPane1.setViewportView(jTable1);
        jSplitPane1.add(jPanel1, JSplitPane.BOTTOM);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField2);
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField3);
        jPanel2.add(jLabel4);
        jPanel2.add(jComboBox1);
        jPanel2.add(jBdel);
        jPanel2.add(jBadd);
        jPanel2.add(jBsave);
        jPanel2.add(jBexit);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jSplitPane1.setDividerLocation(164);

        // jTextField1.addKeyListener(new SendFocuseAdapter(jTextField2));
        buildTable();
        initJcombox();
    }

    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    JPanel jPanel1 = new JPanel();
    JTable jTable1 = new JTable();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JPanel jPanel2 = new JPanel();
    JButton jBdel = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBadd = new JButton();
    JButton jBsave = new JButton();
    JButton jBexit = new JButton();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    String gradeID[] = null;
    // ��ӱ�����������״̬
    boolean insertflag = true;
    JTextField jTextField3 = new JTextField();
    JLabel jLabel4 = new JLabel();

    private void buildTable() {
        DefaultTableModel tablemodel = null; // ���ñ��ģ�ͱ���
        String[] name = { "�༶���", "�꼶���", "�༶����" }; // ���ñ�ͷ����
        String sqlStr = "select * from tb_classinfo"; // ����SQL���
        appstu.util.RetrieveObject bdt = new appstu.util.RetrieveObject();
        tablemodel = bdt.getTableModel(name, sqlStr); // ����getTableModel������ȡһ�����ģ��ʵ��
        jTable1.setModel(tablemodel); // �����ģ�ͷ����ڱ����
        jTable1.setRowHeight(24); // ���ñ����и�Ϊ24
    }

    private void initJcombox() {
        String sqlStr = null;
        sqlStr = "select gradeID,gradeName from tb_gradeinfo";

        RetrieveObject retrieve = new RetrieveObject();
        java.util.Collection collection = null;
        java.util.Iterator iterator = null;
        collection = retrieve.getTableCollection(sqlStr);
        iterator = collection.iterator();
        gradeID = new String[collection.size()];
        int i = 0;
        jComboBox1.removeAllItems();
        while (iterator.hasNext()) {
            java.util.Vector vdata = (java.util.Vector) iterator.next();
            gradeID[i] = String.valueOf(vdata.get(0));
            jComboBox1.addItem(vdata.get(1));
            i++;
        }

    }

    public void jBsave_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ���̰༶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            int index = jComboBox1.getSelectedIndex();
            String gradeid = gradeID[index];
            appstu.model.Obj_classinfo objclassinfo = new appstu.model.Obj_classinfo();
            objclassinfo.setClassID(jTextField2.getText().trim());
            objclassinfo.setGradeID(gradeid);
            objclassinfo.setClassName(jTextField3.getText().trim());
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            if (jdbcAdapter.InsertOrUpdateObject(objclassinfo)) {
                buildTable();
            }
        } else {
            return;
        }

    }

    public void jBexit_actionPerformed(ActionEvent e) {
        javax.swing.DefaultDesktopManager manger = new DefaultDesktopManager();
        int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳��༶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            manger.closeFrame(this);
        }

    }

    public void jBadd_actionPerformed(ActionEvent e) {
        // ����꼶����
        if (jComboBox1.getItemCount() <= 0) {
            return;
        } else {
            int index = jComboBox1.getSelectedIndex();
            String gradeid = gradeID[index];
            String sqlStr = null, classid = null;
            sqlStr = "SELECT MAX(classID) FROM tb_classinfo where gradeID = '" + gradeid + "'";
            ProduceMaxBh pm = new appstu.util.ProduceMaxBh();
            classid = pm.getMaxBh(sqlStr, gradeid);
            jTextField1.setText(String.valueOf(jComboBox1.getSelectedItem()));
            jTextField2.setText(classid);
            jTextField3.setText("");
            jTextField3.requestFocus();
        }

    }

    public void jTable1_mouseClicked(MouseEvent e) {
        insertflag = false;
        String id = null;
        String sqlStr = null;
        int selectrow = 0;
        selectrow = jTable1.getSelectedRow(); // ��ȡ���ѡ��������
        if (selectrow < 0)
            return; // ���������С��0���򷵻�
        id = jTable1.getValueAt(selectrow, 0).toString(); // ���ص�selectrow�У���һ�еĵ�Ԫ��ֵ
        // ���ݰ༶ID�����Ӳ�ѯ�༶��Ϣ�����꼶��Ϣ���еĻ�����Ϣ
        sqlStr = "SELECT d.gradeName, c.classID, c.className FROM tb_classinfo c INNER JOIN " + " tb_gradeinfo d ON c.gradeID = d.gradeID"
                + " where c.classID = '" + id + "'";
        Vector vdata = null;
        RetrieveObject retrive = new RetrieveObject();
        vdata = retrive.getObjectRow(sqlStr); // ִ��SQL��䷵��һ������
        // jComboBox1.removeAllItems();
        jTextField1.setText(vdata.get(0).toString());
        // jComboBox1.addItem(vdata.get(1));
        jTextField2.setText(vdata.get(1).toString());
        jTextField3.setText(vdata.get(2).toString());
    }

    public void jBdel_actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ���༶��Ϣ����?", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {
                "��", "��" }, "��");
        if (result == JOptionPane.YES_OPTION) {
            String sqlDel = "delete from tb_teacher where classID = '" + jTextField2.getText().trim() + "'";
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.DeleteObject(sqlDel);
            sqlDel = "delete from tb_classinfo where classID = '" + jTextField2.getText().trim() + "'";
            if (jdbcAdapter.DeleteObject(sqlDel)) {
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                buildTable();
            }
        } else {
            return;
        }

    }

    public void jComboBox1_itemStateChanged(ItemEvent e) {
        if (insertflag) {
            String gradeID = null;
            gradeID = "0" + String.valueOf(jComboBox1.getSelectedIndex() + 1);
            ProduceMaxBh pm = new appstu.util.ProduceMaxBh();
            String sqlStr = "SELECT MAX(classID) FROM tb_classinfo where gradeID = '" + gradeID + "'";
            String classid = pm.getMaxBh(sqlStr, gradeID);
            jTextField1.setText(jComboBox1.getSelectedItem().toString());
            jTextField2.setText(classid);
        } else {
            jTextField1.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
        }
    }

}

class JF_view_sysset_class_jComboBox1_itemAdapter implements ItemListener {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jComboBox1_itemAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox1_itemStateChanged(e);
    }
}

class JF_view_sysset_class_jTable1_mouseAdapter extends MouseAdapter {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jTable1_mouseAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        adaptee.jTable1_mouseClicked(e);
    }
}

class JF_view_sysset_class_jBdel_actionAdapter implements ActionListener {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jBdel_actionAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBdel_actionPerformed(e);
    }
}

class JF_view_sysset_class_jBadd_actionAdapter implements ActionListener {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jBadd_actionAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBadd_actionPerformed(e);
    }
}

class JF_view_sysset_class_jBexit_actionAdapter implements ActionListener {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jBexit_actionAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_view_sysset_class_jButton3_actionAdapter implements ActionListener {
    private JF_view_sysset_class adaptee;

    JF_view_sysset_class_jButton3_actionAdapter(JF_view_sysset_class adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        adaptee.jBsave_actionPerformed(e);
    }
}
