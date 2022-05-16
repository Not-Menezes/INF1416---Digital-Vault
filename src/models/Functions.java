package models;
import java.util.ArrayList;
import java.util.Random;

public class Functions {
	
	public static ArrayList<DuplaNumeros> Gerar_Set_Duplas()
	{
		ArrayList<DuplaNumeros> dupa_numeros = new ArrayList<DuplaNumeros>();
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
		for (int i = 0; i <= 9; i++) 
			numeros.add(i);

		
		for(int i = 0; i < 5; i++)
		{
			Random rnd = new Random();
			
			int index = rnd.nextInt(numeros.size());
			int num1 = numeros.get(index);
			numeros.remove(index);
			index = rnd.nextInt(numeros.size());
			int num2 = numeros.get(index);
			numeros.remove(index);
			
			dupa_numeros.add(new DuplaNumeros(num1, num2));
		}
		
		return dupa_numeros;
	}
	
	public static boolean Validar_Padrao_Senha(String senha) {
		
		if(senha.length() < 8 || senha.length() > 10) 
		{
			System.out.println("Tamanho da senha invalido");
			return false;
		}
		
		if (!senha.matches("[0-9]+"))
		{
			System.out.println("Senha precisa ser apenas números");
			return false;
		}
		
		for (int i = 0; i < senha.length() - 1; i++)
		{
			String sub1 = senha.substring(i, i+1);
			String sub2 = senha.substring(i+1, i+2);
			if (sub1.compareTo(sub2) == 0)
			{
				System.out.println("Não são aceitas sequências de números repetidos");
				return false;
			}
		}
		
		return true;
	}
	
	public static String Get_Random_SALT()
	{
		String alfabeto = "abcdefghijklmnopqrstuvywxzABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";
		
		Random rnd = new Random();
		
		String SALT = "";
		
		for(int i = 0; i < 10; i++)
		{
			SALT += alfabeto.charAt(rnd.nextInt(alfabeto.length()));
		}
		
		return SALT;
	}
	
	public static byte[] Random_Byte_Array(int size)
	{
		Random rnd = new Random();
		byte[] random_bytes = new byte[size];
		
		rnd.nextBytes(random_bytes);
		
		return random_bytes;
	}
	
	public static String Byte_to_Hex(byte[] bytes) 
	{
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < bytes.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
		
		return buf.toString();
	}
	
	public static byte[] Hex_to_Byte(String hex)
	{
		byte[] val = new byte[hex.length() / 2];
		for (int i = 0; i < val.length; i++) {
		   int index = i * 2;
		   int j = Integer.parseInt(hex.substring(index, index + 2), 16);
		   val[i] = (byte) j;
		}
		
		return val;
	}
}
