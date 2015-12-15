/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture.spi;

import texture.spi.dx.DdsImage;
import com.jogamp.common.util.IOUtil;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLProfile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import texture.ImageType;
import texture.TextureData;

/**
 * DDS image provider
 *
 * @author elect
 */
public class DDSTextureProvider implements TextureProvider {

    private static final ImageType[] imageTypes = new ImageType[]{new ImageType(ImageType.T_DDS)};

    @Override
    public final ImageType[] getImageTypes() {
        return imageTypes;
    }

    @Override
    public TextureData newTextureData(
            final GLProfile glp,
            final InputStream stream,
            final int internalFormat,
            final int pixelFormat,
            final boolean mipmap,
            final String fileSuffix
    ) throws IOException {

        if (ImageType.T_DDS.equals(fileSuffix)
                || ImageType.T_DDS.equals(ImageType.Util.getFileSuffix(stream))) {
            
            final byte[] data = IOUtil.copyStream2ByteArray(stream);
            final ByteBuffer buf = ByteBuffer.wrap(data);
            final DdsImage image = DdsImage.read(buf);
            return newTextureData(glp, image, internalFormat, pixelFormat, mipmap);
        }

        return null;
    }

    private TextureData newTextureData(final GLProfile glp, final com.jogamp.opengl.util.texture.spi.DDSImage image,
            int internalFormat,
            int pixelFormat,
            boolean mipmap) {
        final com.jogamp.opengl.util.texture.spi.DDSImage.ImageInfo info = image.getMipMap(0);
        if (pixelFormat == 0) {
            switch (image.getPixelFormat()) {
                case com.jogamp.opengl.util.texture.spi.DDSImage.D3DFMT_R8G8B8:
                    pixelFormat = GL.GL_RGB;
                    break;
                default:
                    pixelFormat = GL.GL_RGBA;
                    break;
            }
        }
        if (info.isCompressed()) {
            switch (info.getCompressionFormat()) {
                case com.jogamp.opengl.util.texture.spi.DDSImage.D3DFMT_DXT1:
                    internalFormat = GL.GL_COMPRESSED_RGB_S3TC_DXT1_EXT;
                    break;
                case com.jogamp.opengl.util.texture.spi.DDSImage.D3DFMT_DXT3:
                    internalFormat = GL.GL_COMPRESSED_RGBA_S3TC_DXT3_EXT;
                    break;
                case com.jogamp.opengl.util.texture.spi.DDSImage.D3DFMT_DXT5:
                    internalFormat = GL.GL_COMPRESSED_RGBA_S3TC_DXT5_EXT;
                    break;
                default:
                    throw new RuntimeException("Unsupported DDS compression format \""
                            + com.jogamp.opengl.util.texture.spi.DDSImage.getCompressionFormatName(info.getCompressionFormat()) + "\"");
            }
        }
        if (internalFormat == 0) {
            switch (image.getPixelFormat()) {
                case com.jogamp.opengl.util.texture.spi.DDSImage.D3DFMT_R8G8B8:
                    pixelFormat = GL.GL_RGB;
                    break;
                default:
                    pixelFormat = GL.GL_RGBA;
                    break;
            }
        }
        final TextureData.Flusher flusher = new TextureData.Flusher() {
            @Override
            public void flush() {
                image.close();
            }
        };
        TextureData data;
        if (mipmap && image.getNumMipMaps() > 0) {
            final Buffer[] mipmapData = new Buffer[image.getNumMipMaps()];
            for (int i = 0; i < image.getNumMipMaps(); i++) {
                mipmapData[i] = image.getMipMap(i).getData();
            }
            data = new TextureData(glp, internalFormat,
                    info.getWidth(),
                    info.getHeight(),
                    0,
                    pixelFormat,
                    GL.GL_UNSIGNED_BYTE,
                    info.isCompressed(),
                    true,
                    mipmapData,
                    flusher);
        } else {
            // Fix this up for the end user because we can't generate
            // mipmaps for compressed textures
            mipmap = false;
            data = new TextureData(glp, internalFormat,
                    info.getWidth(),
                    info.getHeight(),
                    0,
                    pixelFormat,
                    GL.GL_UNSIGNED_BYTE,
                    mipmap,
                    info.isCompressed(),
                    true,
                    info.getData(),
                    flusher);
        }
        return data;
    }
}
