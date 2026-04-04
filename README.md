# SOBRE

### SERVER

O sistema é contruido na main onde recebe a construção a partir dos controllers,
que por sua vez são injetados no executor(ApiServer). O ApiServer recebe as rotas de forma padrão,
cria a rota na biblioteca http
O ApiServer é responsável por unir e injetar instancias singleton

### ROTAS

Rotas que possuirem :id são relativas ao numero enviado, entao se eu criar uma rota solicitacao/by-id/:id e chamar .../by-id/1
o objeto a ser solicitado ou modificado sera o do id 1

### GUARD

O server ao criar as rotas, as associa a um validador de autenticacao CASO a construcao tenha recebido EnumAccessModifier.PRIVATE
isso só significa que a rota precisa de um Bearer na requisição pra ser aceita. Caso contrario a validação é pulada.

### HASH

o algoritmo escolhido foi só pra facilitar a sua contrução e simular segurança, em cenarios reais outros tipos de hash devem
ser aplicados, alem da localização hardcoded incorreta da chave.

### ApiController

classe a qual facilita a construção de controllers, possui métodos utilitarios e principalmente formas simples de criar as rotas.
responsáveis pela entrada do módulo, em aplicações reais a classe ApiModule é escencial pois visa injetar dependencias de forma
segura e eficaz, elem de isolar o modulo, suas importacoes e exportacoes, mas como é um software simples não foi necessário
