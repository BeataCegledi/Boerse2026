/**
 * 
 */
package boerse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoerseTest {

	
	@Test
    void brokerErzeugenMitNameUndStartkapital() {
        Broker broker = new Luigi("Max");

        assertEquals("Max", broker.name);
        assertEquals(10000.0, broker.getKapital());
    }
	
	
	@Test
	void aktieKaufenKapitalSinktAktieErscheintImDepot() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 89, null, null);
		
		broker.AktienKaufen(aktie);
		assertTrue(broker.willKaufen(aktie));
		assertEquals(10000.0-89.0,broker.getKapital());
		assertTrue(broker.getPortfolio().contains(aktie));	
	}
	
	@Test
	void aktieVerkaufenKapitalSteigtAktieVerschwidetDepot() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 111, null, null);
		broker.getPortfolio().add(aktie);
		
		broker.AktienVerkaufen(aktie);
		assertTrue(broker.willVerkaufen(aktie));
		assertEquals(10000.0+111.0,broker.getKapital());
		assertFalse(broker.getPortfolio().contains(aktie));	
	}
	
	@Test
	void aktieOhneGenügendKapitalKaufen() {
		Broker broker = new Augusto("Augusto Kauft");
		broker.setKapital(1);		
		Aktien aktie = new Aktien("testAktie", 10, 89, null, null);
		
		broker.AktienKaufen(aktie);
		
		assertFalse(broker.willKaufen(aktie));
		assertEquals(1,broker.getKapital());
		assertFalse(broker.getPortfolio().contains(aktie));	
	}
	
	@Test
	void nichtVorhandeneAktieVerkaufenAbgelehnt() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 115, null, null);
		
		broker.AktienVerkaufen(aktie);
		assertFalse(broker.getPortfolio().contains(aktie));	
		assertEquals(10000,broker.getKapital());
	}

	@Test
	void gesamtvermögenBerechnen() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 50, null, null);
		Aktien aktie2 = new Aktien("Aktie2", 100, 50, null, null);
		broker.getPortfolio().add(aktie);
		broker.getPortfolio().add(aktie2);
		
		assertTrue(broker.gesamtVermögen().contains("10100"));
	}
	
	@Test
	void augustoBeiNiedrigemKurs(){
		Broker broker = new Augusto("Aug");
		Aktien aktie = new Aktien("AktieNiedrig", 100, 101, null, null);
		
		assertFalse(broker.willKaufen(aktie));
	}
	
	@Test
	void luigiWillKaufenUndNichtVErkaufdenBeimSteigendenKurs() {
		Broker broker = new Luigi("Luigi");
		double[] historie = {100, 110, 120, 130, 140, 150, 160, 170, 180, 190};
		Aktien aktie = new Aktien("aktie", 100, 200, historie ,null);
		assertFalse(broker.willKaufen(aktie));
		assertTrue(broker.willVerkaufen(aktie));
	}

	@Test
	void luigiWillVerkaufenUndNichtKaufenBeimSinkedendenKurs() {
		Broker broker = new Luigi("Luigi");
		double[] historie = {200, 190, 180, 170, 160, 150, 140, 130, 120, 110};
		Aktien aktie = new Aktien("aktie", 220, 100, historie ,null);
		assertTrue(broker.willKaufen(aktie));
		assertFalse(broker.willVerkaufen(aktie));
	}
}


