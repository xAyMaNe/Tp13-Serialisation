package sérialisationavancée;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Instant;

/**
 * Représente un message de chat sérialisé manuellement.
 * Implémente Externalizable pour contrôler exactement
 * ce qui est lu/écrit.
 */
public class ChatMessage implements Externalizable {
    // Version du format de sérialisation
    private static final long serialVersionUID = 2L;
    private static final int    FORMAT_VERSION = 1;

    private String user;           // nom d’utilisateur
    private String message;        // contenu du message
    private Instant timestamp;     // date d’envoi
    private transient int length;  // taille du message, non stockée

    /** Constructeur public sans argument requis par Externalizable */
    public ChatMessage() { }

    public ChatMessage(String user, String message) {
        this.user      = user;
        this.message   = message;
        this.timestamp = Instant.now();
        this.length    = message.length();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Écrire la version pour évoluer le format si besoin
        out.writeInt(FORMAT_VERSION);

        // Sérialiser manuellement les champs souhaités
        out.writeUTF(user);
        out.writeUTF(message);
        out.writeLong(timestamp.toEpochMilli());
        // length est dérivé de message, on ne l’écrit pas
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int version = in.readInt();
        if (version != FORMAT_VERSION) {
            throw new IOException("Version de format inconnue: " + version);
        }

        // Reconstruire les champs dans le même ordre
        user      = in.readUTF();
        message   = in.readUTF();
        timestamp = Instant.ofEpochMilli(in.readLong());
        length    = message.length();
    }

    // Getters pour affichage
    public String getUser()       { return user; }
    public String getMessage()    { return message; }
    public Instant getTimestamp() { return timestamp; }
    public int getLength()        { return length; }

    @Override
    public String toString() {
        return String.format("%s [%s]: %s", 
            timestamp, user, message);
    }
}