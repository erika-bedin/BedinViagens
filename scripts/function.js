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

// Função para alternar o tema
function alternarTema(tema) {
  const body = document.body;
  const navbar = document.querySelector('.navbar');
  const footer = document.querySelector('.footer');

  if (!body) {
    console.error("Elemento 'body' não encontrado.");
    return;
  }
  
  // Salvar a preferência de tema no localStorage
  localStorage.setItem('tema', tema);

  if (tema === 'claro') {
    body.classList.remove('theme-dark');
  } else {
    body.classList.add('theme-dark');
  }

  if (navbar) {
    if (tema === 'claro') {
      navbar.setAttribute('class', 'navbar navbar-expand-lg navbar-light bg-light');
    } else {
      navbar.setAttribute('class', 'navbar navbar-expand-lg navbar-dark bg-dark');
    }
  }

  if (footer) {
    if (tema === 'claro') {
      footer.classList.remove('footer-dark');
      footer.classList.remove('bg-dark');
      footer.classList.remove('text-white');
      footer.style.backgroundColor = 'white';
      footer.style.color = 'black';
    } else {
      footer.classList.add('footer-dark');
      footer.classList.add('bg-dark');
      footer.classList.add('text-white');
      footer.style.backgroundColor = '';
      footer.style.color = '';
    }
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

// Funções da página pesquisa
function toggleHotelFields() {
  const hotelFields = document.getElementById("hotelFields");
  const simularhotelCheckbox = document.getElementById("simularhotelCheckbox");
  
  if (simularhotelCheckbox.checked) {
    hotelFields.style.display = "block";
  } else {
    hotelFields.style.display = "none";
  }
}

function togglePassagemFields() {
  const passagemFields = document.getElementById("passagemFields");
  const simularpassagemCheckbox = document.getElementById("simularpassagemCheckbox");
  
  if (simularpassagemCheckbox.checked) {
    passagemFields.style.display = "block";
  } else {
    passagemFields.style.display = "none";
  }
}

function toggleClienteFields() {
  const clienteFields = document.getElementById("clienteFields");
  const clienteexistenteCheckbox = document.getElementById("clienteexistenteCheckbox");
  
  if (clienteexistenteCheckbox.checked) {
    clienteFields.style.display = "block";
  } else {
    clienteFields.style.display = "none";
  }
}