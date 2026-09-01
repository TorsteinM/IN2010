# Oblig 1

Obligen har i hovedsak 3 implementasjonsoppgaver. Først en triple ended queue (oppgave 1) og insertion og merge sort(oppgave 2).

# 1 Teque - Triple Ended Queue

## Double ended queue
I følge oppgaveteksten er en dobbelendet kø en struktur som støtter push til både front og back på en sekvensiell struktur.

For en teque - Triple ended queue - skal det også være mulig med push til midten av køen.

Dette kan naturligvis implementeres både som lenket liste og som dynamisk array.

Utfordringen for dynamisk array blir å implementere en elegant måte å indeksere slik at man slipper å flytte alle elementene i arrayen annenhver gang vi skal legge til et element.

## Ringbuffer og logisk indeksing
Vi kan implementere en ringbuffer som et grensesnitt mellom logisk index [0, size - 1] og absolutt index [start, (start + size - 1) % kapasitet]. Remainder/mod operatoren gjør det enkelt og lesbart å la indeks wrappe rundt til begynnelsen av arrayen igjen. Altså kan vi implementere en struktur som gjøre push_front og push_back uten å måtte flytte objekter i minne. 

## Array vs. Lenket liste
Dermed får double ended queue O(1) ved indeksering og amortisert O(1) ved push_front og push_back.

Dobbelt lenket liste ville vi fått O(n) ved indeksering og O(1) ved push_front og push_back.

Arrays er også å foretrekke på moderne maskinvare for å utnytte cache i prossessoren.

## Deque implementert rundt ringbuffer

Først implementeres Deque og så overlates problemet med push_middle til senere.

Målet i Deque er at den forholder seg kun til logisk indeks, mens translasjonen til absolutt indeks tar CircularBuffer seg av.

Ellers er implementasjonen ganske rett frem.

## Teque
Målet er å implementere denne ved hjelp av to ringbuffere. Disse må da følgelig balanseres på en slik måte at implementasjonen oppfyller kravene til push_middle fra oppgaven.