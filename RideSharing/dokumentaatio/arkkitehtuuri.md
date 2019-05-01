<h1> Arkkitehtuurikuvaus </h1>

<h2> Rakenne </h2>

Koodin pakkausrakenne on seuraavanlainen:

![pakausrakenne](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/pakkauskaavio.jpeg "pakkausrakenne")

Pakkaus _riderharing.ui_ sisältää tekstikäyttöliittymän, sekä tietokantojen ja springin alustamiseen tarvittavat komennot, pakkaus _riderharing.domain_ sisältää suurimman osan sovelluksen sovelluslogiikasta ja pakkaus _riderharing.dao_ sisältää tietokantoihin liittyvät toiminnallisuudet. Huomattavaa on, ettö osa sovelluslogiikasta on pakkauksessa _RideSharing.dao_, sillä bean ja spring mahdollistaa todella yksinkertaisia keinoa erilaisten toiminnallisuuksien toteuttamiseen. 

<h2> Käyttöliittymä </h2>

Sovelluksessa on tosiaan tekstikäyttöliittymä, sillä en lukuisista yrityksistä huolimatta saanut spring:iin "yhteyttä", kun käytössä oli javaFX. Toteutettu tekstikäyttöliittymä pitää sisällään kaksi "näkymää", joista toinen on näkymä ennen kirjautumista ja toinen vastaavasti kirjautuneen käyttäjän näkymä. Käyttöliittymä on toteutettu ohjelmallisesti luokassa _ridesharing.ui.TextUserInterface_. 

Sovelluslogiikka on suurimmilta osin pystytty eristämään käyttöliittymästä, kutsumalla sovelluslogiikan toteuttamaa luokkaa _ridesharing.domain.RidesharingService_.

<h2> Sovelluslogiikka </h2>

RideSharing -sovelluksen looginen datamalli muodostuu luokista Reserve, Ride sekä User, jota hahmoittaa alla oleva kuva:

![sovelluslogiikka](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/luokkakaavio.jpeg "sovelluslogiikka")

Sovelluksen toiminnallisuuksista vastaa RidesharingService luokan olio. Jokaiselle toiminnallisuudelle on pyritty luomaan oma metodi, mutta myös erilaiset tarkistuksen (esim. onko käyttäjätunnus varattu) on suoritettu tämän samaisen luokan puitteissa. Päällimäinen tarkoitus tässä on ollut erittää käyttöliittymä mahdollisimman kokonaisvaltaisesti sovelluslogiikasta. RidesharingService luokka/olio pitää sisällään seuraavat metodit:

- String usernameHasAlreadyTaken(String username, Scanner scanner)

- createUser(User user)

- User readUser(int variableId)

- createRide(Ride ride)

- createReserve(Reserve reserve)

- reserveRideAndReserve(List<Ride> list, String variable, int userId)

- correctCredentials(String username, String password)

- List<Ride> returnListofAvailableRides()
  
- List<Ride> returnListofUsersRides(int userId)
  
- List<Reserve> returnListofUsersReserves(int userId)
  
  
RidesharingService -luokka tekee muokkaukset tietokantoihin _ReserveDao, RideDao_ sekä _UserDao_ luokkien välityksellä. Nämä kyseiset luokat sijaitsevat pakkauksessa _ridesharing.domain_ ja ne toteuttavat samassa luokassa olevan rajapinnan _RideSharingDao_. 

Yllä todettua havainnollistaaa alla oleva kuva pakkauskaaviosta:

![Pakkauskaavio](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/kolmas.jpeg "pakkauskaavio")

<h2> Tietojen tallentaminen tietokantaan </h2> 

Yhteys luotuun tietokantaan muodostetaan samalla kun sovellus käynnistetään. Sovelluksen käyttäjää pyydetään sovelluksen käynnistämisen yhteydessä nimeämään tietokanta, jota tullaan sovelluksessa käyttämään. Tietojen lisäämisestä, päivittämisestä, poistamisesta, muuttamisesta ja hakemisesta vastaavat pakkauseen _ridesharing.dao_ sijoitetut luokat _ReserveDao_, _RideDao_, sekä _UserDao_. 

Alla on esitettynä luodut tietokanta taulut:

```
DROP TABLE User IF EXISTS
```
```
CREATE TABLE User(id integer auto_increment, name varchar(255), surname varchar(255), phone varchar(255), email varchar(255), username varchar(255), password varchar(255), primary key (id))
```
```
DROP TABLE Ride IF EXISTS
```
```
CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), userId integer, available integer, primary key (id), foreign key (userId) references User(id))
```
```
DROP TABLE Reserve IF EXISTS
```
```
CREATE TABLE Reserve(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), userId integer, available integer, primary key (id), foreign key (userId) references User(id))
```

Alla on listattuna yleisimmin käytetyt tietokantakyselyt koodimuodossa (esimerkissä käytetään User -taulua):

- Uuden käyttäjän lisääminen:
```
String sql = "INSERT INTO USER(name, surname, phone, email, username, password) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getPhone());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUsername());
        pstmt.setString(6, user.getPassword());
        pstmt.executeUpdate();
        String sql2 = "select last_insert_rowid()";
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        ResultSet rs = pstmt2.executeQuery();
        user.setId(rs.getInt(1));
```
- Tietyn käyttäjän hakeminen id:n perusteella
```
String sql = "SELECT * FROM User WHERE id = " + key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
        user.setId(rs.getInt("id"));
        return user;
```
- Tietyn käyttäjän attribuuttien päivittäminen
```
String sql = "UPDATE User SET name = ?, surname = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getPhone());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUsername());
        pstmt.setString(6, user.getPassword());
        pstmt.setInt(7, user.getId());
        pstmt.executeUpdate();
        return user;
```
- Kaikkien käyttäjien palauttaminen listana
```
String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            user.setId(rs.getInt("id"));
            users.add(user);
        }
        return users;
```


<h2> Päätoiminnallisuudet </h2>

Seuraavassa on esitettynä osa sovelluksen toiminnallisuuksista sekvenssikaavioiden avulla.

<h5> Kirjautuminen </h5>

Kirjautuminen onnistuu, mikäli käyttäjä on aikaisemmin luotuna. Lisäksi kirjautumisessa edellytetään luonnollisesti, että käyttäjän syöttä käyttäjätunnus, sekä salasana ovat oikeelliset. Onnistunut kirjautuminen ohjaa käyttäjän kirjautuneen käyttäjän näkymään.

![signin](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/signin.jpeg "signin")

<h5> Uuden käyttäjän luominen </h5>
Uuden käyttäjän luomisen yhteydessä käyttäjää pyydetään antamaan seuraavat tiedot:

- nimi
- sukunimi
- puhelin
- sähköpostiosoite
- käyttäjätunnus (käyttäjätunnuksen tulee olla uniikki)
- salasana 

Onnistunut käyttäjän luominen ohjaa käyttäjän kirjautuneen käyttäjän näkymään.


![signup](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/sign.jpeg "signup")

<h5> Varauksen tekeminen </h5>

Varauksen tekemisen yhteydessä käyttäjälle näytetään tällä hetkellä vapaana olevat kyydit. Vapaana olevistä kyydeistä käyttäjän on mahdollista varata itselleen mieluisa. Onnistuneet varauksen yhteydessä kyydin lisänneelle henkilölle lähetetään sähköpostiviesti asiasta.

![reserve](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/varaakyyti.jpeg "reserve")


<h5> Vapaiden kyytien listaaminen </h5>

Tässä käyttäjälle esitetään kaikki tällä hetkellä vapaana olevat kyydit.

![signup](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/listaa.jpeg "signup")


<h5> Muut toiminnallisuudet </h5>

Muut toiminnallisuudet noudattavat samaa logiikkaa, kuin edellä kuvatut toiminnallisuudet. Yhdistävä piirre on sovelluslogiikan ulkoistamisen _RidesharingService_ -luokalle, sekä _Dao_ -luokille.



