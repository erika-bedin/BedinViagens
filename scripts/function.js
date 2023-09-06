document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
      var heroTitle = document.querySelector(".hero-title");
      heroTitle.classList.remove("hidden");
      heroTitle.classList.add("zoom-in");
    }, 500); // 500ms após o carregamento da página  
    setTimeout(function() {
      var carousel = document.querySelector("#carouselhome");
      carousel.classList.remove("hidden");
      carousel.classList.add("zoom-in");
    }, 3000); // 3000ms após o carregamento da página
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
// Adiciona um ouvinte de evento para os botões
document.getElementById('btnClaro').addEventListener('click', function() {
  alternarTema('claro');
});
document.getElementById('btnEscuro').addEventListener('click', function() {
  alternarTema('escuro');
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
    // Adicionar classe para mostrar feedback visual do botão copiado
    event.target.classList.add('copied');      
    // Remover classe após 1 segundo
    setTimeout(() => {
      event.target.classList.remove('copied');
    }, 1000);
    }    