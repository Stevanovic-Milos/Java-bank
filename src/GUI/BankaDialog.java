package GUI;

import db_objs.JDBC;
import db_objs.Korisnik;
import db_objs.Transakcija;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BankaDialog extends JDialog implements ActionListener {
    private Korisnik korisnik;
    private GlavnaAplikacija glavnaAplikacija;
    private JLabel stanjeLabel;
    private JLabel unesiKolicinuLabel, unesiKorisnikaLabel;
    private JTextField unesiKolicinuPolje, unesiKorisnikaPolje;
    private JButton akcijskoDugme;
    private JPanel stareTransakcijePanel;
    private ArrayList<Transakcija> stareTransakcije;

    public BankaDialog(GlavnaAplikacija glavnaAplikacija,Korisnik korisnik){

        setSize(400,400);
        setModal(true);
        setLocationRelativeTo(glavnaAplikacija);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.glavnaAplikacija=glavnaAplikacija;
        this.korisnik=korisnik;
    }


    public void dodajTrenutnoStanjeIKolicinu(){
        stanjeLabel=new JLabel("Stanje"+ korisnik.getTrenutno_stanje());
        stanjeLabel.setBounds(10,20,getWidth()-30,20);
        stanjeLabel.setFont(new Font("Dialog",Font.BOLD,16));
        stanjeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(stanjeLabel);

        unesiKolicinuLabel=new JLabel("Unesite kolicinu novca: ");
        unesiKolicinuLabel.setBounds(10,60,getWidth()-30,20);
        unesiKolicinuLabel.setFont(new Font("Dialog",Font.BOLD,16));
        unesiKolicinuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(unesiKolicinuLabel);



        unesiKolicinuPolje=new JTextField();
        unesiKolicinuPolje.setBounds(15,100,getWidth()-60,40);
        unesiKolicinuPolje.setFont(new Font("Dialog",Font.BOLD,16));
        unesiKolicinuPolje.setHorizontalAlignment(SwingConstants.CENTER);
        add(unesiKolicinuPolje);





    }

    public void dodajAkciju(String akcijskoDugmetip){
        akcijskoDugme=new JButton(akcijskoDugmetip);
        akcijskoDugme.setBounds(15,300,getWidth()-60,40);
        akcijskoDugme.setFont(new Font("Dialog",Font.BOLD,16));
        akcijskoDugme.addActionListener(this);
        add(akcijskoDugme);



    }
    public void dodajPoljeKorisnik(){
        unesiKorisnikaLabel=new JLabel("Unesi Korisnika");
        unesiKorisnikaLabel.setBounds(15,160,getWidth()-20,20);
        unesiKorisnikaLabel.setFont(new Font("Dialog",Font.BOLD,16));
        add(unesiKorisnikaLabel);

        unesiKorisnikaPolje=new JTextField();
        unesiKorisnikaPolje.setBounds(15,200,getWidth()-60,40);
        unesiKorisnikaPolje.setFont(new Font("Dialog",Font.BOLD,20));
        unesiKolicinuPolje.setHorizontalAlignment(SwingConstants.LEFT);
        add(unesiKorisnikaPolje);
    }
    public void dodajStareTransakcijeKomponente() {

        stareTransakcijePanel = new JPanel();

        stareTransakcijePanel.setLayout(new BoxLayout(stareTransakcijePanel, BoxLayout.Y_AXIS));


        JScrollPane scrollPane = new JScrollPane(stareTransakcijePanel);


        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 20, getWidth() - 5, getHeight() - 5);

        stareTransakcije = JDBC.getStareTransakcije(korisnik);


        for (int i = 0; i < stareTransakcije.size(); i++) {

            Transakcija transakcija = stareTransakcije.get(i);


            JPanel stareTransakcijeKontejner = new JPanel();
            stareTransakcijeKontejner.setLayout(new BorderLayout());


            JLabel transakcijeTipaLabel = new JLabel(transakcija.getTransakcija_tip());
            transakcijeTipaLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel transakcijaKolicinaLabel = new JLabel(String.valueOf(transakcija.getTransakcija_kolicina()));
            transakcijaKolicinaLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel transakcijaDatumLabel = new JLabel(String.valueOf(transakcija.getTransakcija_datum()));
            transakcijaDatumLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel transakcijaKo = new JLabel(String.valueOf(korisnik.getIme()));
            transakcijaKo.setFont(new Font("Dialog", Font.BOLD, 20));

            stareTransakcijeKontejner.add(transakcijeTipaLabel, BorderLayout.WEST);
            stareTransakcijeKontejner.add(transakcijaKolicinaLabel, BorderLayout.EAST);
            stareTransakcijeKontejner.add(transakcijaDatumLabel, BorderLayout.SOUTH);
            stareTransakcijeKontejner.add(transakcijaKo, BorderLayout.NORTH);



            stareTransakcijePanel.add(stareTransakcijeKontejner);
        }
        add(scrollPane);
    }



    private void obradaTransakcija(String transakcija_tip,Float kolicina){
        Transakcija transakcija;
        if(transakcija_tip.equalsIgnoreCase("Uplati")){
            korisnik.setTrenutno_stanje(korisnik.getTrenutno_stanje().add(new BigDecimal(kolicina)));
            transakcija=new Transakcija(korisnik.getId(),transakcija_tip,null,new BigDecimal(kolicina));

        }
        else{
            korisnik.setTrenutno_stanje(korisnik.getTrenutno_stanje().subtract(new BigDecimal(kolicina)));
            transakcija=new Transakcija(korisnik.getId(),transakcija_tip,null,new BigDecimal(kolicina));
        }
        if(JDBC.dodajTransakciju(transakcija) && JDBC.novoStanje(korisnik)){
            JOptionPane.showMessageDialog(this,transakcija_tip+"Uspesnoo");

            resetovanje();
        }
        else{
            JOptionPane.showMessageDialog(this,transakcija_tip+"Neuspesnoo...");
        }
    }

    private void resetovanje(){
        unesiKolicinuPolje.setText("");
        if(unesiKorisnikaPolje!=null){
            unesiKorisnikaPolje.setText("");

        }
        stanjeLabel.setText("Stanje: RSD "+korisnik.getTrenutno_stanje());
        glavnaAplikacija.getTrenutno_stanjepolje().setText("RSD"+korisnik.getTrenutno_stanje());
    }
    private void obradiTransfer(Korisnik korisnik,String poslatiKorisnik,float kolicina){
        if(JDBC.transfer(korisnik,poslatiKorisnik,kolicina)){
            JOptionPane.showMessageDialog(this,"Uspesan Transfer!!!");
            resetovanje();

        }
        else {
            JOptionPane.showMessageDialog(this,"Neuspesan transfer...");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String pritsnutoDugme=e.getActionCommand();
        float kolicina=Float.parseFloat(unesiKolicinuPolje.getText());

        if(pritsnutoDugme.equalsIgnoreCase("Uplati")){
            obradaTransakcija(pritsnutoDugme,kolicina);
        }
        else {
            int rezultat=korisnik.getTrenutno_stanje().compareTo(BigDecimal.valueOf(kolicina));
                    if(rezultat<0) {
                        JOptionPane.showMessageDialog(this, "Iznos premasuje vasa Sredstva");
                        return;
                    }
                    if(pritsnutoDugme.equalsIgnoreCase("Podigni")){
                        obradaTransakcija(pritsnutoDugme,kolicina);
                    }
                    else{
                        String poslatoKorisnik=unesiKorisnikaPolje.getText();

                        obradiTransfer(korisnik,poslatoKorisnik,kolicina);

                    }

        }
    }
}
