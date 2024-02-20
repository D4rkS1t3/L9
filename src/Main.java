import java.util.Random;

public class Main implements Runnable{
    /*
    Napisz klasę implementującą interfejs Runnable z metodą losującą liczby całkowite zzakresu <0,100>. Wywołaj metodę w kilku wątkach.
    Czy wylosowane liczby są takie same? Czy możliwa jest modyfikacja zapewniająca stuprocentowo, że wynik będzie identyczny?
    */

    @Override
    public void run() {
        Random rd=new Random();
        int wylosowana=rd.nextInt(101);
        System.out.println("Watek:"+Thread.currentThread().getName()+" wyolosowal "+wylosowana+" liczbe");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread watek = new Thread(new Main(), "Watek"+i);
            watek.start();
        }
    }
}