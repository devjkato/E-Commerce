package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import e_commer.dominio.TrocaDevolucao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge
 */
public class TrocaDevolucaoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        TrocaDevolucao td = null;
        if (!operacao.equals("VISUALIZAR")) {
            Pedido ped;
            String cliId = request.getParameter("txtCliId");
            String pedId = request.getParameter("txtIdPed");
            String motivo = request.getParameter("txtMotivo");
            String status = request.getParameter("txtStatus");
            String anotacoes = request.getParameter("txtAnotacao");
            String qtdeDev = request.getParameter("txtQtdeDev");
            String proId = request.getParameter("txtIdpro");
            String acao = request.getParameter("txtAcao");
            String tdId = request.getParameter("txtIdtd");

            if (operacao.equals("ENVIAR") || operacao.equals("ALTERAR")) {
                //String[] ids = request.getParameterValues("txtIdPed");//recebe um ou varios pedidos de troc/dev

//                for (int i = 0; i < ids.length; i++) {
//                    td = new TrocaDevolucao();
//                    td.setId(Integer.parseInt(ids[i]));
//                    
//                }
                td = new TrocaDevolucao();
                ped = new Pedido();
                Produto prod = new Produto();
                prod.setId(Integer.parseInt(proId));
                
                ped.setId(Integer.parseInt(pedId));
                ped.setStatus(status);
                td.setPedido(ped);
                td.setProId(Integer.parseInt(proId));
                td.setMotivo(motivo);
                td.setQuantidade(Integer.parseInt(qtdeDev));
                td.setAnotacao(anotacoes);
                td.setAcao(acao);
                td.setStatus(status);
                if(tdId != null && !tdId.trim().equals("")){
                    td.setId(Integer.parseInt(tdId));
                }
            } else if (operacao.equals("CONSULTAR") || operacao.equals("CONSULTAR1")) {
                td = new TrocaDevolucao();
                Cliente cli = new Cliente();
                if (cliId != null && !cliId.trim().equals("")) {
                    cli.setId(Integer.parseInt(cliId));
                    ped = new Pedido();
                    ped.setCliente(cli);
                    td.setPedido(ped);
                }
            }
        } else {
            Resultado resultado = null;
            String txtId = request.getParameter("txtIdtd");

            int pedId = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                pedId = Integer.parseInt(txtId);
            }

            td = new TrocaDevolucao();
            td.setId(pedId);
            Fachada fachada = new Fachada();
            resultado = fachada.consultar((EntidadeDominio) td);

            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == pedId) {
                    td = (TrocaDevolucao) e;
                }
            }
        }
        return td;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;
        String operacao = request.getParameter("operacao");
        if (resultado.getMsg() == null && operacao.equals("ENVIAR")) {
            request.setAttribute("mensagem", "Seu pedido foi enviado!\nLogo estaremos entrando em contato!");
            d = request.getRequestDispatcher("minhaconta.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
            request.setAttribute("trocadevolucao", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqtrocadevolucao.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {
            if (resultado.getEntidades().size() == 0) {
                request.setAttribute("mensagem", "Não há trocas ou devoluções!");
                d = request.getRequestDispatcher("minhaconta.jsp");
            } else {
                request.setAttribute("trocadevolucao", resultado);
                d = request.getRequestDispatcher("consultaTrocaDevolucao.jsp");
            }
        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {
            request.setAttribute("trocadevolucao", resultado);
            d = request.getRequestDispatcher("LadoAdmin/detalhestrocadevolucao.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            request.setAttribute("trocadevolucao", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqtrocadevolucao.jsp");
        }

        d.forward(request, response);
    }

}