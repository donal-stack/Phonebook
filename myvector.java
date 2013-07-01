//Name:				Donal Stack
//Student Number:	B00056412
//Date:				10/12/2012
//Title:			Program that creates and modifies a phonebook using vector methods.

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class myvector extends JFrame
{
	private JLabel statusLabel;
	private JLabel inputLabel1;
	private JLabel inputLabel2;
	private Vector vector;
	private JTextField inputField1;
	private NumericTextField inputField2;


	//Constructor
	public myvector()
	{
		super("Phonebook");	//titles the program GUI

		//container to add labels, textfields and buttons
		Container container = getContentPane();

		//create vector for phonebook
		vector = new Vector(10,5);

		//create and position labels and textfields
		inputLabel1 = new JLabel( "Enter Contact Name" );
		inputLabel1.setBounds(10, 30, 120, 30);
		container.add(inputLabel1);
		inputField1 = new JTextField(10);
		inputField1.setBounds(140, 30, 100, 30);
		container.add(inputField1);


		inputLabel2 = new JLabel( "Enter Phone Number" );
		inputLabel2.setBounds(260, 30, 120, 30);
		container.add(inputLabel2);
		inputField2 = new NumericTextField();
		inputField2.setColumns(10);
		inputField2.setBounds(390, 30, 100, 30);
		container.add(inputField2);

		statusLabel = new JLabel();


		//button to add contacts to the phonebook
		JButton addButton = new JButton("Add Contact");

		addButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//add an element to vector
					vector.addElement( inputField1.getText() + " " + inputField2.getText() );

					statusLabel.setText( inputField1.getText() + " has been added to your phonebook" );
					inputField1.setText("");
					inputField2.setText("");

				}
			}
		); //ends call to the addActionListener

		addButton.setBounds(20, 80, 150, 30);
		container.add(addButton);


		//button to remove contact from the phonebook
		JButton removeButton = new JButton("Remove Contact");

		removeButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//remove an element from vector
					if ( vector.removeElement( inputField1.getText() + " " + inputField2.getText() ) )
					statusLabel.setText(inputField1.getText() + " has been removed from your phonebook" );

					else
					statusLabel.setText(inputField1.getText() + " is not in your phonebook");
				}
			}
		); //ends call to the addActionListener

		removeButton.setBounds(180, 80, 140, 30);
		container.add(removeButton);


		//button to see if there is information already in the phonebook
		JButton infoButton = new JButton("Any Contacts?");

		infoButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//determine if contacts in the phonebook
					statusLabel.setText( vector.isEmpty() ?
					"No contacts in your Phonebook" : "There are contacts in your Phonebook");
				}
			}
		); //ends call to the addActionListener

		infoButton.setBounds(330, 80, 140, 30);
		container.add(infoButton);


		//button to clear the phonebook
		JButton clearButton = new JButton("Clear Phonebook");

		clearButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//clean phonebook
					vector.clear();
					statusLabel.setText("All contacts cleared from phonebook");
				}
			}
		); //ends call to the addActionListener

		clearButton.setBounds(330, 300, 140, 30);
		container.add(clearButton);


		//button to show the how many contacts in the phonebook
		JButton sizeButton = new JButton("Number of Contacts");

		sizeButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//get current size of the phonebook
					statusLabel.setText("Current number of contacts in the phonebook " + vector.size());
				}
			}
		); //ends call to the addActionListener

		sizeButton.setBounds(20, 130, 150, 30);
		container.add(sizeButton);


		//button to show the phonebooks current capacity
		JButton capacityButton = new JButton("Entries Available");

		capacityButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//get current capacity of the phonebook
					statusLabel.setText("Current phonebook capacity is " + vector.capacity());
				}
			}
		); //ends call to the addActionListener

		capacityButton.setBounds(180, 130, 140, 30);
		container.add(capacityButton);


		//button to trim amount of contact entries to current size
		JButton trimButton = new JButton("Trim Phonebook");

		trimButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//remove unoccupied elements to save memory
					vector.trimToSize();
					statusLabel.setText("Phonebook trimmed to size");
				}
			}
		); //ends call to the addActionListener

		trimButton.setBounds(330, 130, 140, 30);
		container.add(trimButton);


		//button to display all contacts in the phonebook
		JButton displayButton = new JButton("Phonebook");

		displayButton.addActionListener(

			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//use Enumeration to output the phonebook contacts
					Enumeration enumer = vector.elements();
					StringBuffer buf = new StringBuffer();

					while ( enumer.hasMoreElements() )
						buf.append( enumer.nextElement() ).append("\n");


					JOptionPane.showMessageDialog( null,
						"Contacts:" + "\n" + buf.toString(), "Contacts",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
		); //ends call to the addActionListener

		displayButton.setBounds(330, 180, 140, 30);
		container.add(displayButton);

		container.add(statusLabel);


		setSize(520, 400);
		setResizable(false);
		setVisible(true);
	}

	//execute phonebook application
	public static void main(String args[])
	{
		myvector application = new myvector();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class NumericTextField extends JTextField
{

    @Override
    protected Document createDefaultModel()
    {
        return new NumericDocument();
    }

    private static class NumericDocument extends PlainDocument
    {
        // The regular expression to match input against (zero or more digits)
        private final static Pattern DIGITS = Pattern.compile("\\d*");

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            // Only insert the text if it matches the regular expression
            if (str != null && DIGITS.matcher(str).matches())
                super.insertString(offs, str, a);
        }
    }
}
