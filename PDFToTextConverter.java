/*
 * To chsange this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openbravo.pdfreader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.sf.vfsjfilechooser.VFSJFileChooser;
import net.sf.vfsjfilechooser.VFSJFileChooser.RETURN_TYPE;
import net.sf.vfsjfilechooser.VFSJFileChooser.SELECTION_MODE;
import net.sf.vfsjfilechooser.accessories.DefaultAccessoriesPanel;
import net.sf.vfsjfilechooser.utils.VFSResources;
import net.sf.vfsjfilechooser.utils.VFSUtils;

import org.apache.commons.vfs2.FileObject;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * 
 * @author lenovo pc
 */
public class PDFToTextConverter extends JFrame implements Runnable {
  // private static String lastJFCDirectory = null;
  private JTextField filenameTextField;
  private JButton openButton;
  private VFSJFileChooser fileChooser;
  private String buttonText;
  private static JFileChooser jfc = new JFileChooser();

  public void init() {
    // selectPDFFiles();
  }

  // allow pdf files selection for converting
  public void selectPDFFiles() {
    System.out.println("selectpdffiles or yaha bhi agya ");
    // JFileChooser chooser = new JFileChooser();

    /*
     * FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf"); //
     * chooser.setFileFilter(filter); jfc.setFileFilter(filter); //
     * chooser.setMultiSelectionEnabled(true); // int returnVal = chooser.showOpenDialog(null);
     * jfc.setMultiSelectionEnabled(true); int returnVal =
     * jfc.showOpenDialog(PDFToTextConverter.this); if (returnVal == JFileChooser.APPROVE_OPTION) {
     * System.out.println("here i am"); // File[] Files = chooser.getSelectedFiles(); File[] Files =
     * jfc.getSelectedFiles(); System.out.println("Please wait..."); for (int i = 0; i <
     * Files.length; i++) { convertPDFToText(Files[i].toString(), "Converted" + i + ".html"); } //
     * System.out.println("Conversion complete"); }
     */

    fileChooser = new VFSJFileChooser();

    // configure the file dialog
    fileChooser.setAccessory(new DefaultAccessoriesPanel(fileChooser));
    fileChooser.setFileHidingEnabled(false);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setFileSelectionMode(SELECTION_MODE.FILES_ONLY);
    filenameTextField = new JTextField(100);
    buttonText = VFSResources.getMessage("VFSJFileChooser.directoryOpenButtonText");
    openButton = new JButton(new OpenAction(buttonText));
    getContentPane().add(openButton, BorderLayout.WEST);
    getContentPane().add(filenameTextField, BorderLayout.CENTER);
    pack();
    System.out.println("You selected:1");
    // show the file dialog
    RETURN_TYPE answer = fileChooser.showOpenDialog(null);

    // check if a file was selected
    if (answer == RETURN_TYPE.APPROVE) {
      System.out.println("You selected:234");
      final File aFileObject = (fileChooser.getSelectedFile());
      System.out.println("You selected:2134");
      // remove authentication credentials from the file path
      final String safeName = VFSUtils.getFriendlyName(aFileObject.toString());

      System.out.println("You selected:" + safeName);
      // for (int i = 0; i < aFileObject.length(); i++) {
      convertPDFToText(aFileObject.toString(), "Converted" + "0" + ".html");
      // }
      System.out.println("Conversion complete");
      try {
        // Dbcon d = new Dbcon();
        // String[] arguments = new String[] { "123" };

        Dbcon.main();
      } catch (Exception e) {
        //
      }
    }

    /*
     * JButton b = new JButton("File"); System.out.println("or yaha bhi agya returns");
     * System.out.println("Conversion complete"); try {
     * 
     * b.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent ae) {
     * JFileChooser jfc = new JFileChooser(); jfc.setCurrentDirectory(new File("C:\\"));
     * System.out.println("or yaha bhi agya listener me "); int returnVal =
     * jfc.showOpenDialog(PDFToTextConverter.this);
     * System.out.println("or yaha bhi agya listener me display ke bad"); String filename =
     * "E\\red\\z.pdf"; if (returnVal == JFileChooser.APPROVE_OPTION) { File afile =
     * jfc.getSelectedFile(); filename = afile.getName(); } System.out.println(filename); } }); }
     * catch (Exception f) { System.out.println(f); } getContentPane().add(b);
     */
  }

  public static void convertPDFToText(String src, String desc) {
    try {
      // create file writer

      FileWriter fw = new FileWriter(desc);
      // create buffered writer
      BufferedWriter bw = new BufferedWriter(fw);
      // create pdf reader
      PdfReader pr = new PdfReader(src);
      // get the number of pages in the document

      int pNum = pr.getNumberOfPages();
      // extract text from each page and write it to the output text file
      for (int page = 1; page <= pNum; page++) {
        // System.out.println("hi bye ");
        String text = PdfTextExtractor.getTextFromPage(pr, page);
        // System.out.println("yaha bhi agya ");
        bw.write(text);

        bw.newLine();

      }
      // PDFToTextConverter z = new pop();
      System.out.println("or yaha bhi agya ");
      // z.selectPDFFiles();

      bw.flush();
      bw.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    setLocationRelativeTo(getOwner());
    setVisible(true);
    System.out.println("You selected:run");
    toFront();

  }

  public class OpenAction extends AbstractAction {
    /**
     * Create the action with a name
     * 
     * @param name
     *          The action's name
     */
    public OpenAction(String name) {
      super(name);
      System.out.println("You selected:OA");
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent actionEvent) {
      // show the file dialog
      RETURN_TYPE answer = fileChooser.showOpenDialog(PDFToTextConverter.this);
      System.out.println("You selected:ActionPerf");
      // check if a file was selected
      if (answer == RETURN_TYPE.APPROVE) {
        // retrieve the selected file
        final FileObject aFileObject = (FileObject) fileChooser.getSelectedFile();
        System.out.println("You selected:AP2");
        // remove authentication credentials from the file path
        final String safeName = VFSUtils.getFriendlyName(aFileObject.toString());

        // display the file path
        filenameTextField.setText(safeName);
      }
    }
  } // end class OpenAction

}
