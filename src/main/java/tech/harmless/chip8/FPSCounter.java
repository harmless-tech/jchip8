package tech.harmless.chip8;

public class FPSCounter extends Thread {
    public boolean active = true;

    private double fps;
    private double counter;

    public void run() {
        long lastTime = System.nanoTime();
        while (active) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            fps = counter / ((System.nanoTime() - lastTime) / 1000000000.0);
            counter = 0;
            lastTime = System.nanoTime();
        }
    }

    public void count() {
        counter++;
    }

    public double fps() {
        return fps;
    }
}
