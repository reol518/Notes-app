package App.NotesDomain;


import java.util.Map;
import java.util.Set;

public class Notes {
    // Integer - уникальный id заметки, Note - заметка
    private Map<Integer, Note> notesMap;

    private NotesDataService notesDataService;

    public Notes(NotesDataService notesDataService) {
        this.notesDataService = notesDataService;
        notesMap = notesDataService.loadAll();
    }

    public Map<Integer, Note> getNotesMap() {
        return notesMap;
    }

    public void setNotesMap(Map<Integer, Note> notesMap) {
        this.notesMap = notesMap;
    }

    public void addNote(Note note) {
        notesMap.put(note.getId(), note);
        notesDataService.save(note);
    }

    public Note getNote(int key) {
        return notesMap.get(key);
    }

    public void removeNote(int key) {
        notesDataService.deleteNoteFile(notesMap.get(key));
        notesMap.remove(key);
    }

    public Set<Integer> getKeySet() {
        return notesMap.keySet();
    }


}