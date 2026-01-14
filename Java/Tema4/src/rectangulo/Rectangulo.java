package rectangulo;

public class Rectangulo {
			// Atributos
		   private double ancho = 1.0D;
		   private double longitud = 1.0D;

		   	// Constructor
		   public void setLongitud(double longitud) {
		      if (longitud > 0.0D && longitud < 20.0D) {
		         this.longitud = longitud;
		      }

		   }

		   public double getLongitud() {
		      return this.longitud;
		   }

		   public void setAncho(double ancho) {
		      if (ancho > 0.0D && ancho < 20.0D) {
		         this.ancho = ancho;
		      }

		   }

		   public double getAncho() {
		      return this.ancho;
		   }

		   public double calcularArea() {
		      return this.ancho * this.longitud;
		   }

		   public double calcularPerimetro() {
		      return 2.0D * (this.ancho + this.longitud);
		   }
}


