package com.davio.main;
import com.davio.utils.FakeFactory;
import com.davio.utils.SystemFactory;
import com.davio.utils.View;

/*
 O propósito desse sistema é auxiliar no combate a covid-19
 armazendo e provendo dados estatísticos afim de entender
 as característica dos infectados. O sistema deve manipular
 Registro, Atendente e Paciente.
*/

public class Executora {
	public static void main(String[] args) {
		Hospital hospital = new Hospital("Hospital UCB");
		
		SystemFactory factory = new FakeFactory();
		
		factory.popularSistema(hospital);
		View.telaInicial(hospital);
	}
}
