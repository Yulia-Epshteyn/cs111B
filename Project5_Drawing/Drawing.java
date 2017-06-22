package Project5_Drawing;
/*
Raymond Gee, Kelly Suen, Yulia Bugrova
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Drawing extends JPanel {

    private static final int WIDTH = 700, HEIGHT = 700;

    private final int BRUSH_SIZE_STEP = 5;
    private final int MIN_BRUSH_SIZE = 1;
    private final int MAX_BRUSH_SIZE = 60;

    private int diameter = 15;

    private JButton clearButton;

    private JRadioButton greenColorButton, blueColorButton,
            redColorButton, yellowColorButton, eraseColorButton;

    private JButton increaseButton, decreaseButton;

    private Color selectedColor;

    private boolean mouseClicked = false;

    private List<ColoredPoint> coloredPointArrayList = new ArrayList<>();

    public Drawing() {
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);

        setBackground(Color.WHITE);

        selectedColor = Color.WHITE;

        clearButton = new JButton("Clear Drawing");
        controlPanel.add(clearButton);
        clearButton.addActionListener(new ButtonListener());

        greenColorButton = new JRadioButton("Green");
        controlPanel.add(greenColorButton);
        greenColorButton.setForeground(Color.WHITE);
        greenColorButton.setBackground(Color.BLACK);
        greenColorButton.addActionListener(new OptionListener());

        yellowColorButton = new JRadioButton("Yellow");
        controlPanel.add(yellowColorButton);
        yellowColorButton.setForeground(Color.WHITE);
        yellowColorButton.setBackground(Color.BLACK);
        yellowColorButton.addActionListener(new OptionListener());

        blueColorButton = new JRadioButton("Blue");
        controlPanel.add(blueColorButton);
        blueColorButton.setForeground(Color.WHITE);
        blueColorButton.setBackground(Color.BLACK);
        blueColorButton.addActionListener(new OptionListener());

        redColorButton = new JRadioButton("Red");
        controlPanel.add(redColorButton);
        redColorButton.setForeground(Color.WHITE);
        redColorButton.setBackground(Color.BLACK);
        redColorButton.addActionListener(new OptionListener());

        eraseColorButton = new JRadioButton("Eraser");
        controlPanel.add(eraseColorButton);
        eraseColorButton.setForeground(Color.WHITE);
        eraseColorButton.setBackground(Color.BLACK);
        eraseColorButton.addActionListener(new OptionListener());

        ButtonGroup group = new ButtonGroup();
        group.add(greenColorButton);
        group.add(yellowColorButton);
        group.add(blueColorButton);
        group.add(redColorButton);
        group.add(eraseColorButton);

        increaseButton = new JButton("+");
        controlPanel.add(increaseButton);
        increaseButton.addActionListener(new ChangeBrushSizeListener());

        decreaseButton = new JButton("-");
        controlPanel.add(decreaseButton);
        decreaseButton.addActionListener(new ChangeBrushSizeListener());

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.SOUTH);
        this.addMouseListener(new DrawListener());
        this.addMouseMotionListener(new DrawListener());
    }

    private class OptionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(greenColorButton.isSelected()) {
                selectedColor = Color.GREEN;
            } else if(blueColorButton.isSelected()) {
                selectedColor = Color.BLUE;
            } else if (redColorButton.isSelected()) {
                selectedColor = Color.RED;
            } else if (yellowColorButton.isSelected()) {
                selectedColor = Color.YELLOW;
            } else if (eraseColorButton.isSelected()) {
                selectedColor = Color.WHITE;
            }
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            coloredPointArrayList.clear();
            repaint();
        }
    }

    private class DrawListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            mouseClicked = !mouseClicked;
        }
        @Override
        public void mouseMoved(MouseEvent event) {
            if (mouseClicked) {
                ColoredPoint newPoint = new ColoredPoint(event.getPoint(), selectedColor, diameter);
                coloredPointArrayList.add(newPoint);
                repaint();
            }
        }
    }

    private class ChangeBrushSizeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == increaseButton) {
                if (diameter + BRUSH_SIZE_STEP < MAX_BRUSH_SIZE)
                diameter += BRUSH_SIZE_STEP;

            } else if (event.getSource() == decreaseButton) {
                if (diameter - BRUSH_SIZE_STEP > MIN_BRUSH_SIZE) {
                    diameter -= BRUSH_SIZE_STEP;
                }

            }
        }
    }

    @Override
    public void paintComponent(Graphics pen) {
        super.paintComponent(pen);

        Graphics2D g2 = (Graphics2D) pen;

        for (ColoredPoint p : coloredPointArrayList) {
            g2.setColor(p.getColor());

            g2.fill(new Ellipse2D.Double(
                    p.getX() - p.getDiameter() / 2,
                    p.getY() - p.getDiameter() / 2,
                    p.getDiameter(),
                    p.getDiameter()));
            System.out.println();
        }
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Simple Drawing");
                frame.setSize(WIDTH,HEIGHT);
                // create an object of your class
                Drawing panel = new Drawing();
                frame.getContentPane().add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }


        });
    }
}