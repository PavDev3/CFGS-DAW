package AbstractoEjemplo;

public class Principal {

	public static void main(String[] args) {
	
		Figura[] arrayFiguras= new Figura[4];
		Rectangulo rectangulo;
		
		arrayFiguras[0]= new Rectangulo("ROJO", 15, 2, 3);
		
		arrayFiguras[1]=new Triangulo("ROJO", 3, 8, 10);
		arrayFiguras[2]= new Rectangulo("VERDE", 15, 2, 3);
		
		arrayFiguras[3]=new Triangulo("AZUL", 3, 8, 10);
		
		
		
		
		for (int i = 0; i < arrayFiguras.length; i++) {
			
			System.out.println("El area es :"+ arrayFiguras[i].calcularArea());
			
			if ( arrayFiguras[i] instanceof Rectangulo) {
				System.out.println(arrayFiguras[i]);
				rectangulo= (Rectangulo) arrayFiguras[i];
				
				System.out.println("Lado 1  del rectangulo: " + rectangulo.getLado1());
				
			}
		}
		
		//Lo mismo de otra forma 
		for (int i = 0; i < arrayFiguras.length; i++) {
			
			System.out.println("El area es :"+ arrayFiguras[i].calcularArea());
			
			if ( arrayFiguras[i] instanceof Rectangulo) {
				System.out.println(arrayFiguras[i]);
				
				
				System.out.println("Lado 1 del rectangulo: " +
						 ((Rectangulo) arrayFiguras[i]).getLado1());
				
			}
		}
		
		

	}

}