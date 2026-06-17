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

## Passo 4 - MainActivity com CameraX

**O que foi feito:**
- Solicitacao de permissao em runtime com `ActivityResultLauncher`.
- Inicializacao do `ProcessCameraProvider`.
- Implementacao dos use cases:
  - `Preview` para video ao vivo.
  - `ImageCapture` para tirar foto.
- Troca de camera (frontal/traseira) com rebind dos use cases.
- Salvamento da foto em `getFilesDir()/photos`.
- Atualizacao da miniatura apos captura.
- Clique na miniatura abrindo a `PhotoPreviewActivity`.

**Para que serve:**
- Demonstrar o fluxo real de CameraX do inicio ao fim, com responsabilidade separada por metodo.

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

