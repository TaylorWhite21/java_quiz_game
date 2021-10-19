import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Quiz implements ActionListener{
	
	String[] questions = {
		"Who is a currently not an Overwatch character?",
		"What is the best selling videogame of all time?",
		"What did the actor Robin Williams name his daughter?",
		"What year was the Super Nintendo Entertainment System (SNES) released?",
		"In God of War(2018), What is Kratos' son named?",
		"What two keyblades does Roxas wield?"
						};
	String[][] options = {
		{"Mackenzie", "Tracer", "Rheinhardt", "Sombra"},
		{"Fortnite", "Minecraft", "Grand Theft Auto V", "Tetris" },
		{"Peach", "Daisy", "Samus", "Zelda"},
		{"1989", "1991", "1994", "1979"},
		{"Achilles", "Herakles", "Atreus", "Odysseus"},
		{"Oblivion & Oathkeeper", "Kingdom Key & Lionheart", "Way to the Dawn & Destiny's Embrace", "Fenrir & Two Become One"}
						};
	
	char[] answers = {
		'A',
		'B',
		'D',
		'B',
		'C',
		'A',
					};
	
	char guess;
	char answer;
	int index;
	int correct_guesses = 0;
	int total_questions = questions.length;
	int result;
	int seconds=10;
	
	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textArea = new JTextArea();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();
	
	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if (seconds <= 0) {
				displayAnswer();
			}
		}
	});
	
	public Quiz () {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);
		
		textfield.setBounds(0,0, 700, 50);
		textfield.setBackground(new Color(0,0,0));
		textfield.setForeground(new Color(200,5,5));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		textArea.setBounds(0,50, 700, 100);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(0,0,0));
		textArea.setForeground(new Color(200,5,5));
		textArea.setFont(new Font("MV Boli", Font.BOLD, 25));
		textArea.setBorder(BorderFactory.createBevelBorder(1));
		textArea.setEditable(false);
		
		buttonA.setBounds(0,150,100,100);
		buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");
		
		buttonB.setBounds(0,250,100,100);
		buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");
		
		buttonC.setBounds(0,350,100,100);
		buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");
		
		buttonD.setBounds(0,450,100,100);
		buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");
		
		answer_labelA.setBounds(125,150,500,100);
		answer_labelA.setBackground(new Color(50,50,50));
		answer_labelA.setForeground(new Color(255,255,255));
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelB.setBounds(125,250,500,100);
		answer_labelB.setBackground(new Color(50,50,50));
		answer_labelB.setForeground(new Color(255,255,255));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelC.setBounds(125,350,500,100);
		answer_labelC.setBackground(new Color(50,50,50));
		answer_labelC.setForeground(new Color(255,255,255));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelD.setBounds(125,450,500,100);
		answer_labelD.setBackground(new Color(50,50,50));
		answer_labelD.setForeground(new Color(255,255,255));
		answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		seconds_left.setBounds(535, 510, 100,100);
		seconds_left.setBackground(new Color(25,25,25));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("MV Boli", Font.BOLD, 60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		time_label.setBounds(535,475,100,25);
		time_label.setBackground(new Color(50,50,50));
		time_label.setForeground(new Color(255,0,0));
		time_label.setFont(new Font("MV Boli", Font.PLAIN, 20));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("Timer >:D");
		
		number_right.setBounds(225,225,200,100);
		number_right.setBackground(new Color(25,25,25));
		number_right.setForeground(new Color(255,0,0));
		number_right.setFont(new Font("MV Boli", Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		percentage.setBounds(225,325,200,100);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(255,0,0));
		percentage.setFont(new Font("MV Boli", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(seconds_left);
		frame.add(time_label);
//		frame.add(number_right);
//		frame.add(percentage);
		
		frame.add(textArea);
		frame.add(textfield);
		frame.setVisible(true);
		
		nextQuestion();
	}
	
	public void nextQuestion() {
		
		if(index >= total_questions) {
			results();
		} else {
			textfield.setText("Question " + (index+1));
			textArea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(e.getSource()==buttonA) {
			answer= 'A';
			if (answer == answers[index]) {
				correct_guesses++;
			}
			
		}
		
		if(e.getSource()==buttonB) {
			answer= 'B';
			if (answer == answers[index]) {
				correct_guesses++;
			}
			
		}
		
		if(e.getSource()==buttonC) {
			answer= 'C';
			if (answer == answers[index]) {
				correct_guesses++;
			}
			
		}
		
		if(e.getSource()==buttonD) {
			answer= 'D';
			if (answer == answers[index]) {
				correct_guesses++;
			}
			
		}
		displayAnswer();
		
	}
	
	public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(answers[index] != 'A') {
			answer_labelA.setForeground(new Color (255,0,0));
		}
		
		if(answers[index] != 'B') {
			answer_labelB.setForeground(new Color (255,0,0));
		}
		
		if(answers[index] != 'C') {
			answer_labelC.setForeground(new Color (255,0,0));
		}
		
		if(answers[index] != 'D') {
			answer_labelD.setForeground(new Color (255,0,0));
		}
		
		Timer pause = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(255,255,255));
				answer_labelB.setForeground(new Color(255,255,255));
				answer_labelC.setForeground(new Color(255,255,255));
				answer_labelD.setForeground(new Color(255,255,255));
				
				answer = ' ';
				seconds=10;
				seconds_left.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	
	public void results() {
	
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		result = (int)((correct_guesses/(double)total_questions)*100);
		
		textfield.setText("RESULTS!");
		textArea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		percentage.setText(result+"%");
		
		frame.add(percentage);
		frame.add(number_right);
		
		
	}
	
}
