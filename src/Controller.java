import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Controller implements ActionListener, WindowListener {
    private View view = new View(this);
    private Model model = new Model();
    private JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private boolean saved = false;
    private String currentText = "";

    public Controller() throws IOException{
        view.Save.addActionListener(e ->{
           try{
               Save_file(view.TextArea);
           } catch(IOException e1){
               e1.printStackTrace();
           }
        });

        view.Open.addActionListener(e -> {
           try{
               Open_file(view.TextArea);
           }catch(IOException | BadLocationException e1){
                e1.printStackTrace();
            }
        });

        view.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                Check_Before_Closing();
            }
        });
    }
    private void Check_Before_Closing(){
        int safe = JOptionPane.showConfirmDialog(null, "Text của bạn chưa được lưu, bạn có chắc chắn muốn thoát không",
                "Warning", JOptionPane.YES_NO_OPTION);
        if(safe == JOptionPane.YES_OPTION){
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }else{
            view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }

    private void Save_file(JTextPane pane) throws IOException {
        int returnValue = jfc.showDialog(null,"Save my file");

        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = jfc.getSelectedFile();
            selectedFile.createNewFile();
            model.Write_To_File(new File(selectedFile.getAbsolutePath()),pane);
            saved = true;
        }

        currentText = pane.getText();

    }
    private void Open_file(JTextPane pane) throws IOException, BadLocationException {
        pane.setText("");
        int returnValue = jfc.showDialog(null, "Open my file");
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = jfc.getSelectedFile();
            try {
                model.Read_File(selectedFile, pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.Save)){
            try{
                Save_file(view.TextArea);
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }

        if(e.getSource().equals(view.Open)) {
            try{
                Open_file(view.TextArea);
            } catch (IOException | BadLocationException e1) {
                e1.printStackTrace();
            }
        }


    }


    @Override
    public void windowClosing(WindowEvent e) {

        if(!saved & view.TextArea.getText().equals("")) {

            Check_Before_Closing();

        }

        if(saved & view.TextArea.getText().equals(currentText)){

            Check_Before_Closing();

        }
    }

    @Override
    public void windowOpened(WindowEvent e){

    }

    @Override
    public void windowClosed(WindowEvent e){

    }

    @Override
    public void windowIconified(WindowEvent e){

    }

    @Override
    public void windowDeiconified(WindowEvent e){

    }

    @Override
    public void windowActivated(WindowEvent e){

    }

    @Override
    public void windowDeactivated(WindowEvent e){

    }
}
