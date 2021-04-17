package mainline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import addresses.ParentAddress;
import addresses.IndiaAddress;
import addresses.QatarBusinessAddress;
import addresses.UnitedStatesAddress;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

/*******
 * <p>
 * Title: UserInterface Class.
 * </p>
 * 
 * <p>
 * Description: This controller class describes the user interface for the
 * address part and reveals the contacts on the pane.
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
 * @version 3.00 2019-11-12 It is a User Interface designed for Contacts Tab.
 */

public class Contact {

	/**********************************************************************************************
	 * Class Attributes
	 **********************************************************************************************/

	// Attributes used to establish the display and control panel within the window
	// provided to us
	private static final double THIS_WINDOW_HEIGHT = Mainline.WINDOW_HEIGHT;
	private static final double CONTROL_PANEL_HEIGHT = THIS_WINDOW_HEIGHT - 150;
	private static final double THIS_WINDOW_WIDTH = Mainline.WINDOW_WIDTH;

	// These attributes put a graphical frame around the portion of the window that
	// receives the
	// black squares representing alive cells
	private Rectangle rect_outer = new Rectangle(0, 0, THIS_WINDOW_WIDTH, CONTROL_PANEL_HEIGHT - 5);
	private Rectangle rect_middle = new Rectangle(5, 5, THIS_WINDOW_WIDTH - 10, CONTROL_PANEL_HEIGHT - 15);
	private Rectangle rect_inner = new Rectangle(6, 6, THIS_WINDOW_WIDTH - 12, CONTROL_PANEL_HEIGHT - 17);

	// This attribute holds the text that will be displayed
	private TextArea blk_Text = new TextArea("");

	private Button button_Add = new Button("Add Contact");
	private Button button_save = new Button("Display Saved Address");
	private Button button_search = new Button("Search");

	private Label label_name = new Label("Person Name: ");
	private TextField text_name = new TextField();

	private Button Search_button = new Button("Search");
	private Button Cancel_Button = new Button("Cancel");
	private Label message = new Label("You can Delete in the Field");
	private Label message1 = new Label("You can Edit in the Field");
	private Button button_delete = new Button("Delete");
	private Button button_edit = new Button("Edit");
	private Button button_Save = new Button("Save this address");

	/**********************************************************************************************
	 * Constructors
	 **********************************************************************************************/

	/**********
	 * This constructor established the user interface with all of the graphical
	 * widgets that are use to make the user interface work.
	 * 
	 * @param theRoot This parameter is the Pane that JavaFX expects the application
	 *                to use when it sets up the GUI elements.
	 */
	public Contact(Pane theRoot) {

		// Set the fill colors for the border frame for the game's output of the
		// simulation
		rect_outer.setFill(Color.LIGHTGRAY);
		rect_middle.setFill(Color.BLACK);
		rect_inner.setFill(Color.WHITE);

		// Place a text area into the window and just within the above frame and make it
		// not editable
		setupTextAreaUI(blk_Text, "Monaco", 14, 6, 6, THIS_WINDOW_WIDTH - 12, CONTROL_PANEL_HEIGHT - 17, false);

		button_Add.setOnAction((event) -> { initiateAdding();	});
		button_Add.setLayoutX(850);
		button_Add.setLayoutY(500);

		button_save.setOnAction((event) -> {
			/* Need to enter the method name for addition of word */ try {
				appendAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		button_save.setLayoutX(10);
		button_save.setLayoutY(470);

		button_search.setOnAction((event) -> {
			/* Need to enter the method name for addition of word */ search_box();
		});
		button_search.setLayoutX(450);
		button_search.setLayoutY(500);

		button_delete.setOnAction((event) -> {
			/* Need to enter the method name for addition of word */ deleteAddres();
		});
		
		button_edit.setOnAction((event) -> {
			/* Need to enter the method name for addition of word */ editAddress();
		});
		button_delete.setLayoutX(650);
		button_delete.setLayoutY(500);
		button_edit.setLayoutX(550);
		button_edit.setLayoutY(500);

		// Place all of the just-initialized GUI elements into the pane with the
		// exception of the
		// Stop button. That widget will replace the Start button, once the Start has
		// been pressed
		theRoot.getChildren().addAll(rect_outer, button_search, message,message1, rect_middle, button_Save, button_delete,
				rect_inner, blk_Text, button_Add, button_save, button_edit);

	}

	/**********************************************************************************************
	 * Helper methods - Used to set up the JavaFX widgets and simplify the code
	 * above
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

	/**********************************************************************************************
	 * Action methods - Used cause things to happen during the execution of the
	 * application
	 **********************************************************************************************/

	/**
	 * This method takes an address object as a parameter and appends it to the end
	 * of the text currently displayed in the JavaFX window and then adds a line of
	 * dashes after the address as shown above.
	 *
	 * @param lrcarterAddress holds the adress of United States
	 * @param cmuQatarAddress the address of the QatarBusiness Address
	 * @param mmuAddress      holds the address of the India
	 * @param parentAddress   hold the parents adress
	 *
	 */

	private void deleteAddres() {
		blk_Text.setEditable(true);

		final double MAX_FONT_SIZE = 25.0;
		message.setFont(new Font(MAX_FONT_SIZE));

		message.setLayoutX(250);
		message.setLayoutY(450);
		message.setTextFill(Color.web("Green"));
		button_Add.setVisible(false);
		button_Save.setLayoutX(850);
		button_Save.setLayoutY(500);
		button_Save.setVisible(true);
		button_search.setVisible(false);
		button_save.setVisible(true);
		button_Save.setOnAction((event) -> {
			saveDeleteAddress();
		});

	}

	private void saveDeleteAddress() {
		blk_Text.setEditable(false);
		message.setVisible(false);
		button_Save.setVisible(false);
		button_Add.setVisible(true);
		button_search.setVisible(true);

		try {
			String newData = blk_Text.getText();
			String filepath = "RepositoryOut/contacts.txt";
			PrintWriter out = new PrintWriter(new FileOutputStream(filepath));
			out.write(newData);
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void editAddress() {
		blk_Text.setEditable(true);

		final double MAX_FONT_SIZE = 25.0;
		message1.setFont(new Font(MAX_FONT_SIZE));

		message1.setLayoutX(250);
		message1.setLayoutY(450);
		message1.setTextFill(Color.web("Green"));
		button_Add.setVisible(false);
		button_Save.setLayoutX(850);
		button_Save.setLayoutY(500);
		button_Save.setVisible(true);
		button_search.setVisible(false);
		button_save.setVisible(true);
		button_Save.setOnAction((event) -> {
			saveeditAddress();
		});

	}

	private void saveeditAddress() {
		blk_Text.setEditable(false);
		message1.setVisible(false);
		button_Save.setVisible(false);
		button_Add.setVisible(true);
		button_search.setVisible(true);

		try {
			String newData = blk_Text.getText();
			String filepath = "RepositoryOut/contacts.txt";
			PrintWriter out = new PrintWriter(new FileOutputStream(filepath));
			out.write(newData);
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void appendAddress(UnitedStatesAddress lrcarterAddress, QatarBusinessAddress cmuQatarAddress,
			IndiaAddress mmuAddress, ParentAddress HomeAddress) {

		// Prints the address on the console using the attribute that holds the task to
		// display
		blk_Text.setText(lrcarterAddress + "\n" + "----------" + "\n" + cmuQatarAddress + "\n" + "----------" + "\n"
				+ mmuAddress + "\n" + "----------" + "\n" + HomeAddress + "\n" + "----------");
	}

	public static String readFileAsString() throws Exception {

		String data = "";
		File f = new File("RepositoryOut/contacts.txt");
		Scanner sc = new Scanner(f);
		while (sc.hasNext()) data+=sc.nextLine()+"\n";
		sc.close();
		return data;
	}

	public void appendAddress() throws Exception {
		blk_Text.setText("");
		String data = readFileAsString();
		blk_Text.appendText(data);
	}

	public void search_box() {

		Stage theStage = new Stage();

		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(500, 300);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(label_name, 0, 1);
		gridPane.add(text_name, 1, 1);
		text_name.setMinWidth(300);

		gridPane.add(Search_button, 1, 9);
		gridPane.add(Cancel_Button, 2, 9);

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Setting title to the Stage
		theStage.setTitle("Search Contacts");

		// Adding scene to the stage
		theStage.setScene(scene);

		// Displaying the contents of the stage
		theStage.show();

		Search_button.setOnAction((event) -> {
			searchstring();
			theStage.hide();

		});

		Cancel_Button.setOnAction((event) -> {
			theStage.hide();
		});
	}

	public void searchstring() {
		String str = blk_Text.getText();
		String key = text_name.getText();
		if(str.indexOf(key)!=-1) {
			int i = str.indexOf(key);
			String search = str.substring(i);
			blk_Text.setText(search);
		}
	}

	private void initiateAdding() {
		Stage theStage = new Stage();
		Label label_name = new Label("Name");
		TextField text_name = new TextField();
		Label label_address = new Label("Address");
		TextField text_address = new TextField();
		Label label_city = new Label("City");
		TextField text_city = new TextField();
		Label label_pincode = new Label("Pin Code");
		TextField text_pincode = new TextField();
		Label label_state = new Label("State");
		TextField text_state = new TextField();
		Label label_country = new Label("Country");
		TextField text_country = new TextField();

		// Creating Buttons
		Button Save_button = new Button("Save This Address");
		Button Cancel_Button = new Button("Cancel");

		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(500, 300);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid
		// gridPane.add(text1, 0, 0);
		// gridPane.add(textField1, 1, 0);
		gridPane.add(label_name, 0, 1);
		gridPane.add(text_name, 1, 1);
		gridPane.add(label_address, 0, 2);
		gridPane.add(text_address, 1, 2);
		gridPane.add(label_city, 0, 3);
		gridPane.add(text_city, 1, 3);
		gridPane.add(label_pincode, 0, 4);
		gridPane.add(text_pincode, 1, 4);
		gridPane.add(label_state, 0, 5);
		gridPane.add(text_state, 1, 5);
		gridPane.add(label_country, 0, 6);
		gridPane.add(text_country, 1, 6);

		// gridPane.add(textField2, 1, 1);
		gridPane.add(Save_button, 1, 9);
		gridPane.add(Cancel_Button, 2, 9);

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Setting title to the Stage
		theStage.setTitle("Add window");

		// Adding scene to the stage
		theStage.setScene(scene);

		// Displaying the contents of the stage
		theStage.show();
		Cancel_Button.setOnAction((event) -> {
			theStage.hide();
		});

		Save_button.setOnAction((event) -> {
			String FILENAME = "RepositoryOut/contacts.txt";
			BufferedWriter bw_name = null;
			BufferedWriter bw_address = null;
			BufferedWriter bw_city = null;
			BufferedWriter bw_pincode = null;
			BufferedWriter bw_state = null;
			BufferedWriter bw_country = null;
			FileWriter fw_name = null;
			FileWriter fw_address = null;
			FileWriter fw_city = null;
			FileWriter fw_pincode = null;
			FileWriter fw_state = null;
			FileWriter fw_country = null;
			try {

				String data_name = "\n" + text_name.getText() + ",";
				String data_address = "\n" + text_address.getText() + ",";
				String data_city = "\n" + text_city.getText() + ",";
				String data_pincode = text_pincode.getText() + " ";
				String data_state = "(" + text_state.getText() + ")";
				String data_country = "\n" + text_country.getText() + ",";

				File file = new File(FILENAME);

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				// true = append file
				fw_name = new FileWriter(file.getAbsoluteFile(), true);
				fw_address = new FileWriter(file.getAbsoluteFile(), true);
				fw_city = new FileWriter(file.getAbsoluteFile(), true);
				fw_pincode = new FileWriter(file.getAbsoluteFile(), true);
				fw_state = new FileWriter(file.getAbsoluteFile(), true);
				fw_country = new FileWriter(file.getAbsoluteFile(), true);
	
				bw_name = new BufferedWriter(fw_name);
				bw_address = new BufferedWriter(fw_address);
				bw_city = new BufferedWriter(fw_city);
				bw_pincode = new BufferedWriter(fw_pincode);
				bw_state = new BufferedWriter(fw_state);
				bw_country = new BufferedWriter(fw_country);
				
				bw_name.write(data_name);
				bw_address.write(data_address);
				bw_city.write(data_city);
				bw_pincode.write(data_pincode);
				bw_state.write(data_state);
				bw_country.write(data_country);
				theStage.show();
				theStage.hide();
				theStage.close();

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw_name != null)
						bw_name.close();

					if (fw_name != null)
						fw_name.close();

					if (bw_address != null)
						bw_address.close();

					if (fw_address != null)
						fw_address.close();

					if (bw_city != null)
						bw_city.close();

					if (fw_city != null)
						fw_city.close();

					if (bw_pincode != null)
						bw_pincode.close();

					if (fw_pincode != null)
						fw_pincode.close();

					if (bw_state != null)
						bw_state.close();

					if (fw_state != null)
						fw_state.close();

					if (bw_country != null)
						bw_country.close();

					if (fw_country != null)
						fw_country.close();


				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}
		});
	}
}
