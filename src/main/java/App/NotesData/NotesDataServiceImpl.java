package App.NotesData;

import App.NotesDomain.Note;
import App.NotesDomain.NotesDataService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NotesDataServiceImpl implements NotesDataService {
    private final String path = new File("").getAbsolutePath();

    @Override
    public void save(Note note) {
        FileOutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = new FileOutputStream(path + "/src/main/resources/savedNotes/" + note.getId() + ".ser", false);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(note);
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Note> loadAll() {
        File dir = new File(path + "/src/main/resources/savedNotes/");
        Map<Integer, Note> notes = new HashMap<>();

        for (File file : dir.listFiles()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Note note = (Note) objectInputStream.readObject();
                notes.put(note.getId(), note);
                Note.setIdCount(note.getId());
                objectInputStream.close();
                fileInputStream.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }

        return notes;
    }

    public void deleteNoteFile(Note note) {
        File file = new File(path + "/src/main/resources/savedNotes/" + note.getId() + ".ser");
        if (file.delete()) {
            System.out.println("удален");
        } else System.out.println("не удален");
    }
}
