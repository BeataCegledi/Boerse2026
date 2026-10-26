package boerse;

public abstract class Person {
	String vorname; 
	String nachname;
	String geburtsdatum;
	String name;

	public Person(String name) {
		this.name = name;
	}

	public Person(String vorname, String nachname){
		this.vorname = vorname;
		this.nachname = nachname;
		this.name = vorname + "_" + nachname;
	}

	public Person(String vorname, String nachname, String geburtsdatum){
		this(vorname, nachname);
		this.geburtsdatum = geburtsdatum;
	}

	public Person(String name, String vorname, String nachname, String geburtsdatum){
		this.name = name;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}
}
