package view;

import java.awt.*;

public class Human extends Component {

    public Human(int x, int y)
    {
        super();

        m_X = x;
        m_Y = y;
        m_Color = Color.GREEN;
    }

    public float getHumanX() { return m_X; }
    public float getHumanY() { return m_Y; }

    public void setHumanX(float m_X) { this.m_X = m_X; }
    public void setHumanY(float m_Y) { this.m_Y = m_Y; }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(m_Color);
        g.fillRect((int)m_X, (int)m_Y, 5, 5);
    }

    private float m_X, m_Y;
    private Color m_Color;
}
