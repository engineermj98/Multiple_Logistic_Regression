
package multiplelogisticregression;

import javax.swing.JFrame;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame{
    private MultipleLogisticRegression myAgent;
    
    public JTextField txt1,txt2, txt3;
    
    Gui(MultipleLogisticRegression a){
        super(a.getLocalName());
        myAgent = a;
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,4));
        p.add(new JLabel("NewX Pos 00\n"));
        txt1 = new JTextField();
        p.add(txt1);
        p.add(new JLabel("NewX Pos 01\n"));
        txt2 = new JTextField();
        p.add(txt2);
        p.add(new JLabel("NewX Pos 02\n"));
        txt3 = new JTextField();
        p.add(txt3);
        
        getContentPane().add(p,BorderLayout.CENTER);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("test",AID.ISLOCALNAME));    
                    msg.setContent(txt1.getText());
                    myAgent.x1=Double.parseDouble(msg.getContent());
                    myAgent.send(msg);                
                    msg.setContent(txt2.getText());
                    myAgent.x2=Double.parseDouble(msg.getContent());
                    myAgent.send(msg);
                    msg.setContent(txt3.getText());
                    myAgent.x3=Double.parseDouble(msg.getContent());
                    myAgent.send(msg);
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(Gui.this, "Invalid values. "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        p = new JPanel();
        p.add(submit);
        getContentPane().add(p,BorderLayout.SOUTH);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                myAgent.doDelete();
            }
        });
        setResizable(false);
    }
    public void showGui(){
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth()/2;
        int centerY = (int)screenSize.getHeight()/2;
        setLocation(centerX-getWidth()/2,centerY-getHeight()/2);
        super.setVisible(true);
    }
    
}
