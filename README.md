The Drake game using **JavaFX** as my school project at FIT CTU. We worked on implementing the game logic throughout the semester, aiming to pass the tests using **JUnit**. The final task involved creating a graphical interface for the game. The game is designed for two human players competing against each other on a single computer. Below, you can find a description of the game in the Czech language.

# The Drake - pravidla hry
The Drake je desková hra pro dva hráče inspirovaná úspěšnou hrou The Duke. V podstatě jde o zjednodušenou verzi The Duke, která se hraje na menším hracím plánu s menším počtem figur a některá její pravidla jsou přizpůsobena tomu, aby se hra dobře programovala. The Drake má několik možných verzí podle různých úprav a rozšíření, o které je možné obohatit takzvanou základní verzi.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/2232e0d7-55ce-4c9b-a9d2-3cf47a365d4e)

Základní verze hry
Základní verze hry The Drake se hraje na čtvercové desce rozdělené na 4×4 polí.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/92fdb2b7-2663-4360-9f92-b5f97d37d808)

Hra začíná s prázdnou hrací deskou, případně deska může obsahovat dlaždice, na které se nesmí vstoupit, zde hory (Mountain). Každý hráč má od začátku hry k dispozici takzvaný zásobník, který obsahuje předem stanovenou sadu hracích kamenů zvaných jednotky. Jednotka, která se na začátku hry nachází na vrcholu zásobníku, se označuje jako vůdce. Hráči, označovaní jako „modrý“ a „oranžový“ podle barvy svých jednotek, střídavě provádějí tahy, které mohou vyústit v zajmutí některé ze soupeřových jednotek na hrací desce. Hru vyhrává ten hráč, kterému se jako prvnímu podaří zajmout soupeřova vůdce.

# Jednotky
Základní verze hry obsahuje celkem šest druhů jednotek: Drake, Clubman, Monk, Spearman, Swordsman a Archer. Jednotky jsou dvou barev, modré nebo oranžové a každá má lícovou a rubovou stran. Každý hráč má po celou dobu hry pevně přidělenou barvu jednotek, se kterými hraje.

Každý hráč má na začátku hry zásobník sedmi jednotek v přesně tomto pořadí:

- Drake - velitel, jeho zajmutí ukončuje hru
- Clubman - pobočník 
- Clubman - pobočník
- Monk - mnich
- Spearman - muž s kopím, oštěpař
- Swordsman - šermíř
- Archer - lučištník

# Fáze hry
Hra The Drake sestává ze tří fází: zahájení, stavění stráží a střední hra.

## Zahájení
Na počátku hry je hra ve fází zahájení. Během zahájení hráči rozmísťují svoje vůdce. Vůdce je jednotka, která se na začátku hry nachází na vrcholu zásobníku, což je v základní verzi hry vždy jednotka Drake. Hru vždy začíná modrý hráč, který může položit svého vůdce na kterékoliv pole na řadě 1 hrací desky. Následuje oranžový hráč, který může položit svého vůdce na kterékoliv pole řady 4. Tímto hra přechází do fáze stavění stráží.

## Stavění stráží
Jednotky, které se na začátku hry nacházejí na druhé a třetí pozici v zásobníku, se nazývají stráže. V základní verzi hry jsou stráže vždy dvě jednotky Clubman. Na začátku stavění stráží je na tahu modrý hráč. Ten může položit první stráž z vrcholu zásobníku na kterékoliv prázdné pole, které se jednou hranou sousedí s jeho vůdcem. Podle stejných pravidel pak staví svoji první stráž oranžový hráč. Poté modrý hráč staví svoji druhou stráž, která se opět na volné pole, které se jednou hranou dotýká pole s vůdcem. Analogicky postupuje oranžový hráč s postavením svojí druhé stráže. Tímto hra přechází do fáze střední hry.

## Střední hra
Ve střední hře se modrý a oranžový hráč střídají v provádění tahů, jejichž podstata je vyložena v následujícím textu. Střední hra končí ve chvíli, kdy některý z hráčů vyhraje tím, že zajme soupeřova vůdce. Střední hra také může skončit remízou.

Tah ve střední hře
Tahem se rozumí buď:

1. Položení jednotky z vrcholu zásobníku na některé volné pole hrací desky. Hráč může položit jednotku pouze na volné pole, které alespoň jednou hranou sousedí s jednotkou jeho barvy. Jednotka se vždy na desku pokládá lícovou stranou nahoru.
2. Provedení takzvané akce jednotkou, která již leží na nějakém hracím poli. Každá jednotka má na svém líci i rubu vyobrazeny symboly představující akce. Ty určují, jakým způsobem lze jednotkou na hrací desce táhnout. Po provedení jakékoliv akce se jednotka vždy otočí na opačnou stranu, tedy z rubu na líc nebo z líce na rub.

# Akce
Akce na rubové a lícové straně jednotky udávají, jakým způsobem může jednotka táhnout po hrací desce. Každá jednotka má takzvaný pivot, který je označen symbolem připomínajícím i. Pivot představuje políčko, na kterém zrovna jednotka stojí a je tak referenčním bodem pro všechny akce jednotky.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/8062ee06-722b-48cd-8f59-4454caa4adf6)

## Akce krok (shift action) 

Na pole označené akcí krok může jednotka vstoupit nebo zde zajmout soupeřovu jednotku, pokud není přímá cesta na toto pole zablokována jiným polem, na které nelze vstoupit. Krok je o jedno pole a je označeno kolečkem.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/56066330-794e-4c08-8251-e2ee2a8b2041)

## Akce posun (slide action)  
Jednotka může vstoupit nebo zajmout soupeřovu jednotku na všechna pole ve směru šipky akce v případě, že přímá cesta k tomuto poli není zablokována jiným polem, na které nelze vstoupit.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/ba3b8e24-0dff-4360-90a0-9b4e0269f0c3)

![image](https://github.com/zhadavla/JavaGame/assets/113200176/cba3f2d6-27a1-47b8-86c5-95487370f250)

Na obrázku je vybrána modrá karta mnicha (Monk). Je vidět, že se může pohybovat po diagonálách (symbol trojúhelníka), může se posunout o jednu pozici vlevo nahoru, dále o dvě pozice ve stejném směru, kde by zajal oranžového pobočníka (Clubman) a protože tento pobočník blokuje další posun, nemůže ohrozit velitele(Drake).

## Akce úder (strike action)  
Jednotka může zajmout soupeřovu jednotku, pokud soupeřova jednotka stojí na poli označeném touto akcí. Úder je možné provést bez ohledu na to, zda je přímá cesta k cílovému poli volná či nikoliv. Jednotka po úderu zůstává na svém původním poli. Na dalším obrázku je vidět, že oštěpař (Spearman - muž ozbrojený kopím) může ze své pozice udělat jeden krok vpřed a porazit velitele (kolečko nahoře od i) a dále může bez změny pozice zajmout mnicha. Po vržení kopí se karta (Spearman) otáčí.

![image](https://github.com/zhadavla/JavaGame/assets/113200176/77010096-6e56-46cb-b4cb-2dabef2e9e09)

![image](https://github.com/zhadavla/JavaGame/assets/113200176/cc075191-550c-4846-a7de-e8d164a15b72)





