# Oblig 1

Del 1: Implementasjon av trippelendet kø og tilhørende kompleksitetsvurdering.
Del 2: Implementasjon av Insertion Sort og Merge Sort

# 1 Teque - Triple Ended Queue

## Double ended queue
Ifølge oppgaveteksten er en dobbeltendet kø en struktur som støtter push til både front og back på en sekvensiell struktur.

For en teque - Triple ended queue - skal det også være mulig med push til midten av køen.

## Array vs. Lenket liste
Arrays har en rekke fordeler sammenlignet med lister ved blant annet direkte indeksering og enkelte maskinvare-aspekter som ligger litt på siden av faget.

En av utfordringene for arrays er konvensjonen med at indeks 0 er det første elementet og deretter følger et gitt antall elementer i serie. 

Dermed må normalt alle verdiene flyttes ett hakk om et nytt element skal få første posisjon i en konvensjonell array.

## Ringbuffer og logisk vs fysisk indeks
Ringbuffer kan danne et grensesnitt mellom logisk indeks og fysisk indeks og gjøre det relativt sømløst å legge til elementer både før og etter verdiene som allerede ligger i arrayet.

Modulooperatoren gjør det mulig å indeksere riktig fysisk indeks ved å la indeks wrappe rundt kapasitetstallet
$$
i_{fysisk} = (start + i_{logisk}) \mod kapasitet
$$

Et alternativ til modulooperatoren er å låse kapasiteten til en toerpotens og maskere indekset til intervallet $[0, 2^k)$ der $k \in \mathbb{N}$
$$
i_{fysisk} = (start + i_{logisk})\,\&\,(kapasitet - 1)
$$

Ringbuffere gir altså konseptuell funksjonalitet for å legge til elementer på begge ender.

## Deque implementert rundt ringbuffer

Deque er implementert som enklere struktur for testing og verifisering av den underliggende CircularBuffer-implementasjonen før denne brukes i Teque.

I implementasjonen av Deque som ringbuffer/dynamisk array har push_front og push_back amortisert O(1) og get har O(1).

## Teque basert på to ringbuffere (Oppgave 1a)
For at push_middle ikke skal nødvendiggjøre flytting og pådra seg O(n) i kompleksitet, må dette også skje uten å flytte på resten av verdiene i strukturen. Et alternativ er å dele strukturen i to objekter, ett som holder verdiene foran midten og ett som holder verdiene bak midten.

To ringbuffere L (eft) og R (igth) utgjør denne overordnede strukturen. Som for Deque forholder den overordnede strukturen seg kun til logiske indekser og den må implementere funksjonalitet som ivaretar oppgavens definisjon av midtpunkt.

Ringbufferne har push/pop både front/back og kan i tillegg returne størrelse.

### Balansering
Det er implementert en Balance-metode som sikrer invarianten $|L| - |R| \in \{0, 1\}$.

push_middle er implementert slik at $L$ får det nye elementet når $|L| = |R|$ og ivaretar invarianten.

Ved innsetting av ett element kan invarianten maksimalt være brutt med ett element. Følgelig trenger Balance maksimalt å flytte ett element mellom L og R og har kjøretidskompleksitet $O(1)$ amortisert siden den kan utløse en resize.

### Vurdering av tidskompleksitet for Teque (oppgave 1b)


|Struktur|get|push_front|push_middle|push_back|
|-|-|-|-|-|
|Lenket liste|O(n)|O(1)|O(1)**|O(1)**|
|Dynamisk Array|O(1)|O(n)|O(n)|O(1)*|
|Ringbuffer|O(1)|O(1)*|O(n)|O(1)*|
|Dobbel ringbuffer|O(1)|O(1)*|O(1)*|O(1)*|

*amortisert
**ved ivaretakelse av pekere til hhv. midt og tail.