package BoerseKI;

import java.util.ArrayList;

public class BoerseKI extends Thread
{
	String name;
	
	public BoerseKI(String name)
	{
		super();
		this.name = name;
	}

	ArrayList<AktieKI> aListe = new ArrayList<>();
	
	public void addiereAktie(AktieKI neu)
	{
		aListe.add(neu);
	}
	
	public void run()
	{
		while(true)
		{
			refrescheAlleAktien();
			zeigeAlleAktien();
			System.out.println("-----------------------");
			try{ Thread.sleep(2*1000);} catch (InterruptedException e){}        //10 sekunden warten!!
			System.out.println("... nächste Runde");
		}
	}

	private void zeigeAlleAktien()
	{
		for(int i =0; i < aListe.size(); i++)
		{
			aListe.get(i).zeigeDich();
		}		
	}

	private void refrescheAlleAktien()
	{
		for(int i =0; i < aListe.size(); i++)
		{
			aListe.get(i).refresche_dich();
		}
		
	}

}
