package boerse;


public class Luigi extends Broker {
	private static int countLuigi = 0;
	
	public Luigi() {
		super("Luigi_" + (++countLuigi), "Kein", "Name", "01.01.2000");
	}

	public Luigi(String name) {
		super(name);
	}
	
	public Luigi(String vorname, String nachname, String geburtsdatum) {
		super(vorname, nachname, geburtsdatum);
	}

	@Override
	public boolean willVerkaufen(Aktien aktie) {
		if (aktie.historie.length < 10 || !this.getKonto().isAktiv()) {
			System.out.println("Der Börse hat nicht genug lange Historienverlauf zum entscheiden oder \nBroker " + this.name + " hat kein aktives Konto, er kann keine Aktien verkaufen!");
		return false;
		}
		return (aktie.aktuellerWert >= aktie.durchschnitt() * 1.05);
		
	}

	@Override
	public boolean willKaufen(Aktien aktie) {
		if (this.getKonto().isAktiv() == false) {
			System.out.println("Broker " + this.name + " hat kein aktives Konto, er kann keine Aktien kaufen!");
			return false;
		}
		return (aktie.aktuellerWert <= aktie.durchschnitt() * 0.95);
	}

}
