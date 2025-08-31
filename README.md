# 🌳 AreaVerde - API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

Este repositório contém o **backend** (API REST) da aplicação AreaVerde. Para o frontend (Interface Web) que consome esta API, acesse o repositório: **[areaverde-frontend](https://github.com/DougBrando/areaverde-frontend)**.

## 📖 Sobre o Projeto

Esta API foi construída com **Java e Spring Boot** para servir como o cérebro da aplicação AreaVerde. Ela é responsável por toda a lógica de negócio: gerenciar, cadastrar, listar e persistir os dados das áreas verdes.

### A Evolução de uma Ideia

Este projeto nasceu como uma aplicação de console em Java, desenvolvida para um trabalho de faculdade com foco em conceitos de Programação Orientada a Objetos, acesse o repositorio **[AreaVerde (Java)](https://github.com/DougBrando/AreaVerde.git). A motivação foi evoluir essa base de código para uma solução web moderna, transformando a lógica original em uma API RESTful completa, capaz de se comunicar com qualquer cliente web.

A API não utiliza um banco de dados tradicional. Em vez disso, ela faz a **persistência dos dados em um arquivo JSON local** (`dados_areas_verdes.json`), demonstrando uma solução de armazenamento simples e eficaz para projetos de pequeno e médio porte.

## ✨ Tecnologias Utilizadas

* **Java 17**: Linguagem principal da aplicação.
* **Spring Boot 3**: Framework para a criação da API, com as dependências:
    * **Spring Web**: Para criar os endpoints RESTful e o servidor web embarcado (Tomcat).
    * **Spring Boot DevTools**: Para live reload durante o desenvolvimento.
* **Maven**: Gerenciador de dependências e build do projeto.
* **Jackson**: Biblioteca para manipulação (serialização e desserialização) de JSON, integrada ao Spring.
* **Lombok**: Para reduzir código boilerplate (getters, setters, construtores).

## 📂 Estrutura do Projeto

A API foi organizada para separar as responsabilidades de cada camada da aplicação:

```
src/main/java/br/com/areaverde/api/
├── controllers/    # Camada que expõe os endpoints HTTP e recebe as requisições.
├── models/         # Classes que representam as entidades de negócio (AreaVerde, etc.).
├── repositories/   # Camada responsável pela persistência e acesso aos dados.
└── services/       # (Opcional) Camada para a lógica de negócio mais complexa.
└── ApiApplication.java # Ponto de entrada da aplicação Spring Boot.

# Na raiz do projeto, após a primeira execução:
dados_areas_verdes.json # Arquivo que funciona como "banco de dados".
```

## Endpoints da API

A API expõe os seguintes endpoints para manipulação dos dados de áreas verdes:

| Método HTTP | Endpoint           | Descrição                                         |
| :---------- | :----------------- | :------------------------------------------------ |
| `GET`       | `/areasverdes`     | Retorna uma lista com todas as áreas verdes cadastradas. |
| `GET`       | `/areasverdes/{id}`| Retorna os detalhes de uma área verde específica pelo seu ID. |
| `POST`      | `/areasverdes`     | Cadastra uma nova área verde.                          |

---

### Exemplo de Uso: `POST /areasverdes`

Para cadastrar uma nova área, envie uma requisição `POST` com o seguinte formato no corpo (body) da requisição:

**Request Body (JSON):**
```json
{
    "nome": "Parque Malwee",
    "localizacao": {
        "latitude": -26.4764,
        "longitude": -49.0987
    },
    "tipoVegetacao": "Nativa, Lagos e Jardins",
    "horariosFuncionamento": "07:30-19:00",
    "atividadesDisponiveis": [
        "Caminhada",
        "Restaurantes",
        "Eventos"
    ]
}
```

**Response (Sucesso - `201 Created`):**
```json
{
    "id": 1,
    "nome": "Parque Malwee",
    "localizacao": {
        "id": 1,
        "latitude": -26.4764,
        "longitude": -49.0987,
        "idAreaVerde": 1
    },
    "tipoVegetacao": "Nativa, Lagos e Jardins",
    "horariosFuncionamento": "07:30-19:00",
    "atividadesDisponiveis": [
        "Caminhada",
        "Restaurantes",
        "Eventos"
    ],
    "avaliacoes": [],
    "mediaAvaliacoes": 0.0
}
```

## 🚀 Como Executar a API

**Pré-requisitos:**
* Java (JDK 17 ou superior)
* Apache Maven

### Passos para Execução:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/DougBrando/areaverde-api.git
    ```

2.  **Navegue até a pasta do projeto:**
    ```bash
    cd areaverde-api
    ```

3.  **Execute a aplicação usando o Maven Wrapper:**
    *No Windows (cmd ou powershell):*
    ```bash
    ./mvnw spring-boot:run
    ```
    *No Linux ou macOS:*
    ```bash
    ./mvnw spring-boot:run
    ```

✅ A API estará em execução e pronta para receber requisições na porta `http://localhost:8080`.

## 👨‍💻 Autor

**Douglas Graça**

* GitHub: [@DougBrando](https://github.com/DougBrando)
* LinkedIn: [Douglas Graça](https://www.linkedin.com/in/douglas-graca/)
