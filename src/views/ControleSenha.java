package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import models.LoginNameAuthenticator;
import models.BDConnect;
import models.Functions;
import models.ParDigitos;
import models.DuplaNumeros;

public class ControleSenha {
	
	private ArrayList<DuplaNumeros> atualValues;
	
	public void callInterfacePassword() {
		InterfaceSenha ip = new InterfaceSenha(5);
		atualValues = new ArrayList<DuplaNumeros>();
		addValueAndActionToButton(ip);
		AddActButtonSend(ip);
		addActResetButton(ip);
		ip.setVisibleScreen();
		
	}
	
	
	 public void addValuetoButton(int num1, int num2, JButton button) {
	 	button.setText(num1 + " - " + num2);
	 	button.setPreferredSize(new Dimension(100,100));
	 }
	 
	 public void addValueAndActionToButton(InterfaceSenha ip) {
	 	int n1, n2;
	 	ArrayList<DuplaNumeros> numeros = Functions.Gerar_Set_Duplas();
	 	for(int i = 0; i <  ip.getButtons().size() ; i++) {
	 		n1 = numeros.get(i).num1;
	 		n2 = numeros.get(i).num2;
	 		JButton button = ip.getButtons().get(i);
	 		addValuetoButton(n1, n2, button);
	 		if(button.getActionListeners().length == 0) {
	 			addActButtonNumbers(button, ip);
	 		}
	 	}
	 }
	 
	 public void getValueBottom(JButton button) {
	 	String text = button.getText().replace(" - ", "");
	 	int num1 = Integer.parseInt(text.substring(0, 1), 10);
	 	int num2 = Integer.parseInt(text.substring(1, 2), 10);
	 	DuplaNumeros dupla = new DuplaNumeros(num1, num2);
	 	atualValues.add(dupla);
	 }
	 
	public void addActResetButton(InterfaceSenha i) { 
		i.getReset().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				atualValues = new ArrayList<DuplaNumeros>();
	        }
			});
	}
	
	public void addActButtonNumbers(JButton button , InterfaceSenha i) {

		button.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e)
        {	     
			getValueBottom(button);
            addValueAndActionToButton(i);
            i.getScreen().revalidate();
        }
		});
	}	
	
	public void AddActButtonSend(InterfaceSenha i) {
		i.getSend().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int validarSenha = LoginNameAuthenticator.getInstance().Validar_Senha(atualValues);
				
				if ( validarSenha == -1)
				{
					atualValues = new ArrayList<DuplaNumeros>();
				}
				if ( validarSenha == -2) {
					BDConnect.Log(3002, LoginNameAuthenticator.getInstance().Get_LoginName());
					i.getScreen().dispose();
					ControleEmail ce =  new ControleEmail();
					ce.callInterfaceEmail();				
				}
				if (validarSenha == 1) {
					BDConnect.Log(3002, LoginNameAuthenticator.getInstance().Get_LoginName());
					i.getScreen().dispose();
					ControlePrivateKey cc = new ControlePrivateKey();
					cc.callControllerCertificado();
				}
				
			}
		});
	}
}
