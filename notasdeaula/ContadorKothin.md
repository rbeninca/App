
# 📱 App Contador de Cliques (Android - Kotlin)

Este projeto foi desenvolvido com fins **educacionais**, como parte da disciplina de **Dispositivos Móveis**. Ele apresenta, de forma simples e gradual, a criação de um aplicativo Android em **Kotlin** que **conta cliques** na tela e **salva o valor mesmo após o app ser fechado ou o celular ser rotacionado**.

Cada etapa foi versionada com Git para facilitar o acompanhamento e o entendimento dos conceitos envolvidos.

---

## ✅ Funcionalidade

Ao abrir o app, um número aparece centralizado na tela.

- A cada clique sobre o número, ele aumenta em 1.
- Se o celular for rotacionado, o número continua do ponto em que estava.
- Se o app for fechado e aberto novamente, o número também continua do ponto anterior.

---

## 📖 Introdução

Este projeto é um exemplo simples de um aplicativo Android que conta cliques na tela. Ele foi desenvolvido para ensinar conceitos básicos de desenvolvimento Android com Kotlin, como o ciclo de vida da `Activity`, manipulação de componentes da interface do usuário e persistência de dados com `SharedPreferences`.

---

## 📚 Conteúdo

### 1. Associando o TextView no Kotlin

###### `activity_main.xml`
```xml
<TextView
    android:id="@+id/tvShowContador"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="0"
    android:textSize="48sp"
    android:layout_gravity="center"
    android:gravity="center"/>
```

###### `MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var tvShowContador: TextView
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvShowContador = findViewById(R.id.tvShowContador)
        tvShowContador.text = contador.toString()
    }
}
```

### 2. Incrementando com clique

```kotlin
tvShowContador.setOnClickListener {
    contador++
    tvShowContador.text = contador.toString()
}
```

### 3. Salvando o estado na rotação

```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt("contador", contador)
}

override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    contador = savedInstanceState.getInt("contador", 0)
    tvShowContador.text = contador.toString()
}
```

### 4. Persistência após fechamento com SharedPreferences

```kotlin
override fun onPause() {
    super.onPause()
    val prefs = getSharedPreferences("contadorPrefs", MODE_PRIVATE)
    prefs.edit().putInt("contador", contador).apply()
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    tvShowContador = findViewById(R.id.tvShowContador)

    val prefs = getSharedPreferences("contadorPrefs", MODE_PRIVATE)
    contador = prefs.getInt("contador", 0)
    tvShowContador.text = contador.toString()

    tvShowContador.setOnClickListener {
        contador++
        tvShowContador.text = contador.toString()
    }
}
```

---

## 📂 Estrutura do Código

```text
notasdeaula/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/exemplo/contador/
│           │       └── MainActivity.kt
│           ├── res/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   └── values/
│           │       └── strings.xml
```

---

## 🧠 Classes e Métodos Kotlin Utilizados

| Classe/Função              | Descrição                                     |
| -------------------------- | --------------------------------------------- |
| `AppCompatActivity`        | Activity base com suporte a recursos modernos |
| `TextView`                 | Componente para exibir texto                  |
| `findViewById()`           | Acesso a views do layout                      |
| `setOnClickListener`       | Define o que acontece ao clicar               |
| `SharedPreferences`        | Armazenamento persistente de chave-valor      |
| `apply()`                  | Salva valores de forma assíncrona             |
| `Bundle`                   | Objeto para salvar e restaurar estado         |
| `onSaveInstanceState()`    | Salva estado antes da destruição              |
| `onRestoreInstanceState()` | Restaura estado após recriação                |

---

## 💡 Atividades Sugeridas

* Adicione um botão para zerar o contador.
* Adicione um segundo botão para subtrair valores.
* Mostre um `Toast` quando o contador atingir múltiplos de 5.
* Substitua `SharedPreferences` por `DataStore` como desafio.

---

## 📚 Referências

* [Documentação Oficial do Android](https://developer.android.com/docs)
* [Guia Kotlin Android](https://developer.android.com/kotlin)
* [SharedPreferences (Android)](https://developer.android.com/reference/android/content/SharedPreferences)
* [Ciclo de Vida da Activity](https://developer.android.com/guide/components/activities/activity-lifecycle)


