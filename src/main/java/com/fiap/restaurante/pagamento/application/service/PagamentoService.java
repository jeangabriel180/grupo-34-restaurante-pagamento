package com.fiap.restaurante.pagamento.application.service;

import com.fiap.restaurante.pagamento.application.port.out.PagamentoAdapterPortOut;
import com.fiap.restaurante.pagamento.application.port.out.PagamentoServicePortOut;
import com.fiap.restaurante.pagamento.core.domain.Pagamento;
import com.mercadopago.resources.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PagamentoService implements PagamentoServicePortOut {

    @Autowired
    private PagamentoAdapterPortOut pagamentoAdapterPortOut;

    @Override
    public String consultarStatusPagamento(String idPedido) {
        return this.pagamentoAdapterPortOut.consultarStatusPagamento(idPedido);
    }

    @Override
    public String gerarQRCodePagamento(Double valor, String descricao) {
        return this.pagamentoAdapterPortOut.gerarQRCodePagamento(valor, descricao);
    }

    @Override
    public ResponseEntity<String> receberNotificacao(Map<String, Object> payload) {
        return this.pagamentoAdapterPortOut.receberNotificacao(payload);
    }

    @Override
    public Payment consultarPagamentoML(String paymentId) {
        return this.pagamentoAdapterPortOut.consultarPagamentoML(paymentId);
    }

    @Override
    public void cadastrarNovoPagamento(Pagamento pagamento) {
        pagamentoAdapterPortOut.cadastrarNovoPagamento(pagamento);
    }

}
