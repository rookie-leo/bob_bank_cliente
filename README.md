# Serviço: Cliente

## Funções do Serviço
O serviço Cliente, será responsável por fazer o cadastro, login e atualização de dados do cliente.
O sistema irá utilizar JWT para login do cliente, onde o token gerado, será trafegado via sessão para os demais serviços, 
onde serão validados alguns dados como id do cliente, e data e hora da geração do token.

## Configuração do projeto
Para o cliente, será utilizado as seguintes dependências:
- Spring Web
- Spring Data JPA
- Spring MySQL Driver
