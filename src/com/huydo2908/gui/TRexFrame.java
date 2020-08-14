package com.huydo2908.gui;

import javax.swing.*;
import java.awt.*;

public class TRexFrame extends JFrame {
    public static final int W_FRAME = 800;
    public static final int H_FRAME = 400;

    public TRexFrame() {
        setTitle("T-Rex Game");
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(new TRexPanel());
    }
}
