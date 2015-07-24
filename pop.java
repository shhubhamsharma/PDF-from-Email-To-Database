/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openbravo.pdfreader;

/**
 *
 * @author lenovo pc
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.pdfreader.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import org.openbravo.base.exception.OBException;

import com.jscape.inet.email.EmailMessage;
import com.jscape.inet.mime.Attachment;
import com.jscape.inet.mime.MimeException;
import com.jscape.inet.pop.Pop;
import com.jscape.inet.pop.PopException;

public class pop extends PDFToTextConverter {
  private static File messagesDir = new File("\\msg");
  private static File attachmentsDir = new File("\\red");
  private static String fileName;
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // get messages from Pop mailbox
  public static void getMessages(String hostname, String username, String password)
      throws PopException, MimeException, IOException, OBException {
    // ensure that messages directory is valid
    if (!messagesDir.exists()) {
      messagesDir.mkdir();
    }

    // ensure that attachments directory is valid
    if (!attachmentsDir.exists()) {
      attachmentsDir.mkdir();
    }
    // String hostname = "webmail1.hostinger.in";
    // connect and login
    Pop pop = new Pop(hostname, username, "audit@1234");
    pop.connect();

    System.out.println("Retrieving messsages... from " + hostname);

    // get message count
    int messageCount = pop.getMessageCount();
    if (messageCount < 1) {
      // //System.out.println("No messages found");
    }

    // get messages and write messages to disk
    for (int i = 1; i <= messageCount; ++i) {
      EmailMessage msg = (EmailMessage) pop.getMessage(i);
      File f = new File(messagesDir, "log" + i + ".txt");
      FileOutputStream fout = new FileOutputStream(f);
      fout.write(msg.getMessage());

      System.out.println("Retrieved message: " + f.getAbsoluteFile());
      fout.close();
      // write attachments to disk
      int attachnum = 0;
      Enumeration attachments = msg.getAttachments();
      while (attachments.hasMoreElements()) {
        ++attachnum;
        Attachment attach = (Attachment) attachments.nextElement();
        String attachmentFileName = attach.getFilename();
        if ((attachmentFileName == null)) {

          attachmentFileName = "attachment" + i + "-" + attachnum;

        }

        fileName = attach.getFilename();
        if ((fileName == null)) {
          fileName = System.currentTimeMillis() + ".pdf";

          // System.out.println("filename" + fileName);
        }
        if (fileName.endsWith(".pdf")) {
          File attFile = new File(attachmentsDir, fileName);

          FileOutputStream attOut = new FileOutputStream(attFile);

          try {

            attOut.write(attach.getFileData());
            attOut.close();
            System.out.println("Retrieved attachment: " + attFile.getAbsoluteFile());
            convertPDFToText("..\\red\\" + fileName, "r.txt");
          } catch (Exception e) {
            throw new PopException("unable to decode file attachment");
          }
        }
      }
    }

  }

  /*
   * public static void convertPDFToText(String src,String desc){ try{ //create file writer
   * 
   * //FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
   * 
   * FileWriter fw=new FileWriter(desc); //create buffered writer BufferedWriter bw=new
   * BufferedWriter(fw); //create pdf reader PdfReader pr=new PdfReader(src);
   * /****************************************************************************************88888
   * ************************************************************8
   * **********************************************************8
   * 88*******************************************************88
   * 8*****************************************************
   * ***************************************************** *****************************************
   * *&***************************************888*\
   * 
   * 
   * 
   * // /get the number of pages in the document int pNum=pr.getNumberOfPages(); //extract text from
   * each page and write it to the output text file for(int page=1;page<=pNum;page++){ String
   * text=PdfTextExtractor.getTextFromPage(pr, page); bw.write(text); bw.newLine();
   * 
   * } bw.flush(); bw.close();
   * 
   * 
   * 
   * }catch(Exception e){e.printStackTrace();}
   * 
   * }
   */

  public static void main(String hostname, String username, String password) throws Exception {
    // String hostname;
    System.out.println("sharma /n /n /");
    try {
      // //System.out.print("Enter Pop3 hostname (e.g. mail.domain.com): ");
      // hostname = "webmail1.hostinger.in";
      // //System.out.print("Enter Pop3 Username: ");
      String username1 = username;
      String hostname1 = hostname;
      System.out.println(username1);
      // //System.out.print("Enter Pop3 Password: ");
      String password1 = password;
      System.out.println(password);
      // pop tutorial = new pop();
      pop.getMessages(hostname1, username1, password1);
      pop s = new pop();
      s.selectPDFFiles();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

interface at {
  public void main();

  public void getMessages(String username, String password);
}
