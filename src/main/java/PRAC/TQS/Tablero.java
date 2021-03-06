package PRAC.TQS;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

public class Tablero extends JPanel implements ActionListener{
	private int alto=939;	//39 por arriba
    private int ancho=816;	//16 por lados
    private int filas;
    private int columnas;
    private Casilla[][] tablero;
    private JFrame ventana;
    private JPanel todo;
    private JPanel content;
    private JPanel arriba;
    private JPanel banderas;
    private JPanel restart;
    private JButton restartb;
    private JPanel extra;
    //private JPanel[][] casillas;
    mouselistener mouse;
    private JFrame ventana_menu;
    private  JButton aceptar=new JButton("ACEPTAR");
    JTextField input_filas = new JTextField();
    JTextField input_columnas= new JTextField();
    JTextField input_nivel=new JTextField();

    public Tablero() {

    }
 
    public void crearVentana() throws IOException {
           ventana = new JFrame("BUSCAMINAS TQS");
           ventana.setSize(ancho,alto);
           ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           ventana.setVisible(true);
           ventana.setResizable(true);
           crearTablero(filas,columnas);
           ventana.setVisible(true);
    }
    
    public void crearTablero(int filas, int columnas) throws IOException {
    	todo = new JPanel();
    	todo.setLayout(new BoxLayout(todo,BoxLayout.Y_AXIS));
    	content=new JPanel(new GridLayout(filas,columnas));
    	content.setBackground(Color.black);
    	//casillas=new JPanel[filas][columnas];
    	mouse=new mouselistener();
    	todo.addMouseListener(mouse);
    	
    	arriba = new JPanel();
    	arriba.setSize(new Dimension(100,100));
    	arriba.setLayout(new BoxLayout(arriba,BoxLayout.X_AXIS));
    	
    	
    	restart= new JPanel();
    	restartb=new JButton("RESTART");
    	restartb.setSize(new Dimension(90,70));
    	restart.add(restartb);
    	
    	banderas= new JPanel();
    	banderas.setBackground(Color.yellow);
    	
    	extra= new JPanel();
    	extra.setBackground(Color.red);
    	
    	//JPanel extra2= new JPanel();
    	//extra2.setBackground(Color.red);
    	
    	//arriba.add(restartb);
    	//arriba.add(extra2);
    	arriba.add(banderas);
    	arriba.add(extra);
    	
    	
    	
    	tablero = new Casilla[this.filas][this.columnas];
        for (int fila=0; fila<filas; fila++) {
            for (int col=0; col<columnas; col++) {
                Casilla c = new Casilla(fila,col,alto,ancho,filas,columnas);
                tablero[fila][col]=c;
                content.add(c.getLabel());         
            }
            
        }      
        
        todo.add(arriba);
        todo.add(content);
        
        ventana.setContentPane(todo);
    }
    
    public void crearVentanaMenu() {

        ventana_menu=new JFrame("MENU BUSCAMINAS");
        GridLayout g1=new GridLayout();
        input_filas = new JTextField();
        input_columnas= new JTextField();
        input_nivel=new JTextField();
        JLabel mensaje_filas=new JLabel("Inserte el numero de filas (entre 4-20)");
        JLabel mensaje_columnas=new JLabel("Inserte el numero de columnas (entre 4-20)");
        JLabel mensaje_level=new JLabel("Inserte el nivel(entre 1-3)");
        JLabel vacio= new JLabel();

        g1.setRows(4);
        g1.setColumns(2);

        ventana_menu.getContentPane();
        ventana_menu.setLayout(g1);
        ventana_menu.add(mensaje_filas);
        ventana_menu.add(input_filas);
        ventana_menu.add(mensaje_columnas);
        ventana_menu.add(input_columnas);
        ventana_menu.add(mensaje_level);
        ventana_menu.add(input_nivel);
        ventana_menu.add(vacio);
        ventana_menu.add(aceptar);
        aceptar.addActionListener(this);
        ventana_menu.setSize(600,200);
        ventana_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana_menu.setVisible(true);
        ventana_menu.setResizable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand()==aceptar.getActionCommand()) {
            try {
                int valor=Integer.parseInt(input_nivel.getText());
                this.filas=Integer.parseInt(input_filas.getText());
                this.columnas=Integer.parseInt(input_columnas.getText());
                if(valor>3) {
                    JOptionPane.showMessageDialog(null,"N�mero no valido");
                }else {
                    ventana_menu.setVisible(false);
                    crearVentana();
                }
            } catch (Exception e) {JOptionPane.showMessageDialog(null, e+"");} 
        }
    }
}