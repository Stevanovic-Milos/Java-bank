package db_objs;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class JDBC {
    private static final String DB_URL = "";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";

    public static Korisnik validacijaPrijave(String ime, String sifra) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM korisnik WHERE ime=? AND sifra=?"
            );
            preparedStatement.setString(1, ime);
            preparedStatement.setString(2, sifra);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int korisnikId = resultSet.getInt("id");
                String prezime = resultSet.getString("prezime");

                BigDecimal trenutno_stanje = resultSet.getBigDecimal("trenutno_stanje");
                String broj_racuna=resultSet.getString("broj_racuna");

                return new Korisnik(korisnikId, ime, prezime, sifra, trenutno_stanje,broj_racuna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean registracija(String ime, String sifra,String prezime) {
        try {
            if (!proveraImena(ime)) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO korisnik(ime,prezime,sifra,trenutno_stanje,broj_racuna)" + "VALUES (?,?,?,?,?)"
                );
                preparedStatement.setString(1, ime);
                preparedStatement.setString(2, prezime);
                preparedStatement.setString(3, sifra);
                preparedStatement.setBigDecimal(4, BigDecimal.valueOf(0));
                preparedStatement.setString(5,noviBrRacuna());

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean proveraImena(String ime) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM korisnik WHERE ime=?"
            );
            preparedStatement.setString(1, ime);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String noviBrRacuna() {
        StringBuilder uuid;
        Random rng = new Random();
        int len = 10;
        uuid = new StringBuilder();
        for (int i = 0; i < len; i++) {
            //kastujem integer da bih mogao da koristim to string formu
            uuid.append(((Integer) rng.nextInt(10)).toString());
        }
        return uuid.toString();
    }
    public static boolean dodajTransakciju(Transakcija transakcija){
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement insertutabelu=connection.prepareStatement(
                    "INSERT transakcija(korisnik_id,transakcija_tip,transakcija_kolicina,transakcija_datum)"+
                            "VALUES (?,?,?,NOW())"
            );

            insertutabelu.setInt(1,transakcija.getKorisnik_id());
            insertutabelu.setString(2,transakcija.getTransakcija_tip());
            insertutabelu.setBigDecimal(3,transakcija.getTransakcija_kolicina());
            if (transakcija.getTransakcija_kolicina().compareTo(BigDecimal.ZERO) < 0) {
                return false;
            }
            insertutabelu.executeUpdate();
            return true;

        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }
    public static boolean novoStanje(Korisnik korisnik){
        try {
            Connection connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            PreparedStatement noviBalans=connection.prepareStatement(
                    "UPDATE korisnik SET trenutno_stanje=? WHERE id=?"
            );
            noviBalans.setBigDecimal(1,korisnik.getTrenutno_stanje());
            noviBalans.setInt(2,korisnik.getId());

            noviBalans.executeUpdate();
            return true;

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }
    public static boolean transfer(Korisnik korisnik, String poslatoIme, float transferkolicina) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement querykorisnik = connection.prepareStatement(
                    "SELECT * FROM korisnik WHERE ime = ?"
            );
            querykorisnik.setString(1, poslatoIme);
            ResultSet resultSet = querykorisnik.executeQuery();

            while (resultSet.next()) {
                Korisnik poslatiKorisnik = new Korisnik(
                        resultSet.getInt("id"),
                        poslatoIme,
                        resultSet.getString("prezime"),
                        resultSet.getString("sifra"),
                        resultSet.getBigDecimal("trenutno_stanje"),
                        resultSet.getString("broj_racuna")
                );
                if(transferkolicina<0){
                    return false;
                }


                Transakcija transferTransakcija = new Transakcija(
                        korisnik.getId(),
                        "Transfer",
                        null,
                        new BigDecimal(-transferkolicina)
                );

                Transakcija dobijenaTransakcija = new Transakcija(
                        poslatiKorisnik.getId(),
                        "Transfer",
                        null,
                        new BigDecimal(transferkolicina)
                );

                poslatiKorisnik.setTrenutno_stanje(poslatiKorisnik.getTrenutno_stanje().add(BigDecimal.valueOf(transferkolicina)));
                novoStanje(poslatiKorisnik);

                korisnik.setTrenutno_stanje(korisnik.getTrenutno_stanje().subtract(BigDecimal.valueOf(transferkolicina)));
                novoStanje(korisnik);

                dodajTransakciju(transferTransakcija);
                dodajTransakciju(dobijenaTransakcija);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Transakcija> getStareTransakcije(Korisnik korisnik) {
        ArrayList<Transakcija> stareTransakcije = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement sveTransakcije = connection.prepareStatement(
                    "SELECT * FROM transakcija WHERE korisnik_id=?"
            );
            sveTransakcije.setInt(1, korisnik.getId());
            ResultSet resultSet = sveTransakcije.executeQuery();

            while (resultSet.next()) {
                Transakcija transakcija = new Transakcija(
                        korisnik.getId(),
                        resultSet.getString("transakcija_tip"),
                        resultSet.getTimestamp("transakcija_datum"),
                        resultSet.getBigDecimal("transakcija_kolicina")
                );
                stareTransakcije.add(transakcija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stareTransakcije;
    }




}

