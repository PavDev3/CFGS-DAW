package simulacroV2;

public abstract class CentroOperativo {
	protected String codigo;
	protected Zona zona;
	Responsable responsable;
	
	protected int[][] operaciones;
	protected int[][] incidencias;
	
	// constructor
	
	public CentroOperativo(String codigo, Zona zona, Responsable responsable) {
		this.codigo = codigo;
		this.zona = zona;
		this.responsable = responsable;
		this.operaciones = new int[5][2]; //  
		this.incidencias = new int[5][2]; 
	}
	
	 // getters y setters
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Zona getZona() {
		return zona;
	}
	
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	public Responsable getResponsable() {
		return responsable;
	}
	
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	
	public int[][] getOperaciones() {
		return operaciones;
	}
	
	public void setOperaciones(int[][] operaciones) {
		this.operaciones = operaciones;
	}
	
	public int[][] getIncidencias() {
		return incidencias;
	}
	
	public void setIncidencias(int[][] incidencias) {
		this.incidencias = incidencias;
	}
	
	// metodos 
	
	public abstract int calculartotalOperaciones();
	public abstract int calcularTotalIncidencias();
	public abstract int calcularOperacionesDia(int dia);
	public abstract int calcularIncidenciasDia(int dia);
	public abstract double calcularTasaIncidencias();
	
	
	// toString
	
	@Override
	public String toString() {
		return "CentroOperativo [codigo=" + codigo + ", zona=" + zona + ", responsable=" + responsable + "]";
	}
	

}
