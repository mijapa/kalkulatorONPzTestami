package main.kalkulator;

public class Stos {
    int pierwszyWolny = 0;
    String[] stos = new String[100];

    public void push(char naStos) {
        stos[pierwszyWolny++] = "" + naStos;
    }

    public void push(String naStos) {
        stos[pierwszyWolny++] = naStos;
    }

    public String pop() {
        if (pierwszyWolny > 0)
            return stos[--pierwszyWolny];
        else throw new IndexOutOfBoundsException();
    }

    public String top() {
        if (pierwszyWolny > 0)
            return stos[pierwszyWolny - 1];
        else throw new IndexOutOfBoundsException();
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
            temp.push(this.pop());
        }
        while (temp.wysokosc() > 0) {
            temp2.push(temp.pop());
        }
        while (temp2.wysokosc() > 0) {
            this.push(temp2.pop());
        }
    }

    public void wyswietl() {
        for (int i = pierwszyWolny - 1; i >= 0; i--)
            System.out.println(stos[i]);
    }

    public boolean isEmpty() {
        return pierwszyWolny == 0;
    }
}
