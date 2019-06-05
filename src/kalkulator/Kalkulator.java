package kalkulator;

public class Kalkulator {

    public static void main(String[] args) {
        //mała zmiana

        // Double d=2.5 + Double.parseDouble(args[1]);
        // System.out.println(d);
        //
        // tablica stringów
        //
        //
        // String temp ="adklajd";
        // System.out.println(temp.charAt(0));
        // System.out.println(temp.length());
        // String str[] = new String[10];
        // int szczytStosu = 0;
        //
        // funkcja endsWitch do sprawdzania co jest na końcu stringa, jeśli brakuje =
        // wyświetl komunikat o błędzie
        // liczby na switchu a operatory na switchu
        //
        //dodaj liczby ujemne
        int o=0;
        if(o == 0)
            System.out.println("0 jest równe 0");
        Stos opStos = new Stos();
        Stos wyStos = new Stos();
        int i = 0;// numer wiersza z argumentami
        int j = 0;
        boolean debug = true;
        while (i < args.length) {
            if (!args[i].endsWith("=")) {
                System.out.println("błędne dane: " + args[i]);
                i++;
                continue;
            }
            System.out.print(args[i]);

            while (args[i].charAt(j) != '=') {
                switch (args[i].charAt(j)) {
                    case '(':
                        if (debug)
                            System.out.println("nawias otwierający - wrzucam");
                        opStos.wrzuc(args[i].charAt(j));
                        break;
                    case ')':
                        if (debug)
                            System.out.println("zdejmuję ze stosu wszystkie operatory aż do nawiasu otwierającego");
                        if (debug)
                            System.out.println("wysokość stosuOperatorów: " + opStos.wysokosc());
                        while (!opStos.podejrzyj().equals("(")) {
                            wyStos.wrzuc(opStos.zdejmij());
                        }
                        opStos.zdejmij();
                        break;
                    case '+':
                    case '-':
                        switch (opStos.podejrzyj()) {
                            case "(":
                                if (debug)
                                    System.out.println("na stosie operator o niższym priorytecie - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.wrzuc(opStos.zdejmij());
                                }
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                        }
                        break;
                    case '*':
                    case '/':
                    case '%':
                        switch (opStos.podejrzyj()) {
                            case "(":
                            case "+":
                            case "-":
                                if (debug)
                                    System.out.println("na stosie operator o niższym priorytecie - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.wrzuc(opStos.zdejmij());
                                }
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                        }
                        break;
                    case '^':
                    case '&':
                        switch (opStos.podejrzyj()) {
                            case "(":
                            case "+":
                            case "-":
                            case "*":
                            case "/":
                            case "%":
                                if (debug)
                                    System.out.println("na stosie operator o niższym priorytecie - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.wrzuc(opStos.zdejmij());
                                }
                                opStos.wrzuc(args[i].charAt(j));
                                break;
                        }
                        break;
                    case '.':
                        if (debug)
                            System.out.println("kropka - rozszerzam");
                        wyStos.rozszerz('.');
                        break;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '0':
                        if (j > 0 && (Character.isDigit(args[i].charAt(j - 1)) || args[i].charAt(j - 1) == '.')) {
                            wyStos.rozszerz(args[i].charAt(j));
                            if (debug)
                                System.out
                                        .println("jestem pojedynczą cyfrą, poprzednio była kropka lub liczba - rozszerzam");
                            if (debug)
                                System.out.println("teraz na górze stosu jest: " + wyStos.podejrzyj());

                        } else {
                            wyStos.wrzuc(args[i].charAt(j));
                            if (debug)
                                System.out.println("jestem pojedynczą cyfrą - wrzucam na stos wyjściowy");
                        }
                        break;
                    default:
                        System.out.println("nieprawidłowe dane");
                        break;
                }
                j++;
            }
            // = więc wszystko ze stosu operatorów na stosWyjściowy
            while (opStos.wysokosc() != 0) {
                wyStos.wrzuc(opStos.zdejmij());
            }
            if (debug)
                System.out.println("stan stosuWyjściowego:");
            if (debug)
                wyStos.wyswietl();

            wyStos.odwroc();

            if (debug)
                System.out.println("stan stosuWyjściowego:");

            if (debug)
                wyStos.wyswietl();
            if (debug)
                System.out.println("wysokość stosuWyjściowego: " + wyStos.wysokosc());

            Stos oblStos = new Stos();
            while (wyStos.wysokosc() > 0) {
                switch (wyStos.podejrzyj()) {
                    case "+":
                        wyStos.zdejmij();
                        Double operand2 = Double.parseDouble(oblStos.zdejmij());
                        Double operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("dodaję pobrane operandy");
                        oblStos.wrzuc(Double.toString(operand1 + operand2));
                        break;
                    case "-":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("odejmuję pobrane operandy");
                        oblStos.wrzuc(Double.toString(operand1 - operand2));
                        break;
                    case "*":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("mnożę pobrane operandy");
                        oblStos.wrzuc(Double.toString(operand1 * operand2));
                        break;
                    case "/":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("dzięlę pobrane operandy");
                        oblStos.wrzuc(Double.toString(operand1 / operand2));
                        break;
                    case "^":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("podnosze do potęgi pobrane operandy");
                        oblStos.wrzuc(Double.toString(Math.pow(operand1, operand2)));
                        break;
                    case "&":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("pierwiastkuję pobrane operandy");
                        oblStos.wrzuc(Double.toString(Math.pow(operand1, 1 / operand2)));
                        break;
                    case "%":
                        wyStos.zdejmij();
                        operand2 = Double.parseDouble(oblStos.zdejmij());
                        operand1 = Double.parseDouble(oblStos.zdejmij());
                        if (debug)
                            System.out.println("modulo pobrane operandy");
                        oblStos.wrzuc(Double.toString(operand1 % operand2));
                        break;
                    default:
                        if (debug)
                            System.out.println("wrzucam liczbę na stos obliczeniowy");
                        oblStos.wrzuc(wyStos.zdejmij());
                        if (debug)
                            wyStos.wyswietl();
                        if (debug)
                            System.out.println("wysokość stosuWyjściowego: " + wyStos.wysokosc());
                        break;
                }
                if (debug)
                    System.out.println("");
                if (debug)
                    System.out.println("stan stosuObliczeniowego:");
                if (debug)
                    oblStos.wyswietl();
                if (debug)
                    System.out.println("");
            }
            System.out.println(oblStos.podejrzyj());
            i++;
            j = 0;
        }

    }

}

class Stos {
    int pierwszyWolny = 0;
    String stos[] = new String[100];

    public void wrzuc(char naStos) {
        stos[pierwszyWolny++] = "" + naStos;
    }

    public void wrzuc(String naStos) {
        stos[pierwszyWolny++] = naStos;
    }

    public String zdejmij() {
        return stos[--pierwszyWolny];
    }

    public String podejrzyj() {
        if (pierwszyWolny > 0)
            return stos[pierwszyWolny - 1];
        else
            return "x";
    }

    public void rozszerz(char poszerzajacy) {
        stos[pierwszyWolny - 1] += poszerzajacy;
    }

    public int wysokosc() {
        return pierwszyWolny;
    }

    public void odwroc() {
        Stos temp = new Stos();
        Stos temp2 = new Stos();
        while (this.wysokosc() > 0) {
            temp.wrzuc(this.zdejmij());
        }
        while (temp.wysokosc() > 0) {
            temp2.wrzuc(temp.zdejmij());
        }
        while (temp2.wysokosc() > 0) {
            this.wrzuc(temp2.zdejmij());
        }
    }

    public void wyswietl() {
        for (int i = pierwszyWolny - 1; i >= 0; i--)
            System.out.println(stos[i]);
    }
}
