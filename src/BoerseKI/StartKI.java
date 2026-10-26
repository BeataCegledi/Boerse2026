package BoerseKI;

public class StartKI
{

	public static void main(String[] args)
	{
		BoerseKI zuericher = new BoerseKI("Gruezi aus Züri!");		// Boerse erzeugen...

		AktieKI siemens = new AktieKI("Siemens AG", 100.0);			// 5 Aktien erzeugen
		AktieKI basf = new AktieKI("Luft-Verpester", 80.0);			// 5 Aktien erzeugen
		AktieKI bosch = new AktieKI("Bosch AG", 120.0);				// 5 Aktien erzeugen
		AktieKI telekom = new AktieKI("DSL Betrueger", 60.0);		// 5 Aktien erzeugen
		AktieKI chileanMaffia = new AktieKI("Chilean AG", 100.0);	// 5 Aktien erzeugen
		
		zuericher.addiereAktie(siemens);  						// Alle Aktien in die Boerse eintragen
		zuericher.addiereAktie(basf);  							// Alle Aktien in die Boerse eintragen
		zuericher.addiereAktie(bosch);  						// Alle Aktien in die Boerse eintragen
		zuericher.addiereAktie(telekom);  						// Alle Aktien in die Boerse eintragen
		zuericher.addiereAktie(chileanMaffia);  				// Alle Aktien in die Boerse eintragen
		// Die Boerse "starten"
		
		zuericher.start();//zuericher.run();
		
		try{Thread.sleep(23*1000);}catch(Exception e) {}
		
		AktieKI mercedes = new AktieKI("Mercedes benz", 200);		//neue Aktie eintragen
		zuericher.addiereAktie(mercedes);
		System.out.println("Mercedes addiert!!");
		
	}

}
