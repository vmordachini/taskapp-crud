# TaskApp CRUD - App de Lista de Tarefas

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white) ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white) ![Room](https://img.shields.io/badge/Room-DB-orange?style=for-the-badge&logo=android) ![MVVM](https://img.shields.io/badge/Arquitetura-MVVM-blue?style=for-the-badge) ![Coroutines](https://img.shields.io/badge/Kotlin-Coroutines-blueviolet?style=for-the-badge&logo=kotlin)

Este projeto é um aplicativo de lista de tarefas (To-Do List) para Android, desenvolvido como resultado da atividade "CRUD: Room Persistence Library" do SENAI. O objetivo principal é demonstrar a implementação de um CRUD (Criar, Ler, Atualizar, Deletar) completo utilizando a biblioteca **Room** para persistência de dados local.

A aplicação permite ao usuário adicionar, visualizar, marcar como concluída e deletar tarefas, tudo com os dados sendo salvos diretamente no dispositivo.

---

## 🚀 Funcionalidades Principais

* **💾 Persistência de Dados com Room:** Todas as tarefas são salvas em um banco de dados SQLite local, gerenciado pela biblioteca Room. Os dados persistem mesmo se o aplicativo for fechado.
* **📝 Criar Tarefas:** O usuário pode inserir um título e uma descrição para adicionar novas tarefas à lista.
* **📋 Ler (Listar) Tarefas:** As tarefas salvas são exibidas em uma lista (`RecyclerView`) na tela principal.
* **🔄 Atualizar Tarefas (Marcar como Concluída):** O usuário pode tocar em uma tarefa para marcá-la como "concluída" (ou "não concluída"), atualizando seu estado no banco de dados.
* **🗑️ Deletar Tarefas:** O usuário pode remover tarefas da lista e do banco de dados.
* **Atualização em Tempo Real (Reativa):** A interface é atualizada automaticamente sempre que há uma mudança no banco de dados, graças ao uso de **Kotlin Flow**.

---

## 🛠️ Arquitetura e Conceitos Aplicados

Este projeto segue as diretrizes da atividade do SENAI, implementando uma arquitetura **MVVM (Model-View-ViewModel)** e conceitos modernos de desenvolvimento Android.

* **Model (Camada de Dados):**
    * **Room (Entity):** A classe `TaskEntity` define a tabela do banco de dados.
    * **Room (DAO):** A interface `TaskDao` define os métodos de acesso ao banco (CRUD).
    * **Room (Database):** A classe `TaskDatabase` configura e inicializa o banco de dados.
    * **Repository:** A classe `TaskRepository` isola a origem dos dados (o DAO) do resto do aplicativo, seguindo o Padrão de Repositório.

* **ViewModel:**
    * A classe `TaskViewModel` atua como intermediário entre o Repositório e a View (Activity). Ela gerencia a lógica de negócios e expõe os dados para a UI, sobrevivendo a mudanças de configuração.

* **View:**
    * A `MainActivity` é responsável apenas por exibir os dados (observando o ViewModel) e enviar eventos de usuário (como cliques) para o ViewModel.
    * **RecyclerView:** Utilizado para exibir a lista de tarefas de forma eficiente, usando um `TaskAdapter`.

* **Assincronismo:**
    * **Kotlin Coroutines:** Todas as operações de banco de dados (inserir, atualizar, deletar) são executadas de forma assíncrona fora da thread principal usando `suspend fun` e `viewModelScope`, garantindo que a UI nunca seja bloqueada.
    * **Kotlin Flow:** A consulta de *leitura* (`getAll`) retorna um `Flow`, permitindo que a `MainActivity` observe as mudanças nos dados e atualize a lista reativamente.

---

## 🏃‍♀️ Como Rodar a Aplicação

1.  **Pré-requisitos:**
    * [Android Studio](https://developer.android.com/studio) (versão Hedgehog ou mais recente).
    * Emulador Android ou dispositivo físico.

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/vmordachini/taskapp-crud.git](https://github.com/vmordachini/taskapp-crud.git)
    cd taskapp-crud
    ```

3.  **Abra no Android Studio:**
    * No Android Studio, selecione "Open" (Abrir).
    * Navegue até a pasta `taskapp-crud` e selecione-a.
    * Aguarde o Gradle sincronizar e construir o projeto.

4.  **Execute:**
    * Clique no botão "Run 'app'" (ícone de play verde) com um emulador ou dispositivo selecionado.
