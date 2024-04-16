package org.bihe.model;

import javax.swing.*;

public class DisplayMessage
{

	public String getValueFromDialog(String msg) {
		return JOptionPane.showInputDialog(null, msg);
	}
}
