function configurarHeader() {
  const header = document.createElement('header');
  header.className = 'header header-dark bg-dark text-white text-center';

  const headerContent = `
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">    
      <div class="container">
          <img src="./img/logolimpa.png" alt="Logotipo da agência de viagens" width="100">
          <img src="./img/bedinviagens.png" alt="Logotipo da Bedin viagens" width="300">
        <a class="navbar-brand" href="/index.html"></a>          
        <div class="btn-group mx-2" role="group" aria-label="Basic radio toggle button group">
          <input type="radio" class="btn-check" name="btnradio" id="btnClaro" autocomplete="off">
          <label class="btn btn-outline-secondary" for="btnClaro">🌞</label>
          <input type="radio" class="btn-check" name="btnradio" id="btnEscuro" autocomplete="off">
          <label class="btn btn-outline-secondary" for="btnEscuro">🌚</label>
        </div>         
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link" href="index.html">Início</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="destino.html">Destinos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="pesquisa.html">Pesquisar</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="promocoes.html">Promoções</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contato.html">Contato</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  `;
  
  header.innerHTML = headerContent;

  document.body.appendChild(header);
}

function configurarHeaderEFooter() {
// Destacar o item do navbar ativo na página atual
const paginaAtual = document.body.id;
const linksNavbar = document.querySelectorAll('.nav-link');

linksNavbar.forEach(link => {
  if (link.getAttribute('href') === paginaAtual + '.html') {
    link.classList.add('active');
  } else {
    link.classList.remove('active');
  }
});
}

function configurarFooter() {
    const footer = document.createElement('footer');
    footer.className = 'footer footer-dark bg-dark text-white text-center';
  
    const footerContent = `
      Bedin Viagens© 2023. Desenvolvido por 🌺 <a href="https://github.com/erika-bedin">Érika Bedin</a> 👩🏼‍💻. <br> Todos os direitos reservados (fins educacionais).
    `;
  
    footer.innerHTML = footerContent;
  
    document.body.appendChild(footer);
  }