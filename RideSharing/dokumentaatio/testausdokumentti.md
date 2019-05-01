<h1> Testausdokumentti </h1>

Sovelluksen testaus on toteutettu JUnitilla automaattista yksikkötestausta hyödyntämällä. Testien kokonaismäärä on 61 kappaletta,
pyrkimyksenä on ollut, että yksi testi testaisi vain yhtä metodia. Toisaalta monet metodit edellyttävät tiettyjä toimenpiteita ennen, kuin näitä voi järkevissä määrin testata. .

<h2> Yksikkö- ja integraatiotestaus </h2>

<h3> Sovelluslogiikka </h3>

Sovelluslogiikka käytännössä kokonaisuudessaan testattu luokan _RidesharingServiceTest_ avulla. Kyseinen luokka on sijoitettu pakkaukseen _ridesharing.domain_. Muita _ridesharing.domain_ pakkauksessa olevia luokkia testaavaat "testiluokat" _RserveTest_, _RideTest_, sekä _UserTest_ luokat. 

Seuraavat luokat tallentavat tietokantaan arvoja:

- ReserveDao
- RideDao
- UserDao
- RidesharingService (välillisesti yllä olevien luokkien avulla)

, joten näiden luokkien testauksessa olen hyödyntänyt testaukseen luotavie erillisiä tietokantoja, jotka poistetaan testauksen yhteydessä jokaisessa luokassa olevan @After -notaation avulla. Testaukseen  käytetyt tietokannan on nimetty seuraavasti: userTest.db, reserveTest.db, serviceTest.db ja rideTest.db.

<h3> Testikattavuus </h3>

Testauksen rivikattavuus on 92 -prosenttia ja haarautumakattavuus on puolestaan 72 -prosenttia, jotka täyttävät tehtävänannossa määritetyn korkean testikattavuuden. Testauksen ulkopuolelle on jätetty pakkaus ridesharing.ui, joka sisältää luokat _main_, sekä _TextUserInterface_. Nämä samaisen luokat pitävät sisällään tekstikäyttöliittymät, sekä tietokannan alustuksen. Alla esitetty kuva jacoco -raportista:

![testikattavuus](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/testikattavuus.png "testikattavuus")

![testikattavuus](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/testikattavuus2.png "testikattavuus")

![testikattavuus](https://github.com/OttoLasma/ot-harjoitustyo/blob/master/RideSharing/dokumentaatio/testikattavuus3.png "testikattavuus")

Ohjeet jacoco -raportin suorittamiseen ja tieto raportin sijainti suhteessa juurikansioon on annettu Readme.md -tiedostossa.

Testaamatta jäivät tilanteen, jossa tietokantaan ei saada yhteyttä tai käyttäjältä vaadittaisiin syöte testauksen suorittamiseksi. 

<h3> Testaukseen/sovellukseen liittyvät ongelmat </h3> 
  
  Käyttäjän syötteiden validointiin ei ole panostettu kovinkaan paljon ja tämä voidaan luokitella selkeäksi puutteeksi. Validointi on oikeastaan toteutettu lähinnä käyttäjän antamalle käyttäjätunnukselle, sekä salasanalle -tarkastamalla, että käyttäjätunnuksen tulee olla uniikka ja käyttäjä pääsee rekisteröitymään vain, mikäli tietokantataulusta User löytyy käyttäjän antama salasana, sekä käyttäjätunnus.

