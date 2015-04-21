

import java.awt.*;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class openGLTest extends GLCanvas implements GLEventListener, KeyListener{

	static JFrame frame;
	 /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** The frames per second setting. */
    private int fps = 60;

    /** The OpenGL animator. */
    private FPSAnimator animator;
    
    private GLU glu;
    
    
    
	public static void sup()
	{
		
		
		
		GLCapabilities capabilities = createGLCapabilities();
		
        openGLTest canvas = new openGLTest(Framework.xSize, Framework.ySize,capabilities);
        frame = new JFrame("CHOP ATTACK!!!!!");
        
        GraphicsDevice gd =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            frame.setUndecorated(false);
            gd.setFullScreenWindow(frame);
        } else {
            System.err.println("Full screen not supported");
            frame.setSize(100, 100); // just something to let you see the window
            frame.setVisible(true);
        }

        
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        //frame.setSize(xSize,ySize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        canvas.requestFocus();
        
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        //frame.setUndecorated(false);
	}
	
	public openGLTest(int width, int height, GLCapabilities capabilities)
	{
		//super(capabilities);
		//setSize(width, height);
		addGLEventListener(this);
		addKeyListener(this);
	}
	
	public void init(GLAutoDrawable drawable)
	{
		GL gl = drawable.getGL();
		drawable.setGL(new DebugGL(gl));
		
		//Gloobal settings
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glClearColor(0f, 0f, 0f, 1f);
		
		//Start Animator
		animator = new FPSAnimator(this, fps );
		animator.start();
		
		glu = new GLU();
		
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		GL gl = drawable.getGL();
        gl.glViewport(0, 0, width, height);
	}
	
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
	{
		
	}
	
	public void display(GLAutoDrawable drawable)
	{
		if (!animator.isAnimating()) {
            return;
        }
		
        final GL gl = drawable.getGL();

        // Clear screen.
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        int distance = Framework.CAMERA_DISTANCE;
        
        setCamera(gl, glu, distance);
        
        //write triangle
        
        gl.glBegin(GL.GL_QUADS);
        

        
        for(int i = 0; i < GameLevel.object.length; i++)
		{
        	GameLevel.object[i].set();
        	
            gl.glColor3f(GameLevel.object[i].color * .5f * i, GameLevel.object[i].color *.3f * i, GameLevel.object[i].color);
            
			gl.glVertex3f(GameLevel.object[i].x, GameLevel.object[i].y, 0);
			gl.glVertex3f(GameLevel.object[i].x + GameLevel.object[i].width, GameLevel.object[i].y, 0);
			gl.glVertex3f(GameLevel.object[i].x + GameLevel.object[i].width, GameLevel.object[i].y + GameLevel.object[i].height, 0);
			gl.glVertex3f(GameLevel.object[i].x, GameLevel.object[i].y + GameLevel.object[i].height, 0);
		}
        
        for(int i = 0; i < GameLevel.player.length; i++)
		{
        	GameLevel.player[i].set();
        	
            gl.glColor3f(GameLevel.player[i].color * .5f * i, GameLevel.player[i].color *.3f * i, GameLevel.player[i].color);
            
			gl.glVertex3f(GameLevel.player[i].x, GameLevel.player[i].y, 0);
			gl.glVertex3f(GameLevel.player[i].x + GameLevel.player[i].width, GameLevel.player[i].y, 0);
			gl.glVertex3f(GameLevel.player[i].x + GameLevel.player[i].width, GameLevel.player[i].y + GameLevel.player[i].height, 0);
			gl.glVertex3f(GameLevel.player[i].x, GameLevel.player[i].y + GameLevel.player[i].height, 0);
		}
        
        gl.glEnd();
        /*
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.7f, 0.7f, 0.7f);
        gl.glVertex3f(-30, -10, 0);
        gl.glColor3f(0.9f, 0.5f, 0.2f);
        gl.glVertex3f(+20, -15, 0);
        gl.glColor3f(0.3f, 0.5f, 0.2f);
        gl.glVertex3f(+20, +20, 0);
        gl.glColor3f(0.9f, 0.2f, 0.2f);
        gl.glVertex3f(-20, +3, 0);
        gl.glEnd();*/
	}
	
	private static GLCapabilities createGLCapabilities() {
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setRedBits(8);
        capabilities.setBlueBits(8);
        capabilities.setGreenBits(8);
        capabilities.setAlphaBits(8);
        return capabilities;
    }
	
	
	
	private void setCamera(GL gl, GLU glu, float distance)
	{
		 // Change to projection matrix.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        
        
        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(Framework.CAMERA_X, Framework.CAMERA_Y, distance, Framework.CAMERA_X, Framework.CAMERA_Y, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(code == e.VK_S){ Framework.S_pressed = true; }
		if(code == e.VK_A){ Framework.A_pressed = true; }
		if(code == e.VK_D){ Framework.D_pressed = true; }

		if(code == e.VK_SPACE){ Framework.SPACE_pressed = true; }
		
		if(code == e.VK_DOWN){ Framework.DOWN_pressed = true; }
		if(code == e.VK_UP){ Framework.UP_pressed = true; }
		if(code == e.VK_LEFT){ Framework.LEFT_pressed = true; }
		if(code == e.VK_RIGHT){ Framework.RIGHT_pressed = true; } 
		
		
		//if(code == e.KEY){ KEY_PRESSED = true; }

	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if(code == e.VK_S){ Framework.S_pressed = false; }
		if(code == e.VK_A){ Framework.A_pressed = false; }
		if(code == e.VK_D){ Framework.D_pressed = false; }

		if(code == e.VK_SPACE)
		{ 
			Framework.SPACE_pressed = false;
			Framework.Current_Level.chop.jumpCount ++;
		}
		
		if(code == e.VK_DOWN){ Framework.DOWN_pressed = false; }
		if(code == e.VK_UP){ Framework.UP_pressed = false; }
		if(code == e.VK_LEFT){ Framework.LEFT_pressed = false; }
		if(code == e.VK_RIGHT){ Framework.RIGHT_pressed = false; } 
		//if(code == e.KEY){ KEY_PRESSED = false; }
		
	}
	
	
	public void keyTyped(KeyEvent e) {	}
}
