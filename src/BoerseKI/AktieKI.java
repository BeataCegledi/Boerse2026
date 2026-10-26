package BoerseKI;

import java.util.Random;

public class AktieKI
{
	Random zufallsGenerator= new Random();
	String name;
	double startWert;
	double aktuellerWert;
	int i=0;
	
	double[] letzteWerte = new double[10];
	
	public AktieKI(String name, double startWert)
	{
		super();
		this.name = name;
		this.startWert = startWert;
		this.aktuellerWert = startWert;
	}
	public void refresche_dich()
	{
		letzteWerte[i] = aktuellerWert;
		aktuellerWert = startWert*(zufallsGenerator.nextDouble()+0.5);
		i=i+1;
		if(i>=10)
		{
			i=0;
		}
		letzteWerte[i] = aktuellerWert;
	}
	public void zeigeDich()
	{
		System.out.print("Aktie "+ name+ " hat aktuell den Wert "+ String.format("%.2f", aktuellerWert));
		schaueObDuMehrWertBist();

	}
	public void schaueObDuMehrWertBist()
	{
		double summe =0.0;
		for(int i =0; i< 10; i++)
		{
			summe = summe+ letzteWerte[i];
		}
		summe = summe/10; //(durchschittwert)
		if(summe>startWert)
		{
			System.out.println("    KAUFEN!! DIE GEHT HOCH");
		}
		else
		{
			System.out.println("  sniff");
		}
	}

}
