package org.example;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class MouseListener
{
    private static MouseListener instance;
    private double scrollX,scrollY;
    private double xPos,yPos,lastY,lastX;
    private boolean mouseButtonPressed[]=new boolean[3];
    private boolean isDragging;

    private MouseListener()
    {
        scrollX=0;
        scrollY=0;
        xPos=0;
        yPos=0;
        lastX=0;
        lastY=0;
    }

    public static MouseListener get()
    {
         if(MouseListener.instance==null)
         {
             MouseListener.instance=new MouseListener();
         }
         return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xpos,double ypos)
    {
        get().lastY= get().yPos;
        get().lastX= get().xPos;
        get().xPos=xpos;
        get().yPos=ypos;
        get().isDragging=get().mouseButtonPressed[0]||get().mouseButtonPressed[1]||get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods)
    {
        if(button<get().mouseButtonPressed.length)
        {
            get().mouseButtonPressed[button] = (action==GLFW_PRESS);
            if(!get().mouseButtonPressed[button])
            {
                get().isDragging=false;
            }
        }
    }

    public static void mouseScrollCallback(long window,double xOffset,double yOffset)
    {
        get().scrollY=yOffset;
        get().scrollX=xOffset;
    }

    public static void endFrame()
    {
        get().scrollX=0;
        get().scrollY=0;
        get().lastX= get().xPos;
        get().lastY=get().yPos;
    }

    public float getScrollX() {
        return (float) get().scrollX;
    }

    public float getScrollY() {
        return (float) get().scrollY;
    }

    public float getxPos() {
        return (float) get().xPos;
    }

    public float getyPos() {
        return (float) get().yPos;
    }

    public float getDx() {
        return (float) (get().lastX-get().xPos);
    }

    public float getDy() {
        return (float) (get().lastY-get().yPos);
    }

    public float getLastY() {
        return (float) get().lastY;
    }

    public float getLastX() {
        return (float) get().lastX;
    }

    public boolean getMouseButtonPressed( int button) {
        if(button< get().mouseButtonPressed.length)
            return get().mouseButtonPressed[button];
        return false;
    }

    public boolean isDragging() {
        return get().isDragging;
    }
}
