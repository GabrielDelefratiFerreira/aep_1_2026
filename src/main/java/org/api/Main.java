// foi especificado pra nao utilizar de frameworks, o proprio java contem 
// uma biblioteca http que permite a abertura de uma porta para a aplicacao,
// a base escolhida pra iniciar as rotas segue os padroes da biblioteca httpserver
// esse por sua vez construido em cima da classe ApiServer

package org.api;

import org.api.core.ApiServer;
import org.api.routes.solicitacao.SolicitacaoController;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiServer server = new ApiServer();
        new SolicitacaoController(server);
        new UsuarioController(server);
        server.run();
    }
}
