public class Main {

    public static void main(String[] args) {

        SupermarketQueue supermarketQueue = new SupermarketQueue(4);
        Thread[] cashierJobThreads = new Thread[50]; //50 hilos clien
        for (int i = 0; i < 50; i++) {
            cashierJobThreads[i] = new Thread(new CashierJob(supermarketQueue, "Client " + (i+1)));
        }
        for (int i = 0; i < 50; i++) {
            cashierJobThreads[i].start();
        }
    }

}
