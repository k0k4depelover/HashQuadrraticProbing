
package com.mycompany.hashtable;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HashTableGUI extends JFrame {
    private HashTable hashTable;

    public HashTableGUI() {
        hashTable = new HashTable(10);

        setTitle("Tabla Hash con Probing Cuadrático");
        setSize(400, 300);
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

        JTextArea output = new JTextArea();
        output.setBounds(20, 150, 340, 100);
        output.setEditable(false);
        add(output);

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


        setVisible(true);
    }

    public static void main(String[] args) {
        HashTableGUI HashTableGUI = new HashTableGUI();
    }
}