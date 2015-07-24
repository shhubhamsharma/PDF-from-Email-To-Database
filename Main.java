/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openbravo.pdfreader;

import java.io.IOException;

import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 * 
 * @author lenovo pc
 */
public class Main extends DalBaseProcess {

  /**
   * @param args
   *          the command line arguments
   */
  public static void main(String[] args) throws IOException {

  }

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub
    try {

      // retrieve the parameters from the bundle
      // final String bPartnerId = (String) bundle.getParams().get("cBpartnerId");
      // final String organizationId = (String) bundle.getParams().get("adOrgId");
      // final String tabId = (String) bundle.getParams().get("tabId");
      final String hostname = (String) bundle.getParams().get("hostname");
      final String username = (String) bundle.getParams().get("username");
      final String password = (String) bundle.getParams().get("password");
      System.out.println("shubham");
      // final String webmail = (String) bundle.getParams().get("webmail");
      final StringBuilder sb = new StringBuilder();
      // final String myString = (String) bundle.getParams().get("mystring");
      final OBError msg = new OBError();
      System.out.println("shubham test 2s");
      // msg.setType("Success");
      // msg.setTitle("Read parameters!");
      // msg.setMessage(sb.toString());

      // bundle.setResult(msg);
      // pop p = new pop();
      System.out.println("here it is");
      pop.main(hostname, username, password);

    } catch (final Exception e) {
      e.printStackTrace(System.err);
      final OBError msg = new OBError();
      // msg.setType("Error");
      // msg.setMessage(e.getMessage());
      msg.setTitle("Error occurred");
      bundle.setResult(msg);
    }

  }
}
