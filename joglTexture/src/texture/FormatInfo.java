/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture;

/**
 *
 * @author elect
 */
public class FormatInfo {

    public int internal;
    public int external;
    public int type;
    public byte blockSize;
    public short[] blockDimensions;
    public byte component;
    public int[] swizzles;
    public short flags;

    public enum Cap {

        CAP_COMPRESSED_BIT((short) (1 << 0)),
        CAP_PACKED_BIT((short) (1 << 1)),
        CAP_NORMALIZED_BIT((short) (1 << 2)),
        CAP_SCALED_BIT((short) (1 << 3)),
        CAP_UNSIGNED_BIT((short) (1 << 4)),
        CAP_SIGNED_BIT((short) (1 << 5)),
        CAP_INTEGER_BIT((short) (1 << 6)),
        CAP_FLOAT_BIT((short) (1 << 7)),
        CAP_DEPTH_BIT((short) (1 << 8)),
        CAP_STENCIL_BIT((short) (1 << 9)),
        CAP_COLORSPACE_SRGB_BIT((short) (1 << 10)),
        CAP_SWIZZLE_BIT((short) (1 << 11)),
        CAP_LUMINANCE_ALPHA_BIT((short) (1 << 12));

        public final int value;

        private Cap(short value) {
            this.value = value;
        }
    }
}
