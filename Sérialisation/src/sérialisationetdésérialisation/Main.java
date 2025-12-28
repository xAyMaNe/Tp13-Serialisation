package sérialisationetdésérialisation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Teste la sérialisation et la désérialisation
 * d'employés aux noms marocains.
 */
public class Main {
    public static void main(String[] args) {
        final String filePath = "employees.ser";

        // 1) Création d'une liste d'employés
        List<Employee> staff = new ArrayList<>();
        staff.add(new Employee(1, "Youssef", 3000.0, "pwdYoussef"));
        staff.add(new Employee(2, "Fatima",  3500.5, "pwdFatima"));
        staff.add(new Employee(3, "Omar",    2800.75,"pwdOmar"));

        // 2) Sérialisation vers un fichier binaire
        try {
            SerializationUtil.serializeEmployees(filePath, staff);
            System.out.println("→ Sérialisation réussie dans " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur de sérialisation : " + e.getMessage());
            return;
        }

        // 3) Désérialisation depuis le fichier
        List<Employee> loaded;
        try {
            loaded = SerializationUtil.deserializeEmployees(filePath);
            System.out.println("→ Désérialisation réussie, objets restaurés :");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur de désérialisation : " + e.getMessage());
            return;
        }

        // 4) Affichage des objets désérialisés
        for (Employee emp : loaded) {
            System.out.println("  " + emp);
        }

        // 5) Vérification du champ transient
        System.out.println("\nNote : le champ 'password' est transient, il n’a pas été enregistré → null.");
    }
}