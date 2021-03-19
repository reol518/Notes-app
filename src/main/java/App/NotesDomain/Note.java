package App.NotesDomain;

import java.io.Serial;
import java.io.Serializable;

public class Note implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;

    private String text;

    private final int id;

    private static int idCount = 0;

    public static void setIdCount(int idCount) {
        Note.idCount = idCount;
    }

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        id = ++idCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }
}
