//https://data.princeton.edu/wws509/notes/c3.pdf
//https://kseow.com/logisticregression
package multiplelogisticregression;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class MultipleLogisticRegression extends Agent{
    private Gui myGui;
    private MultipleLogisticRegression myAgent;
    static MultipleLogisticRegression mlr = new MultipleLogisticRegression();
    static Dataset ds = new Dataset();
    static Model model = new Model();
    BigDecimal bd = new BigDecimal(0);
    double alpha = 0.1;
    public double x1,x2,x3;   
    public double Prediction = 0;
    
    
    @Override
    public void setup(){
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new LogisticRegression());
        myGui = new Gui(MultipleLogisticRegression.this);
        myGui.showGui();

    }
    private class LogisticRegression extends Behaviour{
        int cont = 0;
        int band = 0;
        @Override
        public void action() {
            ACLMessage msg3 = myAgent.receive();
            if(msg3 != null){
                //JOptionPane.showMessageDialog(null, " XNew Pos 02: "+msg3.getContent()); 
                msg3.getContent();
                for(int i = 0; i < ds.getN(); i++){
                    model.w[i] = 0;
                }
                System.out.println(ds.getN());
                for(int i = 0; i < 100000; i++){
                    for(int k = 0; k < ds.getN(); k++){
                        int q = 0, w = 1, e = 2, r = 3;
                        for(int j = 0; j < ds.getN(); j++){  
                            if (ds.getN() <= 3){            
                                model.fx[k] = (1/(1+Math.pow(2.718,-(ds.getX()[k][0]*model.getW()[0]+
                                    ds.getX()[k][1]*model.getW()[1]+
                                    ds.getX()[k][2]*model.getW()[2]))))-ds.getY()[k];
                                band = 1;
                            }
                            if (ds.getN() > 3){
                                model.fx[k] = (1/(1+Math.pow(2.718,-(ds.getX()[k][0]*model.getW()[0]+
                                    ds.getX()[k][1]*model.getW()[1]+
                                    ds.getX()[k][2]*model.getW()[2]+
                                    ds.getX()[k][3]*model.getW()[3]))))-ds.getY()[k];
                                band = 2;
                            }
                        }
                        if(band == 1){
                        model.w[k] = model.getW()[k] - alpha * (model.getFx()[q]*ds.getX()[k][0]+
                                model.getFx()[w]*ds.getX()[k][1]+
                                model.getFx()[e]*ds.getX()[k][2]);
                        q++; // If we got xi = 9 of length, then we'll have also f(xi) = 9 of length and it will look like f(x1),f(x2)..f(x9)
                        w++;
                        e++;
                        }
                        if(band == 2){
                        model.w[k] = model.getW()[k] - alpha * (model.getFx()[q]*ds.getX()[k][0]+
                                model.getFx()[w]*ds.getX()[k][1]+
                                model.getFx()[e]*ds.getX()[k][2]+
                                model.getFx()[r]*ds.getX()[k][3]);
                        q++; // If we got xi = 9 of length, then we'll have also f(xi) = 9 of length and it will look like f(x1),f(x2)..f(x9)
                        w++;
                        e++;
                        }
                       // q++; // If we got xi = 9 of length, then we'll have also f(xi) = 9 of length and it will look like f(x1),f(x2)..f(x9)
                        //w++;
                        //e++;
                    }  
                }
                 
                if (ds.getY().length <= 3){
                    //System.out.println("W0: "+model.getW()[0]);
                    //System.out.println("W1: "+model.getW()[1]);
                    //System.out.println("W2: "+model.getW()[2]);  
                    Prediction =(1/(1+Math.pow(2.718,-(model.getW()[0]*x1+
                        model.getW()[1]*x2+
                        model.getW()[2]*x3) )));     
                    System.out.println("New X: ["+x1+", "+x2+", "+x3+"]");
                    System.out.println("New feature: "+Prediction);
                    if(Prediction > 0.5) System.out.println("Classification: "+1);
                    else System.out.println("Classification: "+0);
                    System.exit(cont);
                }else if(ds.getY().length > 3){
                   // System.out.println("W0: "+model.getW()[0]);
                   // System.out.println("W1: "+model.getW()[1]);
                   // System.out.println("W2: "+model.getW()[2]);  
                   // System.out.println("W4: "+model.getW()[3]);  
                    Prediction =(1/(1+Math.pow(2.718,-(model.getW()[0]*1+
                        model.getW()[1]*x1+
                        model.getW()[2]*x2+
                            model.getW()[3]*x3) )));     
                    System.out.println("New X: ["+x1+", "+x2+", "+x3+"]");
                    System.out.println("New feature: "+Prediction);
                    if(Prediction > 0.5) System.out.println("Classification: "+1);
                    else System.out.println("Classification: "+0);
                    System.exit(cont);
                }
                
            }
            else block();   
            myGui.txt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   x1 = Double.parseDouble(msg3.getContent());  
                   x2 = Double.parseDouble(msg3.getContent()); 
                   x3 = Double.parseDouble(msg3.getContent());  
                   cont=1;
                }
            });            
        }

        @Override
        public boolean done() {
           if(cont == 1){
                return true;
            }else{
                return false;
            }
        }
        
        @Override
        public int onEnd() {
            //myAgent.doDelete();
            return super.onEnd();
        }
           
        }
}

