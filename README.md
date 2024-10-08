Gerenciador de Estacionamento
Descrição
O Gerenciador de Estacionamento é um sistema simples para o controle de até 50 veículos em um estacionamento. Ele oferece funcionalidades para registrar entradas e saídas, calcular tarifas e gerar relatórios utilizando arquivos CSV. Além disso, utiliza uma API de geolocalização para fornecer a localização exata do estacionamento.

Instalação
Siga as instruções abaixo para clonar e configurar o projeto:

# Clone o repositório
https://github.com/Cogah/GerenciamentoDeEstacionamento.git

# Baixe os arquivos da pasta
Gerenciamento de Estacionameto

#Procure pelo arquivo java
Gerenciador.java

#Altere os caminhos indicados para os caminhos que serão usados em seu computador
private static final String CSVPathOUT = "C:Caminho\\csv\\generate.csv";
private static final String CSVPathIN = "C:Caminho\\in.csv";

# Compile o código no arquivo
App.java
Tecnologias Utilizadas
Java: Linguagem principal usada para desenvolver o sistema.
CSV: Geração de arquivos CSV para armazenamento de registros.
API de Geolocalização: Integração para localizar o estacionamento.
