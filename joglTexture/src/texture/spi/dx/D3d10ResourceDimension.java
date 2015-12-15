/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture.spi.dx;

/**
 *
 * @author elect
 */
public enum D3d10ResourceDimension {

    D3D10_RESOURCE_DIMENSION_UNKNOWN(0),
    D3D10_RESOURCE_DIMENSION_BUFFER(1),
    D3D10_RESOURCE_DIMENSION_TEXTURE1D(2),
    D3D10_RESOURCE_DIMENSION_TEXTURE2D(3),
    D3D10_RESOURCE_DIMENSION_TEXTURE3D(4);

    public final int value;

    private D3d10ResourceDimension(int value) {
        this.value = value;
    }
}
