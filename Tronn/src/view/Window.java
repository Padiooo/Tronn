package view;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private Pannel pan;
	private KeyManager manager;

	public Window(Pannel pan) {
		this.setTitle("Tronn The Evolution");
		this.setSize(606, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(pan);
		this.setResizable(false);
		// add a keylistener
		this.manager = new KeyManager();
		this.addKeyListener(manager);
		this.setVisible(true);

		this.pan = pan;

	}

	public Pannel getPan() {
		return pan;
	}

	public void setPan(Pannel pan) {
		this.pan = pan;
	}

	public KeyManager getManager() {
		return manager;
	}

	public void setManager(KeyManager manager) {
		this.manager = manager;
	}
}
