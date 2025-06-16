import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class JanelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;

	Conexao cx = new Conexao();
	private JPasswordField campoSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaLogin frame = new JanelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ValidacaoUsuarioSenha() {
		
		
		System.out.println("PASSANDO AQUI");
		cx.conectar();
		
		String login = txtLogin.getText();
		String senha = campoSenha.getText();
		String sql = "SELECT username, senha FROM tb_usuario WHERE username = ?;";
		
		try {
			cx.pst = cx.con.prepareStatement(sql);
			cx.pst.setString(1, login);
			ResultSet rs = cx.pst.executeQuery();
			if(rs.next()) {
				System.out.println("usuario existe no banco");
				String senhaUsuarioDB = rs.getString("senha");
				if(senhaUsuarioDB.equals(senha)) {
					System.out.println("Senha e usuário validos");
					JanelaPrincipal menuPrincipal = new JanelaPrincipal();
					setVisible(false); // 
				}else {
					System.out.println("Senha incorreta!!");
				}
			}else {
				System.out.println("não existe no banco");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JanelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 350, 700, 500);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(128, 140, 60, 17);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(128, 173, 60, 17);
		contentPane.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(206, 138, 114, 21);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidacaoUsuarioSenha();
			}
		});
		btnEntrar.setBounds(128, 219, 105, 27);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(245, 219, 105, 27);
		contentPane.add(btnSair);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(206, 171, 114, 21);
		contentPane.add(campoSenha);
	}
}
