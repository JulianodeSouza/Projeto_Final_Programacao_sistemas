import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class FormPaciente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtRg;
	private JTextField txtDataNasc;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JComboBox cbSexo;
	private JRadioButton rdbtnWhatsapp;
	private JTextField txtCep;
	private JTextField txtEstado;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtRua;
	private JTextField txtComplemento;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnPesquisarCep;
	private int idSelecionado;
	private JTable table;
	Conexao cx = new Conexao();
		
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPaciente frame = new FormPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void tabelaPaciente() {
		try {
			int q;
			String sql = "SELECT id, nome, cpf, email, telefone FROM tb_paciente;";
			cx.pst = cx.con.prepareStatement(sql);
			cx.rs = cx.pst.executeQuery();
			ResultSetMetaData rss = cx.rs.getMetaData();
			q = rss.getColumnCount();
			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);
			while(cx.rs.next()) {
				Vector v2 =  new Vector();
				for(int a=1;a<=q;a++) {
					v2.add(cx.rs.getString("id"));
					v2.add(cx.rs.getString("nome"));
					v2.add(cx.rs.getString("cpf"));
					v2.add(cx.rs.getString("email"));
					v2.add(cx.rs.getString("telefone"));
				}
				df.addRow(v2);
			}
		} catch (SQLException ex) {
			Logger.getLogger(FormPaciente.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	}
	
	public void layoutDefault() {
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtDataNasc.setText("");
		cbSexo.setSelectedIndex(0);
		txtEmail.setText("");
		txtTelefone.setText("");
		rdbtnWhatsapp.setSelected(false);
		txtCep.setText("");
		txtEstado.setText("");
		txtCidade.setText("");
		txtBairro.setText("");
		txtRua.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtNome.setEnabled(false);
		txtCpf.setEnabled(false);
		txtRg.setEnabled(false);
		txtDataNasc.setEnabled(false);
		txtEmail.setEnabled(false);
		txtTelefone.setEnabled(false);
		cbSexo.setEnabled(false);
		rdbtnWhatsapp.setEnabled(false);
		txtCep.setEnabled(false);
		txtEstado.setEnabled(false);
	    rdbtnWhatsapp.setEnabled(false);
	    txtCidade.setEnabled(false);
	    txtNumero.setEnabled(false);
	    txtBairro.setEnabled(false);
	    txtRua.setEnabled(false);
	    txtComplemento.setEnabled(false);
	    btnSalvar.setEnabled(false);
	    btnEditar.setEnabled(false);
	    btnExcluir.setEnabled(false);
	    btnPesquisarCep.setEnabled(false);
	    tabelaPaciente();
	}
	
	public void layoutBtnNovo() {
		txtNome.setEnabled(true);
		txtCpf.setEnabled(true);
		txtRg.setEnabled(true);
		txtDataNasc.setEnabled(true);
		txtEmail.setEnabled(true);
		txtTelefone.setEnabled(true);
		cbSexo.setEnabled(true);
		rdbtnWhatsapp.setEnabled(true);
		txtCep.setEnabled(true);
		txtEstado.setEnabled(false);
	    rdbtnWhatsapp.setEnabled(true);
	    txtCidade.setEnabled(false);
	    txtBairro.setEnabled(false);
	    txtRua.setEnabled(false);
	    txtNumero.setEnabled(false);
	    txtComplemento.setEnabled(false);
	    btnSalvar.setEnabled(true);
	    btnEditar.setEnabled(false);
	    btnExcluir.setEnabled(false);
	    btnPesquisarCep.setEnabled(true);
	    tabelaPaciente();
	}
	
	public void layoutSelecionarPacienteTable() {
		txtNome.setEnabled(true);
		txtCpf.setEnabled(true);
		txtRg.setEnabled(true);
		txtDataNasc.setEnabled(true);
		txtEmail.setEnabled(true);
		txtTelefone.setEnabled(true);
		cbSexo.setEnabled(true);
		rdbtnWhatsapp.setEnabled(true);
		txtCep.setEnabled(true);
		txtEstado.setEnabled(false);
	    rdbtnWhatsapp.setEnabled(true);
	    txtCidade.setEnabled(false);
	    txtBairro.setEnabled(false);
	    txtRua.setEnabled(false);
	    txtNumero.setEnabled(true);
	    txtComplemento.setEnabled(true);
	    btnSalvar.setEnabled(false);
	    btnEditar.setEnabled(true);
	    btnExcluir.setEnabled(true);
	    btnPesquisarCep.setEnabled(true);
	    tabelaPaciente();
	}

	public FormPaciente() {
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
		panel.setBounds(10, 45, 678, 406);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel areaImg = new JPanel();
		areaImg.setBounds(10, 11, 190, 140);
		areaImg.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(areaImg);
		
		ImageIcon imgPac = new ImageIcon(getClass().getResource("/img/paciente-img.png"));
		Image img = imgPac.getImage();
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
		areaCadastro.setBounds(210, 11, 456, 383);
		panel.add(areaCadastro);
		areaCadastro.setLayout(null);
		
			JLabel lblNewLabel_1 = new JLabel("Nome");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(15, 10, 60, 20);
			areaCadastro.add(lblNewLabel_1);
					
			JLabel lblNewLabel_2 = new JLabel("CPF");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(15, 35, 60, 20);
			areaCadastro.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("RG");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(230, 35, 45, 20);
			areaCadastro.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Data nasc.");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_4.setBounds(15, 60, 60, 20);
			areaCadastro.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Sexo");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_5.setBounds(230, 60, 45, 20);
			areaCadastro.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("E-mail");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_6.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_6.setBounds(15, 85, 60, 20);
			areaCadastro.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Telefone");
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_7.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_7.setBounds(15, 110, 60, 20);
			areaCadastro.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("Celular / ");
			lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_8.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_8.setBounds(240, 110, 65, 20);
			areaCadastro.add(lblNewLabel_8);
			
			txtNome = new JTextField();
			txtNome.setBounds(80, 10, 354, 20);
			txtNome.setColumns(10);
			areaCadastro.add(txtNome);
			
			txtRg = new JTextField();
			txtRg.setColumns(10);
			txtRg.setBounds(284, 35, 150, 20);
			areaCadastro.add(txtRg);
			
			txtCpf = new JTextField();
			txtCpf.setColumns(10);
			txtCpf.setBounds(80, 35, 150, 20);
			areaCadastro.add(txtCpf);
			
			txtDataNasc = new JTextField();
			txtDataNasc.setColumns(10);
			txtDataNasc.setBounds(80, 60, 150, 20);
			areaCadastro.add(txtDataNasc);
			
			
			cbSexo = new JComboBox();
			cbSexo.setBounds(284, 60, 150, 20);
			areaCadastro.add(cbSexo);
			cbSexo.addItem("");
			cbSexo.addItem("Masculino");
			cbSexo.addItem("Feminino");
			
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(80, 85, 354, 20);
			areaCadastro.add(txtEmail);
			
			txtTelefone = new JTextField();
			txtTelefone.setColumns(10);
			txtTelefone.setBounds(80, 110, 150, 20);
			areaCadastro.add(txtTelefone);
			
			rdbtnWhatsapp = new JRadioButton("WhatsApp");
			rdbtnWhatsapp.setFont(new Font("Tahoma", Font.PLAIN, 11));
			rdbtnWhatsapp.setBounds(301, 110, 109, 23);
			areaCadastro.add(rdbtnWhatsapp);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(22, 135, 400, 1);
			areaCadastro.add(separator);
			
			JLabel lblNewLabel_9 = new JLabel("Complemento");
			lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_9.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_9.setBounds(165, 215, 87, 20);
			areaCadastro.add(lblNewLabel_9);
			
			JLabel lblNewLabel_10 = new JLabel("CEP");
			lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_10.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_10.setBounds(15, 140, 60, 20);
			areaCadastro.add(lblNewLabel_10);
			
			JLabel lblNewLabel_11 = new JLabel("UF");
			lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_11.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_11.setBounds(305, 165, 20, 20);
			areaCadastro.add(lblNewLabel_11);
			
			JLabel lblNewLabel_12 = new JLabel("N°");
			lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_12.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_12.setBounds(15, 215, 60, 20);
			areaCadastro.add(lblNewLabel_12);
			
			JLabel lblNewLabel_13 = new JLabel("Bairro");
			lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_13.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_13.setBounds(175, 165, 35, 20);
			areaCadastro.add(lblNewLabel_13);
			
			JLabel lblNewLabel_14 = new JLabel("Rua");
			lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_14.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_14.setBounds(15, 190, 60, 20);
			areaCadastro.add(lblNewLabel_14);
			
			JLabel lblNewLabel_15 = new JLabel("Cidade");
			lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_15.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_15.setBounds(15, 165, 60, 20);
			areaCadastro.add(lblNewLabel_15);
			
			txtCep = new JTextField();
			txtCep.setColumns(10);
			txtCep.setBounds(80, 140, 90, 20);
			areaCadastro.add(txtCep);
			
			txtEstado = new JTextField();
			txtEstado.setColumns(10);
			txtEstado.setBounds(80, 165, 90, 20);
			areaCadastro.add(txtEstado);
			
			txtCidade = new JTextField();
			txtCidade.setColumns(10);
			txtCidade.setBounds(215, 165, 90, 20);
			areaCadastro.add(txtCidade);
			
			txtNumero = new JTextField();
			txtNumero.setColumns(10);
			txtNumero.setBounds(80, 215, 75, 20);
			areaCadastro.add(txtNumero);
			
			txtBairro = new JTextField();
			txtBairro.setColumns(10);
			txtBairro.setBounds(330, 165, 104, 20);
			areaCadastro.add(txtBairro);
			
			txtRua = new JTextField();
			txtRua.setColumns(10);
			txtRua.setBounds(80, 190, 354, 20);
			areaCadastro.add(txtRua);
			
			txtComplemento = new JTextField();
			txtComplemento.setColumns(10);
			txtComplemento.setBounds(262, 215, 172, 20);
			areaCadastro.add(txtComplemento);
			
			btnPesquisarCep = new JButton("Pesquisar");
			btnPesquisarCep.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String cep = txtCep.getText().trim(); // Remover espaços extras
			        ViaCep api = new ViaCep();
			        String erro = api.buscarCep(cep);
			        if (erro != null) {
			            JOptionPane.showMessageDialog(btnPesquisarCep.getTopLevelAncestor(), erro);
			        }else if (cep.length() == 8 && cep.matches("\\d+")) { // Verifica se tem 8 caracteres e se são apenas números
			            try {
			                api.buscarCep(cep);
			                String estado = api.getEstado();
			                String cidade = api.getCidade();
			                String bairro = api.getBairro();
			                String rua = api.getRua();
			                txtEstado.setText(estado);
			                txtCidade.setText(cidade);
			                txtBairro.setText(bairro);
			                txtRua.setText(rua);
			                txtNumero.setEnabled(true);
			                txtComplemento.setEnabled(true);
			            } catch (Exception ex) {
			                JOptionPane.showMessageDialog(btnPesquisarCep.getTopLevelAncestor(), "Erro ao pesquisar CEP.");
			                ex.printStackTrace();
			            }
			        } else {
			            JOptionPane.showMessageDialog(btnPesquisarCep.getTopLevelAncestor(), "Erro ao pesquisar CEP.");
			        }
			    }
			});
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(22, 240, 400, 1);
			areaCadastro.add(separator_1);
			btnPesquisarCep.setBounds(180, 140, 96, 20);
			areaCadastro.add(btnPesquisarCep);
			
				JTextField txtPesquisa = new JTextField();
				txtPesquisa.setBounds(80, 245, 160, 20);
				areaCadastro.add(txtPesquisa);
				txtPesquisa.setColumns(10);
				
				JLabel lblNewLabel_1_1 = new JLabel("Nome");
				lblNewLabel_1_1.setBounds(15, 245, 60, 20);
				areaCadastro.add(lblNewLabel_1_1);
				lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
				
				JButton btnPesquisaDB = new JButton("Pesquisar");
				btnPesquisaDB.setBounds(250, 245, 96, 20);
				areaCadastro.add(btnPesquisaDB);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(15, 270, 429, 101);
				areaCadastro.add(scrollPane);
				
			
		JPanel areaBotoes = new JPanel();
		areaBotoes.setBounds(50, 162, 106, 207);
		panel.add(areaBotoes);
		
			btnNovo = new JButton("Novo");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layoutBtnNovo();
				}
			});
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String pacSexoDB = "";
						String pacNome = txtNome.getText();
						String pacCpf = txtCpf.getText();
						String pacRg = txtRg.getText();
						String pacDataNasc = txtDataNasc.getText();
						String pacSexo = cbSexo.getSelectedItem().toString();;
						if(pacSexo.equalsIgnoreCase("Masculino")) {
							pacSexoDB = "M";
						}else if(pacSexo.equalsIgnoreCase("Feminino")) {
							pacSexoDB = "F";
						}else {
							pacSexoDB = "";
						}
						String pacEmail = txtEmail.getText();
						String pacTelefone = txtTelefone.getText();
						boolean pacWhatsapp = rdbtnWhatsapp.isSelected();
						String pacCep = txtCep.getText();
						String pacEstado = txtEstado.getText();
						String pacCidade = txtCidade.getText();
						String pacBairro = txtBairro.getText();
						String pacRua = txtRua.getText();
						String pacNumero = txtNumero.getText();
						String pacComplemento = txtComplemento.getText();
												
						if(pacNome.isEmpty() || pacCpf.isEmpty() || pacRg.isEmpty() || pacDataNasc.isEmpty() || 
								pacSexo.isEmpty() || pacEmail.isEmpty() || pacTelefone.isEmpty() || 
								pacCep.isEmpty() || pacEstado.isEmpty() || pacCidade.isEmpty() || 
								pacBairro.isEmpty() || pacRua.isEmpty() || pacNumero.isEmpty()) {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),"Todos os campos precisa ser preenchidos");
						}else {
							String sql = "UPDATE tb_paciente SET nome=?, cpf=?, rg=?, dataNasc=?, sexo=?, email=?, telefone=?, whatsapp=?, cep=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, complemento=? WHERE id=?;";
							cx.pst = cx.con.prepareStatement(sql);
							cx.pst.setString(1, pacNome);
							cx.pst.setString(2, pacCpf);
							cx.pst.setString(3, pacRg);
							cx.pst.setString(4, pacDataNasc);
							cx.pst.setString(5, pacSexoDB);
							cx.pst.setString(6, pacEmail);
							cx.pst.setString(7, pacTelefone);
							cx.pst.setBoolean(8, pacWhatsapp);
							cx.pst.setString(9, pacCep);
							cx.pst.setString(10, pacEstado);
							cx.pst.setString(11, pacCidade);
							cx.pst.setString(12, pacBairro);
							cx.pst.setString(13, pacRua);
							cx.pst.setString(14, pacNumero);
							cx.pst.setString(15, pacComplemento);
							cx.pst.setInt(16, idSelecionado);		
							int sucesso = cx.pst.executeUpdate();				
							if(sucesso == 1) {
								JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Paciênte editado com sucesso!");
								layoutDefault();
							}else {
								JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),"Erro ao editado Paciênte!");
							}
						}
					}catch (SQLException ex) {
						Logger.getLogger(FormPaciente.class.getName()).log(Level.SEVERE, null, ex);
					}				
				}
			});
			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String sql = "delete from tb_paciente where id=?;";
						cx.pst = cx.con.prepareStatement(sql);
						cx.pst.setInt(1, idSelecionado);
						int sucesso = cx.pst.executeUpdate();
						if(sucesso == 1) {
							JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(), "Paciênte deletado com sucesso!");
							layoutDefault();
						}else {
							JOptionPane.showMessageDialog(btnExcluir.getTopLevelAncestor(),"Erro ao deletar Paciênte!");
						}
						
					} catch (SQLException ex) {
						Logger.getLogger(FormPaciente.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			});
			btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						String pacSexoDB = "";
						String pacNome = txtNome.getText();
						String pacCpf = txtCpf.getText();
						String pacRg = txtRg.getText();
						String pacDataNasc = txtDataNasc.getText();
						String pacSexo = cbSexo.getSelectedItem().toString();;
						if(pacSexo.equalsIgnoreCase("Masculino")) {
							pacSexoDB = "M";
						}else if(pacSexo.equalsIgnoreCase("Feminino")) {
							pacSexoDB = "F";
						}else {
							pacSexoDB = "";
						}
						String pacEmail = txtEmail.getText();
						String pacTelefone = txtTelefone.getText();
						boolean pacWhatsapp = rdbtnWhatsapp.isSelected();
						String pacCep = txtCep.getText();
						String pacEstado = txtEstado.getText();
						String pacCidade = txtCidade.getText();
						String pacBairro = txtBairro.getText();
						String pacRua = txtRua.getText();
						String pacNumero = txtNumero.getText();
						String pacComplemento = txtComplemento.getText();
												
						if(pacNome.isEmpty() || pacCpf.isEmpty() || pacRg.isEmpty() || pacDataNasc.isEmpty() || 
								pacSexo.isEmpty() || pacEmail.isEmpty() || pacTelefone.isEmpty() || 
								pacCep.isEmpty() || pacEstado.isEmpty() || pacCidade.isEmpty() || 
								pacBairro.isEmpty() || pacRua.isEmpty() || pacNumero.isEmpty()) {
							JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),"Todos os campos precisa ser preenchidos");
						}else {
							String sql = "INSERT INTO tb_paciente (nome, cpf, rg, dataNasc, sexo, email, telefone, whatsapp, cep, estado, cidade, bairro, rua, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							cx.pst = cx.con.prepareStatement(sql);
							cx.pst.setString(1, pacNome);
							cx.pst.setString(2, pacCpf);
							cx.pst.setString(3, pacRg);
							cx.pst.setString(4, pacDataNasc);
							cx.pst.setString(5, pacSexoDB);
							cx.pst.setString(6, pacEmail);
							cx.pst.setString(7, pacTelefone);
							cx.pst.setBoolean(8, pacWhatsapp);
							cx.pst.setString(9, pacCep);
							cx.pst.setString(10, pacEstado);
							cx.pst.setString(11, pacCidade);
							cx.pst.setString(12, pacBairro);
							cx.pst.setString(13, pacRua);
							cx.pst.setString(14, pacNumero);
							cx.pst.setString(15, pacComplemento);
							int sucesso = cx.pst.executeUpdate();				
							if(sucesso == 1) {
								JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(), "Paciênte adicionado com sucesso!");
								layoutDefault();
							}else {
								JOptionPane.showMessageDialog(btnSalvar.getTopLevelAncestor(),"Erro ao adicionar Paciênte!");
							}
						}
					}catch (SQLException ex) {
						Logger.getLogger(FormPaciente.class.getName()).log(Level.SEVERE, null, ex);
					}			
				}

			});
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JanelaPrincipal menuPrincipal = new JanelaPrincipal();
					setVisible(false);
				}
			});
			
			areaBotoes.setLayout(new GridLayout(0, 1, 0, 0));
			areaBotoes.add(btnNovo);
			JLabel lblNewLabel_bnt_1 = new JLabel("");
			areaBotoes.add(lblNewLabel_bnt_1);
			areaBotoes.add(btnEditar);
			JLabel lblNewLabel_bnt_2 = new JLabel("");
			areaBotoes.add(lblNewLabel_bnt_2);
			areaBotoes.add(btnExcluir);
			JLabel lblNewLabel_bnt_3 = new JLabel("");
			areaBotoes.add(lblNewLabel_bnt_3);
			areaBotoes.add(btnSalvar);
			JLabel lblNewLabel_bnt_4 = new JLabel("");
			areaBotoes.add(lblNewLabel_bnt_4);
			areaBotoes.add(btnCancelar);
			JLabel lblNewLabel = new JLabel("Cadastro de Pacientes");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
			lblNewLabel.setBounds(250, 10, 200, 20);
			contentPane.add(lblNewLabel);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						if (table.getSelectedRow() > -1) {
							idSelecionado = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
							String consultaSQL = "SELECT * FROM tb_paciente WHERE id = ?";
							cx.pst = cx.con.prepareStatement(consultaSQL);
							cx.pst.setInt(1, idSelecionado);
							cx.rs = cx.pst.executeQuery();
							if (cx.rs.next() == true) {
								txtNome.setText(cx.rs.getString(2));
								txtCpf.setText(cx.rs.getString(3));
								txtRg.setText(cx.rs.getString(4));
								txtDataNasc.setText(cx.rs.getString(5));
								if(cx.rs.getString(6).equals("M")) {
									cbSexo.setSelectedIndex(1);		
								}else if(cx.rs.getString(6).equals("F")) {
									cbSexo.setSelectedIndex(2);		
								}else {
									cbSexo.setSelectedIndex(0);	
								}
								txtEmail.setText(cx.rs.getString(7));
								txtTelefone.setText(cx.rs.getString(8));
								if(Integer.parseInt(cx.rs.getString(9)) == 1) {
									rdbtnWhatsapp.setSelected(true);
								}else {
									rdbtnWhatsapp.setSelected(false);
								}
								txtCep.setText(cx.rs.getString(10));
								txtEstado.setText(cx.rs.getString(11));
								txtCidade.setText(cx.rs.getString(12));
								txtBairro.setText(cx.rs.getString(13));
								txtRua.setText(cx.rs.getString(14));
								txtNumero.setText(cx.rs.getString(15));
								txtComplemento.setText(cx.rs.getString(16));
								layoutSelecionarPacienteTable();
							}
						}
					} catch (Exception ex) {
						Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			});
			scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CPF", "E-mail", "Telefone"}));
	
			layoutDefault();
	}
	
	
}
