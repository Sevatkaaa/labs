package lab1;

public class DichotomyAndRelaxationCount {
    public static void main(String[] args) {
        System.out.println("Lab 1 by Vsevolod Reshetnikov, K-26\n");
        System.out.println("Solving equation x^5 - 5x + 2 = 0 using dichotomy method, eps = 1E-3");
        System.out.println("Assume that a = 0, b = 1, f(a) = 2, f(b) = -2");
        System.out.println("There is only 1 root on (0;1), because f' = 5*x^4 - 5 and f' < 0 on (0;1)\n");
        int counts = 0;
        float a = 0;
        float b = 1;
        float c = (a + b) / 2;
        float f = f(c);
        float eps = 0.001f;
        while (Math.abs(f) > eps) {
            if (f < 0) {
                b = c;
            } else {
                a = c;
            }
            c = (a + b) / 2;
            f = f(c);
            counts++;
        }
        System.out.println(String.format("Answer is: x = %.8f", c));
        System.out.println(String.format("f(x) = %.8f\n", f(c)));
        System.out.println("Total number of iterations (a posteriori estimate): " + counts);
        System.out.println("Theoretical minimum number of iterations (a priori estimate): " + getIterationCount(0, 1, eps) + "\n\n");

        System.out.println("Solving equation x^5 - 5x + 2 = 0 using relaxation method, eps = 1E-3");
        System.out.println(String.format("Assume that a = 0,3, b = 0,5, f(a) = %.5f, f(b) = %.5f", f(a), f(b)));
        System.out.println("There is only 1 root on (0,3; 0.5), because f' = 5*x^4 - 5 and f' < 0 on (0,3; 0,5)\n");
        System.out.println("f' = 5 * x^4 - 5 = 5 * (x^4 - 1)");
        counts = 0;
        a = 0.3f;
        b = 0.5f;
        System.out.println(String.format("min(f') = f'(0,3) = %.5f\nmax(f') = f'(0,5) = %.5f", f1(a), f1(b)));
        System.out.println("m = 4,68, M = 4,96");
        float m = 4.68f;
        float Mm = 4.96f;
        float t = 2.0f / (m + Mm);
        System.out.println(String.format("t(opt) = 2 / (m + M) = %.5f", t));
        System.out.println("-2 < t * f'(x) < 0, because f'(x) < 0 and -2/t = " + -2/t + " < -4.96 < f'(x)\n");
        System.out.println("Now we start solving, choose x0 = b = 0.5");
        float x = b;
        f = f(x);
        while (Math.abs(f) > eps) {
            x = x + t * f(x);
            f = f(x);
            counts++;
        }
        System.out.println(String.format("Answer is: x = %.8f", x));
        System.out.println(String.format("f(x) = %.8f\n", f));
        System.out.println("Total number of iterations (a posteriori estimate): " + counts + "\n");
        System.out.println("z0 = x0 - x* = 0.5 - " + x + " = " + (0.5 - x));
        System.out.println("z0 / eps = " + (0.5 - x) / eps);
        float q = (Mm - m) / (Mm + m);
        System.out.println("q = (M - m) / (M + m) = " + q);
        System.out.println("1 / q = " + 1 / q);
        double count = Math.log((0.5 - x) / eps) / Math.log(1 / q);
        System.out.println("(ln(z0 / eps)) / (ln (1 / q)) = " + count + "\n");
        System.out.println("Theoretical minimum number of iterations (a priori estimate): " + ((int) count + 1));
    }

    private static float f(float x) {
        return (float) (Math.pow(x, 5) - 5 * x + 2);
    }

    private static float f1(float x) {
        return (float) (5 * Math.pow(x, 4) - 5);
    }

    private static int getIterationCount(int a, int b, float eps) {
        return (int) (Math.log((b - a) / eps) / Math.log(2)) + 1;
    }
}
