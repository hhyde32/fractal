// Main class that generates the fractal images

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Project2 {
    private Secant iterator;
    private Complex origin;
    private double width;

    private ArrayList<Complex> roots;

    private Color[][] colours;

    private boolean colourIterations;

    private BufferedImage fractal;

    private Graphics2D g2;

    public static final int NUMPIXELS = 400;

    // Initialises the variables
    public Project2(Polynomial p, Complex origin, double width) {
        this.iterator = new Secant(p);
        this.width = width;
        this.origin = origin;
        setupFractal();
    }

    // Prints the roots 
    public void printRoots() {
        System.out.println(this.roots);
    }

    // Get roots
    public ArrayList<Complex> getRoots() {
        return roots;
    }

    public int index(Complex root) {
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i).add(root.negate()).abs() < Secant.TOL) {
                return i;
            }
        }
        return -1;
    }

    // Converts pixel positions to complex numbers starting from the origin
    public Complex pixelToComplex(int i, int j) {
        double dz = width / NUMPIXELS;
        return new Complex(origin.getReal() + i * dz, origin.getImag() - j * dz);
    }

    public void createFractal(boolean colourIterations) {

        this.colourIterations = colourIterations;
        this.roots = new ArrayList<Complex>();
        
        // interate over each pixel at position (j, k))
        for (int j = 0; j < NUMPIXELS; j++) {
            for (int k = 0; k < NUMPIXELS; k++) {
                // translate pixel to complex number
                Complex z = pixelToComplex(j, k);

                // use zero and this complex number as starting points to run though secant algorithm
                iterator.iterate(new Complex(), z);

                if (iterator.getError() != Secant.Error.OK) {
                    continue; // skip this pixel
                }

                Complex root = iterator.getRoot();
                int n = iterator.getNumIterations();

                int rootIndex = index(root);

                // check to see if root is found already
                if (rootIndex == -1) {
                    if (roots.size() < 5) {
                        roots.add(root);
                        rootIndex = roots.size() - 1;
                    } else {
                        return; // skip colouring if something goes wrong
                    }
                }

                colourPixel(j, k, rootIndex, n);
            }
        }
    }

    // Tests

    public static void main(String[] args) {
        Complex[] coeff =
            new Complex[] {
                new Complex(1.0, 1.0),
                new Complex(2.0, 2.0),
                new Complex(3.0, 3.0),
                new Complex(4.0, 4.0),
                new Complex(5.0, 5.0),
                new Complex(6.0, 6.0)
            };
        Polynomial p = new Polynomial(coeff);
        Project2 project = new Project2(p, new Complex(-2.0, 1.0), 2.0);

        project.createFractal(false);
        project.saveFractal("fractal-light.png");
        project.createFractal(true);
        project.saveFractal("fractal-dark.png");
    }


    // Sets up the fractal image
    private void setupFractal() {
        int i, j;

        if (iterator.getF().degree() < 3 || iterator.getF().degree() > 5)
            throw new RuntimeException("Degree of polynomial must be between 3 and 5 inclusive!");

        this.colours = new Color[5][Secant.MAXITER];
        this.colours[0][0] = Color.RED;
        this.colours[1][0] = Color.GREEN;
        this.colours[2][0] = Color.BLUE;
        this.colours[3][0] = Color.CYAN;
        this.colours[4][0] = Color.MAGENTA;

        for (i = 0; i < 5; i++) {
            float[] components = colours[i][0].getRGBComponents(null);
            float[] delta = new float[3];

            for (j = 0; j < 3; j++) delta[j] = 0.8f * components[j] / Secant.MAXITER;

            for (j = 1; j < Secant.MAXITER; j++) {
                float[] tmp = colours[i][j - 1].getRGBComponents(null);
                colours[i][j] = new Color(tmp[0] - delta[0], tmp[1] - delta[1], tmp[2] - delta[2]);
            }
        }

        fractal = new BufferedImage(NUMPIXELS, NUMPIXELS, BufferedImage.TYPE_INT_RGB);
        g2 = fractal.createGraphics();
    }

    // Colours a pixel in the image.
    private void colourPixel(int i, int j, int rootColour, int numIter) {
        if (colourIterations) g2.setColor(colours[rootColour][numIter - 1]);
        else g2.setColor(colours[rootColour][0]);
        g2.fillRect(i, j, 1, 1);
    }

    // Saves the fractal image to a file.
    public void saveFractal(String fileName) {
        try {
            File outputfile = new File(fileName);
            ImageIO.write(fractal, "png", outputfile);
        } catch (IOException e) {
            System.out.println("I got an error trying to save! Maybe you're out of space?");
        }
    }
}
