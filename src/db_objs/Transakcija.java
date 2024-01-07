package db_objs;

import java.math.BigDecimal;
import java.util.Date;

public class Transakcija {
   private final int korisnik_id;
   private final String transakcija_tip;
    private final Date transakcija_datum;
    private final BigDecimal transakcija_kolicina;

    public Transakcija(int korisnik_id, String transakcija_tip, Date transakcija_datum, BigDecimal transakcija_kolicina) {
        this.korisnik_id = korisnik_id;
        this.transakcija_tip = transakcija_tip;
        this.transakcija_datum = transakcija_datum;
        this.transakcija_kolicina = transakcija_kolicina;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public String getTransakcija_tip() {
        return transakcija_tip;
    }

    public Date getTransakcija_datum() {
        return transakcija_datum;
    }

    public BigDecimal getTransakcija_kolicina() {
        return transakcija_kolicina;
    }
}
