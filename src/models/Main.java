package models;
import java.io.FileInputStream;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<DuplaNumeros> dupla_numeros = new ArrayList<DuplaNumeros>();
		BDConnect.Estabelecer_Conexao();		

		dupla_numeros.add(new DuplaNumeros(0, 2));
		dupla_numeros.add(new DuplaNumeros(1, 0));
		dupla_numeros.add(new DuplaNumeros(2, 0));
		dupla_numeros.add(new DuplaNumeros(3, 0));
		dupla_numeros.add(new DuplaNumeros(4, 0));
		dupla_numeros.add(new DuplaNumeros(5, 7));
		dupla_numeros.add(new DuplaNumeros(6, 7));
		dupla_numeros.add(new DuplaNumeros(6, 7));

		String path = "C:\\Users\\om_pe\\Documents\\Trabalhos da Faculdade\\2022.1\\INF1416\\T4\\DigitalVault\\Pacote-T4\\Keys\\admin-pkcs8-des.key";
		
		
		LoginNameAuthenticator.getInstance().Iniciar_Validacao();
		LoginNameAuthenticator.getInstance().Validar_Email("admin@inf1416.puc-rio.br");
		LoginNameAuthenticator.getInstance().Validar_Senha(dupla_numeros);
		LoginNameAuthenticator.getInstance().Validar_ChavePrivada(path, "admin");
		
		Usuario user = Usuario.getInstance();
		if(user != null)
		{
			path = "C:\\Users\\om_pe\\Documents\\Trabalhos da Faculdade\\2022.1\\INF1416\\T4\\DigitalVault\\Pacote-T4\\Files";
			ArrayList<Arquivo> arquivos = user.Parse_Index(path);
			user.Decriptar_Arquivo(arquivos.get(0));
			user.Decriptar_Arquivo(arquivos.get(1));
		}
	}
}
