package br.edu.umfg.estrategia;

public class MeioPagamentoCieloCreditoEstrategia extends MeioPagamentoCieloEstrategia implements MeioPagamentoEstrategia {

    public MeioPagamentoCieloCreditoEstrategia(String numeroCartao, String cpf, String cvv, String dataValidade, String tipoCartao) {
        super(numeroCartao, cpf, cvv, dataValidade, tipoCartao);
    }

    @Override
    public void validarDadosCredito() {
        super.validarDadosCredito();
    }

    @Override
    public void pagar(Double valor){
        System.out.println("Pagamento via Credito Cielo no valor R$" + valor + ", realizado com sucesso");
    }

}
