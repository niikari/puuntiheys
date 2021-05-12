package model;

public class Balsalevy {
	
	private int id;
	private double tiheys, paksuus, leveys, paino, pituus;
	private String grain;
	
	public Balsalevy(int id, double tiheys, double paksuus, double leveys, double paino, double pituus, String grain) {
		super();
		this.id = id;
		this.tiheys = tiheys;
		this.paksuus = paksuus;
		this.leveys = leveys;
		this.paino = paino;
		this.pituus = pituus;
		this.grain = grain;
	}

	public Balsalevy() {
		
	}

	public Balsalevy(double tiheys, double paksuus, double leveys, double paino, double pituus, String grain) {
		super();
		this.tiheys = tiheys;
		this.paksuus = paksuus;
		this.leveys = leveys;
		this.paino = paino;
		this.pituus = pituus;
		this.grain = grain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTiheys() {
		return tiheys;
	}

	public void setTiheys(double tiheys) {
		this.tiheys = tiheys;
	}

	public double getPaksuus() {
		return paksuus;
	}

	public void setPaksuus(double paksuus) {
		this.paksuus = paksuus;
	}

	public double getLeveys() {
		return leveys;
	}

	public void setLeveys(double leveys) {
		this.leveys = leveys;
	}

	public double getPaino() {
		return paino;
	}

	public void setPaino(double paino) {
		this.paino = paino;
	}

	public double getPituus() {
		return pituus;
	}

	public void setPituus(double pituus) {
		this.pituus = pituus;
	}

	public String getGrain() {
		return grain;
	}

	public void setGrain(String grain) {
		this.grain = grain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grain == null) ? 0 : grain.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(paksuus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(leveys);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paino);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pituus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tiheys);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balsalevy other = (Balsalevy) obj;
		if (grain == null) {
			if (other.grain != null)
				return false;
		} else if (!grain.equals(other.grain))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(paksuus) != Double.doubleToLongBits(other.paksuus))
			return false;
		if (Double.doubleToLongBits(leveys) != Double.doubleToLongBits(other.leveys))
			return false;
		if (Double.doubleToLongBits(paino) != Double.doubleToLongBits(other.paino))
			return false;
		if (Double.doubleToLongBits(pituus) != Double.doubleToLongBits(other.pituus))
			return false;
		if (Double.doubleToLongBits(tiheys) != Double.doubleToLongBits(other.tiheys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Balsalevy [id=" + id + ", tiheys=" + tiheys + ", korkeus=" + paksuus + ", leveys=" + leveys + ", paino="
				+ paino + ", pituus=" + pituus + ", grain=" + grain + "]";
	}
	
	

}
