package com.jurel.connect4;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jurel on 6/13/2016.
 */
public class ConnectFour extends JFrame {
    private GameMenuBar gameMenuBar;
    private ConnectFourBoard connectFourBoard;

    public ConnectFour() {

        connectFourBoard = new ConnectFourBoard();
        gameMenuBar = new GameMenuBar(connectFourBoard);

        add(connectFourBoard, BorderLayout.CENTER);
        add(new Status(), BorderLayout.SOUTH);

        setMenuBar(gameMenuBar.getGameMenuBar());

        setResizable(true);
        pack();

        setTitle("Connect Four");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame exe = new ConnectFour();
                exe.setVisible(true);
            }
        });
    }
}
