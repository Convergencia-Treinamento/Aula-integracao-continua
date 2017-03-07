/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aulaintegracaocontinua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author washington-pc
 */
public class AulaJDBC {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    private AulaJDBC(){}
    
    public static void main(String[] args) throws SQLException {
        AulaJDBC a = new AulaJDBC();
        try {
            List<Pessoa> pessoas = a.carregarPessoas();
            if (pessoas != null && !pessoas.isEmpty()) {
                Iterator<Pessoa> it = pessoas.iterator();
                while (it.hasNext()) {
                    Pessoa pessoa = it.next();
                    a.insert(pessoa);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AulaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(Pessoa pessoa) {

        try (Connection con = Conexao.getConexao()) {
            PreparedStatement pstmt;
            pstmt = con.prepareStatement("insert into cliente (id, nome, cpfcnpj) values (?, ?, ?)");
            pstmt.setInt(1, pessoa.getId());
            pstmt.setString(2, pessoa.getNome());
            pstmt.setString(3, pessoa.getCfpCnpj());
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AulaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pessoa> carregarPessoas() throws FileNotFoundException {
        List<Pessoa> pessoas = new ArrayList<>();
        String linha;
        FileReader fileReader = new FileReader("D:\\Estudos\\Treinamento\\pessoa.csv");
        try (BufferedReader br = new BufferedReader(fileReader)) {
            linha = br.readLine();
            Logger.getLogger(linha);
            while ((linha = br.readLine()) != null) {
                String[] cliente = linha.split("[|]");

                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.valueOf(cliente[0]));
                pessoa.setNome(cliente[1]);
                pessoa.setCfpCnpj(cliente[2]);
                pessoas.add(pessoa);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AulaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AulaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pessoas;
    }

}
