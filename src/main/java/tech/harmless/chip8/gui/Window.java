package tech.harmless.chip8.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import javax.swing.*;
import org.jetbrains.annotations.Nullable;
import tech.harmless.chip8.Chip8;
import tech.harmless.chip8.util.Calculate;
import tech.harmless.chip8.util.Tuple;

public class Window extends JPanel implements Runnable {
    private final JFrame frame;
    private final Thread renderThread;

    private final Chip8 chip;

    @Nullable private final FPSCounter fpsCounter;
    private BufferedImage back;

    public Window(final Chip8 chip, final boolean trackFPS) {
        frame = createFrame();

        this.chip = chip;

        fpsCounter = trackFPS ? new FPSCounter() : null;
        renderThread = Thread.startVirtualThread(this);

        setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        var frame = new JFrame();

        frame.setTitle("jchip8 - ALPHA");
        frame.setSize(1280, (1280 + 64) / 2);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(this);

        return frame;
    }

    @Override
    public void paint(Graphics window) {
        super.paint(window);
        if (fpsCounter != null) fpsCounter.count();

        final int width = chip.display.width;
        final int height = chip.display.height;

        Graphics2D twoDGraph = (Graphics2D) window;
        twoDGraph.setColor(Color.RED);
        twoDGraph.fillRect(0, 0, width, height);

        if (back == null || back.getWidth() != width || back.getHeight() != height)
            back = (BufferedImage) (createImage(chip.display.width, chip.display.height));

        final int[] buffer = chip.display.buffer;
        back.setData(
                Raster.createRaster(
                        back.getSampleModel(),
                        new DataBufferInt(buffer, buffer.length),
                        new Point()));

        var d = keepRatio(width, height);
        twoDGraph.drawImage(back, 0, 0, d.x(), d.y(), this);

        if (fpsCounter != null) {
            twoDGraph.setColor(Color.YELLOW);
            // TODO: Change font and size?
            // twoDGraph.setFont(new Font("ArchivoNarrow", Font.PLAIN, 12));
            twoDGraph.drawString("FPS: " + Math.round(fpsCounter.fps()), 5, 15);
        }

        twoDGraph.dispose();
    }

    // TODO: Cache and regen on window size change.
    private Tuple<Integer, Integer> keepRatio(final int wi, final int hi) {
        float ri = (float) wi / hi;

        var d = getSize();
        int ws = d.width;
        int hs = d.height;
        float rs = (float) ws / hs;

        return rs > ri ? new Tuple<>(wi * (hs / hi), hs) : new Tuple<>(ws, hi * (ws / wi));
    }

    @Override
    public void run() {
        // Init
        if (fpsCounter != null) {
            fpsCounter.setPriority(Thread.MIN_PRIORITY);
            fpsCounter.start();
        }

        // Loop
        final long delta = Calculate.ipsTiming(60); // 60 fps
        long lastTime = 0;

        while (true) { // TODO: This needs to be slowed down?
            while (System.nanoTime() - lastTime < delta) {
                try {
                    Thread.sleep(0, 1000);
                } catch (InterruptedException ignored) {
                }
            }
            lastTime = System.nanoTime();

            repaint();
        }
    }
}
