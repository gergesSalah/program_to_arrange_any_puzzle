
package puzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FRAME extends JFrame implements ActionListener {
        
    JButton b1 = new JButton("1");Font newButtonFont=new Font(b1.getFont().getName(),b1.getFont().getStyle(),35);
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b_ = new JButton("_");
    
    JButton moveBtn = new JButton("move");
    
                      //   up    down left  right
    boolean direction[]={false,false,false,false};

    String[][] startMatrix = {
        {"2", "_", "3"},
        {"1","4","6"},
        {"7","5","8"},
    };
    String[][] goalMatrix = { 
        {"1", "2", "3"},
        {"4","5","6"},
        {"7","8","_"},
    };


    
    
    
    FRAME(){// just design
        setbuttons();
        this.setVisible(true);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("puzzle");
        this.setLocation(250, 25);
        this.setResizable(false);
        this.setLayout(null);
        
        b1.setFont(newButtonFont);b2.setFont(newButtonFont);b3.setFont(newButtonFont);
        b4.setFont(newButtonFont);b5.setFont(newButtonFont);b6.setFont(newButtonFont);
        b7.setFont(newButtonFont);b8.setFont(newButtonFont);b_.setFont(newButtonFont);
        moveBtn.setFont(newButtonFont);
        
        JPanel panel1 = new JPanel(new GridLayout(3, 3));
        JPanel panel2 = new JPanel(null);
        
        panel1.setBackground(Color.yellow.darker().darker().darker());
        panel2.setBackground(Color.blue.darker().darker().darker().darker().darker());
        
        panel1.setBounds(0, 0, 1000, 650);
        panel2.setBounds(0, 650, 1000, 150);
        
        this.add(panel1);
        this.add(panel2);
        
        b1.setForeground(Color.white.darker());b1.setBackground(Color.blue.darker().darker().darker().darker());
        b2.setForeground(Color.white.darker());b2.setBackground(Color.blue.darker().darker().darker().darker());
        b3.setForeground(Color.white.darker());b3.setBackground(Color.blue.darker().darker().darker().darker());
        b4.setForeground(Color.white.darker());b4.setBackground(Color.blue.darker().darker().darker().darker());
        b5.setForeground(Color.white.darker());b5.setBackground(Color.blue.darker().darker().darker().darker());
        b6.setForeground(Color.white.darker());b6.setBackground(Color.blue.darker().darker().darker().darker());
        b7.setForeground(Color.white.darker());b7.setBackground(Color.blue.darker().darker().darker().darker());
        b8.setForeground(Color.white.darker());b8.setBackground(Color.blue.darker().darker().darker().darker());
        b_.setForeground(Color.white.darker());b_.setBackground(Color.blue.darker().darker().darker().darker());
        moveBtn.setForeground(Color.white.darker());moveBtn.setBackground(Color.blue.darker().darker().darker().darker());
        
        panel1.add(b1);panel1.add(b2);panel1.add(b3);panel1.add(b4);
        panel1.add(b5);panel1.add(b6);panel1.add(b7);panel1.add(b8);
        panel1.add(b_);
        
        moveBtn.setForeground(Color.white);moveBtn.setBackground(Color.black);
        moveBtn.setBounds(333, 30, 333, 50);
        panel2.add(moveBtn);
        
        moveBtn.addActionListener(this);
         
    }
    
    protected int find_misses(String[][] starMatrixs){
        int missCount = 0;
        for(int i =0 ;i<3;i++)
            for(int j = 0;j<3 ; j++)
               if(starMatrixs[i][j] != goalMatrix[i][j]){
                   missCount++; 
               }
       return missCount;            
    }
    
    protected void search_for_b_ndx(String[][] starMatrixs,int XYOf_[]){ //this function to search for '_' indexs ,here i used array because send parameter as areferance
        for (int i = 0 ; i< 3; i++)
            for(int j = 0 ; j< 3 ; j++)
                if(starMatrixs[i][j] == "_"){
                    XYOf_[0] = i;
                    XYOf_[1] = j;
                }              
    }
    
    protected void possibleAction(String startMatrixs[][]){// to know what is the direction which is available to move
        int xyNdxOf_[] = {0,0};
        search_for_b_ndx(startMatrixs, xyNdxOf_);
        switch(xyNdxOf_[0]){
            case 0 :
                direction[1] = true;
                break;
            case 1 :
                direction[1] = true;
                direction[0] = true;
            case 2 :
                direction[0] = true;                
        }
        switch(xyNdxOf_[1]){
            case 0 :
                direction[3] = true;
                break;
            case 1 :
                direction[3] = true;
                direction[2] = true;
            case 2 :
                direction[2] = true;                
        }
    }
    
    protected void copyMatrix(String copyMatrix[][]){//this function to make a copy matrix for startMatrix, i will use it in move function 
        for(int i = 0 ;i<3;i++)
            for(int j = 0;j<3;j++)
                copyMatrix[i][j] = startMatrix[i][j];
    }
    
    protected void falseddirectioins(){// to give false value to each direction after move 
        for (int i = 0; i < 4; i++) {
            direction[i] = false;
        }
    } 
    
    
    protected void UPSwap(String matrix[][]){ // to swap 
        int xyOf_[]={0,0} ; search_for_b_ndx(startMatrix, xyOf_);
        String temp = matrix[xyOf_[0]][xyOf_[1]];
        matrix[xyOf_[0]][xyOf_[1]] = matrix[(xyOf_[0]-1)][xyOf_[1]];
        matrix[(xyOf_[0]-1)][xyOf_[1]] = temp;
    }   
    protected void DownSwap(String matrix[][]){
        int xyOf_[]={0,0} ; search_for_b_ndx(startMatrix, xyOf_);
        String temp = matrix[xyOf_[0]][xyOf_[1]];
        matrix[xyOf_[0]][xyOf_[1]] = matrix[(xyOf_[0]+1)][xyOf_[1]];
        matrix[(xyOf_[0]+1)][xyOf_[1]] = temp;
    }
    protected void leftSwap(String matrix[][]){
        int xyOf_[]={0,0} ; search_for_b_ndx(startMatrix, xyOf_);
        String temp = matrix[xyOf_[0]][xyOf_[1]];
        matrix[xyOf_[0]][xyOf_[1]] = matrix[(xyOf_[0])][xyOf_[1]-1];
        matrix[(xyOf_[0])][xyOf_[1]-1] = temp;
    }
    protected void rightSwap(String matrix[][]){
        int xyOf_[]={0,0} ; search_for_b_ndx(startMatrix, xyOf_);
        String temp = matrix[xyOf_[0]][xyOf_[1]];
        matrix[xyOf_[0]][xyOf_[1]] = matrix[(xyOf_[0])][xyOf_[1]+1];
        matrix[(xyOf_[0])][xyOf_[1]+1] = temp;
    }
    
    
    protected void move(){
        int upMisses = 10,downMisses = 10,leftMisses = 10,rightMisses = 10;
        
        int xyOf_[]={0,0} ; search_for_b_ndx(startMatrix, xyOf_);
        
        falseddirectioins();
        possibleAction(startMatrix);
        
        
        if(direction[0] == true){
            String copyStartmatrix[][] = {
                {"0","0","0"},
                {"0","0","0"},
                {"0","0","0"},
            };
            copyMatrix(copyStartmatrix); 
            UPSwap(copyStartmatrix);
            
            for (int i = 0; i < 3; i++){//to show what happen in programme in output screen you can make it comment
                for(int j = 0; j<3;j++){
                    System.out.print(copyStartmatrix[i][j] + " ");
                }
                System.out.println();
            }
            
            upMisses = find_misses(copyStartmatrix);
            System.out.println("upmisses = " + upMisses);//to show what happen in programme in output screen you can make it comment
        }
        if(direction[1] == true){
            String copyStartmatrix[][] = {
                {"0","0","0"},
                {"0","0","0"},
                {"0","0","0"},
            };
            copyMatrix(copyStartmatrix);
                        
            DownSwap(copyStartmatrix);
            
            for (int i = 0; i < 3; i++){//to show what happen in programme in output screen you can make it comment
                for(int j = 0; j<3;j++){
                    System.out.print(copyStartmatrix[i][j] + " ");
                }
                System.out.println();
            }
            
            downMisses = find_misses(copyStartmatrix);
            System.out.println("downmisses = " +downMisses);//to show what happen in programme in output screen you can make it comment
        }
        if(direction[2] == true){
            String copyStartmatrix[][] = {
                {"0","0","0"},
                {"0","0","0"},
                {"0","0","0"},
            };
            copyMatrix(copyStartmatrix);
               
            leftSwap(copyStartmatrix);
            
            for (int i = 0; i < 3; i++){//to show what happen in programme in output screen you can make it comment
                for(int j = 0; j<3;j++){
                    System.out.print(copyStartmatrix[i][j] + " ");
                }
                System.out.println();
            }
            
            leftMisses = find_misses(copyStartmatrix);
            System.out.println("leftmisses = " + leftMisses);//to show what happen in programme in output screen you can make it comment
            
        }
        if(direction[3] == true){
            String copyStartmatrix[][] = {
                {"0","0","0"},
                {"0","0","0"},
                {"0","0","0"},
            };
            copyMatrix(copyStartmatrix);
            
            rightSwap(copyStartmatrix);
            
            for (int i = 0; i < 3; i++){//to show what happen in programme in output screen you can make it comment
                for(int j = 0; j<3;j++){
                    System.out.print(copyStartmatrix[i][j] + " ");
                }
                System.out.println();
            }
            
            rightMisses = find_misses(copyStartmatrix);
            System.out.println("rightmisses = " +rightMisses);//to show what happen in programme in output screen you can make it comment
        }       
        
        if(downMisses<=upMisses && downMisses<=leftMisses && downMisses<=rightMisses ){
            DownSwap(startMatrix);
        }
        else if(leftMisses<=upMisses && leftMisses<=downMisses && leftMisses<=rightMisses ){
            leftSwap(startMatrix);
        }
        else if(upMisses<=downMisses&&upMisses<=leftMisses&&upMisses<=rightMisses){
           //swap in orginal matrix
            UPSwap(startMatrix);
        }
        
        else if(rightMisses<=upMisses && rightMisses<=downMisses && rightMisses<=leftMisses ){
            rightSwap(startMatrix);
        }
        
        setbuttons();
        System.out.println("_____________________");//to show what happen in programme in output screen you can make it comment
    }
    
    protected void setbuttons(){
        b1.setText(startMatrix[0][0]);
        b2.setText(startMatrix[0][1]);
        b3.setText(startMatrix[0][2]);
        b4.setText(startMatrix[1][0]);
        b5.setText(startMatrix[1][1]);
        b6.setText(startMatrix[1][2]);
        b7.setText(startMatrix[2][0]);
        b8.setText(startMatrix[2][1]);
        b_.setText(startMatrix[2][2]);      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == moveBtn){
            if (find_misses(startMatrix)==0) {
                JOptionPane.showMessageDialog(null, "                 DONE");
            }
            else{
                
                    move();
            }
        }
            
    }
    
}
