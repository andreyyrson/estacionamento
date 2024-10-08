
package gradlee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Gerenciador{

    private static final String CSVPathOUT = "C:\\Users\\gabri\\OneDrive\\Documentos\\Códigos\\Java\\Gerenciador de Estacionamento\\csv\\generate.csv";
    private static final String CSVPathIN = "C:\\Users\\gabri\\OneDrive\\Documentos\\Códigos\\Java\\Gerenciador de Estacionamento\\csv\\in.csv";
    //Mudar os caminhos do CSV para cada pc

    Scanner sc = new Scanner(System.in);
    Veiculos veiculos[] = new Veiculos[50];
    int qntVeiculo = 0;
    float tarifa;
    int op;

    public void acharEndereco(){
        GeolocationServiceNominatim geolocationService = new GeolocationServiceNominatim();// Instanciação do serviço de geolocalização
        String endereco = "Avenida Alcindo Cacela 1523, Umarizal, Brazil";// Endereço do estacionamento
        System.out.println("Coordenadas do estacionamento: ");
        geolocationService.obterCoordenadas(endereco);
    }
    
    public void cadastro(String placa, String modelo, int horarioEntrada){
        for(int i=0;i<50;i++){
            if(veiculos[i] == null) {
                veiculos[i] = new Veiculos();
                veiculos[i].placa = placa;
                veiculos[i].modelo = modelo;
                veiculos[i].horarioEntrada = horarioEntrada;
                qntVeiculo +=1;
                return;
                }
        }
        System.out.println("Estacionamento cheio! Não é possível cadastrar mais veículos.");
    }
    public void cadastroInput() {
        for(int i=0;i<50;i++){
            if(veiculos[i] == null) {
            Veiculos novoVeiculo = new Veiculos();
            novoVeiculo.Cadastrar();
            veiculos[i] = novoVeiculo;
            System.out.println("Veículo cadastrado via input: " + novoVeiculo.placa + ", " + novoVeiculo.modelo + ", " + novoVeiculo.horarioEntrada);
            qntVeiculo++;
            return;
        }
        
        }
        System.out.println("Estacionamento cheio! Não é possível cadastrar mais veículos.");
    }

    public void saida(){
        System.out.println("Digite o numero do veículo que saiu:");
        int v = sc.nextInt();
        
        System.out.println("Digite o horário de saída: ");
        veiculos[v-1].horarioSaida = sc.nextInt();
        veiculos[v-1].CalcularTarifa();
        System.out.println("Tarifa a pagar: "+ veiculos[v-1].getTarifa());
        veiculos[v-1] = null;
        
        Veiculos novoVeiculos[] = new Veiculos[50];
        int j = 0;

        for(int i=0;i<qntVeiculo;i++){
            if (veiculos[i] != null) {
                novoVeiculos[j] = veiculos[i];
                j++;
            }
        }
        veiculos = novoVeiculos;
        qntVeiculo--;
    }
    
    public void menu(){
        System.out.println("\n    Bem vindo ao Sistema de Veículos:\n Digite o número para escolher\n\n1.Cadastrar novo veículo\n2.Saída de um veículo\n3.Listar os veículos no Estacionamento\n4.Gerar arquivo CSV\n5.Encerrar sistema");
        op = sc.nextInt();

        switch (op) {
            case 1:
              cadastroInput();
              break;
            case 2:
              listagem();
              saida();
              break;
            case 3:
              listagem();
              break;
            case 4:
                gerarArquivoCSV();
                break;
            case 5:
                System.out.println("Obrigado pelo seu trabalho!");
                break;
                
        }
    }
    
    public void listagem() {
        if (qntVeiculo == 0) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        
        for (int i = 0; i < qntVeiculo; i++) {
            if (veiculos[i] != null) {
                System.out.println("Veículo " + (i + 1) + ": Modelo = " + veiculos[i].modelo +
                                   ", Placa = " + veiculos[i].placa +
                                   ", Horário de Entrada = " + veiculos[i].horarioEntrada);
            } else {
                System.out.println("Veículo na posição " + i+1 + " é nulo.");
            }
        }
    }

      public void gerarArquivoCSV() {
        try (FileWriter writer = new FileWriter(CSVPathOUT)) {
            writer.append("  Numero,  Placa,  Modelo,  Horario de entrada\n");
            for (int i = 0; i < qntVeiculo; i++) {
                writer.append(i+1+" ");
                writer.append(",");
                writer.append(veiculos[i].placa); 
                writer.append(",");
                writer.append(veiculos[i].modelo);
                writer.append(",");
                writer.append(veiculos[i].horarioEntrada+"");
                writer.append("\n");
            }

            System.out.println("Arquivo CSV criado com sucesso em : "+ CSVPathOUT);
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo CSV: " + e.getMessage());
        }
    }

    public void lerArquivoCSV(){
        try (Scanner scfile = new Scanner(new File(CSVPathIN))) {
            scfile.nextLine();
            while (scfile.hasNextLine()) {  
                String linha = scfile.nextLine();
                String[] dados = linha.split(",");
                
                String inPlaca = dados[1];
                String inModelo = dados[2];
                int inHorario = Integer.parseInt(dados[3]);

                cadastro(inPlaca,inModelo,inHorario);
            }
            
            System.out.println("Arquivo CSV importado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }