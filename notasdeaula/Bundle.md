Um bundle é um objeto que pode conter pares chave-valor, permitindo armazenar dados simples como inteiros, strings, booleanos, etc. No nosso caso, usamos o método `putInt()` para salvar o valor do contador no bundle com a chave "contador".
Exemplo:
```java
Bundle bundle = new Bundle();
bundle.putString("nome", "Romulo Beninca");
bundle.putInt("userId", 18);
bundle.putBoolean("isProfessor", true);
bundle.putStringArray("disciplinas", new String[]{"Web", "Mobile", "BD"});
```


| Chave (`key`)        | Valor (`value`)           | Tipo de dado              |
|----------------------|---------------------------|----------------------------|
| "nome"               | "Romulo Beninca"          | `String`                   |
| "userId"             | 42                        | `int`                      |
| "isProfessor"        | true                      | `boolean`                  |
| "disciplinas"        | ["Web", "Mobile", "BD"]   | `String[]` ou `ArrayList<String>` |


O `Bundle` é frequentemente usado para passar dados entre atividades ou fragmentos em uma aplicação Android. Ele é especialmente útil para manter o estado da interface do usuário durante mudanças de configuração, como rotações de tela.
O `Bundle` pode ser serializado e parcelado, o que significa que ele pode ser facilmente passado entre componentes da aplicação, como atividades e serviços. Isso é feito através dos métodos `putParcelable()` e `getParcelable()`, que permitem armazenar objetos complexos dentro do bundle, desde que esses objetos implementem a interface `Parcelable`.
O `Bundle` também é usado para armazenar dados temporários que não precisam ser persistidos em um banco de dados ou arquivo, mas que são necessários para a operação atual da aplicação. Por exemplo, ao iniciar uma nova atividade, você pode passar dados relevantes para essa atividade através de um `Bundle`, que pode ser recuperado na nova atividade usando o método `getIntent().getExtras()`.
O `Bundle` é uma parte fundamental do desenvolvimento Android, pois facilita a comunicação entre diferentes componentes da aplicação e ajuda a manter o estado da interface do usuário de forma eficiente. Ele é amplamente utilizado em diversas situações, como ao passar dados entre atividades, fragmentos ou serviços, e é uma ferramenta essencial para o gerenciamento de estado em aplicações Android.