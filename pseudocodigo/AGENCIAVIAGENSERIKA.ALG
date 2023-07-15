Algoritmo "Bedin Viagens"
Var
   //cadastro de clientes com vetor e enquanto
   nome: vetor[1..1000] de caractere
   cpf: vetor[1..1000] de inteiro
   endereco: vetor[1..1000] de caractere
   telefone: vetor[1..1000] de inteiro
   dataNascimento: vetor[1..1000] de inteiro
   opcao: caractere
   totalClientes: inteiro
   i: inteiro
   //cadastro de destinos com vetor e enquanto
   dataPartida: vetor[1..1000] de caractere
   dataRetorno: vetor[1..1000] de caractere
   origem: vetor[1..1000] de caractere
   destino: vetor[1..1000] de caractere
   qtdPessoas: vetor[1..1000] de inteiro
   qtdQuartos: vetor[1..1000] de inteiro
   companhiaAerea: vetor[1..1000] de caractere
   totalDestinos: inteiro
   j: inteiro
Inicio
   totalClientes <- 0
   opcao <- "s"
   Enquanto (opcao = "s") ou (opcao = "S") faca
      totalClientes <- totalClientes + 1
      Escreval("Cadastro do cliente ", totalClientes)
      Escreval("------------------------")
      Escreval("Nome: ")
      Leia(nome[totalClientes])
      Escreval("CPF: ")
      Leia(cpf[totalClientes])
      Escreval("Endereço: ")
      Leia(endereco[totalClientes])
      Escreval("Telefone: ")
      Leia(telefone[totalClientes])
      Escreval("Data de Nascimento: ")
      Leia(dataNascimento[totalClientes])
      opcao <- "s"
      Enquanto (opcao = "s") ou (opcao = "S") faca
         Escreval("------------------------")
         totalDestinos <- totalDestinos + 1
         Escreval("Cadastro do destino para ", nome[totalClientes])
         Escreval("------------------------")
         Escreval("Data de Partida: ")
         Leia(dataPartida[totalDestinos])
         Escreval("Data de Retorno: ")
         Leia(dataRetorno[totalDestinos])
         Escreval("Origem: ")
         Leia(origem[totalDestinos])
         Escreval("Destino: ")
         Leia(destino[totalDestinos])
         Escreval("Quantidade de Pessoas: ")
         Leia(qtdPessoas[totalDestinos])
         Escreval("Quantidade de Quartos: ")
         Leia(qtdQuartos[totalDestinos])
         Escreval("Companhia Aérea: ")
         Leia(companhiaAerea[totalDestinos])
         Escreval("Deseja cadastrar outro destino para ", nome[totalClientes], "? (S/N): ")
         Leia(opcao)
      FimEnquanto
      Escreval("Deseja cadastrar outro cliente? (S/N): ")
      Leia(opcao)
   FimEnquanto
//Consulta para cada estrutura de dados dos clientes e destinos
   Escreval("Total de clientes cadastrados: ", totalClientes)
   Escreval("Dados dos clientes cadastrados:")
   Para i <- 1 até totalClientes faca
      Escreval("------------------------")
      Escreval("Cliente ", i)
      Escreval("Nome: ", nome[i])
      Escreval("CPF: ", cpf[i])
      Escreval("Endereço: ", endereco[i])
      Escreval("Telefone: ", telefone[i])
      Escreval("Data de Nascimento: ", dataNascimento[i])
      Escreval("Destinos cadastrados: ", totalDestinos)
      Escreval("Dados dos destinos:")
      Para j <- 1 até totalDestinos faca
         Escreval("------------------------")
         Escreval("Destino ", j)
         Escreval("Data de Partida: ", dataPartida[j])
         Escreval("Data de Retorno: ", dataRetorno[j])
         Escreval("Origem: ", origem[j])
         Escreval("Destino: ", destino[j])
         Escreval("Quantidade de Pessoas: ", qtdPessoas[j])
         Escreval("Quantidade de Quartos: ", qtdQuartos[j])
         Escreval("Companhia Aérea: ", companhiaAerea[j])
      FimPara
   FimPara
FimAlgoritmo