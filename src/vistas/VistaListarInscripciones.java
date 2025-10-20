
package vistas;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import persistencia.AlumnoData;
import persistencia.InscripcionData;
import persistencia.MiConexion;

/**
 *
 * @author crb_p
 */
public class VistaListarInscripciones extends javax.swing.JInternalFrame {

    MiConexion conex = new MiConexion("jdbc:mariadb://localhost:3306/ulp2025gp9", "root", "");
    AlumnoData alumnoD = new AlumnoData(conex);

    private DefaultTableModel tablaListarInsc = new DefaultTableModel();

    public VistaListarInscripciones() {
        initComponents();
        tablaCabecera();
         cargarAlumnosEnComboBox();
          jcbListaAlumnos.addActionListener(e -> cargarInscripcionesPorAlumno());
        
    }

    private void cargarAlumnosEnComboBox() {
        jcbListaAlumnos.removeAllItems();
        for (Alumno a : alumnoD.listarAlumnos()) {
            jcbListaAlumnos.addItem(a);
        }
    }

     private void cargarInscripcionesPorAlumno() {
         
    tablaListarInsc.setRowCount(0);

    Alumno alumnoSeleccionado = (Alumno) jcbListaAlumnos.getSelectedItem();

    if (alumnoSeleccionado != null) {
        
        InscripcionData inscD = new InscripcionData(conex);

        List<Inscripcion> inscripciones = inscD.obtenerInscripcionPorAlumno(alumnoSeleccionado.getIdAlumno());

        for (Inscripcion i : inscripciones) {
            Materia mat = i.getMateria();
            tablaListarInsc.addRow(new Object[]{
                alumnoSeleccionado.getIdAlumno(),
                alumnoSeleccionado.getDni(),
                alumnoSeleccionado.getApellido(),
                alumnoSeleccionado.getNombre(),
                mat.getIdMateria(),
                mat.getNombre(),
                mat.getAnio(),
                i.getIdInscripto(),
                i.getNota()
               
            });
        }
    }
}
    

    private void tablaCabecera() {
        tablaListarInsc.addColumn("Dni");
        tablaListarInsc.addColumn("Apellido");
       tablaListarInsc.addColumn("Nombre");
        tablaListarInsc.addColumn("IdMateria");
        tablaListarInsc.addColumn("Nombre de la Materia");
         tablaListarInsc.addColumn("Nota");
        jTMateriasInscriptas.setModel(tablaListarInsc);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLMateriasPorAlumno = new javax.swing.JLabel();
        jLIdAlumno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMateriasInscriptas = new javax.swing.JTable();
        jBBuscar = new javax.swing.JButton();
        jcbListaAlumnos = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLMateriasPorAlumno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLMateriasPorAlumno.setText("Lista de Materias Inscriptas por Alumno");

        jLIdAlumno.setText("Seleccione un Alumno:");

        jTMateriasInscriptas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(jTMateriasInscriptas);

        jBBuscar.setBackground(new java.awt.Color(0, 102, 0));
        jBBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jBBuscar.setText("BUSCAR");

        jcbListaAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbListaAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLMateriasPorAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jcbListaAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(314, 314, 314))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLMateriasPorAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbListaAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbListaAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbListaAlumnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbListaAlumnosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JLabel jLIdAlumno;
    private javax.swing.JLabel jLMateriasPorAlumno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTMateriasInscriptas;
    private javax.swing.JComboBox<Alumno> jcbListaAlumnos;
    // End of variables declaration//GEN-END:variables
}
