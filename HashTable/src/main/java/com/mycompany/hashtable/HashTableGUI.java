
package com.mycompany.hashtable;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashTableGUI extends JFrame {
    private HashTable hashTable;

    public HashTableGUI() {
        hashTable = new HashTable(10);

        setTitle("Tabla Hash con Probing Cuadrático");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblKey = new JLabel("Clave:");
        lblKey.setBounds(20, 20, 100, 25);
        add(lblKey);

        JTextField txtKey = new JTextField();
        txtKey.setBounds(120, 20, 200, 25);
        add(txtKey);

        JLabel lblValue = new JLabel("Valor:");
        lblValue.setBounds(20, 60, 100, 25);
        add(lblValue);

        JTextField txtValue = new JTextField();
        txtValue.setBounds(120, 60, 200, 25);
        add(txtValue);

        JButton btnInsert = new JButton("Insertar");
        btnInsert.setBounds(20, 100, 100, 30);
        add(btnInsert);

        JButton btnSearch = new JButton("Buscar");
        btnSearch.setBounds(140, 100, 100, 30);
        add(btnSearch);

        JButton btnDelete = new JButton("Eliminar");
        btnDelete.setBounds(260, 100, 100, 30);
        add(btnDelete);
        
        JButton btnLoadFile= new JButton("Cargar archivo");
        btnLoadFile.setBounds(20, 260, 150, 30);
        add(btnLoadFile);
        
        JButton btnShowAll = new JButton("Mostrar todo");
        btnShowAll.setBounds(200, 260, 150, 30);
        add(btnShowAll);

        JTextArea output = new JTextArea();
        output.setBounds(20, 150, 340, 100);
        output.setEditable(false);
        add(output);

        
    btnShowAll.addActionListener((ActionEvent e) -> {
    String allData = hashTable.toString();
    if (allData.isEmpty()) {
        output.setText("La tabla hash está vacía.");
    } else {
        output.setText(allData);
    }
}); 
        
        
btnInsert.addActionListener((ActionEvent e) -> {
    String key = txtKey.getText();
    String value = txtValue.getText();
    if (key.isEmpty() || value.isEmpty()) {
        output.setText("Por favor, ingrese una clave y un valor.");
    } else {
        hashTable.put(key, value);
        output.setText("Insertado: " + key + " -> " + value);
    }
});

btnSearch.addActionListener((ActionEvent e) -> {
    String key = txtKey.getText();
    if (key.isEmpty()) {
        output.setText("Por favor, ingrese una clave para buscar.");
    } else {
        String result = hashTable.search(key);
        if (result != null) {
            output.setText("Valor encontrado: " + result);
        } else {
            output.setText("Clave no encontrada.");
        }
    }
});

    btnDelete.addActionListener((ActionEvent e) -> {
        String key = txtKey.getText();
        boolean removed = hashTable.remove(key);
        if (removed) {
            output.setText("Clave eliminada: " + key);
        } else {
            output.setText("Clave no encontrada.");
    }
});

    
btnLoadFile.addActionListener((ActionEvent e) -> {
    JFileChooser fileChooser= new JFileChooser();
    int option= fileChooser.showOpenDialog(this);
    if(option== JFileChooser.APPROVE_OPTION){
        java.io.File file= fileChooser.getSelectedFile();
        try(java.util.Scanner scanner= new java.util.Scanner(file)){
            int count= 0;
            while(scanner.hasNextLine()){
                String line= scanner.nextLine();
                String[]parts= line.split(",");
                if(parts.length>= 2){
                    String key= parts[0].trim();
                    String value= parts[1].trim();
                    hashTable.put(key, value);
                    count++;
                }
            }
            output.setText("Archivo cargado con éxito. Total claves insertadas: " + count);
        } catch (Exception ex) {
            output.setText("Error al leer el archivo: " );
        }
    }
});

    

        setVisible(true);
    }

    public static void main(String[] args) {
        HashTableGUI HashTableGUI = new HashTableGUI();
    }
}