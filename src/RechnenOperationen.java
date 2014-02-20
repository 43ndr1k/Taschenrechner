import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

				if(a>=0) {a = Math.sqrt(a);
				
				liste.remove(i);
				liste.add(i, String.valueOf(a));
				liste.remove(i +1);
				//liste.remove(i);
				i = 0;
				}
				else {
					{JOptionPane.showMessageDialog(new JFrame(),
						      "Im Bereich der reellen Zaheln ist die Wurzel nur für Zahlen größer gleich 0 definiert",
						      null, JOptionPane.ERROR_MESSAGE);	
					}
				}
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

				if (a<0){
				a = -Math.sin(Math.toRadians(Math.abs(a)));
				}
				else {
					a=Math.sin(Math.toRadians(a));	
					
				}
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
				if (a<0){
					a = -Math.cos(Math.toRadians(Math.abs(a)));
					}
					else {
						a=Math.cos(Math.toRadians(a));	
					}
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
				if (a<0){
					a = -Math.tan(Math.toRadians(Math.abs(a)));
					}
					else {
						a=Math.tan(Math.toRadians(a));	
					}
				
				liste.remove(i);
				liste.add(i, String.valueOf(Math.toRadians(a)));
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
				if (a==0)
				{JOptionPane.showMessageDialog(new JFrame(),
					      "Durch 0 darf nicht dividiert werden :P",
					      null, JOptionPane.ERROR_MESSAGE);	
				}
				else {
					a = (1/a);
					
					liste.remove(i);
					liste.add(i, String.valueOf(a));
					liste.remove(i +1);
					//liste.remove(i);
					i = 0;
				}
			}
		}

		return liste;
		
	}
	public Vector<String> Fakult(Vector<String> liste) {
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).equals("!")) {
				double a = (Double.parseDouble(liste.get(i-1).toString()));
				
				
				if (a>=0){
					double produkt=1;
					for(int c=1;c <= a ;c++){
						produkt*=c;
					}	
					
					liste.remove(i);
					liste.add(i, String.valueOf(produkt));
					liste.remove(i -1);
					//liste.remove(i);
					i = 0;
					}
					
				else if (a==0) {
				liste.remove(i);
				liste.add(i, String.valueOf(1));
				liste.remove(i -1);
				}
				else {
						JOptionPane.showMessageDialog(new JFrame(),
							      "Die Fakultät einer negativen Zahl ist nicht definiert",
							      null, JOptionPane.ERROR_MESSAGE);	
					}
				
				
			}
		}

		return liste;
		
	}
	

}
