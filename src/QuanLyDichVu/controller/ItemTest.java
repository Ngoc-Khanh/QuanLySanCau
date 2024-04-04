package controller;

import View.ItemView;
import javax.swing.SwingUtilities;

public class ItemTest {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(ItemView::new);
    }
}
