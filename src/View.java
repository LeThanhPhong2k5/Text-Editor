import javax.swing.*;
public class View extends JFrame{

    JMenuItem Save = new JMenuItem("Lưu");
    JMenuItem Open = new JMenuItem("Mở");
    JTextPane TextArea = new JTextPane();

    private JMenuBar Menu_bar = new JMenuBar();
    private JMenu Menu = new JMenu("File");

    View(Controller controller){
        this.add(TextArea);
        this.setJMenuBar(Menu_bar);
        this.setTitle("Text Editor");
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(controller);

        Menu_bar.add(Menu);
        Menu.add(Save);
        Menu.add(Open);

        Save.addActionListener(controller);
        Open.addActionListener(controller);
    }

}
