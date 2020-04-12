# NWT-Team-Storm

## Potrebno za pokretanje projekta
Java verzija 13</br>
MySql baza</br>
XAMPP control panel</br>
IntelliJ IDE (poželjno ali nije neophodno)

## Konfiguracija
1. U XAMPP control panel kliknite na config dugme pored Apache server opcija.
2. U meniju koji se prikaže odaberite phpMyAdmin.
3. U tom fajlu potražite username i password polja i postavite ih na username=root, password= (šifre nema).
4. Spasite promjene.
5. Otvorite MySql bazu kroz XAMPP panel i kreirajte 4 prazne baze sa imenima: katalozi, user-service, rating-service i stripovi.

## Upute za pokretanje projekta
1. Prvo je potrebno u XAMPP control panelu pokrenuti Apache server i Mysql.
2. Nakon toga, potrebno je pokrenuti Eureka server. Desni klik na eureka folder u repozitoriju - open as IntelliJ IDEA project - klik na play dugme u gornjem desnom ćošku.
3. Nakon što je eureka pokrenuta, na isti način se pokreće svaki od mikroservisa - otvori se kao projekat u IDE po želji i pokrene. Ukoliko želite da istestirate komunikaciju između dva ili više servisa, potrebno je da na isti način pokrenete sve servise koji učestvuju u komunikaciji, inače će doći do greške.

## Molba kod bodovanja
Vidjeli smo na c2 da je jedan od kriterija za bodovanje broj komita i redovna sedmična aktivnost praćena na GitHubu. Samo želimo da napomenemo da je kolega Ahmed završio Bachelor na AiE pa iz tog razloga njegovih komita nema mnogo. Ostali članovi tima (Amila i Mahira) znaju koliko se je kolega zalagao za projekat i pokušavao da nadoknadi predznanje koje mu je trebalo (rad s bazama, rad sa gitom, osnove oko http zahtjeva i slično). Radio je svake sedmice i to potvrđujemo, i samo bismo htjeli kao tim da zamolimo da se njegov manji broj komita ne shvati kao nerad, nego jednostavno nedostatak predznanja o ovoj oblasti.

## Pokretanje testova

<ins>Testovi koji testiraju komunikaciju dva ili više servisa</ins></br>
sviIzJednogKataloga metoda (Katalog mikroservis, StripControllerTest)
1. Pokrenuti Eureka server.
2. Pokrenuti Strip mikroservis.
3. Pokrenuti testnu metodu sviIzJednogKataloga koja treba da vrati sve stripove koji se nalaze u nekom katalogu.

kreirajKatalog metoda (Katalog mikroservis, KatalogControllerTest)
1. Pokrenuti Eureka server.
2. Pokrenuti Katalog mikroservis.
3. Pokrenuti testnu metodu kreirajKatalog (ili sve testove odjednom za KatalogController klasu).

signUp metoda (User mikroservis, UserControllerTest)
1. Pokrenuti Eureka server.
2. Pokrenuti Katalog mikroservis.
3. Pokrenuti testnu metodu signUp.

addRating metoda (Ratings mikroservis, RatingKontrolerTest)
1. Pokrenuti Eureka server.
2. Pokrenuti Strip mikroservis.
3. Pokrenuti User mikorservis.
4. Pokrenuti testnu metodu addRating.

commentsByStrip metoda (Rating mikroservis, RatingKontrolerTest)
1. Pokrenuti Eureka server.
2. Pokrenuti Strip mikroservis.
3. Pokrenuti testnu metodu commentsByStrip .

<ins>Ostali testovi</ins></br>
1. Pokrenuti Eureka server.
2. Pokrenuti željeni test klikom na play dugme pokraj testne metode/klase.

## POSTMAN kolekcije
POSTMAN kolekcije možete importovati u POSTMAN kako bi vam lakše bilo rukovati projektom.</br>
User:</br>
Katalog:</br>
Strip:</br>
Rating:</br>
