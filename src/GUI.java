import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;
import javax.swing.border.Border;

public class GUI extends JFrame {
	private Vector<String> liste = new Vector<String>(); // Vector Array list
	private StringBuilder sb = new StringBuilder();
	private JTextField ausgabeFeld = new JTextField();// JTextField() da hier
	
	private String[] LabelZahlen = new String[] { "7", "8",	"9", "4", "5", "6", "1", "2", "3", "I", "0", ",",};
	private String[] LabelIS = new String[] { "(", ")", "+", "-", "*", "/"};
	private String[] LabelOp = new String[] { "="};
	private String[] LabelEOp = new String[] { "e", "ln", "sqrt", "quad"};
	private String[] LabelFOp = new String[] { "sin", "cos", "tan", "!", "1/x", "PI"};
	private String[] LabelClear = new String[] { "C", "E", "<"};
	String buttonmehrhilfe = null;

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

	private static final long serialVersionUID = 1L;
	private final JTabbedPane TabLeiste = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panOP = new JPanel();

	Border emptyBorder = BorderFactory.createEmptyBorder();
	private final JPanel OberpanelHilfe = new JPanel();

	public GUI() {
		RechnerOberfaeche();
		ButtonBlock();
		getContentPane().setLayout(null);

		TabLeiste.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		TabLeiste.setBackground(SystemColor.activeCaptionBorder);
		TabLeiste.setBounds(-2, 0, 369, 450);
		
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


		OberpanelTR.add(ausgabeFeld);

		panOP.setOpaque(false);
		panOP.setBounds(187, 302, 107, 35);
		panOP.setLayout(new GridLayout(1, 1, 7, 7));
		OberpanelTR.add(panOP);
		OberpanelHilfe.setBackground(new Color(125, 196, 240));
		OberpanelHilfe.setToolTipText("");

		TabLeiste.addTab("Hilfe", null, OberpanelHilfe, null);
		OberpanelHilfe.setLayout(null);

		JTextPane txtKurzanleitung = new JTextPane();
		txtKurzanleitung.setEditable(false);
		txtKurzanleitung.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		txtKurzanleitung.setText("Kurzanleitung\nUm die schnelle Bedienung zu erleichtern, hier die grundlegenden Funktionen und einige Eigenarten:\n\nDie Bedienung verlaeuft via Maus.\n\nUm Funktionen des erweiterten Operationsbereich nutzen zu koennen, kann man die Buttons als Hinweise nutzen, wo der Aufruf hin muss. Insgesamt muss ein volles Klammerpaar entstehen.\nAlso kommt z.B. 'sin(x' vor den Term, 'x)!' jedoch dahinter.\n\nUm die Fakultaet, einer negativen Zahl zu bilden, muss die Zahl von der 0 abgezogen werden.\n\n'C' löscht alles; 'CE' bis zum letzten Operator und '<=' das letzte Zeichen.");
		txtKurzanleitung.setBounds(7, 7, 344, 282);
		txtKurzanleitung.setOpaque(false);
		txtKurzanleitung.setBorder(emptyBorder);
		OberpanelHilfe.add(txtKurzanleitung);

		JButton ButtonMehrHilfe = new JButton("Mehr Hilfe");
		ButtonMehrHilfe.setName("buttonmehrhilfe");
		ButtonMehrHilfe.setBounds(119, 300, 89, 23);
		ButtonMehrHilfe.setBorder(emptyBorder);
		ButtonMehrHilfe.addActionListener(new Ereignis());
		OberpanelHilfe.add(ButtonMehrHilfe);

	}
	
	public void RechnerOberfaeche() {
		
		setBounds(305, 205, 363, 401);
		setResizable(false); 
		setTitle("Taschenrechner");
		setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ausgabeFeld.setHorizontalAlignment(JTextField.RIGHT); 
		ausgabeFeld.setEditable(false); 
		ausgabeFeld.requestFocus();
		ausgabeFeld.setForeground(Color.GREEN);
		ausgabeFeld.setFont(new Font("Serif", Font.BOLD, 20));
		ausgabeFeld.setBackground(Color.black);
		ausgabeFeld.setBounds(10, 10, 344, 40);
	}

	private void ButtonBlock() {
	
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

		for (int i = 0; i < LabelZahlen.length; i++) {
			if (LabelZahlen[i].equals("I")) {
				zahlenButtons[i] = new JButton("+/-");
			}
			else {
				zahlenButtons[i] = new JButton(LabelZahlen[i]);
			}
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
			if (LabelEOp[i].equals("ln")) {
				eopButtons[i] = new JButton("ln(x");
			}
			else if (LabelEOp[i].equals("sqrt")) {
				eopButtons[i] = new JButton("sqrt(x");
				eopButtons[i].setBorder(emptyBorder);
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
				fopButtons[i] = new JButton("sin(x");
			}
			else if (LabelFOp[i].equals("cos")) {
				fopButtons[i] = new JButton("cos(x");
			}
			else if (LabelFOp[i].equals("tan")) {
				fopButtons[i] = new JButton("tan(x");
			}
			else if (LabelFOp[i].equals("!")) {
				fopButtons[i] = new JButton("x)!");
			}
			else if (LabelFOp[i].equals("1/x")) {
				fopButtons[i] = new JButton("1/(x");
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

	public class Ereignis implements ActionListener {
		String lastentry = "";
		@Override
		public void actionPerformed(ActionEvent e) {

			JButton a = (JButton) e.getSource();
			String cmd = a.getName();
			if (liste.size() > 0) {
				lastentry = liste.get(liste.size() - 1);
			}
			if (cmd == "0" || cmd == "1" || cmd == "2" || cmd == "3"
					|| cmd == "4" || cmd == "5" || cmd == "6" || cmd == "7"
					|| cmd == "8" || cmd == "9") {
				sb.append(cmd);
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == ",") {
				sb.append(".");
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == "+" || cmd == "-" || cmd == "*" || cmd == "/") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				updatetxt(); 
			}
			else if (cmd == "(" || cmd == ")") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				updatetxt();
			}
			else if (cmd == "C") {
				liste.clear();
				sb.delete(0, sb.length());
				updatetxt();
			}
			else if (cmd == "I") {
			
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
				if(liste.size()>0 || sb.length() > 0){

					if (sb.length() == 0) {
						liste.remove(liste.size() - 1);
					}
					else {
						sb.delete(sb.length() - 1, sb.length());
					}

					updatetxt();
				}
			}
			else if (cmd == "PI") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(String.valueOf(java.lang.Math.PI));
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == "e") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add((String.valueOf(java.lang.Math.E)));
				ausgabeFeld.setText(ausgabeFeld.getText() + cmd);
			}
			else if (cmd == "quad") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				sb.append(cmd);	
				updatetxt();
			}
			else if (cmd == "sqrt") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}
			else if (cmd == "ln") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(cmd);
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}
			else if (cmd == "sin") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add("sin");
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}
			else if (cmd == "cos") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add("cos");
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}
			else if (cmd == "tan") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add("tan");
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}

			else if (cmd == "1/x") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add("Reziproke");
				liste.add("(");
				liste.add("0");
				liste.add("+");
				updatetxt();
			}
			else if (cmd == "!") {
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				liste.add(")");
				liste.add("!");
				updatetxt();
			}
			else if (cmd == "buttonmehrhilfe") {

				try 
				{
					Desktop.getDesktop().browse(new URL("http://www.taschenrechner.t-imperium.de/").toURI());
				}           
				catch (Exception e1) {}

			}
			else if (cmd == "=") {			
				if (sb.length() != 0) {
					liste.add(sb.toString());
					sb.delete(0, sb.length());
				}
				if(liste.size()!=0){
					if (Parser.istEingeklammert(liste) == false) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Die Klammersetzung ist falsch!",
								null, JOptionPane.ERROR_MESSAGE);
					}
					else {
						Parser p = new Parser(liste);
						String ergebnis = p.Ergebnis();
						ausgabeFeld.setText(ergebnis);
						sb.append(ergebnis);
						liste.clear();
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),
							"Keine Eingabe!",
							null, JOptionPane.ERROR_MESSAGE);
				}
			} 
		}
	}
	public void updatetxt() {
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
