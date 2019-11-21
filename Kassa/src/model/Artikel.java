package model;

public class Artikel {
    private int artikelcode;
    private String naam;
    private Artikelgroep artikelgroep;
    private float prijs;
    private int voorraad;

    public Artikel(int artikelcode, String naam, Artikelgroep artikelgroep, float prijs, int voorraad) throws Exception {
        setArtikelcode(artikelcode);
        setNaam(naam);
        setArtikelgroep(artikelgroep);
        setPrijs(prijs);
        setVoorraad(voorraad);
    }

    public void setArtikelcode(int artikelcode) throws Exception {
        if (artikelcode <0)
            throw new Exception("");
        this.artikelcode = artikelcode;
    }

    public void setNaam(String naam) throws Exception {
        if (naam.equals(""))
            throw new Exception("Artikel moet een omschrijving hebben");
        this.naam = naam;
    }

    public void setArtikelgroep(Artikelgroep artikelgroep) {
        this.artikelgroep = artikelgroep;
    }

    public void setPrijs(float prijs) throws Exception {
        if (prijs<0)
            throw new Exception("De prijs moet positief zijn");
        this.prijs = prijs;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
}
