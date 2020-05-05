package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JPanel
{
    public GameCanvas()
    {
        m_HumanList = new ArrayList<>();
    }

    public static void addHuman(Human h)
    {
        m_HumanList.add(h);
    }
    public static void setHuman(Human h, int index)
    {
        m_HumanList.set(index, h);
    }
    public static Human getHuman(int index)
    {
        return m_HumanList.get(index);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        for(Human h : m_HumanList)
        {
            h.paint(g);
        }
    }

    private static ArrayList<Human> m_HumanList;
}