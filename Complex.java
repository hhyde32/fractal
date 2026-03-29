/*
 * NAME: Hedley Hyde
 * UNIVERSITY ID: 5700617
 * DEPARTMENT: Mathematics
 */

public class Complex {
  private double x;
  private double y;

  public Complex(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Complex(double x) {
    this.x = x;
    this.y = 0;
  }

  public Complex() {
    this.x = 0;
    this.y = 0;
  }

  public double getReal() {
    return x;
  }

  public double getImag() {
    return y;
  }

  public void setReal(double x) {
    this.x = x;
  }

  public void setImag(double y) {
    this.y = y;
  }

  public String toString() {
    return String.format("%.3f%s%.3fi", x, (y < 0.0 ? "-" : "+"), Math.abs(y));
  }

  public double abs2() {
    return x * x + y * y;
  }

  public double abs() {
    double abs2 = abs2();
    return Math.sqrt(abs2);
  }

  public Complex conjugate() {
    return new Complex(x, -y);
  }

  public Complex add(Complex b) {
    return new Complex(this.x + b.x, this.y + b.y);
  }

  public Complex negate() {
    return new Complex(-this.x, -this.y);
  }

  public Complex multiply(double alpha) {
    return new Complex(alpha * this.x, alpha * this.y);
  }

  public Complex multiply(Complex b) {
    return new Complex(this.x * b.x - this.y * b.y, this.x * b.y + this.y * b.x);
  }

  public Complex divide(Complex b) {
    double denom = b.abs2();
    return new Complex(
        (this.x * b.x + this.y * b.y) / denom, (this.y * b.x - this.x * b.y) / denom);
  }

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
