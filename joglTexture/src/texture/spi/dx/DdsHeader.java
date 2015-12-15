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
public class DdsHeader {

    public int magic;
    public int size;
    public int flags;
    public int height;
    public int width;
    public int pitch;
    public int depth;
    public int mipMapLevels;
    public int[] reserved1 = new int[11];
    public DdsPixelFormat format;
    public int surfaceFlags;
    public int cubemapFlags;
    public int[] reserved2 = new int[3];

    public static final int SIZEOF = 4 * Byte.BYTES + 7 * Integer.BYTES + 11 * Integer.BYTES
            + DdsPixelFormat.SIZEOF + 2 * Integer.BYTES + 3 * Integer.BYTES;

    public static class Offset {

        public static final int MAGIC = 0;
        public static final int SIZE = MAGIC + 4 * Byte.BYTES;
        public static final int FLAGS = SIZE + Integer.BYTES;
        public static final int HEIGHT = FLAGS + Integer.BYTES;
        public static final int WIDTH = HEIGHT + Integer.BYTES;
        public static final int PITCH = WIDTH + Integer.BYTES;
        public static final int DEPTH = PITCH + Integer.BYTES;
        public static final int MIPMAP_LEVELS = DEPTH + Integer.BYTES;
        public static final int RESERVED1 = MIPMAP_LEVELS + Integer.BYTES;
        public static final int FORMAT = RESERVED1 + 11 * Integer.BYTES;
        public static final int SURFACE_FLAGS = FORMAT + DdsPixelFormat.SIZEOF;
        public static final int CUBEMAP_FLAGS = SURFACE_FLAGS + Integer.BYTES;
        public static final int RESERVED2 = CUBEMAP_FLAGS + Integer.BYTES;
    }

    public DdsHeader(ByteBuffer byteBuffer) {

        magic = byteBuffer.getInt();
        size = byteBuffer.getInt();
        flags = byteBuffer.getInt();
        height = byteBuffer.getInt();
        width = byteBuffer.getInt();
        pitch = byteBuffer.getInt();
        depth = byteBuffer.getInt();
        mipMapLevels = byteBuffer.getInt();
        for (int i = 0; i < reserved1.length; i++) {
            reserved1[i] = byteBuffer.getInt();
        }
        format = new DdsPixelFormat(byteBuffer);
        surfaceFlags = byteBuffer.getInt();
        cubemapFlags = byteBuffer.getInt();
        for (int i = 0; i < reserved2.length; i++) {
            reserved2[i] = byteBuffer.getInt();
        }

        /**
         * G-truc has 124 as he doesn't count the initial word 'magic'.
         */
        if (SIZEOF != 128) {
            throw new Error("DDS Header size mismatch");
        }
    }
}
