/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokugame;

import java.sql.Connection;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Tshi
 */
public class SudokuGame {

    /**
     * @param args the command line arguments
     */
    
    public SudokuGame(){
        
        GameGUI objGUI = new GameGUI(3,2);
        objGUI.setResizable(false);
        objGUI.setVisible(true);
        objGUI.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        textArry a = new textArry();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        
    }
    
    
}



/*

 



        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("\nprivate void T"+i+j+"ActionPerformed(java.awt.event.ActionEvent evt) {\n" +
                "    int row ="+i+" ;\n" +
                "    int col = "+j+";\n" +
                "    int val = Integer.parseInt(tf[row][col].getText());\n" +
                "    checkNum.insertVal(row, col, val);\n" );
                
                if (i == 0 || i == 1) {
                    if (j < 3) {
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(0,1,3);\n");
                    }else{
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(0,1,2);\n");
                    }
                }else if (i == 2 || i == 3) {
                    if (j < 3) {
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(2,3,3);\n");
                    }else{
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(2,3,2);\n");
                    }
                }else if(i == 4 || i == 5){
                    if (j < 3) {
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(4,5,3);\n");
                    }else{
                        System.out.print("ArrayList<Integer> dubleNum = checkNum.foundInRigon(4,5,2);\n");
                    }
                }
                
                System.out.print("    boolean ansCol = checkNum.foundInCol(row, col);\n" +
                "    boolean ansRow = checkNum.foundInRow(row, col);\n" +
                "    \n" +
                "    if (dubleNum.size() > 0) {\n" +
                "        for (int i = 0; i < dubleNum.size(); i++) {\n" +
                "            if (dubleNum.get(i) == val) {\n" +
                "                tf[row][col].setBackground(Color.RED);\n" +
                "            }\n" +
                "        }\n" +
                "    }else if (ansCol && ansRow){\n" +
                "        tf[row][col].setBackground(Color.GREEN);\n" +
                "    }else{\n" +
                "        tf[row][col].setBackground(Color.RED);\n" +
                "    }\n"+
                "}");
                
            }
        }





*/