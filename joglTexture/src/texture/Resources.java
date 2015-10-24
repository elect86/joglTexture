/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture;

import com.jogamp.opengl.GL;

/**
 *
 * @author elect
 */
public class Resources {

    public enum Format {

        GL_R8(GL.GL_R8);

        public final int value;

        private Format(int value) {
            this.value = value;
        }
    }
}
