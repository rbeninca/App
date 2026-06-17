# CameraX Didatico (passo a passo)

Este projeto foi organizado para ensinar CameraX em uma sequencia clara: permissao -> provider -> use cases -> captura -> navegacao para preview.

## Planejamento

- [x] Passo 1 - Preparar dependencias do CameraX.
- [x] Passo 2 - Declarar permissao e ajustar o `AndroidManifest.xml`.
- [x] Passo 3 - Criar layout da tela principal com `PreviewView` + controles.
- [x] Passo 4 - Implementar `MainActivity` com fluxo CameraX completo.
- [x] Passo 5 - Criar activity de visualizacao da foto.
- [x] Passo 6 - Validar build e registrar historico com commit.

## Passo 1 - Dependencias

**O que foi feito:** adicionadas as bibliotecas `camera-core`, `camera-camera2`, `camera-lifecycle` e `camera-view` no modulo `app`.

**Para que serve:**
- `camera-core`: APIs base de camera.
- `camera-camera2`: implementacao do backend Camera2.
- `camera-lifecycle`: integra CameraX ao ciclo de vida da Activity.
- `camera-view`: inclui o `PreviewView` para mostrar video ao vivo.

Arquivo alterado:
- `app/build.gradle.kts`

## Passo 2 - Permissoes e Manifest

**O que foi feito:**
- Declarada permissao `android.permission.CAMERA`.
- Registrada a nova activity `PhotoPreviewActivity`.

**Para que serve:**
- Sem permissao de camera, a imagem ao vivo nao pode iniciar.
- A segunda activity exibe a foto em tamanho maior ao clicar na miniatura.

Arquivo alterado:
- `app/src/main/AndroidManifest.xml`

## Passo 3 - Layout da tela principal

**O que foi feito:**
- Substituida a tela antiga por:
  - `PreviewView` ocupando area principal.
  - Botao **Trocar** (frontal/traseira).
  - Botao **Tirar foto**.
  - `ImageView` pequeno para miniatura da ultima foto.

**Para que serve:**
- Materializar visualmente os controles centrais do exemplo didatico.

Arquivo alterado:
- `app/src/main/res/layout/activity_main.xml`

## Passo 4 - MainActivity com CameraX (quebrado em subpassos)

### Passo 4.1 - Preparar componentes da tela e executor

**O que implementar:**
- Referencias de `PreviewView`, botoes e `ImageView` de miniatura.
- `ExecutorService` para captura em background.

**Para que serve:**
- Garantir que UI e processamento de camera fiquem organizados desde o inicio.

### Passo 4.2 - Definir estado da camera e variaveis principais

**O que implementar:**
- `CameraSelector` inicial (traseira).
- Referencias de `ImageCapture`, `ProcessCameraProvider` e `lastPhotoFile`.

**Para que serve:**
- Centralizar o estado do fluxo CameraX e evitar logica espalhada.

### Passo 4.3 - Tratar permissao em runtime

**O que implementar:**
- `ActivityResultLauncher<String>` para permissao de camera.
- Metodo `checkCameraPermissionAndStart()`.

**Para que serve:**
- Ensinar o pre-requisito obrigatorio antes de abrir o preview.

### Passo 4.4 - Iniciar o provider da camera

**O que implementar:**
- Metodo `startCamera()` com `ProcessCameraProvider.getInstance(this)`.
- Listener no executor principal para recuperar o provider.

**Para que serve:**
- Mostrar onde CameraX conecta com o ciclo de vida da activity.

### Passo 4.5 - Bind dos use cases (Preview + ImageCapture)

**O que implementar:**
- Metodo `bindUseCases(ProcessCameraProvider cameraProvider)`.
- Criar `Preview`, definir `setSurfaceProvider(previewView.getSurfaceProvider())`.
- Criar `ImageCapture` e chamar `bindToLifecycle(...)`.

**Para que serve:**
- Explicar o coracao do CameraX: quais casos de uso estao ativos e como ficam ligados a tela.

### Passo 4.6 - Capturar foto e salvar arquivo local

**O que implementar:**
- Metodo `takePhoto()`.
- Criar pasta `getFilesDir()/photos`.
- Gerar nome com timestamp e usar `ImageCapture.OutputFileOptions`.
- No callback de sucesso: atualizar `lastPhotoFile`, miniatura e feedback.

**Para que serve:**
- Demonstrar captura real e persistencia simples sem permissao extra de armazenamento.

### Passo 4.7 - Trocar camera frontal/traseira

**O que implementar:**
- Metodo `switchCamera()` alternando `CameraSelector`.
- Rebind chamando `bindUseCases(...)` novamente.

**Para que serve:**
- Ensinar que trocar lente exige rebinding dos use cases.

### Passo 4.8 - Abrir foto pela miniatura

**O que implementar:**
- Metodo `openLastPhoto()` com validacao de arquivo.
- `Intent` para `PhotoPreviewActivity` com `Uri` da foto.

**Para que serve:**
- Fechar o fluxo completo: capturar -> mostrar miniatura -> navegar para visualizacao.

Arquivo alterado:
- `app/src/main/java/com/ifsc/contaclicks/MainActivity.java`

## Passo 5 - Activity de preview da foto

**O que foi feito:**
- Criada `PhotoPreviewActivity`.
- Criado layout dedicado com `ImageView` em tela cheia.
- Recebe `Uri` da foto via `Intent` e renderiza o arquivo.

**Para que serve:**
- Ensinar navegacao entre telas e exibicao da imagem capturada.

Arquivos criados:
- `app/src/main/java/com/ifsc/contaclicks/PhotoPreviewActivity.java`
- `app/src/main/res/layout/activity_photo_preview.xml`

## Passo 6 - Strings e finalizacao

**O que foi feito:**
- Atualizados textos de interface e mensagens de feedback (captura/permissao/erros).

**Para que serve:**
- Melhorar clareza para os alunos durante os testes do app.

Arquivo alterado:
- `app/src/main/res/values/strings.xml`

## Como executar

```bash
cd /home/rbeninca/StudioProjects/pdm
./gradlew :app:assembleDebug
```

Se preferir rodar testes locais (unitarios):

```bash
cd /home/rbeninca/StudioProjects/pdm
./gradlew :app:testDebugUnitTest
```

## Observacoes didaticas

- O exemplo salva imagens no armazenamento interno do app para evitar permissao adicional.
- Para galeria do sistema (`MediaStore`) e compartilhamento com outros apps, o proximo passo e ensinar `FileProvider` e/ou escrita no MediaStore.

