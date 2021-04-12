package Java3.Lesson4;

class PrintCorrect implements Runnable {
    final static Object lock = new Object();
    static char letter = 65;
    char character = 65;

    public PrintCorrect() {}

    public PrintCorrect(int i) {
        this.character += i;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            synchronized (lock) {
                try {
                    while (letter != this.character) {
                        lock.wait();
                    }
                    System.out.print(this.character);
                    if (letter == 68) {
                        letter = 65;
                    } else {
                        letter++;
                    }
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}