package GUI;
import db_objs.Korisnik;

import javax.swing.*;
import java.awt.*;

public abstract class glavniOkvir extends JFrame {
    protected Korisnik korisnik;
    public glavniOkvir(String naslov) {
        inicijalizacija(naslov);
    }
    public glavniOkvir(String naslov,Korisnik korisnik){
        this.korisnik=korisnik;
        inicijalizacija(naslov);

    }

    private void inicijalizacija(String naslov) {
        setTitle(naslov);

        setSize(800, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setResizable(false);

        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("C:\\Users\\MS\\IdeaProjects\\Banka\\src\\Slike\\Banka.jpeg"); // Postavite putanju do vaše slike
        setIconImage(icon.getImage());
        requestFocusInWindow();

        dodajGuiKomponente();
    }

    protected abstract void dodajGuiKomponente();
}

