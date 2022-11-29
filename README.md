# Ohjelmistotuotantoprojekti: Easy warehouse management

## Tietokonekauppa App installation. 

- IDE: Apache NetBeans IDE 12.6
- Java: 1.8.0_341
- Maven
- JavaFX
- MySql


## Issues and their fixing.

 Starting Error reason: install Java 1.8.341

Next change mySql dependences in pom.xml according your MacBook installed mysql version 
 
Create database Teitokonekauppa and all needed tables esim. using command line or ’mySql Workbanch’

After creating DB you can check in mySql Workbanch EER Diagram by Database –> Reverse Engineer 

Final ISSUE: After creating all needed tables there still was an error message "Schema-validation: missing table [hibernate_sequence]”

1. add to all tables anotation: @GeneratedValue(strategy=GenerationType.IDENTITY)
2. after that You could see in log message, which table misses column or has an error in their name.   
        -  my fault was in the name ”Henkilisto” but in code anotation is ”Henkilosto” in ”TILAUS” table 
        - ”Hyllynumro” in the code anatation of ”OSA” table but in table itself it was as a ”Hyllynnumero”


