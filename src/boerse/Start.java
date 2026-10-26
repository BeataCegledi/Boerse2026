package boerse;

public class Start{

	public static void main(String[] args)
	{
		BoerseSimulation zuericher = new BoerseSimulation("Gruezi aus Züri!");	

		Aktien siemens = new Aktien("Siemens AG", 100.0);			
		Aktien basf = new Aktien("Luft-Verpester", 80.0);			
		Aktien bosch = new Aktien("Bosch AG", 120.0);				
		Aktien telekom = new Aktien("DSL Betrueger", 60.0);		
		Aktien chileanMaffia = new Aktien("Chilean AG", 100.0);
		
		zuericher.AktienHinzufügen(siemens);  					
		zuericher.AktienHinzufügen(basf);  							
		zuericher.AktienHinzufügen(bosch);  						
		zuericher.AktienHinzufügen(telekom);  					
		zuericher.AktienHinzufügen(chileanMaffia);  			
		Broker luigi = new Luigi("Luigi");
		Broker augusto = new Augusto("Augusto"); 
		zuericher.brokerHinzufügen(augusto);
		zuericher.brokerHinzufügen(luigi);
		
		zuericher.start();
		
		Aktien mercedes = new Aktien("Mercedes benz", 200);
		zuericher.AktienHinzufügen(mercedes);
		System.out.println("Mercedes addiert!!");
		
	}

}
