import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JTextArea TextArea;
    JMenuBar jMenuBar;
    TextEditor(){
        //Frame
        frame = new JFrame("Text Editor");
        //TextArea
        TextArea = new JTextArea();
        //JMenuBar
        jMenuBar = new JMenuBar();
        //Menu
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        //MenuItems for File.
        JMenuItem open = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File");
        JMenuItem newFile = new JMenuItem("New File");
        //MenuItems for Edit
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");

        open.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        Close.addActionListener(this);

        file.add(open);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(Close);

        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.add(jMenuBar);
        frame.setJMenuBar(jMenuBar);
        frame.add(TextArea);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TextEditor obj = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call =e.getActionCommand();
        if(call=="New File"){
            TextArea.setText("");
        }else if(call=="Cut"){
            TextArea.cut();
        }else if(call=="Copy"){
            TextArea.copy();
        }else if(call=="Paste"){
            TextArea.paste();
        }else if(call=="Close"){
            frame.setVisible(false);
        }else if(call=="Save File"){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans =jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writter = null;
                try {
                    writter = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writter.write(TextArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(call=="Open File"){
            JFileChooser jFileChooser1 = new JFileChooser("C:");
            int ans = jFileChooser1.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file =new File(jFileChooser1.getSelectedFile().getAbsolutePath());

                try {
                    String s1 ="",s2="";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    TextArea.setText(s2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }else if(call=="Print File"){
            try {
                TextArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
