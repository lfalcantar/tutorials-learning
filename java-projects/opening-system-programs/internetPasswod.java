import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class internetPasswod {

	public static void main(String[] args) {

		String bits = System.getProperty("os.arch");
		int input = 0;
		try {

			input = displayWindow();

			if (input == ('c' + 'h' + 'a' + 'o' + 's' + '1' + '2' + '3')) {
				Runtime rt = Runtime.getRuntime();
				if (bits.equals("x86_64")) {
					Process p = rt
							.exec("C:\\Program Files\\Internet Explorer.exe");

				}

				else {
					Process p = rt
							.exec("C:\\Program Files\\Internet Explorer.exe");

				}
			}

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	public static int displayWindow() {
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(2, 2));
		JLabel passwordLbl = new JLabel("Password:");
		JPasswordField passwordFld = new JPasswordField();

		// Add the components to the JPanel
		userPanel.add(passwordLbl);
		userPanel.add(passwordFld);

		int input = JOptionPane.showConfirmDialog(null, userPanel,
				"Enter your password:", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		char[] c1 = passwordFld.getPassword();
		int sum = 0;
		for (char c : c1) {
			sum += c;
		}

		return sum;
	}

}