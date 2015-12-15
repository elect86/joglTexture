/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture.spi.dx;

import java.nio.ByteBuffer;

/**
 *
 * @author elect
 */
public class DdsPixelFormat {

    public int size;
    public int flags;
    public D3dFormat fourCC;
    public int bpp;
    public int[] mask = new int[4];

    public static final int SIZEOF = (4 + 4) * Integer.BYTES;

    public DdsPixelFormat(ByteBuffer byteBuffer) {

        size = byteBuffer.getInt();
        flags = byteBuffer.getInt();
        fourCC = D3dFormat.get(byteBuffer.getInt());
        bpp = byteBuffer.getInt();
        for (int i = 0; i < mask.length; i++) {
            mask[i] = byteBuffer.getInt();
        }
    }
}
