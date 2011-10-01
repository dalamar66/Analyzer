package test;

import java.io.*;
import java.net.*;

import org.apache.camel.Exchange;

public class GetUrl {

public static void retrieve (Exchange exchange) {

  URL u;
  InputStream is = null;
  DataInputStream dis;
  String s;

  try {

     u = new URL("http://ichart.finance.yahoo.com/table.csv?s=%5EFTSE&amp;d=8&amp;e=30&amp;f=2011&amp;g=d&amp;a=3&amp;b=2&amp;c=1984&amp;ignore=.csv");

     is = u.openStream();         // throws an IOException

     dis = new DataInputStream(new BufferedInputStream(is));

     while ((s = dis.readLine()) != null) {
        System.out.println(s);
     }

  } catch (MalformedURLException mue) {

     System.out.println("Ouch - a MalformedURLException happened.");
     mue.printStackTrace();

  } catch (IOException ioe) {

     System.out.println("Oops- an IOException happened.");
     ioe.printStackTrace();

  } finally {

     try {
        is.close();
     } catch (IOException ioe) {
        ioe.getStackTrace();
     }

  } 

}  

} 


