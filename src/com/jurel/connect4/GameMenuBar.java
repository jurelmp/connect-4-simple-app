package com.jurel.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Jurel on 6/13/2016.
 */
public class GameMenuBar {

    MenuBar menuBar;
    Menu menu;
    MenuItem newGameMenuItem;
    private ConnectFourBoard connectBoard;

    public GameMenuBar(final ConnectFourBoard connectFourBoard) {

        this.connectBoard = connectFourBoard;

        menuBar = new MenuBar();
        menu = new Menu("Game");

        newGameMenuItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_N));
        newGameMenuItem.setActionCommand("New");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectBoard.resetGame();
            }
        });

        menu.add(newGameMenuItem);

        menuBar.add(menu);
    }

    public MenuBar getGameMenuBar() {
        return this.menuBar;
    }
}
