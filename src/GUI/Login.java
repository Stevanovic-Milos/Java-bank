package GUI;
import db_objs.JDBC;
import db_objs.Korisnik;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Login extends glavniOkvir {
    public Login(){
        super("BB Banka Login");


    }
    @Override
    protected void dodajGuiKomponente() {
        setSize(700,500);

        ImageIcon slikaPozadine = new ImageIcon("C:\\Users\\steva\\OneDrive\\Documents\\Java-bank\\src\\Slike\\RegPoz.png");
        JLabel pozadina = new JLabel(slikaPozadine);
        pozadina.setBounds(0, 0, 700, 500);
        setContentPane(pozadina);


        JLabel bBankaLabel =new JLabel("Naša Banka");
        bBankaLabel.setBounds(0,20,super.getWidth(),40);
        bBankaLabel.setFont(new Font("Dialog",Font.BOLD, 32));
        bBankaLabel.setForeground(Color.WHITE);
        bBankaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pozadina.add(bBankaLabel);




        JLabel korisnikLabel=new JLabel("Korisnicko ime:");
        korisnikLabel.setBounds(20,100,super.getWidth()-60,24);
        korisnikLabel.setFont(new Font("Dialog",Font.BOLD,25));
        korisnikLabel.setForeground(Color.WHITE);2
        pozadina.add(korisnikLabel);

        JTextField korisikText=new JTextField();
        korisikText.setBounds(20,140,super.getWidth()-60,40);
        korisikText.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(korisikText);

        JLabel sifraLabel=new JLabel("Lozinka:");
        sifraLabel.setBounds(20,200,super.getWidth()-40,24);
        sifraLabel.setFont(new Font("Dialog",Font.BOLD,25));
        sifraLabel.setForeground(Color.WHITE);
        pozadina.add(sifraLabel);

        JPasswordField korisiksText=new JPasswordField();
        korisiksText.setBounds(20,240,super.getWidth()-60,40);
        korisiksText.setFont(new Font("Dialog",Font.PLAIN,28));
        pozadina.add(korisiksText);


        JButton ulogujButton=new JButton("Uloguj se");
        ulogujButton.setBounds(20,350,300,50);
        ulogujButton.setHorizontalAlignment(SwingConstants.CENTER);
        ulogujButton.setFont(new Font("Dialog",Font.PLAIN,20));
        ulogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime= korisikText.getText();
                String sifra=String.valueOf(korisiksText.getPassword());

                Korisnik korisnik= JDBC.validacijaPrijave(ime,sifra);

                if(korisnik !=null){
                    Login.this.dispose();
                    GlavnaAplikacija glavnaAplikacija=new GlavnaAplikacija(korisnik);
                    glavnaAplikacija.setVisible(true);

                    JOptionPane.showMessageDialog(glavnaAplikacija,"Uspesno ste Se ulogovali");
                }else{
                    JOptionPane.showMessageDialog(Login.this,"Ups ime/lozinka nisu tacni");
                }

            }
        });

        pozadina.add(ulogujButton);

        JButton regButton=new JButton("Registrujte se");
        regButton.setBounds(350,350,300,50);
        regButton.setHorizontalAlignment(SwingConstants.CENTER);
        regButton.setFont(new Font("Dialog",Font.PLAIN,20));
        regButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Login.this.dispose();
                        new Registracija().setVisible(true);
                    }
                }
        );
        getRootPane().setDefaultButton(ulogujButton);
        korisikText.requestFocusInWindow();

        pozadina.add(regButton);



    }

}
