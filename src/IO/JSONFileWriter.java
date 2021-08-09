package IO;
import Notes.INote;
import Notes.Note;
import Notes.NoteGroup;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWriter {
	
	private JSONObject obj;

	//The constructor
	public JSONFileWriter() {
		obj = new JSONObject();
	}


	/**
	 * Method creates json file and writes given String parameter into that json file.
	 * @param String
	 */
	private void writeJSONFile(String input) {
		obj.put("",input);
		try {
			FileWriter fileWriter = new FileWriter("src\\IO\\Notes.json");
			fileWriter.write(obj.toString());
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method creates json file and writes given JSONArray parameter into that json file.
	 * @param JSONArray
	 */
	private void writeJSONFile(JSONArray input) {
		obj.put("",input);
		try {
			FileWriter fileWriter = new FileWriter("src\\IO\\Notes.json");
			fileWriter.write(obj.toString());
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that creates and returns JSONObject from given INote object.
	 * @param INote
	 * @return JSONObject
	 */
	private JSONObject createNoteObject(INote note) {
		JSONObject noteObject = new JSONObject();
		if(note.isNote()) {
			noteObject.put("ID",note.getId());
			noteObject.put("Title",note.getTitle());
			noteObject.put("Content",((Note) note).getContent());
			noteObject.put("State",((Note) note).getState().toString());
		}
		else{
			noteObject.put("ID",note.getId());
			noteObject.put("Title",note.getTitle());
		}

		return noteObject;
	}
	/**
	 * Method that creates JSONArray from given NoteGroup branch and returns it.
	 * @param noteGroup
	 * @return JSONArray
	 */
	private JSONArray createNotesAsJson(NoteGroup noteGroup) {
		JSONArray noteGroupObject = new JSONArray();
		noteGroupObject.put(createNoteObject(noteGroup));	//id == 0 ise koyma !!!
		for(INote note: noteGroup.getSubNotes()) {
			if(note.isNote()) {
				JSONObject tempNote = createNoteObject(note);
				noteGroupObject.put(tempNote);
			}
			else{
				JSONArray tempNoteGroup = createNotesAsJson((NoteGroup) note);
				noteGroupObject.put(tempNoteGroup);
			}
		}
		return noteGroupObject;
	}

	/**
	 * Method that writes given NoteGroup branch into json file.
	 * @param NoteGroup
	 */
	public void saveAsJSON(NoteGroup notes) {
		JSONArray array = createNotesAsJson(notes);
		writeJSONFile(array);
	}

}
