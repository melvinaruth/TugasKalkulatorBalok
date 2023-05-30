/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laundry;

import controller.ControllerLogin;
import model.ModelLaundry;
import view.ViewLogin;

/**
 *
 * @author Acer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewLogin VL=new ViewLogin();
        ModelLaundry MH=new ModelLaundry();
        ControllerLogin CL=new ControllerLogin(VL,MH);
        VL.setVisible(true);
    }
    
}
