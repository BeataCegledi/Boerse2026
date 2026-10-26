package boerse;
import java.util.Random;

public class Aktien{
	Random random = new Random();
	public String aktienName;
	public double originalWert;
	public double aktuellerWert;
	
	double[] historie = new double[10];
	
	Perzeptron gehirn = new Perzeptron();

	
	public Aktien(String aktienName, double originalWert) {
		super();
		this.aktienName = aktienName;
		this.originalWert = originalWert;
		this.aktuellerWert = originalWert;
		this.historie[0] = originalWert;
	}

	
	public Aktien(String aktienName, double originalWert, double aktuellerWert, double[] historie, Perzeptron gehirn) {
		super();
		this.aktienName = aktienName;
		this.originalWert = originalWert;
		this.aktuellerWert = aktuellerWert;
		this.historie = historie;
		this.gehirn = gehirn;
	}
	
	private int h = 0;

	public void refrescheDich() {
		historie[h % 10] = aktuellerWert;
		aktuellerWert = originalWert * (random.nextDouble()+0.5);
		h += 1;
	}
	
	public void zeigeDich() {
		IO.println("=".repeat(50));
		IO.println("Aktie " + aktienName + " hat aktuell den Wert " + String.format("%.2f",aktuellerWert));
		IO.print("Vorherige Werte: ");
		for (double hist : historie) {
			IO.print(String.format("%.2f", hist) + ", ");
		}
		IO.println();
		this.schaueObSteigt();
	}
	
	public double durchschnitt() {
		double summe = 0;
		for (double hist : this.historie) {
			summe += hist;
		}
		return summe / this.historie.length;
	}
	
	public void schaueObSteigt() {

		if (durchschnitt()> originalWert) {
			IO.println("Aktie geht hoch!!! KAUFEN");
		} else {
			IO.println("Vorisicht der Wert von Aktie SINKT!!! ");
		}
	}
}
