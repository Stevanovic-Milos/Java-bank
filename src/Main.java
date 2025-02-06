import GUI.Login;

import javax.swing.*;

public class Main {
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
