public class Quantization {

    public static final int[][] Q = {
        {8,8,8,16,16,32,32,64},
        {8,8,8,16,16,32,32,64},
        {8,8,16,16,32,32,64,64},
        {16,16,16,32,32,64,64,64},
        {16,16,32,32,64,64,64,64},
        {32,32,32,64,64,64,64,64},
        {32,32,64,64,64,64,64,64},
        {64,64,64,64,64,64,64,64}
    };

    public static int[][] quantize(double[][] block) {
        int[][] q = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                q[i][j] = (int) Math.round(block[i][j] / Q[i][j]);
            }
        }
        return q;
    }

    public static double[][] dequantize(int[][] block) {
        double[][] dq = new double[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                dq[i][j] = block[i][j] * Q[i][j];
            }
        }
        return dq;
    }
}
