/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture;

import static com.jogamp.opengl.GL2ES3.*;

/**
 *
 * @author elect
 */
public class Format {

    public static final int INVALID = -1;
    
    public static int getBlockSize(int glFormat) {
        switch(glFormat) {
            case GL_R8:
            case GL_R8_SNORM:
                return 1;
        }
        return INVALID;
    }
}
