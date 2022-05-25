package view;

import java.io.IOException;

import controller.IOpArquivos;
import controller.OpArquivos;

public class Principal {
	
	public static void main(String[] args) {
		IOpArquivos op = new OpArquivos();
		
		String caminho = "C:\\Windows\\System32";
		String diretorio = "E:\\TEMP";
//		String arquivo = "tabela.csv";
		String arquivo = "11_-_Sistemas_de_Arquivos.pdf";
		try {
//			op.leDir(caminho);
//			op.escreveArquivo(diretorio, arquivo);
//			op.leArquivo(diretorio, arquivo);
			op.abreArquivo(diretorio, arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
