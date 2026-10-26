package boerse;

import java.util.ArrayList;

public class Luigi extends Broker {


	public Luigi(String name) {
		super(name);
	}

	public Luigi(String name, ArrayList<Aktien> portfolio) {
		super(name, portfolio);
	}


	@Override
	public boolean willVerkaufen(Aktien aktie) {
		if (aktie.historie.length < 10) {
		return false;
		}
		return (aktie.aktuellerWert >= aktie.durchschnitt() * 1.05);
		
	}

	@Override
	public boolean willKaufen(Aktien aktie) {
		return (aktie.aktuellerWert <= aktie.durchschnitt() * 0.95);
	}

}
