package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Melvina
 */
public class ModelLaundry {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/laundry";
    static final String USER = "root";
    static final String PASS = ""; 
    public Connection koneksi;
    public Statement statement;
    int jmlData;
    public ModelLaundry() {
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (java.sql.Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("KONEKSI BERHASIL!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("KONEKSI GAGAL!");
        }   
    }  
    public void inputdatapelanggan(String nama, int kg_cucian, String jenis, String tipe, int tanggal_masuk, int tanggal_keluar, int harga){
        try {
            String query = "INSERT INTO `pelanggan`(`id_pelanggan`, `nama`,`kg_cucian`, `jenis`, `tipe`, `tanggal_masuk`, `tanggal_keluar`, `harga`) VALUES (NULL,'"+nama+"','"+kg_cucian+"','"+jenis+"','"+tipe+"','"+tanggal_masuk+"','"+tanggal_keluar+"','"+harga+"')";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"BERHASIL MEMASUKKAN DATA!");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     }
    
    public String[][] readDataPelanggan(){
         String[][] data =new String[jumlahDataPelanggan()][8];
        try{
          int totaldata = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
          String query = "SELECT * FROM `pelanggan`"; //proses pengambilan data
          statement = koneksi.createStatement();
          ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
          while(resultSet.next()){ //konversi tabel ke string
              data[totaldata][0] = resultSet.getString("id_pelanggan"); 
              data[totaldata][1] = resultSet.getString("nama"); 
              data[totaldata][2] = resultSet.getString("kg_cucian");
              data[totaldata][3] = resultSet.getString("jenis");
              data[totaldata][4] = resultSet.getString("tipe");
              data[totaldata][5] = resultSet.getString("tanggal_masuk");
              data[totaldata][6] = resultSet.getString("tanggal_keluar");
              data[totaldata][7] = resultSet.getString("harga");
              totaldata++; 
          }
      }catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL Error");
      }
      return data;
    }
    
    public int jumlahDataPelanggan()
    {
        try{
            jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "SELECT * FROM `pelanggan`"; //proses pengambilan data
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                jmlData++; 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return jmlData;
      }

    public boolean login(String username, String password){
        try{
            String query = "SELECT COUNT(*) AS total FROM admin WHERE username = '"+username+"' AND password = BINARY '"+password+"'";
            statement = koneksi.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                if(rs.getString("total").equals("1")){
                    return true;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return false;
    }
    public void hapusDataPelanggan(int id_pelanggan){
         try{
            String query = "DELETE FROM pelanggan WHERE id_pelanggan='"+id_pelanggan+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"BERHASIL MENGHAPUS DATA!!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } 
    }
    public void editDataPelanggan(int id_pelanggan, String nama, int kg_cucian, String jenis, String tipe, int tanggal_masuk, int tanggal_keluar, int harga){
         try {
            String query = "UPDATE pelanggan SET nama='"+nama+"', kg_cucian='"+kg_cucian+"', jenis='"+jenis+"', tipe='"+tipe+"', tanggal_masuk='"+tanggal_masuk+"', tanggal_keluar='"+tanggal_keluar+"', harga='"+harga+"' WHERE id_pelanggan='"+id_pelanggan+"'";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            System.out.println("BERHASIL MENGUBAH DATA");
            JOptionPane.showMessageDialog(null,"BERHASIL MENGUBAH DATA!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }   
    }   
  
}
