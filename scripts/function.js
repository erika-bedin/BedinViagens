document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
      var heroTitle = document.querySelector(".hero-title");
      heroTitle.classList.remove("hidden");
      heroTitle.classList.add("zoom-in");
    }, 300); // 300ms após o carregamento da página  
    setTimeout(function() {
      var carousel = document.querySelector("#carouselhome");
      carousel.classList.remove("hidden");
      carousel.classList.add("zoom-in");
    }, 1000); // 1000ms após o carregamento da página
  });

document.addEventListener("DOMContentLoaded", function() {
});
// Função para alternar o tema
function alternarTema(tema) {
  const body = document.body;
  const navbar = document.querySelector('.navbar');
  const footer = document.querySelector('.footer');


  if (tema === 'claro') {
    body.classList.remove('theme-dark'); // Remover a classe do tema dark do body
    navbar.setAttribute('class', 'navbar navbar-expand-lg navbar-light bg-light'); // Altera a classe da navegação para o tema claro
    footer.classList.remove('footer-dark'); // Remove a classe escura do footer
    footer.classList.remove('bg-dark'); // Remover a classe de background dark do footer
    footer.classList.remove('text-white'); // Remover a classe de cor branca da fonte
    footer.style.backgroundColor = 'white'; // Define o background do footer como branco
    footer.style.color = 'black'; // Define a cor da fonte do footer como preta
  } else {
    body.classList.add('theme-dark'); // Adiciona a classe do tema dark ao body
    navbar.setAttribute('class', 'navbar navbar-expand-lg navbar-dark bg-dark'); // Altera a classe da navegação para o tema escuro
    footer.classList.add('footer-dark'); // Adiciona a classe de tema escuro ao footer
    footer.classList.add('bg-dark'); // Adiciona a classe de background dark ao footer
    footer.classList.add('text-white'); // Adiciona a classe de cor branca da fonte
    footer.style.backgroundColor = ''; // Remove o background do footer (para herdar o do tema claro definido no CSS)
    footer.style.color = ''; // Remove a cor da fonte do footer (para herdar a do tema claro definido no CSS)
  }
} 

// Função para inicializar o tema com base no armazenamento local
function inicializarTema() {
  const temaSalvo = localStorage.getItem('tema');
  if (temaSalvo) {
    alternarTema(temaSalvo);
  }
}
// Adicione um ouvinte de evento para carregar o tema ao carregar a página
document.addEventListener('DOMContentLoaded', inicializarTema);

// Adicione ouvintes de evento para os botões de light mode e dark mode
document.addEventListener('DOMContentLoaded', function() {
  // Ouvinte de evento para o botão de light mode
  document.getElementById('btnClaro').addEventListener('click', function() {
    alternarTema('claro');
  });
  // Ouvinte de evento para o botão de dark mode
  document.getElementById('btnEscuro').addEventListener('click', function() {
    alternarTema('escuro');
  });
});

// Função de animação dos itens
document.addEventListener("DOMContentLoaded", function() {
  var main = document.querySelector("main"); // Selecionar o elemento main corretamente
  main.classList.remove("hidden"); // Remover a classe 'hidden' para exibir o main
  main.classList.add("zoom-in"); // Aplicar a animação após o main ser exibido
});

// Botão topo da página
document.getElementById('botaoTopo').addEventListener('click', function() {
  // Código para rolar a página para o topo
  window.scrollTo(0, 0);
});

// Função para copiar o conteúdo do cupom para a área de transferência
function copyToClipboard(event) {
  const couponText = event.target.textContent;
  const tempInput = document.createElement('textarea');
  tempInput.style.position = 'fixed';
  tempInput.style.opacity = 0;
  tempInput.value = couponText;
  document.body.appendChild(tempInput);
  tempInput.select();
  document.execCommand('copy');
  document.body.removeChild(tempInput);
  // Exibir a mensagem de aviso
  const copyConfirmation = document.querySelector('.copy-confirmation');
  copyConfirmation.classList.remove('hidden');
  
  // Remover classe após 1 segundo
  setTimeout(() => {
    event.target.classList.remove('copied');
  }, 1000);
}