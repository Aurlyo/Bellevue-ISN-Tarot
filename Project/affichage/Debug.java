package affichage;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

class JTextComponentOutputStream extends OutputStream {

    private final JTextComponent component;
    private final StringBuilder buffer;

    public JTextComponentOutputStream(JTextComponent component) {
        this.component = component;
        this.buffer = new StringBuilder();
    }

    @Override
    public void write(int b) throws IOException {
        buffer.append((char) b);
        component.setText(buffer.toString());
        component.setCaretPosition(buffer.length());
    }
}

class InputStreamKeyListener extends PipedInputStream implements KeyListener {

    private final JTextComponent component;
    private final PipedOutputStream out;

    public InputStreamKeyListener(JTextComponent component) {
        super();
        try {
            this.component = component;
            this.out = new PipedOutputStream(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        component.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                String text = component.getText();
                out.write(text.getBytes());
                component.setText("");
                System.out.print(text);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
 /**
  * <i> Code récuperer pour faciliter la phase de debug.
  * (Code qui sera supprimé pour le rendu final puisque inutile dès lors
  * qu'on aura l'interface graphique.</i>
  * 
  * @author KX (Comment-ça-marche.com)
  *
  */
public class Debug {

	/**
	 * <b> Fonction permetant un debug plus agréable </b>
	 * <p>
	 * Affiche la console dans une fenêtre JAVA, donc plus grande,
	 * mieux lisible, etc...
	 * <p>
	 * <i> Voué a disparaître </i>
	 */
    public static void initConsole() {
        // out,err
        JTextComponent outArea = new JTextArea();
        outArea.setEditable(false);
        OutputStream output = new JTextComponentOutputStream(outArea);
        PrintStream stream = new PrintStream(output);
        System.setOut(stream);
        System.setErr(stream);
        // in
        JTextPane inArea = new JTextPane();
        InputStream input = new InputStreamKeyListener(inArea);
        System.setIn(input);
        // frame
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(outArea), BorderLayout.CENTER);
        frame.add(inArea, BorderLayout.SOUTH);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}