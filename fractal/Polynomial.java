// Class for storing a polynomial with complex coefficients

public class Polynomial {
    // Stores an array of complex numbers as coefficents
    Complex[] coeff;

    // Initialises polynomial as an array of complex numbers
    public Polynomial(Complex[] coeff) {
        int r = coeff.length - 1;

        while (r >= 0 && coeff[r].abs() == 0) {
            r--;
        }
        
        if (r < 0) {
            this.coeff = new Complex[1];
            this.coeff[0] = new Complex();
            return;
        } else {
            this.coeff = new Complex[r + 1];
            for (int i = 0; i <= r; i++) {
                this.coeff[i] = coeff[i];
            }
        }
    }

    // Initialise polynomial with a single zero valued coefficient if no array given
    public Polynomial() {
        this.coeff = new Complex[1];
        this.coeff[0] = new Complex();
    }

    // Returns a copy of the coefficient array
    public Complex[] getCoeff() {
        return coeff.clone();
    }

    // Returns string representation of polynomial
    public String toString() {
        String res = "";

        for (int i = 0; i < coeff.length; i++) {

            String cur = coeff[i].toString();
            String pow = "";

            if (i == 1) {
                pow = "z";
            } else if (i > 1) {
                pow = "z^" + i;
            }

            if (i > 0) {
                res += " + ";
            }

            res += "(" + cur + ")" + pow;
        }

        return res;
    }

    // Returns the degree of the polynomial
    public int degree() {
        return coeff.length - 1;
    }

    // Evaluates the polynomial at a given complex number
    public Complex evaluate(Complex z) {
        Complex res = new Complex(0, 0);
        Complex zPower = new Complex(1, 0);

        for (int i = 0; i < coeff.length; i++) {
            Complex term = coeff[i].multiply(zPower);
            res = res.add(term);
            
            // update zPower
            zPower = zPower.multiply(z);
        }

        return res;
    }

    // Tests
    public static void main(String[] args) {
        Complex[] coeff = new Complex[3];
        coeff[0] = new Complex(1, 2);
        coeff[1] = new Complex(2, 1);
        coeff[2] = new Complex(3, 1);
        Polynomial p = new Polynomial(coeff);

        Complex z = new Complex(3, 3);
        System.out.println("P(z) = " + p.toString());
        System.out.println("P(" + z.toString() + ") = " + p.evaluate(z).toString());
    }
}
