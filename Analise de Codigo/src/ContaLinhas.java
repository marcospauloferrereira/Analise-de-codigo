import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
/**
 * @author Marcos
 */
public class ContaLinhas{
	/**
	 * Declaração dos atributos
	 */
	private String subDir;  
	private int somaLinhas = 0;
	private int somaClasses = 0;
	private int somaDiretorios = 0;
	private String out = "";
	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		ContaLinhas cl = new ContaLinhas ();
		String path = JOptionPane.showInputDialog("Diretório do projeto: "); 
		cl.contador(path);
		/**
		 * Apresenta os resultados no console
		 */
		System.err.println("\nSub-pastas do projeto = " + cl.getSomaDiretorios() + " pastas");
		System.err.println("Classes do projeto = " + cl.getSomaClasses() + " classes");
		System.err.println("Linhas de codigo do projeto = " + cl.getSomaLinhas() + " linhas");
		/**
		 * Adiciona os resultados obtidos ao atributo "out", o qual será gravado no arquivo
		 */
		cl.setOut("\n\n\n\n=============================================" + 
				"\nSub-pastas do projeto = " + cl.getSomaDiretorios() + " pastas" + 
				"\nClasses do projeto = " + cl.getSomaClasses() + " classes" + 
				"\nLinhas de codigo do projeto = " + cl.getSomaLinhas() + " linhas");
		cl.gravaArquivoTxt(path, cl);
	}
	/**
	 * Realiza a gravação dos resultados obtidos
	 * @param path
	 */
	public void gravaArquivoTxt (String path, ContaLinhas cl){
		try{      
			/**
			 * Grava no arquivo
			 */
			File file = new File(path + "/Restult.txt");  
			FileWriter fw = new FileWriter(file, true );
			PrintWriter ps = new PrintWriter(fw, true );  
			ps.println(cl.getOut());  
			JOptionPane.showMessageDialog(null, "Um arquivo com os resultados foi gerado no diretório do seu projeto!", "Informação", JOptionPane.INFORMATION_MESSAGE);
		}catch( IOException e ){
			e.printStackTrace();  
		}
	}
	/**
	 * Faz a contagem de linhas de todo projeto
	 * @param path
	 */
	public void contador (String path){
		File diretorios = new File(path);  
		File[] files = diretorios.listFiles();
		/** 
		 * É feita uma iteração com o vetor que armazenou o path dos arq. e dirs. do dir. recebido como parâmetro inicial **/
		for(int i = 0;i < files.length; i++) {
			/** 
			 * Verifica se o camniho da posição atual do vetor "files", é um arq. ou um dir., caso for um dir., ele entra no if **/
			if (files[i].isDirectory()){
				/**
				 *  Incrementa o atributo "SomaDiretorios" **/
				setSomaDiretorios(1);
				/** 
				 * Atribui o caminho do próximo diretório à variável "subDir", e mostra o dir.no console **/
				subDir = files[i].toString();
				setOut("\n\nPath = " + subDir);
				System.out.println("\nPath = " + subDir);
				try{
					/** 
					 * Carrega em memória todos os "paths" de dirs. e arqs. que estão dentro do path armazenado em "subDir" **/
					File dir = new File(subDir);  
					File[] file = dir.listFiles();		
					/** 
					 * Faz uma iteração com o vetor "file" **/
					for(int j = 0; j < file.length; j++)  
					{  
						/** 
						 * Carrega em memória o path da posição atual do vetor "file" **/
						File f = new File(file[j].getPath());
						/** 
						 * Verifica se a posição atual do vetor "file" é um dir. caso não, ele entra **/
						if (!(file[j].isDirectory()) && file[j].getPath().endsWith(".java")){
							/**
							 *  Armazena em buffer todo conteúdo do arquivo **/
							BufferedReader arquivo = new BufferedReader(new FileReader(f));  
							int somaAux = 0;
							/** 
							 * Enquanto não for final do arquivo ele persiste no laço **/
							while((arquivo.readLine()) != null){  
								/**
								 *  A cada iteração é correspondido a uma linha nova, assim o atributo "somaLinhas" é incrementado **/
								setSomaLinhas(1);
								somaAux++;
							}  
							int lastSlashIndex = file[j].getPath().lastIndexOf('/');
							/**
							 *  Mostra na tela o arquivo + sua quantidade de linhas **/					
							System.err.println(file[j].getPath().substring(lastSlashIndex + 1) + " = " + somaAux + " linhas");
							setOut("\n\t " + file[j].getPath().substring(lastSlashIndex + 1) + " = " + somaAux + " linhas");
							/**
							 *  O atributo "somaClasses" é incrementado à cada arquivo lido **/
							setSomaClasses(1);
						}
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				/**
				 *  Chama recursivamente a função, só que agora com o próximo nível de diretório, 
				 *  assim consecutivamente até que o último subdiretório seja encontrado.
				 */
				contador(subDir);
			}	
		}
	}
	/**
	 * Seta o atributo "somaLinhas"
	 */
	public void setSomaLinhas (int somaLinhas){
		this.somaLinhas = this.somaLinhas + somaLinhas;
	}
	/**
	 * Retorna o valor do atributo "somaLinhas"
	 * @return
	 */
	public int getSomaLinhas (){
		return this.somaLinhas;
	}
	/**
	 * Seta o atributo "somaClasses"
	 */
	public void setSomaClasses (int somaClasses){
		this.somaClasses = this.somaClasses + somaClasses;
	}
	/**
	 * Retorna o valor do atributo "somaClasses"
	 * @return
	 */
	public int getSomaClasses (){
		return this.somaClasses;
	}
	/**
	 * Seta o atributo "somaDiretorios"
	 */
	public void setSomaDiretorios (int somaDiretorios){
		this.somaDiretorios = this.somaDiretorios + somaDiretorios;
	}
	/**
	 * Retorna o valor do atributo "somaDiretorios"
	 * @return
	 */
	public int getSomaDiretorios (){
		return this.somaDiretorios;
	}
	/**
	 * Seta o atributo "out"
	 */
	public void setOut (String out){
		this.out = this.out + out; 
	}
	/**
	 * Retorna o valor do atributo "out"
	 * @return
	 */
	public String getOut (){
		return this.out;
	}
}