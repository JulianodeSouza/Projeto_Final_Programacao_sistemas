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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormConsultas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataConsulta;
	private JTextField txtObservacoes;	
	private JComboBox cbMedico;
	private JComboBox cbPaciente;
	private JTable table;	
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private int idSelecionado;

	Conexao cx = new Conexao();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormConsultas frame = new FormConsultas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void comboBoxMedico() {
		try {
			String sql = "select nome from tb_medico;";
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			cbMedico.addItem("");
			while (cx.rs.next()) {
				cbMedico.addItem(cx.rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void comboBoxPaciente() {
		try {
			String sql = "select nome from tb_paciente;";
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			cbPaciente.addItem("");
			while (cx.rs.next()) {
				cbPaciente.addItem(cx.rs.getString(1));
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void tabelaConsultas() {
		try {
			int q;
			String sql =  "SELECT "
					+ "c.id_consulta, "
					+ "p.nome as nomePaciente, "
					+ "m.nome as nomeMedico, "
					+ "c.data_consulta, "
					+ "c.status, "
					+ "c.observacoes "								
					+ "FROM tb_consultas c "
					+ "INNER JOIN tb_medico m ON m.id = c.id_medico "
					+ "INNER JOIN tb_paciente p ON p.id = c.id_paciente; ";
			
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			ResultSetMetaData rss = cx.rs.getMetaData();
			q = rss.getColumnCount();
			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);
			while (cx.rs.next()) {
				Vector v2 = new Vector();
				for (int a = 1; a <= q; a++) {
					v2.add(cx.rs.getString("id_consulta"));
					v2.add(cx.rs.getString("nomePaciente"));
					v2.add(cx.rs.getString("nomeMedico"));
					v2.add(cx.rs.getString("data_consulta"));
					v2.add(cx.rs.getString("status"));
					v2.add(cx.rs.getString("observacoes"));
				}
				df.addRow(v2);
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void layoutDefault() {
		txtDataConsulta.setEnabled(false);
		txtObservacoes.setEnabled(false);		
		cbMedico.setEnabled(false);
		cbPaciente.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtDataConsulta.setText("");
		txtObservacoes.setText("");		
		tabelaConsultas();
	}

	public void layoutBtnNovo() {
		txtDataConsulta.setEnabled(true);
		txtObservacoes.setEnabled(true);
		cbMedico.setEnabled(true);
		cbPaciente.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtDataConsulta.setText("");
		txtObservacoes.setText("");		
		tabelaConsultas();
	}

	public void layoutSelecionarConsultaTable() {
		txtDataConsulta.setEnabled(true);
		txtObservacoes.setEnabled(true);
		cbMedico.setEnabled(true);
		cbPaciente.setEnabled(true);		
		btnSalvar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}

	public FormConsultas() {
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
		areaImg.setBounds(30, 11, 140, 130);
		areaImg.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(areaImg);

		ImageIcon imgCalendar = new ImageIcon(getClass().getResource("/img/calendar.png"));

		Image img = imgCalendar.getImage();
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
		
		JLabel lblDataConsulta = new JLabel("Data Consulta");
		lblDataConsulta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataConsulta.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDataConsulta.setBounds(15, 8, 95, 20);
		areaCadastro.add(lblDataConsulta);
		
		txtDataConsulta = new JTextField();
		txtDataConsulta.setBounds(115, 8, 320, 20);
		txtDataConsulta.setColumns(10);
		areaCadastro.add(txtDataConsulta);	

		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPaciente.setBounds(-1, 39, 100, 20);
		areaCadastro.add(lblPaciente);
		
		cbPaciente = new JComboBox();
		cbPaciente.setBounds(115, 39, 320, 22);
		areaCadastro.add(cbPaciente);

		JLabel lblMedico = new JLabel("Médico");
		lblMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedico.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblMedico.setBounds(10, 100, 65, 20);
		areaCadastro.add(lblMedico);

		cbMedico = new JComboBox();
		cbMedico.setBounds(115, 100, 320, 22);
		areaCadastro.add(cbMedico);
		
		JLabel lblObservacoes = new JLabel("Observacoes");
		lblObservacoes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservacoes.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblObservacoes.setBounds(0, 70, 100, 20);
		areaCadastro.add(lblObservacoes);
		
		txtObservacoes = new JTextField();
		txtObservacoes.setColumns(10);
		txtObservacoes.setBounds(115, 70, 320, 20);
		areaCadastro.add(txtObservacoes);		
	
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
					String dataConsulta = txtDataConsulta.getText();
					String observacoes = txtObservacoes.getText();					
					String medNome= cbMedico.getSelectedItem().toString();
					String nomePaciente = cbPaciente.getSelectedItem().toString();	
					int medID = -1;
					int pacienteID = -1;					

					if (medNome.isEmpty() || dataConsulta.isEmpty()	|| medNome.isEmpty() || nomePaciente.isEmpty()) {
						JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),
								"Todos os campos precisam ser preenchidos");
					} else {
						String sqlConsultaIDMedico = "select id from tb_medico where nome = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDMedico);
						cx.pst.setString(1, medNome);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							medID = cx.rs.getInt("id");
						} else {
							medID = 0;
						}
						
						String sqlConsultaIDPaciente = "select id from tb_paciente where nome = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDPaciente);
						cx.pst.setString(1, nomePaciente);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							pacienteID = cx.rs.getInt("id");
						} else {
							pacienteID = 0;
						}
					}
					String sql = "UPDATE tb_consultas SET data_consulta=?, observacoes=?, id_medico=?, id_paciente=? WHERE id = ?;";
					cx.pst = cx.con.prepareStatement(sql);
					cx.pst.setString(1, dataConsulta);					
					cx.pst.setString(3, observacoes);
					cx.pst.setInt(4, medID);
					cx.pst.setInt(5, pacienteID);
					
					
					cx.pst.setInt(7, idSelecionado);
					int sucesso = cx.pst.executeUpdate();
					if (sucesso == 1) {
						JOptionPane.showMessageDialog(btnEditar.getTopLevelAncestor(), "Consulta editada com sucesso!");
						layoutDefault();
						
					} else {
						JOptionPane.showMessageDialog(btnEditar.getTopLevelAncestor(), "Erro ao editar consulta!");
					}

				} catch (SQLException ex) {
					Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pID = idSelecionado;
					String sql = "UPDATE tb_consultas SET status='Cancelada' WHERE id = ?;";
					cx.pst = cx.con.prepareStatement(sql);
					cx.pst.setInt(1, pID);

					int sucesso = cx.pst.executeUpdate();
					if (sucesso == 1) {
						JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(), "Consulta cancelada com sucesso!");
						layoutDefault();
					} else {
						JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(), "Erro ao cancelar consulta!");
					}

				} catch (SQLException ex) {
					Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String valorDataConsulta = txtDataConsulta.getText();
					
					DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					
					LocalDate date = LocalDate.parse(valorDataConsulta, inputFormatter); 
					String dataConsultaFormatada = date.format(outputFormatter);
					    
					String status = "Agendado";
					String observacoes = txtObservacoes.getText();					
					String medNome = cbMedico.getSelectedItem().toString();
					String nomePaciente = cbPaciente.getSelectedItem().toString();					
					int medID = -1;
					int pacienteID = -1;

					if (dataConsultaFormatada.isEmpty() || medNome.isEmpty() || nomePaciente.isEmpty()) {
						JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),
								"Todos os campos precisam ser preenchidos");
					} else {
						String sqlConsultaIDMedico = "select id from tb_medico where nome = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDMedico);
						cx.pst.setString(1, medNome);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							medID = cx.rs.getInt("id");
						} else {
							medID = 0;
						}
						
						String sqlConsultaIDPaciente = "select id from tb_paciente where nome = ?";
						cx.pst = cx.con.prepareStatement(sqlConsultaIDPaciente);
						cx.pst.setString(1, nomePaciente);
						cx.rs = cx.pst.executeQuery();

						if (cx.rs.next()) {
							pacienteID = cx.rs.getInt("id");
						} else {
							pacienteID = 0;
						}

						String sql = "insert into tb_consultas (data_consulta, status, observacoes, id_medico, id_paciente) values (?, ?, ?, ?, ?);";
						cx.pst = cx.con.prepareStatement(sql);
						cx.pst.setString(1, dataConsultaFormatada);
						cx.pst.setString(2, status);
						cx.pst.setString(3, observacoes);					
						cx.pst.setInt(4, medID);
						cx.pst.setInt(5, pacienteID);
						int sucesso = cx.pst.executeUpdate();
						if (sucesso == 1) {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Consulta agendada com sucesso!");
							layoutDefault();
						} else {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Erro ao agendar consulta!");
						}
					}
				} catch (SQLException ex) {
					Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 424, 177);
		areaPesquisa.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() > -1) {
						idSelecionado = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
						String sql = "SELECT "
								+ "p.nome as nomePaciente, "
								+ "m.nome as nomeMedico, "
								+ "c.data_consulta, "								
								+ "c.observacoes "								
								+ "FROM tb_consultas c "
								+ "INNER JOIN tb_medico m ON m.id = c.id_medico "
								+ "INNER JOIN tb_paciente p ON p.id = c.id_paciente "
								+ "WHERE c.id_consulta = ?";
						
						cx.pst = cx.con.prepareStatement(sql);
						cx.pst.setInt(1, idSelecionado);
						cx.rs = cx.pst.executeQuery();
						
						if (cx.rs.next() == true) {
							cbMedico.setSelectedItem(cx.rs.getString(1));
							cbPaciente.setSelectedItem(cx.rs.getString(2));
							txtDataConsulta.setText(cx.rs.getString(3));							
							txtObservacoes.setText(cx.rs.getString(4));
							
							layoutSelecionarConsultaTable();
						}
					}
				} catch (Exception ex) {
					Logger.getLogger(FormConsultas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CRM", "Especialidade" }));

		JLabel lblNewLabel_1_1 = new JLabel("Consultas Agendadas");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(0, 10, 140, 20);
		areaPesquisa.add(lblNewLabel_1_1);

		JLabel lblJanela = new JLabel("Consultas");
		lblJanela.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJanela.setBounds(250, 10, 200, 20);
		contentPane.add(lblJanela);

		layoutDefault();
		comboBoxMedico();
		comboBoxPaciente();
	}
}
