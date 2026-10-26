package boerse;

import java.util.ArrayList;


public abstract class Broker extends Person{
	private ArrayList<Aktien> portfolio = new ArrayList<>();
	private double kapital = 10000;
	
	public Broker(String name) {
		super(name);
	}

	public Broker(String name, ArrayList<Aktien> portfolio) {
		super(name);
		this.portfolio = portfolio;
	}
	
	
	public ArrayList<Aktien> getPortfolio() {
		return portfolio;
	}
	
	public void setPortfolio(ArrayList<Aktien> portfolio) {
		this.portfolio = portfolio;
	}
	
	public double getKapital() {
		return kapital;
	}
	
	public void setKapital(double kapital) {
		this.kapital = kapital;
	}


	public abstract boolean willVerkaufen(Aktien aktie);
	
	public abstract boolean willKaufen(Aktien aktie);
	
	public void AktienKaufen(Aktien akt) {
		if (this.willKaufen(akt) && akt.aktuellerWert < this.kapital) {
			this.portfolio.add(akt);
			this.kapital -= akt.aktuellerWert;
			IO.println("Broker " + this.name + " hat die Aktie " + akt.aktienName + " erfolgreich gekauft!");
			IO.println("Der verbliebender Kapital ist: " + String.format("%.2f",this.kapital)+ "€");
		} else {
			IO.println("Broker " + this.name + " hat leider nicht genügend Kapital (" + String.format("%.2f",this.kapital) + "€) für die Aktie " + akt.aktienName );
		}
	}
	
	public void AktienVerkaufen(Aktien akt) {
		if (this.willVerkaufen(akt) && this.portfolio.contains(akt)) {
			this.portfolio.remove(akt);
			this.kapital += akt.aktuellerWert;
			IO.println("Broker " + this.name + " hat die Aktie " + akt.aktienName + " erfolgreich verkauft! ");
			IO.println("Der aktueller Kapital beträgt: " + String.format("%.2f",this.kapital) + "€");
		} else {
			IO.println("Broker " + this.name + " kann die Aktie " + akt.aktienName + " nicht verkaufen, weil er es nicht hat");
		}
	}
	
	
	public String gesamtVermögen() {
		double summe = 0;
		for (Aktien aktie : this.portfolio) {
			summe += aktie.aktuellerWert;
		}
		return ("Gesamtvermögen = " + String.format("%.2f",this.kapital) + "€ Kapital + " + String.format("%.2f",summe) + "€ Depotwert = " + String.format("%.2f",(this.kapital+summe)) +"€");
	}
}
