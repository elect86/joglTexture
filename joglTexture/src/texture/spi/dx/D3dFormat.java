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
public enum D3dFormat {

    D3DFMT_UNKNOWN(0),
    //        
    D3DFMT_R8G8B8(20),
    D3DFMT_A8R8G8B8(21),
    D3DFMT_X8R8G8B8(22),
    D3DFMT_R5G6B5(23),
    D3DFMT_X1R5G5B5(24),
    D3DFMT_A1R5G5B5(25),
    D3DFMT_A4R4G4B4(26),
    D3DFMT_R3G3B2(27),
    D3DFMT_A8(28),
    D3DFMT_A8R3G3B2(29),
    D3DFMT_X4R4G4B4(30),
    D3DFMT_A2B10G10R10(31),
    D3DFMT_A8B8G8R8(32),
    D3DFMT_X8B8G8R8(33),
    D3DFMT_G16R16(34),
    D3DFMT_A2R10G10B10(35),
    D3DFMT_A16B16G16R16(36),
    //        
    D3DFMT_A8P8(40),
    D3DFMT_P8(41),
    //        
    D3DFMT_L8(50),
    D3DFMT_A8L8(51),
    D3DFMT_A4L4(52),
    //        
    D3DFMT_V8U8(60),
    D3DFMT_L6V5U5(61),
    D3DFMT_X8L8V8U8(62),
    D3DFMT_Q8W8V8U8(63),
    D3DFMT_V16U16(64),
    D3DFMT_A2W10V10U10(67),
    //        
    D3DFMT_UYVY(makeFourCC('U', 'Y', 'V', 'Y')),
    D3DFMT_R8G8_B8G8(makeFourCC('R', 'G', 'B', 'G')),
    D3DFMT_YUY2(makeFourCC('Y', 'U', 'Y', '2')),
    D3DFMT_G8R8_G8B8(makeFourCC('G', 'R', 'G', 'B')),
    D3DFMT_DXT1(makeFourCC('D', 'X', 'T', '1')),
    D3DFMT_DXT2(makeFourCC('D', 'X', 'T', '2')),
    D3DFMT_DXT3(makeFourCC('D', 'X', 'T', '3')),
    D3DFMT_DXT4(makeFourCC('D', 'X', 'T', '4')),
    D3DFMT_DXT5(makeFourCC('D', 'X', 'T', '5')),
    //
    D3DFMT_ATI1(makeFourCC('A', 'T', 'I', '1')),
    D3DFMT_AT1N(makeFourCC('A', 'T', '1', 'N')),
    D3DFMT_ATI2(makeFourCC('A', 'T', 'I', '2')),
    D3DFMT_AT2N(makeFourCC('A', 'T', '2', 'N')),
    //
    D3DFMT_ETC(makeFourCC('E', 'T', 'C', ' ')),
    D3DFMT_ETC1(makeFourCC('E', 'T', 'C', '1')),
    D3DFMT_ATC(makeFourCC('A', 'T', 'C', ' ')),
    D3DFMT_ATCA(makeFourCC('A', 'T', 'C', 'A')),
    D3DFMT_ATCI(makeFourCC('A', 'T', 'C', 'I')),
    //
    D3DFMT_POWERVR_2BPP(makeFourCC('P', 'T', 'C', '2')),
    D3DFMT_POWERVR_4BPP(makeFourCC('P', 'T', 'C', '4')),
    //
    D3DFMT_D16_LOCKABLE(70),
    D3DFMT_D32(71),
    D3DFMT_D15S1(73),
    D3DFMT_D24S8(75),
    D3DFMT_D24X8(77),
    D3DFMT_D24X4S4(79),
    D3DFMT_D16(80),
    //
    D3DFMT_D32F_LOCKABLE(82),
    D3DFMT_D24FS8(83),
    //
    D3DFMT_L16(81),
    //
    D3DFMT_VERTEXDATA(100),
    D3DFMT_INDEX16(101),
    D3DFMT_INDEX32(102),
    //
    D3DFMT_Q16W16V16U16(110),
    //
    D3DFMT_MULTI2_ARGB8(makeFourCC('M', 'E', 'T', '1')),
    //
    D3DFMT_R16F(111),
    D3DFMT_G16R16F(112),
    D3DFMT_A16B16G16R16F(113),
    //
    D3DFMT_R32F(114),
    D3DFMT_G32R32F(115),
    D3DFMT_A32B32G32R32F(116),
    //
    D3DFMT_CxV8U8(117),
    //
    D3DFMT_DX10(makeFourCC('D', 'X', '1', '0')),
    //
    D3DFMT_GLI1(makeFourCC('G', 'L', 'I', '1')),
    //                
    D3DFMT_FORCE_DWORD(0x7fffffff);

    public final int value;

    private D3dFormat(int value) {
        this.value = value;
    }

    public static int makeFourCC(char ch0, char ch1, char ch2, char ch3) {
        return (((int) ch0)) | (((int) ch1) << 8) | (((int) ch2) << 16) | (((int) ch3) << 24);
    }

    public static D3dFormat get(int value) {
        for (D3dFormat d3dFormat : values()) {
            if (d3dFormat.value == value) {
                return d3dFormat;
            }
        }
        return D3DFMT_UNKNOWN;
    }
}
