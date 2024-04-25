package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class Resources 
{
	public static Image getImage(String name)
	{
		return Toolkit.getDefaultToolkit().getImage("src/main/java/resources/" + name);
	}
	public static File getFile(String name)
	{
			File f = new File("src/main/java/resources/" + name);
			return f;

	}
}
