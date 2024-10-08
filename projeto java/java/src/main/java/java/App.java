package gradlee;

public class App {
    public static void main(String[] args) throws Exception{

        Gerenciador gerencia = new Gerenciador();
    
        gerencia.acharEndereco();    
        gerencia.lerArquivoCSV();
        gerencia.listagem();

        do {
            gerencia.menu();
        }while(gerencia.op != 5);
        
    }
}