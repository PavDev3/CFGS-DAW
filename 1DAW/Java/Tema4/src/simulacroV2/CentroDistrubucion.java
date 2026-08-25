package simulacroV2;

public class CentroDistrubucion extends CentroOperativo {
	// Atributo 
	private int paquetesUrgentes;
	
	// constructor
	public CentroDistrubucion(String codigo, Zona zona, Responsable responsable, int paquetesUrgentes)
	{
		super(codigo, zona, responsable);
		this.paquetesUrgentes = paquetesUrgentes;
	}
	
	// getter y setter
	public int getPaquetesUrgentes() {
		
	}
	

}
