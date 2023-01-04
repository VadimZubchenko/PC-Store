# Ohjelmistotuotantoprojekti: PC Store / Easy warehouse management

# Asennus

## Asenettavat työkalut:

- IDE: Apache NetBeans IDE 12.6
- Java: 1.8.0_341
- Maven
- JavaFX
- MySql

## Johdanto

Projektin tavoitteena oli luoda varastonhallintatyökalu pienelle tietokonekaupalle. Sovellus tulee yrityksen sisäiseen käyttöön ja suuntautuu myynnin ja varastonhallinnan tueksi, joten sen on tarkoitus olla graafisesti yksinkertainen ja mahdollisimman suoraviivainen.

## Lähtötiedot

Sidosryhmä koostuu myyntihenkilöistä, varastomiehistä, esimiehestä ja ylläpitäjästä. Jo- kaiselle käyttäjäryhmälle luodaan heille suunnatut käyttöliittymät, eivätkä he pääse mui- den käyttäjien näkymään. Tästä poikkeuksena ylläpitäjä, jolla on käyttöoikeus koko so- vellukseen. Sovellus koostuu myyntisivusta, varastosivusta, tilaussivusta ja taloussi- vusta. Myyntisivulla myyntihenkilö voi luoda tilauksen saatavilla olevista osista ja tietokonepaketeista ja täyttää tilaukseen asiakastiedot. Varastosivulla varastohenkilöstö voi hallita varastoa ja lisätä tai poistaa sieltä tuotteita. Tilaussivulla kaikki työntekijät näkevät parhaillaan olevat tilaukset ja taloussivulla esimies voi tarkkailla yrityksen taloustilan- netta.

## Ohjelmia- ja ohjelmistoja

Sovellus luotiin Apache NetBeans-ohjelmalla käyttäen Maven- työkalua. Ohjelmointi ja sovelluksen luominen suoritettiin käyttäen seuraavia ohjelmointikieliä, relaatiotietokanta- järjestelmiä ja sovelluskehyksiä: Java, Hibernate, SQL, MariaDB, CSS ja XML. Aputyö- kaluina käytettiin Jenkins- ja SonarQube palveluita ja versionhallinnassa käytettiin Git- hubia. Tietokanta on Metropolian Educloud SSH-palvelimella, jota käytettiin Putty-ohjelman avulla.

## MVC

Varastonhallintasovellus on arkkitehtuuriltaan MVC-mallin mukainen. Käyttöliittymän nä- kymä koostuu yhdeksästä eri tiedostosta: View.java, LoginView.java, Tab1.java, Tab2.java, Tab3.java, Tab4.java, UserCreation.java, InputValidatori.java ja PackagePopUp.java. Malli-osio koostuu tiedostoista Asiakas.java, Henkilosto.java, Osa.java, Paketti.java, Paketti_rivi.java, Product.java, TietokonekauppaDAO.java, Tilaus.java ja Tilaus_rivi.java, Encryption.java ja Localization.java. Kontrolleri-osiossa on vain control- ler.java-tiedosto. Tyylit, lokalisaatiot ja hibernate määritellään Other Sources-kansiossa.

## Testaus

Sovelluksen testaus suoritettiin käyttämällä JUnit 5 ja TestFX testiympäristöjä. Testejä muodostui yhteensä 52, mutta osa jouduttiin poistamaan Jenkins ja SonarQube ongelmien takia. TestFX testit toimivat ohjelmassa erittäin mallikkaasti, mutta nämä kaikki jouduttiin karsimaan edellä mainittujen syiden takia. Kuvassa 3 esitetään sovelluksen tes- tien tuloksia. Testeissä testataan sovelluksen tärkeimpiä toimintoja, joiden avulla sovel- luksesta löydettiin haavoittuvaisuuksia ja virheitä.

## Toiminta

Sovellus käynnistyy LoginView.java tiedostosta, jossa käyttäjä kirjautuu tunnuksella ja salasanalla sovellukseen. Käyttäjä voi myös luoda uudet käyttäjätunnukset, jolloin sala- sana salataan tietokantaan käyttäen Base64- kryptausta. Kirjautumisen yhteydessä käyttäjä voi myös valita sovelluksen kielen, mutta sen voi vaihtaa myös myöhemmin so- vellusta käyttäessä.
Kirjautumisen jälkeen aukeaa käyttäjälle suunnattu sivu, eikä hän pääse muiden käyttä- jien sivuille. Tästä poikkeuksena sovelluksen ylläpitäjä, jolla on oikeudet kaikille sivuille. Kuvassa 4 näkyy sovelluksen kirjautumisnäkymä.

Sovelluksen myyntisivulla eli Tab1.java tiedostossa käyttäjä voi tarkastella varastossa olevia tuotteita, jotka haetaan taulukkoon tietokannasta. Tuotteita ovat paketit ja osat, joista voi luoda tilauksen, jolloin kyseisen tuotteen kokonaismäärä varastossa pienenee tilaukseen varatulla määrällä. Käyttäjä voi vaihtaa taulukon näkymää painamalla ra- diopainikkeita, jolloin taulukossa näytetään osat tai paketit valinnan mukaan. Taulukon syötettä voi myös suodattaa hakukentällä, jolloin taulukossa näytetään haun mukaiset tuotteet. Haku on toteutettu FilterList- oliolla, jolloin haku on dynaaminen ja nopea. Va- rastotaulukosta voi valita haluamansa tuotteet ja näiden määrät, joista voi tehdä tilauk- sen. Tilauksen luonti vaatii, että tilaukseen on valittu tuotteita ja asiakaskentät on täytetty. Sovelluksessa on virheilmoitukset, jotka kertovat käyttäjän virhekäytöksestä. Tauluk- koon ei voi lisätä tyhjiä rivejä eikä ilman valintaa voi riviä myöskään poistaa. Näistä tulee myös käyttäjälle virheilmoitus.

Varastosivulla eli tiedostoissa Tab2.java ja PackagePopUp.java käyttäjä voi valita halua- mansa tuotteet vasemmalta, jolloin taulukkoon tulee tietokannasta saatavilla olevat tuot- teet ja näiden tiedot, kuten kuvitteellinen varastopaikka. Sivulla voi myös luoda uusia tuotteita, eli osia ja paketteja. Paketti luodaan kasaamalla osista paketti, jolloin ohjelma laskee osien hinnan ja lisää tähän prosentuaalisen katteen.'

Tilaukset sivulla tiedostoissa Tab3.java listataan taulukkoon kaikki tehdyt tilaukset. Ti- lauksista näkee tilauksen ID:n, asiakastiedot, tilauspäivämäärän ja tilauksen yhteishin- nan. Valitsemalla taulukosta yhden tilauksen, tulee alempaan taulukkoon näkyviin tilauk- sen tilausrivit eli tilatut tuotteet. Valitun tilauksen voi myös poistaa halutessaan.

Taloussivulla tiedostossa Tab4.java rakennetaan graafi myydyistä tilauksista. Graafi päi- vittyy automaattisesti jokaisen tilauksen luonnin yhteydessä.

Sovelluksen NavBar luodaan tiedostossa View.java, jolloin se näkyy jatkuvasti muiden välilehtien päällä kuten halusimme. NavBar kertoo kirjautuneen käyttäjän, kirjautumis- ajan ja siitä voi vaihtaa sovelluksen kieltä tai kirjautua ulos. Uloskirjautuminen ohjaa käyt- täjän takaisin kirjautumissivulle, jossa käyttäjä voi kirjautua takaisin tai sulkea sovelluk- sen.

## Issues and their fixing.

Starting Error reason: install Java 1.8.341

Next change mySql dependences in pom.xml according your MacBook installed mysql version

Create database Teitokonekauppa and all needed tables esim. using command line or ’mySql Workbanch’

After creating DB you can check in mySql Workbanch EER Diagram by Database –> Reverse Engineer

Final ISSUE: After creating all needed tables there still was an error message "Schema-validation: missing table [hibernate_sequence]”

1. add to all tables anotation: @GeneratedValue(strategy=GenerationType.IDENTITY)
2. after that you will see a log message, which table or column are missed or has an error in their name.
   - fault was in the name ”Henkilisto” but in code anotation is ”Henkilosto” in ”TILAUS” table - ”Hyllynumro” in the code anatation of ”OSA” table but in table itself it was as a ”Hyllynnumero”
