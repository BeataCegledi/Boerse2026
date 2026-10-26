package boerse;

public class Augusto extends Broker {
	private static int countAugusto = 0;

	public Augusto() {
		super("Augusto_" + (++countAugusto), "Kein", "Name", "01.01.2000");
	}

	public Augusto(String name) {
		super(name);
	}

	public Augusto(String vorname, String nachname, String geburtsdatum) {
		super(vorname, nachname, geburtsdatum);
	}

	
	@Override
	public boolean willVerkaufen(Aktien aktie) {	
		if (this.getKonto().isAktiv() == false) {
			System.out.println("Broker " + this.name + " hat kein aktives Konto, er kann keine Aktien verkaufen!");
			return false;
		}
		return (aktie.aktuellerWert >= aktie.originalWert * 1.1);
	}

	@Override
	public boolean willKaufen(Aktien aktie) {
		if (this.getKonto().isAktiv() == false) {
			System.out.println("Broker " + this.name + " hat kein aktives Konto, er kann keine Aktien kaufen!");
			return false;
		}
		return (aktie.aktuellerWert <= aktie.originalWert * 0.9);
	}

	
}
