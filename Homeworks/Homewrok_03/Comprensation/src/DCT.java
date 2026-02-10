public class DCT {

    public static double[][] dct(double[][] block) {
        double[][] result = new double[8][8];

        for (int u = 0; u < 8; u++) {
            for (int v = 0; v < 8; v++) {
                double sum = 0;

                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        sum += block[x][y] *
                                Math.cos((2*x+1)*u*Math.PI/16) *
                                Math.cos((2*y+1)*v*Math.PI/16);
                    }
                }

                double cu = (u == 0) ? 1 / Math.sqrt(2) : 1;
                double cv = (v == 0) ? 1 / Math.sqrt(2) : 1;

                result[u][v] = 0.25 * cu * cv * sum;
            }
        }
        return result;
    }

    public static double[][] idct(double[][] block) {
        double[][] result = new double[8][8];

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                double sum = 0;

                for (int u = 0; u < 8; u++) {
                    for (int v = 0; v < 8; v++) {
                        double cu = (u == 0) ? 1 / Math.sqrt(2) : 1;
                        double cv = (v == 0) ? 1 / Math.sqrt(2) : 1;

                        sum += cu * cv * block[u][v] *
                                Math.cos((2*x+1)*u*Math.PI/16) *
                                Math.cos((2*y+1)*v*Math.PI/16);
                    }
                }
                result[x][y] = 0.25 * sum;
            }
        }
        return result;
    }
}
