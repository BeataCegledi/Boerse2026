package boerse;

import java.util.ArrayList;

public class BoerseSimulation extends Thread{
	//private ArrayList<Kunde> kundenListe = new ArrayList<>();
	private static ArrayList<Aktien> aktienListe = new ArrayList<>();
	private static ArrayList<Broker> brokerListe = new ArrayList<>();
	
	public BoerseSimulation(String text) {
		IO.println(text);
	}
	
	public static ArrayList<Aktien> getAktienListe() {
		return aktienListe;
	}

	public static void setAktienListe(ArrayList<Aktien> aktienListe) {
		BoerseSimulation.aktienListe = aktienListe;
	}

	/*public BoerseSimulation(ArrayList<Kunde> kundenListe){
		this.kundenListe = kundenListe;
	}
	
	public void AddKunde(Kunde kunde) {		if (!this.kundenListe.contains(kunde)) {
			this.kundenListe.add(kunde);
		} else {
			IO.println("Diese Kunde ist bereits in der Kundenliste, du kannst nur seinen Portfolio aktualisieren");
		}
	}
	
	public void getPortfolio(Kunde kunde) {
		if (this.kundenListe.contains(kunde)) {
			Kunde aktuell = this.kundenListe.get(this.kundenListe.indexOf(kunde));
			IO.println(aktuell.getPortfolio());
		} else
			IO.println("Diese Kunde existiert nicht in der Kundenliste, du sollst erstmal hinzufügen!");
	}
	
	public void AktienHinzufügen(Kunde kunde, Aktien aktie) {
		if (this.kundenListe.contains(kunde)) {
			Kunde aktuell = this.kundenListe.get(this.kundenListe.indexOf(kunde));
			aktuell.AktienKaufen(aktie);
		} else
			IO.println("Diese Kunde existiert nicht in der Kundenliste, du sollst erstmal hinzufügen!");
	}
	
	public void AktienEntfernen(Kunde kunde, Aktien aktie) {
		if (this.kundenListe.contains(kunde)) {
			Kunde aktuell = this.kundenListe.get(this.kundenListe.indexOf(kunde));
			aktuell.AktienVerkaufen(aktie);
		} else
			IO.println("Diese Kunde existiert nicht in der Kundenliste, du sollst erstmal hinzufügen!");
	}*/
	
		
	public static ArrayList<Broker> getBrokerListe() {
		return brokerListe;
	}

	public static void setBrokerListe(ArrayList<Broker> brokerListe) {
		BoerseSimulation.brokerListe = brokerListe;
	}

	public void brokerHinzufügen(Broker broker) {
		if (brokerListe.contains(broker)) {
			IO.println("Dieser Broker gibt es schon in der Liste!");
		} else {
			brokerListe.add(broker);
		}			
	}
	
	public void brokerEntfernen(Broker broker) {
		if (brokerListe.contains(broker)) {
			brokerListe.remove(broker);
		} else {
			IO.println("Dieser Broker gibt es nicht in der Liste!");
		}
	}
	
	public void AktienHinzufügen(Aktien aktie) {
		aktienListe.add(aktie);
	}
	
	public void AktienEntfernen(Aktien aktie) {
		if (aktienListe.contains(aktie)) {
			aktienListe.remove(aktie);
		} else {
			IO.println("Diese Aktie gibt es nicht in der Liste!");
		}
	}
	
	private static void refrescheAlleAktien() {
		for (int i = 0; i < aktienListe.size(); i++) {
			aktienListe.get(i).refrescheDich();
			}
		
		}
	
	private static void zeigeAlleAktien() {
		for (int i = 0; i < aktienListe.size(); i++) {
			aktienListe.get(i).zeigeDich();		}
	}
	
	private static void alleBrokerHandeln() {
		for (Broker broker : brokerListe) {
			for (Aktien aktie : aktienListe) {
				broker.AktienKaufen(aktie);
				broker.AktienVerkaufen(aktie);
			}
		}
	}

	public void run() {
		int runde = 1;
		while (true) {
			IO.println(runde + ". Runde:");
			refrescheAlleAktien();
			zeigeAlleAktien();
			alleBrokerHandeln();
			IO.println("*".repeat(50));
			try {
				Thread.sleep(3*1000); //10 Sekunden warten
			} catch (InterruptedException e) {
				IO.println("Fehler!");
			}
			runde++;
			if (runde%10==0) {
				for (Broker broker : brokerListe) {
					IO.println("\n***** " + broker.name + " hat gerade: " + broker.gesamtVermögen() + " *****");					
				}
				IO.println("\n"+"*".repeat(50)+"\n");
				try {Thread.sleep(3*1000);} catch (InterruptedException e) {}
			}
		}
	}
}
