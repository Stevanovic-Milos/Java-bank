package GUI;

import db_objs.Korisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class GlavnaAplikacija extends glavniOkvir implements ActionListener {
    private JTextField trenutno_stanjepolje;
    public JTextField getTrenutno_stanjepolje(){return trenutno_stanjepolje;}
    public GlavnaAplikacija(Korisnik korisnik){
        super("BB Banka",korisnik);
    }


    @Override
    protected void dodajGuiKomponente() {

        ImageIcon slikaPozadine = new ImageIcon("C:\\Users\\MS\\IdeaProjects\\Banka\\src\\Slike\\RegPoz.png");
        JLabel pozadina = new JLabel(slikaPozadine);
        pozadina.setBounds(0, 0, 700, 500);
        setContentPane(pozadina);

        String pporuka = "<html><body><font color='white' size='5'><b>" +
                korisnik.getIme() + " " + korisnik.getPrezime() + "</b></font></body></html>";

        String ppidoruka = "<html><body><font color='white' size='5'><b>" +
                "broj racuna " + korisnik.getBroj_racuna() + "</b></font></body></html>";

        JLabel pporukaLabel=new JLabel(pporuka);
        pporukaLabel.setBounds(0,20,getWidth(),40);
        pporukaLabel.setFont(new Font("dialog",Font.PLAIN,16));
        pporukaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pozadina.add(pporukaLabel);

        JLabel ppidorukaLabel=new JLabel(ppidoruka);
        ppidorukaLabel.setBounds(0,40,getWidth(),40);
        ppidorukaLabel.setFont(new Font("dialog",Font.PLAIN,16));
        ppidorukaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pozadina.add(ppidorukaLabel);


        trenutno_stanjepolje= new JTextField("Stanje: "+ korisnik.getTrenutno_stanje()+" RSD");
        trenutno_stanjepolje.setBounds(20,140,745,40);
        trenutno_stanjepolje.setFont(new Font("dialog",Font.BOLD,24));
        trenutno_stanjepolje.setHorizontalAlignment(SwingConstants.RIGHT);
        trenutno_stanjepolje.setEditable(false);
        pozadina.add(trenutno_stanjepolje);

        JButton uplatBtt=new JButton("Uplati");
        uplatBtt.setBounds(20,200,340,80);
        uplatBtt.setFont(new Font("dialog",Font.BOLD,22));
        uplatBtt.setHorizontalAlignment(SwingConstants.CENTER);
        uplatBtt.addActionListener(this);
        pozadina.add(uplatBtt);

        JButton podizanjeBtt=new JButton("Podigni");
        podizanjeBtt.setBounds(425,200,340,80);
        podizanjeBtt.setFont(new Font("dialog",Font.BOLD,22));
        podizanjeBtt.setHorizontalAlignment(SwingConstants.CENTER);
        podizanjeBtt.addActionListener(this);
        pozadina.add(podizanjeBtt);

        JButton transakcijeBtt=new JButton("Transakcije");
        transakcijeBtt.setBounds(20,320,340,80);
        transakcijeBtt.setFont(new Font("dialog",Font.BOLD,22));
        transakcijeBtt.setHorizontalAlignment(SwingConstants.CENTER);
        transakcijeBtt.addActionListener(this);
        pozadina.add( transakcijeBtt);

        JButton transferButt=new JButton("Transfer");
        transferButt.setBounds(425,320,340,80);
        transferButt.setFont(new Font("dialog",Font.BOLD,22));
        transferButt.setHorizontalAlignment(SwingConstants.CENTER);
        transferButt.addActionListener(this);
        pozadina.add( transferButt);

        JButton logOutBtt=new JButton("Izloguj se");
        logOutBtt.setBounds(20,440,745,50);
        logOutBtt.setFont(new Font("dialog",Font.BOLD,22));
        logOutBtt.setHorizontalAlignment(SwingConstants.CENTER);
        logOutBtt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GlavnaAplikacija.this.dispose();
                new Login().setVisible(true);
            }
        });
        pozadina.add(logOutBtt);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String pritisnutoDugme=e.getActionCommand();

        BankaDialog bankaDialog=new BankaDialog(this,korisnik);
        bankaDialog.setTitle(pritisnutoDugme);
        if(pritisnutoDugme.equalsIgnoreCase("Izloguj se")){
            new Login().setVisible(true);
            this.dispose();
            return;
        }

        if(pritisnutoDugme.equalsIgnoreCase("Uplati") ||
                pritisnutoDugme.equalsIgnoreCase("Podigni") ||
                pritisnutoDugme.equalsIgnoreCase("Transfer")
        ){

            bankaDialog.dodajTrenutnoStanjeIKolicinu();


            bankaDialog.dodajAkciju(pritisnutoDugme);



            if(pritisnutoDugme.equalsIgnoreCase("Transfer")){
                bankaDialog.dodajPoljeKorisnik();
            }

        }
        else if (pritisnutoDugme.equalsIgnoreCase("Transakcije")) {
            bankaDialog.dodajStareTransakcijeKomponente();
            
        }
        bankaDialog.setVisible(true);

    }


}
