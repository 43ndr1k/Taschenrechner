import java.util.Vector;


public class RechnenOperationen {
	
	public Vector<String> OperatorMalGeteilt(Vector<String> liste) {
		/*
		 * Operationen Multiplikation und Division
		 */
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("*")) {
				double a = (Double.parseDouble(liste.get(i - 1).toString()))
						* (Double.parseDouble(liste.get(i + 1).toString()));
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i - 1);
				liste.remove(i);
				i = 0;
			}
			if (liste.get(i).equals("/")) {
				double a = (Double.parseDouble(liste.get(i - 1).toString()))
						/ (Double.parseDouble(liste.get(i + 1).toString()));
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i - 1);
				liste.remove(i);
				i = 0;
			}
		}
		System.out.println(liste.toString());
		return liste;
	}

	public Vector<String> OperatorPlusMinus(Vector<String> liste) {
		/*
		 * Die Operationen Addition und Divission
		 */
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("+")) {
				double a = (Double.parseDouble(liste.get(i - 1).toString()))
						+ (Double.parseDouble(liste.get(i + 1).toString()));
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i - 1);
				liste.remove(i);
				i = 0;
			}
			if (liste.get(i).equals("-")) {
				double a = (Double.parseDouble(liste.get(i - 1).toString()))
						- (Double.parseDouble(liste.get(i + 1).toString()));
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i - 1);
				liste.remove(i);
				i = 0;
			}
		}
		System.out.println(liste.toString());
		return liste;
	}

}
