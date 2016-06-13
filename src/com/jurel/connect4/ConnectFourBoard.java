package com.jurel.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jurel on 6/13/2016.
 */
public class ConnectFourBoard extends JPanel implements ActionListener {

    private final int ROWS = 6;
    private final int COLS = 7;
    private final int SIZE = 100;
    private final int WIDTH = COLS * SIZE;
    private final int HEIGHT = ROWS * SIZE;
    private final int GOAL = 4;

    int[][] game;

    private int current;
    private int playerTurns;
    private boolean result;
    private int counter;
    private int lastPlay;
    private Map<String, Integer> lastMove;

    public ConnectFourBoard() {

        addMouseListener(new ConnectFourAdapter());
        // setBackground(Color.cyan);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        init();
    }

    private void init() {
        game = new int[COLS][ROWS];
        lastMove = new HashMap<>();
        current = 1;
        playerTurns = 1;
        result = false;
        counter = 0;
        lastPlay = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGridBoard(g);
        drawChips(g);
    }

    public void resetGame() {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                game[i][j] = 0;
            }
        }
        repaint();
    }

    public ConnectFourBoard getGame() {
        return this;
    }

    private void drawChips(Graphics g) {
        for (int x = 0; x < game.length; x++) {
            for (int y = 0; y < game[x].length; y++) {
                switch (game[x][y]) {
                    case 0: g.setColor(Color.WHITE);
                        g.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
                        break;
                    case 1: g.setColor(Color.GREEN);
                        g.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
                        break;
                    case 2: g.setColor(Color.RED);
                        g.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    private void drawGridBoard(Graphics g) {
        g.setColor(Color.BLACK);

        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                g.drawRect(y * SIZE, x * SIZE, SIZE, SIZE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check for connect four
    }

    private class ConnectFourAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            //new Status().setMessage("x = " + e.getX() + "; y = " + e.getY());
            boolean flag = setChip(e.getX() / SIZE);


            if (flag) {
                repaint();
                if (isConnect(lastMove)) {
                    //JOptionPane.showMessageDialog(null, "Player " + current + " wins.\nWant to play again?");
                    int choice = JOptionPane.showConfirmDialog(null, "Player " + current + " wins.\nWant to play again?");
                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame();
                    }
                }
            }

            //JOptionPane.showMessageDialog(null, "x = " + e.getX() / SIZE + "; y = " + e.getY() / SIZE );
        }

        private boolean setChip(int col) {
            int temp = vacantRow(col);
            if (temp != -1) {
                lastPlay = current;
                lastMove.put("row", temp);
                lastMove.put("col", col);
                game[col][temp] = current;
                current = (playerTurns % 2 == 0)? 1 : 2;
                playerTurns++;
                return  true;
            }
            return false;
        }

        private int vacantRow(int col) {
            for (int i = game[col].length - 1; i >= 0 ; i--) {
                if (game[col][i] == 0) {
                    return i;
                }
            }
            return -1;
        }

        private boolean isConnect(Map<String, Integer> item) {
            //boolean flag = false;
            counter = 0;
            int colX = item.get("col");
            int rowY = item.get("row");

            // horizontal right
            for (int x = colX; x < game.length; x++) {
                if (game[x][rowY] == lastPlay) {
                    counter++;
                } else {
                    break;
                }
            }
            if (counter == GOAL) {
                return true;
            }
            /** horizontal right back with one space */
            if (colX > 0){
                counter = 0;
                for (int x = colX - 1; x < game.length; x++) {
                    if (game[x][rowY] == lastPlay) {
                        counter++;
                    } else {
                        break;
                    }
                }
                if (counter == GOAL) {
                    return true;
                }
            }

            // horizontal left
            counter = 0;
            for (int x = colX; x >= 0; x--) {
                if (game[x][rowY] == lastPlay) {
                    counter++;
                } else {
                    break;
                }
            }
            if (counter == GOAL) {
                return true;
            }
            /** horizontal left forward with one space */
            if (colX < game[colX].length - 1) {
                counter = 0;
                for (int x = colX + 1; x >= 0; x--) {
                    if (game[x][rowY] == lastPlay) {
                        counter++;
                    } else {
                        break;
                    }
                }
                if (counter == GOAL) {
                    return true;
                }
            }
            // vertical up
            counter = 0;
            for (int y = rowY; y < game[colX].length; y++) {
                if (game[colX][y] == lastPlay) {
                    counter++;
                } else {
                    break;
                }
            }
            if (counter == GOAL) {
                return true;
            }
            /** vertical up with -1 space */
            if (rowY > 0) {
                counter = 0;
                for (int y = rowY - 1; y < game[colX].length; y++) {
                    if (game[colX][y] == lastPlay) {
                        counter++;
                    } else {
                        break;
                    }
                }
                if (counter == GOAL) {
                    return true;
                }
            }
            // vertical down
            counter = 0;
            for (int y = rowY; y >= 0; y--) {
                if (game[colX][y] == lastPlay) {
                    counter++;
                } else {
                    break;
                }
            }
            if (counter == GOAL) {
                return true;
            }
            /** vertical going down with +1 space */
            /*if (rowY < game[colX].length - 1) {
                counter = 0;
                for (int y = rowY; y >= 0; y--) {
                    if (game[colX][y] == lastPlay) {
                        counter++;
                    } else {
                        break;
                    }
                }
                if (counter == GOAL) {
                    return true;
                }
            }*/
            // diagonal

            // JOptionPane.showMessageDialog(null, "x = " + colX + "; y = " + rowY + "; counter : " + counter + "; data = " + game[colX][rowY] + "; current = " + current + "; last = " + lastPlay);
            return false;
        }
    }
}
