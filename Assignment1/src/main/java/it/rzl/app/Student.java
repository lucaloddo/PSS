package it.rzl.app;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private Integer matricola;
    private String nome;
    private String cognome;
    private String corso;

    public Student() {

    }

    public Student(Integer matricola, String nome, String cognome, String corso) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.corso = corso;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCorso() {
        return corso;
    }

}