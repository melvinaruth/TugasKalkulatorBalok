package controller;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import model.ModelLaundry;
import view.ViewDataPelanggan;
import view.ViewLogin;

/**
 *
 * @author Melvina
 */
public class ControllerDataPelanggan {
    ViewDataPelanggan VDP = new ViewDataPelanggan();
    ModelLaundry ML = new ModelLaundry();
    String[][] DataPelanggan;
    public ControllerDataPelanggan(ViewDataPelanggan VDP, ModelLaundry ML){
        this.VDP = VDP;
        this.ML = ML;
        ShowData();
        VDP.ButtonTambah.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ML.inputdatapelanggan(VDP.getNama(), VDP.getBerat(), VDP.getJenis(), VDP.getTipe(), VDP.getTanggalMasuk(), VDP.getTanggalKeluar(), VDP.getHarga());
                ShowData();
                ResetData();
            }
        });
        VDP.ButtonLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               ViewLogin VL = new ViewLogin();
               ControllerLogin CL = new ControllerLogin(VL,ML);
               VL.setVisible(true);
               VDP.dispose();
            }
            
        });
       VDP.TabelOrder.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e){
               super.mouseClicked(e);
               int row = VDP.TabelOrder.getSelectedRow();
               String nama = (String) VDP.TabelOrder.getValueAt(row, 0);
               int input = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu ingin menghapus data atas nama '" +nama+ "'?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                
                if(input==0){
                    ML.hapusDataPelanggan(Integer.parseInt(DataPelanggan[row][0]));
                    ShowData();
                }
                else{
                    int input1 = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu ingin mengedit data atas nama '"+nama+ "'?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
//                    if(input1==0){
//                        ViewEditKamar VEK=new ViewEditKamar();
//                        ControllerEditKamar CEK=new ControllerEditKamar(VEK,MH,DataKamar[row]);
//                        VEK.setVisible(true);
//                        VDP.dispose();
//                    }
                }
           }
           
           
       });
        
        
    }
    void ResetData(){
        VDP.txtNama.setText("");
        VDP.txtKgCucian.setText("");
        VDP.txtJenis.setText("");
        VDP.txtTipe.setText("");
        VDP.txtTanggalMasuk.setText("");
        VDP.txtTanggalKeluar.setText("");
        VDP.txtHarga.setText("");
    }
    void ShowData(){
        DataPelanggan=ML.readDataPelanggan();
        String[][] pelanggan = new String[ML.jumlahDataPelanggan()][7];
        for(int i=0;i<ML.jumlahDataPelanggan();i++){
            for(int j=0;j<7;j++){
                pelanggan[i][j]=DataPelanggan[i][j+1];
            }
        }
        String[] namaKolom={"NAMA","BERAT CUCIAN","JENIS","TIPE","TANGGAL MASUK","TANGGAL KELUAR","HARGA"};
        DefaultTableModel tableModel=new DefaultTableModel(pelanggan,namaKolom){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        VDP.TabelOrder.setModel(tableModel);
    }
    
    
}
