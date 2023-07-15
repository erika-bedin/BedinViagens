Algoritmo "AgenciaViagensErika"
// Atividade Pratica do Modulo 2
// Autora  : Erika Pena Bedin Matias
// Data atual  : 13/07/2023

CONST
    maxcont = 1

var
   clientes: vetor [1..maxcont] de caractere
   destinos: vetor [1..maxcont] de caractere
   opcao: inteiro
   quantidadeClientes: inteiro
   quantidadeDestinos: inteiro

procedimento CadastrarCliente()
   se quantidadeClientes < 100 entao
      escreva("Digite o nome do cliente: ")
      leia(clientes[quantidadeClientes + 1])
      quantidadeClientes <- quantidadeClientes + 1
   senao
      escreva("Limite de clientes atingido!")
   fimse

procedimento CadastrarDestino()
   se quantidadeDestinos < 100 entao
      escreva("Digite o nome do destino: ")
      leia(destinos[quantidadeDestinos + 1])
      quantidadeDestinos <- quantidadeDestinos + 1
   senao
      escreva("Limite de destinos atingido!")
   fimse

procedimento ConsultarClientes()
   se quantidadeClientes = 0 entao
      escreva("Nenhum cliente cadastrado.")
   senao
      para i de 1 ate quantidadeClientes faca
         escreva("Cliente ", i, ": ", clientes[i])
      fimpara
   fimse

procedimento ConsultarDestinos()
   se quantidadeDestinos = 0 entao
      escreva("Nenhum destino cadastrado.")
   senao
      para i de 1 ate quantidadeDestinos faca
         escreva("Destino ", i, ": ", destinos[i])
      fimpara
   fimse

funcao MenuPrincipal(): inteiro
   escreva("---- Menu Principal ----")
   escreva("1. Cadastrar cliente")
   escreva("2. Cadastrar destino")
   escreva("3. Consultar clientes")
   escreva("4. Consultar destinos")
   escreva("5. Sair")
   escreva("------------------------")
   escreva("Digite sua opcao: ")
   leia(opcao)
   retorne opcao

quantidadeClientes <- 0
quantidadeDestinos <- 0

repita
   opcao <- MenuPrincipal()

   escolha opcao
      caso 1
         CadastrarCliente()
      caso 2
         CadastrarDestino()
      caso 3
         ConsultarClientes()
      caso 4
         ConsultarDestinos()
      caso 5
         escreva("Encerrando o programa...")
      outrocaso
         escreva("Opcao invalida!")
   fimescolha

ate opcao = 5
FimAlgoritmo