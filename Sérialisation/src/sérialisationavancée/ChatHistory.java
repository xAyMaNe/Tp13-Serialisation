package sérialisationavancée;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère la sérialisation d'une liste de ChatMessage.
 */
public class ChatHistory {
    /**
     * Sauvegarde l'historique dans un fichier.
     */
    public static void save(String path, List<ChatMessage> history) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeInt(history.size());
            for (ChatMessage msg : history) {
                msg.writeExternal(oos);
            }
        }
    }

    /**
     * Charge l'historique depuis un fichier.
     */
    public static List<ChatMessage> load(String path) throws IOException, ClassNotFoundException {
        List<ChatMessage> history = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                ChatMessage msg = new ChatMessage();
                msg.readExternal(ois);
                history.add(msg);
            }
        }
        return history;
    }
}