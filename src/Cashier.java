import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cashier {

    private final int cashierNumber;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Random random = new Random();

    public Cashier(int cashierNumber) {
            this.cashierNumber = cashierNumber;
    }

    public void passByCashier(String client) throws InterruptedException {
        System.out.printf("%s -> %s: %s being attended in cashier %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), client, cashierNumber+1);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.printf("%s -> %s: Cashier %d finished with %s and leaves\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), cashierNumber+1, client);
    }

}

