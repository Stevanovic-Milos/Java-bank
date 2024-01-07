package GUI;

import db_objs.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registracija extends glavniOkvir{
    public Registracija(){
        super("Registrujte se");
    }

    @Override
    protected void dodajGuiKomponente() {
        ImageIcon slikaPozadine = new ImageIcon("C:\\Users\\MS\\IdeaProjects\\Banka\\src\\Slike\\RegPoz.png");
        JLabel pozadina = new JLabel(slikaPozadine);
        pozadina.setBounds(0, 0, 700, 500);
        setContentPane(pozadina);

        JLabel bBankaLabelre =new JLabel("Registracija");
        bBankaLabelre.setBounds(0,20,super.getWidth(),40);
        bBankaLabelre.setFont(new Font("Dialog",Font.BOLD, 32));
        bBankaLabelre.setHorizontalAlignment(SwingConstants.CENTER);
        bBankaLabelre.setForeground(Color.WHITE);
        pozadina.add(bBankaLabelre);

        JLabel korisnikLabelre=new JLabel("Ime:");
        korisnikLabelre.setBounds(20,80,375,24);
        korisnikLabelre.setFont(new Font("Dialog",Font.PLAIN,20));
        korisnikLabelre.setForeground(Color.WHITE);
        pozadina.add(korisnikLabelre);

        JTextField korisikTextre=new JTextField();
        korisikTextre.setBounds(20,120,335,40);
        korisikTextre.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(korisikTextre);

        JLabel prezimere=new JLabel("Prezime:");
        prezimere.setBounds(425,80,375,24);
        prezimere.setFont(new Font("Dialog",Font.PLAIN,20));
        prezimere.setForeground(Color.WHITE);
        pozadina.add(prezimere);

        JTextField prezimeTextRe=new JTextField();
        prezimeTextRe.setBounds(425,120,335,40);
        prezimeTextRe.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(prezimeTextRe);

        JLabel sifraLabelre=new JLabel("Lozinka:");
        sifraLabelre.setBounds(20,200,super.getWidth()-40,24);
        sifraLabelre.setFont(new Font("Dialog",Font.PLAIN,20));
        sifraLabelre.setForeground(Color.WHITE);
        pozadina.add(sifraLabelre);

        JPasswordField korisiksTextre=new JPasswordField();
        korisiksTextre.setBounds(20,240,super.getWidth()-60,40);
        korisiksTextre.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(korisiksTextre);

        JLabel sifraLabelre1=new JLabel("Ponovi lozinku:");
        sifraLabelre1.setBounds(20,300,super.getWidth()-40,24);
        sifraLabelre1.setFont(new Font("Dialog",Font.PLAIN,20));
        sifraLabelre1.setForeground(Color.WHITE);
        pozadina.add(sifraLabelre1);

        JPasswordField korisiksTextre1=new JPasswordField();
        korisiksTextre1.setBounds(20,340,super.getWidth()-60,40);
        korisiksTextre1.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(korisiksTextre1);

        JButton reg1Button=new JButton("Postani korisnik");
        reg1Button.setBounds(20,450,335,50);
        reg1Button.setHorizontalAlignment(SwingConstants.CENTER);
        reg1Button.setFont(new Font("Dialog",Font.PLAIN,20));

        reg1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime=korisikTextre.getText();
                String prezime=prezimeTextRe.getText();
                String sifra=String.valueOf(korisiksTextre.getPassword());
                String resifra=String.valueOf(korisiksTextre1.getPassword());
                if(validacija(ime,sifra,resifra)){
                    if(JDBC.registracija(ime,sifra,prezime)){
                        Registracija.this.dispose();
                        Login login=new Login();
                        login.setVisible(true);
                        JOptionPane.showMessageDialog(login,"Uspesno ste se registrovali, ulogujte se");
                    }
                }
            }
        });
        pozadina.add(reg1Button);


        JButton regButtonul=new JButton("Ulogujte se");
        regButtonul.setBounds(425,450,335,50);
        regButtonul.setHorizontalAlignment(SwingConstants.CENTER);
        regButtonul.setFont(new Font("Dialog",Font.PLAIN,20));
        regButtonul.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Registracija.this.dispose();
                new Login().setVisible(true);
            }
        });
        pozadina.add(regButtonul);
        getRootPane().setDefaultButton(reg1Button);

        korisnikLabelre.requestFocusInWindow();
    }
    private boolean validacija(String ime,String sifra,String resifra){
        if(ime.length()==0||sifra.length()==0 ||resifra.length()==0){
            JOptionPane.showMessageDialog(Registracija.this,"Sva polja moraju biti popunjena");
            return false;
        }
        if(ime.length()<6){
            JOptionPane.showMessageDialog(Registracija.this,"Ime mora imati vise od 6 karaktera");
            return false;
        }

        if(!sifra.equals(resifra)){
            JOptionPane.showMessageDialog(Registracija.this,"Sifre nisu iste");
            return false;
        }
        return true;

    }
}
