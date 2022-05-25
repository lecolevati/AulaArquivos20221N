package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Produto;

public class OpArquivos implements IOpArquivos {

	@Override
	public void leDir(String diretorio) throws IOException {
		File dir = new File(diretorio);
		if (dir.exists() && dir.isDirectory()) {
			File[] lista = dir.listFiles();
			for (File arq : lista) {
				if (arq.isFile()) {
					System.out.println("      "+arq.getName());
				} else {
					System.out.println("<DIR> "+arq.getName());
				}
			}
		} else {
			throw new IOException("Diretório não existe");
		}
	}

	@Override
	public void escreveArquivo(String diretorio, String arquivo) throws IOException {
		File arq = new File(diretorio, arquivo);
		File dir = new File(diretorio);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists() && arq.isFile()) {
				existe = true;
			}
			String conteudo = geraConteudo();
			FileWriter abreArquivo = new FileWriter(arq, existe);
			PrintWriter escreveArquivo = new PrintWriter(abreArquivo);
			escreveArquivo.write(conteudo);
			escreveArquivo.flush();
			escreveArquivo.close();
			abreArquivo.close();
		} else {
			throw new IOException("Diretorio inválido");
		}
	}

	private String geraConteudo() {
		String texto = "";
		StringBuffer buffer = new StringBuffer();
		while (!texto.equals("fim")) {
			texto = JOptionPane.showInputDialog(null, "Digite uma frase");
			if (!texto.equals("fim")) {
				buffer.append(texto+"\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void leArquivo(String diretorio, String arquivo) throws IOException {
		File arq = new File(diretorio, arquivo);
		Charset charset = Charset.forName("UTF-8");
		List<Produto> lista = new ArrayList<Produto>();
		if (arq.exists() && arq.isFile()) {
			lista = geraProduto(arq, charset, lista);
			for (Produto p : lista) {
				System.out.print(p.getId()+"\t");
				System.out.print(p.getNome());
				if (p.getNome().length() <= 7) {
					System.out.print("\t\t");
				} else {
					System.out.print("\t");
				}
				System.out.println(p.getPreco()+"\t");
			}
		} else {
			throw new IOException("Arquivo inválido");
		}
	}

	private List<Produto> geraProduto(File arq, Charset charset, List<Produto> lista) throws FileNotFoundException, IOException {
		FileInputStream abreArquivo = new FileInputStream(arq);
		InputStreamReader leARquivo = new InputStreamReader(abreArquivo, charset);
		BufferedReader buffer = new BufferedReader(leARquivo);
		String linha = buffer.readLine();
		while (linha != null) {
			Produto produto = new Produto();
			String[] vetProduto = linha.split(";");
			produto.setId(Integer.parseInt(vetProduto[0]));
			produto.setNome(vetProduto[1]);
			produto.setPreco(Float.parseFloat(vetProduto[2]));
			
			lista.add(produto);
			linha = buffer.readLine();
		}
		buffer.close();
		leARquivo.close();
		abreArquivo.close();
		
		return lista;
	}

	@Override
	public void abreArquivo(String diretorio, String arquivo) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		File arq = new File(diretorio, arquivo);
		if (arq.exists() && arq.isFile() && arq.length() > 0) {
			desktop.open(arq);
		}
	}

}
