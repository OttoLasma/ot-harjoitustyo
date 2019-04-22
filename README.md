
<h1>Ohjelmistotekniikka, harjoitustyö</h1>
<h2>Ride Sharing</h2>

Sovelluksen tarkoitus on mahdollistaa kyytien lisäämisen ja toisaalta myös ehdotetun kyydin hyväksymisen. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä - käyttäjät voivat, joka hyväksyä muiden listaamia kyytejä tai vastaavasti lisätä omia kyytejä. Käyttäjällä on myös mahdollisuus katsoa itse ehdottamiensa kyytien tila ts. onko kyyti hyväksytty toisen käyttäjän toimesta vai ei.
<h2> Dokumentaatio</h2>

- [Vaatimusmäärittely](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/vaatimumaarittely.md)
- [Työaikakirjapito](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/tyoaikakirjanpito.md)
- [Arkkitehtuuri](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/arkkitehtuuri.md)

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

<h3> CheckStyle </h3>

Suoritetaan komennolla :

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle-raportin löydät polusta /target/site/checkstyle.html

<h3> Jarin generointi </h3>


Suoritetaan komennolla:

```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston original-RideSharing2-1.0-SNAPSHOT.jar (huom jar tiedoston nimi)


<h3> JavaDock </h3>


JavaDoc generoidaan seuraavalla komennolla

```
mvn javadoc:javadoc
```

JavaDoc raportti voidaan avata selaimella täältä: _target/site/apidocs/index.html_



