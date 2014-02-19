import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

	/*
	 * Deklaration von Elementen
	 */
	private Vector<String> liste = new Vector<String>(); // Vector Array list
															// liste
	private StringBuilder sb = new StringBuilder();
	private JTextField ausgabeFeld = new JTextField();// JTextField() da hier
														// rechtsbuendig moeglich;
	//alle 6 Felder
	private String[] LabelZahlen = new String[] { "7", "8",	"9", "4", "5", "6", "1", "2", "3", "I", "0", ",",};
	private String[] LabelIS = new String[] { "(", ")", "+", "-", "*", "/"};
	private String[] LabelOp = new String[] { "="};
	private String[] LabelEOp = new String[] { "e", "log", "wurz", "quad"};
	private String[] LabelFOp = new String[] { "sin", "cos", "tan", "!", "1/x", "PI"};
	private String[] LabelClear = new String[] { "C", "E", "<"};

	private JPanel panZahlen = new JPanel();
	private JPanel panIS = new JPanel();
	private JPanel panEOp = new JPanel();
	private JPanel panFOp = new JPanel();
	private JPanel panClear = new JPanel();
	
	private JButton zahlenButtons[] = new JButton[15];
	private JButton isButtons[] = new JButton[6];
	private JButton opButtons[] = new JButton[1];
	private JButton eopButtons[] = new JButton[4];
	private JButton fopButtons[] = new JButton[6];
	private JButton clearButtons[] = new JButton[3];
	private JLabel fehlerfeld = new JLabel();
	//Menus
	private static final long serialVersionUID = 1L;
	private final JMenu MenuItem1Datei = new JMenu("Datei");
	private final JMenu MenuItem2Hilfe = new JMenu("Hilfe");
	//Tableiste
	private final JPanel OberpanelER = new JPanel();
	private final JTabbedPane TabLeiste = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panOP = new JPanel();

	Border emptyBorder = BorderFactory.createEmptyBorder();
	/**
	 * Create the JFrame.
	 */
	public GUI() {
		RechnerOberfaeche();
		ButtonBlock();
		getContentPane().setLayout(null);
		

		JMenuBar Menu = new JMenuBar();
		Menu.setBounds(0, 0, 358, 20);
		getContentPane().add(Menu);
		
		Menu.add(MenuItem1Datei);

		
		JMenuItem Menue1ItemBeenden = new JMenuItem("Beenden");
		MenuItem1Datei.add(Menue1ItemBeenden);
		
		Menu.add(MenuItem2Hilfe);

		JMenuItem Menue2ItemHowTo = new JMenuItem("HowTo");
		MenuItem2Hilfe.add(Menue2ItemHowTo);
		

		JMenuItem Menu2ItemFaQ = new JMenuItem("FaQ");
		MenuItem2Hilfe.add(Menu2ItemFaQ);
		TabLeiste.setToolTipText("teeest\r\n");
		TabLeiste.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		TabLeiste.setBackground(SystemColor.activeCaptionBorder);
		TabLeiste.setBounds(-2, 18, 369, 384);

		getContentPane().add(TabLeiste);

		
		JPanel OberpanelTR = new JPanel();
		OberpanelTR.setBackground(new Color(125, 196, 240));
		TabLeiste.addTab("Rechner", null, OberpanelTR, null);
		OberpanelTR.setLayout(null);

		panEOp.setOpaque(false);
		panEOp.setBounds(301, 160, 50, 177);
		panEOp.setLayout(new GridLayout(4, 1, 7, 7));
		OberpanelTR.add(panEOp);

		panClear = new JPanel();
		panClear.setBounds(10, 60, 344, 40);
		OberpanelTR.add(panClear);
		panClear.setOpaque(false);
		panClear.add(clearButtons[0]);
		panClear.add(clearButtons[1]);
		panClear.add(clearButtons[2]);
		panClear.setLayout(null);

		panZahlen.setOpaque(false);
		panZahlen.setBounds(10, 160, 167, 177);
		OberpanelTR.add(panZahlen);
		panZahlen.setLayout(new GridLayout(4, 3, 7, 7));
	
		panIS.setOpaque(false);
		panIS.setBounds(187, 160, 107, 134);
		panIS.setLayout(new GridLayout(3, 2, 7, 7));
		OberpanelTR.add(panIS);
		
		panFOp.setOpaque(false);
		panFOp.setBounds(10, 110, 344, 40);
		OberpanelTR.add(panFOp);
		panFOp.setLayout(new GridLayout(1, 6, 7, 7));
		
		OberpanelTR.add(fehlerfeld);
		OberpanelTR.add(ausgabeFeld);
		
		panOP.setOpaque(false);
		panOP.setBounds(187, 302, 107, 35);
		panOP.setLayout(new GridLayout(1, 1, 7, 7));
		OberpanelTR.add(panOP);
				
				
				
	
		TabLeiste.addTab("Einheitenrechner", null, OberpanelER, null);
		OberpanelER.setLayout(null);
	}

	/*
	 * groesse,eingabe feld festlegen
	 */
	public void RechnerOberfaeche() {
		// Eingabe/Ausgabe
		setBounds(305, 205, 363, 415); // Groesse des Rahmens
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
		ausgabeFeld.setBounds(10, 10, 344, 40);

		fehlerfeld.setBounds(15, 50, 279, 11);
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
//Inhalt Clear Block		
		clearButtons[0] = new JButton("C");
		clearButtons[0].setName(LabelClear[0]);
		clearButtons[0].addActionListener(new Ereignis());
		clearButtons[0].setBorder(emptyBorder);
		clearButtons[0].setBounds(0, 0, 53, 40);
		
		clearButtons[1] = new JButton("CE");
		clearButtons[1].setName(LabelClear[1]);
		clearButtons[1].addActionListener(new Ereignis());
		clearButtons[1].setBorder(emptyBorder);
		clearButtons[1].setBounds(63, 0, 138, 40);
		
		clearButtons[2] = new JButton("<=");
		clearButtons[2].setName(LabelClear[2]);
		clearButtons[2].addActionListener(new Ereignis());
		clearButtons[2].setBorder(emptyBorder);
		clearButtons[2].setBounds(211, 0, 129, 40);
		
//Inhalt der Blocke erzeugen, 5 Blocke
		for (int i = 0; i < LabelZahlen.length; i++) {
			zahlenButtons[i] = new JButton(LabelZahlen[i]);
			zahlenButtons[i].setName(LabelZahlen[i]);
			zahlenButtons[i].addActionListener(new Ereignis());
			panZahlen.add(zahlenButtons[i]);
			zahlenButtons[i].setBackground(new Color(255, 255, 255));
			zahlenButtons[i].setBorder(emptyBorder);
		}
//
		for (int i = 0; i < LabelIS.length; i++) {
			isButtons[i] = new JButton(LabelIS[i]);
			isButtons[i].setName(LabelIS[i]);
			isButtons[i].addActionListener(new Ereignis());
			panIS.add(isButtons[i]);
			isButtons[i].setBorder(emptyBorder);
		}
//
		for (int i = 0; i < LabelOp.length; i++) {
			opButtons[i] = new JButton(LabelOp[i]);
			opButtons[i].setName(LabelOp[i]);
			opButtons[i].addActionListener(new Ereignis());
			panOP.add(opButtons[i]);
			opButtons[i].setBorder(emptyBorder);
		}
//
		for (int i = 0; i < LabelEOp.length; i++) {
			if (LabelEOp[i].equals("log")) {
				eopButtons[i] = new JButton("log(x)");
			}
			else if (LabelEOp[i].equals("wurz")) {
				eopButtons[i] = new JButton("sqrt(x)");
			}
			else if (LabelEOp[i].equals("quad")) {
				eopButtons[i] = new JButton("x²");
			}
			else {
				eopButtons[i] = new JButton(LabelEOp[i]);
			}
			eopButtons[i].setName(LabelEOp[i]);
			eopButtons[i].addActionListener(new Ereignis());
			panEOp.add(eopButtons[i]);
			eopButtons[i].setBorder(emptyBorder);
		}
//
		for (int i = 0; i < LabelFOp.length; i++) {
			if (LabelFOp[i].equals("sin")) {
				fopButtons[i] = new JButton("sin(x)");
			}
			else if (LabelFOp[i].equals("cos")) {
				fopButtons[i] = new JButton("cos(x)");
			}
			else if (LabelFOp[i].equals("tan")) {
				fopButtons[i] = new JButton("tan(x)");
			}
			else if (LabelFOp[i].equals("!")) {
				fopButtons[i] = new JButton("(x)!");
			}
			else {
				fopButtons[i] = new JButton(LabelFOp[i]);
			}
			fopButtons[i].setName(LabelFOp[i]);
			fopButtons[i].addActionListener(new Ereignis());
			panFOp.add(fopButtons[i]);
			fopButtons[i].setBorder(emptyBorder);
		}

	}

	/*
	 * Actionen festlegen
	 */
	public class Ereignis implements ActionListener {
		String lastentry = ""; // letzte eingabe liste
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
				if(liste.size()>0 || sb.length() > 0){
					if (sb.length() == 0) {
						liste.remove(liste.size() - 1);
					}
					else {
						sb.delete(0, sb.length());
					}
	
					updatetxt();
				}
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
			else if (cmd == "PI") {
				// Tobi - Pi konst //java.lang.Math.PI
				liste.add(sb.toString());
				sb.delete(0, sb.length());
				liste.add(String.valueOf(java.lang.Math.PI));
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
				System.out.println(liste.toString());
			}
			else if (cmd == "e") {
				// Tobi - e konst //java.lang.Math.E
				liste.add(sb.toString());
				sb.delete(0, sb.length());
				liste.add((String.valueOf(java.lang.Math.E)));
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
				//updatetxt(); // textbox update - macht E gleich zu 2.8...
			}
			else if (cmd == "quad") {
				// Tobi - Quadrieren
				liste.add(sb.toString());
				sb.delete(0, sb.length());
				sb.append(cmd);
				ausgabeFeld.setText(ausgabeFeld.getText());
				updatetxt();
			}
			else if (cmd == "=") {			
				// gleiche wie komentar zuvor
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				System.out.println(liste.toString());

				if(liste.size()!=0){
					if (Parser.istEingeklammert(liste) == false) {
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
