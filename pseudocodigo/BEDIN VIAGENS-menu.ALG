Algoritmo "Bedin Viagens"
// Atividade Prática do Módulo 2
Var
   nome: vetor[1..1000] de caractere
   cpf: vetor[1..1000] de inteiro
   endereco: vetor[1..1000] de caractere
   telefone: vetor[1..1000] de inteiro
   dataNascimento: vetor[1..1000] de inteiro
   destinoDataPartida: vetor[1..1000] de inteiro
   destinoDataRetorno: vetor[1..1000] de inteiro
   destinoOrigem: vetor[1..1000] de caractere
   destinoDestino: vetor[1..1000] de caractere
   destinoQtdPessoas: vetor[1..1000] de inteiro
   destinoQtdQuartos: vetor[1..1000] de inteiro
   destinoCompanhiaAerea: vetor[1..1000] de caractere
   opcao: caractere
   totalClientes: inteiro
   totalDestinos: vetor[1..1000] de inteiro
   i, j: inteiro
Inicio
   totalClientes <- 0
   Enquanto opcao <> "0" faca
      Escreval("========== Bedin Viagens ==========")
      Escreval("1 - Cadastrar Cliente")
      Escreval("2 - Cadastrar Destino")
      Escreval("3 - Listar Cadastros")
      Escreval("0 - Sair")
      Escreval("===================================")
      Escreval("Escolha uma opção: ")
      Leia(opcao)
      Escolha opcao
         Caso "1"
            opcao <- "s"
            Enquanto (opcao = "s") ou (opcao = "S") faca
               totalClientes <- totalClientes + 1
               Escreval("===== Cadastro de Cliente =====")
               Escreval("Cliente ", totalClientes)
               Escreval("-----------------------------")
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
               Escreval("-----------------------------")
               Escreval("Cliente cadastrado com sucesso!")
               Escreval("-----------------------------")
               Escreval("Deseja cadastrar um novo cliente (S/N)? ")
               Leia(opcao)
            FimEnquanto
         Caso "2"
            totalDestinos[totalClientes] <- 0
            opcao <- "s"
            Enquanto (opcao = "s") ou (opcao = "S") faca
               totalDestinos[totalClientes] <- totalDestinos[totalClientes] + 1
               Escreval("===== Cadastro de Destino =====")
               Escreval("Cliente: ", nome[totalClientes])
               Escreval("Destino ", totalDestinos[totalClientes])
               Escreval("-----------------------------")
               Escreval("Data de Partida: ")
               Leia(destinoDataPartida[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Data de Retorno: ")
               Leia(destinoDataRetorno[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Origem: ")
               Leia(destinoOrigem[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Destino: ")
               Leia(destinoDestino[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Quantidade de Pessoas: ")
               Leia(destinoQtdPessoas[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Quantidade de Quartos: ")
               Leia(destinoQtdQuartos[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("Companhia Aérea: ")
               Leia(destinoCompanhiaAerea[(totalClientes - 1) * 100 + totalDestinos[totalClientes]])
               Escreval("-----------------------------")
               Escreval("Destino cadastrado com sucesso!")
               Escreval("-----------------------------")
               Escreval("Deseja cadastrar outro destino para ", nome[totalClientes], "? (S/N): ")
               Leia(opcao)
            FimEnquanto
         Caso "3"
            Escreval("===== Lista de Cadastros =====")
            Para i <- 1 até totalClientes faça
               Escreval("-----------------------------")
               Escreval("Cliente ", i)
               Escreval("Nome: ", nome[i])
               Escreval("CPF: ", cpf[i])
               Escreval("Endereço: ", endereco[i])
               Escreval("Telefone: ", telefone[i])
               Escreval("Data de Nascimento: ", dataNascimento[i])
               Escreval("Destinos Cadastrados:")
               Para j <- 1 até totalDestinos[i] faça
                  Escreval("-----------------------------")
                  Escreval("Destino ", j)
                  Escreval("Data de Partida: ", destinoDataPartida[(i - 1) * 100 + j])
                  Escreval("Data de Retorno: ", destinoDataRetorno[(i - 1) * 100 + j])
                  Escreval("Origem: ", destinoOrigem[(i - 1) * 100 + j])
                  Escreval("Destino: ", destinoDestino[(i - 1) * 100 + j])
                  Escreval("Quantidade de Pessoas: ", destinoQtdPessoas[(i - 1) * 100 + j])
                  Escreval("Quantidade de Quartos: ", destinoQtdQuartos[(i - 1) * 100 + j])
                  Escreval("Companhia Aérea: ", destinoCompanhiaAerea[(i - 1) * 100 + j])
               FimPara
            FimPara
            Escreval("-----------------------------")
            Escreval("Pressione ENTER para continuar...")
            Leia(opcao)
         Caso "0"
            Escreval("Saindo...")
      FimEscolha
   FimEnquanto
FimAlgoritmo