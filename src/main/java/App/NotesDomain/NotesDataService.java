package App.NotesDomain;

import java.util.Map;

public interface NotesDataService {
    void save(Note note);

    Map<Integer, Note> loadAll();

    void deleteNoteFile(Note note);
}
