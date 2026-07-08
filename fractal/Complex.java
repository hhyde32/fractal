// Basic class for storing complex numbers and performing elemtentary operations

public class Complex {
    // Variables to store the real and imaginary components 
    private double x;
    private double y;

    // Initialise real and imaginary parts when two values are given  		
    public Complex(double x, double y) {	
        this.x = x;
        this.y = y;
    }

    // Initialise imaginary part as zero if one value is given
    public Complex(double x) {
        this.x = x;
        this.y = 0;
    }

    // Initialise both real and complex parts as zero when no argument is given
    public Complex() {
        this.x = 0;
        this.y = 0;
    }

    // Method to access the real part of the complex number
    public double getReal() {
        return x;
    }

    // Method to access the imaginary part of the complex number
    public double getImag() {
        return y;
    }

    // Sets the real part of the complex number to a given value
    public void setReal(double x) {
        this.x = x;
    }

    // Sets the imaginary part of the complex number to a given value
    public void setImag(double y) {
        this.y = y;
    }

    // Returns the string representation of complex number
    public String toString() {
        return String.format("%.3f%s%.3fi", x, (y < 0.0 ? "-" : "+"), Math.abs(y));
    }

    // Method to calculate the squared absolute value
    public double abs2() {
        return x * x + y * y;
    }

    // Calculates the absolute value
    public double abs() {
        double abs2 = abs2();
        return Math.sqrt(abs2);
    }

    // Returns the complex conjugate
    public Complex conjugate() {
        return new Complex(x, -y);
    }
    
    // Adds the complex number to another complex number
    public Complex add(Complex b) {
        return new Complex(this.x + b.x, this.y + b.y);
    }

    // Returns the negaion of the complex number
    public Complex negate() {
        return new Complex(-this.x, -this.y);
    }

    // Multiplies the complex number by a scalar
    public Complex multiply(double alpha) {
        return new Complex(alpha * this.x, alpha * this.y);
    }

    // Multiplies the complex number by another complex number
    public Complex multiply(Complex b) {
        return new Complex(this.x * b.x - this.y * b.y, this.x * b.y + this.y * b.x);
    }

    // Divides the complex number by another complex number
    public Complex divide(Complex b) {
        double denom = b.abs2();
        return new Complex(
                (this.x * b.x + this.y * b.y) / denom, (this.y * b.x - this.x * b.y) / denom);
    }

    // Tests 
    public static void main(String[] args) {
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
