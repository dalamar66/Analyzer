package project.execute.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class GetUrl {

	
	public void retrieve(CamelContext context, String url, String fileName) {

		URL u;
		InputStream is = null;
		DataInputStream dis;
		String s;
		
		ProducerTemplate template = context.createProducerTemplate();
		
		StringBuffer sb = new StringBuffer();

		try {

			u = new URL(url);

			is = u.openStream(); // throws an IOException

			dis = new DataInputStream(new BufferedInputStream(is));

			try {
				while ((s = dis.readUTF()) != null) {
					sb.append(s);
				}
			} catch (EOFException e) {
				template.sendBodyAndHeader("direct:retrieve", sb.toString(), "fileName", fileName);
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
