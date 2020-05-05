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

        for(int i = 0; i < 1; i++)
        {
            GameCanvas.addHuman(new Human((i * 10), 10));
        }
    }

    public void Draw()
    {
        m_Window.Render();
    }

    private Window m_Window;
}
