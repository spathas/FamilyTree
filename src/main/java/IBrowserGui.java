import javax.swing.JButton;
import javax.swing.JFileChooser;

public interface IBrowserGui {

    static String chooseFolderGui(String nameBtn, String dialogTitle) {
        JButton exportFile = new JButton(nameBtn);
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("C:\\Users\\spath\\Desktop"));
        chooser.setDialogTitle(dialogTitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(exportFile) == JFileChooser.APPROVE_OPTION) {
            System.out.println("User chooce: " + chooser.getSelectedFile().getAbsolutePath());
        }

        return chooser.getSelectedFile().getAbsolutePath();
    }

    static String chooseFileGui(String nameBtn, String dialogTitle) {
        JButton importCSV = new JButton(nameBtn);
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("C:\\Users\\spath\\Desktop"));
        chooser.setDialogTitle(dialogTitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(chooser.showOpenDialog(importCSV) == JFileChooser.APPROVE_OPTION) {
            System.out.println("User chooce: " + chooser.getSelectedFile().getAbsolutePath());
        }

        return chooser.getSelectedFile().getAbsolutePath();
    }
}