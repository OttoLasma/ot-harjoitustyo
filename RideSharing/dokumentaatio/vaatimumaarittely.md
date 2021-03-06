<h1> Vaatimusmäärittely </h1>
<h2> Sovelluksen tarkoitus </h2>
Sovelluksen tarkoitus on mahdollistaa kyytien lisäämisen ja toisaalta myös ehdotetun kyydin hyväksymisen. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä - käyttäjät voivat, joka hyväksyä muiden listaamia kyytejä tai vastaavasti lisätä omia kyytejä. Käyttäjällä on myös mahdollisuus katsoa itse ehdottamiensa kyytien tila ts. onko kyyti hyväksytty toisen käyttäjän toimesta vai ei.  

Mahdollinen tapausesimerkki: 

Pekka on menossa autolla Helsingistä Tampereelle ja autossasi olisi alustavasti kolme paikkaa tyhjänä. Pekka voi ilmoittaa sovelluksessa tarjoavansa kolmelle henkilölle kyydin Tampereelle tiettynä ajankohtana. Toisaalta Pekka vois myös katsoa, onko kukaan toinen käyttäjä ilmoittanut vapaista paikoista samoilla spekseilla ja hyväksyä tämän ehdotuksen.  
<h2> Käyttäjät </h2>
Sovelluksella on yksi käyttäjäroolia, jolloin kaikilla käyttäjillä on mahdollisuus lisätä ja hyväksyä kyytejä. Olisi mahdollisesti mielekästä lisätä vielä yksi käyttäjärooli, josta olisi mahdollista päästä käsiksi erilaisiin raportteihin.
<h2> Perusversion tarjoamat toiminnallisuudet </h2>
<h3> Ennen kirjautumista </h3>

- Sovelluksen käyttäjä nimeää käytettävän tietokanta tidoston nimen.
- Sovelluksen käyttäjät voivat luoda uuden käyttäjätunnuksen
  - Tunnuksen tulee olla sellaisen, ettei se ole käytössä muilla käyttäjillä. Lisäksi kirjautmisen yhteydessä käyttäjältä edellytetään nimen, sukunimen, puhelinnumeron, sähköpostin, sekä salasanan täyttämistä.
- Käyttäjätunnuksen luonut henkilö voi kirjautua sovellukseen
  - Kirjautuminen onnistuu, mikäli käyttäjätunnus on oikein kirjoitettu. Kirjautumisessa edellytetään myös salasanan syöttämistä.
 <h3> Kirjautumisen jälkeen </h3>
 
- Käyttäjällä on valittavissa kuusi toiminnallisuutta, jotka ovat seuraavat: kyydin varaaminen, kättäjän itsensä tekemien kyytien tarkastelu, käyttäjän itsensä tekemien varausten tarkastelu, kyytien tarkastelu, yhteenveto käyttäjän kyydeistä sekä kyydin lisääminen. 
- Kyydin lisäämisen yhteydessä edellytetään seuraavat tiedot: päivämäärä, lähtöpaikka, hinta, määränpää ja vapaiden paikkojen lukumäärä.
- Onnistunut kyydin lisäys lisää tämän samaisen kyydin vapaisiin kyyteihin, jossa on katsottavissa kaikki avoinna olevat kyydit.
- Käyttäjä voi valita mieleisensä avoimen kyydin ja merkata sen käytetyksi, jollain samainen kyyti katoaa avoimien kyytien listauksesta. Tällöin kyydin ilmoittaneelle henkilölle lähetetään sähköposti asiasta.
- Käyttäjän itsensä varaamat/listaaman kyydit voidaan listata käyttäjälle, mikäli tämä on tarpeellista.

<h3> Jatkokehitysideoita </h3>


- Käyttäjät voivat tarkastella vain itse lisäämiään kyytejä ja muokana niitä halutusti, esimerkiksi poistaa aikaisemmin ilmoitettu kyyti.
- Kyytiehdotuksesta ei paljastettaisi listauksen yhteydessä, kuin kuljettajan kuvaus, määränpää, hinta, lähtöpaikka ja ajankohta - mahdolliset kuljettajan yhteystiedot tulisivat näkyviin vasta, kun kyyti on hyväksytty.
- Käyttäjillä on mahdollisuus lisätä saldoa omalle käyttätunnukselleen, josta maksu voitaisiin suorittaa. 
- Pääkäyttäjä voisi listata erilaisia raportteja, kuten suoritetut kyydit, suosituimmat reitit yms.
