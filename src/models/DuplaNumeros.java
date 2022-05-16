package models;

public class DuplaNumeros {
	public int num1;
	public int num2;
	
	public DuplaNumeros(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public boolean Match(int num) {
		if(num1 == num || num2 == num)
			return true;
		else
			return false;
	}

}
