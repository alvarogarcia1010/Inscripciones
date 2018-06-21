/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.InscripcionDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Inscripcion;

/**
 *
 * @author Alvaro García <alvarogarcia1010 at github.com>
 */
public class Consulta extends JFrame{
    public JLabel lblNumAfp, lblNombres, lblApellidos, lblEdad, lblProfesion, lblEstado;
    public JTextField numAfp, nombres, apellidos, edad;
    
    public JComboBox profesion;

    public ButtonGroup estado;
    public JRadioButton si;
    public JRadioButton no;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC = 210, ALTOC = 35;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        
        Container container = getContentPane();
        
        container.add(this.lblNumAfp);
        container.add(this.lblNombres);
        container.add(this.lblApellidos);
        container.add(this.lblEdad);
        container.add(this.lblProfesion);
        container.add(this.lblEstado);
        
        container.add(this.numAfp);
        container.add(this.nombres);
        container.add(this.apellidos);
        container.add(this.edad);
        container.add(this.profesion);

        container.add(this.si);
        container.add(this.no);
        
        container.add(this.buscar);
        container.add(this.insertar);
        container.add(this.actualizar);
        container.add(this.eliminar);
        container.add(this.limpiar);
        container.add(this.table);
        setSize(800,600);
        eventos();
    }

    private void agregarLabels() {
        this.lblNumAfp = new JLabel("N° AFP");
        this.lblNombres = new JLabel("Nombres");
        this.lblApellidos = new JLabel("Apellidos");
        this.lblEdad = new JLabel("Edad");
        this.lblProfesion = new JLabel("Profesion");
        this.lblEstado = new JLabel("Estado");


        this.lblNumAfp.setBounds(20,10, this.ANCHOC,this.ALTOC);
        this.lblNombres.setBounds(20,60, this.ANCHOC,this.ALTOC);
        this.lblApellidos.setBounds(360,60, this.ANCHOC,this.ALTOC);
        this.lblEdad.setBounds(20,110, this.ANCHOC,this.ALTOC);
        this.lblProfesion.setBounds(20,160, this.ANCHOC,this.ALTOC);
        this.lblEstado.setBounds(20,210, this.ANCHOC,this.ALTOC);
    }

    private void formulario() {
        this.numAfp = new JTextField();
        this.nombres = new JTextField();
        this.apellidos = new JTextField();
        this.edad = new JTextField();
        
        this.profesion = new JComboBox();

        this.si = new JRadioButton("si",true);
        this.no = new JRadioButton("no");
        
        this.resultados = new JTable();
        this.buscar = new JButton("Buscar");
        this.insertar = new JButton("Insertar");
        this.eliminar = new JButton("Eliminar");
        this.actualizar = new JButton("Actualizar");
        this.limpiar = new JButton("Limpiar");
        
        this.table = new JPanel();
        
        this.profesion.addItem("Ingeniero");
        this.profesion.addItem("Mecanico");
        this.profesion.addItem("Profesor");
        this.profesion.addItem("Arquitecto");

        this.estado = new ButtonGroup();
        this.estado.add(this.si);
        this.estado.add(this.no);
        
        this.numAfp.setBounds(100, 10, ANCHOC, ALTOC);
        this.nombres.setBounds(100, 60, ANCHOC, ALTOC);
        this.apellidos.setBounds(420, 60, ANCHOC, ALTOC);
        this.edad.setBounds(100, 110, ANCHOC, ALTOC);
        this.profesion.setBounds(100, 160, ANCHOC, ALTOC);
        this.si.setBounds(100,210,50,ALTOC);
        this.no.setBounds(200,210,50,ALTOC);
        
        this.buscar.setBounds(360, 10, 100,ALTOC);
        this.insertar.setBounds(20, 260, 100,ALTOC);
        this.actualizar.setBounds(150, 260, 100,ALTOC);
        this.eliminar.setBounds(300, 260, 100,ALTOC);
        this.limpiar.setBounds(450, 260, 100,ALTOC);
       
        this.resultados = new JTable();
        this.table.setBounds(20, 320, 600,200);
        this.table.add(new JScrollPane(resultados));
            
    }

    private void llenarTabla() {

        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;     
                    default:
                        return Boolean.class;
                }
            }
          
            
        };
        
        tm.addColumn("N° AFP");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Profesion");
        tm.addColumn("Estado");
        
        InscripcionDao id = new InscripcionDao();
        ArrayList<Inscripcion> inscripciones = id.readAll();
        
        for (Inscripcion i : inscripciones){
            this.tm.addRow(new Object[]{i.getNumAFP(),i.getNombre(), i.getApellido(),i.getProfesion(), i.isEstado()});
        }
        
        this.resultados.setModel(this.tm);    
    }
    
    
    public void eventos(){
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                InscripcionDao fd = new InscripcionDao();
                Inscripcion f = new Inscripcion(numAfp.getText(), nombres.getText(), apellidos.getText(),Integer.parseInt(edad.getText()),profesion.getSelectedItem().toString(),true);
                
                if(no.isSelected()){
                    f.setEstado(false);
                }
                
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Inscripcion registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
        });
        
    actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                InscripcionDao fd = new InscripcionDao();
                Inscripcion f = new Inscripcion(numAfp.getText(), nombres.getText(), apellidos.getText(),Integer.parseInt(edad.getText()),profesion.getSelectedItem().toString(),true);
                
                if(no.isSelected()){
                    f.setEstado(false);
                }
                
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null,"Inscripcion Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar el filtro");
                }
            }
        
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                InscripcionDao fd = new InscripcionDao();
                if(fd.delete(numAfp.getText())){
                    JOptionPane.showMessageDialog(null,"Inscripcion eliminado");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });
    
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                InscripcionDao fd = new InscripcionDao();
                Inscripcion f = fd.read(numAfp.getText());
                if(f == null){
                    JOptionPane.showMessageDialog(null,"El filtro buscado no se ha encontrado");
                }else{
                    numAfp.setText(f.getNumAFP());
                    nombres.setText(f.getNombre());
                    apellidos.setText(f.getApellido());
                    profesion.setSelectedItem(f.getProfesion());
                    edad.setText(Integer.toString(f.getEdad()));

                    if(f.isEstado()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarCampos();
            }
        });
        
    }
    
    public void limpiarCampos(){
        this.numAfp.setText("");
        this.nombres.setText("");
        this.apellidos.setText("");
        this.edad.setText("");
        this.profesion.setSelectedItem("Ingeniero");

    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                Consulta c = new Consulta();
                c.setVisible(true);

            }
            
        });

    }    
}
