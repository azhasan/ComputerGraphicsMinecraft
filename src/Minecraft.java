/**
 * File:            Minecraft.java 
 * Team:            Depressed Dinos
 * Author:          Annie Wu, Aatena Hasan 
 * Class:           CS 4450 - Computer Graphics
 *                  
 * Assignment:      Final Program 
 * Date:            21 April 2019 
 *                  
 * Purpose:         Create a window and initialize the graphics. 
 *                  
 */

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Minecraft {
    
    private static DisplayMode displayMode;
    private static CameraController cam;
    public static FloatBuffer lightPosition;
    public static FloatBuffer whiteLight;
    
    /**
     * Method: start
     * Purpose: Run the methods we need to draw with OpenGL
     */
    public static void start() {
        
        // Initialize our instance of our camera controller inside start method
        try {
            createWindow();
            initGL();
            cam = new CameraController(0f, 0f, 0f);
            cam.gameLoop(); //render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method: createWindow
     * Purpose: Create a 800x600 window
     */
    private static void createWindow() throws Exception {
        Display.setFullscreen(false);   // Not fullscreen
        DisplayMode d[] = Display.getAvailableDisplayModes();
        
        for (int i = 0; i < d.length; i++) {
            if(d[i].getWidth() == 800 && d[i].getHeight() == 600 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;  
            }
        }
        
        Display.setDisplayMode(displayMode); 
        Display.setTitle("DEPRESSED DINOS DEPRESSED DINOS DEPRESSED DINOS");  
        Display.create();  
    }
    
    /**
     * Method: initGL
     * Purpose: Initialize the graphics
     */
    private static void initGL() {
        
        glClearColor(0.6f, 0.8f, 1.0f, 0.0f);           // sky blue color
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        
        // Block Data
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_DEPTH_TEST);
        
        // Texture Mapping
        glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
        
        // Light Source
        initLightArrays();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition); // sets light’s position
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);    // sets specular light
        glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);     // sets diffuse light
        glLight(GL_LIGHT0, GL_AMBIENT, whiteLight);     // sets ambient light
        glEnable(GL_LIGHTING);                          // enables lighting
        glEnable(GL_LIGHT0);                            // enables light0
    }
    
    /**
     * Method: initiLightArrays
     * Purpose: Initialize light arrays 
     */
    private static void initLightArrays() {
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(0.0f).put(0.0f).put(0.0f).put(1.0f).flip();
        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
    }
        
    public static void main(String[] args) {
        System.out.println("DEPRESSED DINOS ASSEMBLE");
        
        Minecraft game = new Minecraft();
        game.start();
    }
    
}
