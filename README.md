
<h1>Ohjelmistotekniikka, harjoitustyö</h1>
<h2>Ride Sharing</h2>

Sovelluksen tarkoitus on mahdollistaa kyytien lisäämisen ja toisaalta myös ehdotetun kyydin hyväksymisen. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä - käyttäjät voivat, joka hyväksyä muiden listaamia kyytejä tai vastaavasti lisätä omia kyytejä. Käyttäjällä on myös mahdollisuus katsoa itse ehdottamiensa kyytien tila ts. onko kyyti hyväksytty toisen käyttäjän toimesta vai ei.
<h2> Dokumentaatio</h2>

- [Vaatimusmäärittely](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/vaatimumaarittely.md)
- [Työaikakirjapito](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/tyoaikakirjanpito.md)

<h2> Komentorivitoiminnot </h2>
<h3> Testaus </h3>

Testit suoritetaan komennolla :

```
mvn test
```

Testikattavuusraportti luodaan komennolla:

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_
