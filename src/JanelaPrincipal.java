import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel AreaPrincipal;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 350, 700, 500);
		setVisible(true);
		AreaPrincipal = new JPanel();
		AreaPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(AreaPrincipal);
		AreaPrincipal.setLayout(null);
		setResizable(false);
		
		//Inicio da barra de menu.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);			
			JMenu mnGrupoSistema = new JMenu("Sistema");
			menuBar.add(mnGrupoSistema);
				JMenuItem mnItemLogout = new JMenuItem("Logout");
				mnItemLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JanelaLogin janelaLogin = new JanelaLogin();
						setVisible(false);
					}
				});
				mnGrupoSistema.add(mnItemLogout);
				JMenuItem mnItemSair = new JMenuItem("Sair");
				mnItemSair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				mnGrupoSistema.add(mnItemSair);
		 // Final da barra de menu.
		
		JLabel backgroudImg = new JLabel("");
		backgroudImg.setLabelFor(AreaPrincipal);
		backgroudImg.setBounds(5, 5, 690, 467);
		backgroudImg.setIcon(new ImageIcon("/home/cai-ubuntu/Documents/1-estudos/0-java/workspace-eclipse-estudos/SoftwareConsultorio/src/img/fundoPrincipal.png"));
		AreaPrincipal.add(backgroudImg);
		backgroudImg.setLayout(null);
		
		JLabel lblCabecario = new JLabel("Consultório Médico");
		lblCabecario.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		lblCabecario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecario.setBounds(12, 12, 676, 51);
		backgroudImg.add(lblCabecario);
		
			JLabel lblCadastro = new JLabel("Cadastro");
			lblCadastro.setBounds(12, 75, 60, 17);
			backgroudImg.add(lblCadastro);
			
			JSeparator separadorCadastro = new JSeparator();
			separadorCadastro.setForeground(new Color(94, 92, 100));
			separadorCadastro.setBounds(68, 87, 60, 1);
			backgroudImg.add(separadorCadastro);
		
				JButton btnMedico = new JButton("Médico");
				btnMedico.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						FormMedico formMedico = new FormMedico();
						setVisible(false);
					}
				});
				backgroudImg.add(btnMedico);
				btnMedico.setBounds(12, 95, 110, 100);
				
				JButton btnPaciente = new JButton("Paciente");
				btnPaciente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FormPaciente formPaciente = new FormPaciente();
						setVisible(false);
					}
				});
				backgroudImg.add(btnPaciente);
				btnPaciente.setBounds(134, 95, 110, 100);
						
			JLabel lblAgenda = new JLabel("Agenda");
			lblAgenda.setBounds(11, 207, 60, 17);
			backgroudImg.add(lblAgenda);
		
			JSeparator separadorAgenda = new JSeparator();
			separadorAgenda.setForeground(new Color(94, 92, 100));
			separadorAgenda.setBounds(60, 219, 66, 2);
			backgroudImg.add(separadorAgenda);
		
				JButton btnAgenda = new JButton("Agenda");
				btnAgenda.setBounds(12, 224, 110, 100);
				backgroudImg.add(btnAgenda);
				btnAgenda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						FormConsultas formAgenda = new FormConsultas();
						setVisible(false);
					}
				});
	}
}
