package viewers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controllers.OrcamentoProcess;
import models.Orcamento;

import javax.swing.*;

public class OrcamentoForm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JTable table;
	private JLabel id, fornecedor, produto, preco;
	private JTextField tfId, tfFornecedor, tfProduto, tfPreco;
	private JScrollPane rolagem;
	private JButton btAlterar, btExcluir, btBuscar, btAdicionar;
	private int autoId = OrcamentoProcess.orc.size() + 1;
	private DefaultTableModel tableModel;

	OrcamentoForm() {
		setTitle("VPS02");
		setBounds(100, 100, 800, 600);
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		setContentPane(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		id = new JLabel("id");
		id.setBounds(20, 10, 500, 20);
		panel.add(id);

		fornecedor = new JLabel("fornecedor");
		fornecedor.setBounds(20, 40, 500, 20);
		panel.add(fornecedor);
		fornecedor.setFont(new Font("Arial", Font.PLAIN, 15));

		produto = new JLabel("produto");
		produto.setBounds(20, 80, 500, 20);
		panel.add(produto);
		produto.setFont(new Font("Arial", Font.PLAIN, 15));

		preco = new JLabel("preço");
		preco.setBounds(20, 120, 500, 20);
		panel.add(preco);
		preco.setFont(new Font("Arial", Font.PLAIN, 15));

		table = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.addColumn("id");
		tableModel.addColumn("fornecedor");
		tableModel.addColumn("produto");
		tableModel.addColumn("preço");
		preencherTabela();
		table = new JTable(tableModel);
		rolagem = new JScrollPane(table);
		rolagem.setBounds(20, 310, 740, 230);
		panel.add(rolagem);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int lin = table.getSelectedRow();
				tfId.setText(tableModel.getValueAt(lin, 0).toString());
				tfFornecedor.setText(tableModel.getValueAt(lin, 1).toString());
				tfProduto.setText(tableModel.getValueAt(lin, 2).toString());
				tfPreco.setText(tableModel.getValueAt(lin, 3).toString());
				btAdicionar.setEnabled(false);
				btBuscar.setEnabled(false);
				btExcluir.setEnabled(true);
				btAlterar.setEnabled(true);

			}
		});

		tfId = new JTextField(String.format("%d", autoId));
		tfId.setBounds(100, 10, 200, 20);
		panel.add(tfId);
		tfId.setEditable(false);

		tfFornecedor = new JTextField();
		tfFornecedor.setBounds(100, 40, 200, 25);
		panel.add(tfFornecedor);

		tfProduto = new JTextField();
		tfProduto.setBounds(100, 80, 200, 25);
		panel.add(tfProduto);

		tfPreco = new JTextField();
		tfPreco.setBounds(100, 120, 200, 25);
		panel.add(tfPreco);

		btAdicionar = new JButton("Enviar");
		btAdicionar.setBounds(400, 20, 100, 20);
		panel.add(btAdicionar);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(505, 20, 100, 20);
		panel.add(btExcluir);
		btExcluir.setEnabled(false);

		btBuscar = new JButton("Buscar");
		btBuscar.setBounds(610, 20, 100, 20);
		panel.add(btBuscar);
		btBuscar.setEnabled(false);

		btAlterar = new JButton("Alterar");
		btAlterar.setBounds(505, 50, 100, 20);
		panel.add(btAlterar);
		btAlterar.setEnabled(false);

		btAdicionar.addActionListener(this);
		btExcluir.addActionListener(this);
		btBuscar.addActionListener(this);
		btAlterar.addActionListener(this);

	}

	private void preencherTabela() {
		int totLinhas = tableModel.getRowCount();
		if (tableModel.getRowCount() > 0) {
			for (int i = 0; i < totLinhas; i++) {
				tableModel.removeRow(0);
				OrcamentoProcess.compararProdutos();
			}
		}
		for (Orcamento c : OrcamentoProcess.orc) {
			tableModel.addRow(new String[] { c.getId("s"), c.getFornecedor(), c.getProduto(),
					String.format(c.getPreco().toString()) });
		}

	}

	public void limparCampos() {
		tfFornecedor.setText(null);
		tfProduto.setText(null);
		tfPreco.setText(null);

	}

	private void enviar() {
		if (tfFornecedor.getText().length() != 0 && tfProduto.getText().length() != 0
				&& tfPreco.getText().length() != 0) {

			OrcamentoProcess.orc.add(new Orcamento(autoId, tfFornecedor.getText(), tfProduto.getText(),
					Double.parseDouble(tfPreco.getText())));
			autoId++;
			preencherTabela();
			limparCampos();

		} else {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos");
		}

	}

	private void alterar() {
		int id = Integer.parseInt(tfId.getText());
		Orcamento cadastro = new Orcamento(id);
		int indice = OrcamentoProcess.orc.indexOf(cadastro);
		if (tfFornecedor.getText().length() != 0 && tfProduto.getText().length() != 0
				&& tfPreco.getText().length() != 0) {

			OrcamentoProcess.orc.set(indice, new Orcamento(autoId, tfFornecedor.getText(), tfProduto.getText(),
					Double.parseDouble(tfPreco.getText())));
			autoId++;
			preencherTabela();
			limparCampos();

		} else {
			
		}

	}
	
	

	private void excluir() {

		int id = Integer.parseInt(tfId.getText());
		Orcamento cadastro = new Orcamento(id);
		int indice = OrcamentoProcess.orc.indexOf(cadastro);
		OrcamentoProcess.orc.remove(indice);
		preencherTabela();
		limparCampos();
		tfId.setText(String.format("%d", autoId));
		autoId--;

	}

	private void buscar() {

		String entrada = JOptionPane.showInputDialog(this, "Digite o Id do produto:");

		boolean isNumeric = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					isNumeric = false;
				}
			}
		} else {
			isNumeric = false;
		}
		if (isNumeric) {
			int id = Integer.parseInt(entrada);
			Orcamento cadastro = new Orcamento(id);
			if (OrcamentoProcess.orc.contains(cadastro)) {
				int indice = OrcamentoProcess.orc.indexOf(cadastro);
				tfId.setText(OrcamentoProcess.orc.get(indice).getId("s"));
				tfFornecedor.setText(OrcamentoProcess.orc.get(indice).getFornecedor());
				tfProduto.setText(OrcamentoProcess.orc.get(indice).getProduto());
				tfPreco.setText(OrcamentoProcess.orc.get(indice).getPreco().toString());
				OrcamentoProcess.salvar();

			} else {
				JOptionPane.showMessageDialog(this, "Produto não encontrado");
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btAdicionar) {
			enviar();
			btBuscar.setEnabled(true);
		}
		if (e.getSource() == btExcluir) {
			excluir();
			btExcluir.setEnabled(false);
			btAlterar.setEnabled(false);
			btAdicionar.setEnabled(true);
			btBuscar.setEnabled(true);
		}
		if (e.getSource() == btBuscar) {
			buscar();
			btExcluir.setEnabled(true);
			btAlterar.setEnabled(true);
			btAdicionar.setEnabled(false);
		}
		if (e.getSource() == btAlterar) {
			alterar();
			btExcluir.setEnabled(false);
			btAlterar.setEnabled(false);
			btAdicionar.setEnabled(true);
			btBuscar.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		OrcamentoProcess.abrir();
		new OrcamentoForm().setVisible(true);
	}

}
