import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GUI extends JFrame {

	/*
	 * Deklaration von Elementen
	 */
	private Vector<String> liste = new Vector<String>(); // Vector Array list
															// liste
	private StringBuilder sb = new StringBuilder();
	private JTextField ausgabeFeld = new JTextField();// JTextField() da hier
														// rechtsbuendig moeglich;

	private String[] LabelOp = new String[] { "<", "(", ")", "+", "7", "8",
			"9", "-", "4", "5", "6", "*", "1", "2", "3", "/", "0", "I", ",",
			"=" };
	private String[] LabelClear = new String[] { "E", "C" };

	private JPanel panOp = new JPanel();
	private JPanel panClear = new JPanel();
	private JButton opButtons[] = new JButton[20];
	private JButton clearButtons[] = new JButton[2];
	private JLabel fehlerfeld = new JLabel();

	private static final long serialVersionUID = 1L;
	private final JPanel OberpanelER = new JPanel();
	private final JPanel Hauptbereich = new JPanel();
	private final JMenu MenueItem1Datei = new JMenu("Datei");
	private final JMenu MenueItem2Hilfe = new JMenu("Hilfe");

	/**
	 * Create the JFrame.
	 */
	public GUI() {
		RechnerOberfaeche();
		ButtonBlock();
		getContentPane().setLayout(null);
		
		JMenuBar Menue = new JMenuBar();
		Menue.setBounds(0, 0, 244, 21);
		getContentPane().add(Menue);
		
		Menue.add(MenueItem1Datei);
		
		JMenuItem Menue1ItemBeenden = new JMenuItem("Beenden");
		MenueItem1Datei.add(Menue1ItemBeenden);
		
		Menue.add(MenueItem2Hilfe);
		
		JMenuItem Menue2ItemHowTo = new JMenuItem("HowTo");
		MenueItem2Hilfe.add(Menue2ItemHowTo);
		
		JMenuItem Menue2ItemFaQ = new JMenuItem("FaQ");
		MenueItem2Hilfe.add(Menue2ItemFaQ);
		Hauptbereich.setBounds(0, 44, 244, 326);
		
		getContentPane().add(Hauptbereich);
		Hauptbereich.setLayout(new CardLayout(0, 0));
		
		JPanel OberpanelTR = new JPanel();
		Hauptbereich.add(OberpanelTR, "name_398532789287605");
		OberpanelTR.setLayout(null);
		panClear = new JPanel();
		panClear.setBounds(15, 71, 213, 35);
		OberpanelTR.add(panClear);
		panClear.setLayout(new GridLayout(1, 2, 7, 0));
		
				panClear.add(clearButtons[0]);
				panClear.add(clearButtons[1]);
		panOp.setBounds(15, 117, 213, 198);
		OberpanelTR.add(panOp);
		panOp.setLayout(new GridLayout(5, 4, 7, 7));
		OberpanelTR.add(fehlerfeld);
		OberpanelTR.add(ausgabeFeld);
		Hauptbereich.add(OberpanelER, "name_398532806108807");
		OberpanelER.setLayout(null);

	
	}

	/*
	 * groesse,eingabe feld festlegen
	 */
	public void RechnerOberfaeche() {
		// Eingabe/Ausgabe
		setBounds(305, 205, 250, 399); // Groesse des Rahmens
		setResizable(false); // kein Maximieren moeglich
		setTitle("Taschenrechner");
		setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ausgabeFeld.setHorizontalAlignment(JTextField.RIGHT); // rechtsbuendig
		ausgabeFeld.setEditable(false); // man kann etwas in Display schreiben
		ausgabeFeld.requestFocus(); // hat im Rechner den Focus
		ausgabeFeld.setForeground(Color.GREEN);
		ausgabeFeld.setFont(new Font("Serif", Font.BOLD, 20));
		ausgabeFeld.setBackground(Color.black);
		ausgabeFeld.setBounds(15, 11, 213, 35);

		fehlerfeld.setBounds(15, 45, 213, 15);
		fehlerfeld.setFont(new Font("Serif", Font.BOLD, 15));
		fehlerfeld.setForeground(Color.RED);

	}

	private void ButtonBlock() {
		/*
		 * Buttons generieren und GridLayout
		 */
		/*
		 * Panels generieren
		 */

		clearButtons[0] = new JButton("CE");
		clearButtons[0].setName(LabelClear[0]);
		clearButtons[0].addActionListener(new Ereignis());

		clearButtons[1] = new JButton(LabelClear[1]);
		clearButtons[1].setName(LabelClear[1]);
		clearButtons[1].addActionListener(new Ereignis());
		for (int i = 0; i < LabelOp.length; i++) {
			/*
			 * Button inhalt erzeugen
			 */
			if (LabelOp[i].equals("<")) {
				opButtons[i] = new JButton("<=");
			}
			else if (LabelOp[i].equals("I")) {
				opButtons[i] = new JButton("+/-");
			}
			else {
				opButtons[i] = new JButton(LabelOp[i]);
			}
			opButtons[i].setName(LabelOp[i]);
			opButtons[i].addActionListener(new Ereignis());
			panOp.add(opButtons[i]);
		}
	}

	/*
	 * Actionen festlegen
	 */
	public class Ereignis implements ActionListener {
		String lastentry = ""; // letzte eingabe liste
		private Parser parser = new Parser();
		@Override
		public void actionPerformed(ActionEvent e) {
			fehlerfeld.setText("");

			JButton a = (JButton) e.getSource();
			String cmd = a.getName(); // Name der aufgerufenden Button
			if (liste.size() > 0) {
				/*
				 * letzte eingabe abrufen
				 */
				lastentry = liste.get(liste.size() - 1);
			}
			System.out.print(a.getName());
			if (cmd == "0" || cmd == "1" || cmd == "2" || cmd == "3"
					|| cmd == "4" || cmd == "5" || cmd == "6" || cmd == "7"
					|| cmd == "8" || cmd == "9") {
				/*
				 * Wert zum StringBulder hinzufuegen
				 */
				sb.append(cmd);
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == ",") {
				/*
				 * mach aus komma ein punkt --> wegen double spaeter
				 */
				sb.append(".");
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == "+" || cmd == "-" || cmd == "*" || cmd == "/") {
				/*
				 * Operationen zur liste einfuegen, sofern sb nicht leer ist,
				 * wird die zahl zur liste hinzugefuegt und fuege operator hinzu
				 * und leere sb
				 */
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				updatetxt(); // textbox update
			}
			else if (cmd == "(" || cmd == ")") {
				// Klammer setzung
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				updatetxt(); // textbox update
			}
			else if (cmd == "C") {
				/*
				 * Komplette liste loeschen
				 */
				liste.clear();
				sb.delete(0, sb.length());
				System.out.println("clear: " + liste.toString());
				updatetxt();
			}
			else if (cmd == "I") {
				/*
				 * I bedeutet vorzeichen wechsel .. man nimmt die letzte eingabe
				 * und schaut ob es - ist und tauscht es dann bei + das selbe
				 * bei is empty das bedeutet is die erste zahl der eingabe
				 */
				if (sb.length() > 0) {
					if (lastentry.equals("-")) {
						liste.set(liste.size() - 1, "+");
					}
					else if (lastentry.equals("+")) {
						liste.set(liste.size() - 1, "-");
					}
					else if (lastentry.isEmpty()) {
						
						liste.add("-");
					}
				}
				updatetxt();
			}
			else if (cmd == "E") {
				/*
				 * E bedeutet CE, also eingabe bis zum letztn Operator rueckgaenig
				 * machen
				 */
				if (sb.length() == 0) {
					liste.remove(liste.size() - 1);
				}
				else {
					sb.delete(0, sb.length());
				}

				updatetxt();
			}
			else if (cmd == "<") {
				/*
				 * Letzte Eingabe rueckgaenig machen
				 */
				if(liste.size()>0 || sb.length() > 0){
					
					if (sb.length() == 0) {
						liste.remove(liste.size() - 1);
					}
					else {
						System.out.println(sb.toString());
	
						sb.delete(sb.length() - 1, sb.length());
					}
	
					updatetxt();
				}
			}
			else if (cmd == "=") {
				
				// gleiche wie komentar zuvor
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				System.out.println(liste.toString());

				if(liste.size()!=0){
					if (parser.klammerCount(liste) != 0) {
						fehlerfeld.setText("Die Klammersetzung ist falsch!");
					}
					else {

						// Parsen und Ergebis
						Parser p = new Parser(liste);
						String ergebnis = p.Ergebnis();
						ausgabeFeld.setText(ergebnis);
						sb.append(ergebnis);
						liste.clear();
					}
				}
				else{
					fehlerfeld.setText("Keine Eingabe!");
				}
			} 

		}
	}

	public void updatetxt() {
		/*
		 * Das eingabe feld updaten
		 */
		StringBuilder out = new StringBuilder();
		if(liste.isEmpty()){
			ausgabeFeld.setText(sb.toString());
		}
		else {
			for (String txt : liste) {
				out.append(txt);
			}
			ausgabeFeld.setText(out.toString() + sb.toString());
		}

		

	}
}
