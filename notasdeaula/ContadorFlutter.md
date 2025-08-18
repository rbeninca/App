# 📱 App Contador de Cliques (Flutter - Dart)

Este projeto foi desenvolvido com fins **educacionais**, como parte da disciplina de **Dispositivos Móveis**. Ele apresenta, de forma simples e gradual, a criação de um aplicativo Flutter que **conta cliques** na tela e **salva o valor mesmo após o app ser fechado**.

Cada etapa pode ser acompanhada por meio de versionamento com Git, facilitando a compreensão incremental dos conceitos.

---

## ✅ Funcionalidade

Ao abrir o app, um número aparece centralizado na tela.

* A cada clique sobre o número, ele aumenta em 1.
* O valor persiste mesmo após o fechamento e reabertura do app (usando `SharedPreferences`).

---

## 📖 Introdução

Este projeto é um exemplo introdutório ao desenvolvimento de aplicativos com **Flutter** e **Dart**. Ele ensina conceitos essenciais como:

* Estrutura de widgets (`StatelessWidget` e `StatefulWidget`);
* Manipulação de eventos de toque;
* Gerenciamento de estado com `setState()`;
* Persistência de dados local com o pacote `shared_preferences`.

O aplicativo exibe um número centralizado na tela, que é incrementado a cada toque do usuário. O valor é mantido mesmo após fechar o app, utilizando armazenamento local.

---

## 📚 Conteúdo

### 1. Criando o projeto e adicionando dependência

Abra um terminal e execute os seguintes comandos:

```bash
flutter create contador_cliques_flutter
cd contador_cliques_flutter
flutter pub add shared_preferences
```

O pacote `shared_preferences` será utilizado para salvar o valor do contador localmente.

---

### 2. Estrutura inicial do app em Flutter

Todo aplicativo Flutter começa com a função `main()`, que chama `runApp()` e carrega um widget raiz. Aqui usamos o `MaterialApp`, que fornece temas e componentes visuais do Material Design.

```dart
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Contador de Cliques',
      home: ContadorPage(),
    );
  }
}
```

---

### 3. Criando um `StatefulWidget` para o contador

Como o valor do contador muda com o tempo, utilizamos `StatefulWidget`.

```dart
class ContadorPage extends StatefulWidget {
  @override
  _ContadorPageState createState() => _ContadorPageState();
}
```

A classe `_ContadorPageState` gerencia o estado da interface e guarda a variável `contador`.

---

### 4. Inicializando o valor salvo com `SharedPreferences`

O método `initState()` é chamado automaticamente ao criar a tela. Usamos ele para carregar o valor salvo anteriormente:

```dart
int _contador = 0;

@override
void initState() {
  super.initState();
  _carregarContador();
}

void _carregarContador() async {
  final prefs = await SharedPreferences.getInstance();
  setState(() {
    _contador = prefs.getInt('contador') ?? 0;
  });
}
```

---

### 5. Atualizando o contador ao clicar

Usamos o método `setState()` para atualizar a interface sempre que o valor mudar. O novo valor também é salvo nas preferências.

```dart
void _incrementar() async {
  final prefs = await SharedPreferences.getInstance();
  setState(() {
    _contador++;
    prefs.setInt('contador', _contador);
  });
}
```

---

### 6. Interface visual (UI) com `GestureDetector`

A interface exibe o valor do contador. Para captar toques, usamos `GestureDetector`, que permite adicionar ações a qualquer widget:

```dart
@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(title: Text('Contador de Cliques')),
    body: Center(
      child: GestureDetector(
        onTap: _incrementar,
        child: Text(
          '$_contador',
          style: TextStyle(fontSize: 64),
        ),
      ),
    ),
  );
}
```

---

### 7. Código completo (`main.dart`)

```dart
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Contador de Cliques',
      home: ContadorPage(),
    );
  }
}

class ContadorPage extends StatefulWidget {
  @override
  _ContadorPageState createState() => _ContadorPageState();
}

class _ContadorPageState extends State<ContadorPage> {
  int _contador = 0;

  @override
  void initState() {
    super.initState();
    _carregarContador();
  }

  void _carregarContador() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      _contador = prefs.getInt('contador') ?? 0;
    });
  }

  void _incrementar() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      _contador++;
      prefs.setInt('contador', _contador);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Contador de Cliques')),
      body: Center(
        child: GestureDetector(
          onTap: _incrementar,
          child: Text(
            '$_contador',
            style: TextStyle(fontSize: 64),
          ),
        ),
      ),
    );
  }
}
```

---

## 📂 Estrutura do Código

```text
contador_cliques_flutter/
├── lib/
│   └── main.dart
├── pubspec.yaml
└── ... (arquivos padrão do Flutter)
```

---

## 📌 Conceitos Aplicados

| Conceito             | Descrição                           |
| -------------------- | ----------------------------------- |
| `StatefulWidget`     | Componente com estado mutável       |
| `initState()`        | Método de inicialização de estado   |
| `setState()`         | Atualiza a interface com novo valor |
| `GestureDetector`    | Captura toques do usuário           |
| `shared_preferences` | Salva dados simples localmente      |

---

## 💡 Atividades Sugeridas

* Adicione um botão para **zerar** o contador.
* Adicione outro botão para **subtrair 1**.
* Exiba um `SnackBar` quando o contador atingir múltiplos de 5 ou 10.
* Refatore o projeto usando `Provider` ou `Riverpod` como desafio.

---

## 📚 Referências

* [Documentação do Flutter](https://docs.flutter.dev)
* [Pacote shared\_preferences](https://pub.dev/packages/shared_preferences)
* [Widgets Statefull no Flutter](https://api.flutter.dev/flutter/widgets/StatefulWidget-class.html)
* [Guia de Introdução ao Flutter](https://flutter.dev/docs/get-started/flutter-for/android-devs)