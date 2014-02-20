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
	public Vector<String> quad(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("quad")) {
				double a = (Double.parseDouble(liste.get(i-1).toString()));
				a = Math.pow(a, 2);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i - 1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	public Vector<String> sqrt(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("sqrt")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = Math.sqrt(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	public Vector<String> log(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("log")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = Math.log10(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	public Vector<String> sin(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("sin")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = Math.sin(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}

	public Vector<String> cos(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("cos")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = Math.cos(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}

	public Vector<String> tan(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("tan")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = Math.tan(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	public Vector<String> Reziproke(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("Reziproke")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				a = (1/a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	public Vector<String> Fakult(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("Fakultät")) {
				double a = (Double.parseDouble(liste.get(i+1).toString()));
				double produkt=1;
				for(int c=1;c <= a ;i++){
					produkt=produkt*i;
				}	
				
				liste.remove(i);
				liste.add(i, String.valueOf(produkt));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
			}
		}

		return liste;
		
	}
	

}
