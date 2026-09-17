# Oblig 1

Del 1: Implementasjon av trippelendet kø og tilhørende kompleksitetsvurdering.
Del 2: Implementasjon av Insertion Sort og Merge Sort

# Del 1: Triple Ended Queue

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

# 1a) Teque basert på to ringbuffere
For at push_middle ikke skal nødvendiggjøre flytting og pådra seg O(n) i kompleksitet, må dette også skje uten å flytte på resten av verdiene i strukturen. Et alternativ er å dele strukturen i to objekter, ett som holder verdiene foran midten og ett som holder verdiene bak midten.

To ringbuffere L (eft) og R (igth) utgjør denne overordnede strukturen. Som for Deque forholder den overordnede strukturen seg kun til logiske indekser og den må implementere funksjonalitet som ivaretar oppgavens definisjon av midtpunkt.

Ringbufferne har push/pop både front/back og kan i tillegg returne størrelse.

### Balansering
Det er implementert en Balance-metode som sikrer invarianten $|L| - |R| \in \{0, 1\}$.

push_middle er implementert slik at $L$ får det nye elementet når $|L| = |R|$ og ivaretar invarianten.

Ved innsetting av ett element kan invarianten maksimalt være brutt med ett element. Følgelig trenger Balance maksimalt å flytte ett element mellom L og R og har kjøretidskompleksitet $O(1)$ amortisert siden den kan utløse en resize.

# 1b) Vurdering av tidskompleksitet for Teque


|Struktur|get|push_front|push_middle|push_back|
|-|-|-|-|-|
|Lenket liste|O(n)|O(1)|O(1)**|O(1)**|
|Dynamisk Array|O(1)|O(n)|O(n)|O(1)*|
|Ringbuffer|O(1)|O(1)*|O(n)|O(1)*|
|Dobbel ringbuffer|O(1)|O(1)*|O(1)*|O(1)*|

*amortisert
**ved ivaretakelse av pekere til hhv. midt og tail.

# Del 2: Sortering

# 2a) Insertion Sort
Insertion Sort er implementert som vist under.

Bare den statiske funksjonen er vist som tar en array.

Den har en ytre (for-) løkke som går over alle N elementer.

Den har en indre (while-) løkke som plasserer gjeldende indeks langt nok til venstre og flytter alle elementer som er større ett hakk til høyre.

Både den ytre og den indre løkker må kjøre et antall operasjoner som blir vokser lineært.

Den ytre er vokser direkte med N og den indre er alltid mindre, men men vokser også med en faktor i forhold til N.

Dermed er kjøretidskompleksiteten $O(n^2)$

```Java
    static public int[] sort(int[] array) {
        // Betrakt alt til venstre for posisjonen pos som sortert
        for (int pos = 1; pos < array.length; pos++){
            // Ta vare på verdien som skal settes inn
            int posValue = array[pos];
            // Tell ned indeks
            int i = pos;
            // så lenge innenfor bounds og posValue er større
            while(--i >= 0 && posValue < array[i]) {
                //
                array[i + 1] =  array[i];
            }
            // indekset som brøt løkken er ett for langt, så legg til en
            array[i + 1] = posValue;
        }
        return array;
    }
```

# 2b) Merge Sort

Merge Sort er basert på å flette sorterte arrays. 

## Implementasjonen av flettemekanisme i Java

Flettemekanismen er kopiert i stor grad fra boken.

`from` antas å ha to påfølgende sorterte arrays. 

Disse totalt `N` elementene flettes inn i `to` 

```Java
    static void merge(int[] to, int[] from, int left, int N){
        // samler to like lange arrays med lengde N
        // |to| = 2|from| = 2N
        int i = 0;
        int j = 0;
        // algoritme fra boken skrevet om til indeksering
        while (i < N && j < N) {
            if (from[left + i] < from[left + N + j]) {
                to[left + i + j] = from[left + i];
                i++;
            } else {
                to[left + i + j] = from[left + N + j];
                j++;
            }
        }
        while (i < N) {
            to[left + i + j] = from[left + i];
            i++;
        }
        while (j < N) {
            to[left + i + j] = from[left + N + j];
            j++;
        }
```

## $\lceil \log_2 |A|\rceil$ nivåer for å sortere en array A

For å sortere en array må flettingen gjentas til hele arrayen flettes sammen til en sortert array. Det starter med at array av lengde av 1 er sortert og for hver iterasjon dobles antall elementer. 

Etter $k$ iterasjoner har man sortert $2^k$ elementer, dermed trengs $\lceil \log_2 N \rceil$ nivåer for å sortere N elementer.

Følgende kode allokerer et minne. Den første løkken er nødvendig for å flytte array inn i det første nivået. 

Et alternativ for lesbarheten her ville vært å memcpy (altså tilsvarende funksjon i java) inn i det første nivået og spart denne første løkken.

Når elementene er på plass i det allokerte minnet kan vi lage en løkke som itererer alle de nødvendige nivåene.

Dette er altså ytre løkken som vokser logaritmisk i hht. $N$, altså $O(\log_2 N)$.

Den indre løkken fletter elementene opp til neste nivå, og bruker en comparison og en kopi per element, altså $O(N)$.

Kjøretidskompleksiteten for den ytre og indre løkker er $O(N\log_2N)$, som tilsvarer kjøretidskompleksiteten for Merge Sort.

```Java
    static public int[] sort(int[] array) {
        if(array.length <= 1) {
            return array;
        }
        int len = array.length;
        int levels = 1;
        // finner levels = ceil(log_2(N))
        while (1 << levels < len) {
            levels++;
        }
        int[][] mergeArea = new int[levels][len];
        for(int i = 0; i < array.length; i+=2){
            merge(mergeArea[0], array, i, 1);
        }
        for(int level = 1; level < levels; level++) {
            int divide = 2 << level;
            for(int i = 0; i < len; i+=divide)
                merge(mergeArea[level], mergeArea[level - 1], i, divide/2);
        }
        return mergeArea[levels - 1];
    }
```
## Selection Sort
Selection sort er også implementert.

Denne har også kjøretidskompleksitet $O(N^2)$ da den har indre og ytre løkke der antall operasjoner vokser lineært med $N$.

```Java
    static public int[] sort(int[] array) {
        for (int pos = 0; pos < array.length - 1; pos++){
            int minValue = array[pos];
            int minIndex = pos;
            // Finner den minste verdien
            for(int i = pos; i < array.length; i++){
                if (array[i] < minValue) {
                    minValue = array[i];
                    minIndex = i;
                }
            }
            // Bytt med nåværende posisjon hvis mindre verdi er funnet
            if (pos != minIndex) {
                int temp = array[pos];
                array[pos] = minValue;
                array[minIndex] = temp;
            }
        }
        return array;
    }
```