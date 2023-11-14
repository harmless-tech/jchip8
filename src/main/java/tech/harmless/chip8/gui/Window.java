package tech.harmless.chip8.gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.jetbrains.annotations.Nullable;
import tech.harmless.chip8.Chip8;
import tech.harmless.chip8.util.Calculate;
import tech.harmless.chip8.util.Tuple;

public class Window extends JPanel implements Runnable {
    private final JFrame frame;
    private final Thread renderThread;

    @Nullable private final FPSCounter fpsCounter;

    private final Chip8 chip;

//    private MemoryImageSource source;
    private final Image source;
    private BufferedImage back;
//    private Image scaleBack;

    public Window(final Chip8 chip, final boolean trackFPS) {
//        super(new BorderLayout());
        frame = createFrame();

        this.chip = chip;
        source = createImage(chip.display.source);
        chip.display.source.newPixels();

        fpsCounter = trackFPS ? new FPSCounter() : null;
        renderThread = Thread.startVirtualThread(this);

//        SwingUtilities.invokeLater(this);

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
//        super.paint(window);
        if (fpsCounter != null) fpsCounter.count();

        final int width = chip.display.width;
        final int height = chip.display.height;

        Graphics2D twoDGraph = (Graphics2D) window;
        twoDGraph.setColor(Color.CYAN);
        twoDGraph.fillRect(0, 0, width, height);

        if (back == null || back.getWidth() != width || back.getHeight() != height)
            back = (BufferedImage) (createImage(chip.display.width, chip.display.height));

        final byte[] buffer = chip.display.buffer;
        back.setData(Raster.createRaster(back.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));

//
//
//        System.out.println(back.getColorModel());

//        createImage(1, 1).

//        int[] iDisplay = new int[display.length];
//        for (int i = 0; i < display.length; i++)
//            iDisplay[i] = display[i];

//        back.getSampleModel().setPixels();

// TODO
//        Graphics graphToBack = back.createGraphics();


//        graphToBack.drawImage(BufferedImage.create, 0, 0, this);

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

//        if (scaleBack == null) {
//            scaleBack = back.getScaledInstance(500, 500, Image.SCALE_FAST);
////            ImageIO.write((RenderedImage) scaleBack, "png", new File("./i.tmp.png"));
//        }

// TODO
//        graphToBack.dispose();

        var d = keepRatio(width, height);
        twoDGraph.drawImage(back, 0, 0, d.x(), d.y(), this);
//        twoDGraph.drawImage(scaleBack, 0, 0, this);

        if (fpsCounter != null) {
            twoDGraph.setColor(Color.YELLOW);
            // twoDGraph.setFont(new Font("ArchivoNarrow", Font.PLAIN, 12)); // TODO: Change font
            // and size?
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
