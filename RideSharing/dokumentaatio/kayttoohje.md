
<h1> Käyttöohje </h1>

<h2> Suorittamiseen tarvittava jar-tiedosto </h2>

[Loppupalautus jar](https://github.com/OttoLasma/ot-harjoitustyo/releases/tag/loppupalautus)

<h2> Konfigurointi </h2> 

Käynnistämisen yhteydessä käyttäjää pyydetään nimeämään käytettävän tietokannan nimi. Sovellus luo uuden tiedoston, mikäli nimettyä tietokantaa ei ole olemassa sovelluksen käynnistys vaiheessa.


<h2> Ohjelman käynnistäminen </h2>

Ohjelma käynnistetään seuraavalla komennolla:

```
java -jar Ridesharing.jar
```

<h2> Kirjautuminen/"tuntemattoman" käyttäjän näkymä </h2> 

Ensin käyttäjää pyydetään siis nimeämään käytettävä tietokanta.

![Kirjautuminen](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/tietokannanNimeaminenjaAloitus.png "kirjautuminen")


Tässä vaiheessa käyttäjälle on kolme mahdollisuutta edetä:

1. Sovelluksen lopettaminen ("exit"), joka tapahtuu komennolla "x".

2. Uuden käyttäjän luominen ("Sign up"), joka tapahtuu komennolla "1".

3. Kirjautuminen jo luodulla käyttäjätunnuksella ("Sign in"), joka tapahtuu komennolla "2".

Uuden käyttäjän luomisen yhteydessä kayttäjältä edellytetään seuraavien tietojen antamista:

- Nimi
- Sukunimi
- Puhelinumero
- Sähköpostiosoite
- Käyttäjätunnus, jonka tulee olla uniikki, eli käyttäjältä pyydetään uutta salasanaa, mikäli tietokannasta löytyy vastaavaa käyttäjätunnus.
- Salasana

Kirjautumisessa käyttäjältä pyydetään aikaisemmin luotu käyttäjätunnus ja salasana. Mikäli käyttäjätunnus tai salasana eivät täsmää tietokantaan lisättyihin tietoihin ei kirjautuminenkaan onnistu.

Onnistunut kirjautuminen tai uuden käyttäjän luominen ohjaavat käyttäjän "kyytinäkymään".

<h2> Kirjautuneen käyttäjän toiminnallisuudet </h2>

![valinnat](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/valinnat.png "valinnat")

<h3> Kirjautuneella käyttäjällä on valittavissa seuraavat toiminnallisuudet: </h3>

1. Uuden kyydin luominen ("add new ride"):

Tässä käyttäjää pyydetään täyttämään kyydin yleiset tiedot, jotka ovat seuraavat: 

- Lähtöpaikka ("Location of departure")

- Määränpää ("Location of destination")

- Kyydin hinta ("Total price of the ride")

- Vapaiden paikkojen lukumäärä ("Number of available seats")

- Arvioitu lähtöaika ("estimated time and date of the departure (mm/dd-hh/mm)")

2. Vapaiden kyytien tarkastelu ("List of all available rides"):

- Tässä käyttäjälle listataan kaikki vapaana olevat kyydit, joka mahdollistaa itselle mieleisten kyytien tarkastelun.

3. Käyttäjän itsensä listaamien kyytien listaaminen ("List all the rides that have been added by you"):

- Tässä käyttäjälle listataan kaikki kyydit, jotka ovat käyttäjän itsentsä lisäämiä.

4. Vapaana olevan kyydin varaaminen ("Reserve a ride from available rides")

- Käyttäjän tulee valita vapaista kyydeistä itselle mieluinen kyyti tietokannasta, joka varataan hänelle ja edelleen poistetaan vapaiden kyytien listasta. Onnistunut kyydin varaaminen lähettää kyydin ilmoittaneelle henkilölle sähköpostiviestin asiasta. Mikäli mieluisaa kyytie ei ole tajolla, voi näkymästä poistua painamalla "x".

5. Käyttäjän varaamien kyytien tarkastelu ("List all of the rides that have been reserved by you")

- Tässä käyttäjälle listataan kaikki kyydit, jotka ovat käyttäjän itsensä varaamia.

6. Käyttäjäle ilmoitetaan, kuinka monta kyytiä hän on ilmoittanut järjestelmään ja toisaalta, kuinka monta kyytiä on varannut järjestelmän kautta. 







