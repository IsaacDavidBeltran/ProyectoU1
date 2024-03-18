package Utility;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class JPasswordFieldShowHide extends JPasswordField {
    private JButton btnShowHide;
    private boolean passwordVisible = false;

    public JPasswordFieldShowHide(int columns) {
        super(columns);
        setLayout(new BorderLayout());
        btnShowHide = new JButton(new ImageIcon("src\\img\\esconder.png"));
        btnShowHide.setFocusable(false);
        btnShowHide.setBorderPainted(false); // Eliminar el borde del boton.
        btnShowHide.setContentAreaFilled(false); // Hacer transparente el contenido.
        btnShowHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarVisibilidad();
            }
        });
        add(btnShowHide, BorderLayout.EAST);
    }

    private void cambiarVisibilidad() {
        if (passwordVisible) {
            // Cambia los caracteres introducidos por un '*'
            setEchoChar('*');
            btnShowHide.setIcon(new ImageIcon("src\\img\\esconder.png"));
        } else {
            // Permite ver los caracteres del password
            setEchoChar((char) 0);
            btnShowHide.setIcon(new ImageIcon("src\\img\\mostrar.png"));
        }
        passwordVisible = !passwordVisible;
    }
}
