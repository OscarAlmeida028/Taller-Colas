import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.setContentPane((interfaz.GetVentana()));
        interfaz.setTitle("Taller de colas");
        interfaz.setBounds(300,300,800,500);
        interfaz.setVisible(true);
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}