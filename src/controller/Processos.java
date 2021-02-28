package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Processos {
	
	public void LerProcessos(String so) {
		if (so.contains("Windows")){
			try {
				Process p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (so.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ps -ef");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public String so() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void PorPID(int id, String so){
		if (so.contains("Windows")) {
			String cmdPid = "TASKKILL /PID";
			int pid = 0;
			StringBuffer buffer = new StringBuffer();
			
			try{
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(id);
			}
			catch(Exception e){
				e.printStackTrace();                                                              
			}
			chamarProcessos(buffer.toString());
		}
		else if (so.contains("Linux")){		
			String cmdPid = "kill -9";
			int pid = 0;
			StringBuffer buffer = new StringBuffer();
			
			try{
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(id);
			}
			catch(Exception e){
				e.printStackTrace();                                                              
			}
			chamarProcessos(buffer.toString());
		}
	}	
	public void PorNome(String Name, String so) {
		if (so.contains("Windows")) {
			String CmdName = "TASKKILL /IM";
			StringBuffer buffer = new StringBuffer();
			
			try{
				buffer.append(CmdName);
				buffer.append(" ");
				buffer.append(Name);
			}
			catch(Exception e){
				e.printStackTrace();                                                              
			}
			chamarProcessos(buffer.toString());	
		}
		else if(so.contains("Linux")) {
			String CmdName = "pkill -f";
			StringBuffer buffer = new StringBuffer();
			
			try{
				buffer.append(CmdName);
				buffer.append(" ");
				buffer.append(Name);
			}
			catch(Exception e){
				e.printStackTrace();                                                              
			}
			chamarProcessos(buffer.toString());
		}
	}
	public void chamarProcessos(String id) {
		try {
			Runtime.getRuntime().exec(id);
		} 
		catch (Exception e) {
			String msgError = e.getMessage();
			//System.err.println(msgError);
			if (msgError.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(id);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			else {
				e.printStackTrace();
			}
		}
	}
}