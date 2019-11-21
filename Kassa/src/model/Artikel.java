package model;

public class Artikel {
    private int artikelcode;
    private String naam;
    private String artikelgroep;
    private double prijs;
    private int voorraad;

    public Artikel(int artikelcode, String naam, String artikelgroep, double prijs, int voorraad) {
        setArtikelcode(artikelcode);
        setNaam(naam);
        setArtikelgroep(artikelgroep);
        setPrijs(prijs);
        setVoorraad(voorraad);
    }

    public void setArtikelcode(int artikelcode) throws ModelException {
        if (artikelcode<0)
            throw new ModelException("Artikel moet een positieve artikelcode hebben");
        this.artikelcode = artikelcode;
    }


    public void setNaam(String naam) throws ModelException {
        if (naam.equals(""))
            throw new ModelException("Artikel moet een niet lege naam hebben");
        this.naam = naam;
    }

    public void setArtikelgroep(String artikelgroep) {
        this.artikelgroep = artikelgroep;
    }

    public void setPrijs(double prijs) throws ModelException {
        if (prijs<0)
            throw new ModelException("De prijs moet positief zijn");
        this.prijs = prijs;
    }

    public void setVoorraad(int voorraad) throws ModelException {
        if (voorraad<0)
            throw new ModelException("Voorraad moet positief zijn");
        this.voorraad = voorraad;
    }
}
