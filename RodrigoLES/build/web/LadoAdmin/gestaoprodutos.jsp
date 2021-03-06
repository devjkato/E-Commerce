<%-- 
    Document   : gestaoprodutos
    Created on : 12/03/2016, 07:41:52
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.dominio.Produto"%>

<div class="content">
    <div class="account-in">
        <div>
            <header><h1>Deve conter gr�ficos e avisos referente a produtos</h1></header></div>
                <%
                    resultado = (Resultado) request.getAttribute("resultado");
                %>
        <TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="4"><BR>
                    <H3>PRODUTOS</H3>
                </TH>
            </TR>

            <TR>
                <TH>ID:</TH>
                <TH>Produto:</TH>
                <TH>Quantidade:</TH>
                <TH>Status:</TH>
            </TR>


            <%       if (resultado != null) {
                    List<EntidadeDominio> entidades = resultado.getEntidades();
                    StringBuilder sbRegistro = new StringBuilder();
                    StringBuilder sbLink = new StringBuilder();

                    if (entidades != null) {
                        for (int i = 0; i < entidades.size(); i++) {
                            Produto p = (Produto) entidades.get(i);
                            sbRegistro.setLength(0);
                            sbLink.setLength(0);

                            //	<a href="nome-do-lugar-a-ser-levado">descri��o</a>
                            sbRegistro.append("<TR ALIGN='CENTER'>");

                            sbLink.append("<a href=SalvarProduto?");
                            sbLink.append("txtId=");
                            sbLink.append(p.getId());
                            sbLink.append("&");
                            sbLink.append("operacao=");
                            sbLink.append("VISUALIZAR");

                            sbLink.append(">");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append(p.getId());
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append(p.getNome());
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append(p.getQuantidade());
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            if (p.getFlg_ativo()) {
                                sbRegistro.append("Ativo");
                            } else {
                                sbRegistro.append("Inativo");
                            }
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("</TR>");

                            out.print(sbRegistro.toString());

                        }
                    }

                }

            %>
        </TABLE>
    </div>	
</div>
<!---->
