package konto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProfilTest {


	@Test
	void ProfilErstellen() {
		Profil profil = new Profil("Max", "Mustermann", "01.01.2000");
		assertEquals("Max", profil.getVorname());
		assertEquals("Mustermann", profil.getNachname());
		assertEquals("01.01.2000", profil.getGeburtsdatum());

		profil.zeigeProfil();

		profil.setVorname("Maximilian");
		profil.setNachname("Muster");
		profil.setGeburtsdatum("02.02.2002");
		assertEquals("Maximilian", profil.getVorname());
		assertEquals("Muster", profil.getNachname());
		assertEquals("02.02.2002", profil.getGeburtsdatum());

		profil.zeigeProfil();
	}

	@Test
	void ProfilInBenutzerKontoSpeichernUndAnzeigen() {
		BenutzerKonto konto = new BenutzerKonto("Max", "max@email.de");
		Profil profil = new Profil("Max", "Mustermann", "01.01.2000");
		konto.speichereProfil(profil);
		assertEquals(profil, konto.getProfil());
		konto.zeigeStatus();
	}

	@Test
	void ProfilIsVollstaendig() {
		assertFalse(new Profil().profilIsVollstaendig());
		assertFalse(new Profil("Max", "Mustermann", null).profilIsVollstaendig());
		assertTrue(new Profil("Max", "Mustermann", "01.01.2000").profilIsVollstaendig());
	}
}
