package edu.ucsb.cs56.ratcalc.model;

/**
 * A class to represent a rational number with a numerator and denominator
 *
 * @author P. Conrad for CS56 F16
 *
 */

public class Rational {



    private int num;

    private int denom;



    /** 

	greatest common divisor of a and b

	@param a first number

	@param b second number

	@return gcd of a and b

    */

    public static int gcd(int a, int b) {

	if (a==0)

	    return b;

	else if (b==0)

	    return a;

	else

	    return gcd(b%a, a);

    }

    

    public Rational() {

	this.num = 1;

	this.denom = 1;

    }



    public Rational(int num, int denom) {

	if (denom== 0) {

	    throw new IllegalArgumentException("denominator may not be zero");

	}

	this.num = num;

	this.denom = denom;

	if (num != 0) {

	    int gcd = Rational.gcd(num,denom);

	    this.num /= gcd;

	    this.denom /= gcd;

	}



    if (this.denom < 0){

        this.denom *= -1;

        this.num *= -1;

    }



    }



    public String toString() {

	if (denom == 1 || num == 0)

	    return "" + num;

	return num + "/" + denom;

    }



    public int getNumerator() { return this.num; }

    public int getDenominator() { return this.denom; }



    public Rational times(Rational r) {

	return new Rational(this.num * r.num,

			    this.denom * r.denom);

    }



    public static Rational product(Rational a, Rational b) {

	return new Rational(a.num * b.num,

			    a.denom * b.denom);

    }



    

    

    /** 

	For testing getters.  

	@param args unused

     */



    public static void main (String [] args) {

	Rational r = new Rational(5,7);

	System.out.println("r.getNumerator()=" + r.getNumerator());

	System.out.println("r.getDenominator()=" + r.getDenominator());

    }



    



    public Rational simplify () {

	int gcd = Rational.gcd(this.num, this.denom);

	this.num /= gcd;

	this.denom /= gcd;

    

	if (this.denom < 0){

        this.denom *= -1;

        this.num *= -1;

    }



    return this;

    }



    public static int lcm(int a, int b) {

	return Math.abs(a * b) / gcd(a, b);

    }



    public Rational plus(Rational r) {

	Rational a = new Rational();

	a.num = this.num*r.denom + r.num*this.denom;

	a.denom = this.denom*r.denom;

	return a.simplify();

    }



    public static Rational sum(Rational a, Rational b) {

	Rational r = new Rational();

	r.num = a.num*b.denom + b.num*a.denom;

	r.denom = a.denom*b.denom;

	return r.simplify();

    }



    public Rational minus(Rational r) {

	Rational a = new Rational();

        a.num = this.num*r.denom - r.num*this.denom;

        a.denom = this.denom*r.denom;

        return a.simplify();	

    }



    public static Rational difference(Rational a, Rational b) {

	Rational r = new Rational();

        r.num = a.num*b.denom - b.num*a.denom;

        r.denom = a.denom*b.denom;

        return r.simplify();

    }

	

    public Rational reciprocalOf() {

	if (this.num == 0){

        throw new ArithmeticException("denominator may not be zero");

	}

    int temp = this.num;

	this.num = this.denom;

	this.denom = temp;

	return this.simplify();

    }



    public Rational dividedBy(Rational r) {

	Rational n = r.reciprocalOf();

        this.num = n.num * this.num;

	this.denom = n.denom * this.denom;

	return this.simplify();

    }



    public static Rational quotient(Rational a, Rational b) {

	Rational n = product(a, b.reciprocalOf());

	return n.simplify();

    }





	    

}