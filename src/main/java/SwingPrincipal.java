import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPrincipal {
    private JTextField txtMensaje;
    private JButton btnCifrado;
    private JTextField txtClave;
    private JTextField txtMensajeCifrado;
    private JPanel mainPanel;
    private JButton btnDecifrado;
    private JTextField txtMensajeDecifrado;

    public SwingPrincipal() {
        btnCifrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SwingPrincipal");
        frame.setContentPane(new SwingPrincipal().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
