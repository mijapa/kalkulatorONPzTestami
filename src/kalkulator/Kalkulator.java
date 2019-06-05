package kalkulator;

public class Kalkulator {

    public static void main(String[] args) {
        Kalkulator kalkulator = new Kalkulator();
        System.out.println(kalkulator.oblicz(args));

    }

    public String oblicz(String[] args) {
        if (args == null) throw new IllegalArgumentException();
        Stos opStos = new Stos();
        Stos wyStos = new Stos();
        int i = 0;// numer wiersza z argumentami
        int j = 0;
        boolean debug = false;
        while (i < args.length) {
            if (!args[i].endsWith("=")) {
                System.out.println("błędne dane: " + args[i]);
                throw new IllegalArgumentException();
//                i++;
//                continue;
            }
            System.out.print(args[i]);

            while (args[i].charAt(j) != '=') {
                switch (args[i].charAt(j)) {
                    case '(':
                        if (debug)
                            System.out.println("nawias otwierający - wrzucam");
                        opStos.push(args[i].charAt(j));
                        break;
                    case ')':
                        if (debug)
                            System.out.println("zdejmuję ze stosu wszystkie operatory aż do nawiasu otwierającego");
                        if (debug)
                            System.out.println("wysokość stosuOperatorów: " + opStos.wysokosc());
                        while (!opStos.podejrzyj().equals("(")) {
                            wyStos.push(opStos.pop());
                        }
                        opStos.pop();
                        break;
                    case '+':
                    case '-':
                        switch (opStos.podejrzyj()) {
                            case "(":
                                if (debug)
                                    System.out.println("na stosie operator o niższym priorytecie - wrzucam");
                                opStos.push(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.push(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.push(opStos.pop());
                                }
                                opStos.push(args[i].charAt(j));
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
                                opStos.push(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.push(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.push(opStos.pop());
                                }
                                opStos.push(args[i].charAt(j));
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
                                opStos.push(args[i].charAt(j));
                                break;
                            case "x":
                                if (debug)
                                    System.out.println("stos operatorów pusty - wrzucam");
                                opStos.push(args[i].charAt(j));
                                break;
                            default:
                                if (debug)
                                    System.out.println("zdejmuję ze stosu wszystkie operatory");
                                while (opStos.wysokosc() != 0) {
                                    wyStos.push(opStos.pop());
                                }
                                opStos.push(args[i].charAt(j));
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
                            wyStos.push(args[i].charAt(j));
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
                wyStos.push(opStos.pop());
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
                        wyStos.pop();
                        Double operand2 = Double.parseDouble(oblStos.pop());
                        Double operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("dodaję pobrane operandy");
                        oblStos.push(Double.toString(operand1 + operand2));
                        break;
                    case "-":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("odejmuję pobrane operandy");
                        oblStos.push(Double.toString(operand1 - operand2));
                        break;
                    case "*":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("mnożę pobrane operandy");
                        oblStos.push(Double.toString(operand1 * operand2));
                        break;
                    case "/":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("dzięlę pobrane operandy");
                        oblStos.push(Double.toString(operand1 / operand2));
                        break;
                    case "^":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("podnosze do potęgi pobrane operandy");
                        oblStos.push(Double.toString(Math.pow(operand1, operand2)));
                        break;
                    case "&":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("pierwiastkuję pobrane operandy");
                        oblStos.push(Double.toString(Math.pow(operand1, 1 / operand2)));
                        break;
                    case "%":
                        wyStos.pop();
                        operand2 = Double.parseDouble(oblStos.pop());
                        operand1 = Double.parseDouble(oblStos.pop());
                        if (debug)
                            System.out.println("modulo pobrane operandy");
                        oblStos.push(Double.toString(operand1 % operand2));
                        break;
                    default:
                        if (debug)
                            System.out.println("wrzucam liczbę na stos obliczeniowy");
                        oblStos.push(wyStos.pop());
                        if (debug)
                            wyStos.wyswietl();
                        if (debug)
                            System.out.println("wysokość stosuWyjściowego: " + wyStos.wysokosc());
                        break;
                }
                if (debug)
                    System.out.println();
                if (debug)
                    System.out.println("stan stosuObliczeniowego:");
                if (debug)
                    oblStos.wyswietl();
                if (debug)
                    System.out.println();
            }
            System.out.println(oblStos.podejrzyj());
            return (oblStos.podejrzyj());
//            i++;
//            j = 0;
        }

        return null;
    }

}

