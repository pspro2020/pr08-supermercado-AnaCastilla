import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client {

    private final String clientNumber;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Random random = new Random();

    public Client(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void arrive() {
        System.out.printf("%s -> %s: %s arrives at the supermarket\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), clientNumber);
    }

    public void buy() throws InterruptedException {
        System.out.printf("%s -> %s: %s is buying\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), clientNumber);
        TimeUnit.SECONDS.sleep(random.nextInt(4));
        System.out.printf("%s -> %s: %s finished buying\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), clientNumber);
    }
}
