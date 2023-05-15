# api-spring-boot-user-crud
Api desenvolvida para desafio interno.

Depois de baixar o projeto, abrir como projeto maven e esperar o maven baixar todas as dependencias.

Está configurado para rodar na porta 8084.
Unico endPoint do desafio: http://localhost:8084/usuarios
Exemplo de body:
```
{   
    "nome": "Carlota Joaquina",
    "email": "carlota@gmail.com",
    "dataNascimento": "25/05/1990",
    "endereco": "Rua sao borja",
    "habilidades": ["Paciente","Curiosa","Estudiosa"]
}
```

Foi adicionado banco em memório H2 verificar a insersão dos dados:
http://localhost:8084/h2-console

![image](https://github.com/MarioLuiz/api-spring-boot-user-crud/assets/11471499/09ba7b65-a1c1-47a1-becf-e6a1a739b8bb)

Só clicar connect que podera acessar ele.
