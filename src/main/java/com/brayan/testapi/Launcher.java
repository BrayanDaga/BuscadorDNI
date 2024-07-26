package com.brayan.testapi;

import javax.swing.ImageIcon;

public class Launcher {

	public static void main(String[] args) {
		ImageIcon image = new ImageIcon("..");
		BuscadorDNI buscadorDNI = new  BuscadorDNI();
		buscadorDNI.setVisible(true);
	}

}
