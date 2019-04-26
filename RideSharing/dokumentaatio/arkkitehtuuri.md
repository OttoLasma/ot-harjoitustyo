<h1> Arkkitehtuurikuvaus </h1>

<h2> Rakenne </h2>

Koodin pakkausrakenne on seuraavanlainen:

![pakausrakenne](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/pakkauskaavio.jpeg "pakkausrakenne")

Pakkaus _riderharing.ui_ sisältää tekstikäyttöliittymän, sekä tietokantojen ja springin alustamiseen tarvittavat komennot, pakkaus _riderharing.domain_ sisältää suurimman osan sovelluksen sovelluslogiikasta ja pakkaus _riderharing.dao_ sisältää tietokantoihin liittyvät toiminnallisuudet. Huomattavaa on, ettö osa sovelluslogiikasta on pakkauksessa _RideSharing.dao_, sillä bean ja spring mahdollistaa todella yksinkertaisia keinoa erilaisten toiminnallisuuksien toteuttamiseen. 

<h2> Käyttöliittymä </h2>

Sovelluksessa on tosiaan tekstikäyttöliittymä, sillä en lukuisista yrityksistä huolimatta saanut spring:iin "yhteyttä", kun käytössä oli javaFX. Toteutettu tekstikäyttöliittymä pitää sisällään kaksi "näkymää", joista toinen on näkymä ennen kirjautumista ja toinen vastaavasti kirjautuneen käyttäjän näkymä. Käyttöliittymä on toteutettu ohjelmallisesti luokassa _ridesharing.ui.Interface_ (_aikaisempi ridesharing.ui.kokeiluKayttoliittyma_). 

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

Yhteys luotuun tietokantaan muodostetaan samalla kun sovellus käynnistetään. Tietokantoujen hallinnoimisessa olen käyttänyt spring:iä ja tietokanta on itsessään on h2. Sovelluksen suorittaminen luo käynnistyshakemistoon tietokannan nimeltä: _RideSharingDatabases.mv.db_. Tietojen lisäämisestä, päivittämisestä, poistamisesta, muuttamisesta ja hakemisesta vastaavat pakkauseen _ridesharing.dao_ sijoitetut luokat _ReserveDao_, _RideDao_, sekä _UserDao_. 

Alla on esitettynä luodut tietokanta taulut (_huom. arvosteluperusteissa ei ole edellytetty kiinnittämään huomiota taulujen järkevään toteutukseen_):

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
KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User"
                    + " (name, surname, phone, email, username, password)"
                    + " VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
```
- Tietyn käyttäjän hakeminen id:n perusteella
```
User user = jdbcTemplate.queryForObject(
                "SELECT * FROM User WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                key);

        return user;
```
- Tietyn käyttäjän attribuuttien päivittäminen
```
jdbcTemplate.update("UPDATE User SET name = ?, surname = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?",
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getId());

        return user;
```
- Kaikkien käyttäjien palauttaminen listana
```
return jdbcTemplate.query(
                "SELECT * FROM User",
                new BeanPropertyRowMapper<>(User.class));
```


<h2> Päätoiminnallisuudet </h2>

Seuraavassa on esitettynä osa sovelluksen toiminnallisuuksista sekvenssikaavioiden avulla.

<h5> Kirjautuminen </h5>

<h5> Uuden käyttäjän luominen </h5>

<h5> Varauksen tekeminen </h5>

<h5> Vapaiden kyytien listaaminen </h5>

<h5> Muut toiminnallisuudet </h5>



![arkkitehtuuri](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/IMG_8493.jpg "arkkitehtuuri")

![arkkitehtuuri](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/photo5888638482417561668.jpg "sekvenssikaavio")
