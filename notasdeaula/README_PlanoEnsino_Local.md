| #  | Conteúdos                                     | Carga Horária Estimada | obs |
|----|----------------------------------------------|------------------------|----------------------------------|
| 1  | [Plano de Ensino e Introdução ao desenvolvimento móvel](#1-plano-de-ensino-e-introdução-ao-desenvolvimento-móvel)  | 4h | |
| 2  | Eventos na interface, Activitys                      | 4h | |
| 3  | Componentes de Interface e Layouts                   | 4h | |
| 4  | Layouts e View Groups                                | 4h | |
| 5  | Layouts e View Groups (SlideShow)                    | 4h | |
| 6  | Avaliação                                            | 4h | |
| 7  | Recursos de Listagens de dados                       | 4h | |
| 8  | Persistência de dados no Android                     | 4h | |
| 9  | Sensores                                             | 2h | |
|10  | Geolocalização e Mapas no Android                    | 4h | |
|11  | Multimídia e Câmera no Android                       | 4h | |





## 1. Plano de Ensino e Introdução ao desenvolvimento móvel
### Objetivos
* Apresentar o plano de ensino e os conteúdos que serão abordados ao longo do curso
* Introduzir o desenvolvimento de aplicativos móveis, focando no Android
* Compreender a história do Android e sua evolução
* Familiarizar-se com a arquitetura do Android e o ambiente de desenvolvimento Android Studio

### Conteúdo
* **Plano de Ensino**: Apresentação dos conteúdos, avaliação, recuperação e cronograma
* **História do Android**: Evolução do sistema operacional, principais versões e marcos
* **Arquitetura do Android**: Componentes principais, ciclo de vida das atividades e serviços
* **Ambiente de Desenvolvimento Android Studio**: Configuração, estrutura do projeto e principais
componentes
* **Internacionalização (i18n)**: Conceitos e práticas para tornar aplicativos acessíveis a diferentes idiomas e regiões    
### Aplicações
* **Hello World**: Criação do primeiro aplicativo simples que exibe "Hello World" na tela.
* **Hello World Internacionalizado**: Criação de um aplicativo que exibe "Hello World" em diferentes idiomas, utilizando recursos de internacionalização.
* **Contador de Cliques**: Aplicativo que conta cliques na tela e exibe o número centralizado.
* **Sorteio de Números Aleatórios**: Aplicativo que sorteia um número aleatório entre 0 e 100, exibindo o resultado na tela.

## 2. Eventos na interface, Activitys e seus métodos de callback
### Objetivos
* Compreender a classe Activity e seus métodos de callback
* Aprender a ouvir eventos de clique e executar trechos de código
* Explorar o ciclo de vida das atividades e como gerenciar estados  
* Iniciar novas atividades
### Conteúdo
* **Classe Activity**: Definição, ciclo de vida e métodos principais
* **Métodos de Callback**: onCreate, onStart, onResume, onPause, onStop, onDestroy
* **Captura de Eventos de Clique**: Uso de listeners para capturar cliques em botões e outros componentes
* **Associação de Funções**: Como associar eventos de clique a funções específicas  
### Aplicação
* **Ciclo de Vida da Activity**: Criação de uma aplicação que exibe o ciclo de vida da Activity em ação.
* **Sorteio de Números Aleatórios**: Implementação de um aplicativo que sorteia um número aleatório entre de um intervalo definido pelo usuário, utilizando eventos de clique para gerar novos números e exibi-los na tela.

## 3. Layouts e View Groups
### Objetivos
* Compreender a construção de layouts no Android
* Estudar os ViewGroups e seus componentes disponíveis na SDK
* Aprender a posicionar views no Android
### Conteúdo
* **Construção de Layouts**: Como criar layouts utilizando XML e programaticamente
* **ViewGroups**: Definição, tipos (LinearLayout, RelativeLayout, ConstraintLayout, etc.) e suas características
* **Componentes Disponíveis na SDK**: Botões, TextViews, EditTexts, ImageViews, etc.
* **Posicionamento de Views**: Como organizar views dentro de ViewGroups e definir suas propriedades
### Aplicação
* **Exemplo com Diferentes ViewGroups**: Criação de uma aplicação que utiliza diferentes ViewGroups para organizar a interface do usuário.
* **Lista de Atividade de Fixação**: Desenvolvimento de uma lista de atividades que permite ao usuário adicionar, remover e visualizar itens, utilizando ListView


## 4. Layouts e View Groups (SlideShow)
### Objetivos
* Desenvolver uma aplicação SlideShow com imagens fixas armazenadas nos recursos do sistema
* Compreender o uso de ViewPager e Fragmentos para criar uma interface de slideshow
### Conteúdo
* **ViewPager**: Definição, uso e como implementar um slideshow
* **Fragmentos**: Como criar e gerenciar fragmentos para exibir diferentes imagens no slideshow
* **Recursos do Sistema**: Como armazenar e acessar imagens nos recursos do Android
### Aplicação
* **Desenvolvimento de Aplicação SlideShow**: Criação de uma aplicação que ex       
ibe um slideshow de imagens armazenadas nos recursos do sistema, utilizando ViewPager e Fragmentos. 

## 5. Aula para apresentação de atividades



## 6. Recursos de Listagens de dados
### Objetivos
* Estudar serviços como PackageManager, AudioManager, SensorManager e suas listagens
* Aprender a usar ListView para exibir listas no Android
### Conteúdo
* **Serviços do Android**: O que são, como funcionam e exemplos de serviços
* **PackageManager**: Listagem de aplicativos instalados, informações sobre pacotes
* **AudioManager**: Listagem de dispositivos de áudio e suas propriedades
* **SensorManager**: Listagem de sensores disponíveis no dispositivo
* **ListView**: Como criar e utilizar ListView para exibir listas de dados
### Aplicação
* **Launcher que utiliza listagem do sistema**: Desenvolvimento de um launcher personalizado que exibe uma lista de aplicativos instalados no dispositivo, utilizando PackageManager e ListView.    

## 7. Persistência de dados no Android
### Objetivos
* Estudar mecanismos de persistência de dados no Android
* Compreender o uso de SharedPreferences e SQLite para armazenar dados
### Conteúdo
* **SharedPreferences**: O que é, como funciona e como utilizá-lo para armazenar dados simples
* **SQLite**: O que é, como funciona e como criar um banco  de dados local
* **CRUD com SQLite**: Como realizar operações de criação, leitura, atualização e exclusão
* **Integração com a Interface do Usuário**: Como exibir dados persistidos na interface do usuário
### Aplicação
* **Sistema com banco de dados SQLite local**: Desenvolvimento de um aplicativo que utiliza SQLite para armazenar e gerenciar dados, permitindo ao usuário adicionar, editar e excluir informações. 
## 8. Sensores
### Objetivos
* Estudar o uso do SensorManager no Android
* Compreender como acessar e utilizar sensores disponíveis no dispositivo
### Conteúdo
* **SensorManager**: O que é, como funciona e como acessar sensores
* **Tipos de Sensores**: Acelerômetro, giroscópio, sensor de proximidade, etc.
* **Leitura de Dados dos Sensores**: Como ler e processar dados dos sensores em tempo real
* **Integração com a Interface do Usuário       

* Como exibir dados dos sensores na interface do usuário


## 9. Sensores no Android

Apps possiveis de serem desenvolvidos com os conteúdos abordados:
* **Contador de Cliques Persistente com Rotação**: Aplicativo que conta cliques na tela, persistindo o valor mesmo após a rotação do dispositivo.
* **Contador de Cliques Persistente com Fechamento**: Aplicativo que conta cliques na tela, persistindo o valor mesmo após o fechamento do aplicativo.
* **Sorteio de Números Aleatórios**: Aplicativo que sorteia número aleatório entre 0 e 100.
