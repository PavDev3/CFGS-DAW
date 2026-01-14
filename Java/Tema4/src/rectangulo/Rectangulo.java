package rectangulo;

public class Rectangulo {
		   private double ancho = 1.0D;
		   private double longitud = 1.0D;

		   public void setLongitud(double var1) {
		      if (var1 > 0.0D && var1 < 20.0D) {
		         this.longitud = var1;
		      }

		   }

		   public double getLongitud() {
		      return this.longitud;
		   }

		   public void setAncho(double var1) {
		      if (var1 > 0.0D && var1 < 20.0D) {
		         this.ancho = var1;
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


