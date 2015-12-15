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
public enum Ddpf {

    DDPF_ALPHAPIXELS(0x1),
    DDPF_ALPHA(0x2),
    DDPF_FOURCC(0x4),
    DDPF_RGB(0x40),
    DDPF_YUV(0x200),
    DDPF_LUMINANCE(0x20000),
    DDPF_LUMINANCE_ALPHA(DDPF_LUMINANCE.value | DDPF_ALPHA.value),
    DDPF_FOURCC_ALPHAPIXELS(DDPF_FOURCC.value | DDPF_ALPHAPIXELS.value),
    DDPF_RGBAPIXELS(DDPF_RGB.value | DDPF_ALPHAPIXELS.value),
    DDPF_RGBA(DDPF_RGB.value | DDPF_ALPHA.value),
    DDPF_LUMINANCE_ALPHAPIXELS(DDPF_LUMINANCE.value | DDPF_ALPHAPIXELS.value);

    public final int value;

    private Ddpf(int value) {
        this.value = value;
    }

    private Ddpf(Ddpf ddpf) {
        value = ddpf.value;
    }
}
