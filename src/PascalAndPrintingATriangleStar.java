public class PascalAndPrintingATriangleStar {
    public static void main(String[] args) {
        int n =5;
        printStarWithSpace(n);
        printStarWithNumber(n);
        pascalTriangle(n);
    }

    public int factorial(int i)
    {
        if (i == 0)
            return 1;
        return i * factorial(i - 1);
    }

    private static void pascalTriangle(int n) {
        PascalAndPrintingATriangleStar p = new PascalAndPrintingATriangleStar();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(" " + (p.factorial(i) / (p.factorial(i - j) * p.factorial(j))));
            }
            System.out.println("\n");
        }
    }

    private static void printStarWithNumber(int n) {
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=i;j++) {
                System.out.print(j);
            }
            System.out.println("\n");
        }
    }

    private static void printStarWithSpace(int i) {

        for(int j=0;j<i;j++) {
            for(int k=0;k<i-j+1;k++) {
                System.out.print(" ");
            }
            for(int k=0;k<2*j+1;k++) {
                System.out.print("*");
            }
            System.out.println("\n");
        }
    }
}
