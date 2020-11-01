import java.util.Objects;

public class CashierJob implements Runnable {

    private final SupermarketQueue supermarketQueue;
    private final String client;

    public CashierJob(SupermarketQueue supermarketQueue, String client) {
        Objects.requireNonNull(supermarketQueue);
        Objects.requireNonNull(client);
        this.supermarketQueue = supermarketQueue;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            supermarketQueue.nextClient(client);
        } catch (InterruptedException e) {
            System.out.printf("%s -> I've been interrupted while cashing\n",
                    Thread.currentThread().getName());
        }
    }

}

