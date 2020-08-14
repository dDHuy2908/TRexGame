package com.huydo2908.utility;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static Image getImage(String name, Class cls){
        Image im = new ImageIcon(
                cls.getResource("/images/" + name)
        ).getImage();
        return im;
    }
}
