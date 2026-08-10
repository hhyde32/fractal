# Fractal
This project generates Newton fractal images from polynomials, using the secant 
algorithm to find roots.

## Visualisation
The following diagrams are of the polynomial $f(z) = z^3 - 1$ centered at $-2.0 + 2.0i$
with a width of $4$
![Fractal light diagram](images/fractal-light.png)
![Fractal dark diagram](images/fractal-dark.png)

## Explanation

The main interest of this project is to study the behavior of root convergence in the complex plane.
It involves coloring points on a grid according to what root of a complex polynomial it converges to using a secant algorithm.
It may be intuitive to think that each point converges to the root that is closest in distance to it but it is not the case.
As you can see it forms these beautiful patterns that are infinitely detailed.
