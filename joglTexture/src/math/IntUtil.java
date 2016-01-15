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
public class IntUtil {

    public static boolean equal(int[] a, int[] b) {

        if (a.length != b.length) {
            return false;
        }
        boolean equal = true;
        for (int i = 0; i < a.length; i++) {
            equal = equal && a[i] == b[i];
        }
        return equal;
    }
}
