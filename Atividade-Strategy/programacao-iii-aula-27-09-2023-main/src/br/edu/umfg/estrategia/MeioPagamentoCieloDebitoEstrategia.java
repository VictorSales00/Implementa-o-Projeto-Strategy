package br.edu.umfg.estrategia;

public class MeioPagamentoCieloDebitoEstrategia extends MeioPagamentoCieloEstrategia implements MeioPagamentoEstrategia {

    public MeioPagamentoCieloDebitoEstrategia(String numeroCartao, String cpf, String cvv, String dataValidade, String tipoCartao) {
        super(numeroCartao, cpf, cvv, dataValidade, tipoCartao);
    }

    @Override
    public void validarDadosDebito() {
        super.validarDadosDebito();
    }

    @Override
    public void pagar(Double valor){
        System.out.println("Pagamento via Debito Cielo no valor R$" + valor + ", realizado com sucesso!");
    }

}