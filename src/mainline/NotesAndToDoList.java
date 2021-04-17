package mainline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

/*******
 * <p>
 * Title: UserInterfaceOfContacts Class.
 * </p>
 *
 * <p>
 * Description: A JavaFX demonstration application: This controller class
 * describes the user interface for the Notes and To-Do List for PKMT-II
 * application
 * </p>
 *
 * <p>
 * Copyright: Lynn Robert Carter © 2018-08-26
 * </p>
 *
 * @author Neaz Morshed
 * 
 *
 * @version 2.00 2018-07-19 Baseline
 * @version 3.00 2018-11-12 Notes and To-Do List Items GUI implementation.
 */
public class NotesAndToDoList {

	/**********************************************************************************************
	 * 
	 * Class Attributes
	 * 
	 **********************************************************************************************/

	// Attributes used to establish the display and control panel within the window
	// provided to us
	private static final double THIS_WINDOW_HEIGHT = Mainline.WINDOW_HEIGHT;
	private static final double CONTROL_PANEL_HEIGHT = THIS_WINDOW_HEIGHT - 180;
	private static final double THIS_WINDOW_WIDTH = Mainline.WINDOW_WIDTH;

	// These attributes put a graphical frame around the portion of the window that
	// receives the
	// black squares representing alive cells
	private Rectangle rect_outer = new Rectangle(0, 0, THIS_WINDOW_WIDTH, CONTROL_PANEL_HEIGHT - 5);
	private Rectangle rect_middle = new Rectangle(5, 5, THIS_WINDOW_WIDTH - 10, CONTROL_PANEL_HEIGHT - 15);
	private Rectangle rect_inner = new Rectangle(6, 6, THIS_WINDOW_WIDTH - 12, CONTROL_PANEL_HEIGHT - 17);
	// This attribute holds the text that will be displayed
	private TextArea blk_Text = new TextArea();
	private TextArea blk_Text2 = new TextArea();
	RadioButton r1 = new RadioButton("Notes");
	RadioButton r2 = new RadioButton("To-Do Items");
	public Label lbl_1SelectDefinition = new Label();
	public Label lbl_3SaveDefinition = new Label();

	final Stage editDialog = new Stage();
	private Button button_save = new Button("Save Notes");
	private Button button_addTask = new Button("Save To Do");

	/**********************************************************************************************
	 * 
	 * Constructors
	 * 
	 **********************************************************************************************/

	/**********
	 * This constructor established the user interface with all of the graphical
	 * widgets that are use to make the user interface work.
	 *
	 * @param theRoot This parameter is the Pane that JavaFX expects the application
	 *                to use when it sets up the GUI elements.
	 */
	public NotesAndToDoList(Pane theRoot) {
		// button_Notes.setDisable(true);
		// Set the fill colors for the border frame for the game's output of the
		// simulation
		rect_outer.setFill(Color.LIGHTGRAY);
		rect_middle.setFill(Color.BLACK);
		rect_inner.setFill(Color.WHITE);

		// Place a text area into the window and just within the above frame and make it
		// not editable
		setupTextAreaUI(blk_Text, "Monaco", 14, 6, 6, THIS_WINDOW_WIDTH - 12, CONTROL_PANEL_HEIGHT - 17, true);
		// Place a text area into the window and just within the above frame and make it
		// not editable
		setupTextAreaUI(blk_Text2, "Monaco", 14, 6, 6, THIS_WINDOW_WIDTH - 12, CONTROL_PANEL_HEIGHT - 17, true);

		blk_Text.setVisible(false);
		blk_Text2.setVisible(false);

		// Setup the radio button for Notes.
		setupButtonUI(button_save, "Arial", 18, 120, Pos.BASELINE_LEFT, Mainline.WINDOW_WIDTH - 900,
				CONTROL_PANEL_HEIGHT + 60);

		button_save.setOnAction((event) -> {
			saveTheChanges();
		});

		// Setup the radio button for To-Do List.
		setupRadioButtonUI(r1, "Arial", 18, 120, Pos.BASELINE_LEFT, Mainline.WINDOW_WIDTH - 500,
				CONTROL_PANEL_HEIGHT + 60);

		r1.setOnAction((event) -> {
			blk_Text2.setVisible(false);
			blk_Text.setVisible(true);
			r2.setSelected(false);
			button_addTask.setVisible(false);
			button_save.setVisible(true);
		});

		// Setup the Load button to load the file which display the diction in the
		// window and console.
		setupRadioButtonUI(r2, "Arial", 18, 120, Pos.BASELINE_LEFT, Mainline.WINDOW_WIDTH - 250,
				CONTROL_PANEL_HEIGHT + 60);

		// Setup the radio button for Notes.
		setupButtonUI(button_addTask, "Arial", 18, 120, Pos.BASELINE_LEFT, Mainline.WINDOW_WIDTH - 900,
				CONTROL_PANEL_HEIGHT + 100);

		button_addTask.setOnAction((event) -> {
			saveTheChanges();
		});

		r2.setOnAction((event) -> {

			blk_Text.setVisible(false);
			blk_Text2.setVisible(true);
			r1.setSelected(false);
			button_addTask.setVisible(true);
			button_save.setVisible(false);
		});

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(rect_outer, rect_middle, rect_inner, blk_Text, button_save, blk_Text2, r1, r2,
				button_addTask);
	}

	/**********************************************************************************************
	 * 
	 * Helper methods - Used to set up the JavaFX widgets and simplify the code
	 * above
	 * 
	 **********************************************************************************************/

	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextAreaUI(TextArea t, String ff, double f, double x, double y, double w, double h, boolean e) {
		t.setFont(Font.font(ff, f));
		t.setPrefWidth(w);
		t.setPrefHeight(h);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
		t.setWrapText(true);
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y) {
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a Radio Buttons
	 */
	private void setupRadioButtonUI(RadioButton r22, String ff, double f, double w, Pos p, double x, double y) {
		r22.setFont(Font.font(ff, f));
		r22.setMinWidth(w);
		r22.setAlignment(p);
		r22.setLayoutX(x);
		r22.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********************************************************************************************
	 * 
	 * Action methods - Used cause things to happen during the execution of the
	 * application
	 * 
	 * @return
	 * 
	 **********************************************************************************************/
	public void initiatetodo() {
		blk_Text2.setVisible(true);
		blk_Text.setVisible(false);
	}

	public void initiateNotes() {
		blk_Text.setVisible(true);
		blk_Text2.setVisible(false);
	}

	public void onSelection() {
		// This will reference one line at a time.
		String line = null;

		// FileReader reads text files in the default encoding.
		FileReader fileReader;
		FileReader fileToDoList;

		File Notes = new File("RepositoryOut/Notes.txt");
		File ToDoList = new File("RepositoryOut/ToDoList.txt");

		if (Notes.exists() && ToDoList.exists())
			try {
				fileReader = new FileReader("RepositoryOut/Notes.txt");
				fileToDoList = new FileReader("RepositoryOut/ToDoList.txt");

				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				BufferedReader buff = new BufferedReader(fileToDoList);

				try {
					while ((line = bufferedReader.readLine()) != null) {

						blk_Text.appendText(line);
						blk_Text.appendText("\n");

					}

					while ((line = buff.readLine()) != null) {
						blk_Text2.appendText(line);
						blk_Text2.appendText("\n");
					}

					// Always close files.
					bufferedReader.close();
					buff.close();
					fileReader.close();
					fileToDoList.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public void addATaskPopUp() {

		// Set up the pop-up modal dialogue window
		editDialog.setTitle("Add Task");
		Group dictionaryEditControls = new Group();

		TextField task = new TextField("Enter the Task");
		task.clear();

		// Set up the pop-up window
		Scene dialogScene = new Scene(dictionaryEditControls, 500, 130);

		// Set up the fields for the edit pop-up window
		setupLabelUI(lbl_1SelectDefinition, "Arial", 14, 100, Pos.BASELINE_LEFT, 10, 10);
		setupLabelUI(lbl_3SaveDefinition, "Arial", 14, 100, Pos.BASELINE_LEFT, 10, 80);

		lbl_1SelectDefinition.setText("Enter the task");
		lbl_3SaveDefinition.setText("Save the task");

		// Set up the comboBox to select one of the dictionary items that matched
		task.setLayoutX(25);
		task.setLayoutY(35);

		// Set the screen so we can see it
		editDialog.setScene(dialogScene);

		// Load in the current word and definition data into the edit fields
		setupButtonUI(button_save, "Arial", 12, 50, Pos.BASELINE_LEFT, 30, 100);
		button_save.setText("Add Task");
		button_save.setOnAction((event) -> {
			blk_Text2.appendText("\n>" + task.getText());
			blk_Text2.setEditable(false);
			button_save.setVisible(true);
			editDialog.hide();
			editDialog.close();

		});

		// Populate the pop-up window with the GUI elements
		dictionaryEditControls.getChildren().addAll(lbl_1SelectDefinition, task, lbl_3SaveDefinition, button_save);

		// Show the pop-up window
		editDialog.show();
	}

	/*****
	 * This method saves the Changes made in the todo or notes section
	 */
	public void saveTheChanges() {
		FileWriter ToDoList;
		try {
			ToDoList = new FileWriter("RepositoryOut/ToDoList.txt");
			BufferedWriter write_ToDoList = new BufferedWriter(ToDoList);
			
			Scanner toDo = new Scanner(blk_Text2.getText().toString());
			if (toDo.hasNextLine()) {
				// toDo.nextLine();
				blk_Text2.setText("");
				while (toDo.hasNextLine()) {
					write_ToDoList.append(toDo.nextLine());
					write_ToDoList.newLine();
					// toDo.nextLine();
				}
			}			
			// Closing BufferWriter to end operation
			write_ToDoList.close();
			toDo.close();
		} catch (IOException excpt) {
			excpt.printStackTrace();
		}
	}
	
	

}