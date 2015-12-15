package texture;

import java.nio.Buffer;

import com.jogamp.opengl.GLProfile;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.GLPixelBuffer.GLPixelAttributes;

/**
 * Represents the data for an OpenGL texture. This is separated from the notion
 * of a Texture to support things like streaming in of textures in a background
 * thread without requiring an OpenGL context to be current on that thread.
 *
 * @author Chris Campbell
 * @author Kenneth Russell
 * @author Sven Gothel
 */
public class TextureData {

    protected int target;
    protected int format;
    protected int[] dimensions;
    protected int[] swizzles;
    private int border;
    protected GLPixelAttributes pixelAttributes;
    private boolean dataIsCompressed;
    protected Buffer buffer; // the actual data...
    private Flusher flusher;
    protected int rowLength;
    protected int alignment; // 1, 2, or 4 bytes
    protected int size;
    protected int baseLayer;
    protected int maxLayer;
    protected int baseFace;
    protected int maxFace;
    protected int baseLevel;
    protected int maxLevel;
    private int layers;
    private int faces;
    private int levels;
    private int blockSize;
    private int[] blockCount;
    private short[] blockDimensions;

    public TextureData() {
        baseLevel = maxLevel = layers = 0;
        baseFace = maxFace = faces = 0;
        baseLevel = maxLevel = levels = 0;
        blockSize = 0;
        blockCount = new int[]{0, 0, 0};
        blockDimensions = new short[]{0, 0, 0};
        dimensions = new int[]{0, 0, 0};
    }

    public TextureData(final int target,
            final int format,
            final int[] dimensions,
            final int layers,
            final int faces,
            final int levels,
            final int pixelFormat,
            final int pixelType,
            final int[] swizzles,
            final Buffer buffer,
            final Flusher flusher) throws IllegalArgumentException {
        this(target, format, dimensions, layers, faces, levels,
                new GLPixelAttributes(pixelFormat, pixelType), swizzles, buffer, flusher);
    }

    public TextureData(final int target,
            final int format,
            final int[] dimensions,
            final int layers,
            final int faces,
            final int levels,
            final GLPixelAttributes pixelAttributes,
            final int[] swizzles,
            final Buffer buffer,
            final Flusher flusher) throws IllegalArgumentException {

        this.target = target;
        this.format = format;
        this.dimensions = dimensions;
        this.baseLayer = 0;
        this.maxLayer = layers - 1;
        this.layers = layers;
        this.baseFace = 0;
        this.maxFace = faces - 1;
        this.faces = faces;
        this.baseLevel = 0;
        this.maxLevel = levels - 1;
        this.levels = levels;
        this.pixelAttributes = pixelAttributes;
        this.swizzles = swizzles;
        this.buffer = buffer;
        this.flusher = flusher;

        alignment = 1;  // FIXME: is this correct enough in all situations?
        size = estimatedMemorySize(buffer);
    }

    /**
     * Returns the border in pixels of the texture data.
     */
    public int getBorder() {
        return border;
    }

    /**
     * Returns the intended OpenGL {@link GLPixelAttributes} of the texture
     * data, i.e. format and type.
     */
    public GLPixelAttributes getPixelAttributes() {
        return pixelAttributes;
    }

    /**
     * Returns the intended OpenGL pixel format of the texture data using
     * {@link #getPixelAttributes()}.
     */
    public int getPixelFormat() {
        return pixelAttributes.format;
    }

    /**
     * Returns the intended OpenGL pixel type of the texture data using
     * {@link #getPixelAttributes()}.
     */
    public int getPixelType() {
        return pixelAttributes.type;
    }

    /**
     * Returns the intended OpenGL internal format of the texture data.
     */
    public int getInternalFormat() {
        return format;
    }

    /**
     * Indicates whether the texture data is in compressed form.
     */
    public boolean isDataCompressed() {
        return dataIsCompressed;
    }

    /**
     * Returns the texture data, or null if it is specified as a set of mipmaps.
     */
    public Buffer getBuffer() {
        return buffer;
    }

    /**
     * Returns the required byte alignment for the texture data.
     */
    public int getAlignment() {
        return alignment;
    }

    /**
     * Returns the row length needed for correct GL_UNPACK_ROW_LENGTH
     * specification. This is currently only supported for non-mipmapped,
     * non-compressed textures.
     */
    public int getRowLength() {
        return rowLength;
    }

    /**
     * Sets the border in pixels of the texture data.
     */
    public void setBorder(final int border) {
        this.border = border;
    }

    /**
     * Sets the intended OpenGL pixel format of the texture data.
     */
    public void setPixelAttributes(final GLPixelAttributes pixelAttributes) {
        this.pixelAttributes = pixelAttributes;
    }

    /**
     * Sets the intended OpenGL pixel format component of
     * {@link GLPixelAttributes} of the texture data.
     * <p>
     * Use {@link #setPixelAttributes(GLPixelAttributes)}, if setting format and
     * type.
     * </p>
     */
    public void setPixelFormat(final int pixelFormat) {
        if (pixelAttributes.format != pixelFormat) {
            pixelAttributes = new GLPixelAttributes(pixelFormat, pixelAttributes.type);
        }
    }

    /**
     * Sets the intended OpenGL pixel type component of
     * {@link GLPixelAttributes} of the texture data.
     * <p>
     * Use {@link #setPixelAttributes(GLPixelAttributes)}, if setting format and
     * type.
     * </p>
     */
    public void setPixelType(final int pixelType) {
        if (pixelAttributes.type != pixelType) {
            pixelAttributes = new GLPixelAttributes(pixelAttributes.format, pixelType);
        }
    }

    /**
     * Sets the intended OpenGL internal format of the texture data.
     */
    public void setInternalFormat(final int internalFormat) {
        this.format = internalFormat;
    }

    /**
     * Sets whether the texture data is in compressed form.
     */
    public void setIsDataCompressed(final boolean compressed) {
        this.dataIsCompressed = compressed;
    }

    /**
     * Sets the texture data.
     */
    public void setBuffer(final Buffer buffer) {
        this.buffer = buffer;
        size = estimatedMemorySize(buffer);
    }

    /**
     * Sets the required byte alignment for the texture data.
     */
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
    }

    /**
     * Sets the row length needed for correct GL_UNPACK_ROW_LENGTH
     * specification. This is currently only supported for non-mipmapped,
     * non-compressed textures.
     */
    public void setRowLength(final int rowLength) {
        this.rowLength = rowLength;
    }

    /**
     * Returns an estimate of the amount of memory in bytes this TextureData
     * will consume once uploaded to the graphics card. It should only be
     * treated as an estimate; most applications should not need to query this
     * but instead let the OpenGL implementation page textures in and out as
     * necessary.
     */
    public int getSize() {
        return size;
    }

    /**
     * Flushes resources associated with this TextureData by calling
     * Flusher.flush().
     */
    public void flush() {
        if (flusher != null) {
            flusher.flush();
            flusher = null;
        }
    }

    /**
     * Calls flush()
     *
     * @see #flush()
     */
    public void destroy() {
        flush();
    }

    /**
     * Defines a callback mechanism to allow the user to explicitly deallocate
     * native resources (memory-mapped files, etc.) associated with a particular
     * TextureData.
     */
    public static interface Flusher {

        /**
         * Flushes any native resources associated with this TextureData.
         */
        public void flush();
    }

    @Override
    public String toString() {
        final String optImageType = null != srcImageType ? ", " + srcImageType : "";
        return "TextureData[" + width + "x" + height + ", y-flip " + mustFlipVertically + ", internFormat 0x" + Integer.toHexString(internalFormat) + ", "
                + pixelAttributes + ", border " + border + ", estSize " + size + ", alignment " + alignment + ", rowlen " + rowLength + optImageType;
    }
}
