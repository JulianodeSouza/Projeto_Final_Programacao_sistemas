import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCRM;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtPesquisa;
	private JTable table;
	private JComboBox cbEspecializacao;
	private JRadioButton rdbtnWhatsapp;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private int idSelecionado;

	Conexao cx = new Conexao();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMedico frame = new FormMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void comboBoxEspecializacao() {
		try {
			String sql = "select nome_especializacao from tb_especializacao;";
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			cbEspecializacao.addItem("");
			while (cx.rs.next()) {
				cbEspecializacao.addItem(cx.rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void tabelaMedico() {
		try {
			int q;
			String sql = "SELECT m.id as id, m.nome as nome, m.crm as crm, e.nome_especializacao as especializacao FROM tb_medico m JOIN tb_especializacao e ON m.id_especializacao = e.id;";
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			ResultSetMetaData rss = cx.rs.getMetaData();
			q = rss.getColumnCount();
			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);
			while (cx.rs.next()) {
				Vector v2 = new Vector();
				for (int a = 1; a <= q; a++) {
					v2.add(cx.rs.getString("id"));
					v2.add(cx.rs.getString("nome"));
					v2.add(cx.rs.getString("crm"));
					v2.add(cx.rs.getString("especializacao"));
				}
				df.addRow(v2);
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void layoutDefault() {
		txtNome.setEnabled(false);
		txtCRM.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtEmail.setEnabled(false);
		cbEspecializacao.setEnabled(false);
		rdbtnWhatsapp.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtNome.setText("");
		txtCRM.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		rdbtnWhatsapp.setSelected(false);
		tabelaMedico();
	}

	public void layoutBtnNovo() {
		txtNome.setEnabled(true);
		txtCRM.setEnabled(true);
		txtTelefone.setEnabled(true);
		txtEmail.setEnabled(true);
		cbEspecializacao.setEnabled(true);
		rdbtnWhatsapp.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtNome.setText("");
		txtCRM.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		rdbtnWhatsapp.setSelected(false);
		tabelaMedico();
	}

	public void layoutSelecionarMedicoTable() {
		txtNome.setEnabled(true);
		txtCRM.setEnabled(true);
		txtTelefone.setEnabled(true);
		txtEmail.setEnabled(true);
		cbEspecializacao.setEnabled(true);
		rdbtnWhatsapp.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}

	public FormMedico() {
		cx.conectar();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 350, 700, 500);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 45, 664, 401);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel areaImg = new JPanel();
		areaImg.setBounds(10, 11, 190, 140);
		areaImg.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(areaImg);

		ImageIcon imgMed = new ImageIcon(getClass().getResource("/img/medico-img.png"));

		Image img = imgMed.getImage();
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		double aspectRatio = (double) width / height;
		int newWidth = 190;
		int newHeight = (int) (newWidth / aspectRatio);
		if (newHeight > 140) {
			newHeight = 140;
			newWidth = (int) (newHeight * aspectRatio);
		}
		Image imgResized = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		JLabel lblImagem = new JLabel(new ImageIcon(imgResized));
		areaImg.add(lblImagem);

		JPanel areaCadastro = new JPanel();
		areaCadastro.setBounds(210, 11, 444, 140);
		panel.add(areaCadastro);
		areaCadastro.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(15, 8, 60, 20);
		areaCadastro.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("CRM");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(15, 39, 60, 20);
		areaCadastro.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(15, 70, 60, 20);
		areaCadastro.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("E-mail");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(15, 101, 60, 20);
		areaCadastro.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Especialização");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(185, 39, 100, 20);
		areaCadastro.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Celular / ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(185, 70, 65, 20);
		areaCadastro.add(lblNewLabel_6);

		txtNome = new JTextField();
		txtNome.setBounds(80, 8, 354, 20);
		txtNome.setColumns(10);
		areaCadastro.add(txtNome);

		txtCRM = new JTextField();
		txtCRM.setColumns(10);
		txtCRM.setBounds(80, 39, 105, 20);
		areaCadastro.add(txtCRM);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(80, 70, 105, 20);
		areaCadastro.add(txtTelefone);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(80, 101, 354, 20);
		areaCadastro.add(txtEmail);

		cbEspecializacao = new JComboBox();
		cbEspecializacao.setBounds(288, 39, 146, 22);
		areaCadastro.add(cbEspecializacao);

		rdbtnWhatsapp = new JRadioButton("WhatsApp");
		rdbtnWhatsapp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnWhatsapp.setBounds(250, 70, 109, 23);
		areaCadastro.add(rdbtnWhatsapp);

		JPanel areaBotoes = new JPanel();
		areaBotoes.setBounds(50, 173, 106, 207);
		panel.add(areaBotoes);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layoutBtnNovo();
			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String medNome = txtNome.getText();
					String medCRM = txtCRM.getText();
					String medTelefone = txtTelefone.getText();
					String medEmail = txtEmail.getText();
					String medEspecializacao = cbEspecializacao.getSelectedItem().toString();
					boolean medWhatsapp = rdbtnWhatsapp.isSelected();
					int medIDEspecializacao = -1;

					if (medNome.isEmpty() || medCRM.isEmpty() || medTelefone.isEmpty() || medEmail.isEmpty()
							|| medEspecializacao.isEmpty()) {
						JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),
								"Todos os campos precisa ser preenchidos");
					} else {
						String sqlConsultaIDEsp = "select id from tb_especializacao where nome_especializacao = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDEsp);
						cx.pst.setString(1, medEspecializacao);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							medIDEspecializacao = cx.rs.getInt("id");
						} else {
							medIDEspecializacao = 0;
						}
					}
					String sql = "UPDATE tb_medico SET nome=?, crm=?, id_especializacao=?, telefone=?, whatsapp=?, email=? WHERE id = ?;";
					cx.pst = cx.con.prepareStatement(sql);
					cx.pst.setString(1, medNome);
					cx.pst.setString(2, medCRM);
					cx.pst.setInt(3, medIDEspecializacao);
					cx.pst.setString(4, medTelefone);
					cx.pst.setBoolean(5, medWhatsapp);
					cx.pst.setString(6, medEmail);
					cx.pst.setInt(7, idSelecionado);
					int sucesso = cx.pst.executeUpdate();
					if (sucesso == 1) {
						JOptionPane.showMessageDialog(btnEditar.getTopLevelAncestor(), "Médico editado com sucesso!");
						layoutDefault();
						
					} else {
						JOptionPane.showMessageDialog(btnEditar.getTopLevelAncestor(), "Erro ao editar Médico!");
					}

				} catch (SQLException ex) {
					Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pID = idSelecionado;
					String sql = "delete from tb_medico where id=?;";
					cx.pst = cx.con.prepareStatement(sql);
					cx.pst.setInt(1, pID);
					System.out.println("ID selecionado para exclusão: " + pID);

					int sucesso = cx.pst.executeUpdate();
					if (sucesso == 1) {
						JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(), "Médico deletado com sucesso!");
						layoutDefault();
					} else {
						JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(), "Erro ao deletar Médico!");
					}

				} catch (SQLException ex) {
					Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String medNome = txtNome.getText();
					String medCRM = txtCRM.getText();
					String medTelefone = txtTelefone.getText();
					String medEmail = txtEmail.getText();
					String medEspecializacao = cbEspecializacao.getSelectedItem().toString();
					boolean medWhatsapp = rdbtnWhatsapp.isSelected();
					int medIDEspecializacao = -1;

					if (medNome.isEmpty() || medCRM.isEmpty() || medTelefone.isEmpty() || medEmail.isEmpty()
							|| medEspecializacao.isEmpty()) {
						JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),
								"Todos os campos precisa ser preenchidos");
					} else {
						String sqlConsultaIDEsp = "select id from tb_especializacao where nome_especializacao = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDEsp);
						cx.pst.setString(1, medEspecializacao);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							medIDEspecializacao = cx.rs.getInt("id");
						} else {
							medIDEspecializacao = 0;
						}

						String sql = "insert into tb_medico (nome, crm, id_especializacao, telefone, whatsapp, email) values (?, ?, ?, ?, ?, ?);";
						cx.pst = cx.con.prepareStatement(sql);
						cx.pst.setString(1, medNome);
						cx.pst.setString(2, medCRM);
						cx.pst.setInt(3, medIDEspecializacao);
						cx.pst.setString(4, medTelefone);
						cx.pst.setBoolean(5, medWhatsapp);
						cx.pst.setString(6, medEmail);
						int sucesso = cx.pst.executeUpdate();
						if (sucesso == 1) {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Médico adicionado com sucesso!");
							layoutDefault();
						} else {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Erro ao adicionar Médico!");
						}
					}
				} catch (SQLException ex) {
					Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaPrincipal menuPrincipal = new JanelaPrincipal();
				setVisible(false);
			}
		});
		areaBotoes.setLayout(new GridLayout(0, 1, 0, 0));
		areaBotoes.add(btnNovo);

		JLabel lblNewLabel_7 = new JLabel("");
		areaBotoes.add(lblNewLabel_7);
		areaBotoes.add(btnEditar);

		JLabel lblNewLabel_8 = new JLabel("");
		areaBotoes.add(lblNewLabel_8);
		areaBotoes.add(btnExcluir);

		JLabel lblNewLabel_9 = new JLabel("");
		areaBotoes.add(lblNewLabel_9);
		areaBotoes.add(btnSalvar);

		JLabel lblNewLabel_10 = new JLabel("");
		areaBotoes.add(lblNewLabel_10);
		areaBotoes.add(btnCancelar);

		JPanel areaPesquisa = new JPanel();
		areaPesquisa.setBounds(210, 162, 444, 228);
		panel.add(areaPesquisa);
		areaPesquisa.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(80, 10, 160, 20);
		areaPesquisa.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton btnPesquisa = new JButton("Pesquisar");
		btnPesquisa.setBounds(250, 10, 96, 20);
		areaPesquisa.add(btnPesquisa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 424, 177);
		areaPesquisa.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() > -1) {
						idSelecionado = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
						String sql = "SELECT m.nome as nome, m.crm as crm, m.telefone as telefone, m.whatsapp as whatsapp, m.email as email, m.id_especializacao, e.nome_especializacao as especializacao FROM tb_medico m JOIN tb_especializacao e ON m.id_especializacao = e.id WHERE m.id = ?";
						cx.pst = cx.con.prepareStatement(sql);
						cx.pst.setInt(1, idSelecionado);
						cx.rs = cx.pst.executeQuery();
						if (cx.rs.next() == true) {
							txtNome.setText(cx.rs.getString(1));
							txtCRM.setText(cx.rs.getString(2));
							txtTelefone.setText(cx.rs.getString(3));
							txtEmail.setText(cx.rs.getString(5));
							cbEspecializacao.setSelectedItem(cx.rs.getString(7));
							if (Integer.parseInt(cx.rs.getString(4)) == 1) {
								rdbtnWhatsapp.setSelected(true);
							} else {
								rdbtnWhatsapp.setSelected(false);
							}
							int idEspecializacaoSelecionado = Integer.parseInt(cx.rs.getString(6));
							layoutSelecionarMedicoTable();
						}
					}
				} catch (Exception ex) {
					Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CRM", "Especialidade" }));

		JLabel lblNewLabel_1_1 = new JLabel("Nome");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(15, 10, 60, 20);
		areaPesquisa.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Cadastro de Médicos");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(250, 10, 200, 20);
		contentPane.add(lblNewLabel);

		layoutDefault();
		comboBoxEspecializacao();
	}
}
