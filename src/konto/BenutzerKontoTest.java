package konto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class BenutzerKontoTest {

	@Test
	void BenutzerKontoErzeugenMitBenutzernameEmailUndAktiv() {
		BenutzerKonto konto = new BenutzerKonto();
		konto.setBenutzername("Max");
		konto.setEmail("Max@beispiel.de");
		assertEquals("Max", konto.getBenutzername());
		assertEquals("Max@beispiel.de", konto.getEmail());
		assertFalse(konto.isAktiv());
	}

	@Test
	void BenutzerKontoMitFuenfParameternErzeugtProfil() {
		BenutzerKonto konto = new BenutzerKonto("Max", "Max@beispiel.de", "Max", "Mustermann", "01.01.2000");
		assertNotNull(konto.getProfil());
		assertEquals("Max", konto.getProfil().getVorname());
		assertEquals("Mustermann", konto.getProfil().getNachname());
		assertEquals("01.01.2000", konto.getProfil().getGeburtsdatum());
		assertFalse(konto.isAktiv());
	}

	@Test
	void BenutzerKontoAusgebenMitBenutzernameEmailUndAktiv() {
		BenutzerKonto konto = new BenutzerKonto();
		konto.setBenutzername("Max");
		konto.setEmail("Max@beispiel.de");

		konto.zeigeStatus();

		konto.kontoAktivieren();
		assertFalse(konto.isAktiv());

		konto.speichereProfil(new Profil("Max", "Mustermann", "01.01.2000"));
		konto.kontoAktivieren();
		assertTrue(konto.isAktiv());
		konto.zeigeStatus();
	}

	@Test
	void BenutzerKontoAusgebenMitBenutzernameEmailUndAktivUndNachDaktivieren() {
		Profil profil = new Profil("Max", "Mustermann", "01.01.2000");
		BenutzerKonto konto = new BenutzerKonto("Max", "Max@beispiel.de", profil);

		konto.kontoAktivieren();
		assertTrue(konto.isAktiv());
		konto.zeigeStatus();

		konto.kontoDeaktivieren();
		assertFalse(konto.isAktiv());
		konto.zeigeStatus();
	}

	@Test
	void BenutzerKontoEmailAendern() {
		BenutzerKonto konto = new BenutzerKonto("Max", "Max@beispiel.de");
		konto.zeigeStatus();
		konto.aendereEmail("MaxNEU@beispiel.de");
		assertEquals("MaxNEU@beispiel.de", konto.getEmail());
		konto.zeigeStatus();
	}

	@Test
	void BenutzerKontoIsVollstaendig() {
		BenutzerKonto konto = new BenutzerKonto("Max", "Max@beispiel.de");
		assertFalse(konto.benutzerKontoIsVollstaendig());

		konto.speichereProfil(new Profil());
		assertFalse(konto.benutzerKontoIsVollstaendig());

		konto.speichereProfil(new Profil("Max", "Mustermann", "01.01.2000"));
		assertTrue(konto.benutzerKontoIsVollstaendig());
	}

	@Test
	void BenutzernameUndEmailValidieren() {
		BenutzerKonto konto = new BenutzerKonto("Max", "Max@beispiel.de");
		assertTrue(konto.validiereBenutzernameUndEmail("Max", "Max@beispiel.de"));
		assertFalse(konto.validiereBenutzernameUndEmail("Max", "falsch@beispiel.de"));
		assertFalse(konto.validiereBenutzernameUndEmail("Moritz", "Max@beispiel.de"));
	}

}
