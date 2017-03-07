/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aulaintegracaocontinua;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author washington-pc
 */
public class Conexao {

    private static final String SENHA = "123456";
    private static final String USUARIO = "postgres";
    private static final String URL = "jdbc:postgresql:treinamento";
    private static final String DRIVER = "org.postgresql.Driver";
    
    private Conexao(){}

    public static Connection getConexao() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
