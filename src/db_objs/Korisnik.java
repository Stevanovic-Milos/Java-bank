package db_objs;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Korisnik {
    private final int id;
    private final String ime;
    private final String prezime;
    private final String sifra;
    private BigDecimal trenutno_stanje;
    private final String broj_racuna;

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getSifra() {
        return sifra;
    }

    public String getPrezime() {
        return prezime;
    }

    public BigDecimal getTrenutno_stanje() {
        return trenutno_stanje;
    }

    public String getBroj_racuna() {
        return broj_racuna;
    }

    public void setTrenutno_stanje(BigDecimal novo_stanje) {
        trenutno_stanje=novo_stanje.setScale(2, RoundingMode.FLOOR);
    }

    public Korisnik(int id, String ime,String prezime, String sifra, BigDecimal trenutno_stanje,String broj_racuna){
        this.id=id;
        this.ime =ime;
        this.prezime=prezime;
        this.sifra=sifra;
        this.trenutno_stanje=trenutno_stanje;
        this.broj_racuna=broj_racuna;
    }




}
