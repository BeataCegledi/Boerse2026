package boerse;

import java.util.ArrayList;

public class Augusto extends Broker {
	
	
	public Augusto(String name) {
		super(name);
	
	}

	public Augusto(String name, ArrayList<Aktien> portfolio) {
		super(name, portfolio);
	}

	
	@Override
	public boolean willVerkaufen(Aktien aktie) {		
		return (aktie.aktuellerWert >= aktie.originalWert * 1.1);
	}

	@Override
	public boolean willKaufen(Aktien aktie) {
		return (aktie.aktuellerWert <= aktie.originalWert * 0.9);
	}

	
}
