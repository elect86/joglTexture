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
public class DdsHeader10 {
    
    public DxgiFormat format;
    public D3d10ResourceDimension resourceDimension;
    public int miscFlag;    // D3D10_RESOURCE_MISC_GENERATE_MIPS
    public int arraySize;
    public int reserved;
    public static final int sizeOf = 5 * Integer.BYTES;

    public DdsHeader10() {
        format = DxgiFormat.DXGI_FORMAT_UNKNOWN;
        resourceDimension = D3d10ResourceDimension.D3D10_RESOURCE_DIMENSION_UNKNOWN;
        miscFlag = 0;
        arraySize = 0;
        reserved = 0;
    }

    public DdsHeader10(ByteBuffer byteBuffer) {
        format = DxgiFormat.get(byteBuffer.getInt());
        resourceDimension = D3d10ResourceDimension.values()[byteBuffer.getInt()];
        miscFlag = byteBuffer.getInt();
        arraySize = byteBuffer.getInt();
        reserved = byteBuffer.getInt();

        if (DdsHeader10.sizeOf != 20) {
            throw new Error("DDS DX10 Extended Header size mismatch");
        }
    }
}
