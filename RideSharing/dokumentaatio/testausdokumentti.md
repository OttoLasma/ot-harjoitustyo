<h1> Testausdokumentti </h1>

Sovelluksen testaus on toteutettu JUnitilla automaattista yksikkötestausta hyödyntämällä. Testien kokonaismäärä on 53 kappaletta,
pyrkimyksenä on ollut, että yksi testi testaisi vain yhtä metodia. Toisaalta monet metodit edellyttävät tiettyjä toimenpiteita ennen, kuin näitä voi järkevissä määrin testata. Osa luokan _RidesharingService_ metodeista on jätetty testaamatta, sillä ne on testattu jo luokkien _RideDao_, _UserDao_ sekä _ReserveDao_ testeissä. Tämä selittyy sillä, että olen pyrkinyt ulkoistamaan sovelluslogiikan kokonaisuudessaan käyttöliittymän ulkopuolelle.

<h2> Yksikkö- ja integraatiotestaus </h2>


