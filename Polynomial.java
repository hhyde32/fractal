public class Polynomial {
    // class for storing a polynomial with complex coefficients
    Complex[] coeff;

    public Polynomial(Complex[] coeff) {
        // initialises polynomial given an array of complex numbers
        int r = coeff.length - 1;

        // removes trailing zero coefficients
        while (r >= 0 && coeff[r].abs() == 0) {
            r--;
        }
        
        // ititialise polynomial with a single zero valued coefficient if empty array given
        // otherwise    
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

    public Polynomial() {
        // ititialise polynomial with a single zero valued coefficient if no array given
        this.coeff = new Complex[1];
        this.coeff[0] = new Complex();
    }

    public Complex[] getCoeff() {
        // return a copy of the polynomials coefficients
        return coeff.clone();
    }

    public String toString() {
        // return string representation of polyniomial
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

    public int degree() {
        // returns degree of the polynomial
        return coeff.length - 1;
    }

    public Complex evaluate(Complex z) {
        // evaluates the polynomial at a given Complex number
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

    public static void main(String[] args) {
        // tests
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
