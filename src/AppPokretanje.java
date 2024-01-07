import GUI.GlavnaAplikacija;
import GUI.Login;
import GUI.Registracija;
import db_objs.Korisnik;

import javax.swing.*;
import java.math.BigDecimal;

public class AppPokretanje {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
                //new Registracija().setVisible(true);
              /*  new GlavnaAplikacija(
                        new Korisnik(1,"Milos","Stevanovic","sifra",new BigDecimal("20.00"))
                ).setVisible(true);
                */

            }
        });
    }
}
