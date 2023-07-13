package org.example;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene
{
    private float totTime=0;
    public LevelEditorScene()
    {
        System.out.println("level editor scene");
    }
    @Override
    public void  update (float dt)
    {
        if(KeyListener.isKeyPressed(KeyEvent.VK_SPACE))
        {totTime+=dt;
            System.out.println("total time holding space:"+totTime);
            System.out.println("FPS:"+1/dt);
        }
    }
}
