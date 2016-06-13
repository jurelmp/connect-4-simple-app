package com.jurel.connect4;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Jurel on 6/13/2016.
 */
public class Status extends JPanel {
    private JLabel statusContainer;

    public Status() {
        statusContainer = new JLabel();
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setMessage("Ready");
        add(statusContainer);
    }

    public void setMessage(String message) {
        statusContainer.setText("" + message);
    }

}
