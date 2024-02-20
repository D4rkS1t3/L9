class Bufor {
    private int zawartosc;
    private boolean dostepny = false;

    public synchronized void put(int wartosc) {
        while (dostepny) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        zawartosc = wartosc;
        dostepny = true;
        notifyAll();
    }

    public synchronized int get() {
        while (!dostepny) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        dostepny = false;
        notifyAll();
        return zawartosc;
    }
}

class Producent implements Runnable {
    private Bufor bufor;

    public Producent(Bufor b) {
        bufor = b;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            bufor.put(i);
            System.out.println("Producent wyprodukował: " + i);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {}
        }
    }
}

class Konsument implements Runnable {
    private Bufor bufor;

    public Konsument(Bufor b) {
        bufor = b;
    }

    public void run() {
        int wartosc = 0;
        for (int i = 0; i < 10; i++) {
            wartosc = bufor.get();
            System.out.println("Konsument skonsumował: " + wartosc);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {}
        }
    }
}

public class Problem {
    public static void main(String[] args) {
        Bufor bufor = new Bufor();
        Producent p = new Producent(bufor);
        Konsument k = new Konsument(bufor);
        new Thread(p).start();
        new Thread(k).start();
    }
}
