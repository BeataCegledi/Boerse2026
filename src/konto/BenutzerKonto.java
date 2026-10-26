package konto;

public class BenutzerKonto {
	private String benutzername;
	private String email;
	private boolean aktiv;
	private Profil profil = null;
	
	
	public BenutzerKonto() {
		super();
		this.aktiv = false;
	}

	public BenutzerKonto(String benutzername, String email) {
		super();
		this.benutzername = benutzername;
		this.email = email;
		this.aktiv = false;
	}
	
	public BenutzerKonto(String benutzername, String email, Profil profil) {
		super();
		this.benutzername = benutzername;
		this.email = email;
		this.aktiv = false;
		this.profil = profil;
	}

	public BenutzerKonto(String benutzername, String email, String vorname, String nachname, String geburtsdatum) {
		this(benutzername, email, new Profil(vorname, nachname, geburtsdatum));
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public Profil getProfil() {
		return profil;
	}

	public void kontoAktivieren() {
		if (profil == null) {
			System.out.println("Bitte erstelle ein Profil, bevor du das Konto aktivierst.");
			return;
		}
		this.aktiv = true;
		System.out.println("Das Benutzerkonto wurde aktiviert.");
	}
	
	public void kontoDeaktivieren() {
		this.aktiv = false;
		System.out.println("Das Benutzerkonto wurde deaktiviert.");
	}
	
	public void zeigeStatus() {
		System.out.println("Benutzername: " + benutzername);
		System.out.println("Email: " + email);
		System.out.print("Konto aktiv: ");
		System.out.println(aktiv ? "Ja" : "Nein");
		if (profil != null) {
			profil.zeigeProfil();
		} else {
			System.out.println("Kein Profil vorhanden.");
		}
	}
	
	public void aendereEmail(String neueEmail) {
		this.email = neueEmail;
		System.out.println("Die Email-Adresse wurde geändert.");
	}
	
	public boolean validiereBenutzernameUndEmail(String benutzername, String email) {
		return benutzername.equals(this.benutzername) && email.equals(this.email);
	}

	public void speichereProfil(Profil profil) {
		this.profil = profil;
		System.out.println("Das Profil wurde gespeichert.");
	}
	
	public boolean benutzerKontoIsVollstaendig() {
		return (benutzername != null && email != null  && profil != null && profil.profilIsVollstaendig());
	}
}


