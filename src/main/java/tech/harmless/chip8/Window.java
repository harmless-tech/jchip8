package tech.harmless.chip8;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.harmless.chip8.util.Tuple;

public class Window extends JPanel implements Runnable {
    @NotNull private final JFrame frame;
    @NotNull private final Thread renderThread;

    @Nullable private final FPSCounter fpsCounter;

    private final int width;
    private final int height;

    private BufferedImage back;

    public Window(final int width, final int height, final boolean trackFPS) {
        super(new BorderLayout());
        frame = createFrame();

        this.width = width;
        this.height = height;
        back = (BufferedImage) (createImage(width, height));

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
        if (fpsCounter != null) fpsCounter.count();

        Graphics2D twoDGraph = (Graphics2D) window;

        if (back == null || back.getWidth() != width || back.getHeight() != height)
            back = (BufferedImage) (createImage(width, height));

        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.RED);
        graphToBack.fillRect(0, 0, width, height);

        //        graphToBack.setColor(Color.WHITE);
        //        FontMetrics fm = graphToBack.getFontMetrics();
        //        graphToBack.drawString("Engine Default", WindowControl.WINDOW_WIDTH / 2 -
        // fm.stringWidth("Engine Default") / 2, WindowControl.WINDOW_HEIGHT / 2 - fm.getHeight() /
        // 2);

        // Code Here
        //        for(Renderable r : renderObjects) {
        //            r.render(graphToBack);
        //        }
        // End Code Here

        //        if(WindowControl.DEBUG) {
        //            graphToBack.setColor(Color.YELLOW);
        //            graphToBack.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        //
        //            graphToBack.drawString("FPS: " + Math.round(MainRenderLoop.fpsCounter.fps()),
        // 5, 15);
        //            graphToBack.drawString("Updates: " +
        // Math.round(MainUpdateLoop.fpsCounter.fps()), 5, 30);
        //        }

        var d = keepRatio();
        twoDGraph.drawImage(back, 0, 0, d.x(), d.y(), this);

        if (fpsCounter != null) {
            twoDGraph.setColor(Color.YELLOW);
            // twoDGraph.setFont(new Font("ArchivoNarrow", Font.PLAIN, 12)); // TODO: Change font
            // and size?
            twoDGraph.drawString("FPS: " + Math.round(fpsCounter.fps()), 5, 15);
        }
    }

    // TODO: Cache and regen on window size change.
    private Tuple<Integer, Integer> keepRatio() {
        int wi = width;
        int hi = height;
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
        if (fpsCounter != null) fpsCounter.start();

        // Loop
        final long delta = 16000000;
        long lastTime = 0;

        while (true) { // TODO: This needs to be slowed down?
            while (System.nanoTime() - lastTime < delta) {
                try {
                    Thread.sleep(0, 1000);
                } catch (InterruptedException ignored) {
                }
            }

            repaint();
        }
    }
}
