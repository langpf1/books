package example;



import java.awt.*;
import java.awt.event.*;
import layout.TableLayout;

public class MyClass
{

    public static void main (String args[])
    {
        Frame frame = new Frame("MyTitle");
        frame.setBounds (100, 100, 300, 300);

       double size[][] =
           {{-2.0, 10.0, 50.0, -1.0, 10.0},  // Columns
           {-2.0, 10.0, 0.25, -1.0, 10.0}}; // Rows

        frame.setLayout (new TableLayout(size));

        Button button;
        button = new Button("3, 3, R, C");
        frame.add (button, "3, 3, R, C");
        button = new Button("3, 3, L, T");
        frame.add (button, "3, 3, L, T");
        button = new Button("2, 3, C, T");
        frame.add (button, "2, 3, C, T");
        button = new Button("3, 2, L, C");
        frame.add (button, "3, 2, L, C");
        button = new Button("2, 2, F, F");
        frame.add (button, "2, 2, F, F");
        button = new Button("3, 3, C, C");
        frame.add (button, "3, 3, C, C");

        frame.addWindowListener
            (new WindowAdapter()
                {
                    public void windowClosing (WindowEvent e)
                    {
                        System.exit (0);
                    }
                }
            );

        frame.show();
    }
}
