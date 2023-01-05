# Rent A Car
Sistema para Controle para uma locadora de carros


API REST desenvolvida com a finalidade de registro de carros, clientes e locações.
#
[Clique aqui para a documentação no Postman](https://documenter.getpostman.com/view/21616155/2s8Z72WCNG)
#
Utilizando essa API é possível:

1. Criação de ordem de locação;

2. Registro de contas de clientes;

3. Cadastro de carros da locadora;

4. Registro de carros disponíveis para locação e carros em uso.

#
Os seguintes endpoints estão implementados:

- `GET /carros/{id}` obtém os detalhes de um carro
- `GET /carros` obtém todos os carros cadastrados
- `POST /carros` adiciona um novo carro
- `PUT /carros/{id}` atualiza um carro
- `DELETE /carros/{id}` deleta um carro
- `GET /locacoes/{id}` obtém os detalhes de uma locação
- `GET /locacoes` obtém todas as locações
- `POST /locacoes` adiciona uma nova locação
- `PUT /locacoes/{id}` atualiza uma locação
- `DELETE /locacoes/{id}` deleta uma locação
- `GET /clientes/{id}` obtém os detalhes de um cliente
- `GET /clientes` obtém todos os clientes cadastrados
- `POST /clientes` adiciona um novo cliente
- `PUT /clientes/{id}` atualiza um cliente
- `DELETE /clientes/{id}` deleta um cliente
#
### Features a serem implementadas e bugs a serem corrigidos:
- Cobertura de testes