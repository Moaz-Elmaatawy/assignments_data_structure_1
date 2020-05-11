package home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FileChooser extends JFrame implements ActionListener {
    public static JTextField filename = new JTextField(), dir = new JTextField();

    private JButton open = new JButton("Open"), attach = new JButton("Attach");

    public FileChooser() {
        JPanel p = new JPanel();

        add(attach);
        attach.addActionListener(this);
        attach.setActionCommand("attach");
        p.add(attach);

        open.addActionListener(new OpenL());
        p.add(open);

        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("attach")) {
            if(dir.getText().length()==0) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f, "Please choose file to attach.");
                f.dispose();
            }
            else {
                if(compose_Controller.attachments.contains(dir.getText()+"\\"+filename.getText())){
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "you attached this file before !");
                    f.dispose();
                }
                 else {
                     compose_Controller.attachments.add(dir.getText()+"\\"+filename.getText());
                 }
                dispose();
            }
        }
    }

    class OpenL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(FileChooser.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());

            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }


    public static void fileChooser() {
        run(new FileChooser(), 250, 110);
    }
    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setBounds(600,250,width,height);
        frame.setVisible(true);
    }
}