package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher {
    public static class Listener implements ActionListener{
        Layer l;

        public Listener(Layer l){
            this.l = l;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton j = (JButton)e.getSource();
            String s = j.getActionCommand();
            if(s.equals("settings")){
                l.Settings();
            }else if(s.equals("host")){
                l.Host();
            }else if(s.equals("join")){
                l.Join();
            }else if(s.equals("exit")){
                l.Exit();
            }else{

            }
        }
    }

    public static class Layer extends JPanel {
        private JButton settings;
        private JButton host;
        private JButton join;
        private JButton exit;
        private Listener l;

        public Layer(){
            settings = new JButton("Settings");
            host = new JButton("Host a game");
            join = new JButton("Join a game");
            exit = new JButton("Exit");

            l = new Listener(this);

            settings.addActionListener(l);
            host.addActionListener(l);
            join.addActionListener(l);
            exit.addActionListener(l);

            settings.setActionCommand("settings");
            host.setActionCommand("host");
            join.setActionCommand("join");
            exit.setActionCommand("exit");

            this.add(settings);
            this.add(host);
            this.add(join);
            this.add(exit);
        }

        public void Settings(){
            //Still needs implementation
        }

        public void Host(){
            //Still needs implementation
        }

        public void Join(){
            //Still needs implementation
        }

        public void Exit(){
            //Still needs implementation
        }
    }

    public static class Window extends JFrame{
        public Window(String title){
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setTitle(title);
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            double w = d.getWidth()*0.4;
            double h = d.getHeight()*0.3;
            this.setSize((int)w,(int)h);
            this.setResizable(true);
            this.add(new Layer());
            this.setLocation((int)((d.getWidth()/2)-(w/2)),(int)((d.getHeight()/2)-(h/2)));
            this.setVisible(true);
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window w = new Window("Launcher");
            }
        });
    }
}
