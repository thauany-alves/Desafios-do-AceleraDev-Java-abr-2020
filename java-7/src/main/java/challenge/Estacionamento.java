package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private static final int LIMITE_VAGAS= 10;
    private static final int IDADE_MINIMA = 18;
    private static final int LIMITE_PONTOS_HABILITACAO = 20;
    private static final int IDADE_MIN_PRIORIDADE =55;
    private List<Carro> carroList = new ArrayList<>();



    public void estacionar(Carro carro) {
        validarMotorista(carro);
        if (carrosEstacionados()==LIMITE_VAGAS)
            removeCarro();
        carroList.add(carro);
    }

    public int carrosEstacionados() {
        return carroList.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carroList.contains(carro);
    }

    private void removeCarro(){
        Carro carro = carroList.stream()
                .filter(c -> c.getMotorista().getIdade() <= IDADE_MIN_PRIORIDADE)
                .findFirst().orElseThrow(() -> new EstacionamentoException("Estacionamento lotado!"));
        carroList.remove(carro);
    }

    private void validarMotorista(Carro carro){
        if (carro.getMotorista() == null) throw new EstacionamentoException("Necessário um motorista");
        if (carro.getMotorista().getIdade() < IDADE_MINIMA) throw new EstacionamentoException("Motorista não pode estacionar, menor de idade");
        if (carro.getMotorista().getPontos() > LIMITE_PONTOS_HABILITACAO) throw new EstacionamentoException("Habilitacao suspensa");
    }
}

