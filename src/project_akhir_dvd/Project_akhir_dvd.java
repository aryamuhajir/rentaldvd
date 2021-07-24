/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_akhir_dvd;

import java.sql.Connection;

/**
 *
 * @author hajir
 */
public class Project_akhir_dvd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = KoneksiDB.Koneksi("manager");
    }
    
}
