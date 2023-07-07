package org.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window
{
    private  int width,height;
    private String title;
    private long glfwWindow;
    private static Window window=null;
    private Window()
    {
        width=300;
        height=300;
        title="MyGame";

    }
    public static Window get()
    {
        if(Window.window==null)
        {
            Window.window=new Window();
        }
        return Window.window;
    }

    public void run() {
        System.out.println("We are up and running!!");
        init();
        System.out.println("initialized window.");
        loop();
    }



    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);

        glfwWindow =glfwCreateWindow(width,height,title,NULL,NULL);

        if(glfwWindow==NULL)
        {
            throw new IllegalStateException("Failed to create window");
        }
        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);//v-sync

        glfwShowWindow(glfwWindow);
        GL.createCapabilities();
    }
    private void loop()
    {
        System.out.println("Looping started");
        while(!glfwWindowShouldClose(glfwWindow))
        {
            glfwPollEvents();
            glClearColor(1,0,0,1 );
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
