import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class VentanaPrincipal extends JFrame {

    JTextArea areaDeCodigo;
    JScrollPane scrollCodigo;
    PanelDeDibujo panelDeDibujo;
    JButton ejecutar;
    JButton debug;
    JButton siguiente;
    Parser parser;
    boolean modoDebug;
    public static final Color accent = new Color(0xFFB220);
    
    
    public VentanaPrincipal(){
        
        JPanel top = new JPanel();
        top.setBackground(accent);
        top.setBounds(0,0,750,50);
        add(top);
               
        modoDebug = false;        
        parser = new Parser();
        parser.insertarInstrucciones();
        
        areaDeCodigo = new JTextArea();
        areaDeCodigo.setBackground(new Color(0x3f51b5));
        areaDeCodigo.setCaretColor(accent);
        areaDeCodigo.setLineWrap(true);
        areaDeCodigo.setWrapStyleWord(true);
        areaDeCodigo.setTabSize(2);        
        areaDeCodigo.setMargin(new Insets(10,10,10,10));
        areaDeCodigo.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaDeCodigo.setForeground(Color.WHITE);
        scrollCodigo = new JScrollPane (areaDeCodigo);
        scrollCodigo.setBorder(BorderFactory.createEmptyBorder());
        scrollCodigo.setBounds(500,50,245,475);        
        add(scrollCodigo);
        
        panelDeDibujo = new PanelDeDibujo();
        panelDeDibujo.setBounds(0,50,Propiedades.PANEL_DE_DIBUJO_ANCHO,Propiedades.PANEL_DE_DIBUJO_LARGO);
        panelDeDibujo.setBackground(new Color(0xFFFFFF));
        add(panelDeDibujo);
        
        ejecutar = new ButtonUI(new ImageIcon(System.getProperty("user.dir") + "/run.png"));
        ejecutar.setBounds(10,10,50,40);
        ejecutar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                if(parser.compilar(areaDeCodigo.getText()))
                    panelDeDibujo.setConfiguracion(parser.ejecutar());
                else{
                    parser = new Parser();
                    parser.insertarInstrucciones();
                    panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                }
                panelDeDibujo.repaint();
            }
        }); 
        debug = new ButtonUI(new ImageIcon(System.getProperty("user.dir") + "/Debug.png")); 
        debug.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                if(!modoDebug){
                    if(parser.compilar(areaDeCodigo.getText())){
                        panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                        cambiarDebug();
                    }
                    else{
                        parser = new Parser();
                        parser.insertarInstrucciones();
                        panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                    }
                }
                else
                    cambiarDebug();                
                panelDeDibujo.repaint();
            }
        });         
        siguiente = new ButtonUI(new ImageIcon(System.getProperty("user.dir") + "/Next.png"));
        siguiente.setBounds(210,10,50,40);
        siguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.ejecutarSiguiente();
                panelDeDibujo.setConfiguracion(parser.getConfiguracion());
                panelDeDibujo.repaint();
            }
        });
        siguiente.setEnabled(false); 
        
        JPanel btns = new JPanel();
        btns.setBackground(null);
        btns.setLayout(new GridLayout(1,3,0,0));
        btns.add(ejecutar);


        top.setLayout(new BorderLayout());
        top.add(btns, BorderLayout.EAST);
        
        JLabel title = new JLabel("       LOGO");
        title.setFont(new Font("Roboto Bold", Font.PLAIN, 16));
        title.setForeground(Color.WHITE);
        top.add(title, BorderLayout.WEST);
        
        setLayout(null);
        setBounds(50,50,950,750);
        setVisible(true);
        getContentPane().setBackground(new Color(0x3f51b5));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setPreferredSize(new Dimension(750,550));
        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        setIconImage(icon);
        pack();
    }
    
    private void cambiarDebug(){
        if(!modoDebug){
            areaDeCodigo.setEnabled(false);
            ejecutar.setEnabled(false);
            siguiente.setEnabled(true);
        }
        else{
           areaDeCodigo.setEnabled(true);
            ejecutar.setEnabled(true);
            siguiente.setEnabled(false); 
        }
        modoDebug = !modoDebug;
    }
    
 
}


class ButtonUI extends JButton{
    public ButtonUI(ImageIcon i){ 
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setBackground(null);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setMinimumSize(new Dimension(60,50));
        this.setPreferredSize(new Dimension(60,50));
        this.setSize(new Dimension(60,50));
        setIcon(i);        
    }
}
