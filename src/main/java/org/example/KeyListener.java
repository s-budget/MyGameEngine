package org.example;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class KeyListener
{
    private static KeyListener instance;
    private boolean keyPressed[]=new boolean[350];
    private  KeyListener()
    {

    }
    public static KeyListener get()
    {
        if(KeyListener.instance==null)
             KeyListener.instance=new KeyListener();
        return KeyListener.instance;
    }
    public static void keyCallback(long window,int key, int scancode,int action,int mods)
    {
        get().keyPressed[key]=(action==GLFW_PRESS);
    }

    public static boolean isKeyPressed(int keyCode)
    {
        if(keyCode<get().keyPressed.length)
            return get().keyPressed[keyCode];
        return false;
    }
}
