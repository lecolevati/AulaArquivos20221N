package controller;

import java.io.IOException;

public interface IOpArquivos {

	public void leDir(String diretorio) throws IOException;
	public void escreveArquivo(String diretorio, String arquivo) throws IOException;
	public void leArquivo(String diretorio, String arquivo) throws IOException;
	public void abreArquivo(String diretorio, String arquivo) throws IOException;
	
}
