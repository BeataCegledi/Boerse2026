package konto;

public class Profil {
	private String vorname;
	private String nachname;
	private String geburtsdatum;

	public Profil() {
	}

	public Profil(String vorname, String nachname, String geburtsdatum) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	
	public void zeigeProfil() {
		System.out.println("Vollständiger Name: " + vorname + " " + nachname);
		System.out.println("Geburtsdatum: " + geburtsdatum);
	}

	public boolean profilIsVollstaendig() {
		return (vorname != null && nachname != null && geburtsdatum != null);
	}
	
	

}
