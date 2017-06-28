import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.*;
import java.lang.Object;

public class Start extends JApplet implements ActionListener {
	
	private JFrame frame;
	private JPanel todosPanel;
	private JButton todoAddButton;
	private JTextField todoField;
	private ArrayList<String> todos = new ArrayList<String>();
	private RemoveElement removeEl = new RemoveElement();
	private Start panel = this;

	
	// Public section
	
	public static void main(String[] args) {
		
	}

	public void init() {
		JLabel label = new JLabel("Todo list");
		label.setSize(300, 40);
		label.setLocation(23, 70);

		add(label);

		todoField = new JTextField();
		todoField.setSize(200, 40);
		todoField.setLocation(20, 20);
		todoField.setFocusTraversalKeysEnabled(false);
		todoField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
            	String value = todoField.getText();
            	int charCode = e.getKeyCode();
        		
        		if (value.length() == 0) {
        			todoAddButton.setEnabled(false);
        		} else {
        			todoAddButton.setEnabled(true);
        			
        			if (charCode == 10) {
        				addTodo();
        			}
        		}
            }

            @Override
            public void keyPressed(KeyEvent e) {}
        });

		todoField.setFocusable(true);
		todoField.requestFocusInWindow();

		todoAddButton = new JButton("+");
		todoAddButton.setSize(50, 40);
		todoAddButton.setLocation(240, 20);
		todoAddButton.setVisible(true);
		
		add(todoField);
		add(todoAddButton);

		todoField.setVisible(true);
		
		todoAddButton.addActionListener(this);
		todoAddButton.setEnabled(false);

		setLayout(new BorderLayout());
	}

	public void actionPerformed(ActionEvent e) {
		System.out.print(e.getSource());
		addTodo();
	}
	
	
	// Private section
	
	private void addTodo() {
		String value = todoField.getText();
		
		if (value == "") {
			return;
		}
		
		todoAddButton.setEnabled(false);
		
		todos.add(value);
		todoField.setText("");

		viewTodos();
	}
	
	private void viewTodos() {
		int labelHeight = 20;
		int y = 110;
		
		for (int i = 0; i < todos.size(); i++) {
			String todo = todos.get(i);
			int ii = i;
//			System.out.println(todo);
			JLabel label = new JLabel(todo);
			label.setVisible(true);
			label.setSize(300, labelHeight);
			label.setLocation(23, y);
			label.addMouseListener(new MouseListener() {

	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	System.out.println(todo + " " + ii);
	            	todos.remove(ii);
	            	panel.viewTodos();
	            }

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			add(label);
			
			y = y + labelHeight;
		}
		panel.repaint();
	}
}

class RemoveElement {

	public void removeButton(JButton button) {
		Container parent = button.getParent();
		parent.remove(button);
		parent.revalidate();
		parent.repaint();
	}
	
	public void removeLabel(JLabel label) {
		Container parent = label.getParent();
		parent.remove(label);
		parent.revalidate();
		parent.repaint();
	}
}
