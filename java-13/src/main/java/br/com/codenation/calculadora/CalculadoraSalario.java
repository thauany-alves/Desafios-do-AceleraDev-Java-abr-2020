package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase<1039){
			return Math.round(0.0);
		}
		double salarioLiquido = calcularInss(salarioBase);
		salarioLiquido = calcularIRRF(salarioLiquido);

		return (int) Math.round(salarioLiquido);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		double inss = 1.0;
		if (salarioBase<=1500){
			inss = 0.92;
		}else if (salarioBase<=4000){
			inss = 0.91;
		}else if (salarioBase>4000){
			inss = 0.89;
		}
		return (salarioBase*inss);
	}

	private double calcularIRRF(double salarioBase){
		double irrf = 1.0;
		if (salarioBase>=3000.01 && salarioBase<=6000){
			irrf= 0.925;
		}else if (salarioBase>6000){
			irrf= 0.85;
		}
		return (salarioBase*irrf);
	}

}