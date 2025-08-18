# 🔍 Comparativo entre Desenvolvimento Android com Flutter (Dart) e Java/Kotlin

Este documento apresenta um comparativo didático entre o desenvolvimento de aplicativos Android usando **Flutter com Dart** e a abordagem tradicional usando **Java ou Kotlin com Android SDK**. O foco é oferecer uma visão clara das principais diferenças estruturais, conceituais e práticas entre as abordagens.

---

## 🧱 Estrutura Geral do Projeto

| Aspecto             | Flutter (Dart)                    | Java/Kotlin (Android SDK)                |
| ------------------- | --------------------------------- | ---------------------------------------- |
| Linguagem principal | Dart                              | Java ou Kotlin                           |
| Ponto de entrada    | `main()` com `runApp()`           | `onCreate()` na `MainActivity`           |
| Interface visual    | Declarativa com widgets           | Imperativa com XML e `findViewById()`    |
| Atualização de UI   | `setState()`                      | `setText()` + `setOnClickListener()`     |
| Gerência de layout  | Tudo em código Dart com `Widgets` | Layout definido em XML, lógica no código |

---

## 🖼️ Interface Gráfica (UI)

| Flutter                                                                  | Java/Kotlin                                                                  |
| ------------------------------------------------------------------------ | ---------------------------------------------------------------------------- |
| Utiliza `Widgets` para compor a interface, como blocos Lego              | Utiliza arquivos XML para descrever a UI e `findViewById()` para manipulá-la |
| Estilo declarativo: descreve como a interface deve ser baseada no estado | Estilo imperativo: descreve passo a passo o que a interface deve fazer       |
| A UI é reconstruída com `setState()` automaticamente                     | Precisa atualizar manualmente cada componente visual                         |

---

## 🔄 Gerenciamento de Estado

| Flutter                                                | Java/Kotlin                                                                          |
| ------------------------------------------------------ | ------------------------------------------------------------------------------------ |
| `setState()` atualiza a tela automaticamente           | Precisa usar `setText()`, `notifyDataSetChanged()`, etc.                             |
| Pode escalar para `Provider`, `Riverpod`, `Bloc`, etc. | Depende de `ViewModel`, `LiveData`, ou frameworks como `Jetpack Compose` (em Kotlin) |

---

## 💾 Persistência de Dados Simples

| Flutter                                                      | Java/Kotlin                                         |
| ------------------------------------------------------------ | --------------------------------------------------- |
| Usa o pacote `shared_preferences`                            | Usa a classe `SharedPreferences`                    |
| A API é assíncrona (`await`)                                 | A API é síncrona (mas simples)                      |
| Preferências acessadas com `SharedPreferences.getInstance()` | Preferências acessadas com `getSharedPreferences()` |

---

## 🎯 Vantagens e Limitações

### ✅ Vantagens do Flutter

* Código único para Android e iOS.
* Recarga instantânea (hot reload).
* Interface unificada, sem XML separado.
* Comunidade em rápido crescimento.

### ✅ Vantagens do Java/Kotlin

* Integração completa com a plataforma Android.
* Acesso mais direto a recursos do sistema.
* Suporte maduro para bibliotecas Java.
* Base sólida para projetos com muitos recursos nativos.

---

## 📌 Exemplo Comparativo (Exibir Texto na Tela)

### Kotlin (Java semelhante)

```kotlin
val texto = findViewById<TextView>(R.id.textView)
texto.text = "Olá, mundo!"
```

### Flutter

```dart
Text("Olá, mundo!", style: TextStyle(fontSize: 24))
```

---

## 🎓 Considerações Finais

* Flutter é excelente para quem busca **desenvolvimento multiplataforma**, produtividade e consistência visual.
* Java/Kotlin ainda são muito relevantes, especialmente para quem quer **acesso completo ao ecossistema Android**.
* Para projetos educacionais, Flutter permite explorar conceitos de UI reativa, enquanto Java/Kotlin oferece visão mais próxima do funcionamento interno da plataforma Android.

Ambas as abordagens são válidas e, idealmente, o(a) desenvolvedor(a) deve conhecer as duas para escolher a melhor ferramenta conforme o projeto.

---

## 📚 Referências

* [Flutter.dev](https://flutter.dev)
* [Dart.dev](https://dart.dev)
* [Developer Android - Kotlin](https://developer.android.com/kotlin)
* [Developer Android - Java](https://developer.android.com/guide)
    