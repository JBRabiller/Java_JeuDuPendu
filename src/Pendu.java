import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.Color;

public class Pendu extends JFrame {

	private JPanel contentPane;
	private JTextField textMot;
	private String MotTest;
	private String motAster = "";
	private int pos[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int erreur = 0;
	private boolean finV = false;
	private int tour = 0;
	private int tourMax;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pendu frame = new Pendu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// choix du mot aléatoire
	public static String choisirMot() {
		String Mot = null;
		String[] tabMot = {"charrue", "cheminee","cheval","avion","diplodocus","justice","bureau","pasteque","mathematique","aventure","consoles","dictionnaire",
				"histoire","estomac","baignoire", "parapluie", "sauvage", "casserole", "cuillere", "patate", "souris"};
		int nb = (int)Math.floor(Math.random()*(22));
		Mot = tabMot[nb];
		return Mot;
	}
	//vérification si la lettre saisie par l'utilisateur est accepté
	public static String verifLettre(String envoie) {
		String erreur ="";
		
		if (envoie.length()>1) {
			erreur = "Vous devez saisir une seule lettre";
		}else if ((envoie.charAt(0)>64 && envoie.charAt(0)<90) || (envoie.charAt(0)>96 && envoie.charAt(0)<123)) {
			erreur= "La lettre a bien été saisie";
		}else {
			erreur = "Vous avez saisie une mauvaise lettre";
		}
		
		return erreur;
	}
	//verification si le la lettre saisie par l'utilisateur est dans le mot du Pendu
	public static boolean verifLettre2(String mot, char lettre, int pos[]) {
		boolean vrai= false;
		for (int i=0;i<mot.length();i++) {
			if (mot.charAt(i) == lettre) {
				pos[i] +=1;
				vrai = true;
			}
		}
		
		
		return vrai;
	}
	
	// remplie le Mot afficher en asterisque en fonction des saisies utilisateurs
	public static String MotAste(String mot, int pos[]){
		String MotAst = "";
		MotAst += mot.charAt(0);
		for (int i=1; i<mot.length();i++) {
			if (pos[i] != 0) {
				MotAst += mot.charAt(i);
			}
			else {
				MotAst += "*";
			}
		}
		return MotAst;
	}
	//verification si l'utilisateur à trouver le mot
	public static boolean verifFin(String mot, String motVerif) {
		boolean fin = false;
		fin = mot.equals(motVerif);
		
		return fin;
	}
	
	/**
	 * Create the frame.
	 */
	public Pendu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jeu du Pendu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(214, 23, 266, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblMot = new JLabel("");
		
		lblMot.setHorizontalAlignment(SwingConstants.CENTER);
		lblMot.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMot.setBounds(182, 136, 329, 50);
		contentPane.add(lblMot);
		
		textMot = new JTextField();
		textMot.setBounds(227, 237, 212, 50);
		contentPane.add(textMot);
		textMot.setColumns(10);
		
		JLabel lblSaisirLettre = new JLabel("Saisir lettre :");
		lblSaisirLettre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaisirLettre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSaisirLettre.setBounds(43, 240, 150, 34);
		contentPane.add(lblSaisirLettre);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage.setBounds(465, 212, 260, 32);
		contentPane.add(lblMessage);
		
		JLabel lblNewLabel_1 = new JLabel("Mot :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(43, 150, 88, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNombre = new JLabel("Nombre d'erreurs :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNombre.setBounds(21, 372, 202, 34);
		contentPane.add(lblNombre);
		
		JLabel lblErreur = new JLabel("0");
		lblErreur.setForeground(Color.RED);
		lblErreur.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErreur.setBounds(264, 372, 57, 32);
		contentPane.add(lblErreur);
		
		JLabel lblLettre = new JLabel("Lettres test\u00E9 :");
		lblLettre.setHorizontalAlignment(SwingConstants.CENTER);
		lblLettre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLettre.setBounds(352, 372, 159, 34);
		contentPane.add(lblLettre);
		
		JLabel lblLettresTest = new JLabel("-");
		lblLettresTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblLettresTest.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLettresTest.setBounds(521, 372, 204, 34);
		contentPane.add(lblLettresTest);
		
		JLabel lblTourMaxE = new JLabel("Tour max :");
		lblTourMaxE.setForeground(Color.BLACK);
		lblTourMaxE.setHorizontalAlignment(SwingConstants.CENTER);
		lblTourMaxE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTourMaxE.setBounds(317, 78, 150, 32);
		contentPane.add(lblTourMaxE);
		
		JLabel lblTourE = new JLabel("Tour : ");
		lblTourE.setHorizontalAlignment(SwingConstants.CENTER);
		lblTourE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTourE.setBounds(521, 78, 77, 32);
		contentPane.add(lblTourE);
		
		JLabel lblTour = new JLabel("0");
		lblTour.setForeground(Color.RED);
		lblTour.setHorizontalAlignment(SwingConstants.LEFT);
		lblTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTour.setBounds(594, 78, 77, 32);
		contentPane.add(lblTour);
		
		JLabel lblTourMax = new JLabel("0");
		lblTourMax.setHorizontalAlignment(SwingConstants.LEFT);
		lblTourMax.setForeground(Color.GREEN);
		lblTourMax.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTourMax.setBounds(449, 78, 77, 32);
		contentPane.add(lblTourMax);
		
		//initialisation du jeu 
		MotTest = choisirMot();
		System.out.println(MotTest);
		String MotAdd = "";
		char premier = MotTest.charAt(0);
		MotAdd += premier;
		for (int i=1;i<MotTest.length();i++) {
			MotAdd += "*";
		}
		System.out.println(MotAdd);
		lblMot.setText(MotAdd);
		//Tour max
		tourMax = MotTest.length() + 3;
		lblTourMax.setText(String.valueOf(tourMax));
		
		JButton btnRecommencer = new JButton("REJOUER");
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ne marche pas
				new Pendu();
				
			}
		});
		
		JButton btnNewButton = new JButton("Verifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				String lettreRecup = textMot.getText();
				char lettre = lettreRecup.charAt(0);
				lettre = Character.toLowerCase(lettre);
				String message = "";
				message = verifLettre(lettreRecup);
				lblMessage.setText(message);
				boolean vrai;
				
				if (message == "La lettre a bien été saisie") {
					tour +=1;
					lblLettresTest.setText(lblLettresTest.getText() + lettre);
					vrai = verifLettre2(MotTest, lettre, pos);
					if (vrai) {
						motAster = MotAste(MotTest, pos);
						lblMot.setText(motAster);
					}
					else {
						erreur+=1;
					}
				}
				lblErreur.setText(String.valueOf(erreur));
				finV = verifFin(MotTest, motAster);
				if (finV) {
					lblMot.setText("Bravo ! Vous avez gagné !");
				}
				lblTour.setText(String.valueOf(tour));
				if (tour >tourMax) {
					lblMot.setText("Nul ! Vous avez perdu !");
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(330, 312, 124, 34);
		contentPane.add(btnNewButton);
		
		
		btnRecommencer.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnRecommencer.setBounds(43, 75, 187, 50);
		contentPane.add(btnRecommencer);
		
		
		
		
		
		
	}
}
