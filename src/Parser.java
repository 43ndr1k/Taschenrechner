import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Parser {
	private Vector<String> Wort = null;
	private int aufkl = 0, zukl = 0;
	private RechnenOperationen re = new RechnenOperationen();
	public Parser(Vector<String> v) {
		this.Wort = v;
	}

	public Parser() {

	}
	public String Ergebnis() {
		InKlammer();
		StringBuilder out = new StringBuilder();
		for (String txt : zusammenfuehrung()) {
			out.append(txt);
		}
		return out.toString();
	}
	private Vector<String> zusammenfuehrung() {
		boolean ende = false;
		Vector<String> liste = new Vector<String>();
		liste = Wort;
		liste = SyntaxTest(liste);
		if(!liste.isEmpty()){
			while (ende == false) {
				if (liste.get(0).equals("(")) {
					Vector<String> t = new Vector<>();
					t = Rechne(KlammerPruefung(liste));
					if(!t.isEmpty()){
						liste.add((aufkl), t.get(0).toString());
						for (int i = zukl + 1; i > aufkl; i--) {
							liste.remove(i);
						}
					}else{
						ende = true;
						JOptionPane.showMessageDialog(new JFrame(),
								"Falscher Ausdruck",
								null, JOptionPane.ERROR_MESSAGE);
						liste.clear();
					}
				} 
				else {
					ende = true;
				}
			}
		}
		return liste;
	}
	private void InKlammer() {
		Wort.add(0,"0");
		Wort.add(1,"+");
		Wort.add(0, "(");
		Wort.add(Wort.size(), ")");
	}
	private Vector<String> KlammerPruefung(Vector<String> w) {
		Vector<String> liste = null;
		int counter1 = 0;
		aufkl = 0;
		zukl = 0;
		for (int i = 0; i < w.size(); i++) {
			if (w.get(i) == "(") {
				counter1++;
				aufkl = i;
			} else if (w.get(i) == ")" & zukl == 0) {
				zukl = i;
				break;
			}
		}
		if (counter1 != 0) {
			liste = new Vector<String>();
			liste.add("0");
			liste.add("+");
			for (int i = aufkl + 1; i < zukl; i++) {
				liste.add(w.get(i).toString());
			}
		}
		return liste;
	}

	private Vector<String> SyntaxTest(Vector<String> liste){
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

			}	else if((liste.get(i).matches("\\d+([.]{1}\\d+)?") && liste.get(i + 1) == "(")
					|| (liste.get(i) == ")" && liste.get(i+1).matches("\\d+([.]{1}\\d+)?"))
					|| (liste.get(i) == ")" && liste.get(i+1) == "(")
					|| liste.get(i).matches("\\d+([.]{1}\\d+)?") && liste.get(i+1).matches("\\d+([.]{1}\\d+)?")){
				liste.add(i+1, "*");
			}
			else if ((liste.get(i) == "*" && liste.get(i + 1) == "/")
					|| (liste.get(i) == "/" && liste.get(i + 1) == "*") 
					|| (liste.get(i) =="*" && liste.get(i+1) == ")")
					|| (liste.get(i) =="/" && liste.get(i+1) == ")")
					|| (liste.get(i) =="+" && liste.get(i+1) == ")")
					|| (liste.get(i) =="-" && liste.get(i+1) == ")")
					|| (liste.get(i) =="/" && liste.get(i+1) == "0")
					|| (liste.get(i) =="+" && liste.get(i+1) == "/")
					|| (liste.get(i) =="+" && liste.get(i+1) == "*")) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Nicht definierte Operation im Term enthalten",
						null, JOptionPane.ERROR_MESSAGE);
				liste.clear();
				break;
			}
		}
		return liste;
	}
	static public boolean istEingeklammert(Vector<String> liste) {
		int laenge = liste.size();
		int position;
		int counter = 0;

		for (position = 0; position <= (laenge - 1); position++) {
			if (liste.get(position) == "(") {
				counter ++;
			}
			if (liste.get(position) == ")"){
				counter--;
			}
			if (counter < 0){              
				return false;
			}
		}
		if (counter != 0) {
			return false;
		}
		return true;
	}

	private Vector<String> Rechne(Vector<String> liste) {
		Vector<String> l = new Vector<String>();

		if(!liste.isEmpty() && liste.size()>2){
			l = liste;
			l = re.ln(l);
			l = re.sqrt(l);
			l = re.quad(l);
			l = re.sin(l);
			l = re.cos(l);
			l = re.tan(l);
			l = re.Reziproke(l);
			l = re.Fakult(l);
			l = re.OperatorMalGeteilt(l);
			l = re.OperatorPlusMinus(l);
		}
		else{
			liste.add("0");
		}
		return l;
	}  
}
