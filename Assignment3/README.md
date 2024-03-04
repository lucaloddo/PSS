# QuickAnswer Application

## Pre-requisiti

### Installazione di una JDK17 (Java)

Scaricare e installare una JDK dal seguente indirizzo:
[https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

Si noti che la versione di Java specificata nel pom.xml è la 17, perciò è consigliabile installare una JDK17 per
evitare problemi di compatibilità.

### Installare un server MySQL in locale

Dato che l'applicazione crea in automatico, al primo avvio, un database MySQL in locale,
si rende necessario che l'utilizzatore della piattaforma installi sulla propria macchina
un server MySQL. Le informazioni da seguire sono presenti al seguente link:
[https://dev.mysql.com/doc/refman/8.0/en/installing.html](https://dev.mysql.com/doc/refman/8.0/en/installing.html).

Si ricorda che la creazione del DB e lo sviluppo del codice sorgente sono stati fatti su macchine Windows, 
così come i comandi da eseguire presenti nei prossimi paragrafi, perciò è preferibile l'utilizzo su questa macchina.

### Creazione di un utente MySQL

La connessione dall'applicazione al DB avviene per mezzo di uno specifico utente, la cui configurazione è presente
nel file application.properties all'interno del progetto.
Si consiglia la creazione del nuovo utente con i comandi che seguiranno; in alternativa, è possibile modificare il file
di cui sopra con la configurazione che si preferisce.

### Comandi per la creazione e il conferimento di permessi di un nuovo user MySQL

Lanciare questi comandi da una shell MySQL e come root:

```bash
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON *.* TO 'springuser'@'localhost';
```

## Come eseguire l'applicazione
### Effettuare il package
```bash
./mvnw clean package spring-boot:repackage
```
### Lanciare l'applicazione
```bash
java -jar target/quickAnswer-0.0.1-SNAPSHOT.jar
```
L'applicazione sarà disponibile al seguente indirizzo: [http://localhost:8080/](http://localhost:8080/).

## Avvio alternativo dell'applicazione

I seguenti passaggi sono stati eseguiti utilizzando l'IDE IntelliJ IDEA.
Utilizzando altri IDE, il seguente processo di esecuzione potrebbe differire leggermente.

* Aprire l’IDE
* Selezionare File > New > Project from Version Control e inserire il seguente link: [https://gitlab.com/work2gether/2022_assignment3_quickanswer.git](https://gitlab.com/work2gether/2022_assignment3_quickanswer.git)
* Nella finestra Project sulla sinistra, aprire il progetto 'quickAnswer' > src > main > java > it.work2gether.quickAnswer
* Cliccare con il tasto destro sulla classe 'QuickAnswerApplication'
* Cliccare su Run 'QuickAnswerApplication'
* Aprire un Browser (durante lo sviluppo è stato utilizzato Chrome)
* Digitare nella barra degli indirizzi [http://localhost:8080/](http://localhost:8080/) per entrare nell’applicazione web

## Autori

Luca Poli
Riccardo Moschi
Luca Loddo
Matteo Rondena