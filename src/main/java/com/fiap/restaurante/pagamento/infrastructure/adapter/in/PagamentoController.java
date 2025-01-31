package com.fiap.restaurante.pagamento.infrastructure.adapter.in;

import com.fiap.restaurante.pagamento.application.port.out.usecase.PagamentoUseCasePortOut;
import com.fiap.restaurante.pagamento.infrastructure.adapter.in.request.PagamentoRequest;
import com.fiap.restaurante.pagamento.infrastructure.adapter.in.request.QrCodeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Operações de pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoUseCasePortOut pagamentoUseCasePortOut;

    @Operation(summary = "Consultar status do pagamento", description = "Consulta o status do pagamento de um pedido.")
    @GetMapping("/status/{idPedido}")
    public String consultarStatusPagamento(@PathVariable String idPedido) {
        return this.pagamentoUseCasePortOut.consultarStatusPagamento(idPedido);
    }

    @Operation(summary = "Gerar QR Code para pagamento", description = "Gera um QR Code para um pagamento com base no valor e descrição.")
    @PostMapping("/gerar-qrcode")
    public String gerarQrCode(@RequestBody QrCodeRequest qrCodeRequest) {
        return pagamentoUseCasePortOut.gerarQRCodePagamento(qrCodeRequest.valor(), qrCodeRequest.descricao());
    }

    @Operation(summary = "Receber notificação de pagamento", description = "Recebe uma notificação de pagamento com o payload.")
    @PostMapping("/webhook")
    public ResponseEntity<String> receberNotificacao(@RequestBody Map<String, Object> payload) {
        return pagamentoUseCasePortOut.receberNotificacao(payload);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarNovoPagamento(@RequestBody PagamentoRequest request) {
        pagamentoUseCasePortOut.cadastrarNovoPagamento(request.toDomain());
        return new ResponseEntity<>("Pagamento cadastrado com sucesso", HttpStatus.OK);

    }
}
