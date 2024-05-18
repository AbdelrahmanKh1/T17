package demo_of_speed_interaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class speed_scrolling extends JPanel implements MouseMotionListener {
    private BufferedImage image;
    private int imageWidth, imageHeight;
    private int offsetX = 0, offsetY = 0; // Offset to center the image while scrolling
    private double zoomLevel = 1.0; // Initial zoom level
    private List<Point> mousePoints = new ArrayList<>();
    private List<Long> timePoints = new ArrayList<>();
    private List<Double> speedCategories = new ArrayList<>();
    private static final int FRAME_SIZE = 50; // Number of points to consider for speed calculation
    private boolean isZoomMode = false; // Flag to check if in zoom mode
    private boolean isScrollMode = true; // Flag to check if in scroll mode
    private boolean isSpeedSelectionMode = false; // Flag to check if in speed selection mode

    public speed_scrolling(String imagePath, String csvPath) {
        try {
            // Load the image
            image = ImageIO.read(new File(imagePath));
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();

            // Load speed data from CSV and calculate speed categories
            loadSpeedCategories(csvPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        addMouseMotionListener(this);
    }

    private void loadSpeedCategories(String csvPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            // Skip header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 14 && values[14].matches("\\d+(\\.\\d+)?")) {  // Check if the speed value is numeric
                    speedCategories.add(Double.parseDouble(values[14]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculateSpeed() {
        if (mousePoints.size() < 2) {
            return 0;
        }

        Point lastPoint = mousePoints.get(mousePoints.size() - 1);
        Point firstPoint = mousePoints.get(0);
        long lastTime = timePoints.get(timePoints.size() - 1);
        long firstTime = timePoints.get(0);

        double distance = firstPoint.distance(lastPoint);
        double time = (lastTime - firstTime) / 1000.0; // Convert milliseconds to seconds

        return distance / time;
    }

    private int determineSpeedCategory(double speed) {
        // Use fixed speed categories: 0, 1, 3, and 4
        if (speed < 100) {
            return 0; // Very low speed
        } else if (speed < 300) {
            return 1; // Low speed
        } else if (speed < 700) {
            return 3; // Medium speed
        } else {
            return 4; // High speed
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int displayWidth = (int) (imageWidth * zoomLevel);
            int displayHeight = (int) (imageHeight * zoomLevel);
            int x0 = Math.max(0, offsetX);
            int y0 = Math.max(0, offsetY);
            int x1 = Math.min(displayWidth, offsetX + getWidth());
            int y1 = Math.min(displayHeight, offsetY + getHeight());
            g.drawImage(image, 0, 0, getWidth(), getHeight(), x0, y0, x1, y1, this);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        long currentTime = System.currentTimeMillis();

        // Store the current point and timestamp
        mousePoints.add(new Point(e.getX(), e.getY()));
        timePoints.add(currentTime);

        // Remove old points if we exceed the frame size
        if (mousePoints.size() > FRAME_SIZE) {
            mousePoints.remove(0);
            timePoints.remove(0);
        }

        // Calculate speed
        double speed = calculateSpeed();
        int speedCategory = determineSpeedCategory(speed);

        if (isSpeedSelectionMode) {
            handleSpeedSelection(speedCategory);
        } else {
            if (isZoomMode) {
                applyZoom(speedCategory);
            } else if (isScrollMode) {
                applyScroll(speedCategory, e);
            }
        }

        repaint();

        System.out.println("Mouse event: speed = " + speed + ", speed category = " + speedCategory + ", zoom level = " + zoomLevel);
    }

    private void applyZoom(int speedCategory) {
        switch (speedCategory) {
            case 4:
                zoomLevel = 4.0;
                break;
            case 3:
                zoomLevel = 3.0;
                break;
            case 1:
                zoomLevel = 1.0;
                break;
            case 0:
                zoomLevel = 0.5;
                break;
        }

        zoomLevel = Math.max(0.5, Math.min(5.0, zoomLevel)); // Limit zoom level between 0.5x and 5x

        // Center on mouse position
        if (!mousePoints.isEmpty()) {
            Point lastPoint = mousePoints.get(mousePoints.size() - 1);
            int mouseX = lastPoint.x;
            int mouseY = lastPoint.y;
            offsetX = (int) (mouseX * (zoomLevel - 1) - getWidth() / 2 * (zoomLevel - 1));
            offsetY = (int) (mouseY * (zoomLevel - 1) - getHeight() / 2 * (zoomLevel - 1));
        }
    }

    private void applyScroll(int speedCategory, MouseEvent e) {
        // Update the offset position for image scrolling to center the image while moving the cursor
        int dx = (int) ((e.getX() - getWidth() / 2) * speedCategory);
        int dy = (int) ((e.getY() - getHeight() / 2) * speedCategory);

        offsetX += dx;
        offsetY += dy;

        offsetX = Math.max(0, Math.min(offsetX, (int) (imageWidth * zoomLevel) - getWidth()));
        offsetY = Math.max(0, Math.min(offsetY, (int) (imageHeight * zoomLevel) - getHeight()));
    }

    private void handleSpeedSelection(int speedCategory) {
        if (speedCategory == 4) {
            System.out.println("High speed detected");
        } else if (speedCategory == 3) {
            System.out.println("Medium speed detected");
        } else if (speedCategory == 1) {
            System.out.println("Low speed detected");
        } else {
            System.out.println("Very low speed detected");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Speed-Based Scroller");

        String imagePath = "D:\\679924.jpg";  // Replace with your image path
        String csvPath = "F:\\ispeed-Project-master\\Datasets\\data1.csv";  // Replace with your CSV path

        speed_scrolling scroller = new speed_scrolling(imagePath, csvPath);
        frame.setLayout(new BorderLayout());
        frame.add(scroller, BorderLayout.CENTER);

        // Add buttons for zoom, scroll, and speed selection modes
        JPanel buttonPanel = new JPanel();
        JButton zoomButton = new JButton("Zoom Mode");
        JButton scrollButton = new JButton("Scroll Mode");
        JButton speedSelectButton = new JButton("Speed Select Mode");

        zoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scroller.setZoomMode(true);
                scroller.setScrollMode(false);
                scroller.setSpeedSelectionMode(false);
            }
        });

        scrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scroller.setZoomMode(false);
                scroller.setScrollMode(true);
                scroller.setSpeedSelectionMode(false);
            }
        });

        speedSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scroller.setZoomMode(false);
                scroller.setScrollMode(false);
                scroller.setSpeedSelectionMode(true);
            }
        });

        buttonPanel.add(zoomButton);
        buttonPanel.add(scrollButton);
        buttonPanel.add(speedSelectButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setZoomMode(boolean isZoomMode) {
        this.isZoomMode = isZoomMode;
    }

    public void setScrollMode(boolean isScrollMode) {
        this.isScrollMode = isScrollMode;
    }

    public void setSpeedSelectionMode(boolean isSpeedSelectionMode) {
        this.isSpeedSelectionMode = isSpeedSelectionMode;
    }
}
