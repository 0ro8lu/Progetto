package view;

import java.awt.*;

public class GameView
{

    public GameView()
    {
        Init();
    }

    public void Init()
    {
        m_Window = new Window(1920, 1080, "Hello World");
    }

    public void Draw()
    {
        m_Window.Render();
    }

    private Window m_Window;
}
