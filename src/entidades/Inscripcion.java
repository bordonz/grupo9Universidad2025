/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author julianesquiaga
 */
public class Inscripcion {
    private int idInscripto;
    private int nota;
    private int idAlumno;
    private int idMateria;


    public Inscripcion() {    
    }

    public Inscripcion(int nota, int idAlumno, int idMateria) {
        int idInscripto = -1;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public int getIdInscripto() {
        return idInscripto;
    }

    public int getNota() {
        return nota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripto=" + idInscripto + ", nota=" + nota + ", idAlumno=" + idAlumno + ", idMateria=" + idMateria + '}';
    }

    


};


