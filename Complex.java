public class Complex {
    // Class for representing complex numbers.
    private double x;
    private double y;

    public Complex(double x, double y) {	
        // Initialise real and complex values.
        this.x = x;
        this.y = y;
    }

    public Complex(double x) {
        // Set complex to zero if one value is given.
        this.x = x;
        this.y = 0;
    }

    public Complex() {
        // Set real and complex to zero if no values given.
        this.x = 0;
        this.y = 0;
    }

    public double getReal() {
        // Get the real part of the complex number.
        return x;
    }

    public double getImag() {
        // Get the imaginary part of the complex number.
        return y;
    }

    public void setReal(double x) {
        // Set the real part of the complex number.
        this.x = x;
    }

    public void setImag(double y) {
        // Set the imaginary part of the complex number.
        this.y = y;
    }

    public String toString() {
        // Generate string representation of complex number.
        return String.format("%.3f%s%.3fi", x, (y < 0.0 ? "-" : "+"), Math.abs(y));
    }

    public double abs2() {
        // Calculate the squared absolute value.
        return x * x + y * y;
    }

    public double abs() {
        // Calculate the absolute value.
        double abs2 = abs2();
        return Math.sqrt(abs2);
    }

    public Complex conjugate() {
        // Calculate the complex conjugate.
        return new Complex(x, -y);
    }

    public Complex add(Complex b) {
        // Add another complex number.
        return new Complex(this.x + b.x, this.y + b.y);
    }

    public Complex negate() {
        // Negate the complex number.
        return new Complex(-this.x, -this.y);
    }

    public Complex multiply(double alpha) {
        // Multiply the complex number by a constant.
        return new Complex(alpha * this.x, alpha * this.y);
    }

    public Complex multiply(Complex b) {
        // Multiply the complex number by another complex number.
        return new Complex(this.x * b.x - this.y * b.y, this.x * b.y + this.y * b.x);
    }

    public Complex divide(Complex b) {
        // Divide the complex number by another complex number.
        double denom = b.abs2();
        return new Complex(
                (this.x * b.x + this.y * b.y) / denom, (this.y * b.x - this.x * b.y) / denom);
    }

    public static void main(String[] args) {
        // Tests.
        Complex A = new Complex(2.0, 2.0);
        Complex B = new Complex(1.0);
        Complex C = new Complex();

        System.out.println("Constructor test:");
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());

        System.out.println();
        System.out.println("Setting imag(C) = real(C) + 1:");
        C.setImag(C.getReal() + 1.0);
        System.out.println("C = " + C.toString());

        System.out.println();
        System.out.println("Testing operators:");
        System.out.println("abs(A)   = " + A.abs());
        System.out.println("abs2(A)  = " + A.abs2());
        System.out.println("abs(B)   = " + B.abs());
        System.out.println("abs2(B)  = " + B.abs2());
        System.out.println("conj(A)  = " + A.conjugate());
        System.out.println("conj(B)  = " + B.conjugate());
        System.out.println("neg(A)   = " + A.negate());
        System.out.println("neg(B)   = " + B.negate());
        System.out.println("A+B      = " + A.add(B));
        System.out.println("A+C      = " + A.add(C));
        System.out.println("2*A      = " + A.multiply(2.0));
        System.out.println("A*C      = " + A.multiply(C));
        System.out.println("B*C      = " + B.multiply(C));
        System.out.println("A*A      = " + A.multiply(A));
        System.out.println("A/B      = " + A.divide(B));
        System.out.println("A/C      = " + A.divide(C));
        System.out.println("A/A      = " + A.divide(A));

        System.out.println();
        System.out.println("Chained operators:");
        System.out.println("B*(A+C)    = " + B.multiply(A.add(C)));
        System.out.println("-A+B*C     = " + A.negate().add(B.multiply(C)));
    }
}
