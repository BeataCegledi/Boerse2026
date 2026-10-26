package boerse;

import java.util.ArrayList;
import konto.*;

public abstract class Broker extends Person{
	private ArrayList<Aktien> portfolio = new ArrayList<>();
	private double kapital = 10000;
	private String email = name + "@broker.de";
	private BenutzerKonto konto;


	public Broker(String name) {
		super(name);
		this.konto = new BenutzerKonto(name, email, new Profil());
		aktiviereKontoWennVollstaendig();
	}


	public Broker(String vorname, String nachname, String geburtsdatum) {
		super(vorname, nachname, geburtsdatum);
		this.konto = new BenutzerKonto(name, email, vorname, nachname, geburtsdatum);
		aktiviereKontoWennVollstaendig();
	}

	public Broker(String name, String vorname, String nachname, String geburtsdatum) {
		super(name, vorname, nachname, geburtsdatum);
		this.konto = new BenutzerKonto(name, email, vorname, nachname, geburtsdatum);
		aktiviereKontoWennVollstaendig();
	}

	private void aktiviereKontoWennVollstaendig() {
		if (!konto.isAktiv() && konto.benutzerKontoIsVollstaendig()) {
			konto.kontoAktivieren();
		}
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

	public BenutzerKonto getKonto() {
		return konto;
	}

	public void setKonto(BenutzerKonto konto) {
		this.konto = konto;
	}
	
	public abstract boolean willVerkaufen(Aktien aktie);
	
	public abstract boolean willKaufen(Aktien aktie);
	
	public void AktienKaufen(Aktien akt) {
		aktiviereKontoWennVollstaendig();
		if (this.willKaufen(akt) && akt.aktuellerWert < this.kapital) {
			this.portfolio.add(akt);
			this.kapital -= akt.aktuellerWert;
			System.out.println("Broker " + this.name + " hat die Aktie " + akt.aktienName + " erfolgreich gekauft!");
			System.out.println("Der verbliebender Kapital ist: " + String.format("%.2f",this.kapital)+ "€");
		} else {
			System.out.println("Broker " + this.name + " hat leider nicht genügend Kapital (" + String.format("%.2f",this.kapital) + "€) für die Aktie " + akt.aktienName );
		}
	}
	
	public void AktienVerkaufen(Aktien akt) {
		aktiviereKontoWennVollstaendig();
		if (this.willVerkaufen(akt) && this.portfolio.contains(akt)) {
			this.portfolio.remove(akt);
			this.kapital += akt.aktuellerWert;
			System.out.println("Broker " + this.name + " hat die Aktie " + akt.aktienName + " erfolgreich verkauft! ");
			System.out.println("Der aktueller Kapital beträgt: " + String.format("%.2f",this.kapital) + "€");
		} else {
			System.out.println("Broker " + this.name + " kann die Aktie " + akt.aktienName + " nicht verkaufen, weil er es nicht hat");
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
