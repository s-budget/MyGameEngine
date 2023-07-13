package org.example;

public abstract class Scene
{
    public Scene()
    {
        System.out.println("scene");
    }
    public abstract void update(float dt);
}
