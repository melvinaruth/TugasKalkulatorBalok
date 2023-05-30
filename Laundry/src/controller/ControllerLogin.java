/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelLaundry;
import view.ViewDataPelanggan;
import view.ViewLogin;

/**
 *
 * @author Acer
 */
public class ControllerLogin {
    ViewLogin VL = new ViewLogin();
    ModelLaundry ML = new ModelLaundry();
    public ControllerLogin(ViewLogin VL, ModelLaundry ML){
        this.VL=VL;
        this.ML=ML;
        VL.ButtonLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ML.login(VL.getUsername(), VL.getPassword())){
                    ViewDataPelanggan VDP = new ViewDataPelanggan();
                    ControllerDataPelanggan CDP = new ControllerDataPelanggan(VDP, ML);
                    VDP.setVisible(true);
                    VL.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username/password salah!");
                }
            }
            
        });
    }
    
}
