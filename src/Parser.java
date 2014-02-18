import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Parser {
	/*
	 * Generiere Vector array liste
	 */
	private Vector<String> Wort = null;
	private int aufkl = 0, zukl = 0;
	private RechnenOperationen re = new RechnenOperationen();
	public Parser(Vector<String> v) {
		this.Wort = v;
	}
	
	public Parser() {
		
	}
	public String Ergebnis() {
		/*
		 * Hier wird das ergebnis zusammen gesetzt
		 */
		InKlammer();
		StringBuilder out = new StringBuilder();
		for (String txt : zusammenfuehrung()) {
			out.append(txt);
		}
		return out.toString();
	}

	private Vector<String> zusammenfuehrung() {
		/*
		 * Mehrfaches aufrufen der Klammerpruefung, mit uebergabe in die
		 * rechnenoperationen
		 */
		boolean ende = false;
		Vector<String> liste = new Vector<String>();
		liste = Wort;
		while (ende == false) {
			if (liste.get(0).equals("(")) {
				Vector<String> t = new Vector<>();
				t = Rechne(KlammerPruefung(liste));
				liste.add((aufkl), t.get(0).toString());
				for (int i = zukl + 1; i > aufkl; i--) {
					liste.remove(i);
				}
			} else {
				ende = true;
			}
		}
		return liste;
	}

	private void InKlammer() {
		/*
		 * Hier werden um den kopletten Term Klammer hinzugefuegt, damit die
		 * Klammerpruefung richtig funktioniert
		 */
		Wort.add(0, "(");
		Wort.add(Wort.size(), ")");
		System.out.println(Wort.toString());
	}

	private Vector<String> KlammerPruefung(Vector w) {
		/*
		 * Die Klammerpruefung, hier wird nach der letzten ( gesusucht und die
		 * erste ), alles was dort drin steht wird in eine neue Liste kopiert
		 * und dort der inhalt nach zwei aufeinanderfolgenden Operatoren gesucht
		 */
		Vector<String> liste = null;
		int counter1 = 0, counter2 = 0;
		aufkl = 0;
		zukl = 0;
		for (int i = 0; i < w.size(); i++) {
			if (w.get(i) == "(") {
				counter1++;
				aufkl = i;
			} else if (w.get(i) == ")" & zukl == 0) {
				counter2++;
				zukl = i;
				break;
			}
		}
		if (counter1 != 0) {
			liste = new Vector<String>();
			/*
			 * Hier wird nach den zwei nacheinander folgenden Operatoren gesucht
			 */
			liste.add("0");
			liste.add("+");
			for (int i = aufkl + 1; i < zukl; i++) {
				/*
				 * neue Liste wird erstellt
				 */
				liste.add(w.get(i).toString());
			}
			for (int i = 0; i < liste.size() - 1; i++) {
				if (liste.get(i) == "+" && liste.get(i + 1) == "+") {
					liste.remove(i);
				} else if (liste.get(i) == "-" && liste.get(i + 1) == "-") {
					liste.remove(i);
					liste.remove(i);
					liste.add(i, "+");
				} else if ((liste.get(i) == "-" && liste.get(i + 1) == "+")
						|| (liste.get(i) == "+" && liste.get(i + 1) == "-")) {
					liste.remove(i);
					liste.remove(i);
					liste.add(i, "-");
				} else if ((liste.get(i) == "*" && liste.get(i + 1) == "-")
						|| (liste.get(i) == "/" && liste.get(i + 1) == "-")) {
					double a = Math.abs(Double.parseDouble(liste.get(i + 2)
							.toString()));
					a = -a;
					liste.remove(i + 1);
					liste.remove(i + 1);
					liste.add(i + 1, String.valueOf(a));
				} else if ((liste.get(i) == "*" && liste.get(i + 1) == "/")
						|| (liste.get(i) == "/" && liste.get(i + 1) == "*")) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Nicht definierte Operation im Term enthalten",
							null, JOptionPane.ERROR_MESSAGE);
				}
			}
			System.out.println(liste.toString());
		}
		return liste;
	}

	
	public int klammerCount(Vector<String> liste) {
		/*
		 * Klammer Prüfung, ob alle klammer die auf gehn auch zu gehen
		 */
		int counter = 0;
		for (int i = 0; i < liste.size(); i++) {

			if (liste.get(i) == "(") {
				counter++;
			} else if (liste.get(i) == ")") {
				counter--;
			}
		}
		return counter;
	}
	
	private Vector<String> Rechne(Vector<String> liste) {
		/*
		 * Aufruf der Rechenoperationen
		 */
		Vector<String> l = new Vector<String>();
		l = liste;
		l = re.OperatorMalGeteilt(l);
		l = re.OperatorPlusMinus(l);
		return l;
	}


}
