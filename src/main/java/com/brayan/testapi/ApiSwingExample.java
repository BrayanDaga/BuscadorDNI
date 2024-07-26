package com.brayan.testapi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ApiSwingExample {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts/1"; // URL de la API

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Consumo de API");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el botón y los campos de texto
        JButton button = new JButton("Consultar API");
        JTextField idField = new JTextField(10);
        JTextField titleField = new JTextField(20);

        // Añadir el ActionListener al botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = makeApiRequest(API_URL);
                if (response != null) {
                    try {
                    	System.out.println(response);
                        JSONObject jsonResponse = new JSONObject(response);
                        String id = String.valueOf(jsonResponse.getInt("id")) ;
                        String title = jsonResponse.getString("title");

                        idField.setText(id);
                        titleField.setText(title);
                    } catch (Exception ex) {
                        idField.setText("Error");
                        titleField.setText("Error");
                    }
                } else {
                    idField.setText("Error");
                    titleField.setText("Error");
                }
            }
        });

        // Añadir los componentes al panel
        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);

        // Añadir el panel al frame y hacerlo visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para hacer la solicitud a la API
    private static String makeApiRequest(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            result.append("Error: ").append(e.getMessage());
        }
        return result.toString();
    }

}
