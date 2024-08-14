package com.brayan.buscadorDNI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.json.JSONObject;

import com.brayan.buscadorDNI.exceptions.ErrorHandler;
import com.brayan.buscadorDNI.exceptions.NoInternetException;
import com.brayan.buscadorDNI.utils.InternetChecker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JProgressBar;

public class MainWindow extends JFrame {

	private static final String API_URL = "https://apiperu.dev/api/dni";

	private static final long serialVersionUID = 1L;

	protected static String apiToken = null;

	private JPanel contentPane;
	private JTextField textField_dni;
	private JTextField textField_nombres;
	private JTextField textField_apPaterno;
	private JTextField textField_apMaterno;
	private JTextField textField_numerodni;
	private JLabel lblErrorDNI;
	private Border defaultBorder;
	private JLabel lblNombreCompleto;

	/**
	 * Create the frame.
	 */
	public MainWindow(String apiToken) {

		MainWindow.apiToken = apiToken;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);

		setTitle("Buscador DNI PERU ");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setFont(new Font("Noto Serif", Font.PLAIN, 20));
		lblNewLabel.setBounds(28, 14, 64, 26);
		contentPane.add(lblNewLabel);

		textField_dni = new JTextField();
		textField_dni.setFont(new Font("Serif", Font.PLAIN, 20));
		textField_dni.setBounds(102, 14, 147, 26);
		contentPane.add(textField_dni);
		textField_dni.setColumns(10);

		defaultBorder = textField_dni.getBorder();

		JButton btn_search = new JButton("CERCA");

		btn_search.setFont(new Font("Serif", Font.BOLD, 18));
		btn_search.setBounds(259, 17, 112, 23);
		contentPane.add(btn_search);

		JLabel lblNomi = new JLabel("Nombres");
		lblNomi.setFont(new Font("Noto Serif", Font.PLAIN, 18));
		lblNomi.setBounds(28, 102, 102, 26);
		contentPane.add(lblNomi);

		textField_nombres = new JTextField();
		textField_nombres.setFont(new Font("Serif", Font.PLAIN, 20));
		textField_nombres.setColumns(10);
		textField_nombres.setBounds(208, 102, 250, 26);
		textField_nombres.setEditable(false);
		contentPane.add(textField_nombres);

		JLabel lblNewLabel_1_1 = new JLabel("Apellido Paterno");
		lblNewLabel_1_1.setFont(new Font("Noto Serif", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(28, 151, 156, 26);
		contentPane.add(lblNewLabel_1_1);

		textField_apPaterno = new JTextField();
		textField_apPaterno.setFont(new Font("Serif", Font.PLAIN, 20));
		textField_apPaterno.setColumns(10);
		textField_apPaterno.setBounds(208, 151, 250, 26);
		textField_apPaterno.setEditable(false);
		contentPane.add(textField_apPaterno);

		JLabel lblNewLabel_1_2 = new JLabel("Apellido Materno");
		lblNewLabel_1_2.setFont(new Font("Noto Serif", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(28, 199, 156, 26);
		contentPane.add(lblNewLabel_1_2);

		textField_apMaterno = new JTextField();
		textField_apMaterno.setFont(new Font("Serif", Font.PLAIN, 20));
		textField_apMaterno.setColumns(10);
		textField_apMaterno.setBounds(208, 199, 250, 26);
		textField_apMaterno.setEditable(false);
		contentPane.add(textField_apMaterno);

		JButton buttonCopy1 = new JButton("");
		buttonCopy1.setBounds(480, 98, 32, 32);
		buttonCopy1.setIcon(new ImageIcon(MainWindow.class.getResource("/com/brayan/buscadorDNI/images/copia.png")));
		contentPane.add(buttonCopy1);

		JButton buttonCopy2 = new JButton("");

		buttonCopy2.setIcon(new ImageIcon(MainWindow.class.getResource("/com/brayan/buscadorDNI/images/copia.png")));
		buttonCopy2.setBounds(480, 144, 32, 32);
		contentPane.add(buttonCopy2);

		JButton buttonCopy3 = new JButton("");
		buttonCopy3.setIcon(new ImageIcon(MainWindow.class.getResource("/com/brayan/buscadorDNI/images/copia.png")));
		buttonCopy3.setBounds(480, 194, 32, 32);
		contentPane.add(buttonCopy3);
		
		JButton buttonCopy4 = new JButton("");
		buttonCopy4.setIcon(new ImageIcon(MainWindow.class.getResource("/com/brayan/buscadorDNI/images/copia.png")));
		buttonCopy4.setBounds(480, 243, 32, 32);
		contentPane.add(buttonCopy4);

		JLabel lblNewLabel_1_2_1 = new JLabel("Numero Dni");
		lblNewLabel_1_2_1.setFont(new Font("Noto Serif", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(28, 248, 156, 26);
		contentPane.add(lblNewLabel_1_2_1);

		textField_numerodni = new JTextField();
		textField_numerodni.setFont(new Font("Serif", Font.PLAIN, 20));
		textField_numerodni.setColumns(10);
		textField_numerodni.setBounds(208, 248, 250, 26);
		textField_numerodni.setEditable(false);
		contentPane.add(textField_numerodni);

	

		lblNombreCompleto = new JLabel("");
		lblNombreCompleto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNombreCompleto.setFont(new Font("Noto Serif", Font.PLAIN, 18));
		lblNombreCompleto.setBounds(28, 370, 484, 26);
		contentPane.add(lblNombreCompleto);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(127, 310, 298, 26);
		progressBar.setVisible(false);
		contentPane.add(progressBar);

		JButton btnCancel = new JButton("Cancella");

		btnCancel.setBounds(406, 17, 89, 23);
		contentPane.add(btnCancel);

		lblErrorDNI = new JLabel("New label");
		lblErrorDNI.setFont(new Font("Serif", Font.BOLD, 13));
		lblErrorDNI.setForeground(Color.RED);
		lblErrorDNI.setText("");
		lblErrorDNI.setBounds(102, 50, 147, 13);
		contentPane.add(lblErrorDNI);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarResultados();
				borrarErroresDNI();
			}

		});

		buttonCopy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyToClipboard(textField_nombres);
			}

		});
		buttonCopy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyToClipboard(textField_apPaterno);
			}
		});
		buttonCopy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyToClipboard(textField_apMaterno);
			}
		});
		buttonCopy4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyToClipboard(textField_numerodni);
			}
		});

		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarErroresDNI();

				String dni = textField_dni.getText();

				if (dni.length() == 8) {
					borrarResultados();

					// String response = makePostRequest(API_URL, TOKEN, dni);
					progressBar.setIndeterminate(true);
					progressBar.setVisible(true);
					new SwingWorker<String, Void>() {

						@Override
						protected String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							return makePostRequest(API_URL, apiToken, dni);
						}

						@Override
						protected void done() {
							try {
								String response = get();
								JSONObject jsonResponse = new JSONObject(response);
								if (jsonResponse.getBoolean("success")) {
									JSONObject data = jsonResponse.getJSONObject("data");
									String numero = data.getString("numero");
									String nombreCompleto = data.getString("nombre_completo");
									String nombres = data.getString("nombres");
									String apellidoPaterno = data.getString("apellido_paterno");
									String apellidoMaterno = data.getString("apellido_materno");

									textField_nombres.setText(nombres);
									textField_apPaterno.setText(apellidoPaterno);
									textField_apMaterno.setText(apellidoMaterno);
									textField_numerodni.setText(numero);
									lblNombreCompleto.setText(nombreCompleto);

									System.out
											.println("NÃºmero: " + numero + "\n" + "Nombre Completo: " + nombreCompleto
													+ "\n" + "Nombres: " + nombres + "\n" + "Apellido Paterno: "
													+ apellidoPaterno + "\n" + "Apellido Materno: " + apellidoMaterno);
								} else {
									lblNombreCompleto.setText("No se encontraron datos");
									System.out.println("Consulta fallida");
								}
							} catch (Exception ex) {
								System.err.println("Error al analizar la respuesta: " + ex.getMessage());

							} finally {
								progressBar.setVisible(false);
							}
						}

					}.execute();

				} else {
					textField_dni.setBorder(new LineBorder(Color.RED, 3));
					textField_dni.setForeground(Color.RED);

					lblErrorDNI.setText("El DNI debe tener 8 digitos");
				}

			}
		});
	}

	protected void borrarResultados() {
		textField_dni.setText("");
		textField_nombres.setText("");
		textField_apPaterno.setText("");
		textField_apMaterno.setText("");
		textField_numerodni.setText("");
		lblNombreCompleto.setText("");
	}

	protected void borrarErroresDNI() {
		// TODO Auto-generated method stub
		lblErrorDNI.setText("");
		textField_dni.setBorder(defaultBorder);
		textField_dni.setForeground(Color.BLACK);

	}

	protected void copyToClipboard(JTextField textField) {
		String textcopy = textField.getText();
		StringSelection stringSelection = new StringSelection(textcopy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

	}
	protected String makePostRequest(String apiUrl, String token, String dni) throws Exception {
	    try {
	        // Verificar conexión a Internet antes de hacer la solicitud
	        InternetChecker.checkInternetConnection();

	        @SuppressWarnings("deprecation")
	        URL url = new URL(apiUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setDoOutput(true);

	        // Crear el cuerpo de la solicitud
	        JSONObject jsonBody = new JSONObject();
	        jsonBody.put("dni", dni);
	        String body = jsonBody.toString();

	        // Escribir el cuerpo de la solicitud
	        try (OutputStream os = conn.getOutputStream()) {
	            byte[] input = body.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        // Leer la respuesta
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	        StringBuilder response = new StringBuilder();
	        String responseLine;
	        while ((responseLine = br.readLine()) != null) {
	            response.append(responseLine.trim());
	        }
	        br.close();
	        conn.disconnect();

	        return response.toString();
	    } catch (NoInternetException e) {
	        // Manejar la excepción de falta de conexión a Internet
	        JOptionPane.showMessageDialog(null, "No hay conexión a Internet. Por favor, verifica tu conexión.");
	        ErrorHandler.throwNotInternetException(
	                "No hay conexión a Internet. Por favor, verifica tu conexión",
	                new Throwable(e.getMessage()));
	        return null;
	    } catch (Exception e) {
	        // Manejar otras excepciones
	        JOptionPane.showMessageDialog(null, e.getMessage());
	        ErrorHandler.throwIOExceptionWithCause(
	                "Compruebe si el valor de API_TOKEN no esté vacío y/o sea correcto en el archivo .env",
	                new Throwable(e.getMessage()));

	        return null;
	    }
	}
}
