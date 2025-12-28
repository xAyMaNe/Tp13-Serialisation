package sérialisationetdésérialisation;

import java.io.Serializable;

/**
 * Représente un employé sérialisable.
 * - serialVersionUID : identifiant de version de la classe.
 * - transient : champ non sérialisé.
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int    id;          // identifiant unique
    private final String name;        // nom complet
    private final double salary;      // salaire de base
    private transient String password; // mot de passe, non sérialisé

    public Employee(int id, String name, double salary, String password) {
        this.id       = id;
        this.name     = name;
        this.salary   = salary;
        this.password = password;
    }

    // Getters pour vérification après désérialisation
    public int    getId()       { return id; }
    public String getName()     { return name; }
    public double getSalary()   { return salary; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return String.format(
            "Employee[id=%d, name=%s, salary=%.2f, password=%s]",
            id, name, salary,
            (password == null ? "null" : password)
        );
    }
}