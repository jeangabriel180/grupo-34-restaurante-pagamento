package com.fiap.restaurante.pagamento.core.usecase;

import com.fiap.restaurante.pagamento.application.port.out.PagamentoServicePortOut;
import com.fiap.restaurante.pagamento.application.port.out.usecase.PagamentoUseCasePortOut;
import com.fiap.restaurante.pagamento.core.domain.Pagamento;
import com.fiap.restaurante.pagamento.infrastructure.entity.PagamentoEntity;
import com.mercadopago.resources.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PagamentoUseCase implements PagamentoUseCasePortOut {

    @Autowired
    private PagamentoServicePortOut pagamentoServicePortOut;

    @Override
    public void cadastrarNovoPagamento(Pagamento pagamento) {
        pagamentoServicePortOut.cadastrarNovoPagamento(pagamento);
    }

    @Override
    public String consultarStatusPagamento(String idPedido) {
        return this.pagamentoServicePortOut.consultarStatusPagamento(idPedido);
    }

    @Override
    public String gerarQRCodePagamento(Double valor, String descricao) {
        return this.pagamentoServicePortOut.gerarQRCodePagamento(valor, descricao);
    }

    @Override
    public ResponseEntity<String> receberNotificacao(Map<String, Object> payload) {
        return this.pagamentoServicePortOut.receberNotificacao(payload);
    }

    @Override
    public Payment consultarPagamentoML(String paymentId) {
        return this.pagamentoServicePortOut.consultarPagamentoML(paymentId);
    }

}
