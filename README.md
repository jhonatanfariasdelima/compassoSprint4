Banco de dados: mariaDB - porta 3307


* Autenticação : 
  POST - http://localhost:8080/auth 
         {email: {email}, senha: {****}}
       

* Pedido :

  GET - http://localhost:8080/protected/pedido/all - lista todos os pedidos independente do status

  GET - http://localhost:8080/protected/pedido/ativos - lista todos os pedidos ativos

  GET - http://localhost:8080/protected/pedido/inativos - lista todos os pedidos inativos

  POST - http://localhost:8080/protected/pedido - cria um pedido com status ativo
          {"listaDeProdutos": {id prod.}}

  DELETE - http://localhost:8080/protected/pedido/{id} - inativa um determinado pedido

  PUT - http://localhost:8080/protected/pedido/{id} - atualiza os produtos de um determinado pedido 
          {"listaDeProdutos": {id prod.}}


* Produto :

  GET - http://localhost:8080/protected/produto - lista todos os produtos 
  
  POST - http://localhost:8080/protected/produto - cria um novo produto
             {"descricao": desc., "precoUnitario": R$}
  
  DELETE - http://localhost:8080/protected/produto/{id} - deleta um produto especifico
  
  PUT - http://localhost:8080/protected/produto/{id} - atualiza um produto especifico
           {"descricao": desc., "precoUnitario": R$}}


* Pessoa :

  GET - http://localhost:8080/pessoa - lista todas as pessoas

  GET - http://localhost:8080/pessoa/{id} - lista a pessoa com o id especificado 

  DELET - http://localhost:8080/pessoa/{id} - deleta a pessoa com o id especificado e seus endereços cadastrados
  
  PUT - http://localhost:8080/pessoa/{id} - atualiza uma pessoa especifica
  
  DELETE - http://localhost:8080/pessoa/{id} - deleta uma pessoa especifica
