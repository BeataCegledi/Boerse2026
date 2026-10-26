package boerse;

import java.util.ArrayList;

public class Kunde {
	
	private ArrayList<Aktien> portfolio = new ArrayList<>();
	
	public Kunde(ArrayList<Aktien> portfolio) {
		this.setPortfolio(portfolio);
	}
	
	public ArrayList<Aktien> getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(ArrayList<Aktien> portfolio) {
		this.portfolio = portfolio;
	}
	
	public void AktienKaufen(Aktien akt) {
		this.getPortfolio().add(akt);
	}
	
	public void AktienVerkaufen(Aktien akt) {
		if (this.getPortfolio().contains(akt)) {
			this.getPortfolio().remove(akt);
		} else {
			IO.println("Du kannst einen Aktien nicht verkaufen, was du nicht hast");
		}
	}


	

}
