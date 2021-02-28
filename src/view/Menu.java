package view;

import javax.swing.JOptionPane;

import controller.Processos;

public class Menu {
	public static void main (String args[]) {
		Object [] opc = {"Listar Processos","Matar por PID", "Matar por nome"};
		Processos Proc = new Processos();
		
		String so = Proc.so();
		int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opcao", "Opcoes",0, JOptionPane.DEFAULT_OPTION,null, opc, opc[0]);
		if (escolha == 0) {
			Proc.LerProcessos(so);
		}
		else if (escolha == 1) {
			int Pid = Integer.parseInt(JOptionPane.showInputDialog("Digite o PID"));
			Proc.PorPID(Pid, so);
		}
		else if (escolha == 2) {
			String Name = JOptionPane.showInputDialog("Nome do processo");
			Proc.PorNome(Name, so);
		}
	}
}
