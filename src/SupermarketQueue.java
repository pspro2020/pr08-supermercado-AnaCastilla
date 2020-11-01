import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SupermarketQueue {

    private static final int NO_CASHIER = -1;
    private final Semaphore semaphore;
    private final Lock reentrantLock = new ReentrantLock(true);
    private final Cashier[] cashiers;
    private final Client[] clients = new Client[50];
    private final boolean[] cashierAvailable;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public SupermarketQueue(int numberOfCashiers) {
        semaphore = new Semaphore(numberOfCashiers, true);
        cashiers = new Cashier[numberOfCashiers];
        cashierAvailable = new boolean[numberOfCashiers];
        for (int i = 0; i < numberOfCashiers; i++) {
            cashiers[i] = new Cashier(i);
            cashierAvailable[i] = true;
        }
    }

    public void nextClient(String client) throws InterruptedException {
        try {
            semaphore.acquire();
            Client newClient = new Client(client);
            newClient.arrive();
            newClient.buy();
            int cashierNumber = selectCashier();
            if (cashierNumber != NO_CASHIER) {
                cashiers[cashierNumber].passByCashier(client);
                cashierAvailable[cashierNumber] = true;
            } else {
                System.out.printf("%s -> %s: There aren't any free cashiers\n",
                        LocalTime.now().format(dateTimeFormatter),
                        Thread.currentThread().getName());
            }
        } finally {
            semaphore.release();
        }
    }

    private int selectCashier() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < cashiers.length; i++) {
                if (cashierAvailable[i]) {
                    cashierAvailable[i] = false;
                    return i;
                }
            }
        } finally {
            reentrantLock.unlock();
        }

        return NO_CASHIER;
    }
}
