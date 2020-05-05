package view;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Window {

    public Window(int width, int height, String title)
    {
        m_Width  = width;
        m_Height = height;
        m_Title  = title;

        Init();
    }

    private void Init()
    {
        m_Window = new JFrame(m_Title);
        m_Window.setSize(m_Width, m_Height);
        m_Window.setDefaultCloseOperation(m_Window.DISPOSE_ON_CLOSE);

        m_Canvas = new GameCanvas();

        m_Window.add(m_Canvas);
        m_Window.setVisible(true);

        m_Window.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowActivated(e);
                System.exit(0);
            }
        });
    }

    public void Render()
    {
        m_Canvas.repaint();
    }

    private int m_Width, m_Height;
    private String m_Title;

    private JFrame m_Window;
    private GameCanvas m_Canvas;
}
