import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Interfaz extends JFrame {
    private JPanel panel1;
    private JTabbedPane Ventana;
    private JButton cambiarQuantumValueButton;
    private JButton mostrarHistorialButton;
    private JButton imprmirDatosButton;
    private JButton consultarÚltimoProcesosButton;
    private JTextArea roundRobintextArea;
    private JTextArea historialtextArea;
    private JTextField quantumValuetextField;
    private JTextField IDtextField;
    private JTextField CedtextField;
    private JTextField TiempotextField;
    private JTextPane imprimirDatosPreTextArea;
    private JButton imprimirDatosPredefinidosButton;
    private JButton insertarButton;
    private JButton ejecutarRoundRobinButton;
    private JTextArea quantumValuetextArea;
    private JTextArea imprimirDatosTextArea;
    private JButton quitarButton;
    private JButton imprimirDatosEstudianteButton;
    private JTextArea imprimirDatosEstudianteTextArea;
    private JButton limpiarImprimirDatosButton;
    private JButton limpiarImprimirDatosEstudianteButton;
    private JButton borrarHistorialButton;
    private JButton limpiarButton;

    private Queue<Procesos> colaRobin = new LinkedList<>();
    private Stack<Procesos> pilaHistorial = new Stack<>();

    int quantum = 0;


    public Interfaz() {
        BotonesApagados();

        imprimirDatosPredefinidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatosPredefinidos();
                ImprimirDatosPref();
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuitarDatosPref();
                imprimirDatosPreTextArea.setText("");
                insertarButton.setEnabled(false);
            }
        });
        imprmirDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImprimirDatos();

            }
        });
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    InsertarProceso();

            }
        });
        cambiarQuantumValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CambiarQuantum();
                }catch (Exception x) {
                    JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
                }

            }
        });
        ejecutarRoundRobinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoundRobin();
            }
        });
        imprimirDatosEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImprimirDatosEstudiante();
                limpiarImprimirDatosEstudianteButton.setEnabled(true);
            }
        });
        limpiarImprimirDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!colaRobin.isEmpty()) {
                    imprimirDatosTextArea.setText("");
                    limpiarImprimirDatosButton.setEnabled(false);
                }
            }
        });
        limpiarImprimirDatosEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirDatosEstudianteTextArea.setText("");
                limpiarImprimirDatosEstudianteButton.setEnabled(false);
            }
        });
        mostrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pilaHistorial.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay historial");
                }else {
                    MostrarHistorial();
                }
            }
        });
        consultarÚltimoProcesosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pilaHistorial.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay historial");
                }else {
                    UltimoProcesos();
                }
            }
        });
        borrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarHistorial();
                JOptionPane.showMessageDialog(null, "Historial Borrado");
            }
        });
    }

    public JTabbedPane GetVentana() {
        return Ventana;
    }

    private void ImprimirDatos() {
        if (colaRobin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
            return;
        } else {
            imprimirDatosTextArea.setText(colaRobin.toString());
            limpiarImprimirDatosButton.setEnabled(true);
        }

    }

    private void ImprimirDatosEstudiante() {
        imprimirDatosEstudianteTextArea.setText("Nombre: Oscar Almeida//Cedula:0401925458//Banner:A00086379\n" +
                "Nombre: Diego Toscano//Cedula:1724016660//Banner: A00085345");
    }

    private void DatosPredefinidos() {
        colaRobin.add(new Procesos("P1", 1391385903, 100));
        colaRobin.add(new Procesos("P2", 1947908489, 15));
        colaRobin.add(new Procesos("P3", 1203288367, 40));

        //System.out.println(colaRobin.poll());
        /*colaRobin.add(new Procesos("P4", 1212105535, 25));
        colaRobin.add(new Procesos("P5", 1990066738, 50));
        colaRobin.add(new Procesos("P6", 1409739463, 30));*/
        imprimirDatosPredefinidosButton.setEnabled(false);
        quitarButton.setEnabled(true);
    }

    private void ImprimirDatosPref() {
        imprimirDatosPreTextArea.setText(colaRobin.toString());
        JOptionPane.showMessageDialog(null, "Datos predefinidos agregados a cola");
    }

    private void QuitarDatosPref() {
        colaRobin.clear();
        imprimirDatosPredefinidosButton.setEnabled(true);
        quitarButton.setEnabled(false);
        imprimirDatosPreTextArea.setText("");
        JOptionPane.showMessageDialog(null, "Datos predefinidos eliminados de cola");
    }

    private void InsertarProceso() {
        String id = IDtextField.getText();
        int cedula = Integer.parseInt(CedtextField.getText());
        int tiempo = Integer.parseInt(TiempotextField.getText());
        Procesos nuevoProceso = new Procesos(id, cedula, tiempo);
        colaRobin.add(nuevoProceso);
        JOptionPane.showMessageDialog(null, "Nuevo proceso agregado");
    }

    private void CambiarQuantum() {
        quantum = Integer.parseInt(quantumValuetextField.getText());
        JOptionPane.showMessageDialog(null, "Valor quantum definido en " + quantum);
    }

    private void RoundRobin() {
        int tiempoEjecucion = 0;
        int conmutaciones = 0;
        if (quantum==0) {
            JOptionPane.showMessageDialog(null, "\nEstablezca un valor de quantum\n");
            return;
        } else
            if (colaRobin.size()>1) {
                while (!colaRobin.isEmpty()) {
                    Procesos actual = colaRobin.remove();
                    /*if (!colaRobin.isEmpty() && actual != colaRobin.peek()) {*/
                        System.out.println("Tiempo " + tiempoEjecucion + ": " + actual.getId() + " entra a ejecución.");
                        roundRobintextArea.insert("\nTiempo " + tiempoEjecucion + ": " + actual.getId() + " entra a ejecución.",0);
                        //actual.setTiempo(actual.getTiempo() - quantum);
                        System.out.println("Time: "+actual.getTiempo() +" Nombre: "+actual.getId());
                    //}
                    if (actual.getTiempo() <= quantum) {
                        tiempoEjecucion += actual.getTiempo();
                        System.out.println("Time: "+actual.getTiempo() +" Nombre: "+actual.getId());
                        System.out.println("Tiempo " + tiempoEjecucion + ": " + actual.getId() + " termina su ejecución.");
                        roundRobintextArea.insert("\nTiempo " + tiempoEjecucion + ": " + actual.getId() + " termina su ejecución.",0);
                        actual.setTiempo(0);
                        pilaHistorial.push(actual);
                    } else {
                        tiempoEjecucion += quantum;
                        actual.setTiempo(actual.getTiempo() - quantum);
                        colaRobin.add(actual);
                        System.out.println("Tiempo " + tiempoEjecucion + ": " + actual.getId() + " conmuta ejecucion.\nPendiente por ejecutar " + actual.getTiempo() + " ms.");
                        conmutaciones++;
                        roundRobintextArea.insert("\nTiempo " + tiempoEjecucion + ": " + actual.getId() + " se conmuta. Pendiente por ejecutar " + actual.getTiempo() + " ms.",0);
                    }
                }
        }

        roundRobintextArea.insert("\nESTADÍSTICAS GENERADAS:\n" + "Total tiempo de ejecución de todos los procesos: " + tiempoEjecucion + "\n" + "Total de conmutaciones: " + conmutaciones,0);
    }

    private void MostrarHistorial() {
        historialtextArea.setText(String.valueOf(pilaHistorial));
    }

    private void UltimoProcesos(){
        historialtextArea.setText(String.valueOf(pilaHistorial.peek()));
    }

    private void BorrarHistorial(){
        pilaHistorial.clear();
        historialtextArea.setText(String.valueOf(pilaHistorial));
    }
    private void BotonesApagados(){
        quitarButton.setEnabled(false);
        limpiarImprimirDatosButton.setEnabled(false);
        limpiarImprimirDatosEstudianteButton.setEnabled(false);
    }
}

