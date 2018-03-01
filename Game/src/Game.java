import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    public static int HEIGHT = 720;
    public static int WIDTH = HEIGHT *16/9;

    private Thread thread;
    public static boolean running = false;

    public Game(){
        new GameWindow(WIDTH, HEIGHT, "Game Window", this);
    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        final double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            final long now = System.nanoTime();
            delta = delta + (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer = timer + 1000;
            }
        }
        stop();
    }


    private void tick() {
    }



    private void render() {

    }



    public static void main(final String args[]) {
        new Game();
    }
}
