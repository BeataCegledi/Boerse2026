package boerse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoerseTest {


	@Test
    void brokerErzeugenMitNameUndStartkapital() {
        Broker broker = new Luigi("Max");

        assertEquals("Max", broker.name);
        assertEquals(10000.0, broker.getKapital());
    }

	@Test
	void brokerOhneParameterHatVollstaendigesAktivesKonto() {
		Broker broker = new Luigi();

		assertTrue(broker.name.startsWith("Luigi_"));
		assertEquals(broker.name, broker.getKonto().getBenutzername());
		assertEquals(broker.name + "@broker.de", broker.getKonto().getEmail());
		assertEquals("Kein", broker.getKonto().getProfil().getVorname());
		assertEquals("Name", broker.getKonto().getProfil().getNachname());
		assertEquals("01.01.2000", broker.getKonto().getProfil().getGeburtsdatum());
		assertTrue(broker.getKonto().isAktiv());
	}

	@Test
	void zaehlerErzeugtUnterschiedlicheNamen() {
		Broker luigi1 = new Luigi();
		Broker luigi2 = new Luigi();
		Broker augusto = new Augusto();

		assertNotEquals(luigi1.name, luigi2.name);
		assertTrue(augusto.name.startsWith("Augusto_"));
	}

	@Test
	void brokerMitVornameNachnameGeburtsdatum() {
		Broker broker = new Augusto("Max", "Mustermann", "01.01.2000");

		assertEquals("Max_Mustermann", broker.name);
		assertEquals("Max", broker.vorname);
		assertEquals("Mustermann", broker.nachname);
		assertEquals("01.01.2000", broker.geburtsdatum);
		assertEquals("Max_Mustermann@broker.de", broker.getKonto().getEmail());
		assertTrue(broker.getKonto().isAktiv());
	}

	@Test
	void brokerNurMitNameIstNichtAktiv() {
		Broker broker = new Luigi("Max");

		assertFalse(broker.getKonto().isAktiv());
	}

	@Test
	void brokerNurMitNameKannManuellAktiviertWerden() {
		Broker broker = new Luigi("Max");

		broker.getKonto().kontoAktivieren();
		assertTrue(broker.getKonto().isAktiv());
	}

	@Test
	void inaktiverBrokerKauftNicht() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 89, null);

		broker.AktienKaufen(aktie);
		assertFalse(broker.willKaufen(aktie));
		assertEquals(10000.0, broker.getKapital());
		assertFalse(broker.getPortfolio().contains(aktie));
	}

	@Test
	void aktieKaufenKapitalSinktAktieErscheintImDepot() {
		Broker broker = new Augusto();
		Aktien aktie = new Aktien("testAktie", 100, 89, null);

		broker.AktienKaufen(aktie);
		assertTrue(broker.willKaufen(aktie));
		assertEquals(10000.0-89.0,broker.getKapital());
		assertTrue(broker.getPortfolio().contains(aktie));
	}

	@Test
	void aktieVerkaufenKapitalSteigtAktieVerschwindetDepot() {
		Broker broker = new Augusto();
		Aktien aktie = new Aktien("testAktie", 100, 111, null);
		broker.getPortfolio().add(aktie);

		broker.AktienVerkaufen(aktie);
		assertTrue(broker.willVerkaufen(aktie));
		assertEquals(10000.0+111.0,broker.getKapital());
		assertFalse(broker.getPortfolio().contains(aktie));
	}

	@Test
	void aktieOhneGenügendKapitalKaufen() {
		Broker broker = new Augusto();
		broker.setKapital(1);
		Aktien aktie = new Aktien("testAktie", 100, 89, null);

		broker.AktienKaufen(aktie);

		assertTrue(broker.willKaufen(aktie));
		assertEquals(1,broker.getKapital());
		assertFalse(broker.getPortfolio().contains(aktie));
	}

	@Test
	void nichtVorhandeneAktieVerkaufenAbgelehnt() {
		Broker broker = new Augusto();
		Aktien aktie = new Aktien("testAktie", 100, 115, null);

		broker.AktienVerkaufen(aktie);
		assertTrue(broker.willVerkaufen(aktie));
		assertFalse(broker.getPortfolio().contains(aktie));
		assertEquals(10000,broker.getKapital());
	}

	@Test
	void gesamtvermögenBerechnen() {
		Broker broker = new Augusto("Augusto Kauft");
		Aktien aktie = new Aktien("testAktie", 100, 50, null);
		Aktien aktie2 = new Aktien("Aktie2", 100, 50, null);
		broker.getPortfolio().add(aktie);
		broker.getPortfolio().add(aktie2);

		assertTrue(broker.gesamtVermögen().contains("10100"));
	}

	@Test
	void augustoKauftNichtBeiHohemKurs(){
		Broker broker = new Augusto();
		Aktien aktie = new Aktien("AktieHoch", 100, 101, null);

		assertFalse(broker.willKaufen(aktie));
	}

	@Test
	void luigiWillVerkaufenUndNichtKaufenBeimSteigendenKurs() {
		Broker broker = new Luigi();
		double[] historie = {100, 110, 120, 130, 140, 150, 160, 170, 180, 190};
		Aktien aktie = new Aktien("aktie", 100, 200, historie);
		assertFalse(broker.willKaufen(aktie));
		assertTrue(broker.willVerkaufen(aktie));
	}

	@Test
	void luigiWillKaufenUndNichtVerkaufenBeimSinkendenKurs() {
		Broker broker = new Luigi();
		double[] historie = {200, 190, 180, 170, 160, 150, 140, 130, 120, 110};
		Aktien aktie = new Aktien("aktie", 220, 100, historie);
		assertTrue(broker.willKaufen(aktie));
		assertFalse(broker.willVerkaufen(aktie));
	}

	@Test
	void luigiVerkauftNichtBeiZuKurzerHistorie() {
		Broker broker = new Luigi();
		double[] historie = {100, 110, 120, 130, 140};
		Aktien aktie = new Aktien("aktie", 100, 200, historie);
		assertFalse(broker.willVerkaufen(aktie));
	}
}


