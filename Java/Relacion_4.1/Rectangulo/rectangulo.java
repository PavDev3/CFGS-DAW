public class Rectangulo{
	// Atributos 
	private double ancho = 1;
	private double longitud = 1;
	
	public Rectangulo() {}
	
	public void setLongitud(double longitud){
		
		if (longitud > 0 && longitud < 20){
			this.longitud = longitud;
			}
		}
	
	public double getLongitud(){
		return longitud;
	}
	
	public void setAncho(double ancho){
		
		if (ancho > 0 && ancho < 20){
			this.ancho = ancho;
			}
		}
	
	public double getAncho(){	
		return ancho;
	}
	
	public double calcularArea(){
		return ancho * longitud;
	}
	
	public double calcularPerimetro(){
		return 2 * (ancho + longitud);
	}
	
	
}