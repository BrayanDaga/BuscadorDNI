package com.brayan.testapi;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CopyToClipboardExample {

	public static void main(String[] args) {
	
		  // Crear la ventana principal
        JFrame frame = new JFrame("Copiar al Portapapeles");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un campo de texto
        JTextField textField = new JTextField(20);

        // Crear un botón para copiar
        JButton copyButton = new JButton("Copiar");

        // Añadir el ActionListener al botón
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el texto del campo de texto
                String text = textField.getText();
                
                // Crear un StringSelection con el texto
                StringSelection stringSelection = new StringSelection(text);

                // Obtener el portapapeles del sistema
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                // Poner el texto en el portapapeles
                clipboard.setContents(stringSelection, null);

                // Mostrar un mensaje de confirmación
                JOptionPane.showMessageDialog(frame, "Texto copiado al portapapeles");
            }
        });

        // Añadir los componentes al panel
        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(copyButton);

        // Añadir el panel al frame y hacerlo visible
        frame.add(panel);
        frame.setVisible(true);
	}

}
