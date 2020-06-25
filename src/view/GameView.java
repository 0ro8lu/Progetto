package view;

public class GameView
{

    public GameView()
    {
        Init();
    }

    public void Init()
    {
        m_Window = new Window(800, 600, "Hello World");
    }

    public void Draw()
    {
        m_Window.Render();
    }

    private Window m_Window;
}
