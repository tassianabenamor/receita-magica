<p align="center">
<img width=200 src="https://user-images.githubusercontent.com/87051404/207320811-968baf4a-48d3-4a95-8864-8566abb6e334.png" /></br>
</p>

# :fork_and_knife: Receita Mágica - Aplicativo de receitas
Aplicativo de receitas desenvolvido para a disciplina de Projeto de Bloco: Desenvolvimento Android, Instituto Infnet (4T.2022).

**Motivo:** A motivação do aplicativo é facilitar a listagem de receitas do usuário.

<h3>Requisitos</h3>

Utilização de smartphone com sistema operacional Android para executar o aplicativo ou simulado com tal capacidade. 

Para baixar o aplicativo no seu dispositivo Android, acesse a [Play Store](https://play.google.com/store/apps/developer?id=Aluno+Infnet&hl=pt_PT&gl=US).

<h3>Funcionalidades</h3>

Assista aqui o [vídeo](https://www.youtube.com/watch?v=02YIhLrPiDk) demonstrando as funcionalidades do aplicativo.

- Cadastro e autenticação de usuário utilizando Firebase
- Consulta de receitas cadastradas
- Adicionar, deletar e editar receitas

## ✔️ Técnicas e tecnologias utilizadas

<h3>As técnicas utilizadas para isso são:</h3>

- `CardView`: container para apresentar cada produto na lista de receitas
- `RecyclerView`: listagem das receitas
- `ConstraintLayout`: ViewGroup padrão para implementar todos os layouts
- `ImageView`: View para apresentar imagens no App
- `View Binding`: busca de views do layout de forma segura
- `Toast`: Exibição de mensagem comunicando o sucesso da ação tomada pelo usuário
- `Personalização de tema`: modificação de cores para o tema do App
- `Firebase Authentication`: autenticação de usuário
- `AdMob`: monetização app para mobile
- `Retrofit`: consumo de API

<h3>Tecnologias utilizadas</h3>

A IDE de desenvolvimento utilizada foi o Android Studio e as linguagens utilizadas foram:

- `Kotlin`: Linguagem de programação
- `XML`: Linguagem de marcação

*Nenhum framework foi utilizado*

## :dart: Metodologia ágil - Scrum

A metodologia ágil utilizada foi a Scrum. Em alguns momentos também realizamos pair programming.

Segue abaixo o time de desenvolvimento, clique no nome para acessar o Linkedin:

- [Ciro Volpe](https://www.linkedin.com/in/cirodellavolpe/): Product Owner
- [Tassiana Benamor](https://www.linkedin.com/in/tassiana-benamor/): Desenvolvedora e Documentadora
- [Eduardo Macedo](https://www.linkedin.com/in/eduardo-mello-de-macedo-28ab8b198/): Desenvolvedor
- [Germano Nascimento](https://www.linkedin.com/in/germanonascimento/): Desenvolvedor e Documentador
- [Leticia Barbosa](https://www.linkedin.com/in/let%C3%ADcia-barbosaa/): Desenvolvedora

## :pencil: Product Backlog

Tarefas e etapas realizadas para o desenvolvimento do aplicativo.

### Estilização do aplicativo
- Colocar cores no app

### Lista de receitas - cadastrar, editar, visualizar e excluir
- Criar tela de lista de receitas
- Criar tela de cadastrar nova receita
- Adicionar um botão de cadastro de nova receita
- Permitir acesso a arquivos remotos
- Adicionar receitas criadas ao armazenamento remoto
- Adicionar listagem de receitas do armazenamento remoto
- Visualizar uma receita
- Editar uma receita
- Excluir uma receita

### Autenticação de usuário
- Criar tela, botão e funcionalidade de login
- Criar tela, botão e funcionalidade de cadastro
- Criar botão e funcionalidade de logout (sair)

### Banco de Dados
- Utilizar armazenamento remoto (Firebase)

### Monetização
- Adicionar banner a tela (monetização)

## :money_with_wings: Modelo de negócios

O modelo de negócios escolhido foi o Freemium. Para a implementação deste modelo de negócios será implementada a API de In-App Billing utilizando a Google Play Library. A escolha desse modelo foi feita a partir da principal premissa de deixar o app acessível para todos os públicos, dando uma experiência integral de nosso fluxo. Isso combina com a temática mindfull que permeia. Os ads serão implementados por meio de banners ao longo da rotina do usuário. **O modelo de cobrança para o aplicativo será por recorrência mensal e anual, para retirar banners e anúncios.**

## :computer: Telas

### Telas de login e cadastro de usuário

Autenticação de usuário utilizando e-mail e senha

![image](https://user-images.githubusercontent.com/87051404/207328295-5d420f60-743c-45fa-a11d-fb1e1ca87687.png)

### Telas de receitas e cadastro de novas receitas

![image](https://user-images.githubusercontent.com/87051404/207329745-3905cab2-8a33-43ac-9d1c-f134cb798ce4.png)

## :robot: Abrir e rodar o projeto

Após baixar o projeto, você pode abri-lo utilizando o Android Studio. Para isso, na tela de launcher, clique em:
- Open an Existing Project
- Encontre o local em que o projeto está e o selecione. Se o projeto estiver no formato zip, será necessário extraí-lo antes de realizar a busca pelo arquivo
- Por último, clique em OK

### Observação

O Android Studio deve executar algumas tasks do Gradle para configuração do projeto. 

Após as tasks finalizarem, o app Receita Mágica pode ser executado.
:)
<hr>

*Trabalho em grupo para a disciplina Projeto de Bloco: Desenvolvimento Android, Instituto Infnet (4T.2022).*
