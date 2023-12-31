package org.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import utils.Time;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window
{
    private  int width,height;
    private String title;
    private long glfwWindow;
    private static Window window=null;
    private static Scene currentScene;
    private Window()
    {
        width=600;
        height=600;
        title="MyGame";

    }
    public static void changeScene(int newScene)
    {
     if(newScene==0)
     {
         currentScene=new LevelEditorScene();
     }else if(newScene==1)
     {
         currentScene=new LevelScene();
     }
     else
     {
         assert false: "UNknown scene"+ newScene;
     }
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

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();

    }



    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_FALSE);

        glfwWindow =glfwCreateWindow(width,height,title,NULL,NULL);

        if(glfwWindow==NULL)
        {
            throw new IllegalStateException("Failed to create window");
        }

        glfwSetCursorPosCallback(glfwWindow,MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow,MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow,MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow,KeyListener::keyCallback);

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);//v-sync

        glfwShowWindow(glfwWindow);
        GL.createCapabilities();
        Window.changeScene(0);
    }
    private void loop()
    {
        float beginTime= Time.getTime();
        float endTime;
        float dt=-1;
        System.out.println("Looping started");
        while(!glfwWindowShouldClose(glfwWindow))
        {
            glfwPollEvents();
            glClearColor(178f/255,229f/255,237f/255,1 );

            glClear(GL_COLOR_BUFFER_BIT);
            if(dt>0)
                currentScene.update(dt);

            glfwSwapBuffers(glfwWindow);
            endTime=Time.getTime();
            dt= endTime-beginTime;
            beginTime=endTime;
        }
    }
}
