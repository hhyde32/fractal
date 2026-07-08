// Class that performs the secant algorithm on a complex polynomial to calculate its roots

public class Secant {

    // Maximum iterations before the root finding algorithm throws an error
    public static final int MAXITER = 20;

    // The tolerance for which we assess whether a root is found
    public static final double TOL = 1.0e-10;

    // The polynomial that the alforithm is performed on 
    private Polynomial f;

    // The root of the polynomial
    private Complex root;

    // The number of interations performed before a root is found
    private int numIterations;

    enum Error {
        OK,
        ZERO,
        DNF
    };

    private Error err = Error.OK;

    // Initialises the object by passing a polynomial
    public Secant(Polynomial p) {
        this.f = p;
    }

    // Getter methods
    public Error getError() {
        return err;
    }

    public int getNumIterations() {
        return numIterations;
    }

    public Complex getRoot() {
        return root;
    }

    public Polynomial getF() {
        return f;
    }

    // Performs secant algorithm iteratively
    public void iterate(Complex z0, Complex z1) {

        // Initialise starting points
        Complex z0curr = z0;
        Complex z1curr = z1;
        int M = 1;
        err = Error.OK;

        // Continues until f(z) is close to zero or difference between terms is negligable 
        while (z1curr.add(z0curr.negate()).abs() > TOL && f.evaluate(z1curr).abs() > TOL) {

            Complex fz0 = f.evaluate(z0curr);
            Complex fz1 = f.evaluate(z1curr);

            Complex denom = fz1.add(fz0.negate());

            // Catch potential zero division error
            if (denom.abs() < TOL) {
                err = Error.ZERO;
                break;
            }

            Complex numer = fz1.multiply(z1curr.add(z0curr.negate()));
            Complex znext = z1curr.add(numer.divide(denom).negate());

            z0curr = z1curr;
            z1curr = znext;

            M++;

            // Stop running if current iteration exceeds max interation
            if (M >= MAXITER) {
                err = Error.DNF;
                break;
            }
        }

        // Set the root to the last value in the sequence
        // Set the number of iterations to M
        root = z1curr;
        numIterations = M;
    }

    // Tests
    public static void main(String[] args) {
        Complex[] coeff =
            new Complex[] {new Complex(-1.0, 0.0), new Complex(), new Complex(), new Complex(1.0, 0.0)};
        Polynomial p = new Polynomial(coeff);
        Secant s = new Secant(p);

        s.iterate(new Complex(), new Complex(1.0, 1.0));
        System.out.println(s.getNumIterations());
        System.out.println(p.toString());
        System.out.println(s.getRoot());
        System.out.println(s.getError());
    }
}
