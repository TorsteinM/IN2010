# Oblig 1

Obligen har i hovedsak 3 implementasjonsoppgaver. Først en triple ended queue (oppgave 1) og insertion og merge sort(oppgave 2).

# 1 Teque - Triple Ended Queue

## Double ended queue
I følge oppgaveteksten er en dobbelendet kø en struktur som støtter push til både front og back på en sekvensiell struktur.

For en teque - Triple ended queue - skal det også være mulig med push til midten av køen.

Dette kan naturligvis implementeres både som lenket liste og som dynamisk array.

## Array vs. Lenket liste
For dagens maskinvare er dynamiske arrays kort sagt å foretrekke. I hovedsak på grunn av minnefragmenteringen og høy sannsynlighet for stor andel cache miss på oppslag ved bruk av lenkede lister.

Utfordringen blir da å implementere en elegant måte å indeksere en array slik at man slipper å flytte alle elementene i arrayen annenhver gang vi skal legge til et element.

## Ringbuffer og logisk
Vi kan implementere en ringbuffer som kan danne et grensesnitt mellom logisk indeks (posisjon relativt til første element) og fysisk indeks (posisjon i forhold til første element i array). 

Remainder/mod operatoren gjør det enkelt og lesbart å la indeks wrappe rundt til begynnelsen av arrayen igjen.

Hvis vi garanterer at størrelsen på bufferen alltid er en toerpotens(N) kan vi bruke bitmaske (N - 1) for å konvertere mellom logisk indeks og fysisk ved over- og underflow. 

## Deque implementert rundt ringbuffer

Først implementeres Deque og så overlates problemet med push_middle til senere.

Etter implementasjon og testing kan vi si at med Deque basert på CircularBuffer har oppnådd O(1) innsetting både i front og enden av array. Den har fortsatt en kostbar resize når den må vokse, så innsetting generelt er O(1) amortisert.

## Teque
For å kunne push middle trenger vi to Deque strukturer som vi setter sammen. 

For å unngå ekstra nivå i koden implementeres Teque direkte på CircularBuffer.

To ringbuffere L (eft) og R (igth) utgjør strukturen.

Ringbufferene har push/pop både front/back og kan i tillegg returne størrelse.

Det er implementert en Balance som itererer hele strukturen til den er balansert (|L| - |R| = 1 godtas).

Balanseringen opprettholder invarianten |L| - |R| <= 1 og oppfyller kravet om hvilken indeks push_middle skal legges i (som tolket fra oppgaven).

Balanseringen kan ende opp med å bli noen operasjoner, spesielt hvis invarianten av en eller annen grunn ikke var opprettholdt før kallet. Men den vokser ikke med antall elementer i arrayen(e). Den er altså O(1).

Dermed har vi 