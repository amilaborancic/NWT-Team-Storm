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

## Upute za pokretanje projekta
1. Prvo je potrebno u XAMPP control panelu pokrenuti Apache server i Mysql.
2. Nakon toga, potrebno je pokrenuti Eureka server. Desni klik na eureka folder u repozitoriju - open as IntelliJ IDEA project - klik na play dugme u gornjem desnom ćošku.
3. Nakon što je eureka pokrenuta, na isti način se pokreće svaki od mikroservisa - otvori se kao projekat u IDE po želji i pokrene. Ukoliko želite da istestirate komunikaciju između dva ili više servisa, potrebno je da na isti način pokrenete sve servise koji učestvuju u komunikaciji, inače će doći do greške.

## POSTMAN kolekcije
POSTMAN kolekcije možete importovati u POSTMAN kako bi vam lakše bilo rukovati projektom. 
User:
Katalog: 
Strip:
Rating: 
