# PROCESSO E SVILUPPO DEL SOFTWARE

### MEMBRI DEL TEAM

* Davide Zangari 844760
* Matteo Rondena 847381
* Luca Loddo 844529

Repository GitLab dell'app: https://gitlab.com/rzl1/app.git

### PROGETTAZIONE APP

Abbiamo sviluppato una semplice applicazione che sfrutta un database MySQL per memorizzare alcune semplici informazioni 
sugli studenti dell'Università di Milano-Bicocca di vari corsi di studio in una tabella apposita. Nella tabella sono 
raccolti i seguenti campi: matricola, nome, cognome e corso. La matricola rappresenta la chiave primaria della tabella
in cui sono memorizzati gli studenti. L'applicazione usa il framework Spring e mette a disposizione una API REST (/all) che 
permette di ottenere la lista completa di tutti gli studenti presenti nel DB.

### PIPELINE

Sono stati inseriti, come da richiesta, i seguenti stage: build, verify, unit-test, integration-test, package, release,
deploy.
Di seguito è riportata una descrizione riassuntiva degli stage:

* ##### _Build_ 
  Nello stage _build_ l'applicazione viene compilata, e vengono scaricate le dipendenze, con l'ausilio di Maven.   

* ##### _Verify_ 
  In _verify_ si è proceduto all'esecuzione di un'analisi statica del codice sorgente sfruttando le funzionalità della 
  libreria Spotbugs lanciata con Maven.

* ##### _Unit-test_ 
  In _unit-test_ si effettuano i test presenti nella classe UnitTest sfruttando l'opzione _-Dtest_
  nel comando Maven per limitare l'esecuzione ai soli test presenti nella classe passata come parametro.

* ##### _Integration-test_ 
  Come svolto nello stage precedente, si sono effettuati i test d'integrazione limitandone l'esecuzione alla sola classe
  IntegrationTests, per valutare il corretto funzionamento dei componenti dell'applicazione in comunicazione tra loro, 
  ovvero del DB in relazione all'applicazione stessa.
  E' stato necessario caricare il DB nell'ambiente della pipeline, previa installazione del client di MySql e caricamento del DB 
  tramite un dump creato in locale, così da valutare la corretta integrazione dei componenti.

* ##### _Package_ 
  In _package_ viene generato il pacchetto .jar dell'applicazione. Tale pacchetto è creato seguendo il profilo "prod", o
  production, che sfrutta la configurazione presente all'interno della resource _application-prod.properties_. 
  Questa configurazione è costituita da proprietà che consentono all'applicazione di comunicare collegandosi al DB remoto
  presente su ClearDB in Heroku.

* ##### _Release_ 
  Nello stage di _release_ viene sviluppato il pacchetto .jar, generato nello stage precedente, come immagine Docker 
  internamente al Gitlab Container Registry, per poi essere pushata sul Registry di Heroku.

* ##### _Deploy_ 
  Nell'ultimo stage, concernente la fase di _deploy_, si è proceduto all'installazione dell'Heroku Command Line Interface
  (CLI), tramite le librerie _curl_ e _bash_, per effettuare la distribuzione vera e propria dell'applicazione, 
  recuperandone l'immagine dal Registry di Heroku.


L'applicazione è disponibile al seguente indirizzo: https://app-rzl.herokuapp.com/ , all'endpoint '/all'.



