package boerse;

import java.util.ArrayList;

public class BoerseSimulation extends Thread{
	private static ArrayList<Aktien> aktienListe = new ArrayList<>();
	private static ArrayList<Broker> brokerListe = new ArrayList<>();
	
	public BoerseSimulation(String text) {
		System.out.println(text);
	}
	
	public static ArrayList<Aktien> getAktienListe() {
		return aktienListe;
	}

	public static void setAktienListe(ArrayList<Aktien> aktienListe) {
		BoerseSimulation.aktienListe = aktienListe;
	}


		
	public static ArrayList<Broker> getBrokerListe() {
		return brokerListe;
	}

	public static void setBrokerListe(ArrayList<Broker> brokerListe) {
		BoerseSimulation.brokerListe = brokerListe;
	}

	public void brokerHinzufügen(Broker broker) {
		if (brokerListe.contains(broker)) {
			System.out.println("Dieser Broker gibt es schon in der Liste!");
		} else {
			brokerListe.add(broker);
		}			
	}
	
	public void brokerEntfernen(Broker broker) {
		if (brokerListe.contains(broker)) {
			brokerListe.remove(broker);
		} else {
			System.out.println("Dieser Broker gibt es nicht in der Liste!");
		}
	}
	
	public void AktienHinzufügen(Aktien aktie) {
		aktienListe.add(aktie);
	}
	
	public void AktienEntfernen(Aktien aktie) {
		if (aktienListe.contains(aktie)) {
			aktienListe.remove(aktie);
		} else {
			System.out.println("Diese Aktie gibt es nicht in der Liste!");
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
			System.out.println(runde + ". Runde:");
			refrescheAlleAktien();
			zeigeAlleAktien();
			alleBrokerHandeln();
			System.out.println("*".repeat(50));
			try {
				Thread.sleep(3*1000);
			} catch (InterruptedException e) {
				System.out.println("Fehler!");
			}
			runde++;
			if (runde%10==0) {
				for (Broker broker : brokerListe) {
					System.out.println("\n***** " + broker.name + " hat gerade: " + broker.gesamtVermögen() + " *****");					
				}
				System.out.println("\n"+"*".repeat(50)+"\n");
				try {Thread.sleep(3*1000);} catch (InterruptedException e) {}
			}
		}
	}
}
