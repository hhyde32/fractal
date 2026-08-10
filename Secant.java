public class Secant {
    // Class to performs secant algorithm.

    public static final int MAXITER = 20;
    public static final double TOL = 1.0e-10;
    private Polynomial f;
    private Complex root;
    private int numIterations;

    enum Error {
        OK,
        ZERO,
        DNF
    };

    private Error err = Error.OK;

    public Secant(Polynomial p) {
        // Initialise the polynomial.
        this.f = p;
    }

    public Error getError() {
        // Get error.
        return err;
    }

    public int getNumIterations() {
        // Get the number of iterations needed to find the root.
        return numIterations;
    }

    public Complex getRoot() {
        // Get the root the algorithm converges to.
        return root;
    }

    public Polynomial getF() {
        // Get the polynomial.
        return f;
    }

    public void iterate(Complex z0, Complex z1) {
        // Perform secant algorithm. 

        // Initialise starting points.
        Complex z0curr = z0;
        Complex z1curr = z1;
        int M = 1;
        err = Error.OK;

        // Continues until f(z) is close to zero or difference between terms is negligable.
        while (z1curr.add(z0curr.negate()).abs() > TOL && f.evaluate(z1curr).abs() > TOL) {

            Complex fz0 = f.evaluate(z0curr);
            Complex fz1 = f.evaluate(z1curr);

            Complex denom = fz1.add(fz0.negate());

            // Catch potential zero division error.
            if (denom.abs() < TOL) {
                err = Error.ZERO;
                break;
            }

            Complex numer = fz1.multiply(z1curr.add(z0curr.negate()));
            Complex znext = z1curr.add(numer.divide(denom).negate());

            z0curr = z1curr;
            z1curr = znext;

            M++;

            // Stop running if current iteration exceeds max interation.
            if (M >= MAXITER) {
                err = Error.DNF;
                break;
            }
        }

        root = z1curr;
        numIterations = M;
    }

    public static void main(String[] args) {
        // Tests.
        Complex[] coeff = new Complex[] {new Complex(-1.0, 0.0), new Complex(), new Complex(), new Complex(1.0, 0.0)};
        Polynomial p = new Polynomial(coeff);
        Secant s = new Secant(p);

        s.iterate(new Complex(), new Complex(1.0, 1.0));
        System.out.println(s.getNumIterations());
        System.out.println(p.toString());
        System.out.println(s.getRoot());
        System.out.println(s.getError());
    }
}
