package MainMenu;

import javax.swing.*;

import QuanLyDatSan.DatSanView;

public class MainFrame extends JFrame {
    private MainMenuView mainMenuView;
    private DatSanView datSanView;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Main Frame");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        mainMenuView = new MainMenuView();
        datSanView = new DatSanView();

        mainPanel.add(mainMenuView, "MainMenuView");
        mainPanel.add(datSanView, "DatSanView");

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}