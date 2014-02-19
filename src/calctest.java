import java.util.Vector;


import junit.*;
import junit.framework.TestCase;

public class calctest extends TestCase {

	// Teste die Methode createMatrix auf korrekte Implmentation

	public void testklammercount() {
		Vector<String> v = new Vector<String>();

		//test1 == error!
		v.add(")");
		v.add("(");
		v.add(")");
		v.add("(");
		v.add("-");
		v.add("3");
		assertNotSame(v.toString() + "  0 != " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
	
		v.add(")");
		v.add(")");
		v.add("(");
		v.add("(");
		assertNotSame(v.toString() + "  0 != " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
		
		v.add(")");
		v.add("(");
		v.add("(");
		v.add("(");
		assertNotSame(v.toString() + "  0 != " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();

		v.add(")");
		v.add(")");
		v.add("(");
		v.add(")");
		assertNotSame(v.toString() + "  0 != " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
		
		v.add("(");
		v.add("22");
		v.add("-");
		v.add("222");
		v.add(")");
		v.add("(");
		v.add("2");
		v.add("*");
		v.add("(");
		v.add("3");
		v.add("-");
		v.add("3");
		v.add("(");
		v.add(")");
		assertNotSame(v.toString() + "  0 != " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
		

		//test1 == 0
		v.add("(");
		v.add("5");
		v.add("-");
		v.add("3");
		v.add(")");
		v.add("-");
		v.add("3");
		assertEquals(v.toString() + "  0 == " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
		
		v.add("(");
		v.add("(");
		v.add("5");
		v.add("-");
		v.add("3");
		v.add(")");
		v.add("*");
		v.add("2");
		v.add(")");
		v.add("-");
		v.add("3");
		assertEquals(v.toString() + "  0 == " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
		
		v.add("(");
		v.add("22");
		v.add("-");
		v.add("222");
		v.add(")");
		v.add("(");
		v.add("2");
		v.add("*");
		v.add("(");
		v.add("3");
		v.add("-");
		v.add("3");
		v.add(")");
		v.add(")");
		assertEquals(v.toString() + "  0 == " + Parser.klammerCount(v),0,Parser.klammerCount(v));
		v.clear();
	}
	
	public void testcalc() {
		Vector<String> v = new Vector<String>();
		Parser p = null;

		v.add("(");
		v.add("22");
		v.add("-");
		v.add("222");
		v.add(")");
		v.add("(");
		v.add("2");
		v.add("*");
		v.add("(");
		v.add("3");
		v.add("-");
		v.add("3");
		v.add(")");
		v.add(")");
	    p = new Parser(v);
		assertEquals(v.toString(),"0.0",p.Ergebnis());
		v.clear();
		
		v.add("(");
		v.add("22");
		v.add("-");
		v.add("222");
		v.add(")");
		v.add("*");
		v.add("(");
		v.add("2");
		v.add("*");
		v.add("(");
		v.add("3");
		v.add("-");
		v.add("3");
		v.add(")");
		v.add(")");
		p = new Parser(v);
		assertEquals(v.toString(),"0.0",p.Ergebnis());
		v.clear();

		v.add("(");
		v.add("4");
		v.add("-");
		v.add("2");
		v.add(")");
		v.add("*");
		v.add("3");
		p = new Parser(v);
		assertEquals(v.toString(),"6.0",p.Ergebnis());
		v.clear();

		v.add("(");
		v.add("4.4");
		v.add("-");
		v.add("2.1");
		v.add(")");
		v.add("*");
		v.add("3");
		p = new Parser(v);
		assertEquals(v.toString(),"6.9",p.Ergebnis());
		v.clear();
		
		v.add("14");
		v.add("/");
		v.add("2");
		v.add("(");
		v.add("7");
		v.add("-");
		v.add("3.5");
		v.add(")");
		p = new Parser(v);
		assertEquals(v.toString(),"24.5",p.Ergebnis());
		v.clear();
		
		v.add("0");
		v.add("/");
		v.add("1");
		p = new Parser(v);
		assertEquals(v.toString(),"0.0",p.Ergebnis());
		v.clear();
		
		v.add("1");
		v.add("/");
		v.add("0");
		p = new Parser(v);
		assertEquals(v.toString(),"Infinity",p.Ergebnis());
		v.clear();

	}	
}
