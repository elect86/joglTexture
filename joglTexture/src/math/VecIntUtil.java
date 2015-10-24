/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author elect
 */
public class VecIntUtil {

    /**
     * Return true if both vectors are equal w/o regarding an epsilon.
     */
    public static boolean isEqual(final float[] vec1, final float[] vec2) {
        for (int i = 0; i < vec1.length; i++) {
            if (vec1[i] != vec2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Divides a vector by param using given result int[], result = vector /
     * scale
     *
     * @param result vector for the result, may be vector (in-place)
     * @param vector input vector
     * @param scale 3 component scale constant for each vector component
     * @return result vector for chaining
     */
    public static int[] div(final int[] result, final int[] vector, final int[] scale) {
        for (int i = 0; i < result.length; i++) {
            result[i] = vector[i] / scale[i];
        }
        return result;
    }

    public static int[] max(final int[] result, final int[] vec1, final int[] vec2) {
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(vec1[i], vec2[i]);
        }
        return result;
    }

    public static boolean greaterThan(int[] vec1, int[] vec2) {
        for (int i = 0; i < vec1.length; i++) {
            if (vec1[i] <= vec2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int compMul(int[] vec) {
        int result = 1;
        for (int i = 0; i < vec.length; i++) {
            result *= vec[i];
        }
        return result;
    }

    public static int[] shiftRight(int[] result, int[] vec, int shift) {
        for (int i = 0; i < result.length; i++) {
            result[i] = vec[i] >> shift;
        }
        return result;
    }
}
